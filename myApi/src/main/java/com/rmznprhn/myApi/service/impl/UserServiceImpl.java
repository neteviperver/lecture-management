package com.rmznprhn.myApi.service.impl;

import com.rmznprhn.myApi.common.GeneralException;
import com.rmznprhn.myApi.entity.User;
import com.rmznprhn.myApi.entity.enums.Role;
import com.rmznprhn.myApi.repository.IUserRepository;
import com.rmznprhn.myApi.service.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUserByRole(Role role) {
        return userRepository.findAllByRole(role);

    }

    @Override
    public List<User> getPotentialUsers(List<Integer> ids) {
        if (ids.isEmpty()){
            return getUserByRole(Role.STUDENT);
        }
        return userRepository.findAllByRoleAndIdIsNotIn(Role.STUDENT, ids);
    }

    @Override
    public User save(User user) {

        if (user.getId() == null) {
            if (user.getIdentityNo() == null||user.getIdentityNo().length()==11) {
                throw new GeneralException("invalid identity no!");

            }
            if (userRepository.existsByIdentityNo(user.getIdentityNo())){
                throw new GeneralException("user already exists!");
            }

        }
        return userRepository.save(user);
    }

    @Override
    public User getById(Integer id) {

        return  userRepository.findById(id).orElseThrow(() -> new GeneralException("user not found"));
    }

    @Override
    public List<User> getAll() {

        return userRepository.findAll();
    }

    @Override
    public Page<User> getAll(Pageable pageable) {

        return userRepository.findAll(pageable);
    }

    @Override
    public void delete(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new GeneralException("user not found!");
        }
        userRepository.deleteById(id);
    }
}
