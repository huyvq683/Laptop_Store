package service.impl;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import custommodel.ThongKeDoanhThuRespone;
import custommodel.ThongKeSanPhamRespone;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import repository.impl.ThongKeRepository;
import service.ThongKeService;

/**
 *
 * @author Đức Hiệu
 */
public class ThongKeServiceImpl implements ThongKeService {

    private ThongKeRepository res = new ThongKeRepository();

    @Override
    public List<ThongKeDoanhThuRespone> getAllDoanhThu(Date date) {
        return res.getAllDoanhThu(date);
    }

    @Override
    public List<ThongKeDoanhThuRespone> getAllDoanhThuMonth(int thang, int nam) {
        return res.getAllDoanhThuMonth(thang, nam);
    }

    @Override
    public List<ThongKeDoanhThuRespone> getAllDoanhThuYear(int nam) {
        return res.getAllDoanhThuYear(nam);
    }

    @Override
    public String getDoanhThuDay(Date ngay) {
        return res.getDoanhThuDay(ngay);
    }

    @Override
    public String getDoanhThuYear(int nam) {
        return res.getDoanhThuYear(nam);
    }

    @Override
    public String getDoanhThuMonth(int thang, int nam) {
        return res.getDoanhThuMonth(thang, nam);
    }

    @Override
    public Integer namBatDauDoanhThu() {
        return res.namBatDauDoanhThu();
    }

    @Override
    public List<ThongKeSanPhamRespone> getAllSanPham(Date date) {
        return res.getAllSanPham(date);
    }

    @Override
    public List<ThongKeSanPhamRespone> getAllSanPhamKhoangNgay(Date bd, Date kt) {
        return res.getAllSanPhamKhoangNgay(bd, kt);
    }

    @Override
    public Long spKinhDoanh(int t) {
        return res.spKinhDoanh(t);
    }

    @Override
    public Long spKinhDoanhAll() {
        return res.spKinhDoanhAll();
    }

    @Override
    public List<ThongKeSanPhamRespone> getAllSanPhamMonth(int thang, int nam) {
        return res.getAllSanPhamMonth(thang, nam);
    }

    @Override
    public List<ThongKeSanPhamRespone> getAllSanPhamYear(int n) {
        return res.getAllSanPhamYear(n);
    }

    @Override
    public Integer namBatDau() {
        return res.namBatDau();
    }

    @Override
    public List<ThongKeDoanhThuRespone> getAllDoanhThuKhoangNgay(Date n, Date kt) {
        return res.getAllDoanhThuKhoangNgay(n, kt);
    }

    @Override
    public String getDoanhThuKhoangNgay(Date bd, Date kt) {
        return res.getDoanhThuKhoangDay(bd, kt);
    }

    @Override
    public void bieuDoDoanhThuMonth(int ngay, int thang, int nam, JPanel jpnItem) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (thang == 1 || thang == 3 || thang == 5 || thang == 7 || thang == 8 || thang == 10 || thang == 12) {
            for (int i = 1; i <= 31; i++) {
                dataset.addValue(new BigDecimal(res.bieuDoDoanhThuMonth(i, thang, nam)), "Doanh số", i + "");
            }
        }
        if (thang == 2) {
            for (int i = 1; i <= 28; i++) {
                dataset.addValue(new BigDecimal(res.bieuDoDoanhThuMonth(i, thang, nam)), "Doanh số", i + "");
            }
        }
        if (thang == 4 || thang == 6 || thang == 9 || thang == 11) {
            for (int i = 1; i <= 30; i++) {
                dataset.addValue(new BigDecimal(res.bieuDoDoanhThuMonth(i, thang, nam)), "Doanh số", i + "");
            }
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ thống kê doanh thu tháng " + thang + " năm " + nam + "".toUpperCase(),
                "Thời gian", "Doanh thu",
                dataset, PlotOrientation.VERTICAL,
                false, false, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 580));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

    @Override
    public void bieuDoDoanhThuYear(int thang, int nam, JPanel jpnItem) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 1; i <= 12; i++) {
            dataset.addValue(new BigDecimal(res.bieuDoDoanhThuYear(i, nam)), "Doanh số", i + "");
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ thống kê doanh thu năm " + nam + "".toUpperCase(),
                "Thời gian", "Doanh thu",
                dataset, PlotOrientation.VERTICAL, false, false, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 580));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }
//    @Override
//    public void bieuDoSanPhamMonth(int ngay, int thang, int nam, JPanel jpnItem) {
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        if (thang == 1 || thang == 3 || thang == 5 || thang == 7 || thang == 8 || thang == 10 || thang == 12) {
//            for (int i = 1; i <= 31; i++) {
//                dataset.addValue(new Integer(res.bieuDoSanPhamMonth(i, thang, nam)), "Doanh số", i + "");
//            }
//        }
//        if (thang == 2) {
//            for (int i = 1; i <= 28; i++) {
//                dataset.addValue(new Integer(res.bieuDoSanPhamMonth(i, thang, nam)), "Doanh số", i + "");
//            }
//        }
//        if (thang == 4 || thang == 6 || thang == 9 || thang == 11) {
//            for (int i = 1; i <= 28; i++) {
//                dataset.addValue(new Integer(res.bieuDoSanPhamMonth(i, thang, nam)), "Doanh số", i + "");
//            }
//        }
//
//        JFreeChart barChart = ChartFactory.createBarChart(
//                "Biểu đồ thống kê số lượng sản phẩm đã bán ra tháng " + thang + " năm " + nam + "".toUpperCase(),
//                "Thời gian", "Số lượng",
//                dataset, PlotOrientation.VERTICAL, false, false, false);
//        ChartPanel chartPanel = new ChartPanel(barChart);
//        chartPanel.setPreferredSize(
//                new Dimension(jpnItem.getWidth(), 578));
//        jpnItem.removeAll();
//        jpnItem.setLayout(
//                new CardLayout());
//        jpnItem.add(chartPanel);
//        jpnItem.validate();
//        jpnItem.setBackground(Color.MAGENTA);
//        jpnItem.repaint();
//    }
//    @Override
//    public void bieuDoSanPhamYear(int thang, int nam, JPanel jpnItem) {
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        for (int i = 1; i <= 12; i++) {
//            dataset.addValue(new Integer(res.bieuDoSanPhamYear(i, nam)), "Doanh số", i + "");
//        }
//        JFreeChart barChart = ChartFactory.createBarChart(
//                "Biểu đồ thống kê số lượng sản phẩm đã bán ra  năm " + nam + " ".toUpperCase(),
//                "Thời gian", "Số lượng",
//                dataset, PlotOrientation.VERTICAL, false, false, false);
//        ChartPanel chartPanel = new ChartPanel(barChart);
//        chartPanel.setPreferredSize(
//                new Dimension(jpnItem.getWidth(), 578));
//        jpnItem.removeAll();
//        jpnItem.setLayout(
//                new CardLayout());
//        jpnItem.add(chartPanel);
//        jpnItem.validate();
//        jpnItem.setBackground(Color.MAGENTA);
//        jpnItem.repaint();
//    }
}
