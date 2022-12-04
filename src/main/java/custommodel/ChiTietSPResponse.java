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
public class ChiTietSPResponse {

    private UUID id;
    private String ma;
    private String ten;
    private String cPU;
    private String ram;
    private String card;
    private String oCung;
    private String hang;
    private int tinhTrang;
    private BigDecimal gia;
    private long soLuong;
    private String serial;

    public ChiTietSPResponse(String ma, String ten, String cPU, String ram, String card, String oCung, String hang, int tinhTrang, BigDecimal gia, long soLuong) {
        this.ma = ma;
        this.ten = ten;
        this.cPU = cPU;
        this.ram = ram;
        this.card = card;
        this.oCung = oCung;
        this.hang = hang;
        this.tinhTrang = tinhTrang;
        this.gia = gia;
        this.soLuong = soLuong;
    }

    public ChiTietSPResponse(UUID id, String ma, String ten, String cPU, String ram, String card, String oCung, String hang, int tinhTrang, BigDecimal gia, String serial) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.cPU = cPU;
        this.ram = ram;
        this.card = card;
        this.oCung = oCung;
        this.hang = hang;
        this.tinhTrang = tinhTrang;
        this.gia = gia;
        this.serial = serial;
    }

    public Object[] toDataRow(int stt) {
        return new Object[]{stt, ma, ten, cPU, ram, card, oCung, hang, gia, soLuong};
    }

    public Object[] toDataRowSerial() {
        return new Object[]{false, ma, ten, serial};
    }
}
