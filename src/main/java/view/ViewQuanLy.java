/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

/**
 *
 * @author FPT
 */
public class ViewQuanLy extends javax.swing.JFrame {

    /**
     * Creates new form ViewQuanLy
     */
    public ViewQuanLy() {
        initComponents();
        ImageIcon originalIcon = new ImageIcon("src/main/img/logo.png");
        int width = 200;
        int height = 164;
        Image scaled = scaleImage(originalIcon.getImage(), width, height);
        ImageIcon scaledIcon = new ImageIcon(scaled);
        lblLogo.setIcon(scaledIcon);
        
        ImageIcon thoat = new ImageIcon("src/main/img/thoat.png");
        int widthThoat = 55;
        int heightThoat = 55;
        Image imgThoat = scaleImage(thoat.getImage(), widthThoat, heightThoat);
        ImageIcon scaledIconThoat = new ImageIcon(imgThoat);
        btnThoat.setIcon(scaledIconThoat);
        
        ImageIcon banHang = new ImageIcon("src/main/img/ban.png");
        int withBan = 45;
        int heightBan = 45;
        Image imgBanHang = scaleImage(banHang.getImage(), withBan, heightBan);
        ImageIcon scaledIconBanHang = new ImageIcon(imgBanHang);
        btnBanHang.setIcon(scaledIconBanHang);
        
        ImageIcon mayTinh = new ImageIcon("src/main/img/maytinh.png");
        int withMayTinh = 40;
        int heightMayTinh = 40;
        Image imgMayTinh = scaleImage(mayTinh.getImage(), withMayTinh, heightMayTinh);
        ImageIcon scaledIconMayTinh = new ImageIcon(imgMayTinh);
        btnSanPham.setIcon(scaledIconMayTinh);
        
        ImageIcon nhanVien = new ImageIcon("src/main/img/nhanvien.png");
        int withNhanVien = 45;
        int heightNhanVien = 45;
        Image imgNhanVien = scaleImage(nhanVien.getImage(), withNhanVien, heightNhanVien);
        ImageIcon scaledIconNhanVien = new ImageIcon(imgNhanVien);
        btnNhanVien.setIcon(scaledIconNhanVien);
        
        ImageIcon hoaDon = new ImageIcon("src/main/img/hoadon.png");
        int withHoaDon = 40;
        int heightHoaDon = 40;
        Image imgHoaDon = scaleImage(hoaDon.getImage(), withHoaDon, heightHoaDon);
        ImageIcon scaledIconHoaDon = new ImageIcon(imgHoaDon);
        btnHoaDon.setIcon(scaledIconHoaDon);
        
        ImageIcon sale = new ImageIcon("src/main/img/sale.png");
        int withSale = 45;
        int heightSale = 45;
        Image imgSale = scaleImage(sale.getImage(), withSale, heightSale);
        ImageIcon scaledIconSale = new ImageIcon(imgSale);
        btnkhuyenMai.setIcon(scaledIconSale);
        
        ImageIcon khachHang = new ImageIcon("src/main/img/khachhang.png");
        int withKhachHang = 45;
        int heightKhachHang = 45;
        Image imgKhachHang = scaleImage(khachHang.getImage(), withKhachHang, heightKhachHang);
        ImageIcon scaledIconKhachHang = new ImageIcon(imgKhachHang);
        btnKhachHang.setIcon(scaledIconKhachHang);
        
        ImageIcon thongKe = new ImageIcon("src/main/img/thongke.png");
        int withThongKe = 50;
        int heightThongKe = 50;
        Image imgThongKe = scaleImage(thongKe.getImage(), withThongKe, heightThongKe);
        ImageIcon scaledIconThongKe = new ImageIcon(imgThongKe);
        btnThongKe.setIcon(scaledIconThongKe);
        
        btnSanPham.setBackground(new Color(0, 0, 0, 0));
        btnBanHang.setBackground(new Color(0, 0, 0, 0));
        btnNhanVien.setBackground(new Color(0, 0, 0, 0));
        btnHoaDon.setBackground(new Color(0, 0, 0, 0));
        btnkhuyenMai.setBackground(new Color(0, 0, 0, 0));
        btnKhachHang.setBackground(new Color(0, 0, 0, 0));
        btnThongKe.setBackground(new Color(0, 0, 0, 0));
        btnThoat.setBackground(new Color(0, 0, 0, 0));
    }

    private Image scaleImage(Image image, int w, int h) {
        Image scaled = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return scaled;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        view2 = new view.ViewMenu();
        lblLogo = new javax.swing.JLabel();
        btnSanPham = new javax.swing.JButton();
        btnBanHang = new javax.swing.JButton();
        btnNhanVien = new javax.swing.JButton();
        btnHoaDon = new javax.swing.JButton();
        btnkhuyenMai = new javax.swing.JButton();
        btnKhachHang = new javax.swing.JButton();
        btnThongKe = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1500, 850));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblLogo.setIcon(new ImageIcon("src/main/img/logo.png"));

        btnSanPham.setBackground(new java.awt.Color(0, 0, 0));
        btnSanPham.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnSanPham.setIcon(new ImageIcon("src/main/img/maytinh.png"));
        btnSanPham.setText("Sản phẩm");
        btnSanPham.setAlignmentY(0.0F);
        btnSanPham.setBorder(null);
        btnSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSanPhamMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSanPhamMouseExited(evt);
            }
        });

        btnBanHang.setBackground(new java.awt.Color(0, 0, 0));
        btnBanHang.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnBanHang.setForeground(new java.awt.Color(255, 255, 255));
        btnBanHang.setIcon(new ImageIcon("src/main/img/ban.png"));
        btnBanHang.setText("Bán hàng");
        btnBanHang.setAlignmentY(0.0F);
        btnBanHang.setBorder(null);
        btnBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBanHangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBanHangMouseExited(evt);
            }
        });

        btnNhanVien.setBackground(new java.awt.Color(0, 0, 0));
        btnNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        btnNhanVien.setIcon(new ImageIcon("src/main/img/nhanvien.png"));
        btnNhanVien.setText("Nhân viên");
        btnNhanVien.setAlignmentY(0.0F);
        btnNhanVien.setBorder(null);
        btnNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNhanVienMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNhanVienMouseExited(evt);
            }
        });

        btnHoaDon.setBackground(new java.awt.Color(0, 0, 0));
        btnHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnHoaDon.setIcon(new ImageIcon("src/main/img/hoadon.png"));
        btnHoaDon.setText("Hóa đơn");
        btnHoaDon.setAlignmentY(0.0F);
        btnHoaDon.setBorder(null);
        btnHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHoaDonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHoaDonMouseExited(evt);
            }
        });

        btnkhuyenMai.setBackground(new java.awt.Color(0, 0, 0));
        btnkhuyenMai.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnkhuyenMai.setForeground(new java.awt.Color(255, 255, 255));
        btnkhuyenMai.setIcon(new ImageIcon("src/main/img/sale.png"));
        btnkhuyenMai.setText("Khuyến mãi");
        btnkhuyenMai.setAlignmentY(0.0F);
        btnkhuyenMai.setBorder(null);
        btnkhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnkhuyenMaiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnkhuyenMaiMouseExited(evt);
            }
        });

        btnKhachHang.setBackground(new java.awt.Color(0, 0, 0));
        btnKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        btnKhachHang.setIcon(new ImageIcon("src/main/img/khachhang.png"));
        btnKhachHang.setText("Khách hàng");
        btnKhachHang.setAlignmentY(0.0F);
        btnKhachHang.setBorder(null);
        btnKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnKhachHangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnKhachHangMouseExited(evt);
            }
        });

        btnThongKe.setBackground(new java.awt.Color(0, 0, 0));
        btnThongKe.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnThongKe.setForeground(new java.awt.Color(255, 255, 255));
        btnThongKe.setIcon(new ImageIcon("src/main/img/thongke.png"));
        btnThongKe.setText("Thống kê");
        btnThongKe.setAlignmentY(0.0F);
        btnThongKe.setBorder(null);
        btnThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThongKeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThongKeMouseExited(evt);
            }
        });

        btnThoat.setBackground(new java.awt.Color(0, 0, 0));
        btnThoat.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnThoat.setForeground(new java.awt.Color(255, 255, 255));
        btnThoat.setIcon(new ImageIcon("src/main/img/thoat.png"));
        btnThoat.setText("Thoát");
        btnThoat.setAlignmentY(0.0F);
        btnThoat.setBorder(null);
        btnThoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThoatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThoatMouseExited(evt);
            }
        });

        javax.swing.GroupLayout view2Layout = new javax.swing.GroupLayout(view2);
        view2.setLayout(view2Layout);
        view2Layout.setHorizontalGroup(
            view2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(view2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
            .addComponent(btnSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnkhuyenMai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnThoat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        view2Layout.setVerticalGroup(
            view2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(view2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnkhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(149, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(view2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(view2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1254, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSanPhamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSanPhamMouseEntered
        // TODO add your handling code here:
        btnSanPham.setBackground(new Color(53, 150, 195));
    }//GEN-LAST:event_btnSanPhamMouseEntered

    private void btnBanHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBanHangMouseEntered
        // TODO add your handling code here:
        btnBanHang.setBackground(new Color(50, 159, 200));
    }//GEN-LAST:event_btnBanHangMouseEntered

    private void btnNhanVienMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhanVienMouseEntered
        // TODO add your handling code here:
        btnNhanVien.setBackground(new Color(56, 140, 190));
    }//GEN-LAST:event_btnNhanVienMouseEntered

    private void btnHoaDonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonMouseEntered
        // TODO add your handling code here:
        btnHoaDon.setBackground(new Color(60, 131, 186));
    }//GEN-LAST:event_btnHoaDonMouseEntered

    private void btnHoaDonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonMouseExited
        // TODO add your handling code here:
        btnHoaDon.setBackground(new Color(0, 0, 0, 0));
    }//GEN-LAST:event_btnHoaDonMouseExited

    private void btnNhanVienMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhanVienMouseExited
        // TODO add your handling code here:
        btnNhanVien.setBackground(new Color(0, 0, 0, 0));
    }//GEN-LAST:event_btnNhanVienMouseExited

    private void btnSanPhamMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSanPhamMouseExited
        // TODO add your handling code here:
        btnSanPham.setBackground(new Color(0, 0, 0, 0));
    }//GEN-LAST:event_btnSanPhamMouseExited

    private void btnBanHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBanHangMouseExited
        // TODO add your handling code here:
        btnBanHang.setBackground(new Color(0, 0, 0, 0));
    }//GEN-LAST:event_btnBanHangMouseExited

    private void btnkhuyenMaiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnkhuyenMaiMouseEntered
        // TODO add your handling code here:
        btnkhuyenMai.setBackground(new Color(63, 122, 181));
    }//GEN-LAST:event_btnkhuyenMaiMouseEntered

    private void btnkhuyenMaiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnkhuyenMaiMouseExited
        // TODO add your handling code here:
        btnkhuyenMai.setBackground(new Color(0, 0, 0, 0));
    }//GEN-LAST:event_btnkhuyenMaiMouseExited

    private void btnKhachHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhachHangMouseEntered
        // TODO add your handling code here:    
        btnKhachHang.setBackground(new Color(66, 113, 177));
    }//GEN-LAST:event_btnKhachHangMouseEntered

    private void btnKhachHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhachHangMouseExited
        // TODO add your handling code here:
        btnKhachHang.setBackground(new Color(0, 0, 0, 0));
    }//GEN-LAST:event_btnKhachHangMouseExited

    private void btnThongKeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThongKeMouseEntered
        // TODO add your handling code here:
        btnThongKe.setBackground(new Color(69, 104, 172));
    }//GEN-LAST:event_btnThongKeMouseEntered

    private void btnThongKeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThongKeMouseExited
        // TODO add your handling code here:
        btnThongKe.setBackground(new Color(0, 0, 0, 0));
    }//GEN-LAST:event_btnThongKeMouseExited

    private void btnThoatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatMouseEntered
        // TODO add your handling code here:
        btnThoat.setBackground(new Color(73, 95, 168));
    }//GEN-LAST:event_btnThoatMouseEntered

    private void btnThoatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatMouseExited
        // TODO add your handling code here:
        btnThoat.setBackground(new Color(0, 0, 0, 0));
    }//GEN-LAST:event_btnThoatMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewQuanLy().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBanHang;
    private javax.swing.JButton btnHoaDon;
    private javax.swing.JButton btnKhachHang;
    private javax.swing.JButton btnNhanVien;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JButton btnkhuyenMai;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblLogo;
    private view.ViewMenu view2;
    // End of variables declaration//GEN-END:variables
}
