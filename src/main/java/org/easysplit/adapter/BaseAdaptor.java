package org.easysplit.adapter;

import org.easysplit.dto.BaseDTO;
import org.easysplit.entitites.BaseEntity;

public abstract class BaseAdaptor {
    public abstract BaseDTO convertToDTO(BaseEntity entity);

    public abstract BaseEntity convertToEntity(BaseDTO baseDTO);
}
