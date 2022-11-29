package service;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */


/**
 *
 * @author Đức Hiệu
 */
public interface QuenMatKhauService {

    String getOne(String email);

    String validateTaiKhoan(String taiKhoan);

    String validateMaXacThuc(String maXT);

    String update(String mk, String email);
}
