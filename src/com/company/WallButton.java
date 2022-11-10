package com.company;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WallButton extends JButton {
    private gui_test gui;

    public WallButton(gui_test program){
        super("Walls");
        gui = program;

        this.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e3){
                gui.setStartpointButton = false;
                gui.setEndpointButton = false;
                gui.setWallButton = true;
                gui.setDeleteButton = false;
            } } );

    }

}
