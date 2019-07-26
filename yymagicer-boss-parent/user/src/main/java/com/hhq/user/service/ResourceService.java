package com.hhq.user.service;

import com.hhq.common.base.BaseService;
import com.hhq.user.po.ResourcePO;

public interface ResourceService extends BaseService<ResourcePO> {

    ResourcePO queryByName(String resourceName);

    ResourcePO queryByResourceId(String resourceId);
}
