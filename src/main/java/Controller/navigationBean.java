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
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author albamagdaleno
 */

@Named
@RequestScoped
public class navigationBean {
    
    @Inject
    private adminUserPagesController adminUserPagesController; 
    
    public String redirectToUserPages(User user)  {
        
        adminUserPagesController.setSelectedUser(user);
        return "/private/administrator/userPagesViewAdmin.xhtml?faces-redirect=true";
    }
    
    public String redirectToMainAdmin() {
        return "/private/administrator/mainViewAdmin.xhtml?faces-redirect=true";
    }
    
    public String redirectToSettings() {
        return "/private/settingsViewUser.xhtml?faces-redirect=true";
    }
    
    public String redirectToMainViewUser() {
        return "/private/mainViewUser.xhtml?faces-redirect=true";
    }
    
    public String redirectToStatistics() {
        
        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("globalUser");
        if(user.getRol().toString().equals("Private")){
            
            return "/private/userPrivate/statisticsPrivateUser.xhtml?faces-redirect=true";
        }else{
            
            return "/private/userPublic/statisticsPublicUser.xhtml?faces-redirect=true";
        }
        
    }
    
    public String redirectToStatisticsPrivate() {
        return "/private/userPrivate/statisticsPrivateUser.xhtml?faces-redirect=true";
        
    }
    
    public String redirectToStatisticsPublic() {
        return "/private/userPublic/statisticsPublicUser.xhtml?faces-redirect=true";
        
    }
    
    public String logout(){
        return "/index.xhtml?faces-redirect=true";
    }
}
