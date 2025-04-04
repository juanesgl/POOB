package src.domain; 

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Plan15
 * @author POOB  
 * @version ECI 2025
 */

public class Plan15{
    private ArrayList<Unit> units;
    private TreeMap<String,Course> courses;

    /**
     * Create a Plan15
     */
    public Plan15(){
        units = new ArrayList<Unit>();
        courses = new TreeMap<String,Course>();
        addSome();
    }

    private void addSome() {
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
            addCore(core[0], core[1], core[2], core[3]);
        }
    }


    /**
     * Consult a unit
     */
    public Unit consult(String name){
        Unit c=null;
        for(int i=0;i<units.size() && c == null;i++){
            if (units.get(i).code().compareToIgnoreCase(name)==0) 
               c=units.get(i);
        }
        return c;
    }

    
    /**
     * Add a new course
    */
    public void addCourse(String code, String name, String credits, String inPerson){ 
        Course nc=new Course(code,name,Integer.parseInt(credits),Integer.parseInt(inPerson));
        units.add(nc);
        courses.put(code.toUpperCase(),nc); 
    }
    
    /**
     * Add a new core
    */
    public void addCore(String code, String name, String percentage, String theCourses){
        Core c = new Core(code,name,Integer.parseInt(percentage));
        String [] aCourses= theCourses.split("\n");
        for (String b : aCourses){
            c.addCourse(courses.get(b.toUpperCase()));
        }
        units.add(c);
    }

    /**
     * Consults the units that start with a prefix
     * @param  
     * @return 
     */
    public ArrayList<Unit> select(String prefix){
        ArrayList <Unit> answers=new ArrayList<Unit>();
        prefix=prefix.toUpperCase();
        for(int i=0;i < units.size();i++){
            if(units.get(i).code().toUpperCase().startsWith(prefix)){
                answers.add(units.get(i));
            }   
        }
        return answers;
    }


    
    /**
     * Consult selected units
     * @param selected
     * @return  
     */
    public String data(ArrayList<Unit> selected){
        StringBuffer answer=new StringBuffer();
        answer.append(units.size()+ " unidades\n");
        for(Unit p : selected) {
            try{
                answer.append('>' + p.data());
                answer.append("\n");
            }catch(Plan15Exception e){
                answer.append("**** "+e.getMessage());
            }
        }    
        return answer.toString();
    }
    
    
     /**
     * Return the data of units with a prefix
     * @param prefix
     * @return  
     */ 
    public String search(String prefix){
        return data(select(prefix));
    }
    
    
    /**
     * Return the data of all units
     * @return  
     */    
    public String toString(){
        return data(units);
    }
    
    /**
     * Consult the number of units
     * @return 
     */
    public int numberUnits(){
        return units.size();
    }

}
