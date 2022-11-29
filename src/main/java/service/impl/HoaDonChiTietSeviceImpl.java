/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import custommodel.HoaDonChiTietResponse;
import domainmodel.HoaDonChiTiet;
import java.util.List;
import java.util.UUID;
import repository.impl.HoaDonChiTietRepository;
import service.HoaDonChiTietService;

/**
 *
 * @author FPT
 */
public class HoaDonChiTietSeviceImpl implements HoaDonChiTietService {

    private HoaDonChiTietRepository hoaDonChiTietRepository = new HoaDonChiTietRepository();

    @Override
    public List<HoaDonChiTietResponse> getAll(String ma) {
        return hoaDonChiTietRepository.getAll(ma);
    }

    @Override
    public void add(HoaDonChiTiet hoaDonChiTiet) {
        hoaDonChiTietRepository.add(hoaDonChiTiet);
    }

    @Override
    public void delete(UUID id) {
        hoaDonChiTietRepository.delete(id);
    }

}
