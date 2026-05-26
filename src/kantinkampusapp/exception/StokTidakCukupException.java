package kantinkampusapp.exception;

/**
 * Custom Exception untuk stok tidak cukup.
 * BAB 5: Exception
 */
public class StokTidakCukupException extends Exception {

    private int stokTersedia;
    private int stokDiminta;

    public StokTidakCukupException(int stokTersedia, int stokDiminta) {
        super("Stok tidak cukup! Tersedia: " + stokTersedia
            + ", Diminta: " + stokDiminta);
        this.stokTersedia = stokTersedia;
        this.stokDiminta  = stokDiminta;
    }

    public int getStokTersedia() { return stokTersedia; }
    public int getStokDiminta()  { return stokDiminta;  }
}
