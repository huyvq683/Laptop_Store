/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custommodel;

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
 * @author daohi
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KhachHangRespone {

    private UUID idKH;
    private String maSP;
    private String tenSP;
    private Date ngayMua;
    private BigDecimal donGia;

    private String ngayTao() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        String ngayTaoConvert = dateFormat.format(this.ngayMua);
        return ngayTaoConvert;
    }

    public Object[] toDataRow() {
        return new Object[]{
            maSP, tenSP, ngayTao(), donGia
        };
    }
}
