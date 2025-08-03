package com.tugra.service.impl;

import com.tugra.dto.DtoUser;
import com.tugra.dto.DtoUserUI;
import com.tugra.model.User;
import com.tugra.repository.UserRepository;
import com.tugra.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TcknDogrulama tcknDogrulama;

    @Override
    public List<DtoUser> getUsers() {

        List<User> userList = userRepository.findAll();

        userList
                .stream()
                .forEach(user -> user.getTckn());

        List<DtoUser> dtoUserList = userList
                .stream()
                .map(user -> {
                    DtoUser dtoUser = new DtoUser();
                    BeanUtils.copyProperties(user, dtoUser);
                    return dtoUser;
                })
                .collect(Collectors.toList());

        return dtoUserList;
    }

    @Override
    public DtoUser getUserByAd(String ad) {

        List<User> users = userRepository.findByAd(ad);

        DtoUser dto = users.stream()
                .filter(user -> user.getTckn().equals("65412547894"))
                .findFirst()
                .map(user -> {
                    DtoUser dtoUser = new DtoUser();
                    BeanUtils.copyProperties(user, dtoUser);
                    return dtoUser;
                })
                .orElse(null);

        return dto;
    }

    @Override
    public DtoUser ekleUser(DtoUserUI dtoUserUI) {

        User user = new User();
        user.setTckn(String.valueOf(tcknDogrulama.tcknDogrulama(dtoUserUI.getTckn())));
        user.setAd(dtoUserUI.getAd());
        user.setSoyad(dtoUserUI.getSoyad());
        user.setPassword(dtoUserUI.getPassword());

        User savedUser = userRepository.save(user);
        DtoUser dtoUser = new DtoUser();
        BeanUtils.copyProperties(savedUser,dtoUser);

        return dtoUser;
    }

}
