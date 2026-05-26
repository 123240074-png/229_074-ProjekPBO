package kantinkampusapp.exception;

/**
 * Custom Exception untuk validasi input.
 * BAB 5: Exception
 */
public class InputTidakValidException extends Exception {

    private String namaField;

    public InputTidakValidException(String namaField, String pesan) {
        super(pesan);
        this.namaField = namaField;
    }

    public String getNamaField() { return namaField; }
}
