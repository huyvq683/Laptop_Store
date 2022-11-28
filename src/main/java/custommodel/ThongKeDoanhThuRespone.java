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
import lombok.ToString;

/**
 *
 * @author Đức Hiệu
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ThongKeDoanhThuRespone {

    private String maHD;
    private String maNV;
    private String tenNV;
    private BigDecimal tongTien;

    public Object[] toDataRow() {
        return new Object[]{maHD, maNV, tenNV, tongTien};
    }
}
