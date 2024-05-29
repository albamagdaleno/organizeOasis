package Controller;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import EJB.UserFacadeLocal;
import Modelo.User;

@Named
@RequestScoped
public class SearchUsernameQuery implements Serializable {
    @EJB
    private UserFacadeLocal userFacadeLocal;

    //Campo por el que se realiza la busqueda para el filtro de usuarios
    private String searchTerm;
    private User selectedUser;
    private String errorMessage;
    private List<String> filteredUsers;

    public List<String> completeUser(String searchTerm) {
        List<String> allUsers = userFacadeLocal.getUsersStartingWith(searchTerm);
        List<String> filtered = new ArrayList<>();

        for (String user : allUsers) {
            if (user.toLowerCase().startsWith(searchTerm.toLowerCase())) {
                filtered.add(user);
            }
        }

        return filtered;
    }

    public String searchUser() {
        selectedUser = userFacadeLocal.findUserByUsername(searchTerm);
        if (selectedUser == null) {
            errorMessage = "No se encontró ningún usuario con el nombre de usuario: " + searchTerm;
        } else {
            errorMessage = null;
        }
        return null;
    }

    public UserFacadeLocal getUserFacadeLocal() {
        return userFacadeLocal;
    }

    public void setUserFacadeLocal(UserFacadeLocal userFacadeLocal) {
        this.userFacadeLocal = userFacadeLocal;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public List<String> getFilteredUsers() {
        return filteredUsers;
    }

    public void setFilteredUsers(List<String> filteredUsers) {
        this.filteredUsers = filteredUsers;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
