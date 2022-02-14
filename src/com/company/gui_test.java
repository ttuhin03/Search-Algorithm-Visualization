package com.company;

import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class gui_test implements ActionListener {
    JButton button;
    private Felder[][] feld = new Felder[30][20];
    public static boolean globalIsEndpointSet, globalIsStartpointSet;
    public static int endPointXPos, endPointYPos, startPointXPos, startPointYPos;
    public static boolean setWallButton, setStartpointButton, setEndpointButton, setDeleteButton;

    public static void main (String[] args) {
        gui_test gui = new gui_test();

            globalIsEndpointSet = false;
            globalIsStartpointSet = false;
            setEndpointButton = false;
            setStartpointButton = false;
            setWallButton = false;
            setDeleteButton = false;
            endPointXPos = -42;
            endPointYPos = -42;
            startPointXPos = -42;
            startPointYPos = -42;

        gui.press();
    }

    public JPanel addBoxes(JPanel panel){

        panel.setLayout(null);

        int zaehler = 0;
        for(int i = 1;i<30;i++) {

            for(int j = 1 ; j<20;j++) {

                if((zaehler+j)%2==0){
                    feld[i][j] = new Felder();
                    panel = feld[i][j].addFeld(panel,30 * i,30 * j+60,"grau");
                }else {
                    feld[i][j] = new Felder();
                    panel = feld[i][j].addFeld(panel,30 * i,30 * j+60,"weiß");
                }
            }
            zaehler++;
        }

        return panel;
    }

    public void updateGlobalVar(){
        //updatet globale variablen, nachdem ein block zurückgesetzt wurde
    }

    public boolean checkRequirements(){
        //überprüft, ob auch nur genau ein startpunkt, ein endpunkt, und eine genaue Auswahl getroffen wurde
        return false;
    }



    public void press()   {

        JPanel panel = new JPanel();

        panel = addBoxes(panel);


        JFrame frame = new JFrame("My Window");
        frame.getContentPane();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(1200, 800);
        frame.setVisible(true);
        //for(int i = 1;i<7;i++){
          //  try {
            //    Thread.sleep(  100);
            //} catch (InterruptedException ie) {
              //  Thread.currentThread().interrupt();
            //}
            //feld[i][i].changeToWall();

        //}
    }

    public void actionPerformed(ActionEvent event){ //if button is pressed then this changes button text
        button.setText("I was pressed!");
    }

}