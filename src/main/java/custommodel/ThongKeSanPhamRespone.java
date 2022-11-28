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
public class ThongKeSanPhamRespone {

    private String maSP;
    private String tenSP;
    private BigDecimal gia;
    private long count;

    public long doanhThu() {
        return count * gia.longValue();
    }

    public Object[] toDataRow() {
        return new Object[]{maSP, tenSP, gia, count, doanhThu()};
    }
}
