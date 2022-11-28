/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custommodel;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author WIN11
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ViewHoaDonReponse {

    private String Ma;
    private String maNV;
    private String maKH;
    private Date ngayTao;
    private BigDecimal tongTien;
    private int tinhTrang;

    public Object[] toDataRow() {
        return new Object[]{Ma, maNV, maKH, ngayTao, tongTien, tinhTrang == 0 ? "Đã Thanh Toán" : "Chưa Thanh Toán"};
    }
}
