package org.splitwise.services.impl;

import org.splitwise.adapter.BaseAdaptor;
import org.splitwise.adapter.AdaptorFactory;
import org.splitwise.dto.UserInfo;
import org.splitwise.entitites.User;
import org.splitwise.repositories.UserRepository;
import org.splitwise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private BaseAdaptor adaptor;

    @Override
    public UserInfo getDefaultUser() {
        return new UserInfo(0,
                "userName",
                "firstName",
                "lastName",
                "email",
                "contact");
    }

    @Override
    public UserInfo registerUser(UserInfo userInfo) throws Exception {
        if (userRepository.findByUserName(userInfo.getUserName()) != null) {
            throw new Exception("User with username : " + userInfo.getUserName() + "already exists.");
        }
        User user = (User) getAdaptor().convertToEntity(userInfo);

        return (UserInfo) getAdaptor().convertToDTO(userRepository.save(user));
    }

    @Override
    public UserInfo getUser(String userName) throws Exception {
        User user = userRepository.findByUserName(userName);

        if (user == null) {
            throw new Exception("No user found for username : " + userName);
        }

        return (UserInfo) getAdaptor().convertToDTO(user);
    }

    @Override
    public List<UserInfo> getAllUsers() throws Exception {
        List<User> allUserEntities = userRepository.findAll();
        return allUserEntities.stream()
                .map(user -> (UserInfo) getAdaptor().convertToDTO(user))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public UserInfo updateUser(UserInfo userInfo) throws Exception {
        UserInfo oldUserInfo = getUser(userInfo.getUserName());
        validate(userInfo, oldUserInfo);

        return (UserInfo) getAdaptor().convertToDTO(userRepository.save((User) getAdaptor().convertToEntity(userInfo)));
    }

    private void validate(UserInfo user, UserInfo oldUser) throws Exception {
        if (!oldUser.getFirstName().equals(user.getFirstName()) || !oldUser.getLastName().equals(user.getLastName())) {
            throw new Exception("Cannot update user's name.");
        }
    }


    public BaseAdaptor getAdaptor() {
        if (adaptor == null) {
            adaptor = AdaptorFactory.createAdaptor(new UserInfo());
        }
        return adaptor;
    }
}
