/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import domainmodel.HoaDon;
import java.util.List;
import repository.impl.HoaDonRepository;
import service.HoaDonService;

/**
 *
 * @author WIN11
 */
public class HoaDonServiceImpl implements HoaDonService {

    private HoaDonRepository hoaDonRepository = new HoaDonRepository();

    @Override
    public List<HoaDon> getByOne(String tt) {
        return hoaDonRepository.getByOne(tt);
    }

    @Override
    public List<HoaDon> getAll() {
        return hoaDonRepository.getAll();
    }

}
