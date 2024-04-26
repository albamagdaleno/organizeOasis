/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;
/**
 *
 * @author albamagdaleno
 */

@Entity
@Table(name="pages")
public class Page implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
    
    @Column(name="title")
    private String title; 
    
    @Column(name="num_blocks")
    private int numBlocks;
    
    @Column(name="visits")
    private int visits;
    
    @JoinColumn(name="id_user")
    @ManyToOne //TO-DO no se si es esto pq no me aparecen las relaciones
    private User user;

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getTitle() {
        return title;
    }

    public int getNumBlocks() {
        return numBlocks;
    }

    public int getVisits() {
        return visits;
    }

    public User getUser() {
        return user;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNumBlocks(int numBlocks) {
        this.numBlocks = numBlocks;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Page other = (Page) obj;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.numBlocks != other.numBlocks) {
            return false;
        }
        if (this.visits != other.visits) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }
    
    
}
