/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import domainmodel.HoaDonChiTiet;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author FPT
 */
public interface SerialDaBanService {

    void add(List<String> lists, HoaDonChiTiet hdct);
    
    HoaDonChiTiet getByIdHDCT(UUID id);
    
    void delete(UUID id);
    
}
