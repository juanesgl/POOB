import java.awt.*;

/**
 * A triangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0  (15 July 2000)
 */

public class Triangle{
    
    public static final int VERTICES = 3;
    
    private int height;
    private int width;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;

    /**
     * Create a new triangle at default position with default color.
     */
    public Triangle(){
        height = 30;
        width = 40;
        xPosition = 140;
        yPosition = 15;
        color = "green";
        isVisible = false;
    }

    /**
     * Make this triangle visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this triangle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /**
     * Move the triangle a few pixels to the right.
     */
    public void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Move the triangle a few pixels to the left.
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }

    /**
     * Move the triangle a few pixels up.
     */
    public void moveUp(){
        moveVertical(-20);
    }

    /**
     * Move the triangle a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }

    /**
     * Move the triangle horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the triangle vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the triangle horizontally.
     * @param distance the desired distance in pixels
     */
    public void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the triangle vertically.
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
    }

    /**
     * Change the size to the new size
     * @param newHeight the new height in pixels. newHeight must be >=0.
     * @param newWidht the new width in pixels. newWidht must be >=0.
     */
    public void changeSize(int newHeight, int newWidth) {
        erase();
        if(newHeight >= 0 && newWidth >= 0){
            height = newHeight;
            width = newWidth;
        }
        draw();
    }
    /**
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }

    /*
     * Draw the triangle with current specifications on screen.
     */
    private void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = { xPosition, xPosition + (width/2), xPosition - (width/2) };
            int[] ypoints = { yPosition, yPosition + height, yPosition + height };
            canvas.draw(this, color, new Polygon(xpoints, ypoints, 3));
            canvas.wait(10);
        }
    }

    /*
     * Erase the triangle on screen.
     */
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    /**
     * Found the area of the triangle. 
     * @param just to search the area of the triangle based on the height and width. 
     */
    public int area(){
        return (width * height) / 2;
    }
    /**
     * Transforms the triangle into an equilateral triangle with the same area.
     * The new triangle will have equal sides, and its area will be equivalent to the original triangle's area.
     */
    public void equilateral(){
        int originalArea = this.area();
        
        double side = Math.sqrt((4 * originalArea) / Math.sqrt(3));
        
        int newHeight = (int) (side * Math.sqrt(3) / 2);
        
        int newWidth = (int) side;
        
        this.changeSize(newHeight, newWidth);  
    }
    /**
     * Transforms the triangle into an equilateral triangle with the same area.
     * The new triangle will have equal sides, and its area will be equivalent to the original triangle's area.
     */
    
    public void shrink(int times, int height){
        if(this.height > height){
            
            for (int i = 0; i < times; i++){
                
                this.height = (int)(this.height * 0.9);
                this.width = (int)(this.width * 0.9);  
                    
            }   
            this.changeSize(this.height, this.width);
        }
    }
        /**
     * Create a new triangle at a specific position with default size and color.
     * 
     * @param x The x-coordinate of the triangle's position.
     * @param y The y-coordinate of the triangle's position.
     */
    public Triangle(int x, int y) {  
        height = 30;  
        width = 40;  
        xPosition = x;  
        yPosition = y;
        color = "green";
        isVisible = false;
    }
    
        /**
     * Rotates the triangle around its centroid by a given angle (in degrees).
     * 
     * @param angle The angle (in degrees) to rotate the triangle.
     */
    public void rotate(int angle) {
        if (!isVisible) return;  
    
        erase();  
    
        double radians = Math.toRadians(angle);  
    
        int centerX = xPosition;
        int centerY = yPosition + (height / 3);
    
        int[] xPoints = { xPosition, xPosition + (width / 2), xPosition - (width / 2) };
        int[] yPoints = { yPosition, yPosition + height, yPosition + height };
    
        for (int i = 0; i < 3; i++) {
            int x = xPoints[i];
            int y = yPoints[i];
    
            int newX = (int) (centerX + (x - centerX) * Math.cos(radians) - (y - centerY) * Math.sin(radians));
            int newY = (int) (centerY + (x - centerX) * Math.sin(radians) + (y - centerY) * Math.cos(radians));
    
            xPoints[i] = newX;
            yPoints[i] = newY;
        }
    
        Canvas canvas = Canvas.getCanvas();
        canvas.draw(this, color, new Polygon(xPoints, yPoints, 3));
    }


}
