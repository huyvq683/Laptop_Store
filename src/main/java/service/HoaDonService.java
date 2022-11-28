/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

<<<<<<< HEAD
import custommodel.ViewHoaDonReponse;
import domainmodel.HoaDon;
import java.util.Date;

import java.util.List;

/**
 *
 * @author WIN11
 */
public interface HoaDonService {

    List<ViewHoaDonReponse> getByOne(int tt);

    String update(HoaDon hd);

    List<ViewHoaDonReponse> getAll();

    ViewHoaDonReponse getOneByMa(String ma);

    HoaDon getByMa(String ma);

    List<ViewHoaDonReponse> getByNV(String ma);

    List<ViewHoaDonReponse> getByMaKH(String ma);

    List<ViewHoaDonReponse> getByNgayTao(String ma);

    List<HoaDon> get_All();
//
//    NhanVien getMaNV(String ma);
//
//    KhachHang getMaKH(String ma);
=======
import custommodel.HoaDonResponse;
import domainmodel.HoaDon;
import domainmodel.NhanVien;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author FPT
 */
public interface HoaDonService {

    List<HoaDonResponse> getAll(NhanVien nhanVien);

    String add(HoaDon hoaDon);

    int genMaHD();

    String updateTrangThai(HoaDon hoaDon, UUID id);

    String updateTrangThaiHuy(HoaDon hoaDon, UUID id);

    HoaDon getByIdHoaDon(UUID id);
    
>>>>>>> bd39a4751d5e64f1d6ad636b2af8919232587456
}
