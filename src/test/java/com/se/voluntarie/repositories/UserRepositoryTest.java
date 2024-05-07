package com.se.voluntarie.repositories;

import com.se.voluntarie.dtos.UserDto;
import com.se.voluntarie.models.UserModel;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get successfully from DB")
    void findByEmailSuccess() {
        String email = "luiz@gmail.com";
        UserDto userDto = new UserDto(999999901L, "Luiz", email, "123456");
        this.createUser(userDto);
        Optional<UserModel> result = this.userRepository.findByEmail(email);
        assertThat(result.isPresent()).isTrue();
    }

    private UserModel createUser(UserDto userDto) {
        UserModel userModel = new UserModel(userDto);
        this.entityManager.persist(userModel);
        return userModel;
    }
}