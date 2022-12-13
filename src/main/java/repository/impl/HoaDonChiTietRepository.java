/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import custommodel.HoaDonChiTietResponse;
import domainmodel.ChiTietSP;
import domainmodel.HoaDon;
import domainmodel.HoaDonChiTiet;
import domainmodel.SanPhamKM;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utility.HibernateUtil;

/**
 *
 * @author FPT
 */
public class HoaDonChiTietRepository {

    public List<HoaDonChiTietResponse> getAll(UUID id) {
        List<HoaDonChiTietResponse> lists = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT new custommodel.HoaDonChiTietResponse "
                    + "(c.idHoaDon.id, c.idCTSP.idSanPham.ma, c.idCTSP.idSanPham.ten, c.tienKM, c.donGia, COUNT(c.idCTSP.idSanPham)) "
                    + "FROM HoaDonChiTiet c WHERE c.idHoaDon.id = :id "
                    + "GROUP BY c.idHoaDon.id, c.idCTSP.idSanPham.ma, c.idCTSP.idSanPham.ten, c.tienKM, c.donGia");
            query.setParameter("id", id);
            lists = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }

    public List<HoaDonChiTietResponse> getList(UUID id) {
        List<HoaDonChiTietResponse> lists = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT new custommodel.HoaDonChiTietResponse "
                    + "(c.id, c.idCTSP.id, c.idHoaDon.id, c.idCTSP.idSanPham.ma, c.idCTSP.idSanPham.ten, c.donGia, c.idCTSP.serial) "
                    + "FROM HoaDonChiTiet c WHERE c.idHoaDon.id = :id ");
            query.setParameter("id", id);
            lists = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }

//lười dùng id quá :)))
    public List<HoaDonChiTietResponse> get_All(String ma) {
        List<HoaDonChiTietResponse> lists = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT new custommodel.HoaDonChiTietResponse"
                    + "(c.idHoaDon.id, c.idHoaDon.ma, c.idCTSP.idSanPham.ten, c.tienKM, c.idCTSP.serial , c.donGia, COUNT(c.idCTSP.idSanPham)) "
                    + "FROM HoaDonChiTiet c WHERE c.idHoaDon.ma = :ma "
                    + "GROUP BY c.idHoaDon.id,  c.idHoaDon.ma, c.idCTSP.idSanPham.ma,c.idCTSP.serial , c.idCTSP.idSanPham.ten, c.tienKM, c.donGia");
            query.setParameter("ma", ma);
            lists = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }

    public Boolean delete(UUID id) {
        boolean check = false;

        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM HoaDonChiTiet hd WHERE hd.idHoaDon.id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check;
    }

    public ChiTietSP getCTSPBySerial(String serial) {
        ChiTietSP ctsp = null;
        try {
            Session sess = HibernateUtil.getFACTORY().openSession();
            Query q = sess.createQuery("FROM ChiTietSP WHERE serial = :serial1");
            q.setParameter("serial1", serial);
            ctsp = (ChiTietSP) q.getSingleResult();
            sess.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ctsp;
    }

    public List<SanPhamKM> getSanPhamKM(UUID id) {
        List<SanPhamKM> lits = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM SanPhamKM WHERE idChiTietSP.id = :idChiTietSP");
            query.setParameter("idChiTietSP", id);
            lits = query.getResultList();
        } catch (Exception e) {
        }
        return lits;
    }

    public Boolean add(List<String> listSerial, HoaDon hd) {
        Transaction tran = null;
        BigDecimal tienKM = new BigDecimal(0);
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            for (String serial : listSerial) {
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                ChiTietSP ctsp = getCTSPBySerial(serial);
                List<SanPhamKM> ListSanPhamKM = getSanPhamKM(ctsp.getId());
                for (SanPhamKM sanPhamKM : ListSanPhamKM) {
                    if (sanPhamKM != null) {
                        tienKM = tienKM.add(sanPhamKM.getDonGia().subtract(sanPhamKM.getTienConLai()));
                    }
                }
                hdct.setIdHoaDon(hd);
                hdct.setIdCTSP(ctsp);
                hdct.setTenSP(ctsp.getIdSanPham().getTen());
                hdct.setTienKM(tienKM);
                hdct.setDonGia(ctsp.getGia());
                hdct.setCreatedDate(new Date());
                hdct.setLastModifiedDate(new Date());
                session.save(hdct);
            }
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public Boolean addOne(HoaDonChiTiet hoaDonChiTiet) {
        boolean check = false;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(hoaDonChiTiet);
            transaction.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check;
    }

    public HoaDonChiTiet getHDCTByIdChiTietSP(UUID id) {
        HoaDonChiTiet hoaDonChiTiet = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM HoaDonChiTiet WHERE idCTSP.id = :id");
            query.setParameter("id", id);
            hoaDonChiTiet = (HoaDonChiTiet) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoaDonChiTiet;
    }

    public boolean deleteHDCT(List<String> listSerial) {
        boolean check = true;
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            for (String serial : listSerial) {
                ChiTietSP chiTietSP = getCTSPBySerial(serial);
                HoaDonChiTiet hoaDonChiTiet = getHDCTByIdChiTietSP(chiTietSP.getId());
                session.delete(hoaDonChiTiet);
            }
            transaction.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check;
    }

}
