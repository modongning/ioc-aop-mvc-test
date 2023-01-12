package com.otoomo.iocaopmvc.test.service.impl;

import com.otoomo.ioc.annotation.Autowired;
import com.otoomo.ioc.annotation.Service;
import com.otoomo.iocaopmvc.test.dao.AccountDao;
import com.otoomo.iocaopmvc.test.pojo.Account;
import com.otoomo.iocaopmvc.test.service.TransferService;
import com.otoomo.jdbc.annotation.Transactional;

@Service
public class TransferServiceImpl implements TransferService {
    @Autowired
    private AccountDao accountDao;

    @Override
    @Transactional
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {

        Account from = accountDao.queryAccountByCardNo(fromCardNo);
        Account to = accountDao.queryAccountByCardNo(toCardNo);

        from.setMoney(from.getMoney() - money);
        to.setMoney(to.getMoney() + money);

        accountDao.updateAccountByCardNo(to);
//        int c = 1 / 0; //测试报错事务回滚
        accountDao.updateAccountByCardNo(from);
    }
}
