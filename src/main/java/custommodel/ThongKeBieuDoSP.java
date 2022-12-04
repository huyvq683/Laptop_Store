package custommodel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


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
public class ThongKeBieuDoSP {

    private Date createdDate;
    private long soLuong;

    public Object[] toDataRowBDSP() {
        return new Object[]{createdDate, soLuong};
    }
}
