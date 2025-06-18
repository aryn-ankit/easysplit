package org.easysplit.services;


import org.easysplit.dto.UserInfo;

import java.util.List;

public interface UserService {

    public UserInfo getDefaultUser();
    public UserInfo registerUser(UserInfo user) throws Exception;
    public UserInfo getUser(int userID) throws Exception;
    public List<UserInfo> getAllUsers() throws Exception;
    public UserInfo updateUser(UserInfo userInfo) throws Exception;
}
