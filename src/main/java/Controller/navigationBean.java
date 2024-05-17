/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modelo.User;
import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author albamagdaleno
 */

@Named
@RequestScoped
public class navigationBean {
    
    public String redirectToUserPages()  {
        return "/private/administrator/userPagesViewAdmin.xhtml?faces-redirect=true";
    }
    
    public String redirectToMainAdmin() {
        return "/private/administrator/mainViewAdmin.xhtml?faces-redirect=true";
    }
    
    public String redirectToSettings() {
        return "/private/userPublic/settingsViewUser.xhtml?faces-redirect=true";
    }
    
    public String redirectToMainViewUser() {
        return "/private/userPublic/mainViewUser.xhtml?faces-redirect=true";
    }
}
