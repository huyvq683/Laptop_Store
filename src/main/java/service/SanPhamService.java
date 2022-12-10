/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import domainmodel.SanPham;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Đức Hiệu
 */
public interface SanPhamService {

    List<SanPham> getAllSanPham();
    
    List<SanPham> getAllSP();

    SanPham getOneSP(String ma);

    SanPham getOne(String ma);

    String addSanPham(SanPham sp);

    String upDateSanPham(SanPham sp, UUID id);

    String upDateTrangThai(SanPham sp, UUID id);

    String deleteSanPham(UUID id);

    String validate(SanPham sp);

    String validateSP(SanPham spham);

    List<SanPham> search(String seatchKey);

}
