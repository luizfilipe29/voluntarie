package com.se.voluntarie.models;
import com.se.voluntarie.dtos.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_user")
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUser;
    private String name;
    private String email;
    private String password;

    public UserModel(UserDto userDto) {
        this.name = userDto.getName();
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
    }
}
