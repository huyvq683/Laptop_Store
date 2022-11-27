/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import custommodel.HoaDonResponse;
import domainmodel.HoaDon;
import domainmodel.NhanVien;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import utility.HibernateUtil;

/**
 *
 * @author FPT
 */
public class HoaDonRepository {

    public List<HoaDonResponse> getAll(NhanVien nhanVien) {
        List<HoaDonResponse> lists = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT new custommodel.HoaDonResponse"
                    + "(h.id, h.ma, h.ngayTao, nv.hoTen, h.tinhTrang) "
                    + "FROM HoaDon h LEFT JOIN NhanVien nv "
                    + "on h.idNV = nv.id WHERE nv.id = :id ORDER BY h.ma DESC");
            query.setParameter("id", nhanVien.getId());
            lists = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }

    public Boolean add(HoaDon hoaDon) {
        boolean check = false;
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(hoaDon);
            transaction.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean updateTrangThai(HoaDon hoaDon, UUID id) {
        boolean check = false;
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            HoaDon hd = session.get(HoaDon.class, id);
            hd.setTinhTrang(1);
            session.update(hd);
            transaction.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check;
    }

    public boolean updateTrangThaiHuy(HoaDon hoaDon, UUID id) {
        boolean check = false;
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            HoaDon hd = session.get(HoaDon.class, id);
            hd.setTinhTrang(2);
            session.update(hd);
            transaction.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check;
    }

    public int genMaHD() {
        String maHD = "";
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            NativeQuery query = session.createNativeQuery("SELECT MAX(CONVERT(INT, SUBSTRING(Ma, 3, 10))) FROM HoaDon");
                maHD = query.getSingleResult().toString();
        } catch (Exception e) {
//            e.printStackTrace(System.out);
        }
        if (maHD == "") {
            maHD = "0";
            int ma = Integer.valueOf(maHD);
            return ++ma;
        }
        int ma = Integer.valueOf(maHD);
        return ++ma;
    }

    public HoaDon getByIdHoaDon(UUID id) {
        HoaDon hoaDon = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT n "
                    + "FROM HoaDon n WHERE n.id = :id");
            query.setParameter("id", id);
            hoaDon = (HoaDon) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return hoaDon;
    }

}
