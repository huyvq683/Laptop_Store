/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import customModel.HoaDonResponse;
import domainModel.HoaDon;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import repository.Repository;
import utiliti.HibernateUtil;

/**
 *
 * @author FPT
 */
public class HoaDonRepository implements Repository<HoaDonResponse> {

    @Override
    public List<HoaDonResponse> getAll() {
        List<HoaDonResponse> lists = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT new customModel.HoaDonResponse"
                    + "(h.id, h.ma, h.ngayTao, nv.hoTen, h.tinhTrang) "
                    + "FROM HoaDon h LEFT JOIN NhanVien nv "
                    + "on h.idNV = nv.id ORDER BY h.ma DESC");
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

    public int genMaHD() {
        String maHD = "";
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            NativeQuery query = session.createNativeQuery("SELECT MAX(CONVERT(INT, SUBSTRING(Ma, 3, 10))) FROM HoaDon");
            maHD = query.getSingleResult().toString();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        if (maHD == null) {
            maHD = "0";
            int ma = Integer.valueOf(maHD);
            return ++ma;
        }
        int ma = Integer.valueOf(maHD);
        return ++ma;
    }

    @Override
    public Boolean add(HoaDonResponse t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void main(String[] args) {
        List<HoaDonResponse>lists = new HoaDonRepository().getAll();
        System.out.println(lists);
    }
}
