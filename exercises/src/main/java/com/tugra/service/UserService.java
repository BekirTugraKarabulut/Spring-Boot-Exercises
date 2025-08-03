package com.tugra.service;

import com.tugra.dto.DtoUser;
import com.tugra.dto.DtoUserUI;

import java.util.List;

public interface UserService {

    public List<DtoUser> getUsers();

    public DtoUser getUserByAd(String ad);

    public DtoUser ekleUser(DtoUserUI dtoUserUI);

}
