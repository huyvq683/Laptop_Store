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

/**
 *
 * @author FPT
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonInResponse {
    private String maHD;
    private String tenNV;
    private String tenKH;
    private String sdtKH;
    private String diaChi;
    private BigDecimal tongTienTam;
    private BigDecimal giamGia;
    private BigDecimal tongTien;
    private BigDecimal tienTraLai;
    private String hinhThucThanhToan;
}
