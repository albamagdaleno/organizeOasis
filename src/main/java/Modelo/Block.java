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
@Table(name="blocks")
public class Block implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBlock;
    
    /*
    public enum Type {
        lists, tables, texts
    }
    
    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private Type type;
    */
    
    @Column(name="id_type")
    private String id_type; 
    
    @JoinColumn(name="id_page")
    @ManyToOne
    private Page page;

    public int getIdBlock() {
        return idBlock;
    }

    public String getId_type() {
        return id_type;
    }

    public Page getPage() {
        return page;
    }

    public void setIdBlock(int idBlock) {
        this.idBlock = idBlock;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }

    public void setPage(Page page) {
        this.page = page;
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
        final Block other = (Block) obj;
        if (this.idBlock != other.idBlock) {
            return false;
        }
        if (!Objects.equals(this.id_type, other.id_type)) {
            return false;
        }
        if (!Objects.equals(this.page, other.page)) {
            return false;
        }
        return true;
    }
    
    
}
