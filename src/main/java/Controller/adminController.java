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
    private List<Page> listUserPages;
    
    @EJB
    private UserFacadeLocal userEJB;
    private PageFacadeLocal pageEJB;


    @PostConstruct
    public void init() {
        selectedPage= new Page();
        // Asigna la lista al campo
        this.listUser = userEJB.findAll();
        
    }
    
    public List<User> getListUser(){
        return listUser;
    }
    
    public List<Page> getListUserPages(User user){
        this.listUserPages = userEJB.findPages(user.getId_user());
        return listUserPages;
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
    
    public void deletePage(){
        
            System.out.println("Pagina sseleccionada "+selectedPage.getTitle()+" id "+selectedPage.getId_page()+" bloques "+selectedPage.getNum_blocks()+" id Uset "+selectedPage.getUser().getId_user()+" visitas "+selectedPage.getVisits());
            System.out.println("Usuario seleccionad "+selectedUser.getName()+" id user "+selectedUser.getId_user());
            pageEJB.remove(selectedPage);
            listUserPages = userEJB.findPages(selectedUser.getId_user());
            selectedPage = null;
        
    }
    
}
