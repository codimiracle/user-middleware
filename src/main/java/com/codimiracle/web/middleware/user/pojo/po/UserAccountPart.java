package com.codimiracle.web.middleware.user.pojo.po;

import com.codimiracle.web.middleware.user.typehandler.MoneyTypeHandler;
import lombok.Data;
import org.joda.money.Money;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.*;
import java.util.Date;

/**
 * for optimizing line lock for MySQL, it tolerances multi-record.
 *
 * @author Codimiracle
 */
@Data
@Table(name = "user_accounts")
public class UserAccountPart {
    public static final String TYPE_CURRENCY = "currency";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "balance")
    @ColumnType(typeHandler = MoneyTypeHandler.class)
    private Money balance;

    @Column(name = "user_id")
    private String userId;

    /**
     * 账户类型
     */
    @Column(name = "type")
    private String type;

    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

    @Transient
    private UserMetadata user;

}
