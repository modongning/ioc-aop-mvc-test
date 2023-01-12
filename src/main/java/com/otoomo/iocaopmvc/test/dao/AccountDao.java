package com.otoomo.iocaopmvc.test.dao;

import com.otoomo.iocaopmvc.test.pojo.Account;

import java.sql.SQLException;
import java.util.List;

/**
 */
public interface AccountDao {

    Account queryAccountByCardNo(String cardNo) throws Exception;

    int updateAccountByCardNo(Account account) throws Exception;
}
