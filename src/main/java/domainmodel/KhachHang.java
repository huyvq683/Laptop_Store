/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodel;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author FPT
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "KhachHang")
public class KhachHang implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private UUID id;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "HoTen")
    private String hoTen;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "SDT")
    private String sdt;

    @Column(name = "Email")
    private String email;

    @Column(name = "CapBac")
    private int capBac;

    @Column(name = "CreatedDate")
    private Date createdDate;

    @Column(name = "LastModifiedDate")
    private Date lastModifiedDate;

    public String capBac() {
        if (capBac == 0) {
            return "Đồng";
        } else if (capBac == 1) {
            return "Bạc";
        } else if (capBac == 2) {
            return "Vàng";
        } else if (capBac == 3) {
            return "Kim Cương";
        }
        return null;
    }

    public Object[] toDataRow() {
        return new Object[]{
            ma, hoTen, sdt, email, diaChi, capBac()
        };
    }

   
}
