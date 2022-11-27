/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import custommodel.HoaDonResponse;
import domainmodel.HoaDon;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author FPT
 */
public interface HoaDonService {

    List<HoaDonResponse> getAll();

    String add(HoaDon hoaDon);

    int genMaHD();

    String updateTrangThai(HoaDon hoaDon, UUID id);

    String updateTrangThaiHuy(HoaDon hoaDon, UUID id);

    HoaDon getByIdHoaDon(UUID id);
    
}
