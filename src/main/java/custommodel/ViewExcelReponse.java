/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custommodel;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author WIN11
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViewExcelReponse {

    private String maHD;
    private String maNV;
    private String tenNV;
    private String tenSP;
    private BigDecimal donGia;
    private int hinhThuc;
    private BigDecimal tienKhachTra;
    private BigDecimal tienCK;
    private BigDecimal tienThua;
    private BigDecimal tongTien;
    private Date ngayTao;
    private int tinhTrang;


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

    public String getHinhThuc1() {
        if (this.hinhThuc == 0) {
            return "Tiền Mặt";
        } else if (this.hinhThuc == 1) {
            return "Chuyển Khoản";
        } else {
            return "Chuyển Khoản + Tiền Mặt";
        }
    }

    private String ngayTao1() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        String ngayTaoConvert = dateFormat.format(this.ngayTao);
        return ngayTaoConvert;
    }

    public Object[] toDataRow() {
        return new Object[]{maHD, maNV + tenNV, tenSP, donGia, getHinhThuc1(), tienKhachTra, tienCK, tienThua, tongTien, ngayTao1(), trangThai()};
    }

}
