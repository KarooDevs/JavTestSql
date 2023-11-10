package com.test.mysql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class updatePanel {
  //update
  public updatePanel() {
    updateBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String upID ,upPelanggan, upPegawai, upKodePS, upTglSewa, upTglKembali, upDurasi;
        upID = updateNotaID.getText();
        upPelanggan = updatePelanggan.getText();
        upPegawai = updatePegawai.getText();
        upKodePS = updateKodePS.getText();
        upTglSewa = updateTglSewa.getText();
        upTglKembali = updateTglKembali.getText();
        upDurasi = updateDurasi.getText();

        if(!Objects.equals(upID, "") && !Objects.equals(upPelanggan, "") && !Objects.equals(upPegawai, "") && !Objects.equals(upKodePS, "") && !Objects.equals(upTglSewa, "") && !Objects.equals(upTglKembali, "") && !Objects.equals(upDurasi, "")) {
          try {
            preparedStatement = connector.ConnectDB().prepareStatement("UPDATE tb_nota SET nama_pelanggan=?, nama_pegawai=?, id_ps=?, tgl_sewa=?, tgl_kembali=?, durasi=? WHERE id_nota=?;");
            preparedStatement.setString(1, upPelanggan);
            preparedStatement.setString(2, upPegawai);
            preparedStatement.setString(3, upKodePS);
            preparedStatement.setString(4, upTglSewa);
            preparedStatement.setString(5, upTglKembali);
            preparedStatement.setString(6, upDurasi);
            preparedStatement.setString(7, upID);
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
    updateCancelBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JComponent component = (JComponent) e.getSource();
        Window window = SwingUtilities.getWindowAncestor(component);
        window.dispose();
      }
    });
  }

  public JPanel getUpdatePanel() {
    return mainUpdatePanel;
  }
  private PreparedStatement preparedStatement;
  private JPanel mainUpdatePanel;
  private JLabel updateTitle;
  private JTextField updateNotaID;
  private JTextField updatePelanggan;
  private JTextField updatePegawai;
  private JTextField updateKodePS;
  private JTextField updateTglSewa;
  private JTextField updateTglKembali;
  private JTextField updateDurasi;
  private JButton updateCancelBtn;
  private JButton updateBtn;
  private JLabel idUpdateLabel;
  private JLabel updateID;
  private JPanel updateIDPanel;
  private JPanel updateDataPanel;
  private JLabel updatePelangganLabel;
  private JLabel updatePegawaiLabel;
  private JLabel updateKodePSLabel;
  private JLabel updateTglSewaLabel;
  private JLabel updateTglKembaliLabel;
  private JLabel updateDurasiLabel;
  private JPanel updateBtnPanel;
}
