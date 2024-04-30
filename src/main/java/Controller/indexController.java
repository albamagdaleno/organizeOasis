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
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author alimb
 */
@Named
@ViewScoped

public class indexController implements Serializable{
    
    private User user;
    
    @EJB
    private UserFacadeLocal userEJB;
    
    @PostConstruct
    public void init(){
        
        user = new User();
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
    
    public String verifyUser(){
        
        /*El ?faces-redirect-true se añade para que mientras desarrollamos la app nos muestre a 
          donde estamos navegando, podemos quitarlo cuando esté lista. Es lo suyo quitarlas por 
          motivos de seguridad*/
        String direction = "public/insufficientPermissions.xhtml?faces-redirect=true";
        
        User searchedUser = userEJB.verifyUser(user);
        
        if(searchedUser!=null){
            direction = "private/mainViewUser.xhtml?faces-redirect=true";
        }
        
        return direction;
    }
}
