/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import pojo.Meeting;

/**
 *
 * @author Aaron
 */
public class MeetingDao {
    public int insert_meeting(Meeting pojo) {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        try {
            con = Conexion.getConnection();
            //No funciona con un PA
            st = con.prepareStatement("INSERT INTO meeting (date_of, place) VALUES(?, ?)", 
                    PreparedStatement.RETURN_GENERATED_KEYS);
            st.setTimestamp(1, pojo.getDate_of());
            st.setString(2, pojo.getPlace());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                System.out.println("Inserted ID: "+id);
            } else {
                System.out.println("No");
            }
        } catch (Exception e) {
            System.out.println("Error while inserting a meeting " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return id;
    }
}
