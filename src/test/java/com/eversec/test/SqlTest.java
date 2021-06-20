package com.eversec.test;

import com.eversec.dao.CustomerDao;
import com.eversec.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SqlTest {
    @Autowired
    private CustomerDao customerDao;

    @Test
    public void findAll() {
        List<Object[]> list = customerDao.selectAll();
        for (Object[] objectArray : list) {
            System.out.println(Arrays.toString(objectArray));

        }
    }

    @Test
    public void selectByName() {
        List<Object[]> list = customerDao.selectByName("令狐冲%");
        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void findByName() {
        Customer cust = customerDao.findByCustName("林平之");
        System.out.println(cust);

    }

    @Test
    public void findByNameLike() {

        List<Customer> customers = customerDao.findByCustNameLike("令狐冲%");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    @Test
    public void findByNameLikeAndIndustry() {

        List<Customer> customers = customerDao.findByCustNameLikeAndCustIndustry("令狐冲%","武林中人");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}
