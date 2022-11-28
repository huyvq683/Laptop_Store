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

/**
 *
 * @author WIN11
 */
public class HoaDonRepository {

    private String fromTable = "FROM HoaDon";

    private Session session = HibernateUtil.getFACTORY().openSession();

    public List<ViewHoaDonReponse> getByOne(int tt) {
        String sql = "SELECT new custommodel.ViewHoaDonReponse(h.ma , n.ma , k.ma , h.ngayTao , h.tongTien , h.tinhTrang) From HoaDon h"
                + " JOIN KhachHang k ON h.idKH = k.id "
                + " JOIN NhanVien n ON h.idNV = n.id"
                + " WHERE h.tinhTrang = :tinhTrang";
        Query query = session.createQuery(sql, ViewHoaDonReponse.class);
        query.setParameter("tinhTrang", tt);
        List<ViewHoaDonReponse> list = query.getResultList();
        return list;
    }

    public HoaDon getOne(String ma) {
        String sql = fromTable + " Where ma = :ma ";
        javax.persistence.Query query = session.createQuery(sql, HoaDon.class);
        query.setParameter("ma", ma);
        HoaDon category = (HoaDon) query.getSingleResult();
        return category;
    }

    public List<ViewHoaDonReponse> getAll() {
        String sql = "SELECT new custommodel.ViewHoaDonReponse(h.ma , n.ma , k.ma , h.ngayTao , h.tongTien , h.tinhTrang) From HoaDon h"
                + " JOIN KhachHang k ON h.idKH = k.id "
                + " JOIN NhanVien n ON h.idNV = n.id";
        Query<ViewHoaDonReponse> query = session.createQuery(sql);
        return query.list();
    }

    public List<HoaDon> get_All() {
        javax.persistence.Query query = session.createQuery(fromTable, HoaDon.class);
        List<HoaDon> listCategory = query.getResultList();
        return listCategory;

    }

    public static void main(String[] args) {
        List<ViewHoaDonReponse> list = new HoaDonRepository().getByOne(0);
        System.out.println(list);
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

//    private String fromTable1 = "FROM NhanVien";
//
//    public NhanVien getMaNV(String ma) {
//        String sql = fromTable1 + " WHERE ma = :ma";
//        javax.persistence.Query query = session.createQuery(sql);
//        query.setParameter("ma", ma);
//        NhanVien nv = (NhanVien) query.getSingleResult();
//        return nv;
//    }
//    private String fromTable2 = "FROM KhachHang";
//
//    public KhachHang getMaKH(String ma) {
//        String sql = fromTable + " WHERE ma = :ma";
//        javax.persistence.Query query = session.createQuery(sql);
//        query.setParameter("ma", ma);
//        KhachHang kh = (KhachHang) query.getSingleResult();
//        return kh;
//    }
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

}
