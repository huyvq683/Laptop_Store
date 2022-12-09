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
public class DonHangResponse {

    private UUID id;
    private UUID idNV;
    private UUID idKH;
    private String tenkhachHang;
    private String maHD;
    private String maNV;
    private String diaChi;
    private String sdtKH;
    private BigDecimal tongTien;
    private BigDecimal tienMat;
    private BigDecimal chuyenKhoan;
    private BigDecimal giamGia;
    private int hinhThuc;
    private int trangThai;
    
}
