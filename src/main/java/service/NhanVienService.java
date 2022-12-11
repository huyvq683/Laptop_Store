/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import domainmodel.NhanVien;
import java.util.List;

/**
 *
 * @author FPT
 */
public interface NhanVienService {
    
    List<NhanVien> getAllPage(int row);

    NhanVien getOne(String email);

    NhanVien getOneByMa(String ma, String email);
    
    List<NhanVien> getAll();

    String addOrUpdate(NhanVien nhanVien);

    List<NhanVien> search(String sdt);

    String validateLogin(String email, String mk, NhanVien nv);

}
