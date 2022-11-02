package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RunButton extends JButton{

    private gui_test gui;

    public RunButton(gui_test progam){

        gui = progam;

        this.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e5){

                if(gui.checkRequirements()) {

                    gui.setStartpointButton = false;
                    gui.setEndpointButton = false;
                    gui.setWallButton = false;
                    gui.setDeleteButton = false;

                    gui.startButton.setEnabled(false);
                    gui.endButton.setEnabled(false);
                    gui.wallButton.setEnabled(false);
                    gui.deleteButton.setEnabled(false);
                    gui.runButton.setEnabled(false);

                    //TODO:Algorithmus starten + auswahl des algorithmus überprüfen
                    //breitenSuche();
                    gui.ttest = true;
                    gui.startButton.setEnabled(true);
                    gui.endButton.setEnabled(true);
                    gui.wallButton.setEnabled(true);
                    gui.deleteButton.setEnabled(true);
                    gui.runButton.setEnabled(true);

                }else{
                    System.out.println("Voraussetzungen nicht erfüllt");
                }

            } } );

    }


}
