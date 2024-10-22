/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import customModel.NhanVienResponse;
import domainModel.NhanVien;
import java.util.List;
import repository.impl.NhanVienRepository;
import service.NhanVienService;

/**
 *
 * @author FPT
 */
public class NhanVienServiceImpl implements NhanVienService {

    private NhanVienRepository nhanVienRepository = new NhanVienRepository();

    @Override
    public NhanVien getOne(String email) {
        return nhanVienRepository.getOne(email);
    }

    @Override
    public List<NhanVienResponse> getAll() {
        return nhanVienRepository.getAll();
    }

}
