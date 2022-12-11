/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custommodel;

import domainmodel.KhachHang;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
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
public class HoaDonResponse {

    private UUID id;
    private String ma;
    private Date ngayTao;
    private KhachHang idKH;
    private String tenKH;
    private int hinhThuc;
    private BigDecimal tongTien;
    private BigDecimal tienKhachTra;
    private BigDecimal tienCK;
    private String maNV;
    private String tenNhanVien;
    private int tinhTrang;

    public HoaDonResponse(UUID id, String ma, KhachHang maKH, Date ngayTao, int hinhThuc, BigDecimal tongTien, BigDecimal tienKhachTra, BigDecimal tienCK, String maNV, String tenNhanVien, int tinhTrang) {
        this.id = id;
        this.ma = ma;
        this.ngayTao = ngayTao;
        this.idKH = maKH;
        this.hinhThuc = hinhThuc;
        this.tongTien = tongTien;
        this.tienKhachTra = tienKhachTra;
        this.tienCK = tienCK;
        this.maNV = maNV;
        this.tenNhanVien = tenNhanVien;
        this.tinhTrang = tinhTrang;
    }

    public HoaDonResponse(UUID id, String ma, Date ngayTao, int hinhThuc, BigDecimal tongTien, BigDecimal tienKhachTra, BigDecimal tienCK) {
        this.id = id;
        this.ma = ma;
        this.ngayTao = ngayTao;
        this.hinhThuc = hinhThuc;
        this.tongTien = tongTien;
        this.tienKhachTra = tienKhachTra;
        this.tienCK = tienCK;
    }

    public HoaDonResponse(UUID id, String ma, Date ngayTao, String tenNhanVien, int tinhTrang) {
        this.id = id;
        this.ma = ma;
        this.ngayTao = ngayTao;
        this.tenNhanVien = tenNhanVien;
        this.tinhTrang = tinhTrang;
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

    public String trangThai() {
        switch (tinhTrang) {
            case 0:
                return "Chờ thanh toán";
            case 1:
                return "Đã thanh toán";
            case 2:
                return "Hủy";
            default:
                throw new AssertionError();
        }
    }

    private String ngayTao() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        String ngayTaoConvert = dateFormat.format(this.ngayTao);
        return ngayTaoConvert;
    }

    public Object[] toDataRow1() {
        return new Object[]{ma, ngayTao(), tenNhanVien, trangThai()};
    }

    public Object[] toDataRow(int stt) {
        return new Object[]{stt, ma, ngayTao(), tenNhanVien, trangThai()};
    }
}
