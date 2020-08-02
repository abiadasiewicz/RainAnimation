package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Main extends JFrame {

    Main() {
        this.setSize(400, 400);
        this.setDefaultCloseOperation(3);
        this.setTitle("Rain");
        this.setLocationRelativeTo(null);


        buttonPanel.add(start);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                start.setText("More rain!");

                for (int i = 0; i < 10; i++) {
                    rainPanel.addRain();
                }

            }
        });

        this.getContentPane().add(rainPanel);
        rainPanel.setBackground(new Color(13, 43, 189));
        buttonPanel.setBackground(new Color(116, 189, 13));
        start.setBackground(new Color(116, 189, 13));

        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    class RainPanel extends JPanel {


        JPanel ten = this;

        public void addRain() {

            rainList.add(new Krople());

            thread = new Thread(new RainRunnable((Krople) rainList.get(rainList.size() - 1)));

            thread.start();


        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int i = 0; i < rainList.size(); i++) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(new Color(11, 161, 226));
                int length = new Random().nextInt(30) + 2;
                if (length < 10) {
                    g2.setStroke(new BasicStroke(1));
                } else if (length < 420) {
                    g2.setStroke(new BasicStroke(3));
                } else if (length < 30) {
                    g2.setStroke(new BasicStroke(4));
                }

                g.drawLine(((Krople) rainList.get(i)).x, ((Krople) rainList.get(i)).y, ((Krople) rainList.get(i)).x, ((Krople) rainList.get(i)).y + length);

            }
        }

        ArrayList rainList = new ArrayList();

        public class RainRunnable implements Runnable {

            public RainRunnable(Krople krople) {
                this.krople = krople;
            }

            @Override
            public void run() {
                for (int i = 0; i < 400; i++) {

                    for (int j = 0; j < rainList.size(); j++) {
                        try {
                            while (!Thread.currentThread().isInterrupted()) {

                                this.krople.rusz(ten);
                                repaint();
                                Thread.sleep(5);
                            }

                        } catch (InterruptedException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

            }

            Krople krople;
        }
    }


    private JPanel buttonPanel = new JPanel();
    private RainPanel rainPanel = new RainPanel();
    private JButton start = new JButton("Rain!");

    ThreadGroup threadGroup = new ThreadGroup("Group");
    Thread thread = new Thread();

    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}
