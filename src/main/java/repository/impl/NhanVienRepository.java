/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import domainmodel.NhanVien;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utility.HibernateUtil;

/**
 *
 * @author FPT
 */
public class NhanVienRepository {

    private String fromTable = "FROM NhanVien";

    private Session session = HibernateUtil.getFACTORY().openSession();

    public NhanVien getOne(String email) {
        NhanVien nv = null;
        try {
            String sql = fromTable + " Where email = :email";
            Query query = session.createQuery(sql);
            query.setParameter("email", email);
            nv = (NhanVien) query.getSingleResult();
            return nv;
        } catch (Exception e) {
            return null;
        }
    }

    public List<NhanVien> getAll() {
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            javax.persistence.Query query = session.createQuery(fromTable, NhanVien.class);
            List<NhanVien> list = query.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<NhanVien> search(String searchKey) {
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String sql = fromTable + " WHERE Ma like concat (:searchKey,'%') OR HoTen like concat (:searchKey,'%') OR sdt like concat (:searchKey,'%') OR Email like concat (:searchKey,'%')";
            javax.persistence.Query query = session.createQuery(sql, NhanVien.class);
            query.setParameter("searchKey", searchKey);
            List<NhanVien> search = query.getResultList();
            return search;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean addOrUpdate(NhanVien nhanVien) {
        boolean check = false;
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(nhanVien);
            transaction.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check;
    }

    public static void main(String[] args) {
        NhanVien nv = new NhanVienRepository().getOne("hung@fpt.edu");
        System.out.println(nv.toString());
    }

}
