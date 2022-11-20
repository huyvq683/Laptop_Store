/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customModel;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author WIN11
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NhanVienResponse {
    
    private UUID id;
    private String ma;
    private String ten;
    private String email;
    private String matKhau;
    private int chucVu;
    private int trangThai;
    
}
