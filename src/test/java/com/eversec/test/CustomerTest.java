package com.eversec.test;

import com.eversec.dao.CustomerDao;
import com.eversec.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)/*声明spring单元测试环境*/
@ContextConfiguration(locations = "classpath:applicationContext.xml")/*指定配置文件位置*/
public class CustomerTest {
    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindOne() {
        Customer customer = customerDao.findOne(10l);
        System.out.println(customer);
    }

    /* save:保存或者更新
     *  有id属性，根据id查询并更新
     *  无id属性，保存
     *
     * */
    @Test
    public void testSave() {
        Customer customer = new Customer();
        customer.setCustAddress("河北秦皇岛");
        customer.setCustLevel("欣然用户");
        customer.setCustName("张无忌");
        customerDao.save(customer);
    }

    @Test
    public void testUpdate() {
        Customer customer = new Customer();
        customer.setCustId(8l);
        customer.setCustName("林平之");
        customerDao.save(customer);
    }

    /*删除*/
    @Test
    public void testDelete() {

        customerDao.delete(6l);
    }

    /*查询所有*/
    @Test
    public void testFindAll() {

        List<Customer> all = customerDao.findAll();
        for (Customer customer : all) {
            System.out.println(customer);
        }
    }

    /*统计查询*/
    @Test
    public void testCount() {
        System.out.println(customerDao.count());
    }

    /*是否存在：
     * 1.查询id对应用户是否存在，null表示不存在
     * 2.查询id对应用户数量是否>0
     * */
    @Test
    public void testExists() {
        System.out.println(customerDao.exists(6l));
    }

    /*根据id进行数据库查询
      findOne:em.find 立即执行sql
      getOne:em.getReference,延时执行sql
    * Transactional:保证事务正常运行
    * */
    @Test
    @Transactional
    public void getOne() {
        System.out.println(customerDao.getOne(4l));
    }
}
