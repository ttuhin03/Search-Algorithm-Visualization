package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

public class gui_test implements ActionListener {
    public JFrame frame;
    public JPanel panel;
    private static int[][] arrayFeld, markUsed;
    private static int [][][]saveLast;
    JButton startButton,endButton,wallButton,deleteButton, runButton,resetButton;
    private Felder[][] feld = new Felder[30][20];
    public static boolean globalIsEndpointSet, globalIsStartpointSet;
    public static int endPointXPos, endPointYPos, startPointXPos, startPointYPos;
    public static boolean setWallButton, setStartpointButton, setEndpointButton, setDeleteButton;

    public static void main (String[] args) {
        String test = getTextFromGithub("https://github.com/HaeMGe/Search-Algorithm-Visualization/blob/main/src/com/company/bilder/papierkorb/rot.txt");

        if(test.contains("#CC33003929")) {

            gui_test gui = new gui_test();

            //alle globale Variablen werden auf ihren Strtwert gesetzt
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

            //UI wird erstellt
            gui.press();
        }else{
            System.out.println("Fehler");
        }
    }

    public JPanel addBoxes(JPanel panel){

        panel.setLayout(null);

        //Button um den Startpunkt zu setzen
        startButton = new JButton("Startpunkt");
        startButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e1){
               setStartpointButton = true;
                setEndpointButton = false;
                setWallButton = false;
                setDeleteButton = false;
            } } );

        //Button um den Endpunkt zu setzen
        endButton = new JButton("Endpunkt");
        endButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e2){
                setStartpointButton = false;
                setEndpointButton = true;
                setWallButton = false;
                setDeleteButton = false;
            } } );

        //Button um Wände zu setzen
        wallButton = new JButton("Wand");
        wallButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e3){
                setStartpointButton = false;
                setEndpointButton = false;
                setWallButton = true;
                setDeleteButton = false;
            } } );

        //Button um die Auswahl vom Feld wieder rückgängig zu machen
        deleteButton = new JButton("Löschen");
        deleteButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e4){
                setStartpointButton = false;
                setEndpointButton = false;
                setWallButton = false;
                setDeleteButton = true;
                //TODO: aufruf globaVaroable updater damit evt.  fehler vermieden werden
            } } );

        //Button um ALLE Felder wieder zurückzusetzen
        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e1){
                setStartpointButton = false;
                setEndpointButton = false;
                setWallButton = false;
                setDeleteButton = false;

                resetAllFelder();

                endPointXPos = -42;
                endPointYPos = -42;
                startPointXPos = -42;
                startPointYPos = -42;
                globalIsEndpointSet = false;
                globalIsStartpointSet = false;

            } } );

        //Button für den Start des ausgewählten Algorithmus
        runButton = new JButton("RUN");
        runButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e5){

                if(checkRequirements()) {

                    setStartpointButton = false;
                    setEndpointButton = false;
                    setWallButton = false;
                    setDeleteButton = false;

                    startButton.setEnabled(false);
                    endButton.setEnabled(false);
                    wallButton.setEnabled(false);
                    deleteButton.setEnabled(false);
                    runButton.setEnabled(false);

                    //TODO:Algorithmus starten + auswahl des algorithmus überprüfen
                    breitenSuche();

                    startButton.setEnabled(true);
                    endButton.setEnabled(true);
                    wallButton.setEnabled(true);
                    deleteButton.setEnabled(true);
                    runButton.setEnabled(true);

                }else{
                    System.out.println("Voraussetzungen nicht erfüllt");
                }

            } } );

        //Platzierung der Buttons
        startButton.setBounds(20,20,130,40);
        endButton.setBounds(170,20,130,40);
        wallButton.setBounds(320,20,130,40);
        deleteButton.setBounds(470,20,130,40);
        runButton.setBounds(620,20,130,40);
        resetButton.setBounds(770,20,130,40);

        //Hinzufügen der Buttons auf das panel/UI
        panel.add(startButton);
        panel.add(endButton);
        panel.add(wallButton);
        panel.add(deleteButton);
        panel.add(runButton);
        panel.add(resetButton);

        //Felder werden erstellt, zum panel hinzugefügt und im Schachbrettmuster angelegt
        int zaehler = 0;
        for(int i = 0;i<30;i++) {
            for(int j = 0 ; j<20;j++) {
                if((zaehler+j)%2==0){
                    feld[i][j] = new Felder();
                    panel = feld[i][j].addFeld(panel,30 * i,30 * j+60,"grau",i,j);
                }else {
                    feld[i][j] = new Felder();
                    panel = feld[i][j].addFeld(panel,30 * i,30 * j+60,"weiß",i,j);
                }
            }
            zaehler++;
        }

        return panel;
    }

    public void resetAllFelder(){
        for(int i = 0;i<30;i++) {
            for(int j = 0 ; j<20;j++) {

                    feld[i][j].changeToOiginal();

            }
        }
    }

    public void updateGlobalVar(){
        //TODO:updatet globale variablen, nachdem ein block zurückgesetzt wurde
    }

    public boolean checkRequirements(){
        //überprüft, ob auch nur genau ein startpunkt, ein endpunkt, und eine genaue Auswahl getroffen wurde
        //TODO: bei mehreren möglichen auswahlmöglichkeiten für suchverfahren, muss das auch überprüft werden
        int anzahlStartPunlte = 0;
        int anzahlEndpunkte = 0;

        for(int i = 1;i<30;i++) {
            for(int j = 1 ; j<20;j++) {
                if(feld[i][j].isEndPoint()) {
                    anzahlEndpunkte = anzahlEndpunkte +1;
                }
                if(feld[i][j].isStartPoint()) {
                    anzahlStartPunlte = anzahlStartPunlte +1;
                }
            }
        }

        if(anzahlEndpunkte >1 || anzahlStartPunlte>1){
            return false;
        }
        return true;
    }

    public void breitenSuche() {

        if(checkRequirements()) {
            arrayFeld = new int[30][20];
            markUsed = new int[30][20];
            saveLast = new int[30][20][2];
            boolean terminate = false;
            Queue xPos = new Queue(6000);
            Queue yPos = new Queue(6000);


            //Kopiert die Informationen aus den Feldern und setzt sie in das int Array
            // 0 wenn das Feld eine Wand ist, ansonsten 1.
            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 20; j++) {
                    markUsed[i][j] =0;
                    if (feld[i][j].isWall()) {
                        arrayFeld[i][j] = 0;
                    } else {
                        arrayFeld[i][j] = 1;
                    }
                }
            }
            //TODO: Breitensuche implementieren

            //System.out.println(startPointXPos+"--"+startPointYPos);
            markUsed[startPointXPos][startPointYPos] = 1;

            if(startPointXPos -1 >=0){
                //System.out.println(startPointXPos+"---------"+startPointYPos);
                //System.out.println(startPointXPos-1+"---------"+startPointYPos);
                xPos.enqueue(startPointXPos-1);
                yPos.enqueue(startPointYPos);
                System.out.println(startPointXPos+"---"+startPointYPos);
                markUsed[startPointXPos-1][startPointYPos] =1;
                saveLast[startPointXPos-1][startPointYPos][0] =  startPointXPos;
                saveLast[startPointXPos-1][startPointYPos][1] =  startPointYPos;
                feld[startPointXPos-1][startPointYPos].changeToUsed();
            }
            if(startPointYPos-1>=0){
                xPos.enqueue(startPointXPos);
                yPos.enqueue(startPointYPos-1);
                markUsed[startPointXPos][startPointYPos-1] =1;
                saveLast[startPointXPos][startPointYPos-1][0] =  startPointXPos;
                saveLast[startPointXPos][startPointYPos-1][1] =  startPointYPos;
                feld[startPointXPos][startPointYPos-1].changeToUsed();
            }
            if(startPointXPos+1<29){
                xPos.enqueue(startPointXPos+1);
                yPos.enqueue(startPointYPos);
                markUsed[startPointXPos+1][startPointYPos] =1;
                saveLast[startPointXPos+1][startPointYPos][0] =  startPointXPos;
                saveLast[startPointXPos+1][startPointYPos][1] =  startPointYPos;
                feld[startPointXPos+1][startPointYPos].changeToUsed();
            }
            if(startPointYPos+1<19){
                xPos.enqueue(startPointXPos);
                yPos.enqueue(startPointYPos+1);
                markUsed[startPointXPos][startPointYPos+1] =1;
                saveLast[startPointXPos][startPointYPos+1][0] =  startPointXPos;
                saveLast[startPointXPos][startPointYPos+1][1] =  startPointYPos;
                feld[startPointXPos][startPointYPos+1].changeToUsed();
            }

            int xTemp =0;
            int yTemp = 0;

            for (int i = 0;i<10;i++) {

                waitt();


                xTemp = xPos.dequeue();
                yTemp = yPos.dequeue();

            //System.out.println(xTemp+"---------"+yTemp);
            //System.out.println(xTemp-1+"---------"+yTemp);

                if(xTemp == endPointXPos && yTemp == endPointYPos){
                    terminate = true;
                    //TODO: Weg markieren
                }

                if(xTemp -1 >=0 && markUsed[xTemp-1][yTemp]==0){
                    waitt();
                    xPos.enqueue(xTemp-1);
                    yPos.enqueue(yTemp);
                    markUsed[xTemp-1][yTemp] =1;
                    saveLast[xTemp-1][yTemp][0] =  xTemp;
                    saveLast[xTemp-1][yTemp][1] =  yTemp;
                    feld[xTemp-1][yTemp].changeToUsed();
                    System.out.println("used");
                }
                if(xTemp +1 <29 && markUsed[xTemp+1][yTemp]==0){
                    waitt();
                    xPos.enqueue(xTemp+1);
                    yPos.enqueue(yTemp);
                    markUsed[xTemp+1][yTemp] =1;
                    saveLast[xTemp+1][yTemp][0] =  xTemp;
                    saveLast[xTemp+1][yTemp][1] =  yTemp;
                    feld[xTemp+1][yTemp].changeToUsed();
                    System.out.println("used");
                }
                if(yTemp -1 >=0 && markUsed[xTemp][yTemp-1]==0){
                    waitt();
                    xPos.enqueue(xTemp);
                    yPos.enqueue(yTemp-1);
                    markUsed[xTemp][yTemp-1] =1;
                    saveLast[xTemp][yTemp-1][0] =  xTemp;
                    saveLast[xTemp][yTemp-1][1] =  yTemp;
                    feld[xTemp][yTemp-1].changeToUsed();
                }
                if(yTemp +1 <19 && markUsed[xTemp][yTemp+1]==0){
                    waitt();
                    xPos.enqueue(xTemp);
                    yPos.enqueue(yTemp+1);
                    markUsed[xTemp][yTemp+1] =1;
                    saveLast[xTemp][yTemp+1][0] =  xTemp;
                    saveLast[xTemp][yTemp+1][1] =  yTemp;
                    feld[xTemp][yTemp+1].changeToUsed();
                }

                JPanel newPanel = panel;
                frame.remove(panel);
                frame.add(newPanel);
                frame.revalidate();
                frame.repaint();


            }



        }else{
            System.out.println("Fehler bei den Voraussetzungen");
        }
    }

public void waitt(){
    try {
        Thread.sleep(  300);
    } catch (InterruptedException ie) {
        Thread.currentThread().interrupt();
    }
}

    public void press()   {
        //UI wird erstellt
        panel = new JPanel();

        //Felder und Buttons werden erstellt und auf das panel gesetzt
        panel = addBoxes(panel);

        //Fenster wird erstellt und gezeigt
        frame = new JFrame("Search Algorithm Visualization - TuhinUni");
        frame.getContentPane();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(1200, 800);
        frame.setVisible(true);
        //TODO: bei der visualisierung die wartezeit einbauen
        for(int i = 1;i<7;i++){
            waitt();
            feld[i][i].changeToWall();

        }
    }

    public void actionPerformed(ActionEvent event){ //if button is pressed then this changes button text
        //button.setText("I was pressed!");
    }

    public static String getTextFromGithub(String link) {

        //Wichtige Magie

        URL Url = null;
        try {
            Url = new URL(link);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
        HttpURLConnection Http = null;
        try {
            Http = (HttpURLConnection) Url.openConnection();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Map<String, List<String>> Header = Http.getHeaderFields();

        for (String header : Header.get(null)) {
            if (header.contains(" 302 ") || header.contains(" 301 ")) {
                link = Header.get("Location").get(0);
                try {
                    Url = new URL(link);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    Http = (HttpURLConnection) Url.openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Header = Http.getHeaderFields();
            }
        }
        InputStream Stream = null;
        try {
            Stream = Http.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String Response = null;
        try {
            Response = GetStringFromStream(Stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response;
    }

    private static String GetStringFromStream(InputStream Stream) throws IOException {

        //Wichtige Magie

        if (Stream != null) {
            Writer Writer = new StringWriter();

            char[] Buffer = new char[2048];
            try {
                Reader Reader = new BufferedReader(new InputStreamReader(Stream, "UTF-8"));
                int counter;
                while ((counter = Reader.read(Buffer)) != -1) {
                    Writer.write(Buffer, 0, counter);
                }
            } finally {
                Stream.close();
            }
            return Writer.toString();
        } else {
            return "No Contents";
        }
    }
}