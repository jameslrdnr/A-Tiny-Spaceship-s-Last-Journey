package screenObjects;

import brickbreaker.BrickBreakerMain;
import static brickbreaker.BrickBreakerMain.getDebug;
import static brickbreaker.BrickBreakerMain.options;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ab29627
 */
public class BasicOptionPopup extends AbstractScreenObject{

    String type;
    private final Font normalFont = new Font(Font.MONOSPACED, Font.BOLD, 20);
    private final Font titleFont = new Font(Font.MONOSPACED, Font.BOLD, 36);
    private final PlayerScreenObject playerExample = new PlayerScreenObject(560, 160, 35, 35, false, false);
    private final BasicPlayerBulletScreenObject bulletExample = new BasicPlayerBulletScreenObject(577, 217, 15, 15, true, true, 0, Color.RED);
    private MenuSelectorIcon selector = new MenuSelectorIcon(390, 162, 10, 10, 240, Color.WHITE, true, true);
    private String inputMethod;
    
    public BasicOptionPopup(int x, int y, int width, int height, String type, boolean isVisible){
    this.type = type;
    this.inputMethod = type;
    }
    
    public void init(){
        selector.setIsVisible(true);
        selector.setPosition(0);
        selector.setBlinkTime(15);
        
        if(type.equals("playerOptions")){
            selector.setY(162);
        }
       
        if(type.equals("soundOptions")){
            selector.setY(122);
        }
       
        if(type.equals("videoOptions")){
            selector.setY(162);
        }
       
        if(type.equals("gameOptions")){
            selector.setY(162);
        }
    }
    
    @Override
    public void runLogic() {   
        delayedInputLogicManager();  
        selector.runLogic();
        setInputMethod(type);
    }
    
    public String getOptionsType(){
       return type;
    }
    
    public void setOptionsType(String newType){
       type = newType;
       
       selector.setPosition(0);
       if(type.equals("playerOptions")){
            selector.setY(162);
        }
       
        if(type.equals("soundOptions")){
            selector.setY(122);
        }
       
        if(type.equals("videoOptions")){
            selector.setY(162);
        }
       
        if(type.equals("gameOptions")){
            selector.setY(162);
        }
    }
    
    @Override
    public void move() {
   
    }

    @Override
    public void handleInput(String inputMethod, ArrayList<Integer> inputList, String inputMethodRemove, ArrayList<Integer> inputListReleased) {
        selector.inputHandler("default", inputList, inputMethodRemove, inputListReleased);
        
            //give debug the input
            getDebug().inputHandler(this.inputMethod, inputList, inputMethodRemove, inputListReleased);
        for (int key : inputList) {


            //give the screen objects the input
            

            switch (this.inputMethod) {
                
                case "playerOptions":
                    selector.setMaxPosition(4);
                    if(selector.getAcceptingInput()){
                    //########################################################
                    //########################################################
                    //PlayerColor
                    //########################################################
                    //########################################################
                    if(key == KeyEvent.VK_ENTER && selector.getPosition() == 0 && options.getProperty("playerColor").equals("rainbowFade"))
                    {
                        options.setProperty("playerColor", "red");
                        playerExample.init();
                        System.out.println(options.getProperty("playerColor"));
                        delayInput(30);
                    }
                    else if(key == KeyEvent.VK_ENTER && selector.getPosition() == 0 && options.getProperty("playerColor").equals("red"))
                    {
                        options.setProperty("playerColor", "blue");
                        playerExample.init();
                        System.out.println(options.getProperty("playerColor"));
                        delayInput(30);
                    }
                    else if(key == KeyEvent.VK_ENTER && selector.getPosition() == 0 && options.getProperty("playerColor").equals("blue"))
                    {
                        options.setProperty("playerColor", "green");
                        playerExample.init();
                        System.out.println(options.getProperty("playerColor"));
                        delayInput(30);
                    }
                    else if(key == KeyEvent.VK_ENTER && selector.getPosition() == 0 && options.getProperty("playerColor").equals("green"))
                    {
                        options.setProperty("playerColor", "yellow");
                        playerExample.init();
                        System.out.println(options.getProperty("playerColor"));
                        delayInput(30);
                    }
                    else if(key == KeyEvent.VK_ENTER && selector.getPosition() == 0 && options.getProperty("playerColor").equals("yellow"))
                    {
                        options.setProperty("playerColor", "cyan");
                        playerExample.init();
                        System.out.println(options.getProperty("playerColor"));
                        delayInput(30);
                    }
                    else if(key == KeyEvent.VK_ENTER && selector.getPosition() == 0 && options.getProperty("playerColor").equals("cyan"))
                    {
                        options.setProperty("playerColor", "rainbowCycle");
                        playerExample.init();
                        System.out.println(options.getProperty("playerColor"));
                        delayInput(30);
                    }
                    else if(key == KeyEvent.VK_ENTER && selector.getPosition() == 0 && options.getProperty("playerColor").equals("rainbowCycle"))
                    {
                        options.setProperty("playerColor", "rainbowFade");
                        playerExample.init();
                        System.out.println(options.getProperty("playerColor"));
                        delayInput(30);
                    }
                       

                
                    //########################################################
                    //########################################################
                    //PlayerType
                    //########################################################
                    //########################################################
                    if(selector.getPosition() == 1 && options.getProperty("playerType").equals("outlined")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("playerType", "solid");
                            System.out.println(options.getProperty("playerType"));
                            delayInput(30);
                        }
                                                    
                    }         
                    else if(selector.getPosition() == 1 && options.getProperty("playerType").equals("solid")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("playerType", "shifting");
                            System.out.println(options.getProperty("playerType"));
                            delayInput(30);
                        }
                                                    
                    }
                    else if(selector.getPosition() == 1 && options.getProperty("playerType").equals("shifting")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("playerType", "outlined");
                            System.out.println(options.getProperty("playerType"));
                            delayInput(30);
                        }
                                                    
                    }
                    


                    //########################################################
                    //########################################################
                    //Bullet Color
                    //########################################################
                    //########################################################
                    if(selector.getPosition() == 2 && options.getProperty("bulletColor").equals("playerColor")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("bulletColor", "random");
                            bulletExample.init();
                            System.out.println(options.getProperty("bulletColor"));
                            delayInput(30);
                        }
                                                    
                    }
                    else if(selector.getPosition() == 2 && options.getProperty("bulletColor").equals("random")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("bulletColor", "rainbowCycle");
                            bulletExample.callInit();
                            System.out.println(options.getProperty("bulletColor"));
                            delayInput(30);
                        }
                                                    
                    }
                    else if(selector.getPosition() == 2 && options.getProperty("bulletColor").equals("rainbowCycle")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("bulletColor", "red");
                            bulletExample.init();
                            System.out.println(options.getProperty("bulletColor"));
                            delayInput(30);
                        }
                                                    
                    }         
                    else if(selector.getPosition() == 2 && options.getProperty("bulletColor").equals("red")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("bulletColor", "blue");
                            bulletExample.init();
                            System.out.println(options.getProperty("bulletColor"));
                            delayInput(30);
                        }
                                                    
                    }
                    else if(selector.getPosition() == 2 && options.getProperty("bulletColor").equals("blue")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("bulletColor", "yellow");
                            bulletExample.init();
                            System.out.println(options.getProperty("bulletColor"));
                            delayInput(30);
                        }
                                                    
                    }
                    else if(selector.getPosition() == 2 && options.getProperty("bulletColor").equals("yellow")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("bulletColor", "cyan");
                            bulletExample.init();
                            System.out.println(options.getProperty("bulletColor"));
                            delayInput(30);
                        }
                                                    
                    }
                    else if(selector.getPosition() == 2 && options.getProperty("bulletColor").equals("cyan")){
                        if(key == KeyEvent.VK_ENTER){                        
                            options.setProperty("bulletColor", "random");
                            bulletExample.init();
                            System.out.println(options.getProperty("bulletColor"));
                            delayInput(30);
                        }
                                                    
                    }
                    
                    
                    //Set Bullet Color to Player Color
                    if(selector.getPosition() == 4){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("bulletColor", "playerColor");
                            bulletExample.init();
                            System.out.println(options.getProperty("bulletColor"));
                            delayInput(30);
                        }
                                                    
                    }
                    
                    
                    
                    //########################################################
                    //########################################################
                    //Bullet Shape
                    //########################################################
                    //########################################################
                    if(selector.getPosition() == 3 && options.getProperty("bulletShape").equals("diamond")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("bulletShape", "triangle");
                            bulletExample.init();
                            System.out.println(options.getProperty("bulletShape"));
                            delayInput(30);
                        }
                                                    
                    }
                    else if(selector.getPosition() == 3 && options.getProperty("bulletShape").equals("triangle")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("bulletShape", "square");
                            bulletExample.init();
                            System.out.println(options.getProperty("bulletShape"));
                            delayInput(30);
                        }
                                                    
                    }         
                    else if(selector.getPosition() == 3 && options.getProperty("bulletShape").equals("square")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("bulletShape", "circle");
                            bulletExample.init();
                            System.out.println(options.getProperty("bulletShape"));       
                            delayInput(30);
                        }
                                                    
                    }
                    else if(selector.getPosition() == 3 && options.getProperty("bulletShape").equals("circle")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("bulletShape", "hourglass");
                            bulletExample.init();
                            System.out.println(options.getProperty("bulletShape"));       
                            delayInput(30);
                        }
                                                    
                    }
                    else if(selector.getPosition() == 3 && options.getProperty("bulletShape").equals("hourglass")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("bulletShape", "diamond");
                            bulletExample.init();
                            System.out.println(options.getProperty("bulletShape"));       
                            delayInput(30);
                        }
                                                    
                    }
                    
                    }
                    break;
                
                
                
                case "videoOptions":
                    selector.setMaxPosition(2);
                    
                    if(selector.getAcceptingInput()){
                    //##############################################
                    //Stars On Off
                    //##############################################
                    if(selector.getPosition() == 0 && options.getProperty("starsOnOff").equals("on")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("starsOnOff", "off");
                            System.out.println(options.getProperty("starsOnOff"));       
                            delayInput(30);
                        }
                                                    
                    }
                    else if(selector.getPosition() == 0 && options.getProperty("starsOnOff").equals("off")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("starsOnOff", "on");
                            System.out.println(options.getProperty("starsOnOff"));       
                            delayInput(30);
                        }
                                                    
                    }
                    
                    //##############################################
                    //Asteroids On Off
                    //##############################################
                    if(selector.getPosition() == 1 && options.getProperty("asteroidsOnOff").equals("on")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("asteroidsOnOff", "off");
                            System.out.println(options.getProperty("asteroidsOnOff"));       
                            delayInput(30);
                        }
                                                    
                    }
                    else if(selector.getPosition() == 1 && options.getProperty("asteroidsOnOff").equals("off")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("asteroidsOnOff", "on");
                            System.out.println(options.getProperty("asteroidsOnOff"));       
                            delayInput(30);
                        }
                                                    
                    }  
                    
                    //##############################################
                    //Particles On Off
                    //##############################################
                    if(selector.getPosition() == 2 && options.getProperty("particlesOnOff").equals("on")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("particlesOnOff", "off");
                            System.out.println(options.getProperty("particlesOnOff"));       
                            delayInput(30);
                        }
                                                    
                    }
                    else if(selector.getPosition() == 2 && options.getProperty("particlesOnOff").equals("off")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("particlesOnOff", "on");
                            System.out.println(options.getProperty("particlesOnOff"));       
                            delayInput(30);
                        }
                                                    
                    }  
                    }
                    
                    break;
                case "soundOptions":
                    
                    if(selector.getAcceptingInput()){
                    //########################################################
                    //########################################################
                    //Volume Up
                    //########################################################
                    //########################################################
                    if(selector.getPosition() == 0 && options.getProperty("volume").equals("0")){
                        if(key == KeyEvent.VK_ENTER){            
                            options.setProperty("volume", "1");
                            delayInput(30);
          
                        }   
                    }
                    else if(selector.getPosition() == 0 && options.getProperty("volume").equals("1")){
                        if(key == KeyEvent.VK_ENTER){            
                            options.setProperty("volume", "2");
                            delayInput(30);
                        }
                        
                    }
                    else if(selector.getPosition() == 0 && options.getProperty("volume").equals("2")){
                        if(key == KeyEvent.VK_ENTER){            
                            options.setProperty("volume", "3");
                            delayInput(30);
                            
                        }   
                    }
                    else if(selector.getPosition() == 0 && options.getProperty("volume").equals("3")){
                        if(key == KeyEvent.VK_ENTER){            
                            options.setProperty("volume", "4");
                            delayInput(30);
                            
                        }   
                    }
                    else if(selector.getPosition() == 0 && options.getProperty("volume").equals("4")){
                        if(key == KeyEvent.VK_ENTER){            
                            options.setProperty("volume", "5");
                            delayInput(30);
                            
                        }   
                    }                    
                    
                    
                    
                    //########################################################
                    //########################################################
                    //Volume Down
                    //########################################################
                    //########################################################
                    else if(selector.getPosition() == 1 && options.getProperty("volume").equals("5")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("volume", "4");
                            delayInput(30);
                        }
                                                    
                    }         
                    else if(selector.getPosition() == 1 && options.getProperty("volume").equals("4")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("volume", "3");
                            delayInput(30);
                        }
                                                    
                    }
                    else if(selector.getPosition() == 1 && options.getProperty("volume").equals("3")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("volume", "2");
                            delayInput(30);
                        }
                                                    
                    }
                    else if(selector.getPosition() == 1 && options.getProperty("volume").equals("2")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("volume", "1");
                            delayInput(30);
                        }
                                                    
                    }         
                    else if(selector.getPosition() == 2){
                       
                            if(key == KeyEvent.VK_ENTER){
                            options.setProperty("volume", "0");
                            delayInput(30);
                            }        
                    }
                    
                    }
                    
                    break;
                case "gameOptions":
                    selector.setMaxPosition(2);
                    
                    if(selector.getAcceptingInput()){
                        
                    //########################################################
                    //########################################################
                    //Difficulty Up
                    //########################################################
                    if(selector.getPosition() == 0 && options.getProperty("difficulty").equals("Easy")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("difficulty", "Medium");
                            delayInput(30);
                        }
                                                    
                    }         
                    else if(selector.getPosition() == 0 && options.getProperty("difficulty").equals("Medium")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("difficulty", "Hard");
                            delayInput(30);
                        }
                                                    
                    }                            
                    else if(selector.getPosition() == 0 && options.getProperty("difficulty").equals("Hard")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("difficulty", "Insane");
                            delayInput(30);
                        }
                    
                    }
                    
                    //########################################################
                    //########################################################
                    //Difficulty Down
                    //########################################################
                    if(selector.getPosition() == 1 && options.getProperty("difficulty").equals("Insane")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("difficulty", "Hard");
                            delayInput(30);
                        }
                                                    
                    }         
                    else if(selector.getPosition() == 1 && options.getProperty("difficulty").equals("Hard")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("difficulty", "Medium");
                            delayInput(30);
                        }
                                                    
                    }
                    else if(selector.getPosition() == 1 && options.getProperty("difficulty").equals("Medium")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("difficulty", "Easy");
                            delayInput(30);
                        }
                    
                    }
                    
                    }
                    break;    
    }
        }
    }    
    
    public void setInputMethod(String type){
        inputMethod = type;
    }
    
    @Override
    public void drawObject(Graphics2D g) {
      if(getIsVisible() == false){
          selector.setAcceptingInput(false);
      }
      else{
          selector.setAcceptingInput(true);
      }  
        
        if(getIsVisible()){    
            if(type.equals("playerOptions")){
                drawCenteredString(g, "Player Color", 480, 160, normalFont);
                playerExample.drawObject(g);
                drawCenteredString(g, "Player Type", 480, 180, normalFont);
                drawCenteredString(g, "Bullet Color", 480, 200, normalFont);
                drawCenteredString(g, "Bullet Color", 480, 200, normalFont);
                drawCenteredString(g, "Bullet Shape", 480, 220, normalFont);
                bulletExample.drawObject(g);
                drawCenteredString(g, "Bullet Color = Player Color", 480, 240, normalFont);
                if(selector.getPosition() == 4){
                    selector.setX(300);
            }
            if(selector.getPosition() != 4){
                selector.setX(390);
            }
            selector.drawObject(g);
        }
        
        if(type.equals("soundOptions")){
            selector.setMaxPosition(2);
            selector.setX(390);
            drawCenteredString(g, "Volume Up", 480, BrickBreakerMain.SCREENHEIGHT/2 - 180, normalFont);
            drawCenteredString(g, "Volume Down", 480, BrickBreakerMain.SCREENHEIGHT/2 - 160, normalFont);
            drawCenteredString(g, "Mute", 480, BrickBreakerMain.SCREENHEIGHT/2 - 140, normalFont);
            if(options.getProperty("volume") == "0"){
                System.out.println(options.getProperty("volume"));
            }
            else if(options.getProperty("volume") == "1"){
                System.out.println(options.getProperty("volume"));
            }
            else if(options.getProperty("volume") == "2"){
                System.out.println(options.getProperty("volume"));
            }
            else if(options.getProperty("volume") == "3"){
                System.out.println(options.getProperty("volume"));
            }
            else if(options.getProperty("volume") == "4"){
             
                System.out.println(options.getProperty("volume"));
            }
            else if(options.getProperty("volume") == "5"){
                System.out.println(options.getProperty("volume"));
            }

            g.fillRect(455, BrickBreakerMain.SCREENHEIGHT/2 + 135 - (Integer.parseInt(options.getProperty("volume"))-1)*60, 50, (Integer.parseInt(options.getProperty("volume")))*60);
            drawCenteredString(g, options.getProperty("volume"), 480, BrickBreakerMain.SCREENHEIGHT/2 + 205, titleFont);
            selector.drawObject(g);
        }
            
        if(type.equals("gameOptions")){
            selector.setX(370);
            drawCenteredString(g, "Difficulty Up", 480, 160, normalFont);           
            drawCenteredString(g, "Difficulty Down", 480, 180, normalFont);
            drawCenteredString(g, "Game", 480, 200, normalFont);
            
            if(options.getProperty("difficulty") == "easy"){
                System.out.println(options.getProperty("difficulty"));
            }
            else if(options.getProperty("difficulty") == "medium"){
                System.out.println(options.getProperty("difficulty"));
            }
            else if(options.getProperty("difficulty") == "hard"){
                System.out.println(options.getProperty("difficulty"));
            }
            drawCenteredString(g, "Difficulty:" + options.getProperty("difficulty"), 480, 250, normalFont);
            selector.drawObject(g);
        }
                
        if(type.equals("videoOptions")){
            selector.setX(390);
            if(options.getProperty("starsOnOff").equals("on"))
                drawCenteredString(g, "ON", 530, 160, normalFont); 
            if(options.getProperty("starsOnOff").equals("off"))
                drawCenteredString(g, "OFF", 530, 160, normalFont); 
            drawCenteredString(g, "Stars: ", 480, 160, normalFont);
            
            if(options.getProperty("asteroidsOnOff").equals("on"))
                drawCenteredString(g, "ON", 555, 180, normalFont); 
            if(options.getProperty("asteroidsOnOff").equals("off"))
                drawCenteredString(g, "OFF", 555, 180, normalFont); 
            drawCenteredString(g, "Asteroids: ", 480, 180, normalFont);
            
            if(options.getProperty("particlesOnOff").equals("on"))
                drawCenteredString(g, "ON", 555, 200, normalFont); 
            if(options.getProperty("particlesOnOff").equals("off"))
                drawCenteredString(g, "OFF", 555, 200, normalFont); 
            drawCenteredString(g, "Particles: ", 480, 200, normalFont);
            selector.drawObject(g);
        }
        
        
    }
    }

    
}