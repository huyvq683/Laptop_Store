/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custommodel;

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
public class KhachHangReponse {

    private UUID id;
    private String ma;
    private String ten;
    private String sdt;
    private int capBac;

    public KhachHangReponse(String ma, String ten, String sdt) {
        this.ma = ma;
        this.ten = ten;
        this.sdt = sdt;
    }

    public Object[] toDataRow() {
        return new Object[]{ma, ten, sdt, capBac};
    }
}
