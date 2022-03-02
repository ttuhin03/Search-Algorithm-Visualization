package com.company;

import com.company.gui_test;
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
    //TODO: Change Paths from Absoolute Path to relative paths
    private final ImageIcon blau = new ImageIcon("C:\\Users\\Tuhin Thodeme\\Documents\\GitHub\\Search-Algorithm-Visualization\\src\\com\\company\\bilder\\blau.jpg");
    private final ImageIcon weiss = new ImageIcon("C:\\Users\\Tuhin Thodeme\\Documents\\GitHub\\Search-Algorithm-Visualization\\src\\com\\company\\bilder\\weiß.jpg");
    private final ImageIcon grau = new ImageIcon("C:\\Users\\Tuhin Thodeme\\Documents\\GitHub\\Search-Algorithm-Visualization\\src\\com\\company\\bilder\\grau.jpg");
    private final ImageIcon start = new ImageIcon("C:\\Users\\Tuhin Thodeme\\Documents\\GitHub\\Search-Algorithm-Visualization\\src\\com\\company\\bilder\\green.jpg");
    private final ImageIcon end = new ImageIcon("C:\\Users\\Tuhin Thodeme\\Documents\\GitHub\\Search-Algorithm-Visualization\\src\\com\\company\\bilder\\red.jpg");
    private final ImageIcon used = new ImageIcon("C:\\Users\\Tuhin Thodeme\\Documents\\GitHub\\Search-Algorithm-Visualization\\src\\com\\company\\bilder\\yellow.jpg");
    private final ImageIcon weg = new ImageIcon("C:\\Users\\Tuhin Thodeme\\Documents\\GitHub\\Search-Algorithm-Visualization\\src\\com\\company\\bilder\\magenta.jpg");
    private ImageIcon original = new ImageIcon();


    public Felder(){
        //default Werte
        isWall = false;
        startPoint = false;
        endPoint = false;
        x = -42;
        y = -42;
    }

    public JPanel addFeld(@NotNull JPanel panel, int xPosLabel, int yPosLabel, String farbe, int xPos,int yPos){
        //Es wird ein label erstellt, welches an der Position x,y am panel ist
        //und die aktion für den Fall der Auswahl wird gesetzt.
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

        //Position und Groesse des Labels wird gesetzt
        label.setBounds(xPosLabel, yPosLabel, 30, 30);

        //Die Aktion für den Fall der Auswahl wird gesetzt.
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

        //Label wird dem panel hinzugefügt
        panel.add(label);
        return panel;
    }

    //Falls beim Algorihmus durchlauf das Feld benutzt wurde,
    //wird in der UI die Farbe verändert zur Visualisierung
    public void changeToUsed(){
        label.setIcon(used);
    }

    //Gibt zurück, ob das jeweilige Feld ein Startpunkt ist
    public boolean isStartPoint(){
        return startPoint;
    }

    //Gibt zurück, ob das jeweiliige Feld ein Endpunkt ist
    public boolean isEndPoint() {
        return endPoint;
    }

    //Gibt zurück, ob das jeweilige Feld eine Wand ist
    public boolean isWall() {
        return isWall;
    }

    //Setzt am Ende des Algorithmus den gefundenen schnellesten Weg in die Farbe zur Visualisierung
    public void changeToPath(){
        label.setIcon(weg);
    }

    public void changeToOiginal(){
        //Verändert die Farbe vom Feld zu seinem Original, zB bei einem Reset oder Löschung des Feldes
        label.setIcon(original);
        isWall = false;
        startPoint = false;
        endPoint = false;
        //TODO: ruft nach einem manuellen zurückseen die globale variablen updater auf
    }

    public void setEndPoint(){
        //Setzt Endpunkt, falls noch kein anderer vorhanden ist
        if(!gui_test.globalIsEndpointSet) {
            label.setIcon(end);
            endPoint = true;
            //Updatet die Globale variablen
            gui_test.endPointXPos = x;
            gui_test.endPointYPos = y;
            gui_test.globalIsEndpointSet = true;
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
        //Setzt Startpunkt, falls noch kein anderer vorhanden ist
        if(!gui_test.globalIsStartpointSet) {
                label.setIcon(start);
                startPoint = true;
                //Updatet die Globale variablen
                gui_test.startPointXPos = x;
                gui_test.startPointYPos = y;
                gui_test.globalIsStartpointSet = true;
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
        //Verändert Farbe zu Wand
        label.setIcon(blau);
        isWall = true;
    }
}