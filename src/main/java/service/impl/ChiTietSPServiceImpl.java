/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import customModel.ChiTietSPResponse;
import java.util.List;
import repository.impl.ChiTietSPRepository;
import service.ChiTietSPService;

/**
 *
 * @author FPT
 */
public class ChiTietSPServiceImpl implements ChiTietSPService{
    private ChiTietSPRepository chiTietSPRepository = new ChiTietSPRepository();

    @Override
    public List<ChiTietSPResponse> getAll() {
       return chiTietSPRepository.getAll();
    }
    
}
