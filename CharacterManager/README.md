# TP3-AP3 Character Manager (Java Visual SIH-6406)
Aplikasi ini adalah sistem manajemen karakter sederhana berbasis teks (Console-based Game Character Manager) yang dibangun menggunakan bahasa pemrograman Java. Proyek ini menerapkan prinsip Pemrograman Berorientasi Objek (OOP), khususnya enkapsulasi (Encapsulation) dengan pembatasan hak akses atribut menggunakan setter, getter, serta fungsi validasi nilai logika. Sistem juga mendukung kalkulasi otomatis status karakter (HP dan Attack) berdasarkan level serta fitur pelacakan kondisi hidup/mati karakter.

## Screenshot Output Terminal TP3-AP3
![alt text](<../Output Image/Screenshot (1).png>)
![alt text](<../Output Image/Screenshot (7).png>)

# TP5-AP6 Fitur Baru: Pencarian Karakter Terkuat (findStrongest)
1. Rasional / Alasan Fitur
Dalam pengembangan sebuah game atau sistem RPG, mengetahui entitas atau karakter mana yang memiliki statistik tertinggi (terkuat) merupakan fitur analisis data yang sangat krusial. Fitur findStrongest() ditambahkan untuk mempermudah sistem dalam menyaring karakter dengan daya serang (attackPower) paling mendominasi tanpa mengharuskan layer aplikasi melakukan pengurutan (sorting) manual di sisi luar.

2. Menjaga Prinsip OOP & Enkapsulasi
Defensive Design: Method mengembalikan satu objek Character tunggal atau nilai null apabila sistem belum memiliki data karakter sama sekali.
No Information Leakage: Struktur data internal ArrayList<Character> characterList milik CharacterManager tetap terkunci rapat di dalam class (private). Akses luar murni hanya mendapatkan hasil kalkulasi/pencarian objek spesifik tanpa diberi celah untuk memodifikasi atau menambah isi list secara ilegal.

## Screenshot Output Terminal TP5-AP6
![alt text](<../Output Image/Screenshot (21).png>)
![alt text](<../Output Image/Screenshot (22).png>)
![alt text](<../Output Image/Screenshot (23).png>)
![alt text](<../Output Image/Screenshot (24).png>)

## Struktur Folder Project

```text
CharacterManager/
├── src/
│   ├── Main.java
│   └── com/
│       └── unpri/
│           └── charactermanager/
│               ├── model/
│               │   ├── Character.java
│               │   └── CharacterType.java
│               │   └── CharacterManager.java
│               └── view/
│                   ├── AppThema.java
│                   └── CharacterFormPanel.java
│                   └── MainWindow.java
└── README.md
```

# TP5-AP6 Fitur Baru: Pencarian Karakter Terkuat (findStrongest)
1. Rasional / Alasan Fitur
Dalam pengembangan sebuah game atau sistem RPG, mengetahui entitas atau karakter mana yang memiliki statistik tertinggi (terkuat) merupakan fitur analisis data yang sangat krusial. Fitur findStrongest() ditambahkan untuk mempermudah sistem dalam menyaring karakter dengan daya serang (attackPower) paling mendominasi tanpa mengharuskan layer aplikasi melakukan pengurutan (sorting) manual di sisi luar.

2. Menjaga Prinsip OOP & Enkapsulasi
Defensive Design: Method mengembalikan satu objek Character tunggal atau nilai null apabila sistem belum memiliki data karakter sama sekali.
No Information Leakage: Struktur data internal ArrayList<Character> characterList milik CharacterManager tetap terkunci rapat di dalam class (private). Akses luar murni hanya mendapatkan hasil kalkulasi/pencarian objek spesifik tanpa diberi celah untuk memodifikasi atau menambah isi list secara ilegal.

## Screenshot Output Terminal TP5-AP6
![alt text](<../Output Image/Screenshot (21).png>)
![alt text](<../Output Image/Screenshot (22).png>)
![alt text](<../Output Image/Screenshot (23).png>)
![alt text](<../Output Image/Screenshot (24).png>)

# TP8-AP10 Character Manager GUI dan Statistik Karakter
1. Aliran Data dan Penerapan MVC
Aliran data aplikasi dimulai dari Main.java, yang membuat objek Character dan menambahkan data karakter ke CharacterManager. Selanjutnya, Main.java mengambil daftar karakter melalui method getAllCharacters() dan mengirimkannya ke MainWindow untuk ditampilkan dalam antarmuka Java Swing. Dalam konsep MVC, package model bertanggung jawab terhadap data dan logika karakter, sedangkan MainWindow berperan sebagai bagian View yang menampilkan data kepada pengguna. Main.java mengatur proses inisialisasi dan menghubungkan Model dengan View, sementara statistik rata-rata level dihitung berdasarkan daftar karakter yang diterima oleh View.

2. Karakter Baru dan Alasan Pemilihan Tipe/Level
Dua karakter baru ditambahkan untuk memenuhi kebutuhan minimal lima karakter sekaligus memberikan variasi tipe dan level pada aplikasi. Srikandi — ARCHER, Level 2: Dipilih sebagai karakter pemanah dengan level awal yang relatif rendah untuk menunjukkan variasi statistik pada karakter yang masih berkembang. Bima — WARRIOR, Level 6: Dipilih sebagai karakter petarung dengan level lebih tinggi untuk memberikan perbandingan statistik dengan karakter berlevel rendah serta membantu menguji perhitungan rata-rata level. Penambahan karakter dengan tipe dan level yang berbeda memungkinkan aplikasi menampilkan data yang lebih bervariasi dan menguji apakah statistik rata-rata level dihitung berdasarkan seluruh karakter secara akurat.

## Screenshot Output Terminal TP8-AP10
![alt text](<../Output Image/Screenshot (26).png>)
![alt text](<../Output Image/Screenshot (27).png>)
![alt text](<../Output Image/Screenshot (29).png>)

# TP10-AP13 Dokumentasi Pembaruan GUI & Manajemen Tema
1. Rasionalisasi Pemisahan Data Warna (AppTheme vs Character)
Penyimpanan data warna dikelola secara terpusat di dalam kelas AppTheme (atau AppThema) dan bukan di dalam kelas Character demi menjaga prinsip Separation of Concerns (Pemisahan Tanggung Jawab) pada arsitektur MVC. Kelas Character murni bertindak sebagai Model yang hanya bertanggung jawab atas logika data dan status internal karakter seperti nama, level, atau poin kesehatan. Jika warna dimasukkan ke dalam kelas Character, hal tersebut akan merusak enkapsulasi karena memaksa komponen Model untuk memahami elemen visual grafis yang merupakan ranah penuh dari komponen View. Dengan pendekatan ini, perubahan tema visual seluruh aplikasi di masa mendatang dapat dilakukan dengan cepat hanya melalui satu file konfigurasi saja tanpa berisiko merusak logika bisnis inti.

2. Daftar Properti Tambahan Visual dan Alasan Pemilihan
Untuk meningkatkan kualitas antarmuka dan pengalaman pengguna (UX), berikut adalah dua properti tambahan yang disematkan ke dalam kartu komponen GUI:

Restriksi Akses Karakter Pemula (setEnabled(false))
Alasan: Properti ini dipilih untuk memberikan indikasi visual yang kuat terhadap status kesiapan karakter. Karakter berlevel 1 dianggap sebagai entitas pemula (trainee) yang belum memiliki kapabilitas bertarung, sehingga komponen visualnya sengaja dinonaktifkan untuk menyimulasikan pembatasan akses interaksi dalam sistem permainan.
Mekanisme Informasi Melayang (Dynamic HTML Tooltip)
Alasan: Properti ini diimplementasikan untuk mempertahankan estetika jendela aplikasi agar tetap bersih, minimalis, dan ringkas. Rincian status sekunder yang padat seperti daya tahan (hitPoints) dan daya serang (attackPower) dipindahkan ke dalam hover tooltip berformat HTML sehingga pengguna tetap dapat meninjau statistik mendalam tanpa membuat tata letak utama menjadi penuh sesak.

## Screenshot Output (Sebelum vs Sesudah)
Sebelum
![alt text](<../Output Image/Screenshot (29).png>)

Sesudah
![alt text](<../Output Image/Screenshot (34).png>)

# TP13-AP16 Dokumentasi Pembaruan GUI, Manajemen Tema & Pengembangan Antarmuka Grafis Terstruktur (v0.5)
Analisis Pemilihan Layout Antarmuka (v0.5)
1. Mengapa GridLayout (2x2) Dipilih untuk Panel Statistik?
Alasan Desain: Panel statistik menyajikan 4 metrik ringkas informasi metadata aplikasi. GridLayout(2, 2) dipilih karena memaksa setiap grid component memiliki ukuran kotak yang seragam secara simetris, menghasilkan visual baris-kolom yang sangat rapi untuk widget data ringkas. Ketika aplikasi dilebarkan secara horizontal, grid akan meregang merata mengikuti dinamika ukuran jendela tanpa merusak struktur baris.
2. Mengapa Absolute Layout (setLayout(null)) Ditolak Keras?
Alasan Penolakan: Absolute Layout mengharuskan penentuan koordinat piksel secara kaku via setBounds(). Desain ini ditolak karena tidak responsif. Ketika pengguna melakukan aksi memperbesar atau memperkecil jendela, komponen di dalam Absolute Layout akan tetap membeku di ukuran asalnya, memicu teks terpotong, atau menyisakan area kosong yang mati (dead space). Hal ini melanggar standardisasi multi-platform GUI architecture.
3. Mengapa FlowLayout Ditolak untuk Penempatan Kartu/Statistik?
Alasan Penolakan: FlowLayout menyusun komponen secara menyamping (horizontal) dan otomatis turun ke bawah seperti baris teks mengetik jika ruang habis. Layout ini ditolak untuk panel daftar utama dan panel statistik karena tidak menjamin keseragaman lebar elemen. Jika dipakai pada daftar karakter, orientasi susunannya akan berantakan ke samping kanan alih-alih berbaris vertikal ke bawah yang konsisten sebagai "Daftar Kartu".
4. Struktur Panel Bersarang (Nested Panel Architecture)
Aplikasi ini dibangun menggunakan arsitektur panel bersarang (nested panels) yang tersusun secara hierarkis di dalam kontainer utama JFrame yang ber-layout BorderLayout. Pada bagian atas (NORTH), terdapat sebuah kontainer panel utama yang membungkus dua panel vertikal sekaligus, yaitu komponen judul label di sisi atas dan panel ringkasan statistik ber-layout GridLayout di sisi bawahnya. Area tengah (CENTER) diisi oleh JScrollPane yang mengisolasi komponen cardPanel ber-layout GridLayout(0, 1), sehingga aliran komponen kartu karakter tersusun vertikal terpisah dari manajemen layout luar. Terakhir, bagian samping (EAST) dan bawah (SOUTH) masing-masing diisi oleh panel legenda (BoxLayout) dan status bar (BorderLayout) sebagai komponen independen yang berdiri sendiri.

5. Alasan Pemilihan Panel Tambahan & Layout yang Ditolak
Panel Tambahan (Stats Panel dengan GridLayout 2x2): Panel statistik ini dipilih untuk mengelompokkan metrik ringkas aplikasi (jumlah karakter, rata-rata level, status, dan karakter terkuat) ke dalam posisi grid yang simetris dan presisi. GridLayout menjamin setiap kotak ringkasan memiliki ukuran lebar dan tinggi yang seragam secara proporsional saat diletakkan di bawah header.
Alasan Penolakan Absolute Layout (setLayout(null)): Layout absolut ditolak karena sifatnya yang kaku dan tidak responsif. Jika menggunakan koordinat piksel tetap (setBounds), komponen tidak akan bisa menyesuaikan diri saat jendela aplikasi di-resize oleh pengguna di Windows, yang mengakibatkan teks terpotong atau menyisakan ruang kosong mati (dead space).
Alasan Penolakan FlowLayout: FlowLayout ditolak untuk panel statistik maupun panel daftar karakter karena sifat alirannya yang menyamping. Jika ruang horizontal menyempit, komponen akan bergeser ke bawah secara tidak beraturan, merusak visual baris-kolom statistik serta mematahkan orientasi vertikal kartu karakter yang seharusnya konsisten berbaris ke bawah.
6. Wireframe & Desain Antarmuka
(Silakan gambar sketsa kasar kotak-kotak BorderLayout atau gunakan alat bantu diagram, lalu simpan ke folder proyek Anda dan tautkan di sini)

7. Tabel Pengamatan Resize Jendela
Berikut adalah analisis komparatif antara prediksi teoritis tata letak layout dan hasil aktual saat jendela aplikasi dimanipulasi ukurannya pada sistem operasi Windows:

Komponen Wilayah	Prediksi Perilaku Layout (Saat Jendela Diresize)	Hasil Aktual Pengamatan Lapangan	Kesesuaian (Sesuai / Tidak)
NORTH (Header & Stats)	Lebar panel akan melebar penuh mengikuti horizontal jendela, tetapi tinggi panel akan tetap konstan sesuai isi komponen internalnya.	Panel statistik meregang mulus ke kanan secara horizontal. Kotak grid 2x2 mempertahankan proporsi tingginya tanpa merusak teks judul di atasnya.	Sesuai
CENTER (Cards List)	Mengisi sisa ruang kosong utama. Saat ditarik melebar, kartu memanjang ke kanan. Saat dikecilkan secara vertikal melewati batas tinggi pref, JScrollPane memunculkan scrollbar.	Kartu ber-GridLayout melebar secara horizontal mengisi ruang tengah. Saat tinggi jendela diperkecil, batang penggulung (vertical scrollbar) otomatis muncul di sisi kanan.	Sesuai
EAST (Legenda Role)	Tinggi panel akan memanjang mengikuti tinggi jendela. Lebar panel akan tetap statis mengikuti ukuran komponen terlebar di dalam BoxLayout.	Komponen label role tetap berada di tengah (CENTER_ALIGNMENT). Box.createVerticalGlue() sukses mendorong sisa ruang kosong ke bawah, menjaga susunan tetap rapi.	Sesuai
SOUTH (Status Bar)	Tetap mengunci posisi di bagian terbawah jendela. Teks sistem akan merapat ke kiri (WEST) dan teks versi "v0.5" akan merapat penuh ke sudut kanan (EAST).	Panel status bar tetap berada di dasar frame. Teks info dan versi terpisah secara otomatis ke ujung kiri dan kanan berkat sifat pembagian wilayah BorderLayout.

## Screenshot Output Terminal ap16
Ukuran Awal (Default Window Size):
![alt text](<../Output Image/Screenshot (47).png>)

Jendela Dilebarkan (Maximized / Resized Large):
![alt text](<../Output Image/Screenshot (48).png>)

Jendela Dikecilkan (Minimized / Vertikal Scrollbar Aktif):
![alt text](<../Output Image/Screenshot (49).png>)

# TP14-AP19 Dokumentasi Form Input, Validasi Berlapis & Pengujian Interaktif
## 1. Screenshot Hasil Pengujian Form GUI
1. **Validasi Error Nama Kosong (Border Merah & Fokus Kembali):**
   ![Error Nama Kosong](<../Output Image/Screenshot_TP14_Error_Kosong.png>)

2. **Validasi Error Nama Duplikat (Pengecekan Keunikan via `isNameTaken()`):**
   ![Error Nama Duplikat](<../Output Image/Screenshot_TP14_Error_Duplikat.png>)

3. **Sukses Menambahkan Karakter Baru (Kartu Muncul & Statistik Ter-update):**
   ![Sukses Tambah Karakter](<../Output Image/Screenshot_TP14_Sukses.png>)

---

## 2. Mekanisme Mini-Challenge & Aturan Validasi Baru
* **Mini-Challenge (Batasan Level `MAGE` Maksimal 60 — Lapisan Model):**
  Aturan bahwa karakter bertipe `MAGE` tidak boleh memiliki level di atas 60 diletakkan sepenuhnya pada konstruktor dan `setLevel()` di kelas `Character`. Ketika kondisi dilanggar, Model melempar `IllegalArgumentException` yang langsung ditangkap oleh blok `try-catch` pada `CharacterFormPanel` untuk ditampilkan di label error tanpa perlu mengubah struktur kode form.
* **Aturan Tambahan 1 — Larangan Kata Terlarang Sistem (`Admin`, `System`, `GM`) di Model (`Character.java`):**
  *Alasan:* Aturan ini menjaga integritas data domain agar tidak ada karakter yang menyerupai identitas pengelola sistem, baik saat dibuat lewat antarmuka GUI maupun lewat kode backend.
* **Aturan Tambahan 2 — Batas Maksimal Kapasitas Daftar 20 Karakter di Model (`CharacterManager.java`):**
  *Alasan:* Pengelolaan batas ukuran koleksi (`characterList`) merupakan tanggung jawab langsung pengelola data (`CharacterManager`), sehingga pembatasan slot tetap konsisten dari mana pun method `addCharacter()` dipanggil.

---

## 3. Tabel Pengujian Validasi (Bagian B) & Catatan Perbaikan

| No | Input (Nama, Role, Level) | Hasil Diharapkan | Hasil Nyata | Lapisan yang Menangkap | Catatan Perbedaan & Tindakan Perbaikan |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 1 | `""` (kosong), `WARRIOR`, `5` | Ditolak dengan pesan nama wajib diisi, border merah, fokus ke field nama | Muncul `⚠ Nama karakter wajib diisi!`, border merah, fokus kembali ke field nama | **View** (`CharacterFormPanel.validateName`) | Sesuai harapan. Tidak diperlukan perbaikan. |
| 2 | `"Al"`, `ARCHER`, `10` | Ditolak karena panjang nama kurang dari 3 karakter | Muncul `⚠ Panjang nama harus antara 3 hingga 20 karakter!` | **View** (`CharacterFormPanel.validateName`) | Sesuai harapan. Tidak diperlukan perbaikan. |
| 3 | `"Srikandi@99"`, `HEALER`, `12` | Ditolak karena mengandung simbol `@` | Muncul `⚠ Format nama hanya boleh berisi huruf, angka, spasi, atau underscore!` | **View** (`CharacterFormPanel.validateName`) | Sesuai harapan. Tidak diperlukan perbaikan. |
| 4 | `"Arjuna"`, `WARRIOR`, `15` | Ditolak karena nama sudah terdaftar (cek *case-insensitive*) | Muncul `⚠ Nama 'Arjuna' sudah digunakan oleh karakter lain!` | **View** (`CharacterFormPanel` + `isNameTaken`) | Awalnya pengecekan memakai `equals()` yang sensitif huruf besar/kecil sehingga `"arjuna"` sempat lolos; diperbaiki dengan `equalsIgnoreCase()`. |
| 5 | `"Gandalf"`, `MAGE`, `61` | Ditolak oleh Model karena MAGE maksimal level 60 | Muncul `⚠ Role MAGE tidak boleh memiliki level di atas 60!` | **Model** (`Character` Constructor) | Sesuai harapan. Pesan Model otomatis tampil di form tanpa mengubah `CharacterFormPanel`. |
| 6 | `"Gandalf"`, `MAGE`, `60` | Diterima karena tepat di batas maksimal level MAGE (`60`) | Karakter tampil di daftar kartu, statistik ter-update, form ter-reset | **Lolos Validasi** (View & Model) | Sesuai harapan. Tidak diperlukan perbaikan. |
| 7 | `"Admin"`, `WARRIOR`, `25` | Ditolak karena menggunakan nama cadangan sistem | Muncul `⚠ Nama 'Admin' dicadangkan oleh sistem!` | **Model** (`Character` Constructor) | Sesuai harapan. Tidak diperlukan perbaikan. |
| 8 | `"  Srikandi  "`, `ARCHER`, `20` | Spasi di awal/akhir dipangkas (`trim()`), karakter diterima | Tersimpan sebagai `"Srikandi"` di daftar dan rata-rata level diperbarui | **Lolos Validasi** (View & Model) | Sesuai harapan. Tidak diperlukan perbaikan. |
| 9 | `"Ksatria21"`, `WARRIOR`, `50` (saat list berisi 20 karakter) | Ditolak karena kapasitas daftar penuh (maksimal 20) | Muncul `⚠ Kapasitas daftar penuh! Maksimal 20 karakter.` | **Model** (`CharacterManager.addCharacter`) | Sesuai harapan. Tidak diperlukan perbaikan. |
| 10 | `"Budi"`, `MAGE`, `150` (diketik manual pada `JSpinner`) | Ditolak karena level di luar rentang `1–99` | Muncul `⚠ Level tidak valid! Masukkan angka bulat 1–99.` | **View** (`JSpinner.commitEdit`) | Sebelumnya nilai ketikan manual pada spinner tidak terbaca; diperbaiki dengan memanggil `spnLevel.commitEdit()` sebelum mengambil nilai. |

---

## 4. Analisis Kompromi Arsitektur Sementara (MVC)
Pada tahap pengembangan ini, penanganan aksi tombol pada `CharacterFormPanel` masih menggunakan `ActionListener` minimal berbasis lambda yang langsung memanggil `manager.addCharacter()` serta callback `Runnable` untuk memicu `refreshList()` di `MainWindow`. Pendekatan ini merupakan kompromi sementara agar integrasi antara form input dan pembaruan tampilan dapat berjalan ringkas tanpa menambah lapisan kelas Controller terpisah. Meskipun secara fungsional efektif, pemanggilan `CharacterManager` secara langsung dari dalam komponen View membuat `CharacterFormPanel` memiliki ketergantungan (*coupling*) terhadap struktur Model. Pada tahap pengembangan berikutnya, logika alur penambahan dan pembaruan daftar ini idealnya dipindahkan ke kelas Controller khusus agar pemisahan tanggung jawab (*Separation of Concerns*) dalam pola MVC benar-benar murni.

## Screenshot Output Terminal ap19
![alt text](<../Output Image/Screenshot (70).png>)
![alt text](<../Output Image/Screenshot (71).png>)

Identitas Mahasiswa
Nama Lengkap: Yugas Bintang Bimantara harefa
NIM: 253303621229
Link Google Docs ap3: https://docs.google.com/document/d/13VmEHhD6g9jfoNQVd79ebY7AujzoQrAAqYsLhu1JZDE/edit?usp=sharing
Link Google Docs ap6: https://docs.google.com/document/d/1-aIZ3DACbzdOY9E0Q-ihezBbM8uxdzWK4TRoRQpNqqI/edit?usp=sharing
Link Google Docs ap10: https://docs.google.com/document/d/1vV4PF-lDXRdZbCN6OllHVbcmdEmTuXkokU2j73zBoT0/edit?usp=sharing
Link Google Docs ap13: https://docs.google.com/document/d/1EY9-Iq5pRXuauOWAT54PxH7yV7AS5vSO60K-7qbM4_M/edit?usp=sharing
Link Google Docs ap16: https://docs.google.com/document/d/1JgWPDFcWPl2-3WdPWqxeYHpDCuINMZsC-7nUXxxvOpE/edit?usp=sharing
Link Google Docs ap19: https://docs.google.com/document/d/1B14kNthC2oV4UBlEtoNWuQRv6chxjZQuUGh4wtAVJRE/edit?usp=sharing