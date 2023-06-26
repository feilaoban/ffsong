package pojo.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * OAuth2客户端明细表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="OauthClient对象", description="OAuth2客户端明细表")
public class OauthClient extends Model<OauthClient> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "客户端id")
    private String clientId;

    @ApiModelProperty(value = "授权可以访问的资源服务器集合")
    private String resourceIds;

    @ApiModelProperty(value = "客户端密钥")
    private String clientSecret;

    @ApiModelProperty(value = "作用域")
    private String scope;

    @ApiModelProperty(value = "授权方式")
    private String authorizedGrantTypes;

    @ApiModelProperty(value = "重定向uri")
    private String webServerRedirectUri;

    @ApiModelProperty(value = "授权")
    private String authorities;

    @ApiModelProperty(value = "token有效时长(s)")
    private Integer accessTokenValidity;

    @ApiModelProperty(value = "r-token有效时长(s)")
    private Integer refreshTokenValidity;

    private String additionalInformation;

    private String autoapprove;
    
    @ApiModelProperty(value = "租户id")
    private Long tenantId;
    
    @ApiModelProperty(value = "机构级别（1:租户,2:公司）")
    private Integer orgLevel;
    
    @ApiModelProperty(value = "公司id")
    private Long companyId;
    
    @ApiModelProperty(value = "是否删除（1：删除 0：正常）")
    private Integer deleteFlag;
    
    @ApiModelProperty(value = "创建用户ID")
    private Long creatorId;
    
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;
    
    @ApiModelProperty(value = "更新用户ID")
    private Long modifiederId;
    
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime gmtModified;


    @Override
    protected Serializable pkVal() {
        return this.clientId;
    }

}
