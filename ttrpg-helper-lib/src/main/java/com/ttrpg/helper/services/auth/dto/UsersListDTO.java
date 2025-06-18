package com.ttrpg.helper.services.auth.dto;

import com.ttrpg.helper.services.auth.entites.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UsersListDTO {
    private List<UserDTO> users;

    public static UsersListDTO convertEntityToDto(List<User> entities) {

        List<UserDTO> dtos = new ArrayList<>();
        for(User u : entities)
        {
            dtos.add(UserDTO.convertEntityToDto(u));
        }
        return new UsersListDTO(dtos);
    }

    public static List<User> convertDtoToEntity(UsersListDTO dto) {
        List<User> users = new ArrayList<>();
        for(UserDTO u : dto.users)
        {
            users.add(UserDTO.convertDtoToEntity(u));
        }
        return users;
    }
}