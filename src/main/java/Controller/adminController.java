/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.UserFacadeLocal;
import Modelo.Page;
import Modelo.User;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author albamagdaleno
 */
@Named
@SessionScoped

public class adminController implements Serializable{ 
    
    private User selectedUser;
    private List<User> listUser;
    private List<Page> listPageUser;
    
    @EJB
    private UserFacadeLocal userEJB;

    @PostConstruct
    public void init() {
        // Asigna la lista al campo
        this.listUser = userEJB.findAll();
        
    }
    
    public List<User> getListUser(){
        return listUser;
    }
    
    public List<Page> getListUserPage(User user){
        this.listPageUser = userEJB.findPages(user.getId_user());
        return listPageUser;
    }
    
    public void setSelectedUser(User user){
        this.selectedUser = user;
    }
    
    public User getSelectedUSer(){
        return selectedUser;
    }
    
    public void deleteUser(){
        if(selectedUser!=null){
            userEJB.remove(selectedUser);
            listUser = userEJB.findAll();  
            selectedUser = null;
        }
        
    }
}
