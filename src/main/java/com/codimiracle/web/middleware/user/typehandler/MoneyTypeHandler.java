package com.codimiracle.web.middleware.user.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class MoneyTypeHandler extends BaseTypeHandler<Money> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Money money, JdbcType jdbcType) throws SQLException {
        preparedStatement.setLong(i, money.getAmountMinorLong());
    }

    @Override
    public Money getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return Money.ofMinor(CurrencyUnit.of(Locale.getDefault()), resultSet.getLong(s));
    }

    @Override
    public Money getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return Money.ofMinor(CurrencyUnit.of(Locale.getDefault()), resultSet.getLong(i));
    }

    @Override
    public Money getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return Money.ofMinor(CurrencyUnit.of(Locale.getDefault()), callableStatement.getLong(i));
    }
}
