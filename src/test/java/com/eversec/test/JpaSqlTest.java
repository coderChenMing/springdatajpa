package com.eversec.test;

import com.eversec.dao.CustomerDao;
import com.eversec.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpaSqlTest {
    @Autowired
    private CustomerDao customerDao;

    @Test
    public void selectByName() {
        Customer customer = customerDao.findByName("林平之");
        System.out.println(customer);
    }

    @Test
    public void selectByNameAndId() {
        //Customer customer = customerDao.findByNameAndId("林平之",8l);
        Customer customer = customerDao.findByNameAndId(8l,"林平之");
        System.out.println(customer);
    }
    /*Transactional:自定义的更新和删除必须添加
    springdatajpa 完成更新和删除需要手动添加事务，且其默认自动回滚事务
    * */
    @Test
    @Transactional
    @Rollback(value=false)/*设置不回滚*/
    public void updateNameById() {
        //Customer customer = customerDao.findByNameAndId("林平之",8l);
        int count = customerDao.updateNameById(9l, "岳不群");
        System.out.println(count);
        Customer customer = customerDao.findByNameAndId(9l, "岳不群");
        System.out.println(customer);
    }

}
