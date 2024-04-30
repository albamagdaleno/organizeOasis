/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modelo.User;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author alimb
 */

@Named
@ViewScoped

public class templateController implements Serializable{
    
    public void verifyAndShow() throws IOException{
        
        //Obtenemos el usuario global que se guarda al acceder con el login
        Object globalUserExists = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("globalUser");
        
        if(globalUserExists==null){
            //Si es nulo, directamente redirigimos a pagina de error
            FacesContext.getCurrentInstance().getExternalContext().redirect("public/insufficientPermissions.xhtml");
        }
        
        //Esto sirve pra obtener la dirección de contexto relativa de cada momento
        //FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
        
    }
}
