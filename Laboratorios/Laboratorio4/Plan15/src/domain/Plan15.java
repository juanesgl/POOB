package src.domain;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Plan15
 * @author POOB 
 * @version ECI 2025
 */
public class Plan15 {
    private ArrayList<Unit> units;
    private TreeMap<String, Course> courses;

    /**
     * Create a Plan15
     */
    public Plan15() {
        units = new ArrayList<Unit>();
        courses = new TreeMap<String, Course>();
        try {
            addSome();
        } catch (Plan15Exception e) {
            System.out.println("Error al agregar cursos por defecto: " + e.getMessage());
        }
    }

    private void addSome() throws Plan15Exception {
        String[][] courses = {
                {"PRI1", "Proyecto Integrador", "9", "3"},
                {"DDYA", "Diseño de Datos y Algoritmos", "4", "4"},
                {"MPIN", "Matematicas para Informatica", "3", "4"},
                {"DOSW", "Desarrollo y Operaciones Software", "4", "4"}
        };

        for (String[] course : courses) {
            addCourse(course[0], course[1], course[2], course[3]);
        }

        String[][] cores = {
                {"FCC", "Nucleo formacion por comun por campo", "50", "PRI1\nDDYA\nMPIN"},
                {"NFPE", "Nucleo de formación específica", "100", "DOSW"}
        };

        for (String[] core : cores) {
            try {
                addCore(core[0], core[1], core[2], core[3]);
            } catch (Plan15Exception e) {
                System.out.println("Error al adicionar núcleo " + core[0] + ": " + e.getMessage());
            }
        }
    }

    /**
     * Consult a unit by its code.
     *
     * @param name The code of the unit to consult.
     * @return The unit if found, otherwise null.
     */
    public Unit consult(String name) {
        Unit c = null;
        for (int i = 0; i < units.size() && c == null; i++) {
            if (units.get(i).code().compareToIgnoreCase(name) == 0) {
                c = units.get(i);
            }
        }
        return c;
    }

    /**
     * Add a new course to the plan.
     *
     * @param code     The code of the course.
     * @param name     The name of the course.
     * @param credits  The credits of the course.
     * @param inPerson The in-person hours of the course.
     * @throws Plan15Exception If the credits or in-person hours are not integers.
     */
    public void addCourse(String code, String name, String credits, String inPerson) throws Plan15Exception {
        try {
            int c = Integer.parseInt(credits);
            int i = Integer.parseInt(inPerson);
            Course nc = new Course(code, name, c, i);
            units.add(nc);
            courses.put(code.toUpperCase(), nc);
        } catch (NumberFormatException e) {
            throw new Plan15Exception("Los créditos y horas presenciales deben ser números enteros.");
        }
    }

    /**
     * Add a new core to the plan.
     *
     * @param code       The code of the core.
     * @param name       The name of the core.
     * @param percentage The percentage of the core.
     * @param theCourses The courses that belong to the core, separated by newlines.
     * @throws Plan15Exception If the code is not uppercase, the percentage is not an integer,
     * the percentage is not between 0 and 100, or a course does not exist.
     */
    public void addCore(String code, String name, String percentage, String theCourses) throws Plan15Exception {
        code = code.trim();

        if (!code.equals(code.toUpperCase())) {
            throw new Plan15Exception("La sigla debe estar en mayúsculas.");
        }

        int parsedPercentage;
        try {
            parsedPercentage = Integer.parseInt(percentage);
        } catch (NumberFormatException e) {
            throw new Plan15Exception("El porcentaje debe ser un número entero.");
        }

        if (parsedPercentage < 0 || parsedPercentage > 100) {
            throw new Plan15Exception("El porcentaje debe estar entre 0 y 100.");
        }

        Core c = new Core(code, name, parsedPercentage);

        String[] aCourses = theCourses.split("\n");
        for (String b : aCourses) {
            b = b.trim();
            if (b.isEmpty()) continue;

            Course course = courses.get(b.toUpperCase());
            if (course == null) {
                throw new Plan15Exception("El curso básico '" + b + "' no existe.");
            }

            c.addCourse(course);
        }

        units.add(c);
    }

    /**
     * Select units that start with a given prefix.
     *
     * @param prefix The prefix to search for.
     * @return An ArrayList of units that start with the prefix.
     */
    public ArrayList<Unit> select(String prefix) {
        ArrayList<Unit> answers = new ArrayList<Unit>();
        prefix = prefix.toUpperCase();
        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).code().toUpperCase().startsWith(prefix)) {
                answers.add(units.get(i));
            }
        }
        return answers;
    }

    /**
     * Get the data of a list of units.
     *
     * @param selected The list of units to get data from.
     * @return A String containing the data of the units.
     * @throws Plan15Exception If the list of units is empty.
     */
    public String data(ArrayList<Unit> selected) throws Plan15Exception {
        if (selected.isEmpty()) {
            throw new Plan15Exception("No se encontraron unidades que comiencen con ese prefijo.");
        }
        StringBuffer answer = new StringBuffer();
        answer.append(units.size() + " unidades\n");
        for (Unit p : selected) {
            try {
                answer.append('>').append(p.data());
                answer.append("\n");
            } catch (Plan15Exception e) {
                answer.append("**** " + e.getMessage());
            }
        }
        return answer.toString();
    }

    /**
     * Search for units that start with a given prefix and return their data.
     *
     * @param prefix The prefix to search for.
     * @return A String containing the data of the found units.
     * @throws Plan15Exception If no units are found with the given prefix.
     */
    public String search(String prefix) throws Plan15Exception {
        ArrayList<Unit> selected = select(prefix);
        if (selected.isEmpty()) {
            Plan15Exception e = new Plan15Exception("No se encontraron unidades que comiencen con: " + prefix);
            Log.record(e);
            throw e;
        }
        return data(selected);
    }

    /**
     * Get a String representation of the plan.
     *
     * @return A String representation of the plan.
     */
    public String toString() {
        try {
            return data(units);
        } catch (Plan15Exception e) {
            Log.record(e);
            return "Error al generar la representación del plan.";
        }
    }

    /**
     * Get the number of units in the plan.
     *
     * @return The number of units in the plan.
     */
    public int numberUnits() {
        return units.size();
    }
}