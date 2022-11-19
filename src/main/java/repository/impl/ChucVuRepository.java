/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import customModel.ChucVuResponse;
import domainModel.ChucVu;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utiliti.HibernateUtil;

/**
 *
 * @author WIN11
 */
public class ChucVuRepository {

    public List<ChucVuResponse> getAll() {
        try ( Session session = HibernateUtil.getFACTORY().openSession();) {
            String sql = "SELECT new customModel.ChucVuResponse (n.email, n.matKhau, c.ten, c.ma, n.trangThai)"
                    + " FROM ChucVu c JOIN"
                    + " NhanVien n ON c.id = n.idChucVu";
            Query<ChucVuResponse> query = session.createQuery(sql);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

}
