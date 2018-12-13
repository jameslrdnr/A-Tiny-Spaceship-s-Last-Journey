/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker;

import static brickbreaker.BrickBreakerMain.options;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import screenObjects.*;
/**
 *
 * @author JamesLaptop
 */
public class OptionScreen extends AbstractScreen {
    
    private final Font normalFont = new Font(Font.MONOSPACED, Font.BOLD, 20);
    private final Font titleFont = new Font(Font.MONOSPACED, Font.BOLD, 36);
    private final MenuSelectorIcon optionSelector = new MenuSelectorIcon(getWidth()/4 - 190, getHeight()/2 - 179, 10, 10, getHeight() - 80, Color.WHITE, true, true);
    private final BasicOptionPopup optionPopup = new BasicOptionPopup(getWidth()/4, 90, 500, 500, "playerOptions", false);
    
    
    public OptionScreen(){
        super();
        
        init();
        
    }
    
    //default variables
    public void init(){
        
        setInputMethod("default");
        getObjectsList().get(SCREENOBJLAYER).add(optionSelector);
        getObjectsList().get(SCREENOBJLAYER).add(optionPopup);
        optionSelector.setMaxPosition(3);
        optionSelector.setBlinkTime(15);       
    }

    @Override
    void runLogic() {
        
        delayInputManager();
        
        handleInput(getInputList());
        //debug info
        getDebug().setMouseX(getMouseX());
        getDebug().setMouseY(getMouseY());
        delayInputManager();
        runScreenObjectLogic();
 
        moveScreenObjects();
        
        

    }

    @Override
    void drawGame(Graphics2D g) {
        setBackground(Color.BLACK);
        g.setColor(Color.WHITE);
        //adds debug for graphics
        if(getDebug().getIsVisible())
            getDebug().drawObject(g);
        
        g.drawLine(0, 80, getWidth(), 80);       
        g.drawLine(getWidth()/4 - 60, 0, getWidth()/4 - 60, getHeight());
        
        if(optionSelector.getAcceptingInput() == true){
            drawCenteredString(g, "Press ESC", 70, 20, normalFont);
            drawCenteredString(g, "To Go Back", 70, 40, normalFont);
        }
        drawCenteredString(g, "Options", getWidth()/2 + 80, 30, titleFont);
        drawCenteredString(g, "Audio", getWidth()/4 - 140, getHeight()/2 - 140, normalFont);
        drawCenteredString(g, "Video", getWidth()/4 - 140, getHeight()/2 - 160, normalFont);
        drawCenteredString(g, "Player", getWidth()/4 - 140, getHeight()/2 - 180, normalFont);
        drawCenteredString(g, "Game", getWidth()/4 - 140, getHeight()/2 - 120, normalFont);
        
 
        drawScreenObjects(g);

    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        setMouseX(me.getX());
        setMouseY(me.getY());
    }
    
    @Override
    public void specificInput(ArrayList<Integer> inputList, ArrayList<Integer> inputListReleased) {
        optionPopup.handleInput(getInputMethod(), inputList, getInputMethodRemove(), inputListReleased);
        for (int key : inputList) {

            //give debug the input
            getDebug().inputHandler(getInputMethod(), inputList, getInputMethodRemove(), inputListReleased);

            //give the screen objects the input
            for (ArrayList<AbstractScreenObject> list : getObjectsList()) {
                for (AbstractScreenObject ob : list) {
                    ob.inputHandler(getInputMethod(), inputList, getInputMethodRemove(), inputListReleased);
                }
            }

            switch (getInputMethod()) {

                //default case
                case "default":

                    //cycle through all input
                    
                    if(key == KeyEvent.VK_ESCAPE){
                        if(optionPopup.getIsVisible() == true){
                            optionPopup.setIsVisible(false);
                            optionSelector.setAcceptingInput(true);
                            optionSelector.setBlinking(true);                          
                            delayInput(30);
                            optionPopup.init();
                        }
                        else{
                        setNextScreen('T');
                        delayInput(30);
                        }
                    }
                    
                    if(key == KeyEvent.VK_DOWN){
                        if(optionSelector.getPosition() < optionSelector.getMaxPosition() && optionSelector.getAcceptingInput()){
                            optionSelector.moveY(+20);
                            optionSelector.setPosition(optionSelector.getPosition() + 1);
                            delayInput(30);
                        }
                    }
                    
                    if(key == KeyEvent.VK_UP){
                        if(optionSelector.getPosition() > 0 && optionSelector.getAcceptingInput()){
                            optionSelector.moveY(-20);
                            optionSelector.setPosition(optionSelector.getPosition() - 1);
                            delayInput(30);
                            //optionPopup.init();
                        }                        
                    }
                    
                    if(key == KeyEvent.VK_ENTER && optionSelector.getPosition() == 0){
                        if(optionPopup.getOptionsType().equals("playerOptions") == false){
                        optionPopup.setOptionsType("playerOptions");
                        }
                        delayInput(30);
                        optionPopup.setIsVisible(true);
                        optionSelector.setAcceptingInput(false);
                        optionSelector.setBlinking(false);
                        optionSelector.setIsVisible(true);
                    }
                    if(key == KeyEvent.VK_ENTER && optionSelector.getPosition() == 1){
                        if(optionPopup.getOptionsType().equals("videoOptions") == false){
                        optionPopup.setOptionsType("videoOptions");
                        }
                        delayInput(30);
                        optionPopup.setIsVisible(true);
                        optionSelector.setAcceptingInput(false);
                        optionSelector.setBlinking(false);
                        optionSelector.setIsVisible(true);
                    }
                    if(key == KeyEvent.VK_ENTER && optionSelector.getPosition() == 2){
                        if(optionPopup.getOptionsType().equals("soundOptions") == false){
                        optionPopup.setOptionsType("soundOptions");
                        }
                        delayInput(30);
                        optionPopup.setIsVisible(true);
                        optionSelector.setAcceptingInput(false);
                        optionSelector.setBlinking(false);
                        optionSelector.setIsVisible(true);
                        
                    }
                    if(key == KeyEvent.VK_ENTER && optionSelector.getPosition() == 3){
                        if(optionPopup.getOptionsType().equals("gameOptions") == false){
                        optionPopup.setOptionsType("gameOptions");
                        }
                        delayInput(30);
                        optionPopup.setIsVisible(true);
                        optionSelector.setAcceptingInput(false);
                        optionSelector.setBlinking(false);
                        optionSelector.setIsVisible(true);
                    }
                    
                    
                    
                    break;
                
                case "SecondScreen":
                    
                    //#####################################################
                    //Selector Movement
                    //#####################################################  
                    /*if(key == KeyEvent.VK_DOWN){
                        if(optionSelector2.getPosition() < optionSelector2.getMaxPosition()){
                            optionSelector2.moveY(+20);
                            optionSelector2.setPosition(optionSelector2.getPosition() + 1);  
                            delayInput(30);
                        }
                    }
                    
                    if(key == KeyEvent.VK_UP){
                        if(optionSelector2.getPosition() > 0){
                            optionSelector2.moveY(-20);
                            optionSelector2.setPosition(optionSelector2.getPosition() - 1);                         
                            delayInput(30);
                        }
                    }
                    
                    
                    if(optionSelector.getPosition() == 0)
                    {
                        optionSelector2.setMaxPosition(4);
                    }
                    //#####################################################
                    //Back
                    //#####################################################
                    if(key == KeyEvent.VK_ESCAPE){
                        optionSelector2.setBlinking(false);
                        optionSelector2.setAcceptingInput(false);
                        optionSelector2.setIsVisible(false);
                        optionSelector.setAcceptingInput(true);
                        optionSelector.setBlinking(true);
                        delayInput(30);
                        setInputMethod("default");
                    }          
                                  
                    //#####################################################
                    //###################################
                    //Audio Settings
                    //###################################
                    //#####################################################
                    
                    
                    //###################################
                    //Volume Up
                    //###################################
                    if(optionSelector.getPosition() == 2 && optionSelector2.getPosition() == 0 && options.getProperty("volume").equals("0")){
                        if(key == KeyEvent.VK_ENTER){            
                            options.setProperty("volume", "1");
                            delayInput(30);
          
                        }   
                    }
                    else if(optionSelector.getPosition() == 2 && optionSelector2.getPosition() == 0 && options.getProperty("volume").equals("1")){
                        if(key == KeyEvent.VK_ENTER){            
                            options.setProperty("volume", "2");
                            delayInput(30);
                        }
                        
                    }
                    else if(optionSelector.getPosition() == 2 && optionSelector2.getPosition() == 0 && options.getProperty("volume").equals("2")){
                        if(key == KeyEvent.VK_ENTER){            
                            options.setProperty("volume", "3");
                            delayInput(30);
                            
                        }   
                    }
                    else if(optionSelector.getPosition() == 2 && optionSelector2.getPosition() == 0 && options.getProperty("volume").equals("3")){
                        if(key == KeyEvent.VK_ENTER){            
                            options.setProperty("volume", "4");
                            delayInput(30);
                            
                        }   
                    }
                    else if(optionSelector.getPosition() == 2 && optionSelector2.getPosition() == 0 && options.getProperty("volume").equals("4")){
                        if(key == KeyEvent.VK_ENTER){            
                            options.setProperty("volume", "5");
                            delayInput(30);
                            
                        }   
                    }                    
                    
                    
                    
                    //###################################
                    //Volume Down
                    //###################################
                    else if(optionSelector.getPosition() == 2 && optionSelector2.getPosition() == 1 && options.getProperty("volume").equals("5")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("volume", "4");
                            delayInput(30);
                        }
                                                    
                    }         
                    else if(optionSelector.getPosition() == 2 && optionSelector2.getPosition() == 1 && options.getProperty("volume").equals("4")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("volume", "3");
                            delayInput(30);
                        }
                                                    
                    }
                    else if(optionSelector.getPosition() == 2 && optionSelector2.getPosition() == 1 && options.getProperty("volume").equals("3")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("volume", "2");
                            delayInput(30);
                        }
                                                    
                    }
                    else if(optionSelector.getPosition() == 2 && optionSelector2.getPosition() == 1 && options.getProperty("volume").equals("2")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("volume", "1");
                            delayInput(30);
                        }
                                                    
                    }         
                    else if(optionSelector.getPosition() == 2){
                        if(optionSelector2.getPosition() == 2){
                            if(key == KeyEvent.VK_ENTER){
                            //############################################
                            //Mute the volume
                            //############################################
                            options.setProperty("volume", "0");
                            delayInput(30);
                            }
                                
                        }
                            
                            
                    }
                    
                    //#####################################################
                    //###################################
                    //Video Settings
                    //###################################
                    //#####################################################
                    if(optionSelector.getPosition() == 1){
                        if(optionSelector2.getPosition() == 0){
                            if(key == KeyEvent.VK_ENTER){
                            //############################################
                            //Video option 1
                            //############################################
                            }
                                
                        }
                                                        
                    }
                    if(optionSelector.getPosition() == 1){
                        if(optionSelector2.getPosition() == 1){
                            if(key == KeyEvent.VK_ENTER){
                            //############################################
                            //Video option 2
                            //############################################
                            }
                                
                        }                          
                            
                    }
                    if(optionSelector.getPosition() == 1){
                        if(optionSelector2.getPosition() == 2){
                            if(key == KeyEvent.VK_ENTER){
                            //############################################
                            //Video option 3
                            //############################################
                            }
                                
                        }
                                                       
                    }
                    //#####################################################
                    //###################################
                    //Player Settings
                    //###################################
                    //#####################################################
                    if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 0 && options.getProperty("playerColor").equals("rainbowFade")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("playerColor", "red");
                            System.out.println(options.getProperty("playerColor"));
                            delayInput(30);
                        }
                                                    
                    }         
                    else if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 0 && options.getProperty("playerColor").equals("red")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("playerColor", "blue");
                            System.out.println(options.getProperty("playerColor"));
                            delayInput(30);
                        }
                                                    
                    }
                    else if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 0 && options.getProperty("playerColor").equals("blue")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("playerColor", "green");
                            System.out.println(options.getProperty("playerColor"));
                            delayInput(30);
                        }
                                                    
                    }
                    else if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 0 && options.getProperty("playerColor").equals("green")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("playerColor", "yellow");
                            System.out.println(options.getProperty("playerColor"));
                            delayInput(30);
                        }
                                                    
                    }
                    else if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 0 && options.getProperty("playerColor").equals("yellow")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("playerColor", "cyan");
                            System.out.println(options.getProperty("playerColor"));
                            delayInput(30);
                        }
                                                    
                    }
                    else if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 0 && options.getProperty("playerColor").equals("cyan")){
                        if(key == KeyEvent.VK_ENTER){                        
                            options.setProperty("playerColor", "rainbowRandom");
                            System.out.println(options.getProperty("playerColor"));
                            delayInput(30);
                        }
                                                    
                    }  
                    else if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 0 && options.getProperty("playerColor").equals("rainbowRandom")){
                        if(key == KeyEvent.VK_ENTER){                        
                            options.setProperty("playerColor", "rainbowCycle");
                            System.out.println(options.getProperty("playerColor"));
                            delayInput(30);
                        }
                                                    
                    }
                    else if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 0 && options.getProperty("playerColor").equals("rainbowCycle")){
                        if(key == KeyEvent.VK_ENTER){                        
                            options.setProperty("playerColor", "rainbowFade");
                           System.out.println(options.getProperty("playerColor"));
                           delayInput(30);
                        }
                                                    
                    }
                        
    
                    //############################################
                    //Player Type
                    //############################################
                    if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 1 && options.getProperty("playerType").equals("outlined")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("playerType", "solid");
                            System.out.println(options.getProperty("playerType"));
                            delayInput(30);
                        }
                                                    
                    }         
                    else if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 1 && options.getProperty("playerType").equals("solid")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("playerType", "shifting");
                            System.out.println(options.getProperty("playerType"));
                            delayInput(30);
                        }
                                                    
                    }
                    else if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 1 && options.getProperty("playerType").equals("shifting")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("playerType", "outlined");
                            System.out.println(options.getProperty("playerType"));
                            delayInput(30);
                        }
                                                    
                    }
                    
                    
                    //############################################
                    //Bullet Color
                    //############################################
                    if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 2 && options.getProperty("bulletColor").equals("playerColor")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("bulletColor", "rainbowRandom");
                            System.out.println(options.getProperty("bulletColor"));
                            delayInput(30);
                        }
                                                    
                    }
                    if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 2 && options.getProperty("bulletColor").equals("rainbowRandom")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("bulletColor", "rainbowCycle");
                            System.out.println(options.getProperty("bulletColor"));
                            delayInput(30);
                        }
                                                    
                    }
                    else if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 2 && options.getProperty("bulletColor").equals("rainbowCycle")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("bulletColor", "red");
                            System.out.println(options.getProperty("bulletColor"));
                            delayInput(30);
                        }
                                                    
                    }         
                    else if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 2 && options.getProperty("bulletColor").equals("red")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("bulletColor", "blue");
                            System.out.println(options.getProperty("bulletColor"));
                            delayInput(30);
                        }
                                                    
                    }
                    else if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 2 && options.getProperty("bulletColor").equals("blue")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("bulletColor", "yellow");
                            System.out.println(options.getProperty("bulletColor"));
                            delayInput(30);
                        }
                                                    
                    }
                    else if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 2 && options.getProperty("bulletColor").equals("yellow")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("bulletColor", "cyan");
                            System.out.println(options.getProperty("bulletColor"));
                            delayInput(30);
                        }
                                                    
                    }
                    else if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 2
                            && options.getProperty("bulletColor").equals("cyan")){
                        if(key == KeyEvent.VK_ENTER){                        
                            options.setProperty("bulletColor", "rainbowRandom");
                            delayInput(30);
                        }
                                                    
                    }
                    
                    
                    //Set Bullet Color to Player Color
                    if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 4){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("bulletColor", "playerColor");
                            System.out.println(options.getProperty("bulletColor"));
                            delayInput(30);
                        }
                                                    
                    }
                    
                    
                    
                    //############################################
                    //Player Type
                    //############################################
                    if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 1 && options.getProperty("playerType").equals("outlined")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("playerType", "solid");
                            delayInput(30);
                        }
                                                    
                    }         
                    else if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 1 && options.getProperty("playerType").equals("solid")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("playerType", "shifting");
                            delayInput(30);
                        }
                                                    
                    }
                    else if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 1 && options.getProperty("playerType").equals("shifting")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("playerType", "outlined");
                            delayInput(30);
                        }
                                                    
                    }
                    
                    
                    //############################################
                    //Bullet Color
                    //############################################
                    if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 2 && options.getProperty("bulletColor").equals("rainbowRandom")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("bulletColor", "rainbowCycle");
                            delayInput(30);
                        }
                                                    
                    }
                    else if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 2 && options.getProperty("bulletColor").equals("rainbowCycle")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("bulletColor", "red");
                            delayInput(30);
                        }
                                                    
                    }         
                    else if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 2 && options.getProperty("bulletColor").equals("red")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("bulletColor", "blue");
                            delayInput(30);
                        }
                                                    
                    }
                    else if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 2 && options.getProperty("bulletColor").equals("blue")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("bulletColor", "yellow");
                            delayInput(30);
                        }
                                                    
                    }
                    else if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 2 && options.getProperty("bulletColor").equals("yellow")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("bulletColor", "cyan");
                            delayInput(30);
                        }
                                                    
                    }
                    else if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 2
                            && options.getProperty("bulletColor").equals("cyan")){
                        if(key == KeyEvent.VK_ENTER){                        
                            options.setProperty("bulletColor", "rainbowRandom");
                            delayInput(30);
                        }
                                                    
                    }
                    
                    
                    //############################################
                    //Bullet Shape
                    //############################################
                    if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 3 && options.getProperty("bulletShape").equals("diamond")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("bulletShape", "circle");
                            System.out.println(options.getProperty("bulletShape"));
                            delayInput(30);
                        }
                                                    
                    }
                    else if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 3 && options.getProperty("bulletShape").equals("circle")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("bulletShape", "square");
                            System.out.println(options.getProperty("bulletShape"));
                            delayInput(30);
                        }
                                                    
                    }         
                    else if(optionSelector.getPosition() == 0 && optionSelector2.getPosition() == 3 && options.getProperty("bulletShape").equals("square")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("bulletShape", "diamond");
                            System.out.println(options.getProperty("bulletShape"));       
                            delayInput(30);
                        }
                                                    
                    }  
                    
                    
                    //Difficulty Up
                    if(optionSelector.getPosition() == 3 && optionSelector2.getPosition() == 0 && options.getProperty("difficulty").equals("easy")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("difficulty", "medium");
                            delayInput(30);
                        }
                                                    
                    }         
                    else if(optionSelector.getPosition() == 3 && optionSelector2.getPosition() == 0 && options.getProperty("difficulty").equals("medium")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("difficulty", "hard");
                            delayInput(30);
                        }
                                                    
                    }                            
                    
                    //Difficulty Down
                    if(optionSelector.getPosition() == 3 && optionSelector2.getPosition() == 1 && options.getProperty("difficulty").equals("hard")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("difficulty", "medium");
                            delayInput(30);
                        }
                                                    
                    }         
                    else if(optionSelector.getPosition() == 3 && optionSelector2.getPosition() == 1 && options.getProperty("difficulty").equals("medium")){
                        if(key == KeyEvent.VK_ENTER){                         
                            options.setProperty("difficulty", "easy");
                            delayInput(30);
                        }
                                                    
                    }
                   
                    
                    
                    */
                    break;
                        
            }
        }

    }
   
}