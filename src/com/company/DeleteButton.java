package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteButton extends JButton {

    private gui_test gui;

    public DeleteButton(gui_test program){
        super("Delete");
        gui = program;

        this.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e4){
                gui.setStartpointButton = false;
                gui.setEndpointButton = false;
                gui.setWallButton = false;
                gui.setDeleteButton = true;
                //TODO: aufruf globaVaroable updater damit evt.  fehler vermieden werden
            } } );
    }




}
