/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import pojo.Friend;

/**
 *
 * @author Aaron
 */
public class FriendDao {
    
    public int insert_friend(Friend pojo) {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("call insert_friend(?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, pojo.getName());
            st.setString(2, pojo.getPhoneNumber());
            st.setString(3, pojo.getAddress());
            st.setDate(4, pojo.getBirthtday());
//            st.setBinaryStream(5, null);
            st.setBinaryStream(5, new FileInputStream(
                    new File(pojo.getImage())), 
                    (int) new File(pojo.getImage()).length());
//            st.setBinaryStream(5, new FileInputStream(new File(pojo.getImage())),
//                    (int) new File(pojo.getImage()).length());
            id = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                System.out.println("ID inserted "+id);
            }
        } catch (Exception e) {
            System.out.println("Error while inserting a friend " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return id;
    }

    public DefaultTableModel cargarModelo() {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"ID","Name","Phone"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL select_all_friends");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[3];
                Friend pojo = inflaPOJO(rs);
                
                ob[0] = pojo.getIdFriend();
                ob[1] = pojo.getName();
                ob[2] = pojo.getPhoneNumber();
                
                dt.addRow(ob);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla de grupos " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
    
    public DefaultComboBoxModel loadCombo() {
        Connection con = null;
        PreparedStatement st = null;
        DefaultComboBoxModel dt = null;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL select_all_friends");
            dt = new DefaultComboBoxModel();
            ResultSet rs = st.executeQuery();
            dt.addElement("Seleccione a su amigo");
            while (rs.next()) {
                Friend pojo = inflaPOJO(rs);
                dt.addElement(pojo);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar el modelo amigos " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
    public DefaultComboBoxModel cargarCombo() {
        Connection con = null;
        PreparedStatement st = null;
        DefaultComboBoxModel dt = null;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("SHOW FULL TABLES WHERE TABLE TYPE = 'VIEW'");
            dt = new DefaultComboBoxModel();
            ResultSet rs = st.executeQuery();
            dt.addElement("Seleccione su grupo");
            while (rs.next()) {
                dt.addElement(rs.getString(0));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar el combo grupos " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
    
    public static Friend selectFriend(int id) {
        Connection con = null;
        PreparedStatement st = null;
        Friend pojo = new Friend();
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL select_a_friend(?)");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                pojo = inflaPOJO(rs);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar amigo " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return pojo;
    }
    
    private static Friend inflaPOJO(ResultSet rs) {

       Friend pojo = new Friend();
        try {
            pojo.setIdFriend(rs.getInt("idfriend"));
            pojo.setName(rs.getString("name"));
            pojo.setPhoneNumber(rs.getString("phone_number"));
            pojo.setAddress(rs.getString("address"));
            pojo.setBirthtday(rs.getDate("birthday"));
            //pojo.setImage(rs.getBlob("image"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo " + ex);
        }
        return pojo;
    }
   
    public static boolean delete_friend(int id) {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL delete_friend(?)");
            st.setInt(1, id);
            int num = st.executeUpdate();
            if (num == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar = " + e);
            return false;
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return true;
    }
    
    public static void exportFriends(String exportFile) {
        Connection con = null;
        PreparedStatement st = null;
        boolean success = false;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement(
                    "SELECT * FROM friend INTO OUTFILE '"+exportFile+"' "
                            + "FIELDS TERMINATED BY ';' OPTIONALLY ENCLOSED BY '\\\"' "
                            + "LINES TERMINATED BY '\\n\\r'");
            success = st.execute();
        } catch (Exception e) {
            System.out.println("Error al exportar amigos " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
    }
    
    public static void importFriends(String importFile) {
        Connection con = null;
        PreparedStatement st = null;
        boolean success = false;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement(
                    "LOAD DATA INFILE '"+importFile+"'" +
                    "INTO TABLE gente\n" +
                    "FIELDS TERMINATED BY ','\n" +
                    "LINES TERMINATED BY '\\r\\n'\n" +
                    "IGNORE 2 LINES\n" +
                    "(fecha,nombre)");
            success = st.execute();
        } catch (Exception e) {
            System.out.println("Error al importar amigos " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
    }
    
    public static boolean updateFriend(Friend pojo) {

        Connection con = null;
        PreparedStatement st = null;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("Call update_friend(?,?,?,?,?)");
            st.setInt(1, pojo.getIdFriend());;
            st.setString(2, pojo.getName());
            st.setString(3, pojo.getPhoneNumber());
            st.setString(4, pojo.getAddress());
            st.setDate(5, pojo.getBirthtday());
           
            int x = st.executeUpdate();

            if (x == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar " + e);
            return false;
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return true;
    }
    
    
    
}
