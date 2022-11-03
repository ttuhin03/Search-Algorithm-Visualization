package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetButton extends JButton {

    private gui_test gui;

    public ResetButton(gui_test progam){
        super("Reset");

        gui = progam;

        addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e1){
                gui.setStartpointButton = false;
                gui.setEndpointButton = false;
                gui.setWallButton = false;
                gui.setDeleteButton = false;

                //Setzt alle Felder auf ihren Startzustand (Leeres Feld) zurück
                gui.resetAllFelder();

                gui.endPointXPos = -42;
                gui.endPointYPos = -42;
                gui.startPointXPos = -42;
                gui.startPointYPos = -42;
                gui.globalIsEndpointSet = false;
                gui.globalIsStartpointSet = false;

            } } );
    }
}
