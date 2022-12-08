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
public class HoaDonCHiTietInResponse {

    private String tenSP;
    private BigDecimal donGia;
    private BigDecimal khuyenMai;
    private int soLuong;
    private BigDecimal thanhTien;
    
}
