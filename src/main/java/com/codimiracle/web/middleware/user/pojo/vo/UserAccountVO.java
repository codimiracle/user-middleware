package com.codimiracle.web.middleware.user.pojo.vo;

import lombok.Data;
import org.joda.money.Money;

@Data
public class UserAccountVO {
    private String userId;
    private Money balance;
}
