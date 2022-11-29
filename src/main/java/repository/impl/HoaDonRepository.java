/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import custommodel.ViewHoaDonReponse;
import domainmodel.HoaDon;
import domainmodel.KhachHang;
import domainmodel.NhanVien;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utility.HibernateUtil;
import view.PanelHoaDon;
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
 * @author WIN11
 */
public class HoaDonRepository {

    private String fromTable = "FROM HoaDon";

    private Session session = HibernateUtil.getFACTORY().openSession();

    public List<HoaDonResponse> getByOne(int tt) {
        String sql = "SELECT new custommodel.HoaDonResponse(h.id , h.ma , h.ngayTao, n.hoTen  , h.tinhTrang) From HoaDon h"
                + " JOIN NhanVien n ON h.idNV = n.id"
                + " JOIN KhachHang k ON h.idKH = k.id"
                + " WHERE h.tinhTrang = :tinhTrang";
        Query query = session.createQuery(sql, HoaDonResponse.class);
        query.setParameter("tinhTrang", tt);
        List<HoaDonResponse> list = query.getResultList();
        return list;
    }
    public HoaDon getOne(String ma) {
        String sql = fromTable + " Where ma = :ma ";
        javax.persistence.Query query = session.createQuery(sql, HoaDon.class);
        query.setParameter("ma", ma);
        HoaDon category = (HoaDon) query.getSingleResult();
        return category;
    }

   
    public List<HoaDonResponse> getAll() {
        String sql = "SELECT new custommodel.HoaDonResponse(h.id , h.ma , h.ngayTao, n.hoTen  , h.tinhTrang) From HoaDon h"
                + " JOIN KhachHang k ON h.idKH = k.id "
                + " JOIN NhanVien n ON h.idNV = n.id";
        Query<HoaDonResponse> query = session.createQuery(sql);
        return query.list();
    }

    public List<HoaDon> get_All() {
        javax.persistence.Query query = session.createQuery(fromTable, HoaDon.class);
        List<HoaDon> listCategory = query.getResultList();
        return listCategory;

    }

    public Boolean update(HoaDon hd) {
        Transaction transaction = null;
        try {
            transaction = (Transaction) HibernateUtil.getFACTORY().openSession();
            session.update(hd);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public ViewHoaDonReponse getOneByMa(String ma) {
        String sql = "SELECT new custommodel.ViewHoaDonReponse(h.ma , n.ma , k.ma , h.ngayTao , h.tongTien , h.tinhTrang) From HoaDon h"
                + " JOIN KhachHang k ON h.idKH = k.id "
                + " JOIN NhanVien n ON h.idNV = n.id"
                + " WHERE h.ma = :ma";
        Query query = session.createQuery(sql, ViewHoaDonReponse.class);
        query.setParameter("ma", ma);
        ViewHoaDonReponse viewHoaDonReponse = (ViewHoaDonReponse) query.getSingleResult();
        return viewHoaDonReponse;
    }

    public List<ViewHoaDonReponse> getByNV(String ma) {
        String sql = "SELECT new custommodel.ViewHoaDonReponse(h.ma , n.ma , k.ma , h.ngayTao , h.tongTien , h.tinhTrang) From HoaDon h"
                + " JOIN KhachHang k ON h.idKH = k.id "
                + " JOIN NhanVien n ON h.idNV = n.id"
                + " WHERE n.ma = :ma";
        Query query = session.createQuery(sql, ViewHoaDonReponse.class);
        query.setParameter("ma", ma);
        List<ViewHoaDonReponse> list = query.getResultList();
        return list;
    }

    public List<ViewHoaDonReponse> getByMaKH(String ma) {
        String sql = "SELECT new custommodel.ViewHoaDonReponse(h.ma , n.ma , k.ma , h.ngayTao , h.tongTien , h.tinhTrang) From HoaDon h"
                + " JOIN KhachHang k ON h.idKH = k.id "
                + " JOIN NhanVien n ON h.idNV = n.id"
                + " WHERE k.ma = :ma";
        Query query = session.createQuery(sql, ViewHoaDonReponse.class);
        query.setParameter("ma", ma);
        List<ViewHoaDonReponse> list = query.getResultList();
        return list;
    }

    public List<ViewHoaDonReponse> getByNgayTao(String ma) {
        String sql = "SELECT new custommodel.ViewHoaDonReponse(h.ma , n.ma , k.ma , h.ngayTao , h.tongTien , h.tinhTrang) From HoaDon h"
                + " JOIN KhachHang k ON h.idKH = k.id "
                + " JOIN NhanVien n ON h.idNV = n.id"
                + " WHERE h.ngayTao = :ma";
        Query query = session.createQuery(sql, ViewHoaDonReponse.class);
        query.setParameter("ngayTao", ma);
        List<ViewHoaDonReponse> list = query.getResultList();
        return list;
    }

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
