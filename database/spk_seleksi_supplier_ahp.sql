-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 12, 2025 at 08:55 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spk_seleksi_supplier_ahp`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` char(3) NOT NULL,
  `namalengkap` varchar(20) DEFAULT NULL,
  `user` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `namalengkap`, `user`, `password`) VALUES
('001', 'Neubri Hidayah', 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `data_supplier`
--

CREATE TABLE `data_supplier` (
  `id_supplier` varchar(5) NOT NULL,
  `nama_supplier` varchar(30) NOT NULL,
  `email` varchar(100) NOT NULL,
  `no_telp` varchar(20) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `kualitas` varchar(50) NOT NULL,
  `harga` varchar(50) NOT NULL,
  `layanan` varchar(50) NOT NULL,
  `ketepatan_pengiriman` varchar(50) NOT NULL,
  `ketepatan_jumlah` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `data_supplier`
--

INSERT INTO `data_supplier` (`id_supplier`, `nama_supplier`, `email`, `no_telp`, `alamat`, `kualitas`, `harga`, `layanan`, `ketepatan_pengiriman`, `ketepatan_jumlah`) VALUES
('SP001', 'PT ABC', 'abc@gmail.com', '0858xx', 'Depok', 'Baik', 'Terjangkau', 'Cukup', 'Terlambat', 'Sesuai'),
('SP002', 'PT Sentosa', 'sentosa@gmail.com', '0878xx', 'Jakarta', 'Sangat Baik', 'Sangat Terjangkau', 'Sangat Memuaskan', 'Sangat Tepat Waktu', 'Sangat Sesuai'),
('SP003', 'PT JAYA ABADI', 'jayabadi@gmail.com', '0898xx', 'Bandung', 'Kurang', 'Normal', 'Memuaskan', 'Sangat Tepat Waktu', 'Sangat Sesuai'),
('SP004', 'PT MAKMUR', 'makmur@gmail.com', '0896xx', 'Surabaya', 'Baik', 'Terjangkau', 'Memuaskan', 'Tepat Waktu', 'Sesuai');

-- --------------------------------------------------------

--
-- Table structure for table `kriteria`
--

CREATE TABLE `kriteria` (
  `kd_kriteria` char(3) NOT NULL,
  `nama_kriteria` varchar(30) NOT NULL,
  `prioritas_kepentingan` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kriteria`
--

INSERT INTO `kriteria` (`kd_kriteria`, `nama_kriteria`, `prioritas_kepentingan`) VALUES
('K1', 'Kualitas', 'Sangat Penting ke-1'),
('K2', 'Harga', 'Penting ke-2'),
('K3', 'Layanan', 'Cukup Penting ke-3'),
('K4', 'Ketepatan Pengiriman', 'Biasa ke-4'),
('K5', 'Ketepatan Jumlah', 'Kurang Penting ke-5');

-- --------------------------------------------------------

--
-- Table structure for table `register`
--

CREATE TABLE `register` (
  `id` int(3) NOT NULL,
  `email` varchar(50) NOT NULL,
  `user` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `register`
--

INSERT INTO `register` (`id`, `email`, `user`, `password`) VALUES
(1, 'mail@gmail.com', 'admin', 'admin'),
(2, '', '', ''),
(3, 'awa', 'awa', '123'),
(4, 'admin@gmail.com', 'admin', '12345');

-- --------------------------------------------------------

--
-- Table structure for table `seleksi`
--

CREATE TABLE `seleksi` (
  `id_supplier` char(5) NOT NULL,
  `nama_supplier` varchar(30) NOT NULL,
  `hasil_penilaian` decimal(4,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `seleksi`
--

INSERT INTO `seleksi` (`id_supplier`, `nama_supplier`, `hasil_penilaian`) VALUES
('SP001', 'PT ABC', 0.46),
('SP002', 'PT Sentosa', 1.00),
('SP003', 'PT JAYA ABADI', 0.31),
('SP004', 'PT MAKMUR', 0.52),
('SP005', 'PT INTEN', 0.39);

-- --------------------------------------------------------

--
-- Table structure for table `sub_kriteria`
--

CREATE TABLE `sub_kriteria` (
  `no_sub` int(3) NOT NULL,
  `kd_kriteria` char(3) NOT NULL,
  `nama_kriteria` varchar(30) NOT NULL,
  `nama_sub_kriteria` varchar(30) NOT NULL,
  `prioritas_kepentingan` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sub_kriteria`
--

INSERT INTO `sub_kriteria` (`no_sub`, `kd_kriteria`, `nama_kriteria`, `nama_sub_kriteria`, `prioritas_kepentingan`) VALUES
(1, 'K1', 'Kualitas', 'Sangat Baik', 'Sangat Penting ke-1'),
(2, 'K1', 'Kualitas', 'Baik', 'Penting ke-2'),
(3, 'K1', 'Kualitas', 'Cukup', 'Cukup Penting ke-3'),
(4, 'K1', 'Kualitas', 'Kurang', 'Biasa ke-4'),
(5, 'K1', 'Kualitas', 'Sangat Kurang', 'Kurang Penting ke-5'),
(6, 'K2', 'Harga', 'Sangat Terjangkau', 'Sangat Penting ke-1'),
(7, 'K2', 'Harga', 'Terjangkau', 'Penting ke-2'),
(8, 'K2', 'Harga', 'Normal', 'Cukup Penting ke-3'),
(9, 'K2', 'Harga', 'Mahal', 'Biasa ke-4'),
(10, 'K2', 'Harga', 'Sangat Mahal', 'Kurang Penting ke-5'),
(11, 'K3', 'Layanan', 'Sangat Memuaskan', 'Sangat Penting ke-1'),
(12, 'K3', 'Layanan', 'Memuaskan', 'Penting ke-2'),
(13, 'K3', 'Layanan', 'Cukup', 'Cukup Penting ke-3'),
(14, 'K3', 'Layanan', 'Kurang Memuaskan', 'Biasa ke-4'),
(15, 'K3', 'Layanan', 'Tidak Memuaskan', 'Kurang Penting ke-5'),
(16, 'K4', 'Ketepatan Pengiriman', 'Sangat Tepat Waktu', 'Sangat Penting ke-1'),
(17, 'K4', 'Ketepatan Pengiriman', 'Tepat Waktu', 'Penting ke-2'),
(18, 'K4', 'Ketepatan Pengiriman', 'Cukup Tepat', 'Cukup Penting ke-3'),
(19, 'K4', 'Ketepatan Pengiriman', 'Terlambat', 'Biasa ke-4'),
(20, 'K4', 'Ketepatan Pengiriman', 'Sangat Terlambat', 'Kurang Penting ke-5'),
(21, 'K5', 'Ketepatan Jumlah', 'Sangat Sesuai', 'Sangat Penting ke-1'),
(22, 'K5', 'Ketepatan Jumlah', 'Sesuai', 'Penting ke-2'),
(23, 'K5', 'Ketepatan Jumlah', 'Cukup Sesuai', 'Cukup Penting ke-3'),
(24, 'K5', 'Ketepatan Jumlah', 'Kurang Sesuai', 'Biasa ke-4'),
(25, 'K5', 'Ketepatan Jumlah', 'Tidak Sesuai', 'Kurang Penting ke-5');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `data_supplier`
--
ALTER TABLE `data_supplier`
  ADD PRIMARY KEY (`id_supplier`);

--
-- Indexes for table `kriteria`
--
ALTER TABLE `kriteria`
  ADD PRIMARY KEY (`kd_kriteria`);

--
-- Indexes for table `register`
--
ALTER TABLE `register`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `seleksi`
--
ALTER TABLE `seleksi`
  ADD UNIQUE KEY `id_paket` (`id_supplier`);

--
-- Indexes for table `sub_kriteria`
--
ALTER TABLE `sub_kriteria`
  ADD PRIMARY KEY (`no_sub`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `register`
--
ALTER TABLE `register`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
