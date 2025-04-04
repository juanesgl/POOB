package src.domain;  
 
import java.util.ArrayList;

public class Core extends Unit{
   

    private int inPersonPercentage;
    private ArrayList<Course> courses;
    
    /**
     * Constructs a new core unit
     * @param code
     * @param name
     * @param credits 
     */
    public Core(String code, String name, int inPersonPercentage){
        super(code, name);
        this.inPersonPercentage=inPersonPercentage;
        courses= new ArrayList<Course>();
    }


     /**
     * Add a new course
     * @param s
     */   
    public void addCourse(Course c){
        courses.add(c);
    }
       
 
    
    @Override
    public int credits() throws Plan15Exception{
        int totalCredits = 0;
        boolean hasValidCourse = false;
        
        for(Course c: courses){
            
                totalCredits += c.credits();
                hasValidCourse = true;
            }
            
        if(!hasValidCourse){
            throw new Plan15Exception(Plan15Exception.IMPOSSIBLE);
        
        }
        
        return totalCredits;

    }

    @Override
    public int inPerson() throws Plan15Exception{
        return 0;
    };    
    
    
    /**
    * Calculate the actual or estimated credits 
    * If necessary (unknown or error), assumes the number of credits is 3 
    * is equal to the in-person hours.
    * @return
    * @throws Plan15Exception IMPOSSIBLE, If there are no credits 
    */
    public int creditsEstimated() throws Plan15Exception{
        int totalEstimatedCredits = 0;
        boolean hasValidCourse = false;
        
        for(Course c: courses){
            try{
                totalEstimatedCredits += c.credits();
                hasValidCourse = true;
            }catch (Plan15Exception e){
                totalEstimatedCredits += 3;
            
            }
        }
        
        if(!hasValidCourse){
            throw new Plan15Exception(Plan15Exception.IMPOSSIBLE);
        }
        return totalEstimatedCredits;
    } 
    
    /**
    * Calculate the estimated in-person hours, considering courses that do not have credit issues. 
    * If the hours of a course are not known, calculate the course in-person hours using the percentage suggested in the unit core.
    * If the hours of a course are incorrect, it is assumed that all the time is in person. 
    * @return
    * @throws Plan15Exception IMPOSSIBLE, If there are no courses or all courses has credit issues
    */
    public int inPersonEstimated() throws Plan15Exception{
        
        int totalInPersonHours = 0;
        boolean hasValidCourse = false;
        
        for(Course c: courses){
            try{
                totalInPersonHours += c.inPerson();
                hasValidCourse = true;
            }catch(Plan15Exception e){
                try{
                    totalInPersonHours += (int) (c.credits() * (inPersonPercentage / 100.0));
                
                
                }catch(Plan15Exception ex){
                
                    totalInPersonHours += c.credits();
                }
            }
        }
        if(!hasValidCourse){
            throw new Plan15Exception(Plan15Exception.IMPOSSIBLE);
        }
        return totalInPersonHours;
    }   
    

    @Override
    public String data() throws Plan15Exception{
        StringBuffer answer=new StringBuffer();
        answer.append(code+": "+name+". ["+inPersonPercentage+"%]");
        for(Course c: courses) {
            answer.append("\n\t"+c.data());
        }
        return answer.toString();
    } 
    

}
