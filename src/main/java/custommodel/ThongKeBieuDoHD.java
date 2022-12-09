package custommodel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Đức Hiệu
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ThongKeBieuDoHD {

    private Date createdDate;
    private BigDecimal tongTien;

    public Object[]toDataDT() {
        return new Object[]{createdDate, tongTien};
    }
}
