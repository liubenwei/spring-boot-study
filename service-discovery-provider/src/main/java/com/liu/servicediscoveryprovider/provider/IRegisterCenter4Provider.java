package com.liu.servicediscoveryprovider.provider;

import com.liu.servicediscoveryprovider.Service.ProviderService;

import java.util.List;
import java.util.Map;

public interface IRegisterCenter4Provider {
    /**
     * 服务器将服务提供者的信息注册到zk对应的节点下
     */
    public void registerProvider(List<ProviderService> serviceMetaData);

    /**
     * 服务端获取服务提供者信息
     */

    public Map<String, List<ProviderService>> getProviderServiceMap();


}
