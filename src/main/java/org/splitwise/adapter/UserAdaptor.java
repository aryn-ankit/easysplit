package org.splitwise.adapter;

import org.splitwise.dto.BaseDTO;
import org.splitwise.dto.UserInfo;
import org.splitwise.entitites.BaseEntity;
import org.splitwise.entitites.User;

public class UserAdaptor extends BaseAdaptor {

    @Override
    public UserInfo convertToDTO(BaseEntity entity) {
        User user = (User) entity;
        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setUserName(user.getUserName());
        userInfo.setFirstName(user.getFirstName());
        userInfo.setLastName(user.getLastName());
        userInfo.setEmailId(user.getEmailId());
        userInfo.setContact(user.getContact());
        return userInfo;
    }

    @Override
    public User convertToEntity(BaseDTO baseDTO) {
        UserInfo userInfo = (UserInfo) baseDTO;
        User user = new User();
        user.setId(userInfo.getId());
        user.setUserName(userInfo.getUserName());
        user.setFirstName(userInfo.getFirstName());
        user.setLastName(userInfo.getLastName());
        user.setEmailId(userInfo.getEmailId());
        user.setContact(userInfo.getContact());
        return user;
    }
}
