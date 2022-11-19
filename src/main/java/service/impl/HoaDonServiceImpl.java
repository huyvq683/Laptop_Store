/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import customModel.HoaDonResponse;
import java.util.List;
import repository.impl.HoaDonRepository;
import service.ChiTietSPService;
import service.HoaDonService;

/**
 *
 * @author FPT
 */
public class HoaDonServiceImpl implements HoaDonService{

    private HoaDonRepository hoaDonRepository = new HoaDonRepository();

    @Override
    public List<HoaDonResponse> getAll() {
        return hoaDonRepository.getAll();
    }
}
