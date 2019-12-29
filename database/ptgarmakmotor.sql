-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 08, 2019 at 01:19 PM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ptgarmakmotor`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `kode_barang` varchar(50) NOT NULL,
  `nama_barang` varchar(50) NOT NULL,
  `id_kat` varchar(50) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `tanggal` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`kode_barang`, `nama_barang`, `id_kat`, `stock`, `tanggal`) VALUES
('B001', 'Lampu ', 'KT001', 49, '2019-09-09'),
('B002', 'Sapu', 'KT002', 20, '2019-09-09');

-- --------------------------------------------------------

--
-- Table structure for table `barang_keluar`
--

CREATE TABLE `barang_keluar` (
  `id` varchar(10) NOT NULL,
  `tanggal` date DEFAULT NULL,
  `kd_barang` varchar(11) DEFAULT NULL,
  `kd_karyawan` varchar(11) DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barang_keluar`
--

INSERT INTO `barang_keluar` (`id`, `tanggal`, `kd_barang`, `kd_karyawan`, `jumlah`) VALUES
('BK001', '2019-09-09', 'B001', 'KR001', 1);

--
-- Triggers `barang_keluar`
--
DELIMITER $$
CREATE TRIGGER `barang_keluar_AFTER_INSERT` AFTER INSERT ON `barang_keluar` FOR EACH ROW BEGIN
		UPDATE barang SET stock = stock - NEW.jumlah
    WHERE kode_barang = NEW.kd_barang;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `barang_masuk`
--

CREATE TABLE `barang_masuk` (
  `id` varchar(10) NOT NULL,
  `tanggal` date DEFAULT NULL,
  `kd_supplier` varchar(11) DEFAULT NULL,
  `kd_barang` varchar(11) DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barang_masuk`
--

INSERT INTO `barang_masuk` (`id`, `tanggal`, `kd_supplier`, `kd_barang`, `jumlah`) VALUES
('BM001', '2019-09-09', 'SU001', 'B001', 2),
('BM002', '2019-09-09', 'SU002', 'B002', 1);

--
-- Triggers `barang_masuk`
--
DELIMITER $$
CREATE TRIGGER `barang_masuk_AFTER_INSERT` AFTER INSERT ON `barang_masuk` FOR EACH ROW BEGIN
	UPDATE barang SET stock = stock + NEW.jumlah
    WHERE kode_barang = NEW.kd_barang;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `karyawan`
--

CREATE TABLE `karyawan` (
  `id_karyawan` varchar(50) NOT NULL,
  `nama_karyawan` varchar(50) DEFAULT NULL,
  `staff` varchar(20) DEFAULT NULL,
  `no_tlp` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `karyawan`
--

INSERT INTO `karyawan` (`id_karyawan`, `nama_karyawan`, `staff`, `no_tlp`) VALUES
('KR001', 'Acep', 'IT', '0243532533'),
('KR002', 'May', 'Kebersihan', '0353463463'),
('KR003', 'Joko', 'Mesin', '0235345634');

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `id_kategori` varchar(50) NOT NULL,
  `nama_kategori` varchar(50) DEFAULT NULL,
  `no_rak` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`id_kategori`, `nama_kategori`, `no_rak`) VALUES
('KT001', 'Elektronik', 1),
('KT002', 'Alat Kebersihan', 2);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `id` varchar(30) NOT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id`, `nama`, `username`, `password`) VALUES
('US001', 'heri', 'heri', '123');

-- --------------------------------------------------------

--
-- Table structure for table `permintaan_barang`
--

CREATE TABLE `permintaan_barang` (
  `id` varchar(50) NOT NULL,
  `tanggal` date DEFAULT NULL,
  `nama_barang` varchar(50) DEFAULT NULL,
  `id_kategori` varchar(11) DEFAULT NULL,
  `id_karyawan` varchar(11) DEFAULT NULL,
  `keterangan` varchar(50) DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `permintaan_barang`
--

INSERT INTO `permintaan_barang` (`id`, `tanggal`, `nama_barang`, `id_kategori`, `id_karyawan`, `keterangan`, `jumlah`) VALUES
('BN001', '2019-09-09', 'TV', 'KT001', 'KR001', 'Kantor', 1);

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `id_supplier` varchar(50) NOT NULL,
  `namaSupplier` varchar(50) DEFAULT NULL,
  `alamatSupplier` varchar(20) DEFAULT NULL,
  `noSupplier` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`id_supplier`, `namaSupplier`, `alamatSupplier`, `noSupplier`) VALUES
('SU001', 'PT Cevron', 'Banten', '02536564556'),
('SU002', 'PT Jaya', 'Bekasi', '01423534534');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kode_barang`);

--
-- Indexes for table `barang_keluar`
--
ALTER TABLE `barang_keluar`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `barang_masuk`
--
ALTER TABLE `barang_masuk`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`id_karyawan`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`id_kategori`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `permintaan_barang`
--
ALTER TABLE `permintaan_barang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id_supplier`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
