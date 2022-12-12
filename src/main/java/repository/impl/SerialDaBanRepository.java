/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import domainmodel.ChiTietSP;
import domainmodel.HoaDonChiTiet;
import domainmodel.SerialDaBan;
import java.util.Date;
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

    public Boolean add(List<String> listSerial) {
        Transaction tran = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            for (String serial : listSerial) {
                ChiTietSP chiTietSP = getCTSPBySerial(serial);
                HoaDonChiTiet hoaDonChiTiet = getIdHoaDonChiTiet(serial);
                SerialDaBan serialDaBan = new SerialDaBan();
                serialDaBan.setMa(chiTietSP.getSerial());
                serialDaBan.setIdHDCT(hoaDonChiTiet);
                serialDaBan.setCreatedDate(new Date());
                serialDaBan.setLastModifiedDate(new Date());
                session.save(serialDaBan);
            }
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public HoaDonChiTiet getIdHoaDonChiTiet(String serial) {
        HoaDonChiTiet hoaDonChiTiet = null;
        try {
            Session sess = HibernateUtil.getFACTORY().openSession();
            Query q = sess.createQuery("SELECT ct FROM HoaDonChiTiet ct WHERE ct.idCTSP.serial = :serial");
            q.setParameter("serial", serial);
            hoaDonChiTiet = (HoaDonChiTiet) q.getSingleResult();
            sess.close();
        } catch (Exception e) {
            e.printStackTrace();
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
    

    public Boolean delete(List<String> listSerial) {
        Transaction transaction = null;
        boolean check = false;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            for (String serial : listSerial) {
                transaction = session.beginTransaction();
                Query query = session.createQuery("DELETE FROM SerialDaBan WHERE ma = :ma");
                query.setParameter("ma", listSerial);
                query.executeUpdate();
                transaction.commit();
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
}
