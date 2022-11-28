/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import custommodel.ViewHoaDonReponse;
import custommodel.HoaDonResponse;
import domainmodel.HoaDon;
import domainmodel.NhanVien;
import java.util.UUID;
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
    
    List<HoaDonResponse> getAll(NhanVien nhanVien);

    String add(HoaDon hoaDon);

    int genMaHD();

    String updateTrangThai(HoaDon hoaDon);

    String updateTrangThaiHuy(HoaDon hoaDon, UUID id);

    HoaDon getByIdHoaDon(UUID id);

}
