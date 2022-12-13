/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import custommodel.HoaDonInResponse;
import custommodel.HoaDonResponse;
import custommodel.KhachHangReponse;
import custommodel.ViewExcelReponse;
import domainmodel.HoaDon;
import domainmodel.KhachHang;
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
    private String fromTableKH = "FROM KhachHang";

    private Session session = HibernateUtil.getFACTORY().openSession();

    public List<HoaDonResponse> getByOne(int tt) {
        String sql = "SELECT new custommodel.HoaDonResponse(h.id , h.ma , h.ngayTao, n.hoTen  , h.tinhTrang) From HoaDon h"
                + " JOIN NhanVien n ON h.idNV = n.id"
                + " WHERE h.tinhTrang = :tinhTrang"
                + " ORDER BY h.ma DESC";
        Query query = session.createQuery(sql, HoaDonResponse.class);
        query.setParameter("tinhTrang", tt);
        List<HoaDonResponse> list = query.getResultList();
        return list;
    }

    public List<HoaDonResponse> getAllPage(int row) {
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String sql = "SELECT new custommodel.HoaDonResponse(h.id , h.ma , h.ngayTao, n.hoTen  , h.tinhTrang) From HoaDon h"
                    + " JOIN NhanVien n ON h.idNV = n.id"
                    + " ORDER BY h.ma OFFSET :row ROW FETCH NEXT 5 ROWS ONLY";
            NativeQuery query = session.createNativeQuery(sql, HoaDonResponse.class);
            query.setParameter("row", row);
            List<HoaDonResponse> list = query.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public HoaDonResponse getOne(String ma) {
        HoaDonResponse hoaDonResponse = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query sql = session.createQuery("SELECT new custommodel.HoaDonResponse(h.id, h.ma, h.idKH, h.ngayTao , h.hinhThuc, h.tongTien, h.tienKhachTra, h.tienCK, n.ma , n.hoTen, h.tinhTrang) From HoaDon h"
                    + " Join NhanVien n On n.id = h.idNV"
                    + " WHERE h.ma = :ma");
            sql.setParameter("ma", ma);
            hoaDonResponse = (HoaDonResponse) sql.getSingleResult();
            return hoaDonResponse;
        } catch (Exception e) {
            return null;
        }
    }

    public HoaDonResponse getOne1(String ma) {
        HoaDonResponse hoaDonResponse = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query sql = session.createQuery("SELECT new custommodel.HoaDonResponse(h.id, h.ma, h.ngayTao , h.hinhThuc, h.tongTien, h.tienKhachTra, h.tienCK, n.ma , n.hoTen, h.tinhTrang) From HoaDon h"
                    + " Join NhanVien n On n.id = h.idNV"
                    + " WHERE h.ma = :ma");
            sql.setParameter("ma", ma);
            hoaDonResponse = (HoaDonResponse) sql.getSingleResult();
            return hoaDonResponse;
        } catch (Exception e) {
            return null;
        }
    }

    public HoaDonInResponse getHDIn(String ma) {
        HoaDonInResponse hoaDonInResponse = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query sql = session.createQuery("SELECT new custommodel.HoaDonInResponse(h.ma , n.hoTen , k.hoTen , k.sdt , k.diaChi ,  h.tongTien , h.hinhThuc )From HoaDon h "
                    + " Join KhachHang k ON k.id = h.idKH"
                    + " Join NhanVien n ON n.id = h.idNV"
                    + " WHERE h.ma = :ma");
            sql.setParameter("ma", ma);
            hoaDonInResponse = (HoaDonInResponse) sql.getSingleResult();
            return hoaDonInResponse;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
    }

    public List<HoaDonResponse> getAll() {
        String sql = "SELECT new custommodel.HoaDonResponse(h.id , h.ma , h.ngayTao, n.hoTen  , h.tinhTrang) From HoaDon h"
                + " JOIN NhanVien n ON h.idNV = n.id"
                + " ORDER BY h.ma DESC";
        Query<HoaDonResponse> query = session.createQuery(sql);
        return query.list();
    }

    public List<ViewExcelReponse> getAllExcel() {
        String sql = "SELECT new custommodel.ViewExcelReponse(h.ma , n.ma , n.hoTen , hd.tenSP , hd.donGia , h.hinhThuc , h.tienKhachTra , h.tienCK , h.tienThua , h.tongTien , h.ngayTao , h.tinhTrang) From HoaDonChiTiet hd\n"
                + "			   Join HoaDon h On hd.idHoaDon = h.id\n"
                + "                        JOIN NhanVien n ON h.idNV = n.id\n"
                + " ORDER BY h.ma ASC";
        Query<ViewExcelReponse> query = session.createQuery(sql);
        return query.list();
    }

    public List<ViewExcelReponse> getListExcel(int tt) {
        String sql = "SELECT new custommodel.ViewExcelReponse(h.ma , n.ma , n.hoTen , hd.tenSP , hd.donGia , h.hinhThuc , h.tienKhachTra , h.tienCK , h.tienThua , h.tongTien , h.ngayTao , h.tinhTrang)\n"
                + "From HoaDonChiTiet hd\n"
                + "Join HoaDon h On hd.idHoaDon = h.id\n"
                + "Join NhanVien n On h.idNV = n.id\n"
                + "WHERE h.tinhTrang = :tinhTrang "
                + "ORDER BY h.ma ASC ";
        Query query = session.createQuery(sql, ViewExcelReponse.class);
        query.setParameter("tinhTrang", tt);
        List<ViewExcelReponse> list = query.getResultList();
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

    public KhachHangReponse getKhachHang(String ma) {
        try ( Session session = HibernateUtil.getFACTORY().openSession();) {
            String sql = "SELECT new custommodel.KhachHangReponse(k.ma , k.hoTen , k.sdt) From KhachHang k"
                    + " Join HoaDon h ON h.idKH = k.id"
                    + " WHERE h.ma = :ma ";
            javax.persistence.Query query = session.createQuery(sql);
            query.setParameter("ma", ma);
            KhachHangReponse kh = (KhachHangReponse) query.getSingleResult();
            return kh;
        } catch (Exception e) {
            return null;
        }
    }

    public List<HoaDonResponse> search(String searchKey) {
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String sql = "Select new custommodel.HoaDonResponse(h.id , h.ma , h.ngayTao, n.hoTen  , h.tinhTrang) From HoaDon h"
                    + " Join KhachHang k on k.id = h.idKH"
                    + " Join NhanVien n on n.id = h.idNV"
                    + " WHERE h.ma like concat (:searchKey,'%') OR n.ma like concat (:searchKey,'%') OR k.ma like concat (:searchKey,'%') OR h.ngayTao like concat (:searchKey,'%')";
            javax.persistence.Query query = session.createQuery(sql, HoaDonResponse.class);
            query.setParameter("searchKey", searchKey);
            List<HoaDonResponse> search = query.getResultList();
            return search;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<HoaDonResponse> getAll(NhanVien nhanVien) {
        List<HoaDonResponse> lists = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT new custommodel.HoaDonResponse"
                    + "(h.id, h.ma, h.ngayTao, nv.hoTen, h.tinhTrang) "
                    + "FROM HoaDon h LEFT JOIN NhanVien nv "
                    + "on h.idNV = nv.id WHERE nv.id = :id  "
                    + "GROUP BY h.id, h.ma, h.ngayTao, nv.hoTen, h.tinhTrang "
                    + "ORDER BY MAX(CONVERT(INT, SUBSTRING(h.ma, 3, 10))) DESC");
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

    public boolean updateTrangThai(HoaDon hoaDon) {
        boolean check = false;
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(hoaDon);
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
