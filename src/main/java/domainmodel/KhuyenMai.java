/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodel;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "KhuyenMai")
public class KhuyenMai implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private UUID id;

    @Column(name = "Ma")
    private String ma;
    
    @Column(name = "Ten")
    private String tenKM;
    
    @Column(name = "LoaiKhuyenMai")
    private int loaiKM;
    
    @Column(name = "GiaTriKM")
    private BigDecimal giaTriKM;
    
    @Column(name = "NgayBatDau")
    private Date ngayBD;
    
    @Column(name = "NgayKetThuc")
    private Date ngayKT;
    
    @Column(name = "TrangThai")
    private int trangThai;

    @Column(name = "CreatedDate")
    private Date createdDate;

    @Column(name = "LastModifiedDate")
    private Date lastModifiedDate;
}
