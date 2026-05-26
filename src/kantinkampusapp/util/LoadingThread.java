package kantinkampusapp.util;

import javax.swing.*;

/**
 * Thread untuk animasi loading.
 * BAB 6: Multithreading
 */
public class LoadingThread extends Thread {

    private JLabel lblStatus;
    private boolean berjalan = true;

    public LoadingThread(JLabel lblStatus) {
        this.lblStatus = lblStatus;
    }

    /**
     * BAB 6: Override run() dari Thread
     */
    @Override
    public void run() {
        String[] animasi = {"Memproses.", "Memproses..", "Memproses..."};
        int i = 0;
        while (berjalan) {
            final String teks = animasi[i % 3];
            // BAB 6: Update UI dari thread terpisah
            SwingUtilities.invokeLater(() -> {
                if (lblStatus != null) lblStatus.setText(teks);
            });
            i++;
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                berjalan = false;
            }
        }
    }

    public void hentikan() {
        berjalan = false;
        this.interrupt();
    }
}
