package com.smartvault.authentication.auth.mapper;

import com.smartvault.authentication.auth.dto.UserDTO;
import com.smartvault.authentication.auth.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserMapper(ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User convertToEntity(UserDTO dto) {
        User user = modelMapper.map(dto, User.class);
        if (dto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        return user;
    }
}