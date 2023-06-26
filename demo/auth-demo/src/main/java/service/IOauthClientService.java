package service;

import com.baomidou.mybatisplus.extension.service.IService;
import pojo.entity.OauthClient;

/**
 * OAuth2客户端明细表 服务类
 */
public interface IOauthClientService extends IService<OauthClient> {

	OauthClient findById(String clientId);
	
}
