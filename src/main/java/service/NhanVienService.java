/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import customModel.NhanVienResponse;
import domainModel.NhanVien;
import java.util.List;

/**
 *
 * @author FPT
 */
public interface NhanVienService {

    NhanVien getOne(String email);

    List<NhanVienResponse> getAll();
    
}
