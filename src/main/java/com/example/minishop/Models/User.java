package com.example.minishop.Models;
import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name="Users")
public class User implements Serializable{
    @Id
    @GeneratedValue//(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(unique = true)
    public String email;

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String password;

   
}
