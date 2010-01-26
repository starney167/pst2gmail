/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * StatusWindow.java
 *
 * Created on 06/09/2009, 5:49:04 PM
 */

package pst2Gmail;

import java.text.DecimalFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author toweruser
 */
public class StatusWindow extends javax.swing.JDialog {

    private Transfer transfer = null;

    /** Creates new form StatusWindow */
    public StatusWindow(javax.swing.JFrame parentFrame, final Transfer transfer) {
        super(parentFrame);
        this.setModalityType(ModalityType.APPLICATION_MODAL);
        initComponents();

        this.transfer = transfer;

        int max = transfer.getItemCount();
        this.progressBar.setMaximum(max);
        this.lblMessagesMigrated.setText("0 of "+max+" migrated.");

        this.transfer.addStatusListener(new TransferStatusListener() {

            public void statusUpdated(String message) {
                txtStatus.setText(txtStatus.getText()+"\n"+message);
                txtStatus.setCaretPosition(txtStatus.getText().lastIndexOf("\n")+1);
                if (transfer.hasCompleted()) {
                    btnCancel.setText("Done!");
                }
            }

            public void progressUpdated(int totalNumberOfMessages, int numberMessagesCompleted) {
                progressBar.setValue(numberMessagesCompleted);
                lblMessagesMigrated.setText(numberMessagesCompleted+" of "+totalNumberOfMessages+" migrated.");
            }

            public void ratesUpdated(double kbps, double emailsps, Date completionTime) {
                DecimalFormat format = new DecimalFormat("#.##");
                lblDuration.setText(format.format(kbps)+ "KB/s, " + format.format(emailsps*60)+ " emails/min, estimated completion: "+completionTime);
            }
        });

        Thread transferThread = new Thread(this.transfer);
        transferThread.start();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();
        lblMessagesMigrated = new javax.swing.JLabel();
        lblDuration = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtStatus = new javax.swing.JTextArea();

        setTitle("Progress");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Migration Status"));

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        lblMessagesMigrated.setText("- of - messages migrated");

        lblDuration.setText("-");

        txtStatus.setColumns(20);
        txtStatus.setEditable(false);
        txtStatus.setFont(txtStatus.getFont().deriveFont(txtStatus.getFont().getSize()-2f));
        txtStatus.setRows(5);
        jScrollPane1.setViewportView(txtStatus);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                    .addComponent(progressBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                    .addComponent(lblMessagesMigrated, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                    .addComponent(lblDuration, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                    .addComponent(btnCancel))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMessagesMigrated)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDuration)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        if (!transfer.hasCompleted()) {
            int result = JOptionPane.showConfirmDialog(this, "Are you sure you wish to cancel?", "test", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDuration;
    private javax.swing.JLabel lblMessagesMigrated;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JTextArea txtStatus;
    // End of variables declaration//GEN-END:variables

}