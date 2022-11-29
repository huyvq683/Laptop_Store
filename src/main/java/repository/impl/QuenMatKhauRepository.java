package repository.impl;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import domainmodel.NhanVien;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Đức Hiệu
 */
public class QuenMatKhauRepository {

    public Boolean getOne(String email) {
        NhanVien nhanVien = null;
        try ( Session session = utility.HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery(" FROM NhanVien WHERE email =:email");
            query.setParameter("email", email);
            nhanVien = (NhanVien) query.getSingleResult();
            return true;
        } catch (Exception e) {
            // e.printStackTrace(System.out);
        }
        return false;
    }

    public NhanVien getOneUpdate(String email) {
        NhanVien nhanVien = null;
        try ( Session session = utility.HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM NhanVien WHERE email =:email");
            query.setParameter("email", email);
            nhanVien = (NhanVien) query.getSingleResult();
            return nhanVien;
        } catch (Exception e) {
            // e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean updateMatKhau(String mk, String email) {
        UUID idd = getOneUpdate(email).getId();
        boolean check = false;
        Transaction tran = null;
        try ( Session session = utility.HibernateUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            NhanVien nvv = session.get(NhanVien.class, idd);
            nvv.setMatKhau(mk);
            session.update(nvv);
            tran.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check;
    }
}
