package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.ResponseWrapperUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.exception.NoAccessException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public ResponseWrapperUserDto getAllUsersWithOrderById() {
        List<UserDto> userDtoDtoList = userMapper.usersEntitiesToUserDtos(userRepository.findAllByOrderById());
        ResponseWrapperUserDto responseWrapperUserTo = new ResponseWrapperUserDto();
        responseWrapperUserTo.setCount(userDtoDtoList.size());
        responseWrapperUserTo.setResults(userDtoDtoList);
        return responseWrapperUserTo;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Authentication authentication) {
        String currentUsername = authentication.getName();
        UserDetails currentUserDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findUserByUserName(currentUsername).orElseThrow(UserNotFoundException::new);

        if (currentUserDetails.getAuthorities().toString().contains("ROLE_ADMIN")
                || currentUsername.equals(user.getUserName())) {
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPhone(userDto.getPhone());
            user.setEmail(user.getEmail());
            userRepository.save(user);

        } else {
            throw new NoAccessException();
        }
        return userDto;
    }

    @Override
    public UserDto getUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return userMapper.usersEntityToUserDto(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUserName(username).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User updateUser(User user) {
        User currentUser = userRepository.findUserByUserName(user.getUserName()).orElseThrow(UserNotFoundException::new);
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setPhone(user.getPhone());
        userRepository.save(currentUser);

        return user;
    }

    @Override
    public void savePassword(String username, String newPassword) {
        User user = userRepository.findUserByUserName(username).orElseThrow(UserNotFoundException::new);
        user.setPassword(newPassword);
        userRepository.save(user);

    }
}
