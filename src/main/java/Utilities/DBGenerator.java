/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import domainModel.ChiTietSp;
import domainModel.KhachHang;
import domainModel.NhanVien;
import domainModel.SanPham;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import utilities.HibernateUtil;
import static utilities.HibernateUtil.getConfiguration;

/**
 *
 * @author vinhnv
 */
public class DBGenerator {

    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Properties prop = HibernateUtil.getProperties();
        prop.put(Environment.HBM2DDL_AUTO, "create");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(prop)
                .build();

        Configuration conf = getConfiguration(prop);
        SessionFactory factory = conf.buildSessionFactory(serviceRegistry);
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();

        SanPham sanPham = new SanPham();
        sanPham.setTen("San pham 1");
        sanPham.setMa("SP1");
        sanPham.setCreatedDate(new Date());
        sanPham.setLastModifiedDate(new Date());
        session.save(sanPham);

        SanPham sanPham1 = new SanPham();
        sanPham.setTen("San pham 2");
        sanPham.setMa("SP2");
        sanPham.setCreatedDate(new Date());
        sanPham.setLastModifiedDate(new Date());
        session.save(sanPham1);

        SanPham sanPham2 = new SanPham();
        sanPham.setTen("San pham 3");
        sanPham.setMa("SP3");
        sanPham.setCreatedDate(new Date());
        sanPham.setLastModifiedDate(new Date());
        session.save(sanPham2);

        NhanVien nhanVien = new NhanVien();
        nhanVien.setMa("NV1");
        nhanVien.setHoTen("Phùng Việt Hùng");
        nhanVien.setGioiTinh(true);
        nhanVien.setDiaChi("Thanh Hóa");
        nhanVien.setSdt("0345424622");
        nhanVien.setEmail("hung@gmail.com");
        nhanVien.setTrangThai(0);
        nhanVien.setCreatedDate(new Date());
        nhanVien.setLastModifiedDate(new Date());
        session.save(nhanVien);

        NhanVien nhanVien1 = new NhanVien();
        nhanVien1.setMa("NV2");
        nhanVien1.setHoTen("Vũ Quang Huy");
        nhanVien1.setGioiTinh(false);
        nhanVien1.setDiaChi("Nam Định");
        nhanVien1.setSdt("0345424622");
        nhanVien1.setEmail("huy@gmail.com");
        nhanVien1.setTrangThai(0);
        nhanVien1.setCreatedDate(new Date());
        nhanVien1.setLastModifiedDate(new Date());
        session.save(nhanVien1);

        NhanVien nhanVien2 = new NhanVien();
        nhanVien2.setMa("NV3");
        nhanVien2.setHoTen("Nguyễn Đức Hiệu");
        nhanVien2.setGioiTinh(false);
        nhanVien2.setDiaChi("Nam Định");
        nhanVien2.setSdt("0345424622");
        nhanVien2.setEmail("hieu@gmail.com");
        nhanVien2.setTrangThai(0);
        nhanVien2.setCreatedDate(new Date());
        nhanVien2.setLastModifiedDate(new Date());
        session.save(nhanVien2);

        ChiTietSp chiTietSP = new ChiTietSp();
        chiTietSP.setIdSanPham(sanPham);
        chiTietSP.setSerial("123456789");
        chiTietSP.setCPU("i7");
        chiTietSP.setHang("acer");
        chiTietSP.setRam("32G");
        chiTietSP.setCard("RTX4090");
        chiTietSP.setGia(new BigDecimal(10000.0));
        chiTietSP.setCreatedDate(new Date());
        chiTietSP.setLastModifiedDate(new Date());
        session.save(chiTietSP);

        ChiTietSp chiTietSP1 = new ChiTietSp();
        chiTietSP1.setIdSanPham(sanPham);
        chiTietSP1.setSerial("987654321");
        chiTietSP1.setCPU("i9");
        chiTietSP1.setHang("Predator");
        chiTietSP1.setRam("32G");
        chiTietSP1.setCard("RTX4090");
        chiTietSP1.setGia(new BigDecimal(10000.0));
        chiTietSP1.setCreatedDate(new Date());
        chiTietSP1.setLastModifiedDate(new Date());
        session.save(chiTietSP1);

        ChiTietSp chiTietSP2 = new ChiTietSp();
        chiTietSP2.setIdSanPham(sanPham);
        chiTietSP2.setSerial("543219876");
        chiTietSP2.setCPU("i7");
        chiTietSP2.setHang("ROG");
        chiTietSP2.setRam("32G");
        chiTietSP2.setCard("RTX4090");
        chiTietSP2.setGia(new BigDecimal(10000.0));
        chiTietSP2.setCreatedDate(new Date());
        chiTietSP2.setLastModifiedDate(new Date());
        session.save(chiTietSP2);

        KhachHang khachHang = new KhachHang();
        khachHang.setMa("KH1");
        khachHang.setHoTen("Phan Quốc Huy");
        khachHang.setGioiTinh(false);
        try {
            khachHang.setNgaySinh(dateFormat.parse("2003-05-06"));
        } catch (ParseException ex) {
            Logger.getLogger(DBGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        khachHang.setDiaChi("Nam Định");
        khachHang.setSdt("0345424622");    
        khachHang.setCreatedDate(new Date());
        khachHang.setLastModifiedDate(new Date());
        session.save(khachHang);

        KhachHang khachHang1 = new KhachHang();
        khachHang1.setMa("KH2");
        khachHang1.setHoTen("Đào Minh Hiền");
        khachHang1.setGioiTinh(false);
        try {
            khachHang1.setNgaySinh(dateFormat.parse("2003-05-06"));
        } catch (ParseException ex) {
            Logger.getLogger(DBGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        khachHang1.setDiaChi("Hà Nội");
        khachHang1.setSdt("0345424622");    
        khachHang1.setCreatedDate(new Date());
        khachHang1.setLastModifiedDate(new Date());
        session.save(khachHang1);

        KhachHang khachHang2 = new KhachHang();
        khachHang2.setMa("KH1");
        khachHang2.setHoTen("Lương Thị Nhung");
        khachHang2.setGioiTinh(false);
        try {
            khachHang2.setNgaySinh(dateFormat.parse("2003-05-06"));
        } catch (ParseException ex) {
            Logger.getLogger(DBGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        khachHang2.setDiaChi("Hà Nội");
        khachHang2.setSdt("0345424622");    
        khachHang2.setCreatedDate(new Date());
        khachHang2.setLastModifiedDate(new Date());
        session.save(khachHang2);

        trans.commit();
    }

}
