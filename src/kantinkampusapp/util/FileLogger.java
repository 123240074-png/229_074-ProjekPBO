package kantinkampusapp.util;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

/**
 * Utility untuk mencatat log ke file.
 * BAB 9: Stream & File
 */
public class FileLogger {

    private static final String LOG_FILE = "log_kantin.txt";

    /**
     * Tulis log ke file.
     * BAB 9: FileWriter, BufferedWriter
     */
    public static void tulisLog(String pesan) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(LOG_FILE, true))) {

            String waktu = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
            );
            writer.write("[" + waktu + "] " + pesan);
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Gagal menulis log: " + e.getMessage());
        }
    }

    /**
     * Baca semua isi log dari file.
     * BAB 9: Stream & Files
     */
    public static String bacaLog() {
        StringBuilder sb = new StringBuilder();
        try {
            Path path = Paths.get(LOG_FILE);
            if (!Files.exists(path)) {
                return "File log belum ada.";
            }
            Stream<String> lines = Files.lines(path);
            lines.forEach(line -> sb.append(line).append("\n"));
            lines.close();
        } catch (IOException e) {
            return "Gagal membaca log: " + e.getMessage();
        }
        return sb.toString();
    }

    public static void logTransaksiSimpan(int total) {
        tulisLog("TRANSAKSI DISIMPAN - Total: Rp " + total);
    }

    public static void logMenuTambah(String namaMenu) {
        tulisLog("MENU DITAMBAH - Nama: " + namaMenu);
    }

    public static void logMenuHapus(String namaMenu) {
        tulisLog("MENU DIHAPUS - Nama: " + namaMenu);
    }
}
