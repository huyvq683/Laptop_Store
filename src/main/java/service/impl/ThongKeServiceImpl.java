package service.impl;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import custommodel.ThongKeDoanhThuRespone;
import custommodel.ThongKeSanPhamRespone;
import java.util.Date;
import java.util.List;
import repository.impl.ThongKeRepository;
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
    public List<ThongKeDoanhThuRespone> getAllDoanhThuMonth(int thang, int nam) {
        return res.getAllDoanhThuMonth(thang, nam);
    }

    @Override
    public List<ThongKeDoanhThuRespone> getAllDoanhThuYear(int nam) {
        return res.getAllDoanhThuYear(nam);
    }

    @Override
    public String getDoanhThuDay(Date ngay) {
        return res.getDoanhThuDay(ngay);
    }

    @Override
    public String getDoanhThuYear(int nam) {
        return res.getDoanhThuYear(nam);
    }

    @Override
    public String getDoanhThuMonth(int thang, int nam) {
        return res.getDoanhThuMonth(thang, nam);
    }

    @Override
    public Integer namBatDauDoanhThu() {
        return res.namBatDauDoanhThu();
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
    public List<ThongKeSanPhamRespone> getAllSanPhamMonth(int thang, int nam) {
        return res.getAllSanPhamMonth(thang, nam);
    }

    @Override
    public List<ThongKeSanPhamRespone> getAllSanPhamYear(int n) {
        return res.getAllSanPhamYear(n);
    }

    @Override
    public Integer namBatDau() {
        return res.namBatDau();
    }

}
