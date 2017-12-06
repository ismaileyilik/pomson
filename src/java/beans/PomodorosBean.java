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
public class PomodorosBean implements Serializable{
    private int goalID;
    private int pomodoroID;
    private String taskDescription;
    private Timestamp startTime;
    private Timestamp endTime;
    private String comments;

    public PomodorosBean(){
        super();
    }

    public int getGoalID() {
        return goalID;
    }

    public void setGoalID(int goalID) {
        this.goalID = goalID;
    }

    public int getPomodoroID() {
        return pomodoroID;
    }

    public void setPomodoroID(int pomodoroID) {
        this.pomodoroID = pomodoroID;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
    
}