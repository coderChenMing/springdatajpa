package com.eversec.test;

import com.eversec.dao.CustomerDao;
import com.eversec.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
}
