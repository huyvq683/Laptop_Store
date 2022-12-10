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
import java.math.BigDecimal;
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
            Query q = sess.createQuery("FROM KhuyenMai ORDER BY ma DESC");
            list = q.getResultList();
            sess.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateTrangThaiKM() {
        try {
            Session sess = HibernateUtil.getFACTORY().openSession();
            for (KhuyenMai km : getAllKhuyenMai()) {
                if (km.getNgayKT().before(new Date())) {
                    km.setTrangThai(0);
                    sess.getTransaction().begin();
                    sess.update(km);
                    sess.getTransaction().commit();
                }
            }
            sess.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean kiemTraKM(SanPhamViewKMResponse spvkmr, KhuyenMai km) {
        List<ChiTietSP> list = new ArrayList<>();
        try {
            Session sess = HibernateUtil.getFACTORY().openSession();
            Query q = sess.createQuery("FROM ChiTietSP s WHERE s.idSanPham.ma = :maSP AND s.idSanPham.ten = :tenSP AND s.idCPU.ten = :cpu AND s.idHang.ten = :hang AND s.idRam.ten = :ram AND s.idCard.ten = :card AND s.idOCung.ten = :oCung AND s.gia = :gia AND s.id IN (SELECT spkm.idChiTietSP.id FROM SanPhamKM spkm WHERE spkm.idKhuyenMai.ma = :maKM)");
            q.setParameter("maKM", km.getMa());
            q.setParameter("maSP", spvkmr.getMa());
            q.setParameter("tenSP", spvkmr.getTen());
            q.setParameter("cpu", spvkmr.getCpu());
            q.setParameter("hang", spvkmr.getHang());
            q.setParameter("ram", spvkmr.getRam());
            q.setParameter("card", spvkmr.getCard());
            q.setParameter("oCung", spvkmr.getOCung());
            q.setParameter("gia", spvkmr.getGia());
            list = q.getResultList();
            sess.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list.isEmpty()) {
            return false;
        } else {
            return true;
        }
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

    //Đã sửa
    public List<SanPhamViewKMResponse> getAllSanPham() {
        List<SanPhamViewKMResponse> list = new ArrayList<>();
        try {
            Session sess = HibernateUtil.getFACTORY().openSession();
            Query q = sess.createQuery("SELECT new custommodel.SanPhamViewKMResponse (s.idSanPham.ma, s.idSanPham.ten, s.idCPU.ten, s.idHang.ten, s.idRam.ten, s.idCard.ten, s.idOCung.ten, s.gia, COUNT(s.idSanPham.id)) FROM ChiTietSP s WHERE s.tinhTrang = 0 GROUP BY s.idSanPham.ma, s.idSanPham.ten, s.idCPU.ten, s.idHang.ten, s.idRam.ten, s.idCard.ten, s.idOCung.ten, s.gia");
            list = q.getResultList();
            sess.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<SanPhamViewKMResponse> getAllSanPhamKhuyenMai(KhuyenMai km) {
        List<SanPhamViewKMResponse> list = new ArrayList<>();
        try {
            Session sess = HibernateUtil.getFACTORY().openSession();
            Query q = sess.createQuery("SELECT new custommodel.SanPhamViewKMResponse (s.idSanPham.ma, s.idSanPham.ten, s.idCPU.ten, s.idHang.ten, s.idRam.ten, s.idCard.ten, s.idOCung.ten, s.gia, COUNT(s.idSanPham)) FROM ChiTietSP s WHERE s.tinhTrang = 0 AND s.id IN(SELECT spkm.idChiTietSP FROM SanPhamKM spkm WHERE spkm.idKhuyenMai.ma = :maKM) GROUP BY s.idSanPham.ma, s.idSanPham.ten, s.idCPU.ten, s.idHang.ten, s.idRam.ten, s.idCard.ten, s.idOCung.ten, s.gia");
            q.setParameter("maKM", km.getMa());
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

    public void deleteSanPhamKhuyenMaiByMa(List<SanPhamViewKMResponse> listMaSP, KhuyenMai km) {
        List<ChiTietSP> listCTSP = new ArrayList<>();
        List<ChiTietSP> listCTSPFake = new ArrayList<>();
        try {
            Session sess = HibernateUtil.getFACTORY().openSession();
            for (SanPhamViewKMResponse spvkmr : listMaSP) {
                Query q = sess.createQuery("FROM ChiTietSP s WHERE s.idSanPham.ma = :maSP AND s.idSanPham.ten = :tenSP AND s.idCPU.ten = :cpu AND s.idHang.ten = :hang AND s.idRam.ten = :ram AND s.idCard.ten = :card AND s.idOCung.ten = :oCung AND s.gia = :gia AND s.id IN(SELECT spkm.idChiTietSP.id FROM SanPhamKM spkm WHERE spkm.idKhuyenMai.ma = :maKM)");
                q.setParameter("maKM", km.getMa());
                q.setParameter("maSP", spvkmr.getMa());
                q.setParameter("tenSP", spvkmr.getTen());
                q.setParameter("cpu", spvkmr.getCpu());
                q.setParameter("hang", spvkmr.getHang());
                q.setParameter("ram", spvkmr.getRam());
                q.setParameter("card", spvkmr.getCard());
                q.setParameter("oCung", spvkmr.getOCung());
                q.setParameter("gia", spvkmr.getGia());
                listCTSPFake = q.getResultList();
                for (ChiTietSP chiTietSP : listCTSPFake) {
                    listCTSP.add(chiTietSP);
                }
            }
            for (ChiTietSP chiTietSP : listCTSP) {
                Query q1 = sess.createQuery("FROM SanPhamKM spkm WHERE spkm.idChiTietSP.id = :idCTSP AND spkm.idKhuyenMai.ma = :maKM");
                q1.setParameter("idCTSP", chiTietSP.getId());
                q1.setParameter("maKM", km.getMa());
                List<SanPhamKM> listspkm = q1.getResultList();
                for (SanPhamKM spkm : listspkm) {
                    sess.getTransaction().begin();
                    sess.delete(spkm);
                    sess.getTransaction().commit();
                }
            }
            sess.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertSanPhamKhuyenMai(List<SanPhamViewKMResponse> listMaSP, KhuyenMai km) {
        List<ChiTietSP> listCTSP = new ArrayList<>();
        try {
            Session sess = HibernateUtil.getFACTORY().openSession();
            for (SanPhamViewKMResponse spvkmr : listMaSP) {
                Query q = sess.createQuery("FROM ChiTietSP s WHERE s.idSanPham.ma = :maSP AND s.idSanPham.ten = :tenSP AND s.idCPU.ten = :cpu AND s.idHang.ten = :hang AND s.idRam.ten = :ram AND s.idCard.ten = :card AND s.idOCung.ten = :oCung AND s.gia = :gia AND s.id NOT IN(SELECT spkm.idChiTietSP.id FROM SanPhamKM spkm WHERE spkm.idKhuyenMai.ma = :maKM)");
                q.setParameter("maKM", km.getMa());
                q.setParameter("maSP", spvkmr.getMa());
                q.setParameter("tenSP", spvkmr.getTen());
                q.setParameter("cpu", spvkmr.getCpu());
                q.setParameter("hang", spvkmr.getHang());
                q.setParameter("ram", spvkmr.getRam());
                q.setParameter("card", spvkmr.getCard());
                q.setParameter("oCung", spvkmr.getOCung());
                q.setParameter("gia", spvkmr.getGia());
                listCTSP = q.getResultList();
                for (ChiTietSP chiTietSP : listCTSP) {
                    SanPhamKM spkm = new SanPhamKM();
                    spkm.setIdKhuyenMai(km);
                    spkm.setIdChiTietSP(chiTietSP);
                    spkm.setDonGia(chiTietSP.getGia());
                    BigDecimal tienConLai = new BigDecimal(0);
                    if (km.getLoaiKM() == 0) {
                        tienConLai = chiTietSP.getGia().subtract(chiTietSP.getGia().multiply(km.getGiaTriKM()).divide(new BigDecimal(100)));
                    } else {
                        tienConLai = chiTietSP.getGia().subtract(km.getGiaTriKM());
                    }
                    spkm.setTienConLai(tienConLai);
                    spkm.setCreatedDate(new Date());
                    spkm.setLastModifiedDate(new Date());
                    sess.getTransaction().begin();
                    sess.save(spkm);
                    sess.getTransaction().commit();
                }
            }
            sess.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
