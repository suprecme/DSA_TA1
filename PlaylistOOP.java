/*
 * Group 1 - Data Structure and Algorithm Analysis
 * Anggota Kelompok:
 * 1. AGUSTINA ROMAULI SINAGA // 2902786144
 * 2. FACHRYZAL AMRI // 2802542653
 * 3. HANIF AHMAD NURMAAJID // 2502080345
 * 4. KUKUH CHIHO MEHAGA BUKIT // 2802608132
 * 5. RAHMA SETIANINGSIH // 2902791371
 *
 * Tugas TA1: PlaylistOOP.java
 */

import java.util.Scanner;

// Class Lagu digunakan untuk merepresentasikan data setiap lagu
class Lagu {
    private String judul;
    private String artis;
    private double durasi;

    // Constructor digunakan untuk mengisi data awal objek Lagu
    public Lagu(String judul, String artis, double durasi) {
        this.judul = judul;
        this.artis = artis;
        this.durasi = durasi;
    }

    // Getter untuk mengambil judul lagu
    public String getJudul() {
        return judul;
    }
    // Setter untuk mengubah judul lagu
    public void setJudul(String judul) {
        this.judul = judul;
    }

    // Getter untuk mengambil nama artis
    public String getArtis() {
        return artis;
    }
    // Setter untuk mengubah nama artis
    public void setArtis(String artis) {
        this.artis = artis;
    }

    // Getter untuk mengambil durasi lagu
    public double getDurasi() {
        return durasi;
    }
    // Setter untuk mengubah durasi lagu
    public void setDurasi(double durasi) {
        this.durasi = durasi;
    }

    // Method ini digunakan untuk menampilkan informasi lengkap dari satu lagu
    public void tampilkanInfo() {
        System.out.println("Judul  : " + judul);
        System.out.println("Artis  : " + artis);
        System.out.println("Durasi : " + durasi + " menit");
        System.out.println();
    }
}

// Class User sebagai parent class untuk Admin dan Member
class User {
    protected String nama;

    // Constructor untuk mengisi nama user
    public User(String nama) {
        this.nama = nama;
    }

    // Method ini akan dioverride oleh class Admin dan Member
    public void tampilkanAkses() {
        System.out.println("User memiliki akses umum.");
    }
}

// Class Admin mewarisi class User
class Admin extends User {

    // Constructor Admin memanggil constructor milik parent class User
    public Admin(String nama) {
        super(nama);
    }

    // Method ini menunjukkan polymorphism karena isi method berbeda dari parent class
    @Override
    public void tampilkanAkses() {
        System.out.println("Akses Admin: dapat menambahkan lagu dan melihat daftar lagu.");
    }

    // Method ini digunakan Admin untuk menambahkan lagu baru ke dalam array playlist
    public int tambahLagu(Lagu[] playlist, int jumlahLagu, Scanner input) {
        // Mengecek apakah array playlist masih memiliki ruang kosong
        if (jumlahLagu < playlist.length) {
            System.out.print("Masukkan judul lagu: ");
            String judul = input.nextLine();

            System.out.print("Masukkan nama artis: ");
            String artis = input.nextLine();

            System.out.print("Masukkan durasi lagu: ");
            double durasi = input.nextDouble();
            input.nextLine();

            // Membuat objek Lagu baru dan menyimpannya ke indeks array berikutnya
            playlist[jumlahLagu] = new Lagu(judul, artis, durasi);

            // Menambah jumlah lagu setelah data berhasil dimasukkan
            jumlahLagu++;

            System.out.println("Lagu berhasil ditambahkan!");
        } else {
            // Jika array sudah penuh, lagu tidak bisa ditambahkan
            System.out.println("Playlist penuh, lagu tidak dapat ditambahkan.");
        }

        // Mengembalikan jumlah lagu terbaru
        return jumlahLagu;
    }

    // Method ini digunakan Admin untuk melihat seluruh data lagu dalam playlist
    public void lihatDaftarLagu(Lagu[] playlist, int jumlahLagu) {
        // Mengecek apakah playlist masih kosong
        if (jumlahLagu == 0) {
            System.out.println("Playlist masih kosong.");
        } else {
            System.out.println("\n=== Daftar Lagu ===");

            // Loop digunakan untuk menampilkan semua lagu yang tersimpan di array
            for (int i = 0; i < jumlahLagu; i++) {
                System.out.println("Lagu ke-" + (i + 1));
                playlist[i].tampilkanInfo();
            }
        }
    }
}

// Class Member mewarisi class User
class Member extends User {

    // Constructor Member memanggil constructor milik parent class User
    public Member(String nama) {
        super(nama);
    }

    // Method ini menunjukkan polymorphism karena isi method berbeda dari Admin
    @Override
    public void tampilkanAkses() {
        System.out.println("Akses Member: dapat melihat, mencari lagu, dan menghitung rata-rata durasi.");
    }

    // Method ini digunakan Member untuk melihat seluruh daftar lagu
    public void lihatDaftarLagu(Lagu[] playlist, int jumlahLagu) {
        // Mengecek apakah playlist masih kosong
        if (jumlahLagu == 0) {
            System.out.println("Playlist masih kosong.");
        } else {
            System.out.println("\n=== Daftar Lagu ===");

            // Loop digunakan untuk menampilkan data lagu satu per satu
            for (int i = 0; i < jumlahLagu; i++) {
                System.out.println("Lagu ke-" + (i + 1));
                playlist[i].tampilkanInfo();
            }
        }
    }

    // Method ini digunakan Member untuk mencari lagu berdasarkan judul
    public void cariLagu(Lagu[] playlist, int jumlahLagu, Scanner input) {
        System.out.print("Masukkan judul lagu yang dicari: ");
        String judulCari = input.nextLine();

        boolean ditemukan = false;

        // Loop digunakan untuk mengecek setiap lagu dalam array
        for (int i = 0; i < jumlahLagu; i++) {
            // equalsIgnoreCase digunakan agar pencarian tidak sensitif huruf besar/kecil
            if (playlist[i].getJudul().equalsIgnoreCase(judulCari)) {
                System.out.println("\nLagu ditemukan:");
                playlist[i].tampilkanInfo();
                ditemukan = true;
                break;
            }
        }

        // Jika tidak ada judul yang cocok, tampilkan pesan tidak ditemukan
        if (!ditemukan) {
            System.out.println("Lagu tidak ditemukan.");
        }
    }

    // Method ini digunakan Member untuk menghitung rata-rata durasi lagu
    public void hitungRataRataDurasi(Lagu[] playlist, int jumlahLagu) {
        // Jika playlist kosong, rata-rata tidak bisa dihitung
        if (jumlahLagu == 0) {
            System.out.println("Playlist masih kosong.");
        } else {
            double total = 0;

            // Loop digunakan untuk menjumlahkan seluruh durasi lagu
            for (int i = 0; i < jumlahLagu; i++) {
                total += playlist[i].getDurasi();
            }

            // Rata-rata dihitung dari total durasi dibagi jumlah lagu
            double rataRata = total / jumlahLagu;

            System.out.println("Rata-rata durasi lagu: " + rataRata + " menit");
        }
    }
}

// Class utama program
public class PlaylistOOP {
    public static void main(String[] args) {

        // Scanner digunakan untuk menerima input dari pengguna
        Scanner input = new Scanner(System.in);

        // Array digunakan untuk menyimpan kumpulan objek Lagu
        Lagu[] playlist = new Lagu[10];

        // Variabel untuk menghitung jumlah lagu yang sudah tersimpan
        int jumlahLagu = 0;

        // Data awal playlist
        playlist[0] = new Lagu("Beautiful Day", "Coldiac", 2.55);
        playlist[1] = new Lagu("love lesson no. 1", "Skyline", 4.06);
        playlist[2] = new Lagu("Gabriela", "KATSEYE", 3.17);
        jumlahLagu = 3;

        // Membuat objek Admin dan Member
        Admin admin = new Admin("Admin Playlist");
        Member member = new Member("Member Playlist");

        int pilihanRole;

        // Loop menu utama untuk memilih role Admin atau Member
        do {
            System.out.println("=== SISTEM MANAJEMEN PLAYLIST MUSIK: GROUP-01 ===");
            System.out.println("1. Masuk sebagai Admin");
            System.out.println("2. Masuk sebagai Member");
            System.out.println("3. Keluar");
            System.out.print("Silahkan pilih role terlebih dahulu: ");
            pilihanRole = input.nextInt();
            input.nextLine();

            // Switch digunakan untuk menjalankan menu sesuai role yang dipilih
            switch (pilihanRole) {
                case 1:
                    jumlahLagu = menuAdmin(admin, playlist, jumlahLagu, input);
                    break;

                case 2:
                    menuMember(member, playlist, jumlahLagu, input);
                    break;

                case 3:
                    System.out.println("Program selesai.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }

            System.out.println();

        } while (pilihanRole != 3);

        // Menutup Scanner setelah program selesai
        input.close();
    }

    // Method ini berisi menu khusus Admin
    public static int menuAdmin(Admin admin, Lagu[] playlist, int jumlahLagu, Scanner input) {
        int pilihan;

        // Loop akan terus berjalan sampai Admin memilih kembali
        do {
            System.out.println("\n=== MENU ADMIN - SISTEM MANAJEMEN PLAYLIST MUSIK ===");
            admin.tampilkanAkses();
            System.out.println("1. Tambah Lagu");
            System.out.println("2. Lihat Daftar Lagu");
            System.out.println("3. Kembali");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine();

            // Switch digunakan untuk menjalankan fitur sesuai pilihan Admin
            switch (pilihan) {
                case 1:
                    jumlahLagu = admin.tambahLagu(playlist, jumlahLagu, input);
                    break;

                case 2:
                    admin.lihatDaftarLagu(playlist, jumlahLagu);
                    break;

                case 3:
                    System.out.println("Kembali ke menu utama.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }

        } while (pilihan != 3);

        // Mengembalikan jumlah lagu terbaru setelah Admin menambah data
        return jumlahLagu;
    }

    // Method ini berisi menu khusus Member
    public static void menuMember(Member member, Lagu[] playlist, int jumlahLagu, Scanner input) {
        int pilihan;

        // Loop akan terus berjalan sampai Member memilih kembali
        do {
            System.out.println("\n=== MENU MEMBER - SISTEM MANAJEMEN PLAYLIST MUSIK ===");
            member.tampilkanAkses();
            System.out.println("1. Lihat Daftar Lagu");
            System.out.println("2. Cari Lagu Berdasarkan Judul");
            System.out.println("3. Hitung Rata-rata Durasi Lagu");
            System.out.println("4. Kembali");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine();

            // Switch digunakan untuk menjalankan fitur sesuai pilihan Member
            switch (pilihan) {
                case 1:
                    member.lihatDaftarLagu(playlist, jumlahLagu);
                    break;

                case 2:
                    member.cariLagu(playlist, jumlahLagu, input);
                    break;

                case 3:
                    member.hitungRataRataDurasi(playlist, jumlahLagu);
                    break;

                case 4:
                    System.out.println("Kembali ke menu utama.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }

        } while (pilihan != 4);
    }
}