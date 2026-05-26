package kantinkampusapp.model;

import kantinkampusapp.database.Koneksi;
import java.sql.*;

/**
 * Model untuk data Menu.
 * BAB 2: Class & Anatominya
 * BAB 3: Encapsulation, Inheritance, Overriding, Overloading
 * BAB 4: Abstract Class, Interface
 */
public class Menu extends BaseModel implements Saveable {

    // BAB 3: Encapsulation
    private String namaMenu;
    private int harga;
    private int stok;

    // BAB 3: Overloading constructor
    public Menu() {}

    public Menu(String namaMenu, int harga, int stok) {
        this.namaMenu = namaMenu;
        this.harga    = harga;
        this.stok     = stok;
    }

    public Menu(int id, String namaMenu, int harga, int stok) {
        this.id       = id;
        this.namaMenu = namaMenu;
        this.harga    = harga;
        this.stok     = stok;
    }

    // Getter & Setter - BAB 3: Encapsulation
    public String getNamaMenu() { return namaMenu; }
    public void setNamaMenu(String namaMenu) { this.namaMenu = namaMenu; }

    public int getHarga() { return harga; }
    public void setHarga(int harga) { this.harga = harga; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }

    // BAB 4: Implementasi abstract method dari BaseModel
    @Override
    public String toDisplayString() {
        return namaMenu + " - Rp " + harga + " (Stok: " + stok + ")";
    }

    // BAB 4: Implementasi interface Saveable
    @Override
    public boolean simpan() {
        try {
            Connection conn = Koneksi.getKoneksi();
            String sql = "INSERT INTO menu(nama_menu,harga,stok) VALUES(?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, namaMenu);
            pst.setInt(2, harga);
            pst.setInt(3, stok);
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
            String sql = "DELETE FROM menu WHERE id_menu=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    // BAB 3: Overriding toString dari Object
    @Override
    public String toString() {
        return namaMenu;
    }
}
