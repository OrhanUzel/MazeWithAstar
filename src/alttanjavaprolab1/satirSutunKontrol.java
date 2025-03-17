/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package alttanjavaprolab1;
import java.awt.List;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author Orhan UZEL
 */
public class satirSutunKontrol extends javax.swing.JFrame {
 //String liste[]=new String[1000];
 ArrayList<String>liste=new ArrayList<>();
    DefaultListModel model=new DefaultListModel();
     DefaultListModel modelHazineler=new DefaultListModel();
public static int satir;
public static int sutun;
public String satirString;
public String sutunString;
//RandomPathFinding haritayaGit=new RandomPathFinding();
    /**
     * Creates new form satirSutunKontrol
     */
    public satirSutunKontrol() {
        initComponents();
      //  this.setSize(700, 200);
      this.setSize(950, 520); 
      this.setLocation(180, 180);
       this.setResizable(false);
       jListBulunanNesneler.setModel(model);//videodan öğrendim 
       jListSandiklar.setModel(modelHazineler);
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
        buttonHaritaOlustur = new javax.swing.JButton();
        alSutunSayisi = new java.awt.TextField();
        alSatirSayisi = new java.awt.TextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        createFog = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListBulunanNesneler = new javax.swing.JList<>();
        btnNesneleriGoster = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListSandiklar = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 102));
        setBounds(new java.awt.Rectangle(0, 0, 916, 495));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setSize(new java.awt.Dimension(9, 1080));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setToolTipText("");
        jPanel1.setMinimumSize(new java.awt.Dimension(1080, 1920));
        jPanel1.setName(""); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(1920, 1080));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonHaritaOlustur.setBackground(new java.awt.Color(255, 204, 204));
        buttonHaritaOlustur.setText("Haritayı Oluştur");
        buttonHaritaOlustur.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonHaritaOlustur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHaritaOlusturActionPerformed(evt);
            }
        });
        jPanel1.add(buttonHaritaOlustur, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 130, 30));

        alSutunSayisi.setBackground(new java.awt.Color(255, 204, 204));
        alSutunSayisi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                alSutunSayisiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                alSutunSayisiMouseExited(evt);
            }
        });
        alSutunSayisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alSutunSayisiActionPerformed(evt);
            }
        });
        jPanel1.add(alSutunSayisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 130, -1));

        alSatirSayisi.setBackground(new java.awt.Color(255, 204, 204));
        alSatirSayisi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                alSatirSayisiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                alSatirSayisiMouseExited(evt);
            }
        });
        alSatirSayisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alSatirSayisiActionPerformed(evt);
            }
        });
        jPanel1.add(alSatirSayisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 130, -1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Satır Sayısı :");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 160, -1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Sütun Sayısı :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 180, -1));

        createFog.setBackground(new java.awt.Color(255, 204, 204));
        createFog.setText("Sis Oluştur");
        createFog.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        createFog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createFogActionPerformed(evt);
            }
        });
        jPanel1.add(createFog, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 130, 30));

        jListBulunanNesneler.setBackground(new java.awt.Color(255, 204, 204));
        jListBulunanNesneler.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jListBulunanNesneler.setToolTipText("");
        jListBulunanNesneler.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(jListBulunanNesneler);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 0, 230, 490));

        btnNesneleriGoster.setBackground(new java.awt.Color(255, 204, 204));
        btnNesneleriGoster.setText("Nesneleri Göster");
        btnNesneleriGoster.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNesneleriGoster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNesneleriGosterActionPerformed(evt);
            }
        });
        jPanel1.add(btnNesneleriGoster, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 330, -1, -1));

        jListSandiklar.setBackground(new java.awt.Color(255, 204, 204));
        jListSandiklar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane2.setViewportView(jListSandiklar);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 240, 490));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void alSatirSayisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alSatirSayisiActionPerformed
  
    }//GEN-LAST:event_alSatirSayisiActionPerformed

    private void alSatirSayisiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_alSatirSayisiMouseEntered
      
    }//GEN-LAST:event_alSatirSayisiMouseEntered

    private void alSutunSayisiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_alSutunSayisiMouseEntered
           
    }//GEN-LAST:event_alSutunSayisiMouseEntered

    private void alSatirSayisiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_alSatirSayisiMouseExited
        
    }//GEN-LAST:event_alSatirSayisiMouseExited

    private void alSutunSayisiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_alSutunSayisiMouseExited
      
    }//GEN-LAST:event_alSutunSayisiMouseExited

    private void buttonHaritaOlusturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHaritaOlusturActionPerformed
        if(alSatirSayisi.getText()!=null && alSutunSayisi.getText()!=null){
            satirString=alSatirSayisi.getText();
            sutunString=alSutunSayisi.getText();
            satir=Integer.parseInt(satirString);
            sutun=Integer.parseInt(sutunString);
            System.out.println(satir);
            System.out.println(sutun);
//            haritayaGit.setColumnCount(sutun);
//            haritayaGit.setRowCount(satir);
//            haritayaGit.setBelirtec(true);
//                  RandomPathFinding.columnCount=sutun;
//                  RandomPathFinding.rowCount=satir;
//                  RandomPathFinding.belirtec=true;
//                  RandomPathFinding.cellSize=32;
                  
                  ProLab.columnCount=sutun;
                  ProLab.rowCount=satir;
                  ProLab.belirtec=true;
                  ProLab.cellSize=32;
ProLab proLab=new ProLab();
//RandomPathFinding haritayaGit=new RandomPathFinding();
this.setVisible(true);

            
                   
        }
    }//GEN-LAST:event_buttonHaritaOlusturActionPerformed

    private void alSutunSayisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alSutunSayisiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alSutunSayisiActionPerformed

    private void createFogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createFogActionPerformed
     ProLab.sisOlustur=true;
     ProLab.createFog(satir, sutun);
//     RandomPathFinding.sisOlustur=true;
//     RandomPathFinding.createFog(satir,sutun);
    }//GEN-LAST:event_createFogActionPerformed

    private void btnNesneleriGosterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNesneleriGosterActionPerformed
//        String hazineAltin[]=new String[20];
//        String hazineGumus[]=new String[20];
//        String hazineBakir[]=new String[20];
//        String hazineZumrut[]=new String[20];
        ArrayList<String> hazineAltin = new ArrayList();
        ArrayList<String> hazineGumus = new ArrayList();
        ArrayList<String> hazineBakir = new ArrayList();
        ArrayList<String> hazineZumrut = new ArrayList();
        for (int i = 0; i <   ProLab.listeBulunanlar.size(); i++) {
           
           // jListBulunanNesneler.setSelectedValue(ProLab.listeBulunanlar, false);
            model.addElement(ProLab.listeBulunanlar.get(i));
            
        }  
        for (int i = 0; i < ProLab.listeBulunanHazineler.size(); i++) {
           // modelHazineler.addElement(ProLab.listeBulunanHazineler.get(i));
               //hazine[i] =ProLab.listeBulunanHazineler.get(i);
              if (ProLab.listeBulunanHazineler.get(i).contains("Altın")==true) {
                hazineAltin.add(ProLab.listeBulunanHazineler.get(i));
            }
              if (ProLab.listeBulunanHazineler.get(i).contains("Gümüş")==true) {
                hazineGumus.add(ProLab.listeBulunanHazineler.get(i));
            }
              if (ProLab.listeBulunanHazineler.get(i).contains("Zümrüt")==true) {
                hazineZumrut.add(ProLab.listeBulunanHazineler.get(i));
            }
              if (ProLab.listeBulunanHazineler.get(i).contains("Bakır")==true) {
                hazineBakir.add(ProLab.listeBulunanHazineler.get(i));
            }
              
              
        }
       // for (int j = 0; j < hazineAltin.size(); j++) {
              //  if(hazineAltin[j]!=null){
                    modelHazineler.addElement(hazineAltin);
             //   }
         //   }
       // for (int j = 0; j < 20; j++) {
         //       if(hazineGumus[j]!=null){
                    modelHazineler.addElement(hazineGumus);
           //     }
          //  }
       // for (int j = 0; j < 20; j++) {
         //       if(hazineZumrut[j]!=null){
                    modelHazineler.addElement(hazineZumrut);
        //        }
          //  }
        //for (int j = 0; j < 20; j++) {
        //        if(hazineBakir[j]!=null){
                    modelHazineler.addElement(hazineBakir);
       //         }
       //     }
        
      
    }//GEN-LAST:event_btnNesneleriGosterActionPerformed

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
            java.util.logging.Logger.getLogger(satirSutunKontrol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(satirSutunKontrol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(satirSutunKontrol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(satirSutunKontrol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new satirSutunKontrol().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.TextField alSatirSayisi;
    private java.awt.TextField alSutunSayisi;
    private javax.swing.JButton btnNesneleriGoster;
    private javax.swing.JButton buttonHaritaOlustur;
    private javax.swing.JButton createFog;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public static javax.swing.JList<String> jListBulunanNesneler;
    private javax.swing.JList<String> jListSandiklar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
