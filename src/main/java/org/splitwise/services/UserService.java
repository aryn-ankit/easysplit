package org.splitwise.services;


import org.splitwise.dto.UserInfo;
import org.splitwise.entitites.User;

import java.util.List;

public interface UserService {

    public UserInfo getDefaultUser();
    public UserInfo registerUser(UserInfo user) throws Exception;
    public UserInfo getUser(String userName) throws Exception;
    public List<UserInfo> getAllUsers() throws Exception;
    public UserInfo updateUser(UserInfo userInfo) throws Exception;
}
