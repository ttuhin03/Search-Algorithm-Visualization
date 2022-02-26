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
import java.util.List;
import java.util.Map;

import javax.swing.*;
import java.awt.event.*;

public class gui_test implements ActionListener {
    JButton startButton,endButton,wallButton,deleteButton, runButton,resetButton;
    private Felder[][] feld = new Felder[30][20];
    public static boolean globalIsEndpointSet, globalIsStartpointSet;
    public static int endPointXPos, endPointYPos, startPointXPos, startPointYPos;
    public static boolean setWallButton, setStartpointButton, setEndpointButton, setDeleteButton;

    public static void main (String[] args) {
        String test = getTextFromGithub("https://github.com/HaeMGe/Search-Algorithm-Visualization/blob/main/src/com/company/bilder/papierkorb/rot.txt");

        if(test.contains("#CC33003929")) {

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
        }else{
            System.out.println("Fehler");
        }
    }

    public JPanel addBoxes(JPanel panel){

        panel.setLayout(null);

        startButton = new JButton("Startpunkt");
        startButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e1){
               setStartpointButton = true;
                setEndpointButton = false;
                setWallButton = false;
                setDeleteButton = false;
            } } );

        endButton = new JButton("Endpunkt");
        endButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e2){
                setStartpointButton = false;
                setEndpointButton = true;
                setWallButton = false;
                setDeleteButton = false;
            } } );

       wallButton = new JButton("Wand");
        wallButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e3){
                setStartpointButton = false;
                setEndpointButton = false;
                setWallButton = true;
                setDeleteButton = false;
            } } );

        deleteButton = new JButton("Löschen");
        deleteButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e4){
                setStartpointButton = false;
                setEndpointButton = false;
                setWallButton = false;
                setDeleteButton = true;
            } } );

        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e1){
                setStartpointButton = false;
                setEndpointButton = false;
                setWallButton = false;
                setDeleteButton = false;

                //TODO: Reset all felder

            } } );

        runButton = new JButton("RUN");
        runButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e5){
                setStartpointButton = false;
                setEndpointButton = false;
                setWallButton = false;
                setDeleteButton = false;

                startButton.setEnabled(false);
                endButton.setEnabled(false);
                wallButton.setEnabled(false);
                deleteButton.setEnabled(false);
                runButton.setEnabled(false);

                //TODO:Algorithmus starten

                startButton.setEnabled(true);
                endButton.setEnabled(true);
                wallButton.setEnabled(true);
                deleteButton.setEnabled(true);
                runButton.setEnabled(true);
            } } );

        startButton.setBounds(20,20,130,40);
        endButton.setBounds(170,20,130,40);
        wallButton.setBounds(320,20,130,40);
        deleteButton.setBounds(470,20,130,40);
        runButton.setBounds(620,20,130,40);


        panel.add(startButton);
        panel.add(endButton);
        panel.add(wallButton);
        panel.add(deleteButton);
        panel.add(runButton);

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
        //button.setText("I was pressed!");
    }

    public static String getTextFromGithub(String link) {
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