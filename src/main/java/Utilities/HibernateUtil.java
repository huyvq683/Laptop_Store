package utilities;

import domainModel.ChiTietSp;
import domainModel.HoaDon;
import domainModel.HoaDonChiTiet;
import domainModel.KhachHang;
import domainModel.NhanVien;
import domainModel.SanPham;
import domainModel.TaiKhoan;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static final SessionFactory FACTORY;

    static {

        Properties prop = getProperties();
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(prop).build();
        Configuration conf = new Configuration();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void main(String[] args) {
        getFACTORY();
    }

    public static Properties getProperties() {
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=DuAn1");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "123456");
        properties.put(Environment.SHOW_SQL, "true");
        return properties;
    }

    public static Configuration getConfiguration(Properties prop) {
        Configuration conf = new Configuration();
        conf.setProperties(prop);
        conf.addAnnotatedClass(SanPham.class);
        conf.addAnnotatedClass(ChiTietSp.class);
        conf.addAnnotatedClass(HoaDon.class);
        conf.addAnnotatedClass(HoaDonChiTiet.class);
        conf.addAnnotatedClass(KhachHang.class);
        conf.addAnnotatedClass(NhanVien.class);
        conf.addAnnotatedClass(TaiKhoan.class);
        return conf;
    }
}
