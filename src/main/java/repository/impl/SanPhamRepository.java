/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;


import domainmodel.SanPham;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateUtil;

/**
 *
 * @author Đức Hiệu
 */
public class SanPhamRepository {

    public List<SanPham> getAllSanPham() {
        List<SanPham> lists = new ArrayList<>();
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("From SanPham");
            lists = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }

    public SanPham getOneSP(String ma) {
        String sql = " FROM SanPham WHERE Ma = :ma ";
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(sql, SanPham.class);
        query.setParameter("ma", ma);
        SanPham sanPham = (SanPham) query.getSingleResult();
        return sanPham;
    }
    public SanPham getOne(String maSP) {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery(" SELECT new SanPham (s.id , s.ma) FROM SanPham s WHERE Ma =:ma ", SanPham.class);
            query.setParameter("ma", maSP);
            SanPham sp = (SanPham) query.getSingleResult();
            return sp;
        } catch (Exception e) {
             e.printStackTrace(System.out);
        }
        return null;
    }

    public static void main(String[] args) {
        List<SanPham> lists = new SanPhamRepository().getAllSanPham();
        lists.forEach(s -> System.out.println(s.toString()));
    }

    public Boolean addSanPham(SanPham sp) {
        Transaction tran = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            session.save(sp);
            tran.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public Boolean updateSanPham(SanPham sp, UUID id) {
        Transaction tran = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            SanPham s = session.get(SanPham.class, id);
            s.setMa(sp.getMa());
            s.setTen(sp.getTen());
            s.setCreatedDate(sp.getCreatedDate());
            s.setLastModifiedDate(sp.getLastModifiedDate());
            session.update(s);
            tran.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public Boolean deleteSanPham(UUID id) {
        Transaction tran = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            SanPham s = session.get(SanPham.class,
                    id);
            session.delete(s);
            tran.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }
}
