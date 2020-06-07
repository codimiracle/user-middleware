package com.codimiracle.web.middleware.user.service.impl;

import com.codimiracle.web.middleware.user.pojo.po.UserAccountPart;
import com.codimiracle.web.middleware.user.pojo.vo.UserAccountVO;
import com.codimiracle.web.middleware.user.service.AccountService;
import com.codimiracle.web.mybatis.contract.AbstractService;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * account service for money system
 *
 * @author Codimiracle
 */
@Service
@Transactional
public class AccountServiceImpl extends AbstractService<String, UserAccountPart> implements AccountService {
    @Override
    public void save(UserAccountPart model) {
        model.setCreatedAt(new Date());
        model.setUpdatedAt(model.getCreatedAt());
        super.save(model);
    }

    @Override
    public List<UserAccountPart> findByUserId(String userId) {
        Condition condition = new Condition(UserAccountPart.class);
        condition.createCriteria().andEqualTo("userId", userId);
        return findByCondition(condition);
    }

    @Override
    public UserAccountVO findByUserIdForBalance(String userId) {
        List<UserAccountPart> parts = findByUserId(userId);
        UserAccountVO userAccountVO = new UserAccountVO();
        userAccountVO.setUserId(userId);
        userAccountVO.setBalance(parts.stream().map(UserAccountPart::getBalance).reduce(Money::plus).orElse(Money.of(CurrencyUnit.of(Locale.getDefault()), BigDecimal.ZERO)));
        return userAccountVO;
    }
}
