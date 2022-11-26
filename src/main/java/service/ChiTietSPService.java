/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;


import domainmodel.ChiTietSP;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Đức Hiệu
 */
public interface ChiTietSPService {

    List<ChiTietSP> getAllChiTietSP();

    String add(ChiTietSP ctsp);

    String update(ChiTietSP ctsp, UUID id);

    String delete(ChiTietSP ctsp, UUID id);

    ChiTietSP getOne(String serial);

    List<ChiTietSP> seatch(String ram);

    String checkInt(String gia);
}
