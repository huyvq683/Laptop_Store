/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import domainmodel.KhachHang;
import java.util.List;

/**
 *
 * @author daohi
 */
public interface KhachHangService<T> {

    List<T> getAll();

    List<T> searchBySDT(List<T> list, String sdt);

    KhachHang getMa(String ma);

    KhachHang getEmail(String email);

    String add(T t);

    String delete(T t);
}
