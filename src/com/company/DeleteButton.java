package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteButton extends JButton {

    private gui_test gui;

    public DeleteButton(gui_test program){
        super("Delete");
        gui = program;

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(Color.GREEN);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(Color.BLUE);
            }
        });

        this.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e4){
                gui.setStartpointButton = false;
                gui.setEndpointButton = false;
                gui.setWallButton = false;
                gui.setDeleteButton = true;
            } } );
    }




}
