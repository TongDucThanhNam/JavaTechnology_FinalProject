-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 17, 2022 lúc 07:33 AM
-- Phiên bản máy phục vụ: 10.4.25-MariaDB
-- Phiên bản PHP: 8.1.10

SET
SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET
time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlysanbong`
--
    create
database quanlysanbong;
use quanlysanbong;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `datsan`
--

CREATE TABLE `datsan`
(
    `ma_dat_san`               int(11) NOT NULL,
    `gio_bat_dau`              varchar(255) DEFAULT NULL,
    `gio_ket_thuc`             varchar(255) DEFAULT NULL,
    `ma_san_bong`              int(11) NOT NULL,
    `ngay_dat_san`             varchar(255) DEFAULT NULL,
    `so_dien_thoai_khach_hang` varchar(120) NOT NULL,
    `ten_khach_hang`           varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `datsan`
--

INSERT INTO `datsan` (`ma_dat_san`, `gio_bat_dau`, `gio_ket_thuc`, `ma_san_bong`, `ngay_dat_san`,
                      `so_dien_thoai_khach_hang`, `ten_khach_hang`)
VALUES (5, '11:11', '12:12', 1, '2022-12-17', '869887363', 'Thành Nam'),
       (6, '11:11', '12:12', 1, '2022-12-09', '909121212', 'Nam'),
       (7, '06:06', '09:09', 4, '2022-11-29', '0606060606', 'Nam'),
       (8, '00:12', '13:13', 1, '2022-12-17', '0909151212', 'Teacher');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence`
(
    `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`)
VALUES (1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE `role`
(
    `role_id`    int(11) NOT NULL,
    `permission` varchar(255) DEFAULT NULL,
    `role_name`  varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `role`
--

INSERT INTO `role` (`role_id`, `permission`, `role_name`)
VALUES (0, 'Truy cap trang web, xem bang gia, dang nhap, dang ky', 'Khach'),
       (1, 'Dat San, Xem lich su dat san, Thay doi thon tin ca nhan', 'TaiKhoanCaNhan'),
       (2, 'Quan ly tai khoan, quan ly san', 'ADMIN');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `san`
--

CREATE TABLE `san`
(
    `ma_san_bong`  int(11) NOT NULL,
    `gia_thue`     int(11) NOT NULL,
    `loai_san`     varchar(255) DEFAULT NULL,
    `ten_san_bong` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `san`
--

INSERT INTO `san` (`ma_san_bong`, `gia_thue`, `loai_san`, `ten_san_bong`)
VALUES (1, 200000, 'Sân cỏ 7', 'Sân cỏ tự nhiên - 7 người'),
       (2, 250000, 'Sân cỏ 11', 'Sân cỏ tự nhiên -11 người'),
       (4, 900000, 'Sân 11 người', 'Sân cỏ nhân tạo 3G - 7 người');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user`
(
    `id`       int(11) NOT NULL,
    `email`    varchar(255) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    `phone`    varchar(255) DEFAULT NULL,
    `role_id`  int(11) NOT NULL,
    `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `phone`, `role_id`, `username`)
VALUES (1, 'namthongminhghe@gmail.com', '$2a$10$HZoBJG7Z2HJvnPwZ52BenuzlxZ.SFnkVD5TZqI8ZVLQKSCrtUGmAa', '0869887363', 2,
        'namtong'),
       (2, 'admin@gmail.com', '$2a$10$QLPTtZ3BE6N/LwEhuME7COaIcBbIYDaTafbspru.Df1VHpF.FFfUG', '0906045151', 2, 'admin'),
       (3, 'tongducthanhnam@gmail.com', '$2a$10$MYJal8oTEb.d7wQ/QQgJq.kVHmrAbqFGEvomNUadMcZtsyxsu.qke', '123', 2,
        'nam');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `datsan`
--
ALTER TABLE `datsan`
    ADD PRIMARY KEY (`ma_dat_san`);

--
-- Chỉ mục cho bảng `role`
--
ALTER TABLE `role`
    ADD PRIMARY KEY (`role_id`);

--
-- Chỉ mục cho bảng `san`
--
ALTER TABLE `san`
    ADD PRIMARY KEY (`ma_san_bong`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
    ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `datsan`
--
ALTER TABLE `datsan`
    MODIFY `ma_dat_san` int (11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `san`
--
ALTER TABLE `san`
    MODIFY `ma_san_bong` int (11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
    MODIFY `id` int (11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
