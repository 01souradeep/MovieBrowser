/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ramaih.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author souradeep
 */
public class MovieListPanel extends javax.swing.JPanel {

    /**
     * Creates new form MovieListPanel
     */
    public MovieListPanel() {
        initComponents();
        fillUpTable();
    }

    private void fillUpTable() {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        conn = org.ramaih.project.MovieConn.connectdb();
        String sql = "select* from movie";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            List<String[]> tabdata = new ArrayList<>();
            while (rs.next()) {
                String row[] = new String[5];
                row[0] = rs.getString("S");
                row[1] = rs.getString("Name");
                row[2] = rs.getString("Genre");
                row[3] = rs.getString("Ratings");
                row[4] = rs.getString("IMDB");
                tabdata.add(row);
            }

            String[][] data = tabdata.toArray(new String[0][0]);

            dataTable.setModel(new DefaultTableModel(data, new String[]{"Serial Number", "Movie", "Genre", "Ratings", "IMDB"}));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(dataTable);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable dataTable;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
