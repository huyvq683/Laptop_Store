/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customModel;

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

    private UUID idCTSP;
    private UUID idHoaDon;
    private String ma;
    private String ten;
    private BigDecimal gia;
    
    public Object[] toDataRow(int stt){
        return new Object[]{stt, ma, ten, gia};
    }
}
