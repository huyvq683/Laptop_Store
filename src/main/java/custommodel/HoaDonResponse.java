/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custommodel;

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
    private String tenNhanVien;
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
