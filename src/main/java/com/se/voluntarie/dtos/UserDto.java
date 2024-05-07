package com.se.voluntarie.dtos;
import com.se.voluntarie.models.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long idUser;
    private String name;
    private String email;
    private String password;

    public UserDto(UserModel userModel){
        this.idUser = userModel.getIdUser();
        this.name = userModel.getName();
        this.email = userModel.getEmail();
    }

}
