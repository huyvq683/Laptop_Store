/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import custommodel.HoaDonInResponse;
import custommodel.HoaDonResponse;
import custommodel.KhachHangReponse;
import custommodel.ViewExcelReponse;
import domainmodel.HoaDon;
import domainmodel.NhanVien;
import java.util.UUID;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author WIN11
 */
public interface HoaDonService {

    List<HoaDonResponse> search(String searchKey);

    void exportExcel(JTable table);

    HoaDonInResponse getHDIn(String ma);

    KhachHangReponse getKhachHang(String ma);

    public List<ViewExcelReponse> getListExcel(int tt);

    List<ViewExcelReponse> getAllExcel();

    List<HoaDonResponse> getByOne(int tt);

    String update(HoaDon hd);

    List<HoaDonResponse> getAllPage(int row);

    List<HoaDonResponse> getAll();

    HoaDonResponse getByMa(String ma);

    List<HoaDon> get_All();

    List<HoaDonResponse> getAll(NhanVien nhanVien);

    String add(HoaDon hoaDon);

    int genMaHD();

    String updateTrangThai(HoaDon hoaDon);

    String updateTrangThaiHuy(HoaDon hoaDon, UUID id);

    HoaDon getByIdHoaDon(UUID id);

}
