/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screenObjects;

import brickbreaker.BrickBreakerMain;
import static brickbreaker.BrickBreakerMain.options;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.shape.Circle;
import static screenObjects.AbstractScreenObject.BASICPLAYERBULLETSCREENOBJECTID;
import static screenObjects.AbstractScreenObject.rainbowColors;

/**
 *
 * @author ge29779
 */
public class BasicPlayerBulletScreenObject extends AbstractScreenObject{
   
    //what angle is the bullet rotated at
    private final float degreesToRotate = 10;
    
    //default bullet damage
    private final int defaultDamage = 5;
    
    //speed of the bullet
    private final int speed = 10;
    
    //the name of the shape of the bullet
    private String shapeName;
    
    //color of the bullet
    private Color color;
    
    //color of the player
    private Color playerColor;
    
    
    //distance out of bounds which will cause bullet to be removed
    private int boundsDistance;
    private int maxLifeTime;
    
    //has a bullet collided
    private boolean hasHit;
    
    //how much damage the bullet causes
    private int damage;


    public BasicPlayerBulletScreenObject() {
        super();

        damage = defaultDamage;
        init();
    }

    public BasicPlayerBulletScreenObject(float xTemp, float yTemp, int widthTemp, int heightTemp, boolean collisionTemp, boolean acceptingInput, float playerDegrees, Color playerColor) {
        super(xTemp, yTemp, widthTemp, heightTemp, BASICPLAYERBULLETSCREENOBJECTID, collisionTemp, acceptingInput);
        if (Debug.isEnabled()) {
            System.out.println("Creating Player Bullet at X : " + xTemp + " Y : " + yTemp);
        }
        
        //this centers the shape
        float tempX = getX() - getWidth() / 2;
        float tempY = getY() - getHeight() / 2;
        setX(tempX);
        setY(tempY);
        
        //this should set the x and y movement
        
        setSpeed(speed);
        setDegrees(-playerDegrees);
        
        if (Debug.isEnabled()) {
            System.out.println("deltaX : " + getDeltaX());
            System.out.println("deltaY : " + getDeltaY());

        }
        
        
        this.playerColor = playerColor;
        if (Debug.isEnabled()) {
            System.out.println("Degrees : " + getDegrees());
            System.out.println("playerColor : " + this.playerColor.toString());
        }
        
        damage = defaultDamage;
        init();
    }
    
    public void callInit(){
        init();
    }
    
    public void init() {
        
        //sets default maxlifetime for bullet
        maxLifeTime = 500;
        
        this.shapeName = options.getProperty("bulletShape");
        if (Debug.isEnabled()) {
            System.out.println("Shape : " + this.shapeName);
        }
        
        switch(shapeName){
            case "diamond" : createDiamond(); break;

            case "circle" : createCircle(); break;
            
            case "triangle" : createTriangle(); break;

            case "square" : createSquare(); break;
            
            case "hourglass" : createHourglass(); break;
                   
        }
        
        String colorString = BrickBreakerMain.getOptions().getProperty("bulletColor");
        switch(colorString){
            case "playerColor" : color = playerColor; break;
            case "random" : color = getRandomColor(); break;
            default : try {
                                Field field = Class.forName("java.awt.Color").getField(colorString.toLowerCase()); // toLowerCase because the color fields are RED or red, not Red
                                color = (Color) field.get(null);
                            } catch (Exception ex) {
                                Logger.getLogger(PlayerScreenObject.class.getName()).log(Level.SEVERE, null, ex);
                                color = Color.white;
                            }
        }
        if (Debug.isEnabled()) {
            System.out.println("Color : " + this.color);
        }
        
        if(getHeight() >= getWidth()){
            boundsDistance = getHeight();

        }
        else{
            boundsDistance = getWidth();
        }
        if (Debug.isEnabled()) {
            System.out.println("boundsDistance : " + this.boundsDistance);
        }
        hasHit = false;
    }
    
    @Override
    public void move() {
        //hehehe
        float cumX = getSpeed() * getDeltaX();
        float cumY = getSpeed() * getDeltaY();
        
        //Just gonna put this here to document the fact that I spent 15 minutes trying to fix the issue that
        //due to rounding to an integer to pass to translateMyShape, bullets would never hit the mouse directly
        //then I noticed the parameter was a float :( .... I wrote that method
        
        translateMyShape(cumX, cumY);
        
        rotateMyShape(degreesToRotate, getMyShape().getBounds2D().getCenterX(), getMyShape().getBounds2D().getCenterY());
        setCollisionShape(getMyShape());
    }

    @Override
    public boolean shouldDestroyObject(){
        return (checkIsOffScreen((int)boundsDistance) || hasHit || maxLifeTime <= 0);
    }
    
    
    @Override //NOTE - This checks for if the CENTER is off the screen by the amount
    public boolean checkIsOffScreen(int byAmount){
        if(getMyShape().getBounds2D().getCenterX() < 0 - byAmount){
            return true;
        }else if(getMyShape().getBounds2D().getCenterX() > brickbreaker.BrickBreakerMain.SCREENWIDTH + byAmount){
            return true;
        }else if(getMyShape().getBounds2D().getCenterY() < 0 - byAmount){
            return true;
        }else if(getMyShape().getBounds2D().getCenterY() > brickbreaker.BrickBreakerMain.SCREENHEIGHT + byAmount){
            return true;
        }
        
        return false;
    }
    
    @Override
    public void handleInput(String inputMethod, ArrayList<Integer> inputList, String inputMethodRemove, ArrayList<Integer> inputListReleased) {}

    @Override
    public void runLogic() {maxLifeTime--;}

    @Override
    public void drawObject(Graphics2D g) {
        
        Color gameColor = g.getColor();
        
        g.setColor(color);
        
        g.fill(getMyShape());
        
        if(Debug.isEnabled()){
            if (isCollision()) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.RED);
            }

            g.draw(getCollisionShape());
        }
        
        g.setColor(gameColor);
        
    }

    private Color getRandomColor() {
        addColors();
        int ranIndex = (int)(Math.random() * rainbowColors.length);
        return rainbowColors[ranIndex];
    }

    
    private void createDiamond() {
        
        //points for shape creation B
        Point topMidPoint = new Point((int) (getX() + (getWidth() / 2)), (int) (getY()));
        Point midLeftPoint = new Point((int) (getX() + getWidth() / 4), (int) (getY() + (getHeight() / 2)));
        Point midRightPoint = new Point((int) (getX() + getWidth() * 3 / 4), (int) (getY() + (getHeight() / 2)));
        Point bottomMidPoint = new Point((int) (getX() + (getWidth() / 2)), (int) (getY() + getHeight()));

        int[] xpoints = {(int)topMidPoint.getX(), (int)midRightPoint.getX(), (int)bottomMidPoint.getX(), (int)midLeftPoint.getX()};
        int[] ypoints = {(int)topMidPoint.getY(), (int)midRightPoint.getY(), (int)bottomMidPoint.getY(), (int)midLeftPoint.getY()};
        
        Shape tempShape = new Polygon(xpoints, ypoints, 4);
        
        setMyShape(tempShape);
        setCollisionShape(getMyShape());
    }
    
    private void createHourglass() {
        
        //points for shape creation B
        Point topRightPoint = new Point((int) (getX() + (getWidth())), (int) (getY()));
        Point topLeftPoint = new Point((int) (getX()), (int) (getY()));
        Point bottomLeftPoint = new Point((int) (getX()), (int) (getY() + getHeight()));
        Point bottomRightPoint = new Point((int) (getX() + getWidth()), (int) (getY() + getHeight()));

        int[] xpoints = {(int)topLeftPoint.getX(), (int)bottomRightPoint.getX(), (int)topRightPoint.getX(), (int)bottomLeftPoint.getX() };
        int[] ypoints = {(int)topLeftPoint.getY(), (int)bottomRightPoint.getY(), (int)topRightPoint.getY(), (int)bottomLeftPoint.getY(), };
        
        Shape tempShape = new Polygon(xpoints, ypoints, 4);
        
        setMyShape(tempShape);
        setCollisionShape(getMyShape());
    }
    
    private void createTriangle() {
     
        Point topMidPoint = new Point((int) (getX() + (getWidth() / 2)), (int) (getY()));
        Point bottomLeftPoint = new Point((int) (getX()), (int) (getY() + getHeight()));
        Point bottomRightPoint = new Point((int) (getX() + getWidth()), (int) (getY() + getHeight()));
        
        int[] xpoints = { (int)bottomLeftPoint.getX(), (int)topMidPoint.getX(), (int)bottomRightPoint.getX()};
        int[] ypoints = { (int)bottomLeftPoint.getY(), (int)topMidPoint.getY(), (int)bottomRightPoint.getY()};
        
        Shape tempShape = (Shape) new Polygon(xpoints, ypoints, 3);
     
        setMyShape(tempShape);
        setCollisionShape(getMyShape());
    }
    
    private void createSquare() {
        
        //points for shape creation
        Point topRightPoint = new Point((int) (getX() + (getWidth())), (int) (getY()));
        Point topLeftPoint = new Point((int) (getX()), (int) (getY()));
        Point bottomLeftPoint = new Point((int) (getX()), (int) (getY() + getHeight()));
        Point bottomRightPoint = new Point((int) (getX() + getWidth()), (int) (getY() + getHeight()));

        int[] xpoints = {(int)topLeftPoint.getX(), (int)bottomLeftPoint.getX(), (int)bottomRightPoint.getX(), (int)topRightPoint.getX() };
        int[] ypoints = {(int)topLeftPoint.getY(), (int)bottomLeftPoint.getY(), (int)bottomRightPoint.getY(), (int)topRightPoint.getY(), };
        
        Shape tempShape = new Polygon(xpoints, ypoints, 4);
        
        setMyShape(tempShape);
        setCollisionShape(getMyShape());
    }
    
    private void createCircle() {
        
        Shape tempShape = new Ellipse2D.Float(getX() + 2, getY() + 2, getWidth() - 4, getHeight() - 4);
        
        setMyShape(tempShape);
        setCollisionShape(getMyShape());
    }
    
    public float getBoundsDistance() {
        return boundsDistance;
    }
    
    public void setHasHit(boolean hasHit){
        this.hasHit = hasHit;
    }
    
    public int getDamage() {
        return damage;
    }
    
    public void setDamage(int damage) {
        this.damage = damage;
    }

}
