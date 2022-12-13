/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import custommodel.ChiTietSPResponse;
import domainmodel.CPU;
import domainmodel.CardMH;
import domainmodel.ChiTietSP;
import domainmodel.Hang;
import domainmodel.OCung;
import domainmodel.Ram;
import domainmodel.SanPham;
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
 * @author Đức Hiệu
 */
public class ChiTietSPRepository {

    public List<ChiTietSP> getAllChiTietSP() {
        List<ChiTietSP> listCTSP = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            NativeQuery query = session.createNativeQuery(" SELECT ct.id , ct.maCTSP , ct.idSanPham , ct.idCPU , ct.idCardMH, ct.idHang, ct.idOCung , ct.idRam ,ct.serial , ct.gia , ct.tinhTrang, ct.createdDate , ct.lastModifiedDate FROM ChiTietSP ct "
                    + " GROUP BY ct.id , ct.maCTSP , ct.idSanPham , ct.idCPU , ct.idCardMH , ct.idHang , ct.idOCung , ct.idRam ,ct.serial , ct.gia , ct.tinhTrang, ct.createdDate , ct.lastModifiedDate "
                    + " ORDER BY MAX(CONVERT(INT, SUBSTRING(maCTSP, 5, 10))) DESC", ChiTietSP.class);
            listCTSP = query.getResultList();
        } catch (Exception e) {
        }
        return listCTSP;
    }

    public List<ChiTietSP> getOneGia(String gia1, String gia2) {
        List<ChiTietSP> listctsp = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery(" FROM ChiTietSP  WHERE Gia BETWEEN :gia1 AND :gia2");
            query.setParameter("gia1", gia1);
            query.setParameter("gia2", gia2);
            listctsp = query.getResultList();
            return listctsp;
        } catch (Exception e) {
        }
        return null;
    }

    private String fromTable = "From SanPham";
    private Session session = HibernateUtil.getFACTORY().openSession();

    public ChiTietSP getOne(String seriall) {
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery(" SELECT new ChiTietSP (ct.id , ct.serial) FROM ChiTietSP ct WHERE Serial = :serial ");
            query.setParameter("serial", seriall);
            ChiTietSP ctsp = (ChiTietSP) query.getSingleResult();
            return ctsp;
        } catch (Exception e) {
        }
        return null;
    }

    public Boolean add(ChiTietSP ctsp) {
        Transaction transantion = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transantion = session.beginTransaction();
            session.save(ctsp);
            transantion.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean upDate(ChiTietSP ctsp, UUID id) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            ChiTietSP ctSP = session.get(ChiTietSP.class, id);
            ctSP.setId(id);
            ctSP.setIdCard(ctsp.getIdCard());
            ctSP.setIdCPU(ctsp.getIdCPU());
            ctSP.setCreatedDate(ctsp.getCreatedDate());
            ctSP.setGia(ctsp.getGia());
            ctSP.setIdHang(ctsp.getIdHang());
            ctSP.setLastModifiedDate(ctsp.getLastModifiedDate());
            ctSP.setIdRam(ctsp.getIdRam());
            ctSP.setSerial(ctsp.getSerial());
            ctSP.setIdSanPham(ctsp.getIdSanPham());
            session.update(ctSP);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public Boolean upDateTinhTrang(List<ChiTietSP> ctsp) {
        Transaction tranction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            tranction = session.beginTransaction();
            for (ChiTietSP x : ctsp) {
                x.setTinhTrang(2);
                session.update(x);
            }
            tranction.commit();
            return true;
        } catch (Exception e) {
        }
        return null;
    }

    public ChiTietSP getOneCheck(String tinhTrang) {
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery(" FROM ChiTietSP  WHERE TinhTrang = 1");
            query.setParameter("TinhTrang", tinhTrang);
            ChiTietSP ctsp = (ChiTietSP) query.getSingleResult();
            return ctsp;
        } catch (Exception e) {
        }
        return null;
    }

    public Boolean upDateKhoiPhuc(List<ChiTietSP> ctsp) {
        Transaction tranction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            tranction = session.beginTransaction();
            for (ChiTietSP x : ctsp) {
                x.setTinhTrang(0);
                session.update(x);
            }
            tranction.commit();
            return true;
        } catch (Exception e) {
        }
        return null;
    }

    public Boolean delete(ChiTietSP ctsp, UUID id) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            ChiTietSP ctSP = session.get(ChiTietSP.class, id);
            session.delete(ctSP);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public List<ChiTietSP> search(String searchKey) {
        List<ChiTietSP> listCTSP = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM ChiTietSP WHERE Serial like concat (:searchKey,'%') OR IdCPU like concat (:searchKey,'%') OR IdSanPham like concat (:searchKey,'%') OR IdRam like concat (:searchKey,'%') OR IdCardMH like concat (:searchKey,'%') OR IdOCung like concat (:searchKey,'%') OR Gia like concat (:searchKey,'%') OR IdHang like concat (:searchKey,'%')");
            query.setParameter("searchKey", searchKey);
            listCTSP = query.getResultList();
            return listCTSP;
        } catch (Exception e) {
        }
        return null;
    }

    public List<ChiTietSP> searchCPU(String searchKey) {
        List<ChiTietSP> listCTSP = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            org.hibernate.query.Query query = session.createQuery("SELECT c FROM ChiTietSP c WHERE c.idCPU.ten = :cpu");
            query.setParameter("cpu", searchKey);
            listCTSP = query.getResultList();
            return listCTSP;
        } catch (Exception e) {
        }
        return null;
    }

    public List<ChiTietSP> searchSP(String searchKey) {
        List<ChiTietSP> listCTSP = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            org.hibernate.query.Query query = session.createQuery("SELECT c FROM ChiTietSP c WHERE c.idSanPham.ten = :sanPham");
            query.setParameter("sanPham", searchKey);
            listCTSP = query.getResultList();
            return listCTSP;
        } catch (Exception e) {
        }
        return null;
    }

    public List<ChiTietSP> searchHang(String searchKey) {
        List<ChiTietSP> listCTSP = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            org.hibernate.query.Query query = session.createQuery("SELECT c FROM ChiTietSP c WHERE c.idHang.ten = :hang");
            query.setParameter("hang", searchKey);
            listCTSP = query.getResultList();
            return listCTSP;
        } catch (Exception e) {
        }
        return null;
    }

    public List<ChiTietSP> searchRam(String searchKey) {
        List<ChiTietSP> listCTSP = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            org.hibernate.query.Query query = session.createQuery("SELECT c FROM ChiTietSP c WHERE c.idRam.ten = :ram");
            query.setParameter("ram", searchKey);
            listCTSP = query.getResultList();
            return listCTSP;
        } catch (Exception e) {
        }
        return null;
    }

    public List<ChiTietSP> searchCard(String searchKey) {
        List<ChiTietSP> listCTSP = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            org.hibernate.query.Query query = session.createQuery("SELECT c FROM ChiTietSP c WHERE c.idCard.ten = :card");
            query.setParameter("card", searchKey);
            listCTSP = query.getResultList();
            return listCTSP;
        } catch (Exception e) {
        }
        return null;
    }

    public List<ChiTietSP> searchOCung(String searchKey) {
        List<ChiTietSP> listCTSP = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            org.hibernate.query.Query query = session.createQuery("SELECT c FROM ChiTietSP c WHERE c.idOCung.ten = :oCung");
            query.setParameter("oCung", searchKey);
            listCTSP = query.getResultList();
            return listCTSP;
        } catch (Exception e) {
        }
        return null;
    }

    public SanPham findTenSP(String ten) {
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery(" SELECT new SanPham (s.id,s.ten) FROM SanPham s WHERE Ten =:ten ", SanPham.class);
            query.setParameter("ten", ten);
            SanPham sp = (SanPham) query.getSingleResult();
            return sp;
        } catch (Exception e) {
        }
        return null;
    }

    public CPU findTenCPU(String ten) {
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery(" SELECT new CPU (s.id , s.ten) FROM CPU s WHERE Ten =:ten ", CPU.class);
            query.setParameter("ten", ten);
            CPU sp = (CPU) query.getSingleResult();
            return sp;
        } catch (Exception e) {
        }
        return null;
    }

    public CardMH findTenCard(String ten) {
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery(" SELECT new CardMH (s.id , s.ten) FROM CardMH s WHERE Ten =:ten ", CardMH.class);
            query.setParameter("ten", ten);
            CardMH sp = (CardMH) query.getSingleResult();
            return sp;
        } catch (Exception e) {
        }
        return null;
    }

    public Hang findTenHang(String ten) {
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery(" SELECT new Hang (s.id , s.ten) FROM Hang s WHERE Ten =:ten ", Hang.class);
            query.setParameter("ten", ten);
            Hang sp = (Hang) query.getSingleResult();
            return sp;
        } catch (Exception e) {
        }
        return null;
    }

    public Ram findTenRam(String ten) {
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery(" SELECT new Ram (s.id , s.ten) FROM Ram s WHERE Ten =:ten ", Ram.class);
            query.setParameter("ten", ten);
            Ram sp = (Ram) query.getSingleResult();
            return sp;
        } catch (Exception e) {
        }
        return null;
    }

    public OCung findTenOCung(String ten) {
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery(" SELECT new OCung (s.id , s.ten) FROM OCung s WHERE Ten =:ten ", OCung.class);
            query.setParameter("ten", ten);
            OCung sp = (OCung) query.getSingleResult();
            return sp;
        } catch (Exception e) {
        }
        return null;
    }

    public ChiTietSP findSerial(String soSerial) {
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery(" SELECT new ChiTietSP (s.id ,s.serial) FROM ChiTietSP s WHERE Serial =:serial ", ChiTietSP.class);
            query.setParameter("serial", soSerial);
            ChiTietSP sp = (ChiTietSP) query.getSingleResult();
            return sp;
        } catch (Exception e) {
        }
        return null;
    }

    public List<ChiTietSPResponse> getAll() {
        List<ChiTietSPResponse> lists = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            org.hibernate.query.Query query = session.createQuery("SELECT new custommodel.ChiTietSPResponse "
                    + "(ct.idSanPham.ma, ct.idSanPham.ten,"
                    + "ct.idCPU.ten, ct.idRam.ten, ct.idCard.ten, ct.idOCung.ten, ct.idHang.ten, ct.tinhTrang, ct.gia, COUNT(ct.idSanPham)) "
                    + "FROM ChiTietSP ct WHERE ct.tinhTrang = 0"
                    + "GROUP BY ct.idSanPham.ma, ct.idSanPham.ten, ct.idCPU.ten, ct.idRam.ten, ct.idCard.ten, ct.idOCung.ten, ct.idHang.ten, ct.tinhTrang, ct.gia");
            lists = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }

    public List<ChiTietSPResponse> getAllCTSP(String ma, String ten, String cPU, String card, BigDecimal gia, String hang, String oCung, String ram) {
        List<ChiTietSPResponse> lists = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            org.hibernate.query.Query query = session.createQuery("SELECT new custommodel.ChiTietSPResponse "
                    + "(ct.id, ct.idSanPham.ma, ct.idSanPham.ten, "
                    + "ct.idCPU.ten, ct.idRam.ten, ct.idCard.ten, ct.idOCung.ten, ct.idHang.ten, ct.tinhTrang, ct.gia, ct.serial) "
                    + "FROM ChiTietSP ct WHERE ct.idSanPham.ma = :ma AND ct.idSanPham.ten = :ten AND ct.idCPU.ten = :cPU AND ct.idCard.ten = :card "
                    + "AND ct.gia = :gia AND ct.idHang.ten = :hang AND ct.idRam.ten = :ram "
                    + "AND ct.idOCung.ten = :oCung AND ct.tinhTrang = 0");
            query.setParameter("ma", ma);
            query.setParameter("ten", ten);
            query.setParameter("cPU", cPU);
            query.setParameter("card", card);
            query.setParameter("gia", gia);
            query.setParameter("hang", hang);
            query.setParameter("ram", ram);
            query.setParameter("oCung", oCung);
            lists = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }

    public List<ChiTietSPResponse> searchCTSP(String ma) {
        List<ChiTietSPResponse> lists = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            org.hibernate.query.Query query = session.createQuery("SELECT new custommodel.ChiTietSPResponse "
                    + "(ct.idSanPham.ma, ct.idSanPham.ten, "
                    + "ct.idCPU.ten, ct.idRam.ten, ct.idCard.ten, "
                    + "ct.idOCung.ten, ct.idHang.ten, ct.tinhTrang, ct.gia, COUNT(ct.idSanPham)) "
                    + "FROM ChiTietSP ct WHERE ct.tinhTrang = 0 AND ct.idSanPham.ma LIKE CONCAT('%',:ma,'%') "
                    + "OR ct.idSanPham.ten LIKE CONCAT('%',:ten,'%') "
                    + "OR ct.idCPU.ten LIKE CONCAT('%',:cPU,'%')"
                    + "OR ct.idCard.ten LIKE CONCAT('%',:card,'%')"
                    + "OR ct.gia LIKE CONCAT('%',:gia,'%')"
                    + "OR ct.idHang.ten LIKE CONCAT('%',:hang,'%')"
                    + "OR ct.idRam.ten LIKE CONCAT('%',:ram,'%')"
                    + "OR ct.idOCung.ten LIKE CONCAT('%',:oCung,'%')"
                    + "GROUP BY ct.idSanPham.ma, ct.idSanPham.ten, ct.idCPU.ten, "
                    + "ct.idRam.ten, ct.idCard.ten, ct.idOCung.ten, ct.idHang.ten, ct.tinhTrang, ct.gia");
            query.setParameter("ma", ma);
            query.setParameter("ten", ma);
            query.setParameter("cPU", ma);
            query.setParameter("card", ma);
            query.setParameter("gia", ma);
            query.setParameter("hang", ma);
            query.setParameter("ram", ma);
            query.setParameter("oCung", ma);
            lists = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }

    public ChiTietSP getBySerialChiTietSP(String serial) {
        ChiTietSP chiTietSP = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            org.hibernate.query.Query query = session.createQuery("SELECT c "
                    + "FROM ChiTietSP c WHERE c.serial = :serial");
            query.setParameter("serial", serial);
            chiTietSP = (ChiTietSP) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return chiTietSP;
    }

    public boolean updateTinhTrangSP(ChiTietSP chiTietSP, UUID id) {
        boolean check = true;
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            ChiTietSP ctsp = session.get(ChiTietSP.class, id);
            ctsp.setTinhTrang(1);
            session.update(ctsp);
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
            org.hibernate.query.Query q = sess.createQuery("FROM ChiTietSP WHERE serial = :serial");
            q.setParameter("serial", serial);
            ctsp = (ChiTietSP) q.getSingleResult();
            sess.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ctsp;
    }

    public boolean updateTinhTrangSP(List<String> listSerial) {
        boolean check = true;
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            for (String serial : listSerial) {
                ChiTietSP chiTietSP = getBySerialChiTietSP(serial);
                chiTietSP.setTinhTrang(1);
                session.update(chiTietSP);
            }
            transaction.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check;
    }

    public boolean updateTinhTrang(ChiTietSP chiTietSP) {
        boolean check = true;
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(chiTietSP);
            transaction.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check;
    }

    public boolean updateTinhTrangChuaBan(List<String> listSerial) {
        boolean check = true;
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            for (String serial : listSerial) {
                ChiTietSP chiTietSP = getBySerialChiTietSP(serial);
                chiTietSP.setTinhTrang(0);
                session.update(chiTietSP);
            }
            transaction.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check;
    }

}
