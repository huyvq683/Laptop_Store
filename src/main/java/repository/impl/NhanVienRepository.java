/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import customModel.ChucVuResponse;
import domainModel.NhanVien;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utiliti.HibernateUtil;

/**
 *
 * @author FPT
 */
public class NhanVienRepository {
    public NhanVien getOne(String email) {
        NhanVien nhanVien = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT n FROM NhanVien n "
                    + " WHERE n.email = :email");
            query.setParameter("email", email);
            nhanVien = (NhanVien) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return nhanVien;
    }
    
        public static void main(String[] args) {
//        List<ChucVuResponse> lists = new ChucVuRepository().getAll();
        NhanVien chucVu = new NhanVienRepository().getOne("huy@gmail.com");
        System.out.println(chucVu);
    }
}
