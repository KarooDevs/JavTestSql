package com.test.mysql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class psDeletePanel {
  public psDeletePanel() {

    delBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String idPS;
        idPS = delID.getText();

        try{
          preparedStatement = connector.ConnectDB().prepareStatement("DELETE FROM tb_ps WHERE id_ps=?;");
          preparedStatement.setString(1, idPS);
          preparedStatement.executeUpdate();

          JOptionPane.showMessageDialog(null, "Data Berhasil dihapus.");
          JComponent component = (JComponent) e.getSource();
          Window window = SwingUtilities.getWindowAncestor(component);
          window.dispose();
        } catch (SQLException err) {
          err.printStackTrace();
        }
      }
    });

    cancelBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JComponent component = (JComponent) e.getSource();
        Window window = SwingUtilities.getWindowAncestor(component);
        window.dispose();
      }
    });
  }
  public JPanel getPsDelPanel() {
    return psDelMainPanel;
  }
  public PreparedStatement preparedStatement;
  private JPanel psDelMainPanel;
  private JPanel psIDDelPanel;
  private JPanel psDelBtn;
  private JLabel psDelTitle;
  private JLabel targetDelIDTitle;
  private JTextField delID;
  private JLabel idTitle;
  private JButton cancelBtn;
  private JButton delBtn;
}
