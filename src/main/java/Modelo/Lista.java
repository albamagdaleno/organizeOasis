/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;

/**
 *
 * @author albamagdaleno
 */
@Entity
@Table(name = "lists")
public class Lista implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_list")
    private int idList;

    @Column(name = "text_str")
    private String text;

    @JoinColumn(name = "id_block")
    @ManyToOne
    private Block block;

    public int getIdList() {
        return idList;
    }

    public String getText() {
        return text;
    }

    public Block getBlock() {
        return block;
    }

    public void setIdList(int idList) {
        this.idList = idList;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.idList;
        hash = 79 * hash + Objects.hashCode(this.text);
        hash = 79 * hash + Objects.hashCode(this.block);
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
        final Lista other = (Lista) obj;
        if (this.idList != other.idList) {
            return false;
        }
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        if (!Objects.equals(this.block, other.block)) {
            return false;
        }
        return true;
    }

    
}
