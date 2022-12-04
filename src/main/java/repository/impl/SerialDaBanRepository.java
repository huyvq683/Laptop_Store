/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import domainmodel.ChiTietSP;
import domainmodel.HoaDonChiTiet;
import domainmodel.SerialDaBan;
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
public class SerialDaBanRepository {

    public Boolean add(List<String> lists, HoaDonChiTiet hdct) {
        Transaction tran = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            for (String serial : lists) {
                SerialDaBan sdb = new SerialDaBan();
                ChiTietSP ctsp = getCTSPBySerial(serial);
                sdb.setIdHDCT(hdct);
                sdb.setMa(ctsp.getSerial());
                session.save(sdb);
            }
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    
    public HoaDonChiTiet getByIdHoaDonCT(UUID id){
        HoaDonChiTiet hoaDonChiTiet = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT n "
                    + "FROM HoaDonChiTiet n WHERE n.idCTSP.id = :id");
            query.setParameter("id", id);
            hoaDonChiTiet = (HoaDonChiTiet) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return hoaDonChiTiet;
    }

    public ChiTietSP getCTSPBySerial(String serial) {
        ChiTietSP ctsp = null;
        try {
            Session sess = HibernateUtil.getFACTORY().openSession();
            Query q = sess.createQuery("FROM ChiTietSP WHERE serial = :serial");
            q.setParameter("serial", serial);
            ctsp = (ChiTietSP) q.getSingleResult();
            sess.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ctsp;
    }

    public Boolean delete(UUID id) {
        boolean check = false;
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession();) {
            transaction = session.beginTransaction();
            SerialDaBan sdb = session.get(SerialDaBan.class,
                     id);
            session.delete(sdb);
            transaction.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check;
    }
}
