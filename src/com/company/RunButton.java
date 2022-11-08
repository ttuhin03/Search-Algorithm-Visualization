package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class RunButton extends JButton{

    private gui_test gui;

    public RunButton(gui_test progam){
        //super("Run");
        super(new ImageIcon(new ImageIcon("src/com/company/bilder/Screenshot 2022-11-08 195052.jpg").getImage().getScaledInstance(130, 40, Image.SCALE_DEFAULT)));

        gui = progam;

        //Deletes all Borders and Background from Button
        //Löscht alle Bilder und den Hintergrund vom Knopf
        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setBorder(BorderFactory.createEmptyBorder(0,0,0,0)); // Especially important

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

    public Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;


    }


}
