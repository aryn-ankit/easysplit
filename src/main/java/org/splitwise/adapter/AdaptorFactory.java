package org.splitwise.adapter;

import org.splitwise.dto.BaseDTO;
import org.splitwise.dto.UserInfo;

public class AdaptorFactory {
    public static BaseAdaptor createAdaptor(BaseDTO baseDTO) {
        if (baseDTO instanceof UserInfo) {
            return new UserAdaptor();
        }
        return null;
    }
}
