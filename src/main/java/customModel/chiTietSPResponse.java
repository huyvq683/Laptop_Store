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
public class ChiTietSPResponse {

    private UUID id;
    private String ma;
    private String ten;
    private String serial;
    private String cPU;
    private String hang;
    private String ram;
    private String card;
    private BigDecimal gia;

    public Object[] toDataRow(int stt) {
        return new Object[]{stt, ma, ten, cPU, ram, card, hang, gia};
    }
}
