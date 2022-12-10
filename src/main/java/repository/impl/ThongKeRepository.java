package repository.impl;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
                    + "(hd.ma, hd.idNV.ma, hd.idNV.hoTen, hd.hinhThuc, hd.tienKhachTra, hd.tienCK ,hd.tongTien) FROM HoaDon hd"
                    + " WHERE hd.tinhTrang = 1 AND hd.createdDate =:date ORDER BY hd.ma ASC");
            query.setParameter("date", n);
            getAllDoanhThu = query.getResultList();
        } catch (Exception e) {
        }
        return getAllDoanhThu;
    }

    public List<ThongKeDoanhThuRespone> getAllDoanhThuKhoangNgay(Date n, Date kt) {
        List<ThongKeDoanhThuRespone> getAllDoanhThu = new ArrayList<>();
        try ( Session session = utility.HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT new custommodel.ThongKeDoanhThuRespone"
                    + "(hd.ma, hd.idNV.ma, hd.idNV.hoTen, hd.hinhThuc, hd.tienKhachTra, hd.tienCK ,hd.tongTien) FROM HoaDon hd"
                    + " WHERE hd.tinhTrang = 1 AND hd.createdDate >=:date AND hd.createdDate <=: kt ORDER BY hd.ma ASC");
            query.setParameter("date", n);
            query.setParameter("kt", kt);
            getAllDoanhThu = query.getResultList();
        } catch (Exception e) {
        }
        return getAllDoanhThu;
    }

    public List<ThongKeDoanhThuRespone> getAllDoanhThuMonth(int thang, int nam) {
        List<ThongKeDoanhThuRespone> getAllDoanhThu = new ArrayList<>();
        try ( Session session = utility.HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT new custommodel.ThongKeDoanhThuRespone"
                    + "(hd.ma, hd.idNV.ma, hd.idNV.hoTen, hd.hinhThuc, hd.tienKhachTra, hd.tienCK ,hd.tongTien) FROM HoaDon hd"
                    + " WHERE hd.tinhTrang = 1 AND Month(hd.createdDate) =:mot AND Year(hd.createdDate) =:yea ORDER BY hd.ma ASC");
            query.setParameter("mot", thang);
            query.setParameter("yea", nam);
            getAllDoanhThu = query.getResultList();
        } catch (Exception e) {
        }
        return getAllDoanhThu;
    }

    public List<ThongKeDoanhThuRespone> getAllDoanhThuYear(int nam) {
        List<ThongKeDoanhThuRespone> getAllDoanhThu = new ArrayList<>();
        try ( Session session = utility.HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT new custommodel.ThongKeDoanhThuRespone"
                    + "(hd.ma, hd.idNV.ma, hd.idNV.hoTen, hd.hinhThuc, hd.tienKhachTra, hd.tienCK ,hd.tongTien) FROM HoaDon hd"
                    + " WHERE hd.tinhTrang = 1 AND YEAR(hd.createdDate) =:yea ORDER BY hd.ma ASC");
            query.setParameter("yea", nam);
            getAllDoanhThu = query.getResultList();
        } catch (Exception e) {
        }
        return getAllDoanhThu;
    }

    public int namBatDauDoanhThu() {
        String date = null;
        try ( Session session = utility.HibernateUtil.getFACTORY().openSession()) {
            NativeQuery query = session.createNativeQuery("SELECT Min(Year(hd.createdDate)) FROM HoaDon hd");
            date = query.getSingleResult().toString();
            return Integer.parseInt(date);
        } catch (Exception e) {
        }
        return 0;
    }

    public String getDoanhThuDay(Date ngay) {
        String tong = null;
        try ( Session session = utility.HibernateUtil.getFACTORY().openSession()) {
            NativeQuery query = session.createNativeQuery("SELECT SUM(hd.tongTien) FROM HoaDon hd"
                    + " WHERE hd.tinhTrang= 1 AND hd.createdDate =:date");
            query.setParameter("date", ngay);
            tong = query.getSingleResult().toString();
            return tong;
        } catch (Exception e) {
        }
        return null;
    }

    public String getDoanhThuKhoangDay(Date ngay, Date kt) {
        String tong = null;
        try ( Session session = utility.HibernateUtil.getFACTORY().openSession()) {
            NativeQuery query = session.createNativeQuery("SELECT SUM(hd.tongTien) FROM HoaDon hd"
                    + " WHERE hd.tinhTrang= 1 AND hd.createdDate >=:date AND hd.createdDate <= :kt");
            query.setParameter("date", ngay);
            query.setParameter("kt", kt);
            tong = query.getSingleResult().toString();
            return tong;
        } catch (Exception e) {
        }
        return null;
    }

    public String getDoanhThuMonth(int thang, int nam) {
        String tong = null;
        try ( Session session = utility.HibernateUtil.getFACTORY().openSession()) {
            NativeQuery query = session.createNativeQuery("SELECT SUM(hd.tongTien) FROM HoaDon hd"
                    + " WHERE hd.tinhTrang= 1 AND (Month(hd.createdDate) =:aaa AND Year(hd.createdDate) =:bbb)");
            query.setParameter("aaa", thang);
            query.setParameter("bbb", nam);
            tong = query.getSingleResult().toString();

        } catch (Exception e) {
        }
        return tong;
    }

    public String getDoanhThuYear(int nam) {
        String tong = null;
        try ( Session session = utility.HibernateUtil.getFACTORY().openSession()) {
            NativeQuery query = session.createNativeQuery("SELECT SUM(hd.tongTien) FROM HoaDon hd"
                    + " WHERE hd.tinhTrang= 1 AND Year(hd.createdDate) =:date");
            query.setParameter("date", nam);
            tong = query.getSingleResult().toString();
            return tong;
        } catch (Exception e) {
        }
        return null;
    }

    public List<ThongKeSanPhamRespone> getAllSanPham(Date n) {
        List<ThongKeSanPhamRespone> getAllSanPham = new ArrayList<>();
        try ( Session session = utility.HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT new custommodel.ThongKeSanPhamRespone "
                    + "	(sp.ma, sp.ten, ctsp.gia , count(ctsp.idSanPham.id)) FROM ChiTietSP ctsp"
                    + " JOIN SanPham sp ON ctsp.idSanPham.id = sp.id"
                    + " Where ctsp.serial in (select sr.ma from SerialDaBan sr "
                    + " WHERE sr.createdDate =:aaa)"
                    + " Group By sp.ma, sp.ten, ctsp.gia "
                    + "	ORDER BY sp.ma asc ");
            query.setParameter("aaa", n);
            getAllSanPham = query.getResultList();
        } catch (Exception e) {
        }
        return getAllSanPham;
    }

    public List<ThongKeSanPhamRespone> getAllSanPhamKhoangNgay(Date bd, Date kt) {
        List<ThongKeSanPhamRespone> getAllSanPham = new ArrayList<>();
        try ( Session session = utility.HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT new custommodel.ThongKeSanPhamRespone "
                    + "	(sp.ma, sp.ten, ctsp.gia , count(ctsp.idSanPham.id)) FROM ChiTietSP ctsp"
                    + " JOIN SanPham sp ON ctsp.idSanPham.id = sp.id"
                    + " Where ctsp.serial in (select sr.ma from SerialDaBan sr "
                    + " WHERE sr.createdDate >=:aaa AND sr.createdDate <=:bbb)"
                    + " Group By sp.ma, sp.ten, ctsp.gia "
                    + "	ORDER BY sp.ma asc ");
            query.setParameter("aaa", bd);
            query.setParameter("bbb", kt);
            getAllSanPham = query.getResultList();
        } catch (Exception e) {
        }
        return getAllSanPham;
    }

    public List<ThongKeSanPhamRespone> getAllSanPhamMonth(int thang, int nam) {
        List<ThongKeSanPhamRespone> getAllSanPham = new ArrayList<>();
        try ( Session session = utility.HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT new custommodel.ThongKeSanPhamRespone "
                    + "	(sp.ma, sp.ten, ctsp.gia , count(ctsp.idSanPham.id)) FROM ChiTietSP ctsp"
                    + " JOIN SanPham sp ON ctsp.idSanPham.id = sp.id"
                    + " Where ctsp.serial in (select sr.ma from SerialDaBan sr "
                    + " WHERE month(sr.createdDate) =:mo AND year(sr.createdDate) =:yea )"
                    + " Group By sp.ma, sp.ten, ctsp.gia "
                    + "	ORDER BY sp.ma asc ");
            query.setParameter("mo", thang);
            query.setParameter("yea", nam);
            getAllSanPham = query.getResultList();
        } catch (Exception e) {
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
        }
        return getAllSanPham;
    }

    public long spKinhDoanh(int t) {
        String count = null;
        try ( Session session = utility.HibernateUtil.getFACTORY().openSession()) {
            NativeQuery query = session.createNativeQuery("SELECT count(*) From ChiTietSP sp WHERE sp.tinhTrang =:tt");
            query.setParameter("tt", t);
            count = query.getSingleResult().toString();
        } catch (Exception e) {
        }
        return Long.valueOf(count);
    }

    public long spKinhDoanhAll() {
        String count = null;
        try ( Session session = utility.HibernateUtil.getFACTORY().openSession()) {
            NativeQuery query = session.createNativeQuery("SELECT count(*) From ChiTietSP sp");
            count = query.getSingleResult().toString();
        } catch (Exception e) {
        }
        return Long.valueOf(count);
    }

    public int namBatDau() {
        String date = null;
        try ( Session session = utility.HibernateUtil.getFACTORY().openSession()) {
            NativeQuery query = session.createNativeQuery("SELECT Min(Year(sr.createdDate)) FROM SerialDaBan sr");
            date = query.getSingleResult().toString();
            return Integer.parseInt(date);
        } catch (Exception e) {
        }
        return 0;
    }

    public String bieuDoDoanhThuMonth(int ngay, int thang, int nam) {
        String tong = null;
        try ( Session session = utility.HibernateUtil.getFACTORY().openSession()) {
            NativeQuery query = session.createNativeQuery("SELECT SUM(hd.tongTien) FROM HoaDon hd"
                    + " WHERE hd.tinhTrang= 1 AND (day(hd.createdDate) =:daa AND Month(hd.createdDate) =:aaa AND Year(hd.createdDate) =:bbb)");
            query.setParameter("daa", ngay);
            query.setParameter("aaa", thang);
            query.setParameter("bbb", nam);
            tong = query.getSingleResult().toString();
            return tong;
        } catch (Exception e) {
        }
        return "0";
    }

    public String bieuDoDoanhThuYear(int thang, int nam) {
        String tong = null;
        try ( Session session = utility.HibernateUtil.getFACTORY().openSession()) {
            NativeQuery query = session.createNativeQuery("SELECT SUM(hd.tongTien) FROM HoaDon hd"
                    + " WHERE hd.tinhTrang= 1 AND ( Month(hd.createdDate) =:aaa AND Year(hd.createdDate) =:bbb)");
            query.setParameter("aaa", thang);
            query.setParameter("bbb", nam);
            tong = query.getSingleResult().toString();
            return tong;
        } catch (Exception e) {
        }
        return "0";
    }
}
