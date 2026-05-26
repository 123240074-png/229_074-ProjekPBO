package kantinkampusapp.controller;

import kantinkampusapp.database.Koneksi;
import kantinkampusapp.exception.InputTidakValidException;
import kantinkampusapp.model.Menu;
import kantinkampusapp.util.FileLogger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller untuk operasi Menu.
 * BAB 12: MVC Pattern
 */
public class MenuController {

    /**
     * Ambil semua data menu dari database.
     */
    public List<Menu> getAllMenu() {
        List<Menu> list = new ArrayList<>();
        try {
            Connection conn = Koneksi.getKoneksi();
            String sql = "SELECT * FROM menu";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new Menu(
                    rs.getInt("id_menu"),
                    rs.getString("nama_menu"),
                    rs.getInt("harga"),
                    rs.getInt("stok")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Tambah menu baru.
     * BAB 5: Custom Exception
     */
    public void tambahMenu(String nama, String hargaStr, String stokStr)
            throws InputTidakValidException {

        if (nama == null || nama.trim().isEmpty()) {
            throw new InputTidakValidException(
                "Nama Menu", "Nama menu tidak boleh kosong!");
        }

        int harga, stok;
        try {
            harga = Integer.parseInt(hargaStr);
        } catch (NumberFormatException e) {
            throw new InputTidakValidException("Harga", "Harga harus berupa angka!");
        }
        try {
            stok = Integer.parseInt(stokStr);
        } catch (NumberFormatException e) {
            throw new InputTidakValidException("Stok", "Stok harus berupa angka!");
        }

        if (harga <= 0) {
            throw new InputTidakValidException("Harga", "Harga harus lebih dari 0!");
        }
        if (stok < 0) {
            throw new InputTidakValidException("Stok", "Stok tidak boleh negatif!");
        }

        Menu menu = new Menu(nama.trim(), harga, stok);
        menu.simpan();
        FileLogger.logMenuTambah(nama);
    }

    /**
     * Update data menu.
     */
    public void updateMenu(String id, String nama, String hargaStr, String stokStr)
            throws InputTidakValidException, SQLException {

        if (nama == null || nama.trim().isEmpty()) {
            throw new InputTidakValidException(
                "Nama Menu", "Nama menu tidak boleh kosong!");
        }

        int harga, stok;
        try {
            harga = Integer.parseInt(hargaStr);
        } catch (NumberFormatException e) {
            throw new InputTidakValidException("Harga", "Harga harus berupa angka!");
        }
        try {
            stok = Integer.parseInt(stokStr);
        } catch (NumberFormatException e) {
            throw new InputTidakValidException("Stok", "Stok harus berupa angka!");
        }

        Connection conn = Koneksi.getKoneksi();
        String sql = "UPDATE menu SET nama_menu=?,harga=?,stok=? WHERE id_menu=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, nama.trim());
        pst.setInt(2, harga);
        pst.setInt(3, stok);
        pst.setString(4, id);
        pst.executeUpdate();
    }

    /**
     * Hapus menu.
     */
    public void hapusMenu(String id) throws SQLException {
        Connection conn = Koneksi.getKoneksi();
        String sql = "DELETE FROM menu WHERE id_menu=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, id);
        pst.executeUpdate();
    }
}
