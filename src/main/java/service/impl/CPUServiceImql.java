/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import domainmodel.CPU;
import java.util.List;
import java.util.UUID;
import repository.impl.CPURepository;
import repository.impl.SanPhamRepository;
import service.CPUService;

/**
 *
 * @author huyxo
 */
public class CPUServiceImql implements CPUService{
        CPURepository cpuRepo = new CPURepository();

    @Override
    public List<CPU> getAll() {
        return cpuRepo.getAll();
    }

    @Override
    public String add(CPU sp) {
        if (validate(sp) != null) {
            return validate(sp);
        } else {
            boolean add = cpuRepo.add(sp);
            if (add) {
                return "Thêm thành công";
            } else {
                return "Thêm không thành công";
            }
        }
    }

    @Override
    public String upDate(CPU sp, UUID id) {
        if (validateCPU(sp) != null) {
            return validateCPU(sp);
        } else {
            boolean updateSP = cpuRepo.update(sp, id);
            if (updateSP) {
                return "Sửa thành công";
            } else {
                return "Sửa không thành công";
            }
        }
    }

    @Override
    public String delete(UUID id) {
        boolean deleteSP = cpuRepo.delete(id);
        if (deleteSP) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public String validate(CPU sp) {
        
        if (sp.getTen().trim().isBlank()) {
            return "Tên trống";
        }
        if (getOne(sp.getMa()) != null) {
            return "Trùng mã";
        }
        return null;
    }

    @Override
    public CPU getOne(String ma) {
        return cpuRepo.getOne(ma);
    }

    @Override
    public CPU getOneCPU(String ma) {
        return cpuRepo.getOneCPU(ma);
    }

    @Override
    public String validateCPU(CPU spham) {
        if (spham.getTen().isEmpty()) {
            return "Tên trống";
        }
        return null;
    }

    @Override
    public List<CPU> search(String seatchKey) {
        return cpuRepo.search(seatchKey);
    }
}
