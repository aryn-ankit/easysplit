package org.splitwise.adapter;

import org.splitwise.dto.BaseDTO;
import org.splitwise.dto.UserInfo;
import org.splitwise.entitites.BaseEntity;

public abstract class BaseAdaptor {
    public abstract BaseDTO convertToDTO(BaseEntity entity);

    public abstract BaseEntity convertToEntity(BaseDTO baseDTO);
}
