/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import customModel.HoaDonResponse;
import domainModel.HoaDon;
import java.util.List;

/**
 *
 * @author FPT
 */
public interface HoaDonService {

    List<HoaDonResponse> getAll();

    String add(HoaDon hoaDon);
    
    int genMaHD();
    
}
