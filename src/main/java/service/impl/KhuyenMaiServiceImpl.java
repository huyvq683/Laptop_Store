/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import custommodel.SanPhamViewKMResponse;
import domainmodel.KhuyenMai;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import repository.impl.KhuyenMaiRepository;

/**
 *
 * @author dinhv
 */
public class KhuyenMaiServiceImpl {

    private KhuyenMaiRepository khuyenMaiRepository;

    public KhuyenMaiServiceImpl() {
        khuyenMaiRepository = new KhuyenMaiRepository();
    }

    public boolean kiemTraTrong(String txt) {
        Matcher checknull = Pattern.compile("^\\s*$").matcher(txt);
        return checknull.matches();
    }

    public String insertOrUpdateKhuyenMai(KhuyenMai km) {
        Matcher checkGiaTri = Pattern.compile("^[0-9]*$").matcher(String.valueOf(km.getGiaTriKM()));
        if (kiemTraTrong(km.getTenKM()) || kiemTraTrong(String.valueOf(km.getGiaTriKM())) || km.getNgayBD() == null || km.getNgayKT() == null) {
            return "Nhập đầy đủ dữ liệu";
        }
        if (!checkGiaTri.matches()) {
            return "Giá trị chỉ được nhập từ các số từ 0-9";
        }
        if (km.getNgayBD().before(new Date())) {
            return "Ngày bắt đầu phải lớn hơn ngày hiện tại";
        }
        if (km.getNgayBD().after(km.getNgayKT())) {
            return "Ngày bắt đầu phải nhỏ hơn ngày kết thúc";
        }
        khuyenMaiRepository.insertOrUpdateKhuyenMai(km);
        return "Thêm thành công";
    }

    public List<KhuyenMai> getAllKhuyenMai(String txt, int trangThai) {
        List<KhuyenMai> list = new ArrayList<>();
        for (KhuyenMai khuyenMai : trangThai == 2 ? khuyenMaiRepository.getAllKhuyenMai() : locTrangThai(trangThai)) {
            if (khuyenMai.getTenKM().toLowerCase().contains(txt.toLowerCase())) {
                list.add(khuyenMai);
            }
        }
        return list;
    }

    public List<KhuyenMai> locTrangThai(int trangThai) {
        List<KhuyenMai> list = new ArrayList<>();
        for (KhuyenMai km : khuyenMaiRepository.getAllKhuyenMai()) {
            if (km.getTrangThai() == trangThai) {
                list.add(km);
            }
        }
        return list;
    }

    public void deleteSanPhamKhuyenMaiByMa(List<SanPhamViewKMResponse> listMaSP, KhuyenMai km) {
        khuyenMaiRepository.deleteSanPhamKhuyenMaiByMa(listMaSP, km);
    }

    public int genMaHD() {
        return khuyenMaiRepository.genMaHD();
    }

    public List<SanPhamViewKMResponse> getAllSanPham(String txt) {
        List<SanPhamViewKMResponse> list = new ArrayList<>();
        for (SanPhamViewKMResponse sp : khuyenMaiRepository.getAllSanPham()) {
            if (sp.getTen().toLowerCase().contains(txt.toLowerCase())) {
                list.add(sp);
            }
        }
        return list;
    }

    public void insertSanPhamKhuyenMai(List<SanPhamViewKMResponse> listMaSP, KhuyenMai km) {
        khuyenMaiRepository.insertSanPhamKhuyenMai(listMaSP, km);
    }

    public List<SanPhamViewKMResponse> getAllSanPhamKhuyenMai(KhuyenMai km) {
        return khuyenMaiRepository.getAllSanPhamKhuyenMai(km);
    }

    public boolean kiemTraKM(SanPhamViewKMResponse spvkmr, KhuyenMai km) {
        return khuyenMaiRepository.kiemTraKM(spvkmr, km);
    }

    public void updateTrangThaiKM() {
        khuyenMaiRepository.updateTrangThaiKM();
    }
}
