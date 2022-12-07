/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import custommodel.KhachHangReponse;
import custommodel.KhachHangRespone;
import domainmodel.KhachHang;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author daohi
 */
public interface KhachHangService<T> {

    List<T> getAll();

    List<T> searchBySDT(List<T> list, String sdt);

    List<KhachHangRespone> getHD(UUID id);

    KhachHang getMa(String ma);

    KhachHang getIdKhachHang(UUID id);

    KhachHang getEmail(String email);

    String add(T t);

    String delete(T t);

    List<KhachHangReponse> getListKH();

    BigDecimal getTongTienByIDHD(UUID id);

}
