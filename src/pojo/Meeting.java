/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Aaron
 */
public class Meeting {
    private int idmeeting;
    private String place;
    private Timestamp date_of;

    public Meeting(int idmeeting, String place, Timestamp date_of) {
        this.idmeeting = idmeeting;
        this.place = place;
        this.date_of = date_of;
    }

    public Meeting(String place, Timestamp date_of) {
        this.place = place;
        this.date_of = date_of;
    }

    public int getIdmeeting() {
        return idmeeting;
    }

    public void setIdmeeting(int idmeeting) {
        this.idmeeting = idmeeting;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Timestamp getDate_of() {
        return date_of;
    }

    public void setDate_of(Timestamp date_of) {
        this.date_of = date_of;
    }
}
