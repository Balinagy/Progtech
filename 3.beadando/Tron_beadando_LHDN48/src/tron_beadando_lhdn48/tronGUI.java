/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tron_beadando_lhdn48;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * gui, amely megjeleníti a menüt, valamint a játékteret
 * @author nagybalazs
 */
public class tronGUI {
    private JFrame frame;
    private gameEngine gameArea;
    private JLabel timerLabel;
    private Timer timer;

    /**
     * konstruktor felállítja a gui elemeket, nullázza az órát
     */
    public tronGUI() {
        frame = new JFrame("TRON");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        gameArea = new gameEngine();
        frame.getContentPane().add(gameArea);
        JMenuItem newGame = new JMenuItem("Új játék");
        JMenuItem leaderBoard = new JMenuItem("Toplista");
        menuBar.add(newGame);
        menuBar.add(leaderBoard);
        timerLabel = new JLabel("Idő: 00:00");
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(timerLabel);
        menuBar.add(newGame);
        menuBar.add(leaderBoard);
        frame.setPreferredSize(new Dimension(800, 620));
        frame.setResizable(false);
        /**
         * itt hozom létre a popupot az új játék gomb megnyomásakor
         */
        newGame.addActionListener(new ActionListener(){
            @Override
                public void actionPerformed(ActionEvent e) {
                JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));

                inputPanel.add(new JLabel("Játékos 1 neve:"));
                JTextField player1Name = new JTextField();
                inputPanel.add(player1Name);

                inputPanel.add(new JLabel("Játékos 2 neve:"));
                JTextField player2Name = new JTextField();
                inputPanel.add(player2Name);

                String[] colors = {"Piros", "Kék", "Zöld", "Sárga", "Lila"};
                inputPanel.add(new JLabel("Játékos 1 színe:"));
                JComboBox<String> player1Color = new JComboBox<>(colors);
                inputPanel.add(player1Color);

                inputPanel.add(new JLabel("Játékos 2 színe:"));
                JComboBox<String> player2Color = new JComboBox<>(colors);
                inputPanel.add(player2Color);

                int result = JOptionPane.showConfirmDialog(frame, inputPanel, "Játékosok adatai",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    String name1 = player1Name.getText().trim();
                    String name2 = player2Name.getText().trim();
                    String color1 = (String) player1Color.getSelectedItem();
                    String color2 = (String) player2Color.getSelectedItem();

                    if (name1.isEmpty() || name2.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Mindkét játékos nevét meg kell adni!",
                                "Hiba", JOptionPane.ERROR_MESSAGE);
                    } else if (color1.equals(color2)) {
                        JOptionPane.showMessageDialog(frame, "A két játékos színe nem lehet ugyanaz!",
                                "Hiba", JOptionPane.ERROR_MESSAGE);
                    } else {
                        gameArea.restart(name1, name2, color1, color2);
                        resetTimer();
                    }
                }
        }
        });
        /**
         * figyeli, hogy a toplista gomb meg lett-e nyomva
         */
        leaderBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLeaderBoard();
            }
        });
        frame.pack();
        frame.setVisible(true);
    }
    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            private int seconds = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
                String formattedTime = sdf.format(new Date(seconds * 1000));
                timerLabel.setText("Time: " + formattedTime);
            }
        });
        timer.start();
    }

    private void resetTimer() {
        if (timer != null) {
            timer.stop();
        }
        timerLabel.setText("Idő: 00:00");
        startTimer();
    }
    
    /**
     * kiíratja a top 10 játékost
     */
    private void showLeaderBoard() {
        try {
            HighScores highScores = new HighScores(10);
            ArrayList<HighScore> scores = highScores.getHighScores();

            StringBuilder message = new StringBuilder("<html><h1>Toplista</h1><table border='1'><tr><th>Helyezés</th><th>Név</th><th>Pontszám</th></tr>");
            for (int i = 0; i < Math.min(10, scores.size()); i++) {
                HighScore hs = scores.get(i);
                message.append("<tr><td>").append(i + 1).append("</td><td>").append(hs.getName()).append("</td><td>").append(hs.getScore()).append("</td></tr>");
            }
            message.append("</table></html>");

            JOptionPane.showMessageDialog(frame, message.toString(), "Toplista", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Hiba történt a toplista betöltése során: " + ex.getMessage(),
                    "Hiba", JOptionPane.ERROR_MESSAGE);
        }
    }
}
