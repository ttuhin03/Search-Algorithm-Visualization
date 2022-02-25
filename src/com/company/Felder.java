package com.company;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Felder {
    private int x,y;
    private boolean isWall;
    private boolean startPoint;
    private boolean endPoint;
    private JLabel label = new JLabel();
    private final ImageIcon blau = new ImageIcon("C:\\Users\\Tuhin Thodeme\\Search Algo V 0\\src\\com\\company\\bilder\\blau.jpg");
    private final ImageIcon weiss = new ImageIcon("C:\\Users\\Tuhin Thodeme\\Search Algo V 0\\src\\com\\company\\bilder\\weiß.jpg");
    private final ImageIcon grau = new ImageIcon("C:\\Users\\Tuhin Thodeme\\Search Algo V 0\\src\\com\\company\\bilder\\grau.jpg");
    private final ImageIcon start = new ImageIcon("C:\\Users\\Tuhin Thodeme\\Search Algo V 0\\src\\com\\company\\bilder\\green.jpg");
    private final ImageIcon end = new ImageIcon("C:\\Users\\Tuhin Thodeme\\Search Algo V 0\\src\\com\\company\\bilder\\red.jpg");
    private final ImageIcon used = new ImageIcon("C:\\Users\\Tuhin Thodeme\\Search Algo V 0\\src\\com\\company\\bilder\\yellow.jpg");
    private ImageIcon original = new ImageIcon();


    public Felder(){
        isWall = false;
        startPoint = false;
        endPoint = false;
        x = -42;
        y = -42;
    }

    public JPanel addFeld(@NotNull JPanel panel, int xPos, int yPos, String farbe){
        x = xPos;
        y = yPos;

        if(farbe.equals("weiß")){
            original = weiss;
            label.setIcon(weiss);
        }else if(farbe.equals("grau")){
            original = grau;
            label.setIcon(grau);
        }else{
            throw new java.lang.Error("Fehler beim setzen der Felder/Farben");
        }

        label.setBounds(xPos, yPos, 30, 30);

        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if(gui_test.setWallButton){
                    changeToWall();
                }else if (gui_test.setStartpointButton){
                    setStartPoint();
                }else if(gui_test.setEndpointButton){
                    setEndPoint();
                }else if(gui_test.setDeleteButton){
                    changeToOiginal();
                }else{
                    System.out.println("Keine Auswahl getroffen");
                }

            }
        });

        panel.add(label);
        return panel;
    }

    public void changeToUsed(){
        label.setIcon(used);
    }

    public boolean isStartPoint(){
        return startPoint;
    }

    public boolean isEndPoint() {
        return endPoint;
    }

    public boolean isWall() {
        return isWall;
    }

    public void changeToPath(){
        //get color for path
        //label.setIcon();
    }

    public void changeToOiginal(){
        label.setIcon(original);
        isWall = false;
        startPoint = false;
        endPoint = false;
        //TODO: ruft nach einem manuellen zurückseen die globale variablen updater auf
    }

    public void setEndPoint(){
        if(!gui_test.globalIsEndpointSet) {
            label.setIcon(end);
            endPoint = true;
            gui_test.endPointXPos = x;
            gui_test.startPointYPos = y;
            gui_test.globalIsEndpointSet = true;
            //System.out.println("Set Endpoint");
            if(startPoint) {
                startPoint = false;
                gui_test.globalIsStartpointSet = false;
            }
            if (isWall){
                isWall = false;
            }
        }else {
            System.out.println("Another Endpoint is already set");
        }
    }

    public void setStartPoint(){
        if(!gui_test.globalIsStartpointSet) {
                label.setIcon(start);
                startPoint = true;
                gui_test.startPointXPos = x;
                gui_test.startPointYPos = y;
                gui_test.globalIsStartpointSet = true;
                //System.out.println("Set Startpoint");
            if(endPoint) {
                endPoint = false;
                gui_test.globalIsEndpointSet = false;
            }
            if (isWall){
                isWall = false;
            }
        }else{
            System.out.println("Another Startpoint is already set");
        }
    }

    public void changeToWall(){
        label.setIcon(blau);
        isWall = true;
        System.out.println("Set Wall");
    }
}