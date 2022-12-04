/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import domainmodel.Hang;
import java.util.List;
import java.util.UUID;
import repository.impl.HangRepository;
import repository.impl.RamRepository;
import service.HangService;

/**
 *
 * @author huyxo
 */
public class HangServiceImql implements HangService{
    
        HangRepository hangRepo = new HangRepository();

    @Override
    public List<Hang> getAll() {
        return hangRepo.getAll();
    }

    @Override
    public String add(Hang sp) {
        if (validate(sp) != null) {
            return validate(sp);
        } else {
            boolean add = hangRepo.add(sp);
            if (add) {
                return "Thêm thành công";
            } else {
                return "Thêm không thành công";
            }
        }
    }

    @Override
    public String upDate(Hang sp, UUID id) {
        if (validateHang(sp) != null) {
            return validateHang(sp);
        } else {
            boolean updateSP = hangRepo.update(sp, id);
            if (updateSP) {
                return "Sửa thành công";
            } else {
                return "Sửa không thành công";
            }
        }
    }

    @Override
    public String delete(UUID id) {
        boolean deleteSP = hangRepo.delete(id);
        if (deleteSP) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public String validate(Hang sp) {

        if (sp.getTen().trim().isBlank()) {
            return "Tên trống";
        }
        if (getOne(sp.getMa()) != null) {
            return "Trùng mã";
        }
        return null;
    }

    @Override
    public Hang getOne(String ma) {
        return hangRepo.getOne(ma);
    }

    @Override
    public Hang getOneHang(String ma) {
        return hangRepo.getOneHang(ma);
    }

    @Override
    public String validateHang(Hang spham) {
        if (spham.getTen().isEmpty()) {
            return "Tên trống";
        }
        return null;
    }
}
