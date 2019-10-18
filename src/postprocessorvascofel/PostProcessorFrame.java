/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package postprocessorvascofel;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
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
        
        setIconImage(new ImageIcon(getClass().getResource("icon.png")).getImage());
        
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pós-processador Vascofel");
        setResizable(false);

        buttonLoadFile.setText("Carregar Arquivo");
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

        jLabel1.setText("Caminho do arquivo");

        jLabel2.setText("Nome do novo arquivo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonLoadFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldFilePath, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                            .addComponent(textFieldNewFileName)))
                    .addComponent(buttonSaveFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(buttonLoadFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldNewFileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSaveFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonLoadFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLoadFileActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION){
            fileProcessor.loadFile(fileChooser.getSelectedFile());
            textFieldFilePath.setText(fileChooser.getSelectedFile().getPath());
            
            buttonSaveFile.setEnabled(true);
            progressBar.setValue(0);
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
                    
                    Object[] options = {"Sim", "Não"};
                    int result = JOptionPane.showOptionDialog(PostProcessorFrame.this,
                        "Você deseja abrir a pasta do arquivo?",
                        "Arquivo criado com sucesso",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,     //do not use a custom Icon
                        options,  //the titles of buttons
                        options[0]); //default button title
                    
                    if(result == JOptionPane.OK_OPTION){
                        Desktop.getDesktop().open(new File(fileProcessor.getFilePath()));
                    }
                    
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JTextField textFieldFilePath;
    private javax.swing.JTextField textFieldNewFileName;
    // End of variables declaration//GEN-END:variables
}
