package com.rmznprhn.myApi.service;

import com.rmznprhn.myApi.entity.User;
import com.rmznprhn.myApi.entity.enums.Role;

import java.util.List;

public interface IUserService extends IService<User> {

    List<User> getUserByRole(Role role);

    List<User> getPotentialUsers(List<Integer> ids);

}
