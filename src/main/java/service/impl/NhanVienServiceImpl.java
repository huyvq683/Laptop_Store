/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import domainmodel.NhanVien;
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
    public List<NhanVien> getAll() {
        return nhanVienRepository.getAll();
    }

    @Override
    public String addOrUpdate(NhanVien nhanVien) {
        Boolean add = nhanVienRepository.addOrUpdate(nhanVien);
        if (add) {
            return "Thành công";
        } else {
            return "Thất bại";
        }
    }

    @Override
    public List<NhanVien> search(String sdt) {
        return nhanVienRepository.search(sdt);
    }

    @Override
    public String validateLogin(String email, String mk, NhanVien nv) {
        if (nv == null) {
            return "Tài Khoản Không Tồn Tại";
        }
        if ( mk.trim() == null) {
            return "Mật Khẩu Trống";
        }
        if (!nv.getMatKhau().trim().equalsIgnoreCase(mk.trim())) {
            return "Mật Khẩu Sai";
        }
        if (nv.getTrangThai() == 1) {
            return "Nhân Viên Đã Xóa";
        }
        return null;
    }
}
