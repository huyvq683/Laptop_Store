/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import custommodel.ViewChucVuCustomModel;
import domainModel.NhanVien;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utilities.HibernateUtil;

/**
 *
 * @author WIN11
 */
public class ChucVuRepository {

    private String fromTable = "FROM NhanVien";

    private Session session = HibernateUtil.getFACTORY().openSession();

    public List<ViewChucVuCustomModel> getAll() {
        String sql = "SELECT new custommodel.ViewChucVuCustomModel (n.Email, n.MatKhau, c.Ten, n.TrangThai)"
                + " FROM ChucVu c  JOIN"
                + " NhanVien n ON c.Id = n.IdChucVu";
        Query<ViewChucVuCustomModel> query = session.createQuery(sql);
        return query.list();
    }

}
