/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import pojo.Meeting_has_friend;

/**
 *
 * @author Aaron
 */
public class Meeting_has_frien_Dao {
    public int insert_mhf(Meeting_has_friend pojo) {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("call insert_date_nm(?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setInt(1, pojo.getMeeting_idmeeting());
            st.setInt(2, pojo.getFriend_idfriend());
            id = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error while inserting a meetingNM " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return id;
    }
}
