/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import domainmodel.Ram;
import java.util.List;
import java.util.UUID;
import repository.impl.CardRepository;
import repository.impl.RamRepository;
import service.RamService;

/**
 *
 * @author huyxo
 */
public class RamServiceImql implements RamService{

    RamRepository ramRepo = new RamRepository();

    @Override
    public List<Ram> getAll() {
        return ramRepo.getAll();
    }

    @Override
    public String add(Ram sp) {
        if (validate(sp) != null) {
            return validate(sp);
        } else {
            boolean add = ramRepo.add(sp);
            if (add) {
                return "Thêm thành công";
            } else {
                return "Thêm không thành công";
            }
        }
    }

    @Override
    public String upDate(Ram sp, UUID id) {
        if (validateRam(sp) != null) {
            return validateRam(sp);
        } else {
            boolean updateSP = ramRepo.update(sp, id);
            if (updateSP) {
                return "Sửa thành công";
            } else {
                return "Sửa không thành công";
            }
        }
    }

    @Override
    public String delete(UUID id) {
        boolean deleteSP = ramRepo.delete(id);
        if (deleteSP) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public String validate(Ram sp) {

        if (sp.getTen().trim().isBlank()) {
            return "Tên trống";
        }
        if (getOne(sp.getMa()) != null) {
            return "Trùng mã";
        }
        return null;
    }

    @Override
    public Ram getOne(String ma) {
        return ramRepo.getOne(ma);
    }

    @Override
    public Ram getOneRam(String ma) {
        return ramRepo.getOneRam(ma);
    }

    @Override
    public String validateRam(Ram spham) {
        if (spham.getTen().isEmpty()) {
            return "Tên trống";
        }
        return null;
    }

}
