package com.test.mysql;

import javax.swing.*;

public class show {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(show::createGUI);
  }
  public static void createGUI() {
    dataInterface UI = new dataInterface();
    JPanel root = UI.getMainPanel();

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(root);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
