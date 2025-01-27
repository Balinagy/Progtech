/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tron_beadando_lhdn48;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * HighScores osztály kapcsolatot teremt a db és a java kód között
 * @author nagybalazs
 */
public class HighScores {
    private PreparedStatement insertStatement;
    private PreparedStatement updateStatement;
    private Connection connection;

    public HighScores(int maxScores) throws SQLException {
        String dbURL = "jdbc:derby://localhost:1527/tronHighScores;";
        connection = DriverManager.getConnection(dbURL);
        String insertQuery = "INSERT INTO TRONHIGHSCORES (NAME, SCORE) VALUES (?, ?)";
        String updateQuery = "UPDATE TRONHIGHSCORES SET SCORE = ? WHERE NAME = ?";
        insertStatement = connection.prepareStatement(insertQuery);
        updateStatement = connection.prepareStatement(updateQuery);
    }

    public ArrayList<HighScore> getHighScores() throws SQLException {
        String query = "SELECT * FROM TRONHIGHSCORES";
        ArrayList<HighScore> highScores = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet results = stmt.executeQuery(query)) {
            while (results.next()) {
                String name = results.getString("NAME");
                int score = results.getInt("SCORE");
                highScores.add(new HighScore(name, score));
            }
        }
        sortHighScores(highScores);
        return highScores;
    }

    private void sortHighScores(ArrayList<HighScore> highScores) {
        Collections.sort(highScores, new Comparator<HighScore>() {
            @Override
            public int compare(HighScore t, HighScore t1) {
                return t1.getScore() - t.getScore();
            }
        });
    }

    public void putHighScore(String name) throws SQLException {
        ArrayList<HighScore> highScores = getHighScores();
        boolean alreadyInTheTable = false;
        HighScore helper = null;
        for (HighScore a : highScores) {
            if (a.getName().equals(name)) {
                alreadyInTheTable = true;
                helper = a;
                break;
            }
        }
        if (alreadyInTheTable) {
            updateScore(helper.getName(), helper.getScore() + 1);
        } else {
            insertScore(name, 1);
        }
    }

    private void updateScore(String name, int score) throws SQLException {
        try {
            updateStatement.setInt(1, score);
            updateStatement.setString(2, name);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Hiba történt a pontszám frissítése során");
        }
    }

    private void insertScore(String name, int score) throws SQLException {
        try {
            insertStatement.setString(1, name);
            insertStatement.setInt(2, score);
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Hiba történt a pontszám hozzáadása során");
        }
    }
}
