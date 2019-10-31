/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.sql.Date;

/**
 *
 * @author Aaron
 */
public class Friend {
    private int idFriend;
    private String name;
    private String phoneNumber;
    private String address;
    private Date birthtday;
    private String image;

    public Friend(int idFriend, String name, String phoneNumber, String address, Date birthtday, String imagen) {
        this.idFriend = idFriend;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthtday = birthtday;
        this.image = imagen;
    }
    
    public Friend(int idFriend, String name, String phoneNumber, String address, Date birthtday) {
        this.idFriend = idFriend;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthtday = birthtday;
    }

    public Friend(String name, String phoneNumber, String address, Date birthtday, String imagen) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthtday = birthtday;
        this.image = imagen;
    }
    
    public Friend() {
        
    }

    public String getImage() {
        return image;
    }

    public void setImage(String imagen) {
        this.image = imagen;
    }

    public int getIdFriend() {
        return idFriend;
    }

    public void setIdFriend(int idFriend) {
        this.idFriend = idFriend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthtday() {
        return birthtday;
    }

    public void setBirthtday(Date birthtday) {
        this.birthtday = birthtday;
    }

    @Override
    public String toString() {
        return getName();
    }
    
    
}
