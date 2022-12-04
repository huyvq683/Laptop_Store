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
import lombok.ToString;

/**
 *
 * @author FPT
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ChiTietSP")
@ToString
public class ChiTietSP implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "IdSanPham")
    private SanPham idSanPham;

    @Column(name = "Serial")
    private String serial;

    @ManyToOne
    @JoinColumn(name = "IdCPU")
    private CPU idCPU;

    @ManyToOne
    @JoinColumn(name = "IdHang")
    private Hang idHang;

    @ManyToOne
    @JoinColumn(name = "IdRam")
    private Ram idRam;

    @ManyToOne
    @JoinColumn(name = "IdCardMH")
    private CardMH idCard;

    @ManyToOne
    @JoinColumn(name = "IdOCung")
    private OCung idOCung;

    @Column(name = "Gia")
    private BigDecimal gia;

    @Column(name = "TinhTrang")
    private int tinhTrang;

    @Column(name = "CreatedDate")
    private Date createdDate;

    @Column(name = "LastModifiedDate")
    private Date lastModifiedDate;

 public ChiTietSP(UUID id, String serial) {
        this.id = id;
        this.serial = serial;
    }

    public String conVert(Date x) {
        DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        return date.format(x);
    }

    public ChiTietSP(SanPham idSanPham, String serial, CPU idCPU, Hang idHang, Ram idRam, CardMH idCard, OCung idOCung, BigDecimal gia, int tinhTrang, Date createdDate, Date lastModifiedDate) {
        this.idSanPham = idSanPham;
        this.serial = serial;
        this.idCPU = idCPU;
        this.idHang = idHang;
        this.idRam = idRam;
        this.idCard = idCard;
        this.idOCung = idOCung;
        this.gia = gia;
        this.tinhTrang = tinhTrang;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }
    
}
