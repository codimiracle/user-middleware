package com.codimiracle.web.middleware.user.service.impl;

import com.codimiracle.web.middleware.user.TestSpringApplication;
import com.codimiracle.web.middleware.user.pojo.po.UserAccountPart;
import com.codimiracle.web.middleware.user.pojo.vo.UserAccountVO;
import com.codimiracle.web.middleware.user.service.AccountService;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceImplTest {
    @Resource
    private AccountService accountService;

    @Test
    void findByUserId() {
        final String userId = "10320";
        UserAccountPart accountPart = new UserAccountPart();
        accountPart.setType(UserAccountPart.TYPE_CURRENCY);
        accountPart.setBalance(Money.of(CurrencyUnit.of(Locale.getDefault()), 0));
        accountPart.setUserId(userId);
        accountService.save(accountPart);
        List<UserAccountPart> accountParts = accountService.findByUserId(userId);

        // check result
        assertTrue(accountParts.size() > 0);
        UserAccountPart result = accountParts.get(0);
        assertEquals(accountPart.getType(), result.getType());
        assertEquals(accountPart.getBalance(), result.getBalance());
        assertEquals(accountPart.getUserId(), result.getUserId());
        assertNotNull(accountPart.getCreatedAt());
        assertNotNull(accountPart.getUpdatedAt());
    }

    @Test
    void findByUserIdForBalance() {
        final String userId = "100111";
        UserAccountPart accountPart = new UserAccountPart();
        accountPart.setType(UserAccountPart.TYPE_CURRENCY);
        accountPart.setBalance(Money.ofMinor(CurrencyUnit.of(Locale.getDefault()), 0));
        accountPart.setUserId(userId);
        accountService.save(accountPart);
        accountPart.setBalance(Money.ofMinor(CurrencyUnit.of(Locale.getDefault()), 100));
        accountService.update(accountPart);

        // should be 100
        UserAccountVO userAccountVO = accountService.findByUserIdForBalance(userId);
        assertEquals(userId, userAccountVO.getUserId());
        assertEquals(Money.ofMinor(CurrencyUnit.of(Locale.getDefault()), 100), userAccountVO.getBalance());
    }
}