/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.UserFacadeLocal;
import Modelo.Page;
import Modelo.User;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author albamagdaleno
 */
@Named
@ViewScoped

public class adminController implements Serializable{ 
    
    private User selUser;
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
    
    public List<Page> getListUserPage(){
        return listPageUser;
    }
    
    public void setSelectedUser(User user){
        this.selUser = user;
    }
    
    public User getSelectedUSer(){
        return selUser;
    }
    
    public void deleteUser(){
        if(selUser!=null){
            userEJB.remove(selUser);
            listUser = userEJB.findAll();  
            selUser = null;
        }
    }
}
