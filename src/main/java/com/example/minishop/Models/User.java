package com.example.minishop.Models;
import java.io.Serializable;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Required;

@Entity
@Table(name="Users")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(unique = true)
    public String email;

    @Column
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String password;

    @Column
    public String role;

    @Column
    public String nom;

    @Column()
    public boolean isActive;
}
