package org.easysplit.services.impl;

import org.easysplit.adapter.BaseAdaptor;
import org.easysplit.adapter.AdaptorFactory;
import org.easysplit.dto.UserInfo;
import org.easysplit.entitites.User;
import org.easysplit.repositories.UserRepository;
import org.easysplit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (userInfo.getId() != 0 && userRepository.findById(userInfo.getId()).isPresent()) {
            throw new Exception("User with id : " + userInfo.getId() + "already exists.");
        }

        if (userRepository.findByPhone(userInfo.getPhoneNumber()) != null) {
            throw new Exception("User with identifier: " + userInfo.getPhoneNumber() + " already exists.");
        }

        User user = (User) getAdaptor().convertToEntity(userInfo);

        return (UserInfo) getAdaptor().convertToDTO(userRepository.save(user));
    }

    @Override
    public UserInfo getUser(int userID) throws Exception {
        User user = userRepository.findById(userID).orElseThrow(() -> new RuntimeException("No user found for id : " + userID));
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
        UserInfo oldUserInfo = getUser(userInfo.getId());
        validate(userInfo, oldUserInfo);

        return (UserInfo) getAdaptor().convertToDTO(userRepository.save((User) getAdaptor().convertToEntity(userInfo)));
    }

    private void validate(UserInfo user, UserInfo oldUser) throws Exception {
        if (!oldUser.getPhoneNumber().equals(user.getPhoneNumber())) {
            throw new Exception("Cannot update user's phone number.");
        }
    }


    public BaseAdaptor getAdaptor() {
        if (adaptor == null) {
            adaptor = AdaptorFactory.createAdaptor(new UserInfo());
        }
        return adaptor;
    }
}
