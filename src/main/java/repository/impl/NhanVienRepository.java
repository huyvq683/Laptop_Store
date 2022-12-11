/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import domainmodel.NhanVien;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import utility.HibernateUtil;

/**
 *
 * @author FPT
 */
public class NhanVienRepository {

    private String fromTable = "FROM NhanVien";

    private Session session = HibernateUtil.getFACTORY().openSession();
    
    public List<NhanVien> getAllPage(int row) {
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String sql = "SELECT nv.id, nv.ma, nv.hoTen, nv.gioiTinh, nv.ngaySinh, nv.diaChi, nv.sdt, nv.email, nv.chucVu, nv.matKhau, nv.trangThai, nv.createdDate, nv.lastModifiedDate "
                    + " FROM NhanVien nv GROUP BY nv.id, nv.ma, nv.hoTen, nv.gioiTinh, nv.ngaySinh, nv.diaChi, nv.sdt, nv.email, nv.chucVu, nv.matKhau, nv.trangThai, nv.createdDate, nv.lastModifiedDate "
                    + " ORDER BY MAX(CONVERT(INT, SUBSTRING(ma, 3, 10))) DESC OFFSET :row ROW FETCH NEXT 10 ROWS ONLY";
            NativeQuery query = session.createNativeQuery(sql, NhanVien.class);
            query.setParameter("row", row);
            List<NhanVien> list = query.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public NhanVien getOne(String email) {
        NhanVien nhanVien = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT n FROM NhanVien n "
                    + " WHERE n.email = :email");
            query.setParameter("email", email);
            nhanVien = (NhanVien) query.getSingleResult();
            return nhanVien;
        } catch (Exception e) {
            return null;
        }
    }
    
    public NhanVien getOneByMa(String ma, String email){
        NhanVien nhanVien = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT n FROM NhanVien n WHERE n.ma NOT LIKE :ma AND n.email = :email");
            query.setParameter("ma", ma);
            query.setParameter("email", email);
            nhanVien = (NhanVien) query.getSingleResult();
            return nhanVien;
        } catch (Exception e) {
            return null;
        }
    }

    public List<NhanVien> getAll() {
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            javax.persistence.Query query = session.createQuery(fromTable, NhanVien.class);
            List<NhanVien> list = query.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<NhanVien> search(String searchKey) {
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String sql = fromTable + " WHERE Ma like concat (:searchKey,'%') OR HoTen like concat (:searchKey,'%') OR sdt like concat (:searchKey,'%') OR Email like concat (:searchKey,'%')";
            javax.persistence.Query query = session.createQuery(sql, NhanVien.class);
            query.setParameter("searchKey", searchKey);
            List<NhanVien> search = query.getResultList();
            return search;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean addOrUpdate(NhanVien nhanVien) {
        boolean check = false;
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(nhanVien);
            transaction.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check;
    }
}
