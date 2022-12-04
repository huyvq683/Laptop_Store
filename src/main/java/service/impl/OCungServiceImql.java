/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import domainmodel.OCung;
import java.util.List;
import java.util.UUID;
import repository.impl.HangRepository;
import repository.impl.OCungRepository;
import service.OCungService;

/**
 *
 * @author huyxo
 */
public class OCungServiceImql implements OCungService {

    OCungRepository ocungRepo = new OCungRepository();

    @Override
    public List<OCung> getAll() {
        return ocungRepo.getAll();
    }

    @Override
    public String add(OCung sp) {
        if (validate(sp) != null) {
            return validate(sp);
        } else {
            boolean add = ocungRepo.add(sp);
            if (add) {
                return "Thêm thành công";
            } else {
                return "Thêm không thành công";
            }
        }
    }

    @Override
    public String upDate(OCung sp, UUID id) {
        if (validateOCung(sp) != null) {
            return validateOCung(sp);
        } else {
            boolean updateSP = ocungRepo.update(sp, id);
            if (updateSP) {
                return "Sửa thành công";
            } else {
                return "Sửa không thành công";
            }
        }
    }

    @Override
    public String delete(UUID id) {
        boolean deleteSP = ocungRepo.delete(id);
        if (deleteSP) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public String validate(OCung sp) {

        if (sp.getTen().trim().isBlank()) {
            return "Tên trống";
        }
        if (getOne(sp.getMa()) != null) {
            return "Trùng mã";
        }
        return null;
    }

    @Override
    public OCung getOne(String ma) {
        return ocungRepo.getOne(ma);
    }

    @Override
    public OCung getOneOCung(String ma) {
        return ocungRepo.getOneOCung(ma);
    }

    @Override
    public String validateOCung(OCung spham) {
        if (spham.getTen().isEmpty()) {
            return "Tên trống";
        }
        return null;
    }
}
