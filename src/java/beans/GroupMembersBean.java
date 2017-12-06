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
public class GroupMembersBean implements Serializable{
    private int groupID;
    private String groupMemberUsername;
    private String groupRole;
    private Timestamp joinDate;
    
    public GroupMembersBean(){
        super();
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getGroupMemberUsername() {
        return groupMemberUsername;
    }

    public void setGroupMemberUsername(String groupMemberUsername) {
        this.groupMemberUsername = groupMemberUsername;
    }

    public String getGroupRole() {
        return groupRole;
    }

    public void setGroupRole(String groupRole) {
        this.groupRole = groupRole;
    }

    public Timestamp getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Timestamp joinDate) {
        this.joinDate = joinDate;
    }
    
    
}
