/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import custommodel.ChiTietSPResponse;
import domainmodel.ChiTietSP;
import java.util.List;
import java.util.UUID;
import repository.impl.ChiTietSPRepository;
import service.ChiTietSPService;

/**
 *
 * @author FPT
 */
public class ChiTietSpServiceImpl implements ChiTietSPService {

    private ChiTietSPRepository chiTietSPRepository = new ChiTietSPRepository();

    @Override
    public List<ChiTietSPResponse> getAll() {
        return chiTietSPRepository.getAll();
    }

    @Override
    public ChiTietSP getBySerialChiTietSP(String serial) {
        return chiTietSPRepository.getBySerialChiTietSP(serial);
    }

    @Override
    public void updateTinhTrangSP(ChiTietSP chiTietSP, UUID id) {
        chiTietSPRepository.updateTinhTrangSP(chiTietSP, id);
    }

    @Override
    public List<ChiTietSPResponse> getlist() {
        return chiTietSPRepository.getList();
    }

}
