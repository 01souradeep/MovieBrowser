/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ramaih.project;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author souradeep
 */
public class AvailableMoviesPanel extends javax.swing.JPanel {

    private final Path localDirectory;

    /**
     * Creates new form AvailableMoviesPanel
     */
    public AvailableMoviesPanel(Path localDirectory) {
        this.localDirectory = localDirectory;
        initComponents();
        populateAvailbleMovies();
    }

    private void populateAvailbleMovies() {
        File[] movies = localDirectory.toFile().listFiles();
        DefaultListModel<MovieListItem> model = new DefaultListModel<>();
        for (File movie : movies) {
            model.addElement(new MovieListItem(movie));
        }
        jListMovies.setModel(model);
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
        jListMovies = new javax.swing.JList<>();

        setLayout(new java.awt.BorderLayout());

        jListMovies.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListMoviesMouseClicked(evt);
            }
        });
        jListMovies.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jListMoviesKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jListMovies);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jListMoviesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListMoviesMouseClicked

        

    }//GEN-LAST:event_jListMoviesMouseClicked

    private void jListMoviesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jListMoviesKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            playSelectedMovie();
        }

    }//GEN-LAST:event_jListMoviesKeyPressed

    private void playSelectedMovie() {
        MovieListItem selectedMovie = jListMovies.getSelectedValue();

        if (selectedMovie != null) {
            if (System.getProperty("os.name").toLowerCase().contains("linux")) {
                EventQueue.invokeLater(() -> {
                    jListMovies.setEnabled(false);
                    String url = "file://" + selectedMovie.moviePath.getAbsolutePath();

                    //replace all occurances ofspace with %20
                    String sanitisedUrl = url.replaceAll(" ", "%20");

                    String moviePlayerCommand = "vlc ";

                    try {
                        Runtime.getRuntime().exec(moviePlayerCommand + sanitisedUrl);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error occured", JOptionPane.ERROR_MESSAGE);
                    } finally{
                        jListMovies.setEnabled(true);
                    }
                });
            } else {
                JOptionPane.showMessageDialog(this, System.getProperty("os.name") + " is not supported!", "Error occured", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<MovieListItem> jListMovies;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private static class MovieListItem {

        final File moviePath;

        public MovieListItem(File moviePath) {
            this.moviePath = moviePath;
        }

        public String toString() {
            return moviePath.getName();
        }

    }

}