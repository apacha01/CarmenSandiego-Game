/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carmen_sandiegopacheco2;

import java.awt.event.*;
import java.sql.*;

/**
 *
 * @author Alumno
 */
public class Modelo {

    private String driver;
    private String prefijoConexion;
    private String ip;
    private String bd;
    private String tabla;
    private String usr;
    private String psw;
    private String campoDesde;
    private String campoHacia;
    private String titulo;
    private String carmenS;
    private int cantViajes;
    private int caminoMasLargo;
    private String resultadoConsulta;
    private ActionListener listener;
    private Connection connection;

    public Modelo() {
        driver = "com.mysql.cj.jdbc.Driver";
        prefijoConexion = "jdbc:mysql://";
        ip = "127.0.0.1";
        bd = "carmen_sandiego";
        tabla = "ciudades";
        campoDesde = "desde";
        campoHacia = "hacia";
        titulo = "Buscando a Carmen SanDiego";
        usr = "";
        psw = "";
        resultadoConsulta = "";
        carmenS = "Carmen Sandiego";
        cantViajes = 0;
        caminoMasLargo = 9;
    }

    public String getResultadoConsulta() {
        return resultadoConsulta;
    }

    public int getCantViajes() {
        return cantViajes;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCarmenS() {
        return carmenS;
    }

    public void consultar(String ciudad) {
        connection = obtenerConexion();
        String q = "SELECT " + campoHacia + " FROM " + tabla + " WHERE " + campoDesde + " = '" + ciudad + "'";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(q);
            
            while(resultSet.next())
                resultadoConsulta = resultSet.getString(1);
            
            resultSet.close();
            statement.close();
        }
        catch(SQLException ex) {reportException(ex.getMessage());};

    }
    
    public void buscarCarmenSandiego(String ciudad){
        cantViajes = 0;
        resultadoConsulta = ciudad;
        while(!resultadoConsulta.equals(carmenS)){
            consultar(resultadoConsulta);
            cantViajes++;
            if(resultadoConsulta.equals(ciudad)){
                resultadoConsulta = "Es imposible llegar a Carmen Sandiego desde " + ciudad + ".";
                cantViajes = -1;
                return;
            }
        }
        resultadoConsulta = (cantViajes == caminoMasLargo)
                ? "Encontraste a Carmen Sandiego en " + String.valueOf(cantViajes) + 
                (cantViajes == 1 ? " viaje." : " viajes.") + " Es el camino más largo."
                : "Encontraste a Carmen Sandiego en " + String.valueOf(cantViajes) + 
                (cantViajes == 1 ? " viaje." : " viajes.") + " No es el camino más largo.";
    }

    public void addExceptionListener(ActionListener listener) {
        this.listener = listener;
    }

    private void reportException(String exception) {
        if (listener != null) {
            ActionEvent evt = new ActionEvent(this, 0, exception);
            listener.actionPerformed(evt);
        }
    }

    private Connection obtenerConexion() {
        if (connection == null) {
            try {
                Class.forName(driver); // driver = "com.mysql.cj.jdbc.Driver";
            } catch (ClassNotFoundException ex) {
                reportException(ex.getMessage());
            }
            try { // prefijoConexion = "jdbc:mysql://";
                connection
                        = DriverManager.getConnection(prefijoConexion + ip + "/" + bd, usr, psw);
            } catch (Exception ex) {
                reportException(ex.getMessage());
            }
            Runtime.getRuntime().addShutdownHook(new ShutDownHook());
        }
        return connection;
    }

    private class ShutDownHook extends Thread {

        public void run() {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                reportException(ex.getMessage());
            }
        }
    }
}
