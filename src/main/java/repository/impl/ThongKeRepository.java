/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import custommodel.ThongKeDoanhThuRespone;
import custommodel.ThongKeSanPhamRespone;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

/**
 *
 * @author Đức Hiệu
 */
public class ThongKeRepository {

    private DateFormat dateFor = new SimpleDateFormat("yyyy/MM/dd");

    public List<ThongKeDoanhThuRespone> getAllDoanhThu(Date n) {
        List<ThongKeDoanhThuRespone> getAllDoanhThu = new ArrayList<>();
        try ( Session session = utility.HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT new custommodel.ThongKeDoanhThuRespone"
                    + "(hd.ma, hd.idNV.ma, hd.idNV.hoTen, hd.tongTien) FROM HoaDon hd"
                    + " WHERE hd.tinhTrang=1 AND hd.createdDate =:date ORDER BY hd.ma ASC");
            query.setParameter("date", n);
            getAllDoanhThu = query.getResultList();
        } catch (Exception e) {
            //e.printStackTrace(System.out);
        }
        return getAllDoanhThu;
    }

    public List<ThongKeDoanhThuRespone> getAllDoanhThuSortTang(Date n) {
        List<ThongKeDoanhThuRespone> getAllDoanhThu = new ArrayList<>();
        try ( Session session = utility.HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT new custommodel.ThongKeDoanhThuRespone"
                    + "(hd.ma, hd.idNV.ma, hd.idNV.hoTen, hd.tongTien) FROM HoaDon hd"
                    + " WHERE hd.tinhTrang=1 AND hd.createdDate =:date ORDER BY hd.tongTien ASC");
            query.setParameter("date", n);
            getAllDoanhThu = query.getResultList();
        } catch (Exception e) {
            //e.printStackTrace(System.out);
        }
        return getAllDoanhThu;
    }

    public List<ThongKeDoanhThuRespone> getAllDoanhThuSortGiam(Date n) {
        List<ThongKeDoanhThuRespone> getAllDoanhThu = new ArrayList<>();
        try ( Session session = utility.HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT new custommodel.ThongKeDoanhThuRespone"
                    + "(hd.ma, hd.idNV.ma, hd.idNV.hoTen, hd.tongTien) FROM HoaDon hd"
                    + " WHERE hd.tinhTrang=1 AND hd.createdDate =:date ORDER BY hd.tongTien DESC");
            query.setParameter("date", n);
            getAllDoanhThu = query.getResultList();
        } catch (Exception e) {
            //e.printStackTrace(System.out);
        }
        return getAllDoanhThu;
    }

    public String getDoanhThu() {
        String tong = null;
        try ( Session session = utility.HibernateUtil.getFACTORY().openSession()) {
            NativeQuery query = session.createNativeQuery("SELECT SUM(hd.tongTien) FROM HoaDon hd"
                    + " WHERE hd.tinhTrang= 1 AND hd.createdDate =:date");
            query.setParameter("date", dateFor.format(new Date()));
            tong = query.getSingleResult().toString();
            return tong;
        } catch (Exception e) {
            //e.printStackTrace(System.out);
        }
        return null;
    }

    public List<ThongKeSanPhamRespone> getAllSanPham(Date n) {
        List<ThongKeSanPhamRespone> getAllSanPham = new ArrayList<>();
        try ( Session session = utility.HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT new custommodel.ThongKeSanPhamRespone "
                    + "	(sp.ma, sp.ten, ctsp.gia ,count(ctsp.idSanPham.id)) FROM ChiTietSP ctsp"
                    + " JOIN SanPham sp ON ctsp.idSanPham.id = sp.id"
                    + " Where ctsp.serial in (select sr.ma from SerialDaBan sr "
                    + " WHERE sr.createdDate =:aaa)"
                    + " Group By sp.ma, sp.ten, ctsp.gia "
                    + "	ORDER BY sp.ma asc ");
            query.setParameter("aaa", n);
            getAllSanPham = query.getResultList();
        } catch (Exception e) {
            //e.printStackTrace(System.out);
        }
        return getAllSanPham;
    }

    public List<ThongKeSanPhamRespone> getAllSanPhamMonth(int n) {
        List<ThongKeSanPhamRespone> getAllSanPham = new ArrayList<>();
        try ( Session session = utility.HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT new custommodel.ThongKeSanPhamRespone "
                    + "	(sp.ma, sp.ten, ctsp.gia ,count(ctsp.idSanPham.id)) FROM ChiTietSP ctsp"
                    + " JOIN SanPham sp ON ctsp.idSanPham.id = sp.id"
                    + " Where ctsp.serial in (select sr.ma from SerialDaBan sr "
                    + " WHERE month(sr.createdDate) =:mo AND year(sr.createdDate) = 2022 )"
                    + " Group By sp.ma, sp.ten, ctsp.gia "
                    + "	ORDER BY sp.ma asc ");
            query.setParameter("mo", n);
            getAllSanPham = query.getResultList();
        } catch (Exception e) {
            //e.printStackTrace(System.out);
        }
        return getAllSanPham;
    }

    public List<ThongKeSanPhamRespone> getAllSanPhamYear(int n) {
        List<ThongKeSanPhamRespone> getAllSanPham = new ArrayList<>();
        try ( Session session = utility.HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT new custommodel.ThongKeSanPhamRespone "
                    + "	(sp.ma, sp.ten, ctsp.gia ,count(ctsp.idSanPham.id)) FROM ChiTietSP ctsp"
                    + " JOIN SanPham sp ON ctsp.idSanPham.id = sp.id"
                    + " Where ctsp.serial in (select sr.ma from SerialDaBan sr "
                    + " WHERE Year(sr.createdDate) =:ye )"
                    + " Group By sp.ma, sp.ten, ctsp.gia "
                    + "	ORDER BY sp.ma asc ");
            query.setParameter("ye", n);
            getAllSanPham = query.getResultList();
        } catch (Exception e) {
            //e.printStackTrace(System.out);
        }
        return getAllSanPham;
    }

    public long spKinhDoanh(int t) {
        String count = null;
        try ( Session session = utility.HibernateUtil.getFACTORY().openSession()) {
            NativeQuery query = session.createNativeQuery("SELECT count(sp.id) From SanPham sp WHERE sp.tinhTrang =:tt");
            query.setParameter("tt", t);
            count = query.getSingleResult().toString();
        } catch (Exception e) {
            //e.printStackTrace(System.out);
        }
        return Long.valueOf(count);
    }

    public int thangNamBatDau() {
        String date = null;
        try ( Session session = utility.HibernateUtil.getFACTORY().openSession()) {
            NativeQuery query = session.createNativeQuery("SELECT Min(Year(sr.createdDate)) FROM SerialDaBan sr");
            date = query.getSingleResult().toString();
        } catch (Exception e) {
            //e.printStackTrace(System.out);
        }
        return Integer.parseInt(date);
    }

}
