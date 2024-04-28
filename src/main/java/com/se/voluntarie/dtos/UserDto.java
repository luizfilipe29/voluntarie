package com.se.voluntarie.dtos;
import com.se.voluntarie.models.UserModel;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID idUser;
    private String name;
    private String email;


    public UserDto() {}

    public UserDto(UUID idUser, String name, String email) {
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
