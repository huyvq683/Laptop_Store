/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import domainmodel.KhachHang;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateUtil;

/**
 *
 * @author daohi
 */
public class KhachHangRepository {

    private String fromTable = "FROM KhachHang";

    private Session session = HibernateUtil.getFACTORY().openSession();

    public List<KhachHang> getAll() {
        try ( Session session = HibernateUtil.getFACTORY().openSession();) {
            Query query = session.createQuery(fromTable);
            List<KhachHang> list = query.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public KhachHang getMa(String ma) {
        String sql = fromTable + " WHERE ma = :ma";
        Query query = session.createQuery(sql);
        query.setParameter("ma", ma);
        KhachHang kh = (KhachHang) query.getSingleResult();
        return kh;
    }

    public KhachHang getEmail(String email) {
        String sql = fromTable + " WHERE email = :email";
        Query query = session.createQuery(sql);
        query.setParameter("email", email);
        KhachHang kh = (KhachHang) query.getSingleResult();
        return kh;
    }

    public Boolean add(KhachHang kh) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(kh);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public Boolean delete(KhachHang kh) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(kh);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }
}
