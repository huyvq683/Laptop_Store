/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodel;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
@Table(name = "NhanVien")
public class NhanVien implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private UUID id;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "HoTen")
    private String hoTen;

    @Column(name = "GioiTinh")
    private boolean gioiTinh;

    @Column(name = "NgaySinh")
    private Date ngaySinh;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "SDT")
    private String sdt;

    @Column(name = "Email")
    private String email;

    @Column(name = "ChucVu")
    private int chucVu;

    @Column(name = "MatKhau")
    private String matKhau;

    @Column(name = "TrangThai")
    private int trangThai;

    @Column(name = "CreatedDate")
    private Date createdDate;

    @Column(name = "LastModifiedDate")
    private Date lastModifiedDate;
    
    private String convertDate() {
        String date;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        date = dateFormat.format(ngaySinh);
        return date;
    }

    public NhanVien(String ma, String hoTen, boolean gioiTinh, Date ngaySinh, String diaChi, String sdt, String email, int chucVu, String matKhau, int trangThai, Date createdDate, Date lastModifiedDate) {
        this.ma = ma;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.chucVu = chucVu;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }
    
    

    public Object[] toDataRow() {
        return new Object[]{ma, hoTen, gioiTinh == true ? "Nam" : "Nữ", convertDate(), diaChi, sdt, email, trangThai == 0 ? "Đang làm việc" : "Đã nghỉ việc", chucVu == 0 ? "Quản lý" : "Nhân viên"};
    }
}
