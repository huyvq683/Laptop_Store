/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import customModel.HoaDonResponse;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import repository.Repository;
import utiliti.HibernateUtil;

/**
 *
 * @author FPT
 */
public class HoaDonRepository implements Repository<HoaDonResponse> {

    @Override
    public List<HoaDonResponse> getAll() {
        List<HoaDonResponse> lists = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT new customModel.HoaDonResponse"
                    + "(h.id, h.ma, h.ngayTao, nv.hoTen, h.tinhTrang) "
                    + "FROM HoaDon h LEFT JOIN NhanVien nv "
                    + "on h.idNV = nv.id ORDER BY h.ma DESC");
            lists = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }

    public static void main(String[] args) {
        List<HoaDonResponse> lists = new HoaDonRepository().getAll();
        System.out.println(lists);
    }
}
