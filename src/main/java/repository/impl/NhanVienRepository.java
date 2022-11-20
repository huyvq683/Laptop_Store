/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import customModel.NhanVienResponse;
import domainModel.NhanVien;
import java.util.List;
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

   public List<NhanVienResponse> getAll() {
        try ( Session session = HibernateUtil.getFACTORY().openSession();) {
            String sql = "SELECT new customModel.NhanVienResponse (n.id, n.ma, n.hoTen, n.email, n.matKhau, n.chucVu, n.trangThai) FROM NhanVien n";
            Query<NhanVienResponse> query = session.createQuery(sql);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static void main(String[] args) {
//        List<NhanVienResponse> lists = new NhanVienRepository().getAll();
        NhanVien lists = new NhanVienRepository().getOne("huy@gmail.com");
        System.out.println(lists);
    }
}
