/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.PageFacadeLocal;
import EJB.UserFacadeLocal;
import Modelo.Page;
import Modelo.User;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped; 

import javax.inject.Named;

/**
 *
 * @author albamagdaleno
 */
@Named
@SessionScoped

public class adminController implements Serializable{ 
    
    private User selectedUser;
    private List<User> listUser;

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
   
    public void setSelectedUser(User user){
        this.selectedUser = user;
    }
    
    public User getSelectedUSer(){
        return selectedUser;
    }
    
    public void deleteUser(){
        if(selectedUser!=null){
            //Borrar todas las paginas del usuario --METHOD
            userEJB.remove(selectedUser);
            listUser = userEJB.findAll();  
            selectedUser = null;
        } 
    }

}
