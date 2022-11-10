package com.company;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPointButton extends JButton {

    private gui_test gui;

    public StartPointButton(gui_test program){
        super("Startpoint");
        gui = program;

        this.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e1){
                gui.setStartpointButton = true;
                gui.setEndpointButton = false;
                gui.setWallButton = false;
                gui.setDeleteButton = false;
            } } );

    }


}
