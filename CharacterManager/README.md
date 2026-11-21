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

## Screenshot Output Terminal TP5-AP
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
│               └── model/
│                   ├── Character.java
│                   └── CharacterType.java
│                   └── CharacterManager.java
└── README.md
```

# TP5-AP6 Fitur Baru: Pencarian Karakter Terkuat (findStrongest)
1. Rasional / Alasan Fitur
Dalam pengembangan sebuah game atau sistem RPG, mengetahui entitas atau karakter mana yang memiliki statistik tertinggi (terkuat) merupakan fitur analisis data yang sangat krusial. Fitur findStrongest() ditambahkan untuk mempermudah sistem dalam menyaring karakter dengan daya serang (attackPower) paling mendominasi tanpa mengharuskan layer aplikasi melakukan pengurutan (sorting) manual di sisi luar.

2. Menjaga Prinsip OOP & Enkapsulasi
Defensive Design: Method mengembalikan satu objek Character tunggal atau nilai null apabila sistem belum memiliki data karakter sama sekali.
No Information Leakage: Struktur data internal ArrayList<Character> characterList milik CharacterManager tetap terkunci rapat di dalam class (private). Akses luar murni hanya mendapatkan hasil kalkulasi/pencarian objek spesifik tanpa diberi celah untuk memodifikasi atau menambah isi list secara ilegal.

Screenshot Output Terminal ap6
![alt text](<../Output Image/Screenshot (21).png>)
![alt text](<../Output Image/Screenshot (22).png>)
![alt text](<../Output Image/Screenshot (23).png>)
![alt text](<../Output Image/Screenshot (24).png>)

Identitas Mahasiswa
Nama Lengkap: Yugas Bintang Bimantara harefa
NIM: 253303621229
Link Google Docs ap3: https://docs.google.com/document/d/13VmEHhD6g9jfoNQVd79ebY7AujzoQrAAqYsLhu1JZDE/edit?usp=sharing
Link Google Docs ap6: https://docs.google.com/document/d/1-aIZ3DACbzdOY9E0Q-ihezBbM8uxdzWK4TRoRQpNqqI/edit?usp=sharing