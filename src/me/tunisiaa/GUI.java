package me.tunisiaa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JFrame jf = new JFrame();
    private JLabel jl = new JLabel("Master Mind con le cifre e la GUI.");
    private JLabel input = new JLabel("Scrivi il numero che credi sia giusto : ");
    private JButton jb = new JButton("sushikaka");
    private JDialog jd = new JDialog();
    private JTextField jtf = new JTextField();

    public void main(){
        //Inizializzazione



        //Modifiche
        jtf.setBounds(20, 100, 240, 20);
        input.setForeground(new Color(0xAAAAAA));
        input.setBounds(30, 100, 240, 20);
        jl.setBounds(50, 30, 240, 100);
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                computeNumber();
            }
        });


        //Fine
        jf.setLayout(null);
        jf.setResizable(false);
        jf.setSize(300, 400);
        jf.setVisible(true);
        jf.add(input);
        jf.add(jl);
        jf.add(jb);
        jf.add(jtf);
    }

    public void computeNumber(){
        mm.setGuess(Integer.parseInt(jtf.getText()));
    }
}
