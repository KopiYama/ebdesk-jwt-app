## Keputusan dan Tantangan Saya
### Keputusan Desain
Selama pengembangan aplikasi ini, saya memilih untuk menggunakan Spring Boot karena dukungannya yang luas dalam membangun REST API dan kemampuannya untuk mengelola dependensi kompleks dengan lancar. Untuk otentikasi, Spring Security terintegrasi untuk menangani login aman dan penerbitan token JWT.

### Pilihan Perpustakaan
- Spring Boot: Untuk menyederhanakan pengembangan backend.
- Keamanan Musim Semi: Untuk keamanan dan otentikasi yang kuat.
- JJWT: Untuk pembuatan dan validasi JWT.

### Tantangan yang Dihadapi
- Mengonfigurasi JWT dengan benar merupakan sebuah tantangan, terutama memastikan bahwa token ditangani dengan aman.
- Menghasilkan jwtSecret yang aman untuk aplikasi. Properti memerlukan pemahaman tentang JWT dan praktik keamanan.
- Menerapkan pengambilan token dan memastikan bahwa titik akhir GET /resource menggunakan token dengan benar untuk autentikasi merupakan rintangan signifikan yang pada akhirnya dapat diatasi melalui proses debug mendetail dan dukungan komunitas.