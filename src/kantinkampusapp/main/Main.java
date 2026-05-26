package kantinkampusapp.main;

import kantinkampusapp.database.Koneksi;
import kantinkampusapp.view.LoginView;

public class Main {

    public static void main(String[] args) {

        Koneksi.getKoneksi();
        
        new LoginView().setVisible(true);

    }
}