/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custommodel;

import java.math.BigDecimal;
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
public class HoaDonChiTietResponse {

    private UUID id;
    private UUID idCTSP;
    private UUID idHD;
    private String ma;
    private String ten;
    private BigDecimal gia;
    private long soluong;

    public HoaDonChiTietResponse(UUID idHD, String ma, String ten, BigDecimal gia, long soluong) {
        this.idHD = idHD;
        this.ma = ma;
        this.ten = ten;
        this.gia = gia;
        this.soluong = soluong;
    }
    
    public long thanhTien() {
        return (long) (soluong * gia.doubleValue());
    }

    public Object[] toDataRow() {
        return new Object[]{ ma, ten, gia, soluong, thanhTien()};
    }
}
