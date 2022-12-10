package repository.impl;

import custommodel.KhachHangReponse;
import custommodel.KhachHangRespone;
import domainmodel.KhachHang;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import utility.HibernateUtil;

/**
 *
 * @author daohi
 */
public class KhachHangRepository {

    private String fromTable = "FROM KhachHang";

    private Session session = HibernateUtil.getFACTORY().openSession();

    public List<KhachHang> getAll() {
        List<KhachHang> lists = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession();) {
            NativeQuery query = session.createNativeQuery("SELECT kh.id, kh.ma, kh.hoTen, kh.diaChi, kh.sdt, kh.email, kh.capBac, kh.createdDate, kh.lastModifiedDate"
                    + " FROM KhachHang kh"
                    + " GROUP BY kh.id, kh.ma, kh.hoTen, kh.diaChi, kh.sdt, kh.email, kh.capBac, kh.createdDate, kh.lastModifiedDate"
                    + " ORDER BY MAX(CONVERT(INT, SUBSTRING(ma, 3, 10))) DESC", KhachHang.class);
            lists = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }

    public List<KhachHangRespone> getHD(UUID id) {
        List<KhachHangRespone> lists = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT new custommodel.KhachHangRespone "
                    + "(c.idHoaDon.idKH.id, c.idCTSP.idSanPham.ma, c.idCTSP.idSanPham.ten, c.idHoaDon.ngayTao, COUNT(c.idCTSP.idSanPham), c.donGia) "
                    + "FROM HoaDonChiTiet c WHERE c.idHoaDon.idKH.id = :id "
                    + "GROUP BY c.idHoaDon.idKH.id, c.idCTSP.idSanPham.ma, c.idCTSP.idSanPham.ten, c.idHoaDon.ngayTao, c.donGia");
            query.setParameter("id", id);
            lists = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }

    public BigDecimal getTongTienByIDHD(UUID id) {
        BigDecimal db = new BigDecimal(0);
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT "
                    + "(SUM(hd.tongTien)) "
                    + "FROM HoaDon hd WHERE hd.idKH.id = :id ");
            query.setParameter("id", id);
            db = (BigDecimal) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return db;
    }

    public KhachHang getMa(String ma) {
        try ( Session session = HibernateUtil.getFACTORY().openSession();) {
            String sql = fromTable + " WHERE ma = :ma";
            Query query = session.createQuery(sql);
            query.setParameter("ma", ma);
            KhachHang kh = (KhachHang) query.getSingleResult();
            return kh;
        } catch (Exception e) {
            return null;
        }
    }

    public KhachHang getIdKhachHang(UUID id) {
        try ( Session session = HibernateUtil.getFACTORY().openSession();) {
            String sql = fromTable + " WHERE id = :id";
            Query query = session.createQuery(sql);
            query.setParameter("id", id);
            KhachHang kh = (KhachHang) query.getSingleResult();
            return kh;
        } catch (Exception e) {
            return null;
        }
    }

    public KhachHang getEmail(String email) {
        String sql = fromTable + " WHERE email = :email";
        Query query = session.createQuery(sql);
        query.setParameter("email", email);
        KhachHang kh = (KhachHang) query.getSingleResult();
        return kh;
    }

    public Boolean add(KhachHang kh) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(kh);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public Boolean delete(KhachHang kh) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(kh);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public List<KhachHangReponse> getListKH() {
        List<KhachHangReponse> lists = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession();) {
            Query query = session.createQuery("SELECT new custommodel.KhachHangReponse (k.id, k.ma, k.hoTen, k.sdt, k.capBac) FROM KhachHang k");
            lists = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }
}
