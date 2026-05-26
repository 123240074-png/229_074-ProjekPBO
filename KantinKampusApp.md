# 🍽️ Kantin Kampus App
### Aplikasi Sistem Kasir Kantin Kampus Berbasis Java Swing

---

## 📋 Deskripsi

Kantin Kampus App adalah aplikasi desktop berbasis Java Swing yang dirancang untuk membantu pengelolaan operasional kantin kampus secara digital. Aplikasi ini menyediakan fitur manajemen menu makanan dan minuman, proses transaksi kasir, pencatatan log aktivitas, serta pembuatan laporan transaksi. Dengan adanya sistem login berbasis role (admin dan kasir), setiap pengguna memiliki akses yang sesuai dengan tanggung jawabnya.

---

## 👥 Anggota Kelompok

| NIM | Nama |
|-----|------|
| 123240074 | Selgy Pradista Risqi |
| 123240229 | Riesky Tanto Firmansyah |

---

## 🗂️ Materi yang Digunakan

| BAB | Materi | Implementasi |
|-----|--------|-------------|
| BAB 1 | Dasar-Dasar Java | Tipe data, variabel, operator, percabangan, perulangan |
| BAB 2 | Class & Anatominya | Class Menu, Transaksi, BaseModel, constructor, method |
| BAB 3 | Lima Pilar OOP | Encapsulation (getter/setter), Inheritance (Menu extends BaseModel), Overloading (constructor), Overriding (toString, toDisplayString) |
| BAB 4 | Abstract Class & Interface | BaseModel (abstract class), Saveable (interface) |
| BAB 5 | Exception | StokTidakCukupException, InputTidakValidException (custom exception) |
| BAB 6 | Multithreading | LoadingThread untuk animasi loading saat proses berlangsung |
| BAB 7 | Komponen Swing & Layout | JFrame, JTable, JComboBox, JButton, JTextField, GroupLayout |
| BAB 8 | Event Handling & Sistem Menu | ActionListener, MouseListener pada semua tombol dan tabel |
| BAB 9 | Stream & File | FileLogger untuk menulis dan membaca log transaksi ke file .txt |
| BAB 10 | Database by Java | Koneksi MySQL via JDBC, operasi CRUD menu dan transaksi |
| BAB 11 | Java Reporting | LaporanTransaksi untuk mencetak laporan transaksi ke file .txt |
| BAB 12 | MVC | MenuController, KasirController sebagai Controller dalam pola MVC |

---

## 🏗️ Struktur Package

```
src/kantinkampusapp/
├── main/
│   └── Main.java
├── database/
│   └── Koneksi.java
├── model/
│   ├── BaseModel.java
│   ├── Saveable.java
│   ├── Menu.java
│   └── Transaksi.java
├── exception/
│   ├── StokTidakCukupException.java
│   └── InputTidakValidException.java
├── controller/
│   ├── MenuController.java
│   └── KasirController.java
├── util/
│   ├── FileLogger.java
│   └── LoadingThread.java
├── report/
│   └── LaporanTransaksi.java
└── view/
    ├── LoginView.java
    ├── DashboardView.java
    ├── MenuView.java
    └── KasirView.java
```

---

## 🗄️ Skema Database (DDL)

```sql
CREATE DATABASE kantin_kampus CHARACTER SET utf8mb4;
USE kantin_kampus;

CREATE TABLE user (
    id_user  INT         NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    role     VARCHAR(20) NOT NULL DEFAULT 'kasir',
    PRIMARY KEY (id_user)
);

CREATE TABLE menu (
    id_menu   INT          NOT NULL AUTO_INCREMENT,
    nama_menu VARCHAR(100) NOT NULL,
    harga     INT          NOT NULL,
    stok      INT          NOT NULL DEFAULT 0,
    PRIMARY KEY (id_menu)
);

CREATE TABLE transaksi (
    id_transaksi INT      NOT NULL AUTO_INCREMENT,
    tanggal      DATETIME NOT NULL,
    total        INT      NOT NULL,
    PRIMARY KEY (id_transaksi)
);

CREATE TABLE detail_transaksi (
    id_detail    INT          NOT NULL AUTO_INCREMENT,
    id_transaksi INT          NOT NULL,
    menu         VARCHAR(100) NOT NULL,
    harga        VARCHAR(20)  NOT NULL,
    jumlah       VARCHAR(10)  NOT NULL,
    subtotal     VARCHAR(20)  NOT NULL,
    PRIMARY KEY (id_detail),
    FOREIGN KEY (id_transaksi) REFERENCES transaksi(id_transaksi)
);

INSERT INTO user(username, password, role) VALUES
('admin', 'admin123', 'admin'),
('kasir', 'kasir123', 'kasir');
```

---

## ⚙️ Cara Menjalankan Aplikasi

1. Buat database MySQL dengan DDL di atas
2. Edit `Koneksi.java` sesuaikan username & password MySQL
3. Buka project di NetBeans
4. Tekan F6 untuk menjalankan
5. Login dengan:
   - Admin: username `admin` / password `admin123`
   - Kasir: username `kasir` / password `kasir123`

---

## ✨ Fitur Aplikasi

- Login dengan role Admin dan Kasir
- Manajemen Menu (CRUD) — khusus Admin
- Proses Transaksi Kasir
- Laporan Transaksi ke file .txt
- Laporan Menu Terlaris
- Log Aktivitas otomatis ke file log_kantin.txt
