/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controller.AdminControl;
import Model.Pegawai;
import Session.LoginSession;
import com.placeholder.PlaceHolder;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class LoginForm extends javax.swing.JFrame {

    /**
     * Creates new form LoginForm
     */
     AdminControl AC ;
      Pegawai PGW;
    Pegawai P ;
    public LoginForm() {
        initComponents();
            setResizable(false);
            AC = new AdminControl();
            setTitle("LOGIN");

             PGW= AC.getPegawai() ;
              P= AC.getPegawai();
         int uid = PGW.getId() ;
          String userName= PGW.getUserName();
          
          
        PlaceHolder holder0 = new PlaceHolder(txtUserName,"Masukan User Name");
        
        PlaceHolder holder1 = new PlaceHolder(txtPassword, "Masukkan Password");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelMasuk = new javax.swing.JPanel();
        txtTitle = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        txtPassword = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        txtUserName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));

        PanelMasuk.setBackground(new java.awt.Color(51, 51, 51));
        PanelMasuk.setPreferredSize(new java.awt.Dimension(700, 500));

        txtTitle.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        txtTitle.setForeground(new java.awt.Color(255, 255, 255));
        txtTitle.setText("       Laman Masuk ");

        txtPassword.setBackground(new java.awt.Color(51, 51, 51));
        txtPassword.setBorder(null);

        jButton1.setText("MASUK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtUserName.setBackground(new java.awt.Color(51, 51, 51));
        txtUserName.setBorder(null);
        txtUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelMasukLayout = new javax.swing.GroupLayout(PanelMasuk);
        PanelMasuk.setLayout(PanelMasukLayout);
        PanelMasukLayout.setHorizontalGroup(
            PanelMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMasukLayout.createSequentialGroup()
                .addGroup(PanelMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelMasukLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(PanelMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator2)
                            .addComponent(txtPassword)
                            .addComponent(txtTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                            .addComponent(txtUserName)))
                    .addGroup(PanelMasukLayout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        PanelMasukLayout.setVerticalGroup(
            PanelMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMasukLayout.createSequentialGroup()
                .addContainerGap(171, Short.MAX_VALUE)
                .addComponent(txtTitle)
                .addGap(28, 28, 28)
                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButton1)
                .addGap(142, 142, 142))
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cute.jpeg"))); // NOI18N
        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(455, 455, 455)
                .addComponent(PanelMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(PanelMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        P  = AC.searchPegawai(txtUserName.getText());
        if (txtUserName.getText().equalsIgnoreCase("ADMIN")|| txtPassword.getText().equalsIgnoreCase("ADMIN")) {
               JOptionPane.showMessageDialog(this, "SELAMAT DATANG");
                   MenuAdmin MA = new MenuAdmin();
                    this.setVisible(false);
                    MA.setVisible(true);
        }
        if(P!=null)

        {

            String originalPassword ;
            originalPassword = P.getPassword();

            System.out.println(originalPassword);
            String generatedSecuredPasswordHash;
            BCrypt bcrp=new BCrypt();
            boolean matched;
            matched= bcrp.checkpw(txtPassword.getText(), originalPassword);

            String password =Boolean.toString(matched) ;

                if(txtUserName.getText().equalsIgnoreCase(P.getUserName()) && matched==true)
            {
                System.out.println(P.getRole());
                String role = P.getRole();
                int jumlah = P.getRole().length();
                System.out.println(jumlah);
                if(P.getRole().equalsIgnoreCase("Kasir"))
                {
                    JOptionPane.showMessageDialog(this, "WELCOME ");
                    LoginSession.setIdUser(P.getId());
                    LoginSession.setNama(P.getUserName());
                    MenuKasirTransaksiLayanan n = new MenuKasirTransaksiLayanan();
                    this.setVisible(false);
                    n.setVisible(true);
                }
                else if(P.getRole().equalsIgnoreCase("Customer Service"))
                {
                    JOptionPane.showMessageDialog(this, "HAI : "+txtUserName.getText());
                    LoginSession.setIdUser(P.getId());
                    LoginSession.setNama(P.getUserName());
                    MenuCS MS = new MenuCS();
                    this.setVisible(false);
                    MS.setVisible(true);
                }

            }
            else
            {

                JOptionPane.showMessageDialog(this, "PASSWORD SALAH");
            }
        }
        else
        {
            System.out.println("Tidak adda user nAME");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserNameActionPerformed

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
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelMasuk;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JLabel txtTitle;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
