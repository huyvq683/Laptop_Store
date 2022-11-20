/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import customModel.ChiTietSPResponse;
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
public class ChiTietSPRepository implements Repository<ChiTietSPResponse>{

    @Override
    public List<ChiTietSPResponse> getAll() {
        List<ChiTietSPResponse>lists = new ArrayList<>();
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT new customModel.ChiTietSPResponse "
                    + "(ct.id, ct.idSanPham.ma, ct.idSanPham.ten, ct.serial, "
                    + "ct.cPU, ct.hang, ct.ram, ct.card, ct.gia) "
                    + "FROM ChiTietSP ct"); 
            lists = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }

    @Override
    public Boolean add(ChiTietSPResponse t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
