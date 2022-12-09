package custommodel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
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

    private String dinhDangTienVN(long tien) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        return currencyVN.format(tien);
    }

    public Object[] toDataRow() {
        return new Object[]{maSP, tenSP, dinhDangTienVN(gia.longValue()), count, dinhDangTienVN(doanhThu())};
    }
}
