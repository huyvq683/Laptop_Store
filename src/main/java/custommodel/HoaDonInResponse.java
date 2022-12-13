/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custommodel;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author FPT
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonInResponse {

    private String maHD;
    private String tenNV;
    private String tenKH;
    private String sdtKH;
    private String diaChi;
    private BigDecimal tongTienTam;
    private BigDecimal giamGia;
    private BigDecimal tongTien;
    private BigDecimal tienTraLai;
    private String hinhThucThanhToan;
    private int hinhThuc;

    public HoaDonInResponse(String maHD, String tenNV, String tenKH, String sdtKH, String diaChi, BigDecimal tongTien, int hinhThuc) {
        this.maHD = maHD;
        this.tenNV = tenNV;
        this.tenKH = tenKH;
        this.sdtKH = sdtKH;
        this.diaChi = diaChi;
        this.tongTien = tongTien;
        this.hinhThuc = hinhThuc;
    }

    public String loaiThanhToan() {
        switch (hinhThuc) {
            case 0:
                return "Chuyển Khoản";
            case 1:
                return "Tiền Mặt";
            case 2:
                return "Cả Hai";
            default:
                return null;
        }
    }

    public HoaDonInResponse(String maHD, String tenNV, String tenKH, String sdtKH, String diaChi, BigDecimal tongTienTam, BigDecimal giamGia, BigDecimal tongTien, String hinhThucThanhToan) {
        this.maHD = maHD;
        this.tenNV = tenNV;
        this.tenKH = tenKH;
        this.sdtKH = sdtKH;
        this.diaChi = diaChi;
        this.tongTienTam = tongTienTam;
        this.giamGia = giamGia;
        this.tongTien = tongTien;
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

}
