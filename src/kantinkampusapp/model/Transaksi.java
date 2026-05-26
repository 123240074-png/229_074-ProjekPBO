package kantinkampusapp.model;

import kantinkampusapp.database.Koneksi;
import java.sql.*;

/**
 * Model untuk data Transaksi.
 * BAB 2, 3: Class, Encapsulation, Inheritance
 */
public class Transaksi extends BaseModel implements Saveable {

    private String tanggal;
    private int total;

    public Transaksi() {}

    public Transaksi(String tanggal, int total) {
        this.tanggal = tanggal;
        this.total   = total;
    }

    public String getTanggal() { return tanggal; }
    public void setTanggal(String tanggal) { this.tanggal = tanggal; }

    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }

    @Override
    public String toDisplayString() {
        return "Transaksi #" + id + " | Total: Rp " + total;
    }

    @Override
    public boolean simpan() {
        try {
            Connection conn = Koneksi.getKoneksi();
            String sql = "INSERT INTO transaksi(tanggal,total) VALUES(NOW(),?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, total);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean hapus() {
        try {
            Connection conn = Koneksi.getKoneksi();
            String sql = "DELETE FROM transaksi WHERE id_transaksi=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Transaksi #" + id;
    }
}
