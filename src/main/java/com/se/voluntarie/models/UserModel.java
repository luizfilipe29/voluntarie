package com.se.voluntarie.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_users")
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idUser;
    private String name;
    private String email;

    public UserModel(){}

    public UserModel(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
}
