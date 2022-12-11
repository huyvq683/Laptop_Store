/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import domainmodel.NhanVien;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public List<NhanVien> getAllPage(int row) {
        return nhanVienRepository.getAllPage(row);
    }

    private String convertDate(Date ngaySinh) {
        String date;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        date = dateFormat.format(ngaySinh);
        return date;
    }

    @Override
    public String addOrUpdate(NhanVien nhanVien) {
        if (nhanVien.getMa().isEmpty()) {
            return "Vui lòng nhập mã!!";
        } else if (nhanVien.getHoTen().isEmpty()) {
            return "Vui lòng nhập tên!!";
        } else if (nhanVien.getSdt().isEmpty()) {
            return "Vui lòng nhập số điện thoại!!";
        } else if (nhanVien.getDiaChi().isEmpty()) {
            return "Vui lòng nhập địa chỉ!!";
        } else if (nhanVien.getEmail().isEmpty()) {
            return "Vui lòng nhập email!!";
        } else if (convertDate(nhanVien.getNgaySinh()).isEmpty()) {
            return "Vui lòng nhập ngày sinh!!";
        } else if (!convertDate(nhanVien.getNgaySinh()).matches("^([0-2][0-9]|(3)[0-1])(\\-)(((0)[0-9])|((1)[0-2]))(\\-)\\d{4}$")) {
            return "Vui lòng nhập ngày sinh theo định dạng dd-mm-yyyy";
        } else if (Integer.valueOf(convertDate(nhanVien.getNgaySinh()).substring(6, 10)) < 1950 || Integer.valueOf(convertDate(nhanVien.getNgaySinh()).substring(6, 10)) > 2022) {
            return "Năm sinh không hợp lệ" + Integer.valueOf(convertDate(nhanVien.getNgaySinh()).substring(6, 10));
        } else if (!nhanVien.getEmail().matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")) {
            return "Vui lòng nhập email đúng định dạng!!";
        } else {
            Boolean add = nhanVienRepository.addOrUpdate(nhanVien);
            if (add) {
                return "Thành công";
            } else {
                return "Thất bại";
            }
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
        } else if (!nv.getMatKhau().trim().equalsIgnoreCase(getMd5(mk))) {
            return "Mật Khẩu Sai";
        } else if (nv.getTrangThai() == 1) {
            return "Nhân Viên Đã Xóa";
        }
        return null;
    }

    public String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public NhanVien getOneByMa(String ma, String email) {
        return nhanVienRepository.getOneByMa(ma, email);
    }
}
