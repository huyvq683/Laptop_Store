/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import custommodel.HoaDonChiTietResponse;
import domainmodel.HoaDonChiTiet;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author FPT
 */
public interface HoaDonChiTietService {

    List<HoaDonChiTietResponse> getAll(String ma);

    void add(HoaDonChiTiet hoaDonChiTiet);

    void delete(UUID id);
    
}
