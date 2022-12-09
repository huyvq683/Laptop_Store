/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;
import domainmodel.Hang;
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
public class HangRepository {
    public List<Hang> getAll() {
        List<Hang> lists = new ArrayList<>();
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("From Hang ORDER BY Ma DESC");
            lists = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }

    public Hang getOneHang(String ma) {
        String sql = " FROM Hang WHERE Ten = :ten ";
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(sql, Hang.class);
        query.setParameter("ten", ma);
        Hang cpu = (Hang) query.getSingleResult();
        return cpu;
    }

    public Hang getOne(String maSP) {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery(" SELECT new Hang (s.id , s.ten) FROM Hang s WHERE Ten =:ten ", Hang.class);
            query.setParameter("ten", maSP);
            Hang cpu = (Hang) query.getSingleResult();
            return cpu;
        } catch (Exception e) {
        }
        return null;
    }

    public Boolean add(Hang sp) {
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

    public Boolean update(Hang sp, UUID id) {
        Transaction tran = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            Hang s = session.get(Hang.class, id);
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
            Hang s = session.get(Hang.class,id);
            session.delete(s);
            tran.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }
}
