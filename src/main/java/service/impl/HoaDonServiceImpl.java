/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import custommodel.HoaDonInResponse;
import custommodel.HoaDonResponse;
import custommodel.KhachHangReponse;
import custommodel.ViewExcelReponse;
import domainmodel.HoaDon;
import domainmodel.NhanVien;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.UUID;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import repository.impl.HoaDonRepository;
import service.HoaDonService;

/**
 * @author FPT
 */
public class HoaDonServiceImpl implements HoaDonService {

    private HoaDonRepository hoaDonRepository = new HoaDonRepository();

    @Override
    public List<HoaDonResponse> getByOne(int tt) {
        return hoaDonRepository.getByOne(tt);
    }

    @Override
    public void exportExcel(JTable table) {
        JFileChooser chooser = new JFileChooser();
        int i = chooser.showSaveDialog(chooser);
        if (i == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                FileWriter out = new FileWriter(file + ".xls");
                BufferedWriter bwrite = new BufferedWriter(out);
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                // ten Cot
                for (int j = 0; j < table.getColumnCount(); j++) {
                    bwrite.write(model.getColumnName(j) + "\t");
                }
                bwrite.write("\n");
                // Lay du lieu dong
                for (int j = 0; j < table.getRowCount(); j++) {
                    for (int k = 0; k < table.getColumnCount(); k++) {
                        bwrite.write(model.getValueAt(j, k) + "\t");
                    }
                    bwrite.write("\n");
                }
                bwrite.close();
                JOptionPane.showMessageDialog(null, "Lưu file thành công!");
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "Lỗi khi lưu file!");
            }
        }
    }

    @Override
    public String update(HoaDon hd) {
        if (hoaDonRepository.update(hd)) {
            return "Thành Công";
        } else {
            return "Thất Bại";
        }
    }

    @Override
    public List<HoaDon> get_All() {
        return hoaDonRepository.get_All();
    }

    @Override
    public HoaDonResponse getByMa(String ma) {
        return hoaDonRepository.getOne(ma);
    }

    @Override
    public List<HoaDonResponse> getAll() {
        return hoaDonRepository.getAll();
    }

    @Override
    public List<HoaDonResponse> getAll(NhanVien nhanVien) {
        return hoaDonRepository.getAll(nhanVien);
    }

    @Override
    public String add(HoaDon hoaDon) {
        boolean add = hoaDonRepository.add(hoaDon);
        if (add) {
            return "Thành công";
        } else {
            return "Thất bại";
        }
    }

    @Override
    public int genMaHD() {
        return hoaDonRepository.genMaHD();
    }

    @Override
    public String updateTrangThai(HoaDon hoaDon) {
        boolean update = hoaDonRepository.updateTrangThai(hoaDon);
        if (update) {
            return "Thành công";
        } else {
            return "Thất bại";
        }
    }

    @Override
    public String updateTrangThaiHuy(HoaDon hoaDon, UUID id) {
        boolean update = hoaDonRepository.updateTrangThaiHuy(hoaDon, id);
        if (update) {
            return "Thành công";
        } else {
            return "Thất bại";
        }
    }

    @Override
    public HoaDon getByIdHoaDon(UUID id) {
        return hoaDonRepository.getByIdHoaDon(id);
    }

    @Override
    public List<HoaDonResponse> search(String searchKey) {
        return hoaDonRepository.search(searchKey);
    }

    @Override
    public List<HoaDonResponse> getAllPage(int row) {
        return hoaDonRepository.getAllPage(row);
    }

    @Override
    public List<ViewExcelReponse> getAllExcel() {
        return hoaDonRepository.getAllExcel();
    }

    @Override
    public List<ViewExcelReponse> getListExcel(int tt) {
        return hoaDonRepository.getListExcel(tt);
    }

    @Override
    public KhachHangReponse getKhachHang(String ma) {
        return hoaDonRepository.getKhachHang(ma);
    }

    @Override
    public HoaDonInResponse getHDIn(String ma) {
        return hoaDonRepository.getHDIn(ma);
    }

}
