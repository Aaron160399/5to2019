/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author Aaron
 */
public class Meeting_has_friend {
    private int friend_idfriend;
    private int meeting_idmeeting;

    public Meeting_has_friend(int friend_idfriend, int meeting_idmeeting) {
        this.friend_idfriend = friend_idfriend;
        this.meeting_idmeeting = meeting_idmeeting;
    }

    public int getFriend_idfriend() {
        return friend_idfriend;
    }

    public void setFriend_idfriend(int friend_idfriend) {
        this.friend_idfriend = friend_idfriend;
    }

    public int getMeeting_idmeeting() {
        return meeting_idmeeting;
    }

    public void setMeeting_idmeeting(int meeting_idmeeting) {
        this.meeting_idmeeting = meeting_idmeeting;
    }
}
