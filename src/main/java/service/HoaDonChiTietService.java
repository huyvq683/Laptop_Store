/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import custommodel.HoaDonChiTietResponse;
import domainmodel.HoaDon;
import domainmodel.HoaDonChiTiet;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author FPT
 */
public interface HoaDonChiTietService {

    List<HoaDonChiTietResponse> get_All(String ma);

    List<HoaDonChiTietResponse> getAll(UUID id);
    
    List<HoaDonChiTietResponse> getList(UUID id);

    void add(List<String> listSerial, HoaDon hd);

    void delete(UUID id);
    
    void addOne(HoaDonChiTiet hoaDonChiTiet);
    
    void deleteHDCT(List<String> listSerial);

}
