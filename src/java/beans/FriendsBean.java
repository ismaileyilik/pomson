/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author colton
 */
public class FriendsBean implements Serializable{
    private String firstFriend;
    private String secondFriend;
    private Timestamp friendsSinceDate;

    public FriendsBean(){
        super();
    }

    public String getFirstFriend() {
        return firstFriend;
    }

    public void setFirstFriend(String firstFriend) {
        this.firstFriend = firstFriend;
    }

    public String getSecondFriend() {
        return secondFriend;
    }

    public void setSecondFriend(String secondFriend) {
        this.secondFriend = secondFriend;
    }

    public Timestamp getFriendsSinceDate() {
        return friendsSinceDate;
    }

    public void setFriendsSinceDate(Timestamp friendsSinceDate) {
        this.friendsSinceDate = friendsSinceDate;
    }
    
    
}
