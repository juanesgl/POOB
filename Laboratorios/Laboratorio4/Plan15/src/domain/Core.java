package src.domain;

import java.util.ArrayList;

/**
 * Represents a core unit, which is a collection of courses.
 */
public class Core extends Unit {

    private int inPersonPercentage;
    private ArrayList<Course> courses;

    /**
     * Constructs a new core unit.
     *
     * @param code              The code of the core.
     * @param name              The name of the core.
     * @param inPersonPercentage The percentage of in-person hours for the core.
     */
    public Core(String code, String name, int inPersonPercentage) {
        super(code, name);
        this.inPersonPercentage = inPersonPercentage;
        courses = new ArrayList<Course>();
    }

    /**
     * Adds a new course to the core.
     *
     * @param c The course to add.
     */
    public void addCourse(Course c) {
        courses.add(c);
    }

    @Override
    public int credits() throws Plan15Exception {
        int totalCredits = 0;
        boolean hasValidCourse = false;

        for (Course c : courses) {
            totalCredits += c.credits();
            hasValidCourse = true;
        }

        if (!hasValidCourse) {
            throw new Plan15Exception(Plan15Exception.IMPOSSIBLE);
        }

        return totalCredits;
    }

    @Override
    public int inPerson() throws Plan15Exception {
        return 0;
    }

    /**
     * Calculates the estimated credits for the core.
     * If a course's credits are unknown or an error occurs, it assumes 3 credits.
     *
     * @return The estimated total credits.
     * @throws Plan15Exception If there are no courses.
     */
    public int creditsEstimated() throws Plan15Exception {
        int totalEstimatedCredits = 0;
        boolean hasValidCourse = false;

        for (Course c : courses) {
            try {
                totalEstimatedCredits += c.credits();
                hasValidCourse = true;
            } catch (Plan15Exception e) {
                totalEstimatedCredits += 3;
            }
        }

        if (!hasValidCourse) {
            throw new Plan15Exception(Plan15Exception.IMPOSSIBLE);
        }
        return totalEstimatedCredits;
    }

    /**
     * Calculates the estimated in-person hours for the core.
     * If a course's in-person hours are unknown, it calculates them using the core's percentage.
     * If a course's in-person hours are incorrect, it assumes all hours are in-person.
     *
     * @return The estimated total in-person hours.
     * @throws Plan15Exception If there are no courses.
     */
    public int inPersonEstimated() throws Plan15Exception {
        int totalInPersonHours = 0;
        boolean hasValidCourse = false;

        for (Course c : courses) {
            try {
                totalInPersonHours += c.inPerson();
                hasValidCourse = true;
            } catch (Plan15Exception e) {
                try {
                    totalInPersonHours += (int) (c.credits() * (inPersonPercentage / 100.0));
                } catch (Plan15Exception ex) {
                    totalInPersonHours += c.credits();
                }
            }
        }
        if (!hasValidCourse) {
            throw new Plan15Exception(Plan15Exception.IMPOSSIBLE);
        }
        return totalInPersonHours;
    }

    @Override
    public String data() throws Plan15Exception {
        StringBuffer answer = new StringBuffer();
        answer.append(code + ": " + name + ". [" + inPersonPercentage + "%]");
        for (Course c : courses) {
            answer.append("\n\t" + c.data());
        }
        return answer.toString();
    }
}