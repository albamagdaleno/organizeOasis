/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.UserFacadeLocal;
import Modelo.User;
import Modelo.User.Rol;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *
 * @author alimb
 */
@Named
@ViewScoped

public class indexController implements Serializable{
    
    private User user;
    private String errorLoginMessage;
    
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
    
    public String getErrorLoginMessage(){
        return errorLoginMessage;
    }
    
    public void setErrorLoginMessage(String errorLoginMessage) {
        this.errorLoginMessage = errorLoginMessage;
}
    
    public String verifyUser(){
        
        /*El ?faces-redirect-true se añade para que mientras desarrollamos la app nos muestre a 
          donde estamos navegando, podemos quitarlo cuando esté lista. Es lo suyo quitarlas por 
          motivos de seguridad*/
        String direction = "public/insufficientPermissions.xhtml?faces-redirect=true";
        //String direction = "index.xhtml?faces-redirect=true";
        
        User searchedUser = userEJB.verifyUser(user);
        
        if(searchedUser!=null){
            if(searchedUser.getRol().equals(Rol.Administrator)){
            //if(searchedUser.getRol().toString().equals("Administrator")){
                
                direction = "private/administrator/mainViewAdmin.xhtml?faces-redirect=true";
            }else{
                
                direction = "private/mainViewUser.xhtml?faces-redirect=true";
            }
            
        }else{
            errorLoginMessage = "Nombre de usuario o contraseña incorrectos.";
            //PrimeFaces.current().executeScript("PF('errorDialog').show()");
                   
        }
        
        return direction;
    }
}
