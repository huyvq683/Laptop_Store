/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import domainmodel.SanPham;
import java.util.List;
import java.util.UUID;
import repository.impl.SanPhamRepository;
import service.SanPhamService;

/**
 *
 * @author Đức Hiệu
 */
public class SanPhamServiceImpl implements SanPhamService {

    SanPhamRepository sanPhamRe = new SanPhamRepository();

    @Override
    public List<SanPham> getAllSanPham() {
        return sanPhamRe.getAllSanPham();
    }

    @Override
    public String addSanPham(SanPham sp) {
        if (validate(sp) != null) {
            return validate(sp);
        } else {
            boolean add = sanPhamRe.addSanPham(sp);
            if (add) {
                return "Thêm thành công";
            } else {
                return "Thêm không thành công";
            }
        }
    }

    @Override
    public String upDateSanPham(SanPham sp, UUID id) {
        if (validateSP(sp) != null) {
            return validateSP(sp);
        } else {
            boolean updateSP = sanPhamRe.updateSanPham(sp, id);
            if (updateSP) {
                return "Sửa thành công";
            } else {
                return "Sửa không thành công";
            }
        }
    }

    @Override
    public String deleteSanPham(UUID id) {
        boolean deleteSP = sanPhamRe.deleteSanPham(id);
        if (deleteSP) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public String validate(SanPham sp) {
//        if (sp.getMa().trim().isBlank()) {
//            return "Mã trống";
//        }
        if (sp.getTen().trim().isBlank()) {
            return "Tên trống";
        }
        if (getOne(sp.getTen()) != null) {
            return "Trùng tên";
        }
        return null;
    }

    @Override
    public SanPham getOne(String ma) {
        return sanPhamRe.getOne(ma);
    }

    @Override
    public SanPham getOneSP(String ma) {
        return sanPhamRe.getOneSP(ma);
    }

    @Override
    public String validateSP(SanPham spham) {
        if (spham.getMa().isEmpty()) {
            return "Mã trống";
        }
        if (spham.getTen().isEmpty()) {
            return "Tên trống";
        }
        return null;
    }

    @Override
    public List<SanPham> search(String seatchKey) {
        return sanPhamRe.search(seatchKey);
    }

    @Override
    public String upDateTrangThai(SanPham sp, UUID id) {
        Boolean trangThai = sanPhamRe.upDateTrangThai(sp, id);
        if (trangThai) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public List<SanPham> getAllSP() {
        return sanPhamRe.getAllSP();
    }

}
