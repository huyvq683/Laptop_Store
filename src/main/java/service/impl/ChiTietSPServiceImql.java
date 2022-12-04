/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import custommodel.ChiTietSPResponse;
import domainmodel.ChiTietSP;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import repository.impl.ChiTietSPRepository;

import service.ChiTietSPService;

/**
 *
 * @author Đức Hiệu
 */
public class ChiTietSPServiceImql implements ChiTietSPService {

    private ChiTietSPRepository repository = new ChiTietSPRepository();

    @Override
    public List<ChiTietSP> getAllChiTietSP() {
        return repository.getAllChiTietSP();
    }

    @Override
    public String delete(ChiTietSP ctsp, UUID id) {
        boolean delete = repository.delete(ctsp, id);
        if (delete) {
            return "Xóa thành công";
        } else {
            return "Xóa không thành công";
        }
    }

    @Override
    public ChiTietSP getOne(String seriall) {
        return repository.getOne(seriall);
    }

    @Override
    public List<ChiTietSP> seatch(String seatch) {
        return repository.search(seatch);
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

    @Override
    public List<ChiTietSP> getOneGia(String gia1, String gia2) {
        return repository.getOneGia(gia1, gia2);
    }

    @Override
    public String add(ChiTietSP ctsp) {
        if (validate(ctsp) != null) {
            return validate(ctsp);
        } else {
            Boolean add = repository.add(ctsp);
            if (add) {
                return "Thêm thành công";
            } else {
                return "Thêm không thành công";
            }
        }

    }

    @Override
    public String update(ChiTietSP ctsp, UUID id) {
        Boolean update = repository.upDate(ctsp, id);
        if (update) {
            return "Sửa thành công";
        } else {
            return "Sửa không thành công";
        }
    }

    @Override
    public String validate(ChiTietSP ctsp) {
        if (ctsp.getSerial().isEmpty()) {
            return "Serial trống";
        }
        if (getOne(ctsp.getSerial()) != null) {
            return "Serial đã tốn tại";
        }
        return null;
    }
    @Override
    public List<ChiTietSPResponse> getAll() {
        return repository.getAll();
    }

    @Override
    public ChiTietSP getBySerialChiTietSP(String serial) {
        return repository.getBySerialChiTietSP(serial);
    }

    @Override
    public void updateTinhTrangSP(ChiTietSP chiTietSP, UUID id) {
        repository.updateTinhTrangSP(chiTietSP, id);
    }


    @Override
    public void updateTinhTrangSP(ChiTietSP chiTietSP) {
         repository.updateTinhTrangSP(chiTietSP);
    }

    @Override
    public void updateTTSPDangBan(BigDecimal gia) {
        repository.updateTTSPDangBan(gia);
    }

    @Override
    public List<ChiTietSPResponse> getAllCTSP(String cPU, String card, BigDecimal gia, String hang, String oCung, String ram) {
        return repository.getAllCTSP(cPU, card, gia, hang, oCung, ram);
    }
}
