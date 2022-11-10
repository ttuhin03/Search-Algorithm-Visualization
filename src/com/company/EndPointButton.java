package com.company;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndPointButton extends JButton {

    private gui_test gui;
    public EndPointButton(gui_test program){
        super("Endpoint");
        gui = program;

        this.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e2){
                gui.setStartpointButton = false;
                gui.setEndpointButton = true;
                gui.setWallButton = false;
                gui.setDeleteButton = false;
            } } );

    }


}
