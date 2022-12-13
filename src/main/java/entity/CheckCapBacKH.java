/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import domainmodel.CPU;
import domainmodel.CardMH;
import domainmodel.ChiTietSP;
import domainmodel.Hang;
import domainmodel.HoaDon;
import domainmodel.HoaDonChiTiet;
import domainmodel.KhachHang;
import domainmodel.KhuyenMai;
import domainmodel.NhanVien;
import domainmodel.OCung;
import domainmodel.Ram;
import domainmodel.SanPham;
import domainmodel.SanPhamKM;
import domainmodel.SerialDaBan;
import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import service.impl.KhachHangServiceImpl;

/**
 *
 * @author daohi
 */
public class CheckCapBacKH extends Thread {

    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=DuAn1");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "123456");
        properties.put(Environment.SHOW_SQL, "true");

        conf.setProperties(properties);
        conf.addAnnotatedClass(SanPham.class);
        conf.addAnnotatedClass(NhanVien.class);
        conf.addAnnotatedClass(KhachHang.class);
        conf.addAnnotatedClass(HoaDon.class);
        conf.addAnnotatedClass(ChiTietSP.class);
        conf.addAnnotatedClass(HoaDonChiTiet.class);
        conf.addAnnotatedClass(SerialDaBan.class);
        conf.addAnnotatedClass(SanPhamKM.class);
        conf.addAnnotatedClass(KhuyenMai.class);
        conf.addAnnotatedClass(CPU.class);
        conf.addAnnotatedClass(Ram.class);
        conf.addAnnotatedClass(OCung.class);
        conf.addAnnotatedClass(Hang.class);
        conf.addAnnotatedClass(CardMH.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void dailyCheckingRankCustomer() {
        Thread countDownThread = new Thread() {
            @Override
            public void run() {
                do {
                    List<KhachHang> listKH = new KhachHangServiceImpl().getAll();
                    for (KhachHang xx : listKH) {
                        BigDecimal tienKhachMua = (BigDecimal) new KhachHangServiceImpl().getTongTienByIDHD(xx.getId());
                        if (tienKhachMua == null) {
                            continue;
                        }
                        if (tienKhachMua != null) {
                            if (tienKhachMua.compareTo(new BigDecimal(30000000)) < 0) {
                                xx.setCapBac(0);
                            }
                            if (tienKhachMua.compareTo(new BigDecimal(30000000)) > 0 && tienKhachMua.compareTo(new BigDecimal(60000000)) < 0) {
                                xx.setCapBac(1);
                            }
                            if (tienKhachMua.compareTo(new BigDecimal(60000000)) > 0 && tienKhachMua.compareTo(new BigDecimal(90000000)) < 0) {
                                xx.setCapBac(2);
                            }
                            if (tienKhachMua.compareTo(new BigDecimal(90000000)) > 0 && tienKhachMua.compareTo(new BigDecimal(120000000)) < 0) {
                                xx.setCapBac(3);
                            }
                            if (tienKhachMua.compareTo(new BigDecimal(120000000)) > 0) {
                                xx.setCapBac(4);
                            }
                            new KhachHangServiceImpl().add(xx);
                        }
                    }
                    try {
                        Thread.sleep(20000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (true);
            }
        };
        countDownThread.start();
    }
}
