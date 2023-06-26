package service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dao.OauthClientMapper;
import org.springframework.stereotype.Service;
import pojo.entity.OauthClient;
import service.IOauthClientService;

/**
 * OAuth2客户端明细表 服务实现类
 */
@Service
public class OauthClientServiceImpl extends ServiceImpl<OauthClientMapper, OauthClient> implements IOauthClientService {

	@Override
	public OauthClient findById(String clientId) {
		return getOne(Wrappers.<OauthClient>lambdaQuery()
        		.eq(OauthClient::getClientId, clientId));
	}

}
