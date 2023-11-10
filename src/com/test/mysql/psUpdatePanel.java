package com.test.mysql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class psUpdatePanel {
  public psUpdatePanel() {

    updatePSBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String upID, upTipe, upHarga;
        upID = updtKodePS.getText();
        upTipe = updtTipePS.getText();
        upHarga = updtHargaPS.getText();

        if(!Objects.equals(upID, "") && !Objects.equals(upTipe, "") && !Objects.equals(upHarga, "")){
          try {
            preparedStatement = connector.ConnectDB().prepareStatement("UPDATE tb_ps SET tipe_ps=?, harga=? WHERE id_ps=?;");
            preparedStatement.setString(1, upTipe);
            preparedStatement.setString(2, upHarga);
            preparedStatement.setString(3, upID);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Update Data Berhasil!");
            JComponent component = (JComponent) e.getSource();
            Window window = SwingUtilities.getWindowAncestor(component);
            window.dispose();
          } catch (SQLException exception) {
            exception.printStackTrace();
          }
        } else {
          JOptionPane.showMessageDialog(null, "Data tidak boleh kosong!!");
        }
      }
    });
    //cancel
    cancelPSBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JComponent component = (JComponent) e.getSource();
        Window window = SwingUtilities.getWindowAncestor(component);
        window.dispose();
      }
    });
  }
  public JPanel getPSUpdatePanel() {
    return mainPSUpdatePanel;
  }
  private PreparedStatement preparedStatement;
  private JPanel mainPSUpdatePanel;
  private JLabel psUpdateTitle;
  private JPanel idPSUpdatePanel;
  private JPanel psDataUpdatePanel;
  private JPanel psUpdateBtnPanel;
  private JLabel idPSTitle;
  private JLabel psDataTitle;
  private JButton cancelPSBtn;
  private JButton updatePSBtn;
  private JTextField updtKodePS;
  private JLabel inIdPSTitle;
  private JTextField updtTipePS;
  private JTextField updtHargaPS;
  private JLabel updtTipeTitle;
  private JLabel updtHargaTitle;
}
