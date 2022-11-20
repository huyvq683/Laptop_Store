/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import customModel.HoaDonResponse;
import domainModel.HoaDon;
import java.util.List;
import repository.impl.HoaDonRepository;
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

    @Override
    public String add(HoaDon hoaDon) {
        boolean add = hoaDonRepository.add(hoaDon);
        if(add){
            return "Thành công";
        }
        else{
            return "Thất bại";
        }
    }

    @Override
    public int genMaHD() {
        return hoaDonRepository.genMaHD();
    }
}
