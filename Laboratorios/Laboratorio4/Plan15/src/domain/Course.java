package src.domain;

/**
 * Represents a course unit.
 */
public class Course extends Unit {

    private Integer credits;
    private Integer inPerson;

    /**
     * Constructs a new course unit with only code and name.
     *
     * @param code The code of the course.
     * @param name The name of the course.
     */
    public Course(String code, String name) {
        super(code, name);
    }

    /**
     * Constructs a new course unit with code, name, and credits.
     *
     * @param code    The code of the course.
     * @param name    The name of the course.
     * @param credits The credits of the course.
     */
    public Course(String code, String name, int credits) {
        super(code, name);
        this.credits = credits;
    }

    /**
     * Constructs a new course unit with code, name, credits, and in-person hours.
     *
     * @param code     The code of the course.
     * @param name     The name of the course.
     * @param credits  The credits of the course.
     * @param inPerson The in-person hours of the course.
     */
    public Course(String code, String name, int credits, int inPerson) {
        this(code, name, credits);
        this.inPerson = inPerson;
    }

    @Override
    public int credits() throws Plan15Exception {
        if (credits == null) throw new Plan15Exception(Plan15Exception.CREDITS_UNKNOWN);
        if (credits <= 0) throw new Plan15Exception(Plan15Exception.CREDITS_ERROR);
        if ((inPerson != null) && (inPerson > 3 * credits))
            throw new Plan15Exception(Plan15Exception.CREDITS_ERROR);
        return credits;
    }

    @Override
    public int inPerson() throws Plan15Exception {
        if (inPerson == null) throw new Plan15Exception(Plan15Exception.IN_PERSON_UNKNOWN);
        if (inPerson <= 0) throw new Plan15Exception(Plan15Exception.IN_PERSON_ERROR);
        if ((credits != null) && (inPerson > 3 * credits))
            throw new Plan15Exception(Plan15Exception.CREDITS_ERROR);
        return inPerson;
    }

    @Override
    public String data() {
        String theData = code + ": " + name;
        try {
            theData = theData + ". Creditos:" + credits() + "[" + inPerson() + "+" + independent() + "]";
        } catch (Plan15Exception e) {
        }
        return theData;
    }
}