/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;



import domainmodel.ChiTietSP;
import java.util.List;
import java.util.UUID;
import repository.impl.ChiTietSPRepository;


import service.ChiTietSPService;

/**
 *
 * @author Đức Hiệu
 */
public class ChiTietSPServiceImql implements ChiTietSPService {

    ChiTietSPRepository repository = new ChiTietSPRepository();

    @Override
    public List<ChiTietSP> getAllChiTietSP() {
        return repository.getAllChiTietSP();
    }

    @Override
    public String add(ChiTietSP ctsp) {
        if (validate(ctsp) != null) {
            return validate(ctsp);
        } else {
            boolean add = repository.Add(ctsp);
            if (add) {
                return "Thêm thành công";
            } else {
                return "Thêm không thành công";
            }
        }
    }

    @Override
    public String update(ChiTietSP ctsp, UUID id) {
        if (ctsp.getSerial().isEmpty()) {
            return "Serial chống";
        } else {
            boolean update = repository.Update(ctsp, id);
            if (update) {
                return "Sửa thành công";
            } else {
                return "Sửa không thành công";
            }
        }
    }

    @Override
    public String delete(ChiTietSP ctsp, UUID id) {
        boolean delete = repository.Delete(ctsp, id);
        if (delete) {
            return "Xóa thành công";
        } else {
            return "Xóa không thành công";
        }
    }

    @Override
    public ChiTietSP getOne(String serial) {
        return repository.getOne(serial);
    }

    @Override
    public List<ChiTietSP> seatch(String ram) {
        return repository.seatch(ram);
    }

    public String validate(ChiTietSP ctsp) {
        if (ctsp.getSerial().trim().isBlank()) {
            return "Serial trống";
        }
        if (ctsp.getCPU().trim().isBlank()) {
            return "CPU trống";
        }
        if (ctsp.getHang().trim().isBlank()) {
            return "Hãng trống";
        }
        if (ctsp.getRam().trim().isBlank()) {
            return "Ram trống";
        }
        if (ctsp.getCard().trim().isBlank()) {
            return "Card trống";
        }
        if (getOne(ctsp.getSerial()) != null) {
            return "Serial trùng";
        }
        return null;
    }

    public String checkInt(String gia) {
        if (gia.trim().isBlank()) {
            return "Giá trống";
        }
        if (gia.trim().matches("^[A-Za-z]$")) {
            return "Giá sai định dạng";
        }
        return null;
    }
}
