package com.codimiracle.web.middleware.user.mapper;

import com.codimiracle.web.middleware.user.pojo.po.Authority;
import com.codimiracle.web.middleware.user.pojo.vo.AuthorityVO;
import com.codimiracle.web.mybatis.contract.support.vo.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface AuthorityMapper extends Mapper<Authority, AuthorityVO> {

}
