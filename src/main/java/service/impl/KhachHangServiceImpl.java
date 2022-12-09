/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import custommodel.KhachHangReponse;
import custommodel.KhachHangRespone;
import domainmodel.KhachHang;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
        if (t.getHoTen().trim().isBlank()
                || t.getSdt().trim().isBlank() || t.getEmail().trim().isBlank()
                || t.getDiaChi().trim().isBlank()) {
            return "Không được để trống";
        }
        if (!t.getSdt().matches("^[0-9]{10}+$")) {
            return "Số điện thoại sai định dạng";
        }
        if (!t.getEmail().matches("\\w+@gmail.com$")) {
            return "Email sai định dạng";
        }
        Boolean check = repository.add(t);
        if (check) {
            return "Thành công";
        } else if (getEmail(t.getEmail()) != null) {
            return "Email trùng";
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
    public List<KhachHangReponse> getListKH() {
        return repository.getListKH();

    }

    @Override
    public List<KhachHangRespone> getHD(UUID id) {
        return repository.getHD(id);
    }

    @Override
    public KhachHang getIdKhachHang(UUID id) {
        return repository.getIdKhachHang(id);
    }

    @Override
    public BigDecimal getTongTienByIDHD(UUID id) {
        return repository.getTongTienByIDHD(id);
    }
}
