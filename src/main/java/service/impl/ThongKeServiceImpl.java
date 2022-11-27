/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import custommodel.ThongKeDoanhThuRespone;
import custommodel.ThongKeSanPhamRespone;
import java.util.Date;
import java.util.List;
import repository.ThongKeRepository;
import service.ThongKeService;

/**
 *
 * @author Đức Hiệu
 */
public class ThongKeServiceImpl implements ThongKeService {

    private ThongKeRepository res = new ThongKeRepository();

    @Override
    public List<ThongKeDoanhThuRespone> getAllDoanhThu(Date date) {
        return res.getAllDoanhThu(date);
    }

    @Override
    public List<ThongKeSanPhamRespone> getAllSanPham(Date date) {
        return res.getAllSanPham(date);
    }

    @Override
    public Long spKinhDoanh(int t) {
        return res.spKinhDoanh(t);
    }

    @Override
    public List<ThongKeSanPhamRespone> getAllSanPhamMonth(int n) {
        return res.getAllSanPhamMonth(n);
    }

    @Override
    public List<ThongKeSanPhamRespone> getAllSanPhamYear(int n) {
        return res.getAllSanPhamYear(n);
    }

    @Override
    public Integer thangNamBatDau() {
        return res.thangNamBatDau();
    }

    @Override
    public String getDoanhThu() {
        return res.getDoanhThu();
    }

    @Override
    public List<ThongKeDoanhThuRespone> getAllDoanhThuSortTang(Date n) {
        return res.getAllDoanhThuSortTang(n);
    }

    @Override
    public List<ThongKeDoanhThuRespone> getAllDoanhThuSortGiam(Date n) {
        return res.getAllDoanhThuSortGiam(n);
    }

}
