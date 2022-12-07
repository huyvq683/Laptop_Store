/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import custommodel.HoaDonChiTietResponse;
import domainmodel.HoaDon;
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
    public List<HoaDonChiTietResponse> get_All(String ma) {
        return hoaDonChiTietRepository.get_All(ma);
    }

    @Override
    public List<HoaDonChiTietResponse> getAll(UUID id) {
        return hoaDonChiTietRepository.getAll(id);
    }

    @Override
    public void add(List<String> listSerial, HoaDon hd) {
        hoaDonChiTietRepository.add(listSerial, hd);
    }

    @Override
    public void delete(UUID id) {
        hoaDonChiTietRepository.delete(id);
    }

    @Override
    public void addOne(HoaDonChiTiet hoaDonChiTiet) {
        hoaDonChiTietRepository.addOne(hoaDonChiTiet);
    }

    @Override
    public List<HoaDonChiTietResponse> getList(UUID id) {
        return hoaDonChiTietRepository.getList(id);
    }

    @Override
    public void deleteHDCT(List<String> listSerial) {
         hoaDonChiTietRepository.deleteHDCT(listSerial);
    }

}
