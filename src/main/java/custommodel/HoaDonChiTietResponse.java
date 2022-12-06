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
    private BigDecimal tienKM;
    private BigDecimal gia;
    private long soluong;

    public HoaDonChiTietResponse(UUID idCTSP, UUID idHD, String ma, String ten, BigDecimal tienKM, BigDecimal gia, long soluong) {
        this.idCTSP = idCTSP;
        this.idHD = idHD;
        this.ma = ma;
        this.ten = ten;
        this.tienKM = tienKM;
        this.gia = gia;
        this.soluong = soluong;
    }

    

    public HoaDonChiTietResponse(UUID id, UUID idCTSP, UUID idHD, String ma, String ten, BigDecimal gia) {
        this.id = id;
        this.idCTSP = idCTSP;
        this.idHD = idHD;
        this.ma = ma;
        this.ten = ten;
        this.gia = gia;
    }

    public long thanhTien() {
        return (long) (soluong * gia.doubleValue());
    }

    public Object[] toDataRow(int stt) {
        return new Object[]{stt, ma, ten, gia, soluong, tienKM, thanhTien()};
    }
    
    public Object[] toDataRowHDCT() {
        return new Object[]{false, ma, ten, gia};
    }

    public Object[] toDataRow1() {
        return new Object[]{ma, ten, gia, soluong, thanhTien()};
    }
}
