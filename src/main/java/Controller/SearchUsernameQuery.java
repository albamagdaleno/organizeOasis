package Controller;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import EJB.UserFacadeLocal;

@Named
@RequestScoped
public class SearchUsernameQuery implements Serializable {
    @EJB
    private UserFacadeLocal userFacadeLocal;

    //Campo por el que se realiza la busqueda para el filtro de usuarios
    private String searchTerm;

    //Nombre del username que se selecciona en el filtro de busqueda de usuarios
    private String selectedUser;
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

    public void searchUser() {
        // Lógica para buscar el usuario seleccionado (this.selectedUser)
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

    public String getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(String selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<String> getFilteredUsers() {
        return filteredUsers;
    }

    public void setFilteredUsers(List<String> filteredUsers) {
        this.filteredUsers = filteredUsers;
    }
}
