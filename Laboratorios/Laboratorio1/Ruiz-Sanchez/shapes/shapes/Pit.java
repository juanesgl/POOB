
/**
 * Write a description of class Pit here.
 *
 * @author (Daniel Ruiz/Juan Sanchez)
 * @version (Version 1)
 */
public class Pit
{
    // instance variables - replace the example below with your own
    private int seeds;
    private Rectangle hueco;
    private boolean isVisible;
    

    /**
     * Constructor for objects of class Pit
     * @param big When boolean big is true creates the Rectangle 100*100 and if is false creates it 50*50
     * seeds number starts in 0
     * Visible starts in false
   
     */
    public Pit(boolean big)
    {
        // initialise instance variables
        hueco =new Rectangle();
        if(big){
            hueco.changeSize(200,800);
        }else{
            hueco.changeSize(190,90);
        }
        seeds=0;
        isVisible = false;
    }

    /**
     * Add seeds in the box
     *
     * @param  seeds  number of seeds that are added to the box
     */
    public void putSeeds(int seeds)
    {
        // put your code here
        this.seeds += seeds;
    }
    
       /**
     * Remove seeds from the box
     *
     * @param  seeds  number of seeds that are removed from the box, can't be >0
     */
    public void removeSeeds(int seeds)
    {
        // put your code here
        // this.seeds -= seeds; esta era la idea inicial pero permite llevar a menos de cero la cantidad
        this.seeds = Math.max(this.seeds-seeds,0);
    }
    
    /**
     * Make this hueco visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        if (!isVisible){
            hueco.makeVisible();
            isVisible = true;
        }
    
    }
    
    /**
     * Make this hueco invisible. If it was already visible, do nothing.
     */
    public void makeInvisible(){
        if (isVisible){
            hueco.makeInvisible();
            isVisible = false;
        }
    
    
    }
    
       /**
     * Check number of seeds
       */
    public int seeds(){
        return this.seeds;
    }
      /**
     * Change color of Hueco
     * @param background color for hueco
       */
    public void changeColor(String background) {
        this.hueco.changeColor(background);
           }
      /**
     * Move hueco in x and y
     * @param x distance in exe X in pixels
     * @param y distance in exe y in pixels
       */
    public void moveTo(int x, int y) {
        this.hueco.moveHorizontal(x);
        this.hueco.moveVertical(y);        
           }
           
        /**
     * Obtener Rectangle para hueco
       */
    public Rectangle gethueco() {
        return hueco;        
           }
}
