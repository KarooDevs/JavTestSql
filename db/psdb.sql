-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 10 Nov 2023 pada 16.16
-- Versi server: 10.4.28-MariaDB
-- Versi PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `psdb`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_nota`
--

CREATE TABLE `tb_nota` (
  `id_nota` int(11) NOT NULL,
  `nama_pelanggan` varchar(50) DEFAULT NULL,
  `nama_pegawai` varchar(50) DEFAULT NULL,
  `id_ps` int(11) DEFAULT NULL,
  `tgl_sewa` date DEFAULT NULL,
  `tgl_kembali` date DEFAULT NULL,
  `durasi` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_nota`
--

INSERT INTO `tb_nota` (`id_nota`, `nama_pelanggan`, `nama_pegawai`, `id_ps`, `tgl_sewa`, `tgl_kembali`, `durasi`) VALUES
(1001, 'Adit', 'Udin', 9001, '2023-10-30', '2023-11-03', 4),
(1002, 'Rama', 'Udin', 9003, '2023-10-30', '2023-11-01', 2),
(1004, 'Jarwo', 'Udin', 9002, '2023-10-10', '2023-10-13', 3),
(1005, 'Ramadhan', 'Udin', 9003, '2023-10-01', '2023-10-06', 5),
(1007, 'as', 'as', 9002, '2023-11-01', '2023-11-03', 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_ps`
--

CREATE TABLE `tb_ps` (
  `id_ps` int(11) NOT NULL,
  `tipe_ps` varchar(50) DEFAULT NULL,
  `harga` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_ps`
--

INSERT INTO `tb_ps` (`id_ps`, `tipe_ps`, `harga`) VALUES
(9001, 'PlayStation1', 10000),
(9002, 'PlayStation2', 15000),
(9003, 'PlayStation3', 20000);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tb_nota`
--
ALTER TABLE `tb_nota`
  ADD PRIMARY KEY (`id_nota`),
  ADD KEY `id_ps` (`id_ps`);

--
-- Indeks untuk tabel `tb_ps`
--
ALTER TABLE `tb_ps`
  ADD PRIMARY KEY (`id_ps`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `tb_nota`
--
ALTER TABLE `tb_nota`
  MODIFY `id_nota` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9000;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `tb_nota`
--
ALTER TABLE `tb_nota`
  ADD CONSTRAINT `id_ps` FOREIGN KEY (`id_ps`) REFERENCES `tb_ps` (`id_ps`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
