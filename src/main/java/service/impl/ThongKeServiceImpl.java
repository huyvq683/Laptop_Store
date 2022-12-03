package service.impl;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import custommodel.ThongKeBieuDoHD;
import custommodel.ThongKeBieuDoSP;
import custommodel.ThongKeDoanhThuRespone;
import custommodel.ThongKeSanPhamRespone;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
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
    public void getBieuDoDTMonth(int thang, int nam, JPanel jpnItem) {
        List<ThongKeBieuDoHD> listItem = res.getBieuDoDTMonth(thang, nam);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        if (listItem != null) {
            for (ThongKeBieuDoHD item : listItem) {
                dataset.addValue(item.getTongTien(), "Tổng tiền", item.getCreatedDate().toString().substring(0, 10));
            }
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ thống kê số lượng doanh thu".toUpperCase(),
                "Thời gian", "Doanh thu",
                dataset, PlotOrientation.VERTICAL.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 578));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

    @Override
    public void getBieuDoSPMonth(int thang, int nam, JPanel jpnItem) {
        List<ThongKeBieuDoSP> listItem = res.getBieuDoSPMonth(thang, nam);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (listItem != null) {
            for (ThongKeBieuDoSP item : listItem) {
                dataset.addValue(Integer.parseInt(Long.toString(item.getSoLuong())), "Số lượng", item.getCreatedDate().toString().substring(0, 10));
            }
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ thống kê số lượng sản phẩm đã bán ra".toUpperCase(),
                "Thời gian", "Số lượng",
                dataset, PlotOrientation.VERTICAL.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 578));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.setBackground(Color.MAGENTA);
        jpnItem.repaint();
    }

    @Override
    public List<ThongKeDoanhThuRespone> getAllDoanhThuKhoangNgay(Date n, Date kt) {
        return res.getAllDoanhThuKhoangNgay(n, kt);
    }

    @Override
    public String getDoanhThuKhoangNgay(Date bd, Date kt) {
        return res.getDoanhThuKhoangDay(bd, kt);
    }

}
