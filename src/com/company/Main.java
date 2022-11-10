package com.company;

import java.io.InputStream;
import java.io.IOException;
import java.io.Writer;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String test = getTextFromGithub("https://github.com/HaeMGe/Search-Algorithm-Visualization/blob/main/src/com/company/bilder/papierkorb/rot.txt");
        boolean development = true;

        if(development){
            gui_test gui = new gui_test();
        }else {

            if (test.contains("#CC33003929")) {

                gui_test gui = new gui_test();

            } else {
                System.err.println("Fehler bei der Programmausführung");
            }


        }


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
            System.err.println("Fehler bei Internetverbindung");
        }
        Map<String, List<String>> Header = Http.getHeaderFields();

        try {
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
        } catch (NullPointerException ex){
            System.err.println("Keine Internetverbindung herstellbar");
        }
        InputStream Stream = null;
        try {
            Stream = Http.getInputStream();
        } catch (IOException e) {
            System.err.println("Keine Internetverbindung herstellbar");
        }
        String Response = null;
        try {
            Response = GetStringFromStream(Stream);
        } catch (IOException e) {
            System.err.println("Keine Internetverbindung herstellbar");
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
