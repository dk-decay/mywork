/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.user.model;

import java.util.ArrayList;
import java.util.List;
import org.biogen.business.enterprise.model.BusinessUnitType;

/**
 *
 * @author deveshkandpal
 */
public class UserDirectory {

    private List<User> userList;

    public UserDirectory() {
        this.userList = new ArrayList<User>();
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User addUser(UserType userType, BusinessUnitType buType, RoleType roleType, String username, String password) {
        User u = null;
        if (userType == UserType.BU) {
            u = new BusinessUser(userType, buType, roleType, username, password);
        } else {
            u = new PatientUser(userType);
        }
        this.userList.add(u);
        return u;
    }

    public User authenticateUser(String username, String password) {
        System.out.println("username : " + userList);
        return this.userList
                .stream()
                .filter(u -> u.getType() == UserType.BU)
                .filter(user -> user.getUsername().equals(username)
                && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

}
