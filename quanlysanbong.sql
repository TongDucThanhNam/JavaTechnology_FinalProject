-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 14, 2022 lúc 03:02 PM
-- Phiên bản máy phục vụ: 10.4.25-MariaDB
-- Phiên bản PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlysanbong`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `datsan`
--

CREATE TABLE `datsan` (
  `ma_dat_san` int(11) NOT NULL,
  `gio_bat_dau` varchar(255) DEFAULT NULL,
  `gio_ket_thuc` varchar(255) DEFAULT NULL,
  `ma_san_bong` int(11) NOT NULL,
  `ngay_dat_san` varchar(255) DEFAULT NULL,
  `so_dien_thoai_khach_hang` varchar(120) NOT NULL,
  `ten_khach_hang` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `datsan`
--

INSERT INTO `datsan` (`ma_dat_san`, `gio_bat_dau`, `gio_ket_thuc`, `ma_san_bong`, `ngay_dat_san`, `so_dien_thoai_khach_hang`, `ten_khach_hang`) VALUES
(1, '12:11', '11:11', 0, '2022-12-22', '123', '123'),
(2, '03:01', '03:01', 0, '2022-12-14', '12414', '123123'),
(3, '', '', 0, '', '123', '123'),
(4, '', '', 2, '', '123', '123123'),
(5, '11:11', '12:12', 1, '2022-12-17', '869887363', 'Thành Nam'),
(6, '11:11', '12:12', 1, '2022-12-09', '909121212', 'Nam'),
(7, '06:06', '09:09', 4, '2022-11-29', '0606060606', 'Nam');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `datsan`
--
ALTER TABLE `datsan`
  ADD PRIMARY KEY (`ma_dat_san`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `datsan`
--
ALTER TABLE `datsan`
  MODIFY `ma_dat_san` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
