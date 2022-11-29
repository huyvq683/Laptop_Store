package service;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
import custommodel.ThongKeDoanhThuRespone;
import custommodel.ThongKeSanPhamRespone;
import java.util.Date;
import java.util.List;

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

//    List<ThongKeDoanhThuRespone> getAllDoanhThuSortTang(Date n);
//
//    List<ThongKeDoanhThuRespone> getAllDoanhThuSortGiam(Date n);
    List<ThongKeSanPhamRespone> getAllSanPham(Date date);

    List<ThongKeSanPhamRespone> getAllSanPhamMonth(int thang, int nam);

    List<ThongKeSanPhamRespone> getAllSanPhamYear(int n);

    Long spKinhDoanh(int t);

    Integer namBatDau();

}
