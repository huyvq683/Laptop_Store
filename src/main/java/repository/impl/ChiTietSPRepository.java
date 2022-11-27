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
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utility.HibernateUtil;

/**
 *
 * @author FPT
 */
public class ChiTietSPRepository {

    public List<ChiTietSPResponse> getAll() {
        List<ChiTietSPResponse> lists = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT new custommodel.ChiTietSPResponse "
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
            Query query = session.createQuery("SELECT new custommodel.ChiTietSPResponse "
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
            Query query = session.createQuery("SELECT c "
                    + "FROM ChiTietSP c WHERE c.serial = :serial");
            query.setParameter("serial", serial);
            chiTietSP = (ChiTietSP) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return chiTietSP;
    }
    
    public boolean updateTinhTrangSP(ChiTietSP chiTietSP, UUID id){
        boolean check = true;
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
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
    
    public static void main(String[] args) {
        List<ChiTietSPResponse>lists = new ChiTietSPRepository().getList();
        System.out.println(lists);
    }
}
