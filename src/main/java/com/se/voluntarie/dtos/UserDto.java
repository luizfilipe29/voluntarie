package com.se.voluntarie.dtos;
import com.se.voluntarie.models.UserModel;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long idUser;
    private String name;
    private String email;


    public UserDto() {}

    public UserDto(Long idUser, String name, String email) {
        this.idUser = idUser;
        this.name = name;
        this.email = email;
    }

    public UserDto(UserModel userModel){
        this.idUser = userModel.getIdUser();
        this.name = userModel.getName();
        this.email = userModel.getEmail();
    }

}
