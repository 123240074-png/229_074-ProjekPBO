package kantinkampusapp.controller;

import kantinkampusapp.database.Koneksi;
import kantinkampusapp.exception.InputTidakValidException;
import kantinkampusapp.exception.StokTidakCukupException;
import kantinkampusapp.util.FileLogger;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

/**
 * Controller untuk operasi Kasir/Transaksi.
 * BAB 12: MVC Pattern
 */
public class KasirController {

    /**
     * Validasi jumlah pesanan vs stok.
     * BAB 5: Custom Exception
     */
    public void validasiPesanan(String jumlahStr, int stok)
            throws InputTidakValidException, StokTidakCukupException {

        if (jumlahStr == null || jumlahStr.trim().isEmpty()) {
            throw new InputTidakValidException(
                "Jumlah", "Jumlah tidak boleh kosong!");
        }

        int jumlah;
        try {
            jumlah = Integer.parseInt(jumlahStr);
        } catch (NumberFormatException e) {
            throw new InputTidakValidException(
                "Jumlah", "Jumlah harus berupa angka!");
        }

        if (jumlah <= 0) {
            throw new InputTidakValidException(
                "Jumlah", "Jumlah harus lebih dari 0!");
        }

        if (jumlah > stok) {
            throw new StokTidakCukupException(stok, jumlah);
        }
    }

    /**
     * Simpan transaksi ke database.
     * BAB 5: Exception Handling
     * BAB 10: Database CRUD
     */
    public void simpanTransaksi(DefaultTableModel model)
            throws InputTidakValidException, SQLException {

        if (model.getRowCount() == 0) {
            throw new InputTidakValidException(
                "Pesanan", "Belum ada pesanan yang ditambahkan!");
        }

        Connection conn = Koneksi.getKoneksi();

        // Hitung total
        int total = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            total += Integer.parseInt(
                model.getValueAt(i, 3).toString()
            );
        }

        // Simpan ke tabel transaksi
        String sqlTransaksi =
            "INSERT INTO transaksi(tanggal,total) VALUES(NOW(),?)";
        PreparedStatement pstTransaksi = conn.prepareStatement(
            sqlTransaksi, Statement.RETURN_GENERATED_KEYS
        );
        pstTransaksi.setInt(1, total);
        pstTransaksi.executeUpdate();

        // Ambil ID transaksi
        ResultSet rs = pstTransaksi.getGeneratedKeys();
        int idTransaksi = 0;
        if (rs.next()) idTransaksi = rs.getInt(1);

        // Simpan detail & update stok
        for (int i = 0; i < model.getRowCount(); i++) {
            String menu     = model.getValueAt(i, 0).toString();
            String harga    = model.getValueAt(i, 1).toString();
            String jumlah   = model.getValueAt(i, 2).toString();
            String subtotal = model.getValueAt(i, 3).toString();

            // Simpan detail transaksi
            String sqlDetail =
                "INSERT INTO detail_transaksi" +
                "(id_transaksi,menu,harga,jumlah,subtotal)" +
                " VALUES(?,?,?,?,?)";
            PreparedStatement pstDetail =
                conn.prepareStatement(sqlDetail);
            pstDetail.setInt(1, idTransaksi);
            pstDetail.setString(2, menu);
            pstDetail.setString(3, harga);
            pstDetail.setString(4, jumlah);
            pstDetail.setString(5, subtotal);
            pstDetail.executeUpdate();

            // Update stok
            String sqlStok =
                "UPDATE menu SET stok=stok-? WHERE nama_menu=?";
            PreparedStatement pstStok =
                conn.prepareStatement(sqlStok);
            pstStok.setInt(1, Integer.parseInt(jumlah));
            pstStok.setString(2, menu);
            pstStok.executeUpdate();
        }

        FileLogger.logTransaksiSimpan(total);
    }
}
