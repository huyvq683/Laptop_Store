/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import custommodel.ChiTietSPResponse;
import domainmodel.ChiTietSP;
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
public class ChiTietSPRepository {

    public List<ChiTietSP> getAllChiTietSP() {
        List<ChiTietSP> listCTSP = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("From ChiTietSP");
            listCTSP = query.getResultList();
        } catch (Exception e) {
        }
        return listCTSP;
    }

    private String fromTable = "From SanPham";
    private Session session = HibernateUtil.getFACTORY().openSession();

    public ChiTietSP getOne(String serial) {
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery(" SELECT new ChiTietSP (ct.id , ct.serial) FROM ChiTietSP ct WHERE Serial =:serial ", ChiTietSP.class);
            query.setParameter("serial", serial);
            ChiTietSP ctsp = (ChiTietSP) query.getSingleResult();
            return ctsp;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean add(ChiTietSP ctsp) {
        Transaction transantion = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transantion = session.beginTransaction();
            session.save(ctsp);
            transantion.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public Boolean upDate(ChiTietSP ctsp, UUID id) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            ChiTietSP ctSP = session.get(ChiTietSP.class, id);
            ctSP.setId(id);
            ctSP.setCard(ctsp.getCard());
            ctSP.setCPU(ctsp.getCPU());
            ctSP.setCreatedDate(ctsp.getCreatedDate());
            ctSP.setGia(ctsp.getGia());
            ctSP.setHang(ctsp.getHang());
            ctSP.setLastModifiedDate(ctsp.getLastModifiedDate());
            ctSP.setRam(ctsp.getRam());
            ctSP.setSerial(ctsp.getSerial());
            ctSP.setIdSanPham(ctsp.getIdSanPham());
            session.update(ctSP);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public Boolean delete(ChiTietSP ctsp, UUID id) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            ChiTietSP ctSP = session.get(ChiTietSP.class, id);
            session.delete(ctSP);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public List<ChiTietSP> search(String searchKey) {
        List<ChiTietSP> listCTSP = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("From ChiTietSP WHERE Serial like concat (:searchKey,'%') OR CPU like concat (:searchKey,'%') OR Hang like concat (:searchKey,'%') OR Ram like concat (:searchKey,'%') OR CardMH like concat (:searchKey,'%') OR OCung like concat (:searchKey,'%')");
            query.setParameter("searchKey", searchKey);
            listCTSP = query.getResultList();
            return listCTSP;
        } catch (Exception e) {
        }
        return null;
    }

    public List<ChiTietSPResponse> getAll() {
        List<ChiTietSPResponse> lists = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            org.hibernate.query.Query query = session.createQuery("SELECT new custommodel.ChiTietSPResponse "
                    + "(ct.idSanPham.ma, ct.idSanPham.ten,"
                    + "ct.cPU, ct.ram, ct.card, ct.oCung, ct.hang, ct.tinhTrang, ct.gia, COUNT(ct.idSanPham)) "
                    + "FROM ChiTietSP ct WHERE ct.tinhTrang = 0"
                    + "GROUP BY ct.idSanPham.ma, ct.idSanPham.ten, ct.cPU, ct.ram, ct.card, ct.oCung, ct.hang, ct.tinhTrang, ct.gia");
            lists = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }

    public List<ChiTietSPResponse> getList() {
        List<ChiTietSPResponse> lists = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            org.hibernate.query.Query query = session.createQuery("SELECT new custommodel.ChiTietSPResponse "
                    + "(ct.id, ct.serial) "
                    + "FROM ChiTietSP ct WHERE ct.tinhTrang = 0");
            lists = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }

    public ChiTietSP getBySerialChiTietSP(String serial) {
        ChiTietSP chiTietSP = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            org.hibernate.query.Query query = session.createQuery("SELECT c "
                    + "FROM ChiTietSP c WHERE c.serial = :serial");
            query.setParameter("serial", serial);
            chiTietSP = (ChiTietSP) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return chiTietSP;
    }

    public boolean updateTinhTrangSP(ChiTietSP chiTietSP, UUID id) {
        boolean check = true;
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            ChiTietSP ctsp = session.get(ChiTietSP.class, id);
            ctsp.setTinhTrang(1);
            session.update(ctsp);
            transaction.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check;
    }
    
}