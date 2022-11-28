/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import custommodel.ViewHoaDonReponse;
import domainmodel.HoaDon;
import domainmodel.NhanVien;
import java.util.List;
import org.hibernate.query.Query;

import repository.impl.HoaDonRepository;
import service.HoaDonService;

/**
 *
 * @author WIN11
 */
public class HoaDonServiceImpl implements HoaDonService {

    private HoaDonRepository hoaDonRepository = new HoaDonRepository();

    @Override
    public List<ViewHoaDonReponse> getByOne(int tt) {
        return hoaDonRepository.getByOne(tt);
    }

    @Override
    public String update(HoaDon hd) {
        if (hoaDonRepository.update(hd)) {
            return "Thành Công";
        } else {
            return "Thất Bại";
        }
    }

//    public NhanVien getMaNV(String ma) {
//        return hoaDonRepository.getMaNV(ma);
//    }
//
//    public KhachHang getMaKH(String ma) {
//        return hoaDonRepository.getMaKH(ma);
//    }
    @Override
    public List<HoaDon> get_All() {
        return hoaDonRepository.get_All();
    }

    @Override
    public ViewHoaDonReponse getOneByMa(String ma) {
        return hoaDonRepository.getOneByMa(ma);
    }

    @Override
    public List<ViewHoaDonReponse> getByNV(String ma) {
        return hoaDonRepository.getByNV(ma);
    }

    @Override
    public List<ViewHoaDonReponse> getByMaKH(String ma) {
        return hoaDonRepository.getByMaKH(ma);
    }

    @Override
    public List<ViewHoaDonReponse> getByNgayTao(String ma) {
        return hoaDonRepository.getByNgayTao(ma);
    }

    @Override
    public HoaDon getByMa(String ma) {
        return hoaDonRepository.getOne(ma);
    }

    @Override
    public List<ViewHoaDonReponse> getAll() {
        return hoaDonRepository.getAll();
    }

}
