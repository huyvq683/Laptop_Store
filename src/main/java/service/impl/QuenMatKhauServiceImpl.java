package service.impl;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import repository.impl.QuenMatKhauRepository;
import service.QuenMatKhauService;

/**
 *
 * @author Đức Hiệu
 */
public class QuenMatKhauServiceImpl implements QuenMatKhauService {

    private QuenMatKhauRepository quenMKRe = new QuenMatKhauRepository();

    @Override
    public String getOne(String email) {
        if (quenMKRe.getOne(email) == true) {
            return "Tài khoản chính xác";
        } else {
            return "Tài khoản không chính xác";
        }
    }

    @Override
    public String validateTaiKhoan(String taiKhoan) {
        if (taiKhoan.trim().isBlank()) {
            return "Tài khoản không được để trống";
        }
        if (!taiKhoan.trim().matches("^(.+)@(\\S+)$")) {
            return "Tài khoản sai định dạng";
        }
        return null;
    }

    @Override
    public String validateMaXacThuc(String maXT) {
        if (maXT.trim().isBlank()) {
            return "Mã xác thực không được để trống";
        }
        if (!maXT.trim().matches("^[0-9]+$")) {
            return "Mã xác thực sai định dạng";
        }
        return null;
    }

    @Override
    public String update(String mk, String email) {
        boolean up = quenMKRe.updateMatKhau(mk, email);
        if (up) {
            return "Đổi mật khẩu thành công";
        } else {
            return "Đổi mật khẩu thất bại";
        }
    }

}
