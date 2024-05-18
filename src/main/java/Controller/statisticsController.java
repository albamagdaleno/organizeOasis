/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.UserFacadeLocal;
import Modelo.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    
    public String getPercentile() {
        List<Integer> visitsUsers = userEJB.getVisitsUsers();

        int visitsGlobalUser = this.user.getVisits();

        Collections.sort(visitsUsers);

        // Posición del usuario actual dentro de la lista ordenada
        int index = Collections.binarySearch(visitsUsers, visitsGlobalUser);

        // Si el número de visitas del usuario no está en la lista, calcular su posición en base a dónde debería estar
        if (index < 0) {
            index = -(index + 1);
        }

        // Calcular el percentil y redondearlo al entero más cercano
        double percentile = (double) index / visitsUsers.size() * 100;
        int roundedPercentile = (int) Math.round(percentile);

        // Devolver el percentil redondeado
        return roundedPercentile + "%";
    }
}
