/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import domainmodel.SanPham;
import java.lang.annotation.Native;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import utility.HibernateUtil;

/**
 *
 * @author Đức Hiệu
 */
public class SanPhamRepository {

    public List<SanPham> getAllSanPham() {
        List<SanPham> lists = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            NativeQuery query = session.createNativeQuery("SELECT s.id , s.ma, s.ten, s.createdDate , s.lastModifiedDate From SanPham s"
                    + " GROUP BY s.id , s.ma, s.ten, s.createdDate , s.lastModifiedDate "
                    + " ORDER BY MAX(CONVERT(INT, SUBSTRING(ma, 3, 10))) DESC", SanPham.class);
            lists = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }

    public List<SanPham> getAllSP() {
        List<SanPham> lists = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("From SanPham ORDER BY Ma DESC");
            lists = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }

    public SanPham getOneSP(String ma) {
        String sql = " FROM SanPham WHERE Ten = :ten ";
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(sql, SanPham.class);
        query.setParameter("ten", ma);
        SanPham sanPham = (SanPham) query.getSingleResult();
        return sanPham;
    }

    public SanPham getOne(String maSP) {
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery(" SELECT new SanPham (s.id , s.ten) FROM SanPham s WHERE Ten =:ten ", SanPham.class);
            query.setParameter("ten", maSP);
            SanPham sp = (SanPham) query.getSingleResult();
            return sp;
        } catch (Exception e) {
        }
        return null;
    }

    public Boolean addSanPham(SanPham sp) {
        Transaction tran = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            session.save(sp);
            tran.commit();
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public Boolean updateSanPham(SanPham sp, UUID id) {
        Transaction tran = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
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
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
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

    public Boolean upDateTrangThai(SanPham sp, UUID id) {
        Transaction tran = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            SanPham sanPham = session.get(SanPham.class, id);
            session.update(sanPham);
            tran.commit();
            return true;
        } catch (Exception e) {
        }
        return null;
    }

    public List<SanPham> search(String seatchKey) {
        List<SanPham> listSP = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("From SanPham WHERE Ma like concat (:searchKey,'%') OR Ten like concat (:searchKey , '%')");
            query.setParameter("searchKey", seatchKey);
            listSP = query.getResultList();
            return listSP;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
