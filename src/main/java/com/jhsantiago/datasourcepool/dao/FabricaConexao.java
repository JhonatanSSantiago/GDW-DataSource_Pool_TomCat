/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhsantiago.datasourcepool.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author jhons
 */
public class FabricaConexao {
    
       public Connection pegaConexao() throws ErroDAOException {
        try {
            Connection con;
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/banco_testes");
            con = ds.getConnection();
            return con;
        } catch (NamingException |SQLException ex) {
            throw new ErroDAOException(ex);
        }
    }

}
