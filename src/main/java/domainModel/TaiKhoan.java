/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModel;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "TaiKhoan")
public class TaiKhoan implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "IdNhanVien")
    private NhanVien idNhanVien;

    @Column(name = "MatKhau")
    private String matKhau;

    @Column(name = "ChucVu")
    private int chucVu;

    @Column(name = "TrangThai")
    private int trangThai;

    @Column(name = "CreatedDate")
    private Date createdDate;

    @Column(name = "LastModifiedDate")
    private Date lastModifiedDate;
    
}
