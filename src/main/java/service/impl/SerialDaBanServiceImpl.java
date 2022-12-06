/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import domainmodel.ChiTietSP;
import domainmodel.HoaDonChiTiet;
import java.util.List;
import java.util.UUID;
import repository.impl.SerialDaBanRepository;
import service.SerialDaBanService;

/**
 *
 * @author FPT
 */
public class SerialDaBanServiceImpl implements SerialDaBanService {

    private SerialDaBanRepository serialDaBanRepository = new SerialDaBanRepository();

    @Override
    public void add(List<String> lists, HoaDonChiTiet hdct) {
         serialDaBanRepository.add(lists, hdct);
    }

    @Override
    public void delete(UUID id) {
        serialDaBanRepository.delete(id);
    }

    @Override
    public HoaDonChiTiet getByIdHDCT(UUID id) {
        return serialDaBanRepository.getHDCTByIdCTSP(id);
    }

    @Override
    public ChiTietSP getIdCtspBySerial(String serial) {
        return serialDaBanRepository.getCTSPBySerial(serial);
    }

}
