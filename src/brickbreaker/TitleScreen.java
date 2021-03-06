/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker;

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
public class TitleScreen extends AbstractScreen{
    
    long timeAtScreen;
    private String[] options;
    private Color backroundColor;
    
    
    //constructors
    //------------------------------------------------------------------
    public TitleScreen(){
        super();
        
        //sets backround color
        backroundColor = Color.BLACK;
        
        //sets params for input handling
        setInputMethod("default");
        
        setNextScreen(' ');
                
        //sets options for the menu
        options = new String[5];
        options[0] = "Play Game";
        options[1] = "HighScores";
        options[2] = "Instructions";
        options[3] = "Options";
        options[4] = "Quit";
        
        //adds screen objects
        AbstractScreenObject tempOb;
        tempOb = new MenuSelectorIcon(320, getHeight() / 2, 10, 10, 4, Color.WHITE, true, true);
        tempOb.setSpeed(0);
        addToObjectsArray(SCREENOBJLAYER, tempOb);
        
    }
    
    //handles all game logic
    //------------------------------------------------------------------
    @Override
    public void runLogic() {
        timeAtScreen = System.nanoTime();
        
        //sets input list
        delayInputManager();
        handleInput(getInputList());
        
        //updates mouse pos
        getDebug().setMouseX(getMouseX());
        getDebug().setMouseY(getMouseY());
        
        //runs all logic for screen objects
        runScreenObjectLogic();
        
        //moves all screen objects
        moveScreenObjects();
        
    }

    //handles all screen specific drawing then parses through every AbstractScreenObject to draw them
    //------------------------------------------------------------------
    
    @Override
    public void drawGame(Graphics2D g) {
        setBackground(backroundColor);
        
        if(getDebug().getIsVisible())
            getDebug().drawObject(g);
        
        
        drawTitleScreenText(g);
        
        
        //draws all Screen Objects
        drawScreenObjects(g);
        
    }
    
    public void drawTitleScreenText(Graphics2D g){
        Font currentFont = g.getFont();
        Font normalFont = new Font(Font.MONOSPACED, Font.BOLD, 16);
        Font titleFont = new Font(Font.MONOSPACED, Font.BOLD, 32);
        
        g.setColor(Color.WHITE);
        
        drawCenteredString(g, "BRICK BREAKER", getWidth() / 2, 50, titleFont);
        
        for(int x = 0; x < options.length; x++){
            drawCenteredString(g, options[x], getWidth() / 2, getHeight() / 2 + x*20, normalFont);
        }
        
    }

    //user input handling
    //------------------------------------------------------------------
    
    @Override
    public void specificInput(ArrayList<Integer> inputList, ArrayList<Integer> inputListReleased){
        
        //copies input list to new list so it is not changed as it parses the input
            
            //gives input to debug
            getDebug().inputHandler(getInputMethod(), inputList, getInputMethodRemove(), inputListReleased);
            
            //gives input to screen objects
            for (AbstractScreenObject ob : getObjectsList().get(SCREENOBJLAYER)) {
                ob.inputHandler(getInputMethod(), inputList, getInputMethodRemove(), inputListReleased);
            }

            for(int key : inputList){
            //handles input for the screen depending on input method
            switch (getInputMethod()) {

                //handles all default input for the screen
                case "default":
                    switch (key) {

                        case KeyEvent.VK_ENTER:
                            for (AbstractScreenObject ob : getObjectsList().get(SCREENOBJLAYER)) {
                                if (ob.getIdNum() == 1) {
                                    switch (ob.getPosition()) {
                                        case 0:
                                            setNextScreen('P');
                                            break;
                                        case 1:
                                            setNextScreen('H');
                                            break;
                                        case 2:
                                            setNextScreen('I');
                                            break;
                                        case 3:
                                            setNextScreen('O');
                                            break;
                                        case 4:
                                            setNextScreen('Q');
                                            break;
                                    }
                                }
                            }
                        
                    }

                    break;

                //handles all input while a menu is open
                case "menu":

                    break;

            }
            }
    }
    
    //this method handles almost everything
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    //Mouse Input methods
    //------------------------------------------------------------------
    
    
    @Override
    public void mouseMoved(MouseEvent me) {
        setMouseX(me.getX());
        setMouseY(me.getY());
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }
}
