/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import custommodel.ChiTietSPResponse;
import domainmodel.ChiTietSP;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author FPT
 */
public interface ChiTietSPService {

    List<ChiTietSPResponse> getAll();
    
    List<ChiTietSPResponse> getlist();

    ChiTietSP getBySerialChiTietSP(String serial);
    
    void updateTinhTrangSP(ChiTietSP chiTietSP, UUID id);
    
}
