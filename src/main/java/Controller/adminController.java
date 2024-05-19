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
    private Page selectedPage;
    private List<User> listUser;
    private List<Page> listPageUser;
    
    @EJB
    private UserFacadeLocal userEJB;
    private PageFacadeLocal pageEJB;


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
    
    public void setSelectedPage(Page selectedPage) {
        this.selectedPage = selectedPage;
    }
    
    public User getSelectedUSer(){
        return selectedUser;
    }
    
    public Page getSelectedPAge() {
        return selectedPage;
    }
    
    public void deleteUser(){
        if(selectedUser!=null){
            //Borrar todas las paginas del usuario --METHOD
            userEJB.remove(selectedUser);
            listUser = userEJB.findAll();  
            selectedUser = null;
        } 
    }
    
    public void deletePage(User user){
        if(selectedPage!=null){
            pageEJB.remove(selectedPage);
            listPageUser = userEJB.findPages(user.getId_user());
            selectedPage = null;
        }
    }
    
}
