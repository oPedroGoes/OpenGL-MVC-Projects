package pguel.computacao_grafica.common.controller;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;

public class KeyboardController extends KeyAdapter {

    Map<Integer, Runnable> actions;
    JPanel panel;

    public KeyboardController(Map<Integer, Runnable> actions, JPanel panel){
        this.actions = actions;
        this.panel = panel;
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
