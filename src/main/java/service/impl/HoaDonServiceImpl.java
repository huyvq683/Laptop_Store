/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import custommodel.HoaDonResponse;
import domainmodel.HoaDon;
import java.util.List;
import java.util.UUID;
import repository.impl.HoaDonRepository;
import service.HoaDonService;

/**
 *
 * @author FPT
 */
public class HoaDonServiceImpl implements HoaDonService {

    private HoaDonRepository hoaDonRepository = new HoaDonRepository();

    @Override
    public List<HoaDonResponse> getAll() {
        return hoaDonRepository.getAll();
    }

    @Override
    public String add(HoaDon hoaDon) {
        boolean add = hoaDonRepository.add(hoaDon);
        if (add) {
            return "Thành công";
        } else {
            return "Thất bại";
        }
    }

    @Override
    public int genMaHD() {
        return hoaDonRepository.genMaHD();
    }

    @Override
    public String updateTrangThai(HoaDon hoaDon, UUID id) {
        boolean update = hoaDonRepository.updateTrangThai(hoaDon, id);
        if (update) {
            return "Thành công";
        } else {
            return "Thất bại";
        }
    }

    @Override
    public String updateTrangThaiHuy(HoaDon hoaDon, UUID id) {
        boolean update = hoaDonRepository.updateTrangThaiHuy(hoaDon, id);
        if (update) {
            return "Thành công";
        } else {
            return "Thất bại";
        }
    }

    @Override
    public HoaDon getByIdHoaDon(UUID id) {
        return hoaDonRepository.getByIdHoaDon(id);
    }


}
