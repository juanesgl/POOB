
/**
 * Write a description of class Casa here.
 *
 * @author Daniel Ruiz, Juan Sanchez
 * @version (version 1)
 */
public class Casa
{
    // instance variables - replace the example below with your own
    private int seeds;
    private Circle casa;
    private boolean isVisible;
    

    /**
     * Constructor for objects of class Casa
     */
    public Casa()
    {
        casa = new Circle();
        seeds=3;
    }

  
    
    /**
     * Make this casa visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        if (!isVisible){
            casa.makeVisible();
            isVisible = true;
        }
    
    }
    
    /**
     * Make this casa invisible. If it was already visible, do nothing.
     */
    public void makeInvisible(){
        if (isVisible){
            casa.makeInvisible();
            isVisible = false;
        }
    
    
    }
    
    /**
     * Add seeds in casa
     *
     * @param  seeds  number of seeds that are added to the casa
     */
    public void putSeeds(int seeds)
    {
        // put your code here
        this.seeds += seeds;
    }
    
       /**
     * Remove seeds from casa
     *
     * @param  seeds  number of seeds that are removed from the casa, can't be >0
     */
    public void removeSeeds(int seeds)
    {
        // put your code here
        // this.seeds -= seeds; esta era la idea inicial pero permite llevar a menos de cero la cantidad
        this.seeds = Math.max(this.seeds-seeds,0);
    }
    
    
       /**
     * Check number of seeds
       */
    public int seeds(){
        return this.seeds;
    }
      /**
     * Change color of Casa
     * @param background color for casa
       */
    public void changeColor(String background) {
        this.casa.changeColor(background);
           }
    
             /**
     * Move casa in x and y
     * @param x distance in exe X in pixels
     * @param y distance in exe y in pixels
       */
    public void moveTo(int x, int y) {
        this.casa.moveHorizontal(x);
        this.casa.moveVertical(y);        
           }
       /**
     * Obtener Rectangle para hueco
       */
    public Circle getcasa() {
        return casa;        
           }
    
}
