package org.easysplit.adapter;

import org.easysplit.dto.BaseDTO;
import org.easysplit.dto.UserInfo;
import org.easysplit.entitites.BaseEntity;
import org.easysplit.entitites.User;

public class UserAdaptor extends BaseAdaptor {

    @Override
    public UserInfo convertToDTO(BaseEntity entity) {
        User user = (User) entity;
        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setUserName(user.getUserName());
        userInfo.setFirstName(user.getFirstName());
        userInfo.setLastName(user.getLastName());
        userInfo.setEmail(user.getEmailId());
        userInfo.setPhoneNumber(user.getPhone());
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
        user.setEmailId(userInfo.getEmail());
        user.setPhone(userInfo.getPhoneNumber());
        return user;
    }
}
