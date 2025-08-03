package com.tugra.service;
import com.tugra.dto.DtoUser;
import com.tugra.dto.DtoUserUI;
import com.tugra.model.User;
import com.tugra.repository.UserRepository;
import com.tugra.service.impl.TcknDogrulama;
import com.tugra.service.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("User Service Test")
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TcknDogrulama tcknDogrulama;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    @DisplayName("User ekleme testi")
    void testEkleUser() {

        DtoUserUI dtoUserUI = new DtoUserUI();
        dtoUserUI.setTckn("43603460444");
        dtoUserUI.setAd("Ali");
        dtoUserUI.setSoyad("Veli");
        dtoUserUI.setPassword("sifre");

        when(tcknDogrulama.tcknDogrulama(dtoUserUI.getTckn())).thenReturn(true);

        User user = new User();
        user.setTckn("true");
        user.setAd("Ali");
        user.setSoyad("Veli");
        user.setPassword("sifre");

        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        DtoUser result = userServiceImpl.ekleUser(dtoUserUI);

        assertEquals("true", result.getTckn());
        assertEquals("Ali", result.getAd());
        assertEquals("Veli", result.getSoyad());
        assertEquals("sifre", result.getPassword());
    }

}