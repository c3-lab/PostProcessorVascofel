/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package postprocessorvascofel;

import java.io.IOException;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author asafe
 */
public class PostProcessorFrame extends javax.swing.JFrame {
    
    FileProcessor fileProcessor;
    SwingWorker task;

    final String[] invalidCharacters = {".", ",", "\\", "/", "|", "<", ">", "*", ":", ";","\"", "´", "`","?", "!", "@", "#", "$", "%", "^"};

    /**
     * Creates new form PostProcessorFrame
     */
    public PostProcessorFrame() {
        initComponents();
        
        buttonSaveFile.setEnabled(false);
        
        fileProcessor = new FileProcessor();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonLoadFile = new javax.swing.JButton();
        buttonSaveFile = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();
        textFieldFilePath = new javax.swing.JTextField();
        textFieldNewFileName = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        buttonLoadFile.setText("Caregar Arquivo");
        buttonLoadFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLoadFileActionPerformed(evt);
            }
        });

        buttonSaveFile.setText("Salvar arquivo");
        buttonSaveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveFileActionPerformed(evt);
            }
        });

        progressBar.setStringPainted(true);

        textFieldFilePath.setEditable(false);

        textFieldNewFileName.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonLoadFile)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFieldFilePath))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 208, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textFieldNewFileName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonSaveFile)
                        .addGap(21, 21, 21))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonLoadFile)
                    .addComponent(textFieldFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldNewFileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSaveFile))
                .addGap(19, 19, 19)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(134, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonLoadFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLoadFileActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION){
            fileProcessor.loadFile(fileChooser.getSelectedFile());
            textFieldFilePath.setText(fileChooser.getSelectedFile().getPath());
            
            buttonSaveFile.setEnabled(true);
        }
    }//GEN-LAST:event_buttonLoadFileActionPerformed

    private void buttonSaveFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveFileActionPerformed
        
        if (textFieldNewFileName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Adicione um nome para o novo arquivo!",
                "",
                JOptionPane.WARNING_MESSAGE);
            
            return;
        }else{
            for (String invalidCharacter : invalidCharacters) {
                boolean containsInvalidChar = textFieldNewFileName.getText().contains(invalidCharacter);
                if (containsInvalidChar) {
                    JOptionPane.showMessageDialog(this,
                            "Os nomes de arquivo não podem conter quaisqer\n"
                                    + "dos seguintes caracteres: "
                                    + String.join("", invalidCharacters),
                            "Nome de arquivo inválido",
                            JOptionPane.WARNING_MESSAGE);
                    
                    return;
                }
            }
        }
        
        try {
            fileProcessor.processFile(textFieldNewFileName.getText());
            
            task = new SwingWorker(){
                @Override
                protected Object doInBackground() throws Exception {
                    for (int i = 0; i <= 100; i++) {
                        try {
                            progressBar.setValue(i);
                            Thread.sleep(20);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    buttonSaveFile.setEnabled(true);
                    buttonLoadFile.setEnabled(true);
                    return null;
                }
            };
            
            buttonSaveFile.setEnabled(false);
            buttonLoadFile.setEnabled(false);
            task.execute();
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Arquivo Inválido!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonSaveFileActionPerformed

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
            java.util.logging.Logger.getLogger(PostProcessorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PostProcessorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PostProcessorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PostProcessorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new PostProcessorFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonLoadFile;
    private javax.swing.JButton buttonSaveFile;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JTextField textFieldFilePath;
    private javax.swing.JTextField textFieldNewFileName;
    // End of variables declaration//GEN-END:variables
}
