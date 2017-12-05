/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;

/**
 *
 * @author colton
 */
public class GroupsBean implements Serializable{
    private int groupID;
    private String groupName;
    private String description;
    private Boolean verifyBeforeJoining;

    public GroupsBean(){
        super();
    }
    
    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getVerifyBeforeJoining() {
        return verifyBeforeJoining;
    }

    public void setVerifyBeforeJoining(Boolean verifyBeforeJoining) {
        this.verifyBeforeJoining = verifyBeforeJoining;
    }
    
    
}
