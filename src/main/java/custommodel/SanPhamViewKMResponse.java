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
 * @author dinhv
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamViewKMResponse {

    private String ma;
    private String ten;
    private String cpu;
    private String hang;
    private String ram;
    private String card;
    private String oCung;
    private BigDecimal gia;
    private Long soLuong;
}
