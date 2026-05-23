package kantinkampusapp;

import kantinkampusapp.database.Koneksi;
import kantinkampusapp.view.LoginView;

public class KantinKampusApp {

    public static void main(String[] args) {

        Koneksi.getKoneksi();
        
        new LoginView().setVisible(true);

    }
}