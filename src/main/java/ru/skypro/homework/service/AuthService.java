package ru.skypro.homework.service;

import ru.skypro.homework.dto.RegisterReqTo;
import ru.skypro.homework.dto.Role;

public interface AuthService {
    boolean login(String userName, String password);
    boolean register(RegisterReqTo registerReq, Role role);
}
