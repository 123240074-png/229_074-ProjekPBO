package kantinkampusapp.report;

import kantinkampusapp.database.Koneksi;
import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Kelas untuk membuat laporan transaksi.
 * BAB 11: Java Reporting
 * BAB 9: Stream & File
 */
public class LaporanTransaksi {

    /**
     * Cetak laporan semua transaksi ke file TXT.
     * BAB 11: Reporting
     */
    public static String cetakLaporan() {
        String namaFile = "laporan_transaksi_"
            + LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
            + ".txt";

        try (PrintWriter pw = new PrintWriter(
                new BufferedWriter(new FileWriter(namaFile)))) {

            pw.println("============================================");
            pw.println("    LAPORAN TRANSAKSI KANTIN KAMPUS         ");
            pw.println("============================================");
            pw.println("Tanggal Cetak: " + LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
            pw.println("--------------------------------------------");

            Connection conn = Koneksi.getKoneksi();
            String sql =
                "SELECT * FROM transaksi ORDER BY id_transaksi DESC";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            int totalKeseluruhan = 0;
            int jumlahTransaksi  = 0;

            while (rs.next()) {
                int    idTr    = rs.getInt("id_transaksi");
                String tgl     = rs.getString("tanggal");
                int    total   = rs.getInt("total");

                pw.println("\nTransaksi #" + idTr);
                pw.println("Tanggal   : " + tgl);

                // Detail transaksi
                String sqlDetail =
                    "SELECT * FROM detail_transaksi WHERE id_transaksi=?";
                PreparedStatement pstD = conn.prepareStatement(sqlDetail);
                pstD.setInt(1, idTr);
                ResultSet rsD = pstD.executeQuery();

                pw.println("Detail    :");
                while (rsD.next()) {
                    pw.println("  - " + rsD.getString("menu")
                        + " x" + rsD.getString("jumlah")
                        + " = Rp " + rsD.getString("subtotal"));
                }

                pw.println("Total     : Rp " + total);
                pw.println("--------------------------------------------");

                totalKeseluruhan += total;
                jumlahTransaksi++;
            }

            pw.println("\nJumlah Transaksi : " + jumlahTransaksi);
            pw.println("Total Keseluruhan: Rp " + totalKeseluruhan);
            pw.println("============================================");

            return namaFile;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Laporan menu terlaris.
     */
    public static String laporanMenuTerlaris() {
        String namaFile = "laporan_terlaris_"
            + LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
            + ".txt";

        try (PrintWriter pw = new PrintWriter(
                new BufferedWriter(new FileWriter(namaFile)))) {

            pw.println("============================================");
            pw.println("       LAPORAN MENU TERLARIS                ");
            pw.println("============================================");

            Connection conn = Koneksi.getKoneksi();
            String sql =
                "SELECT menu, SUM(jumlah) as total_terjual " +
                "FROM detail_transaksi " +
                "GROUP BY menu ORDER BY total_terjual DESC";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            int rank = 1;
            while (rs.next()) {
                pw.println(rank + ". "
                    + rs.getString("menu")
                    + " - Terjual: "
                    + rs.getInt("total_terjual") + " porsi");
                rank++;
            }

            pw.println("============================================");
            return namaFile;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
