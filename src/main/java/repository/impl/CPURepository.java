/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import domainmodel.CPU;
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
public class CPURepository {
    public List<CPU> getAll() {
        List<CPU> lists = new ArrayList<>();
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("From CPU ORDER BY Ma DESC");
            lists = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }

    public CPU getOneCPU(String ma) {
        String sql = " FROM CPU WHERE Ten = :ten ";
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(sql, CPU.class);
        query.setParameter("ten", ma);
        CPU cpu = (CPU) query.getSingleResult();
        return cpu;
    }

    public CPU getOne(String maSP) {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery(" SELECT new CPU (s.id , s.ten) FROM CPU s WHERE Ten =:ten ", CPU.class);
            query.setParameter("ten", maSP);
            CPU cpu = (CPU) query.getSingleResult();
            return cpu;
        } catch (Exception e) {
        }
        return null;
    }

    public Boolean add(CPU sp) {
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

    public Boolean update(CPU sp, UUID id) {
        Transaction tran = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            CPU s = session.get(CPU.class, id);
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
            CPU s = session.get(CPU.class,id);
            session.delete(s);
            tran.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public List<CPU> search(String seatchKey) {
        List<CPU> listSP = new ArrayList<>();
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("From CPU WHERE Ma like concat (:searchKey,'%') OR Ten like concat (:searchKey , '%')");
            query.setParameter("searchKey", seatchKey);
            listSP = query.getResultList();
            return listSP;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//    public static void main(String[] args) {
//        List<CPU> list = new SanPhamRepository().search("SP1");
//        for (CPU x : list) {
//            System.out.println(x.toString());
//        }
//    }
}
