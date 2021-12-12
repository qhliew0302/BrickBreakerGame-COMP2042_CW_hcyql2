package brickdestroy.controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import brickdestroy.model.*;
import brickdestroy.view.*;

/**
 * A debugConsole controller class that controls debugConsole Class.
 */
public class DebugConsoleController implements WindowListener {
    private DebugConsole debugConsole;

    public DebugConsoleController(DebugConsole debugConsole){
        this.debugConsole = debugConsole;

    }
    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        debugConsole.getGameBoard().repaint();
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {
        debugConsole.setLocation();
        Ball b = debugConsole.getWall().getBall();
        debugConsole.getDebugPanel().setValues(b.getSpeedX(),b.getSpeedY());
    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
