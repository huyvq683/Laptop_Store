/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "HoaDon")
public class HoaDon implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "IdKH")
    private KhachHang idKH;

    @ManyToOne
    @JoinColumn(name = "IdNV")
    private NhanVien idNV;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "NgayTao")
    private Date ngayTao;

    @Column(name = "TienKhachTra")
    private BigDecimal tienKhachTra;

    @Column(name = "TienCK")
    private BigDecimal tienCK;

    @Column(name = "TienThua")
    private BigDecimal tienThua;

    @Column(name = "TongTien")
    private BigDecimal tongTien;

    @Column(name = "HinhThuc")
    private int hinhThuc;

    @Column(name = "TinhTrang")
    private int tinhTrang;

    @Column(name = "CreatedDate")
    private Date createdDate;

    @Column(name = "LastModifiedDate")
    private Date lastModifiedDate;

      public String getTinhTrang1() {
        return this.tinhTrang == 1 ? "Chờ Thanh Toán" : "Đã Thanh Toán";
    }

    public String getHinhThuc1() {
        if (this.hinhThuc == 0) {
            return "Tiền Mặt";
        } else if (this.hinhThuc == 1) {
            return "Chuyển Khoản";
        } else {
            return "Chuyển Khoản + Tiền Mặt";
        }
    }

    private String ngayTao() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        String ngayTaoConvert = dateFormat.format(this.ngayTao);
        return ngayTaoConvert;
    }

}
