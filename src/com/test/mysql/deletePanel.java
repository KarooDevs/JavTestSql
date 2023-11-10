package com.test.mysql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class deletePanel {
  public deletePanel() {
    delBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String idNota;
        idNota = delId.getText();


        try {
          preparedStatement = connector.ConnectDB().prepareStatement("DELETE FROM tb_nota WHERE id_nota=?;");
          preparedStatement.setString(1, idNota);
          preparedStatement.executeUpdate();

          JOptionPane.showMessageDialog(null, "Data Berhasil dihapus.");
          JComponent component = (JComponent) e.getSource();
          Window window = SwingUtilities.getWindowAncestor(component);
          window.dispose();
        }catch (SQLException err) {
          err.printStackTrace();
        }
      }
    });
    delCancelBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JComponent component = (JComponent) e.getSource();
        Window window = SwingUtilities.getWindowAncestor(component);
        window.dispose();
      }
    });
  }

  public  JPanel getDeletePanel() {
    return deleteMainPanel;
  }
  public PreparedStatement preparedStatement;
  private JPanel deleteMainPanel;
  private JLabel deleteTitleLabel;
  private JPanel delIDPanel;
  private JPanel delBtnPanel;
  private JTextField delId;
  private JButton delCancelBtn;
  private JButton delBtn;
  private JLabel idDelLabel;
  private JLabel delIDLabel;
}
