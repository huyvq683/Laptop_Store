/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import custommodel.ChiTietSPResponse;
import domainmodel.ChiTietSP;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Đức Hiệu
 */
public interface ChiTietSPService {
    
    String add(ChiTietSP ctsp);
    
    String validate(ChiTietSP ctsp);
    
    String validateUpdate(ChiTietSP ctsp);
    
    String update(ChiTietSP ctsp , UUID id);
    
    String updateTinhTrang(List<ChiTietSP> ctsp);
    
    String updateKhoiPhuc(List<ChiTietSP> ctsp);

    List<ChiTietSP> getAllChiTietSP();

    List<ChiTietSP> getOneGia(String gia1, String gia2);

    String delete(ChiTietSP ctsp, UUID id);

    ChiTietSP getOne(String serial);
    
    ChiTietSP getOneTinhTrang(String tinhTrang);
    
    List<ChiTietSP> seatch(String ram);

    String checkInt(String gia);

    List<ChiTietSPResponse> getAll();

    ChiTietSP getBySerialChiTietSP(String serial);
    
    List<ChiTietSPResponse> getAllCTSP(String ma, String ten, String cPU, String card, BigDecimal gia, String hang, String oCung, String ram);

    void updateTinhTrangSP(List<String> listSerial);
    
    void updateTinhTrang(ChiTietSP chiTietSP);
    
    void updateTinhTrangChuaBan(List<String> listSerial);
    
    List<ChiTietSPResponse> searchCTSP(String ma);

}
