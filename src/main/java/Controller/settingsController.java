package Controller;


import EJB.UserFacadeLocal;
import Modelo.User;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alimb
 */
@Named
@ViewScoped
public class settingsController implements Serializable{
    
    private User user;
    
    
    @EJB
    private UserFacadeLocal userEJB;
    
    @PostConstruct
    public void init(){
        
        user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("globalUser");
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserEJB(UserFacadeLocal userEJB) {
        this.userEJB = userEJB;
    }

    public User getUser() {
        return user;
    }

    public UserFacadeLocal getUserEJB() {
        return userEJB;
    }
    
    public String nameGlobalUser(){
    
    return user.getName();
    }

}

