package com.test.mysql;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dataInterface {
  public dataInterface() {
    //add
    addBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String inPelanggan, inPegawai, inKode, inTglSewa, inTglKembali, inDurasi;
        inPelanggan = inputPelanggan.getText();
        inPegawai = inputPegawai.getText();
        inKode = inputPS.getText();
        inTglSewa = inputTglSewa.getText();
        inTglKembali = inputTglKembali.getText();
        inDurasi = inputDurasi.getText();

        try {
          preparedStatement = connector.ConnectDB().prepareStatement("INSERT INTO tb_nota (nama_pelanggan, nama_pegawai, id_ps, tgl_sewa, tgl_kembali, durasi) VALUES(?,?,?,?,?,?);");
          preparedStatement.setString(1, inPelanggan);
          preparedStatement.setString(2, inPegawai);
          preparedStatement.setString(3, inKode);
          preparedStatement.setString(4, inTglSewa);
          preparedStatement.setString(5, inTglKembali);
          preparedStatement.setString(6, inDurasi);
          preparedStatement.executeUpdate();
          showData();
          JOptionPane.showMessageDialog(null, "Data Nota Berhasil Dimasukkan.");
          inputPelanggan.setText("");
          inputPegawai.setText("");
          inputPS.setText("");
          inputTglSewa.setText("");
          inputTglKembali.setText("");
          inputDurasi.setText("");
        }catch (SQLException err) {
          Logger.getLogger(dataInterface.class.getName()).log(Level.SEVERE, null, err);
        }
      }
    });

    //update
    updateBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(dataInterface::createUpdateGUI);
      }
    });

    //delete
    deleteBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(dataInterface::createDeleteGUI);
      }
    });

    //add PS
    addPSBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String inKode, inTipe, inHarga;
        inKode = inKodePS.getText();
        inTipe = inTipePS.getText();
        inHarga = inHargaPS.getText();

        try{
          preparedStatement = connector.ConnectDB().prepareStatement("INSERT INTO tb_ps (id_ps, tipe_ps, harga) VALUES (?,?,?);");
          preparedStatement.setString(1, inKode);
          preparedStatement.setString(2, inTipe);
          preparedStatement.setString(3, inHarga);
          preparedStatement.executeUpdate();
          psShowData();
          JOptionPane.showMessageDialog(null, "Data PS Berhasil Dimasukkan.");
          inKodePS.setText("");
          inTipePS.setText("");
          inHargaPS.setText("");
        } catch (SQLException err) {
          Logger.getLogger(dataInterface.class.getName()).log(Level.SEVERE, null, err);
        }
      }
    });

    //update ps
    updatePSBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(dataInterface::createUpdatePSGUI);
      }
    });

    //delete ps
    delPSBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(dataInterface::createDelPSGUI);
      }
    });
  }

  public JPanel getMainPanel() {
    showData();
    psShowData();
    return mainPanel;
  }
  private DefaultTableModel tableModel;
  private DefaultTableModel psTableModel;
  private ResultSet resultSet;
  private PreparedStatement preparedStatement;
  private void psShowData() {
    try {
      Object[] kolomTitel = {
              "Kode PS",
              "Tipe PS",
              "Harga PS"
      };
      psTableModel = new DefaultTableModel(null, kolomTitel);
      psTable.setModel(psTableModel);

      Connection connection = connector.ConnectDB();
      Statement statement = connection.createStatement();
      psTableModel.getDataVector().removeAllElements();

      resultSet = statement.executeQuery("SELECT * FROM tb_ps");
      while (resultSet.next()) {
        Object[] data = {
                resultSet.getString("id_ps"),
                resultSet.getString("tipe_ps"),
                resultSet.getString("harga")
        };
      psTableModel.addRow(data);
      }
    } catch (SQLException err) {
        throw new RuntimeException(err);
    }
  }
  private void showData() {
    try {
      Object[] columnTitle = {
              "Nota ID",
              "Nama Pelanggan",
              "Nama Pegawai",
              "Kode PS",
              "Tipe PS",
              "Tanggal Sewa",
              "Tanggal Kembali",
              "Durasi Sewa(hari)",
              "Total Biaya"
      };
      tableModel = new DefaultTableModel(null, columnTitle);
      riwayatTable.setModel(tableModel);

      //ambil koneksi DB
      Connection connection = connector.ConnectDB();
      Statement statement = connection.createStatement();
      tableModel.getDataVector().removeAllElements();

      //inisiasi result dengan query
      resultSet = statement.executeQuery(
              "SELECT tb_nota.id_nota, tb_nota.nama_pelanggan, tb_nota.nama_pegawai, tb_ps.id_ps,tb_ps.tipe_ps, tb_nota.tgl_sewa, tb_nota.tgl_kembali, tb_nota.durasi, tb_nota.durasi * tb_ps.harga AS total_harga FROM tb_nota JOIN tb_ps ON tb_nota.id_ps = tb_ps.id_ps;");
      while (resultSet.next()) {
        Object[] data = {
                resultSet.getString("id_nota"),
                resultSet.getString("nama_Pelanggan"),
                resultSet.getString("nama_pegawai"),
                resultSet.getString("id_ps"),
                resultSet.getString("tipe_ps"),
                resultSet.getString("tgl_sewa"),
                resultSet.getString("tgl_kembali"),
                resultSet.getString("durasi"),
                resultSet.getString("total_harga")
        };
        tableModel.addRow(data);
      }
    }catch (SQLException err) {
      throw new RuntimeException(err);
    }
  }

  //Create Update GUI
  private static void createUpdateGUI() {
    updatePanel updateUI = new updatePanel();
    JPanel updateRoot = updateUI.getUpdatePanel();

    JFrame jFrame = new JFrame();
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jFrame.setContentPane(updateRoot);
    jFrame.pack();
    jFrame.setLocationRelativeTo(null);
    jFrame.setVisible(true);
  }

  //create Delete GUI
  public static void createDeleteGUI() {
    deletePanel deleteUI = new deletePanel();
    JPanel deleteRoot = deleteUI.getDeletePanel();

    JFrame jFrame = new JFrame();
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jFrame.setContentPane(deleteRoot);
    jFrame.pack();
    jFrame.setLocationRelativeTo(null);
    jFrame.setVisible(true);
  }

  //create updatePS GUI
  public static void createUpdatePSGUI() {
    psUpdatePanel psUpdateUI = new psUpdatePanel();
    JPanel psUpdateRoot = psUpdateUI.getPSUpdatePanel();

    JFrame jFrame = new JFrame();
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jFrame.setContentPane(psUpdateRoot);
    jFrame.pack();
    jFrame.setLocationRelativeTo(null);
    jFrame.setVisible(true);
  }

  //create delPS GUI
  public static void createDelPSGUI() {
    psDeletePanel psDeleteUI = new psDeletePanel();
    JPanel psDeleteRoot = psDeleteUI.getPsDelPanel();

    JFrame jFrame = new JFrame();
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jFrame.setContentPane(psDeleteRoot);
    jFrame.pack();
    jFrame.setLocationRelativeTo(null);
    jFrame.setVisible(true);
  }
  private JPanel mainPanel;
  private JTabbedPane tabPane;
  private JPanel notaPanel;
  private JPanel psPanel;
  private JPanel inputPanel;
  private JPanel riwayatPanel;
  private JLabel notaLabel;
  private JLabel riwayatLabel;
  private JLabel titleLabel;
  private JScrollPane riwayatScrollPane;
  private JTextField inputPelanggan;
  private JTextField inputPegawai;
  private JTextField inputPS;
  private JTextField inputTglSewa;
  private JTextField inputTglKembali;
  private JTextField inputDurasi;
  private JPanel buttonPanel;
  private JButton updateBtn;
  private JButton addBtn;
  private JButton deleteBtn;
  private JTable riwayatTable;
  private JLabel namaPelangganLabel;
  private JLabel tanggalSewaLabel;
  private JLabel tanggalKembaliLabel;
  private JLabel durasiLabel;
  private JPanel addPSPanel;
  private JPanel psBtnPanel;
  private JPanel tabelPSPanel;
  private JTable psTable;
  private JLabel addPSTitle;
  private JTextField inTipePS;
  private JLabel Tipe;
  private JTextField inHargaPS;
  private JLabel hargaPSTitle;
  private JButton addPSBtn;
  private JButton updatePSBtn;
  private JButton delPSBtn;
  private JTextField inKodePS;
}
