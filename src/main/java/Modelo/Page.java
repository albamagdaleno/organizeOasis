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
import javax.persistence.Table;
/**
 *
 * @author albamagdaleno
 */

@Entity
@Table(name="pages")
public class Page implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_page;
    
    @Column(name="title")
    private String title; 
    
    @Column(name="num_blocks")
    private int num_blocks;
    
    @Column(name="visits")
    private int visits;
    
    @JoinColumn(name="id_user")
    @ManyToOne 
    private User user;

    public int getId_page() {
        return id_page;
    }

    public String getTitle() {
        return title;
    }

    public int getNum_blocks() {
        return num_blocks;
    }

    public int getVisits() {
        return visits;
    }

    public User getUser() {
        return user;
    }

    public void setIdPage(int id_page) {
        this.id_page = id_page;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNumBlocks(int num_blocks) {
        this.num_blocks = num_blocks;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public void setUser(User user) {
        this.user = user;
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
        if (this.id_page != other.id_page) {
            return false;
        }
        if (this.num_blocks != other.num_blocks) {
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
