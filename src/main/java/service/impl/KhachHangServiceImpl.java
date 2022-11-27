/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import domainmodel.KhachHang;
import java.util.ArrayList;
import java.util.List;
import repository.impl.KhachHangRepository;
import service.KhachHangService;

/**
 *
 * @author daohi
 */
public class KhachHangServiceImpl implements KhachHangService<KhachHang> {

    private KhachHangRepository repository = new KhachHangRepository();

    @Override
    public List<KhachHang> getAll() {
        return repository.getAll();
    }

    @Override
    public KhachHang getMa(String ma) {
        return repository.getMa(ma);
    }

    @Override
    public KhachHang getEmail(String email) {
        return repository.getEmail(email);
    }

    @Override
    public String add(KhachHang t) {
        if (t.getHoTen().trim().isBlank()) {
            return "Tên trống";
        }
        if (!t.getHoTen().trim().matches("^[a-zA-z]$")) {
            return "Tên sai";
        }
        if (t.getSdt().trim().isBlank()) {
            return "Sđt trống";
        }
        if (t.getDiaChi().trim().isBlank()) {
            return "Địa chỉ trống";
        }
        Boolean check = repository.add(t);
        if (check) {
            return "Thành công";
        } else {
            return "Thất bại";
        }
    }

    @Override
    public String delete(KhachHang t) {
        Boolean check = repository.delete(t);
        if (check) {
            return "Thành công";
        } else {
            return "Thất bại";
        }
    }

    @Override
    public List<KhachHang> searchBySDT(List<KhachHang> list, String sdt) {
        List<KhachHang> listSearch = new ArrayList<>();
        for (KhachHang kh : list) {
            if (kh.getSdt().contains(sdt)) {
                listSearch.add(kh);
            }
        }
        return listSearch;
    }

    @Override
    public String validate(String ma) {
        if (getMa(ma) != null) {
            return "Mã trùng";
        }
        return null;
    }

}
