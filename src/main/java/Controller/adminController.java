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
import javax.inject.Inject;

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
    private PageFacadeLocal pageEJB;
    
    @Inject
    private adminUserPagesController adminUserPagesController; 
    
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
            
            //Borramos las páginas si las hubiera para ese usuario
            adminUserPagesController.setSelectedUser(selectedUser);
            List<Page> pagesList = adminUserPagesController.getListUserPages();
            for(Page page: pagesList){
                adminUserPagesController.setSelectedPage(page);
                adminUserPagesController.deletePage();
            }
            
            //Borramos el usuario
            userEJB.remove(selectedUser);
            listUser = userEJB.findAll();  
            selectedUser = null;
            
        }
        
    }

}
