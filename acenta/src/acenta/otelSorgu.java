/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acenta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Zahid
 */
public class otelSorgu extends javax.swing.JFrame {

    String adres = "jdbc:mysql://94.73.170.236/acenta";
    String username = "fsm";
    String password = "RRrv34U8";
    static otel[] o;

    /**
     * Creates new form otelSorgu
     */
    public otelSorgu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        odeme = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        sehir = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablo = new javax.swing.JTable();
        giristarih = new datechooser.beans.DateChooserCombo();
        cikistarih = new datechooser.beans.DateChooserCombo();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setText("<-");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 11, -1, -1));

        jLabel2.setText("Şehir");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 14, -1, -1));

        jLabel3.setText("giriş tarih");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(273, 14, -1, -1));

        jLabel4.setText("çıkış tarih");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(273, 45, -1, -1));

        odeme.setText("ödemeye devam et");
        odeme.setEnabled(false);
        odeme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                odemeActionPerformed(evt);
            }
        });
        getContentPane().add(odeme, new org.netbeans.lib.awtextra.AbsoluteConstraints(336, 551, -1, -1));

        jButton2.setText("otel sorgula");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 41, -1, -1));

        getContentPane().add(sehir, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 11, 120, -1));

        tablo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Adı", "Yıldız", "Fiyat", "Açıklama"
            }
        ));
        jScrollPane1.setViewportView(tablo);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 106, -1, -1));
        getContentPane().add(giristarih, new org.netbeans.lib.awtextra.AbsoluteConstraints(327, 11, -1, -1));
        getContentPane().add(cikistarih, new org.netbeans.lib.awtextra.AbsoluteConstraints(327, 41, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        anaMenu a = new anaMenu();
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void odemeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_odemeActionPerformed
        int seciliSatir=tablo.getSelectedRow();
        System.out.println("secilisatir: "+seciliSatir);
        System.out.println("o[secilisatir].id: "+o[seciliSatir].id);
        rezervasyonEkran rE=new rezervasyonEkran(o[seciliSatir].id,o[seciliSatir].fiyat,o[seciliSatir].giris_tarih,o[seciliSatir].cikis_tarih,o[seciliSatir].sehir);  //o[seciliSatir].id
        rE.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_odemeActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            rezervasyon r=new rezervasyon();
            o = r.uygunOtel(giristarih.getText(),
             
                    cikistarih.getText(),
                    sehir.getSelectedItem()+"");
            
            System.out.println("0[0].id= "+o[0].id);
            
            int sutunsayisi = 4;
            int satirsayisi = o.length;
            
            Object rowData[][] = new Object[satirsayisi][sutunsayisi];
            
            
            System.out.println(o[0].adi+" satirsayisi"+satirsayisi);
            
            for (int i = 0;i<satirsayisi; i++) {
                        rowData[i][0]=o[i].adi;
                        rowData[i][1]=o[i].yildiz;
                        rowData[i][2]=o[i].fiyat;
                        rowData[i][3]=o[i].aciklama;
                }
            
            String[] s={"Adı","Yıldız","Fiyat","Açıklama"};
            DefaultTableModel model = new DefaultTableModel(rowData,s);
            tablo.setModel(model);
            
            if(o.length>0){
                odeme.setEnabled(true);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(otelSorgu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
    
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            // ekran geldiğinde

            Connection con = DriverManager.getConnection(adres, username, password);

            Statement stat = con.createStatement();

           /* ResultSet res = stat.executeQuery("select * from acenta.otel");

            while (res.next()) {
                sehir.addItem(res.getString("sehir"));
            }
            res.close();
          */
            
            
            ResultSet res = stat.executeQuery("select * from acenta.otel");
            HashSet<String> set = new HashSet<>();
            while (res.next()) {
                set.add(res.getString("sehir"));
            }

            Object[] sehirler =set.toArray();

            for (int i = 0; i < sehirler.length; i++) {
                sehir.addItem(sehirler[i]);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(otelSorgu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(otelSorgu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(otelSorgu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(otelSorgu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(otelSorgu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new otelSorgu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo cikistarih;
    private datechooser.beans.DateChooserCombo giristarih;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton odeme;
    private javax.swing.JComboBox sehir;
    private javax.swing.JTable tablo;
    // End of variables declaration//GEN-END:variables
}
