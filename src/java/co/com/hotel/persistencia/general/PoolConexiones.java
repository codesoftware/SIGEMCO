/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.persistencia.general;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author nicolas
 */
public class PoolConexiones {

    private static PoolConexiones poolConexiones;
    private static String nameResource;
    private Context context;
    private DataSource ds;
    private Connection con;
    
    public static PoolConexiones getInstance(){
        if(poolConexiones == null){
            poolConexiones = new PoolConexiones();
        }
        return poolConexiones;        
    }
    

    private PoolConexiones() {
        ResourceBundle props = ResourceBundle.getBundle("co.com.hotel.archivos.configuracion.BaseConfig", Locale.getDefault());
        this.nameResource = props.getString("namePool");     
        try {
            context = new InitialContext();            
            ds = (DataSource) context.lookup("java:/comp/env/jdbc/"+nameResource);
        } catch (Exception e) {
            System.out.println("Error PoolConexiones.PoolConexiones " + e);
        }
        
    }

    public Connection getCon() {
        try {
            this.con = ds.getConnection();
        } catch (Exception e) {
            System.out.println("Error PoolConexiones.getCon "+ e);
        }
        return this.con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
    public void cerrarConexion(){
        try {
            this.con.close();
        } catch (SQLException ex) {
            System.out.println("Error PoolConexiones.cerrarConexion " + ex);
            Logger.getLogger(PoolConexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String []args){
        PoolConexiones obj = PoolConexiones.getInstance();
        Connection con2 = obj.getCon();
        
    }    
    
}
