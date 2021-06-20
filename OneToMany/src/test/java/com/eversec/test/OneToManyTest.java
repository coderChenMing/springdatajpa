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

    /*综合比较save和add两个方法
    * save比add多Hibernate: insert into cst_linkman (lkm_cust_id, lkm_email, lkm_gender, lkm_memo, lkm_name, lkm_phone, lkm_position, lkm_mobile) values (?, ?, ?, ?, ?, ?, ?, ?)
     多了一条sql，在多的一方维护外键关系比一的一方要好，所以去掉一的一方外键维护
     * */
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

    /*级联测试
      主表保存，从表保存
      主表删除，从表删除
      需要在操作主体配置cascade:操作主体就是主表一的一方
    * */
    @Test
    @Transactional
    @Rollback(false)
    public void cascadeTest() {
        Customer customer = new Customer();
        customer.setCustName("左冷禅");
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("冲虚道长");
        //关联关系
        linkMan.setCustomer(customer);
        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    /*级联删除测试*/
    @Test
    @Transactional
    @Rollback(false)
    public void cascadeDelTest() {
        customerDao.delete(1l);
    }
}
