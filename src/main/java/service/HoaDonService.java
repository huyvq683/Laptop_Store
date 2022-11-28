/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

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
}
