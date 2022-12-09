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
public class ThongKeDoanhThuRespone {

    private String maHD;
    private String maNV;
    private String tenNV;
    private int hinhThuc;
    private BigDecimal tienKhachTra;
    private BigDecimal tienCK;
    private BigDecimal tongTien;

    public String dinhDangTienVN(BigDecimal tien) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        return currencyVN.format(tien);
    }

    public Object[] toDataRow() {
        return new Object[]{maHD, maNV, tenNV, hinhThuc == 0 ? "Tiền mặt" : hinhThuc == 1 ? "Chuyển khoản" : "Cả tiền mặt và chuyển khoản", dinhDangTienVN(tienKhachTra), dinhDangTienVN(tienCK), dinhDangTienVN(tongTien)};
    }

}
