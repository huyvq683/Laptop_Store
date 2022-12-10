/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import domainmodel.OCung;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateUtil;

/**
 *
 * @author huyxo
 */
public class OCungRepository {
        public List<OCung> getAll() {
        List<OCung> lists = new ArrayList<>();
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("From OCung ORDER BY Ma DESC");
            lists = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }

    public OCung getOneOCung(String ma) {
        String sql = " FROM OCung WHERE Ten = :ten ";
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(sql, OCung.class);
        query.setParameter("ten", ma);
        OCung cpu = (OCung) query.getSingleResult();
        return cpu;
    }

    public OCung getOne(String maSP) {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery(" SELECT new OCung (s.id , s.ten) FROM OCung s WHERE Ten =:ten ", OCung.class);
            query.setParameter("ten", maSP);
            OCung cpu = (OCung) query.getSingleResult();
            return cpu;
        } catch (Exception e) {
        }
        return null;
    }

    public Boolean add(OCung sp) {
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

    public Boolean update(OCung sp, UUID id) {
        Transaction tran = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            OCung s = session.get(OCung.class, id);
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

    public Boolean delete(UUID id) {
        Transaction tran = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            OCung s = session.get(OCung.class,id);
            session.delete(s);
            tran.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public List<OCung> search(String seatchKey) {
        List<OCung> listSP = new ArrayList<>();
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("From OCung WHERE Ma like concat (:searchKey,'%') OR Ten like concat (:searchKey , '%')");
            query.setParameter("searchKey", seatchKey);
            listSP = query.getResultList();
            return listSP;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
