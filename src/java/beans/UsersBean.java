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
    private String username;
    private String password;
    private int PomodoroLengthPreferenceMins;
    private int PomodoroShortBreakPreferenceMins;
    private int PomodoroLongBreakPreferenceMins;
    
    public UsersBean(){
        super();
    }
              
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String Password) {
        this.password = Password;
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
