/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import domainmodel.NhanVien;
import domainmodel.Common;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author FPT
 */
public class ViewNhanVien extends javax.swing.JFrame {

    //Biến để làm hover í mà anh không cần quan tâm
    private int b1 = 0;
    private int b4 = 0;
    private int b6 = 0;
    private int b8 = 0;

    /**
     * Creates new form ViewQuanLy
     */
    public ViewNhanVien() {
        initComponents();
        designImg();
        designMenu();
        btnBanHang.setBackground(new Color(50, 159, 200));
        panelBanHang.removeAll();
        PanelBanHang pbh = new PanelBanHang();
        panelBanHang.add(pbh);
        panelBanHang.setLayout(new FlowLayout());
        this.pack();
        pbh.setVisible(true);
    }

    private Image scaleImage(Image image, int w, int h) {
        Image scaled = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return scaled;
    }

    private void designImg() {
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

        ImageIcon hoaDon = new ImageIcon("src/main/img/hoadon.png");
        int withHoaDon = 40;
        int heightHoaDon = 40;
        Image imgHoaDon = scaleImage(hoaDon.getImage(), withHoaDon, heightHoaDon);
        ImageIcon scaledIconHoaDon = new ImageIcon(imgHoaDon);
        btnHoaDon.setIcon(scaledIconHoaDon);

        ImageIcon khachHang = new ImageIcon("src/main/img/khachhang.png");
        int withKhachHang = 45;
        int heightKhachHang = 45;
        Image imgKhachHang = scaleImage(khachHang.getImage(), withKhachHang, heightKhachHang);
        ImageIcon scaledIconKhachHang = new ImageIcon(imgKhachHang);
        btnKhachHang.setIcon(scaledIconKhachHang);

    }

    private void designMenu() {
        btnBanHang.setBackground(new Color(0, 0, 0, 0));
        btnHoaDon.setBackground(new Color(0, 0, 0, 0));
        btnKhachHang.setBackground(new Color(0, 0, 0, 0));
        btnThoat.setBackground(new Color(0, 0, 0, 0));
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
        btnBanHang = new javax.swing.JButton();
        btnHoaDon = new javax.swing.JButton();
        btnKhachHang = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        btnChiTietNV = new javax.swing.JButton();
        btnDoiMK = new javax.swing.JButton();
        panelBanHang = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1535, 850));
        setMinimumSize(new java.awt.Dimension(1535, 850));
        setPreferredSize(new java.awt.Dimension(1535, 850));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblLogo.setIcon(new ImageIcon("src/main/img/logo.png"));

        btnBanHang.setBackground(new java.awt.Color(0, 0, 0));
        btnBanHang.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnBanHang.setForeground(new java.awt.Color(255, 255, 255));
        btnBanHang.setIcon(new ImageIcon("src/main/img/ban.png"));
        btnBanHang.setText("Bán hàng");
        btnBanHang.setAlignmentY(0.0F);
        btnBanHang.setBorder(null);
        btnBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBanHangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBanHangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBanHangMouseExited(evt);
            }
        });
        btnBanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanHangActionPerformed(evt);
            }
        });

        btnHoaDon.setBackground(new java.awt.Color(0, 0, 0));
        btnHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnHoaDon.setIcon(new ImageIcon("src/main/img/hoadon.png"));
        btnHoaDon.setText("Hóa đơn");
        btnHoaDon.setToolTipText("");
        btnHoaDon.setActionCommand("   Hóa đơn");
        btnHoaDon.setAlignmentY(0.0F);
        btnHoaDon.setBorder(null);
        btnHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoaDonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHoaDonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHoaDonMouseExited(evt);
            }
        });
        btnHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonActionPerformed(evt);
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKhachHangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnKhachHangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnKhachHangMouseExited(evt);
            }
        });
        btnKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangActionPerformed(evt);
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThoatMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThoatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThoatMouseExited(evt);
            }
        });
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        btnChiTietNV.setBackground(new java.awt.Color(77, 83, 161));
        btnChiTietNV.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnChiTietNV.setForeground(new java.awt.Color(255, 255, 255));
        btnChiTietNV.setText("Chi tiết nhân viên");
        btnChiTietNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietNVActionPerformed(evt);
            }
        });

        btnDoiMK.setBackground(new java.awt.Color(77, 83, 161));
        btnDoiMK.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnDoiMK.setForeground(new java.awt.Color(255, 255, 255));
        btnDoiMK.setText("Đổi mật khẩu");
        btnDoiMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout view2Layout = new javax.swing.GroupLayout(view2);
        view2.setLayout(view2Layout);
        view2Layout.setHorizontalGroup(
            view2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(view2Layout.createSequentialGroup()
                .addGroup(view2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(view2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(view2Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(view2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnChiTietNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDoiMK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(20, Short.MAX_VALUE))
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
                .addComponent(btnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 235, Short.MAX_VALUE)
                .addComponent(btnChiTietNV, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDoiMK, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
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

        panelBanHang.setBackground(new java.awt.Color(255, 255, 255));
        panelBanHang.setMaximumSize(new java.awt.Dimension(1300, 850));
        panelBanHang.setMinimumSize(new java.awt.Dimension(1300, 850));
        panelBanHang.setName(""); // NOI18N
        panelBanHang.setPreferredSize(new java.awt.Dimension(1300, 850));

        javax.swing.GroupLayout panelBanHangLayout = new javax.swing.GroupLayout(panelBanHang);
        panelBanHang.setLayout(panelBanHangLayout);
        panelBanHangLayout.setHorizontalGroup(
            panelBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelBanHangLayout.setVerticalGroup(
            panelBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 1289, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanHangActionPerformed
        // TODO add your handling code here:
        panelBanHang.removeAll();
        PanelBanHang pbh = new PanelBanHang();
        panelBanHang.add(pbh);
        panelBanHang.setLayout(new FlowLayout());
        this.pack();
        pbh.setVisible(true);
    }//GEN-LAST:event_btnBanHangActionPerformed

    private void btnHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHoaDonActionPerformed

    private void btnKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangActionPerformed
        // TODO add your handling code here:
        panelBanHang.removeAll();
        PanelKhachHang kh = new PanelKhachHang();
        panelBanHang.add(kh);
        panelBanHang.setLayout(new FlowLayout());
        this.pack();
        kh.setVisible(true);
        this.setSize(1546, 850);
    }//GEN-LAST:event_btnKhachHangActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnBanHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBanHangMouseExited
        if (b1 == 0) {
            btnBanHang.setBackground(new Color(0, 0, 0, 0));
        }
    }//GEN-LAST:event_btnBanHangMouseExited

    private void btnBanHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBanHangMouseClicked
        b1 = 1;
        b4 = 0;
        b6 = 0;
        b8 = 0;
        btnHoaDon.setBackground(new Color(0, 0, 0, 0));
        btnKhachHang.setBackground(new Color(0, 0, 0, 0));
        btnThoat.setBackground(new Color(0, 0, 0, 0));
    }//GEN-LAST:event_btnBanHangMouseClicked

    private void btnHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonMouseClicked
        b1 = 0;
        b4 = 1;
        b8 = 0;
        btnBanHang.setBackground(new Color(0, 0, 0, 0));
        btnKhachHang.setBackground(new Color(0, 0, 0, 0));
        btnThoat.setBackground(new Color(0, 0, 0, 0));
    }//GEN-LAST:event_btnHoaDonMouseClicked

    private void btnKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhachHangMouseClicked
        b1 = 0;
        b4 = 0;
        b6 = 1;
        b8 = 0;
        btnBanHang.setBackground(new Color(0, 0, 0, 0));
        btnHoaDon.setBackground(new Color(0, 0, 0, 0));
        btnThoat.setBackground(new Color(0, 0, 0, 0));
    }//GEN-LAST:event_btnKhachHangMouseClicked

    private void btnThoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatMouseClicked
        b1 = 0;
        b4 = 0;
        b6 = 0;
        b8 = 1;
        btnBanHang.setBackground(new Color(0, 0, 0, 0));
        btnHoaDon.setBackground(new Color(0, 0, 0, 0));
        btnKhachHang.setBackground(new Color(0, 0, 0, 0));
    }//GEN-LAST:event_btnThoatMouseClicked

    private void btnBanHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBanHangMouseEntered
        btnBanHang.setBackground(new Color(50, 159, 200));
    }//GEN-LAST:event_btnBanHangMouseEntered

    private void btnHoaDonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonMouseEntered
        btnHoaDon.setBackground(new Color(50, 159, 200));
    }//GEN-LAST:event_btnHoaDonMouseEntered

    private void btnKhachHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhachHangMouseEntered
        btnKhachHang.setBackground(new Color(50, 159, 200));
    }//GEN-LAST:event_btnKhachHangMouseEntered

    private void btnThoatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatMouseEntered
        btnThoat.setBackground(new Color(50, 159, 200));
    }//GEN-LAST:event_btnThoatMouseEntered

    private void btnHoaDonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonMouseExited
        if (b4 == 0) {
            btnHoaDon.setBackground(new Color(0, 0, 0, 0));
        }
    }//GEN-LAST:event_btnHoaDonMouseExited

    private void btnKhachHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhachHangMouseExited
        if (b6 == 0) {
            btnKhachHang.setBackground(new Color(0, 0, 0, 0));
        }
    }//GEN-LAST:event_btnKhachHangMouseExited

    private void btnThoatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatMouseExited
        if (b8 == 0) {
            btnThoat.setBackground(new Color(0, 0, 0, 0));
        }
    }//GEN-LAST:event_btnThoatMouseExited

    private void btnChiTietNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietNVActionPerformed
        // TODO add your handling code here:
//        NhanVien nv = Common.tenNV;
//        DetailChiTietNV detailChiTietNV = new DetailChiTietNV(this, true, nv);
//        detailChiTietNV.setVisible(true);
    }//GEN-LAST:event_btnChiTietNVActionPerformed

    private void btnDoiMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMKActionPerformed
        // TODO add your handling code here:
        ViewDoiMatKhau2 dmk = new ViewDoiMatKhau2();
        dmk.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnDoiMKActionPerformed

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
            java.util.logging.Logger.getLogger(ViewNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBanHang;
    private javax.swing.JButton btnChiTietNV;
    private javax.swing.JButton btnDoiMK;
    private javax.swing.JButton btnHoaDon;
    private javax.swing.JButton btnKhachHang;
    private javax.swing.JButton btnThoat;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JPanel panelBanHang;
    private view.ViewMenu view2;
    // End of variables declaration//GEN-END:variables
}
