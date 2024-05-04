package com.se.voluntarie.services;
import com.se.voluntarie.dtos.UserDto;
import com.se.voluntarie.models.UserModel;
import com.se.voluntarie.repositories.UserRepository;
import com.se.voluntarie.services.exceptions.DatabaseException;
import com.se.voluntarie.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserModel save(UserModel userModel) {
        return userRepository.save(userModel);
    }

    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        List<UserModel> userModelList = userRepository.findAll();
        return userModelList.stream().map(UserDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDto findById(Long userId) {
        Optional<UserModel> userDtoOptional = userRepository.findById(userId);
        UserModel entity = userDtoOptional.orElseThrow(() -> new ResourceNotFoundException("Entity Not Found"));
        return new UserDto(entity);
    }

    @Transactional
    public UserDto updateUser(Long id, UserDto userDto) {
        try {
            UserModel userModel = userRepository.getReferenceById(id);
            userModel.setEmail(userDto.getEmail());
            userModel.setName(userDto.getName());
            return new UserDto(userRepository.save(userModel));
        }catch (EntityNotFoundException e) {
           throw new ResourceNotFoundException("Id not found: " + id);
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteUser(Long id) {
        if(!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Id not found");
        }
        try {
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }
}
