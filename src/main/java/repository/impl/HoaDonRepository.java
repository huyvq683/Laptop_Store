/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import domainmodel.HoaDon;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utility.HibernateUtil;

/**
 *
 * @author WIN11
 */
public class HoaDonRepository {

    private String fromTable = "FROM HoaDon";

    private Session session = HibernateUtil.getFACTORY().openSession();

    public List<HoaDon> getByOne(String trangThai) {
        try {
            String sql = fromTable + " Where TinhTrang = :tt";
            Query query = session.createQuery(sql);
            query.setParameter("tt", trangThai);
            List<HoaDon> list = query.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public List<HoaDon> getAll() {
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            javax.persistence.Query query = session.createQuery(fromTable, HoaDon.class);
            List<HoaDon> list = query.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
}
