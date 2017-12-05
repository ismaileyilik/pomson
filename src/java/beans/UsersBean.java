package beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;

/**
 *
 * @author colton
 */
public class UsersBean implements Serializable{
    private String givenUsername;
    private String givenPassword;
    private int PomodoroLengthPreferenceMins;
    private int PomodoroShortBreakPreferenceMins;
    private int PomodoroLongBreakPreferenceMins;
    
    public UsersBean(){
        super();
    }
              
    public String getGivenUsername() {
        return givenUsername;
    }

    public void setGivenUsername(String givenUsername) {
        this.givenUsername = givenUsername;
    }

    public String getGivenPassword() {
        return givenPassword;
    }

    public void setGivenPassword(String givenPassword) {
        this.givenPassword = givenPassword;
    }

    public int getPomodoroLengthPreferenceMins() {
        return PomodoroLengthPreferenceMins;
    }

    public void setPomodoroLengthPreferenceMins(int PomodoroLengthPreferenceMins) {
        this.PomodoroLengthPreferenceMins = PomodoroLengthPreferenceMins;
    }

    public int getPomodoroShortBreakPreferenceMins() {
        return PomodoroShortBreakPreferenceMins;
    }

    public void setPomodoroShortBreakPreferenceMins(int PomodoroShortBreakPreferenceMins) {
        this.PomodoroShortBreakPreferenceMins = PomodoroShortBreakPreferenceMins;
    }

    public int getPomodoroLongBreakPreferenceMins() {
        return PomodoroLongBreakPreferenceMins;
    }

    public void setPomodoroLongBreakPreferenceMins(int PomodoroLongBreakPreferenceMins) {
        this.PomodoroLongBreakPreferenceMins = PomodoroLongBreakPreferenceMins;
    }
    
    
}
