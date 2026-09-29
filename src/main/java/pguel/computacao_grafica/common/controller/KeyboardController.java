package pguel.computacao_grafica.common.controller;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;

public class KeyboardController extends KeyAdapter {

    Map<Integer, Runnable> actions;
    Map<Character, Runnable> charActions;
    JPanel panel;

    public KeyboardController(Map<Integer, Runnable> actions, JPanel panel){
        this(actions, Map.of(), panel);
    }

    public KeyboardController(Map<Integer, Runnable> actions, Map<Character, Runnable> charActions, JPanel panel){
        this.actions = actions;
        this.charActions = charActions;
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent event) {
        Runnable action = charActions.get(event.getKeyChar());
        if (action != null){
            action.run();
            panel.repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {
        Runnable action = actions.get(event.getKeyCode());
        if (action != null){
            action.run();
            panel.repaint();
        }
    }
}
