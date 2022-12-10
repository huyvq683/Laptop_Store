/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import domainmodel.CardMH;
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
public class CardRepository {
    
    public List<CardMH> getAll() {
        List<CardMH> lists = new ArrayList<>();
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("From CardMH ORDER BY Ma DESC");
            lists = query.getResultList(); 
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }

    public CardMH getOneCard(String ma) {
        String sql = " FROM CardMH WHERE Ten = :ten ";
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(sql, CardMH.class);
        query.setParameter("ten", ma);
        CardMH cpu = (CardMH) query.getSingleResult();
        return cpu;
    }

    public CardMH getOne(String maSP) {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery(" SELECT new CardMH (s.id , s.ten) FROM CardMH s WHERE Ten =:ten ", CardMH.class);
            query.setParameter("ten", maSP);
            CardMH cpu = (CardMH) query.getSingleResult();
            return cpu;
        } catch (Exception e) {
        }
        return null;
    }

    public Boolean add(CardMH sp) {
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

    public Boolean update(CardMH sp, UUID id) {
        Transaction tran = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            CardMH s = session.get(CardMH.class, id);
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
            CardMH s = session.get(CardMH.class,id);
            session.delete(s);
            tran.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public List<CardMH> search(String seatchKey) {
        List<CardMH> listSP = new ArrayList<>();
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("From CardMH WHERE Ma like concat (:searchKey,'%') OR Ten like concat (:searchKey , '%')");
            query.setParameter("searchKey", seatchKey);
            listSP = query.getResultList();
            return listSP;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
