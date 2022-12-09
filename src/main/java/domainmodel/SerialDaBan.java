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
@Table(name = "SerialDaBan")
public class SerialDaBan implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private UUID id;

    @Column(name = "Ma")
    private String ma;

    @ManyToOne
    @JoinColumn(name = "IdHDCT")
    private HoaDonChiTiet idHDCT;

    @Column(name = "CreatedDate")
    private Date createdDate;

    @Column(name = "LastModifiedDate")
    private Date lastModifiedDate;
}
