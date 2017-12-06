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
public class FriendRequestsBean implements Serializable{
    private String requestor;
    private String requestee;
    private int acceptedStatusBoolean;
    private Timestamp dateRequested;

    public FriendRequestsBean(){
        super();
    }

    public String getRequestor() {
        return requestor;
    }

    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    public String getRequestee() {
        return requestee;
    }

    public void setRequestee(String requestee) {
        this.requestee = requestee;
    }

    public int getAcceptedStatusBoolean() {
        return acceptedStatusBoolean;
    }

    public void setAcceptedStatusBoolean(int acceptedStatusBoolean) {
        this.acceptedStatusBoolean = acceptedStatusBoolean;
    }

    public Timestamp getDateRequested() {
        return dateRequested;
    }

    public void setDateRequested(Timestamp dateRequested) {
        this.dateRequested = dateRequested;
    }
    
    
}
