package service;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
import custommodel.ThongKeBieuDoHD;
import custommodel.ThongKeBieuDoSP;
import custommodel.ThongKeDoanhThuRespone;
import custommodel.ThongKeSanPhamRespone;
import java.util.Date;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Đức Hiệu
 */
public interface ThongKeService {

    List<ThongKeDoanhThuRespone> getAllDoanhThu(Date date);

    List<ThongKeDoanhThuRespone> getAllDoanhThuMonth(int thang, int nam);

    List<ThongKeDoanhThuRespone> getAllDoanhThuYear(int nam);

    String getDoanhThuDay(Date ngay);

    String getDoanhThuYear(int nam);

    String getDoanhThuMonth(int thang, int nam);

    Integer namBatDauDoanhThu();

    List<ThongKeSanPhamRespone> getAllSanPham(Date date);

    List<ThongKeSanPhamRespone> getAllSanPhamMonth(int thang, int nam);

    List<ThongKeSanPhamRespone> getAllSanPhamYear(int n);

    Long spKinhDoanh(int t);

    Long spKinhDoanhAll();

    Integer namBatDau();

    void getBieuDoDTMonth(int thang, int nam, JPanel panelHienThi);

    void getBieuDoSPMonth(int thang, int nam, JPanel panelHienThi);
}
