/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

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

    List<ThongKeDoanhThuRespone> getAllDoanhThuSortTang(Date n);

    List<ThongKeDoanhThuRespone> getAllDoanhThuSortGiam(Date n);

    List<ThongKeSanPhamRespone> getAllSanPham(Date date);

    List<ThongKeSanPhamRespone> getAllSanPhamMonth(int n);

    List<ThongKeSanPhamRespone> getAllSanPhamYear(int n);

    Long spKinhDoanh(int t);

    Integer thangNamBatDau();

    String getDoanhThu();
}
