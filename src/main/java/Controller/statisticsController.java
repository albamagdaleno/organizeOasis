/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.UserFacadeLocal;
import Modelo.User;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author alimb
 */
@Named
@ViewScoped
public class statisticsController implements Serializable{
   
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
    
    public int getVisitsUser(){
        
        return this.user.getVisits();
    }
}
