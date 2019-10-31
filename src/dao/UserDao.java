/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pojo.User;

/**
 *
 * @author Aaron
 */
public class UserDao {
    public User login(String user, String password){
        User pojo = new User();
        Connection con = null;
        int id = 0;
        try {
            con = Conexion.getConnection();
            PreparedStatement st = con.prepareStatement("CALL login(?,?)");
            st.setString(1, user);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                pojo = inflatePOJO(rs);
            }
        } catch (Exception e) {
            System.out.println("Error en login " + e);
        }
        return pojo;
    }
    
    private static User inflatePOJO(ResultSet rs) {

       User pojo = new User();
        try {
            pojo.setId(rs.getInt("iduser"));
            pojo.setUser(rs.getString("password"));
            pojo.setPassword(rs.getString("user"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo usuario " + ex);
        }
        return pojo;
    }
}
