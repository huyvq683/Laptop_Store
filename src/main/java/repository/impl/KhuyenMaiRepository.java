/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import custommodel.SanPhamViewKMResponse;
import domainmodel.ChiTietSP;
import domainmodel.KhuyenMai;
import domainmodel.SanPham;
import domainmodel.SanPhamKM;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import utility.HibernateUtil;

/**
 *
 * @author dinhv
 */
public class KhuyenMaiRepository {

    public boolean insertOrUpdateKhuyenMai(KhuyenMai km) {
        try {
            Session sess = HibernateUtil.getFACTORY().openSession();
            sess.getTransaction().begin();
            sess.saveOrUpdate(km);
            sess.getTransaction().commit();
            sess.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<KhuyenMai> getAllKhuyenMai() {
        List<KhuyenMai> list = new ArrayList<>();
        try {
            Session sess = HibernateUtil.getFACTORY().openSession();
            Query q = sess.createQuery("FROM KhuyenMai");
            list = q.getResultList();
            sess.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int genMaHD() {
        String maHD = "";
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            NativeQuery query = session.createNativeQuery("SELECT MAX(CONVERT(INT, ma)) FROM KhuyenMai");
            if (query.getSingleResult() == null) {
                return 1;
            }
            maHD = query.getSingleResult().toString();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        int ma = Integer.valueOf(maHD);
        return ++ma;
    }

    public List<SanPhamViewKMResponse> getAllSanPham() {
        List<SanPhamViewKMResponse> list = new ArrayList<>();
        try {
            Session sess = HibernateUtil.getFACTORY().openSession();
            Query q = sess.createQuery("SELECT new custommodel.SanPhamViewKMResponse (s.idSanPham.ma, s.idSanPham.ten, s.cPU, s.hang, s.ram, s.card, s.oCung, s.gia, COUNT(s.idSanPham)) FROM ChiTietSP s WHERE s.tinhTrang = 0 GROUP BY s.idSanPham.ma, s.idSanPham.ten, s.cPU, s.hang, s.ram, s.card, s.oCung, s.gia");
            list = q.getResultList();
            sess.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<SanPhamViewKMResponse> getAllSanPhamKhuyenMai() {
        List<SanPhamViewKMResponse> list = new ArrayList<>();
        try {
            Session sess = HibernateUtil.getFACTORY().openSession();
//            Query q = sess.createQuery("SELECT new custommodel.SanPhamViewKMResponse (sp.ma, sp.ten, s.cPU, s.hang, s.ram, s.card, s.oCung, s.gia, COUNT(s.idSanPham)) FROM ChiTietSP s JOIN SanPham sp ON s.idSanPham = sp.id JOIN SanPhamKM km ON km.idSP = sp.id WHERE km.id = '34A888E1-F68E-1145-880C-E3E97127EA80' GROUP BY sp.ma, sp.ten, s.cPU, s.hang, s.ram, s.card, s.oCung, s.gia");
//            q.setParameter("idKM", km.getId());
            Query q = sess.createQuery("SELECT new custommodel.SanPhamViewKMResponse (sp.ma, sp.ten, ctsp.cPU, ctsp.hang, ctsp.ram, ctsp.card, ctsp.oCung, ctsp.gia, COUNT(ctsp.idSanPham)) FROM SanPhamKM spkm JOIN SanPham sp ON spkm.idSanPham = sp.id JOIN ChiTietSP ctsp ON ctsp.idSanPham = sp.id WHERE spkm.idKhuyenMai.id = 'idSanPham' GROUP BY sp.ma, sp.ten, ctsp.cPU, ctsp.hang, ctsp.ram, ctsp.card, ctsp.oCung, ctsp.gia");
            list = q.getResultList();
            sess.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public SanPham getSanPhamByMa(String ma) {
        SanPham sp = null;
        try {
            Session sess = HibernateUtil.getFACTORY().openSession();
            Query q = sess.createQuery("FROM SanPham WHERE ma = :maSP");
            q.setParameter("maSP", ma);
            sp = (SanPham) q.getSingleResult();
            sess.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sp;
    }

    public void deleteSanPhamKhuyenMaiByMa(String maKM) {
        try {
            Session sess = HibernateUtil.getFACTORY().openSession();
            Query q = sess.createQuery("FROM SanPhamKM s WHERE s.idKhuyenMai.ma = :maKM");
            q.setParameter("maKM", maKM);
            List<SanPhamKM> list = q.getResultList();
            for (SanPhamKM sanPhamKM : list) {
                sess.getTransaction().begin();
                sess.delete(sanPhamKM);
                sess.getTransaction().commit();
            }
            sess.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ChiTietSP> getChiTietSanPhamByIdSanPham(SanPham sp) {
        List<ChiTietSP> list = new ArrayList<>();
        try {
            Session sess = HibernateUtil.getFACTORY().openSession();
            Query q = sess.createQuery("FROM ChiTietSP WHERE idSanPham = 'idSanPham'");
            System.out.println(sp.getId());
//            q.setParameter("idSP", sp.getId());
            list = q.getResultList();
            sess.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insertSanPhamKhuyenMai(List<String> listMaSP, KhuyenMai km) {
        deleteSanPhamKhuyenMaiByMa(km.getMa());
        try {
            Session sess = HibernateUtil.getFACTORY().openSession();
            for (String ma : listMaSP) {
                SanPham sp = getSanPhamByMa(ma);
                SanPhamKM spkm = new SanPhamKM();
                spkm.setIdKhuyenMai(km);
                spkm.setIdSanPham(sp);
                spkm.setCreatedDate(new Date());
                spkm.setAlstModifiedDate(new Date());
                sess.getTransaction().begin();
                sess.save(spkm);
                sess.getTransaction().commit();
            }
            sess.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        new KhuyenMaiRepository().deleteSanPhamKhuyenMaiByMa("2");
        new KhuyenMaiRepository().getAllSanPhamKhuyenMai().forEach(c -> System.out.println(c.getMa()));
    }
}
