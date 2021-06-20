package com.eversec.test;

import com.eversec.dao.CustomerDao;
import com.eversec.dao.LinkManDao;
import com.eversec.domain.Customer;
import com.eversec.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneToManyTest {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;

    @Test
    @Transactional
    @Rollback(false)
    public void save() {

        Customer customer = new Customer();
        customer.setCustName("左冷禅");
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("冲虚道长");
        //关联关系
        customer.getLinkMans().add(linkMan);
        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void add() {

        Customer customer = new Customer();
        customer.setCustName("左冷禅");
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("冲虚道长");
        //关联关系
        // customer.getLinkMans().add(linkMan);
        linkMan.setCustomer(customer);
        customerDao.save(customer);
        linkManDao.save(linkMan);
    }
}
