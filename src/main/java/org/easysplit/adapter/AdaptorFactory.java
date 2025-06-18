package org.easysplit.adapter;

import org.easysplit.dto.BaseDTO;
import org.easysplit.dto.UserInfo;

public class AdaptorFactory {
    public static BaseAdaptor createAdaptor(BaseDTO baseDTO) {
        if (baseDTO instanceof UserInfo) {
            return new UserAdaptor();
        }
        return null;
    }
}
