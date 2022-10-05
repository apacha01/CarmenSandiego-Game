
package carmen_sandiegopacheco2;

import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author Alumno
 */
public class Vista extends JFrame{
    private JButton btnBuscar;
    private JLabel lblCiudad;
    private JTextField txtfCiudad;
    private JTextField txtfRespuesta;
    private Modelo m;
    
    public Vista(Modelo m){
        this.m = m;
        this.m.addExceptionListener(new ExceptionListener());
        txtfCiudad = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        lblCiudad = new javax.swing.JLabel();
        txtfRespuesta = new javax.swing.JTextField();
        
        btnBuscar.setText("Buscar a Carmen SanDiego");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        
        lblCiudad.setText("Ciudad");
        txtfRespuesta.setEditable(false);
    }
    
    private void btnBuscarActionPerformed(ActionEvent evt) {                                          
        m.buscarCarmenSandiego(txtfCiudad.getText());
        txtfRespuesta.setText(m.getResultadoConsulta());
    }
    
    public void mostrar(){
        setTitle(m.getTitulo());
        inicializar();
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private void inicializar() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfRespuesta)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtfCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtfRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }
    
    public void mostrarExcepcion(final String s) {
        JOptionPane.showMessageDialog(this, s, "Error!", 0);
    }
    
    private class ExceptionListener implements ActionListener
    {
        @Override
        public void actionPerformed(final ActionEvent event) {
            Vista.this.mostrarExcepcion(event.getActionCommand());
        }
    }
}
