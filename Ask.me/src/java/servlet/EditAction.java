/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

/**
 *
 * @author zometaax
 */
public class EditAction {
    
    private String newUsername;
    private String newPassword;
    private String newName;
    private String newDOBDay;
    private String newDOBMonth;
    private String newDOBYear;
    private String newGender;
  

    public String getNewUsername() {
        return newUsername;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getNewName() {
        return newName;
    }

    public String getNewDOBDay() {
        return newDOBDay;
    }

    public String getNewDOBMonth() {
        return newDOBMonth;
    }

    public String getNewDOBYear() {
        return newDOBYear;
    }

    public String getNewGender() {
        return newGender;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public void setNewDOBDay(String newDOBDay) {
        this.newDOBDay = newDOBDay;
    }

    public void setNewDOBMonth(String newDOBMonth) {
        this.newDOBMonth = newDOBMonth;
    }

    public void setNewDOBYear(String newDOBYear) {
        this.newDOBYear = newDOBYear;
    }

    public void setNewGender(String newGender) {
        this.newGender = newGender;
    }
    
}
