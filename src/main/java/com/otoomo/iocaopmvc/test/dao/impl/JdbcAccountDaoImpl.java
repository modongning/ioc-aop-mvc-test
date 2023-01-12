package com.otoomo.iocaopmvc.test.dao.impl;

import com.otoomo.ioc.annotation.Autowired;
import com.otoomo.ioc.annotation.Service;
import com.otoomo.iocaopmvc.test.dao.AccountDao;
import com.otoomo.iocaopmvc.test.pojo.Account;
import com.otoomo.iocaopmvc.test.service.TransferService;
import com.otoomo.jdbc.ConnectionUtils;
import com.otoomo.jdbc.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 */
@Service
public class JdbcAccountDaoImpl implements AccountDao {

    //测试循环依赖
    @Autowired
    private TransferService transferService;

    @Override
    public Account queryAccountByCardNo(String cardNo) throws Exception {
        Connection con = ConnectionUtils.getCurrentThreadConn();
        String sql = "select * from account where cardNo=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, cardNo);
        ResultSet resultSet = preparedStatement.executeQuery();

        Account account = new Account();
        while (resultSet.next()) {
            account.setCardNo(resultSet.getString("cardNo"));
            account.setName(resultSet.getString("name"));
            account.setMoney(resultSet.getInt("money"));
        }

        resultSet.close();
        preparedStatement.close();

        return account;
    }

    @Override
    @Transactional
    public int updateAccountByCardNo(Account account) throws Exception {
        Connection con = ConnectionUtils.getCurrentThreadConn();
        String sql = "update account set money=? where cardNo=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, account.getMoney());
        preparedStatement.setString(2, account.getCardNo());
        int i = preparedStatement.executeUpdate();

        preparedStatement.close();
        return i;
    }
}
