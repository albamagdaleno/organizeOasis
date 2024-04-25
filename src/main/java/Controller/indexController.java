/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.Serializable;
import javax.faces.view.ViewScoped;

/**
 *
 * @author alimb
 */

@ViewScoped

public class indexController implements Serializable{
    
    public String verifyUser(){
        
        return "private/mainViewUser.xhtml";
    }
}
