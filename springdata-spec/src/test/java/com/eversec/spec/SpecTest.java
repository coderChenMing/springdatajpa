package com.eversec.spec;

import com.eversec.dao.CustomerDao;
import com.eversec.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecTest {
    @Autowired
    private CustomerDao customerDao;

    @Test
    public void findOne() {
        Specification<Customer> spec = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                /*
                 * 构造查询条件：
                 * root:获取需要查询的对象属性
                 * CriteriaQuery：构造查询条件，内部封装了很多的查询条件
                 * CriteriaBuilder：查询构造器
                 * 查询条件：
                 * 1.查询方式：cb对象中
                 * 2.比骄属性的名称：root对象中
                 *
                 *  * */
                // 1.获取查询属性
                Path<Object> custName = root.get("custName");
                //2.构造查询条件
                Predicate predicate = cb.equal(custName, "林平之");//精准匹配
                return predicate;
            }
        };
        Customer cus = customerDao.findOne(spec);
        System.out.println(cus);
    }

    /*根据客户名和所属行业进行查询*/
    @Test
    public void testSpecs() {
        Specification<Customer> spec = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");
                Predicate p1 = cb.equal(custName, "林平之");
                Predicate p2 = cb.equal(custIndustry, "武林中人");
                // 组合
                Predicate and = cb.and(p1, p2);
                return and;
            }
        };
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

    @Test
    public void testLike() {
        Specification<Customer> spec = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                // 模糊查询
                Predicate like = cb.like(custName.as(String.class),"令狐冲%" );
                return like;
            }
        };
        /*List<Customer> list= customerDao.findAll(spec);
        for (Customer customer : list) {
            System.out.println(customer);
        }*/
        /*排序*/
        /*按照id降序显示*/
        Sort sort = new Sort(Sort.Direction.DESC,"custId");
        List<Customer> list = customerDao.findAll(sort);
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    /*
    * 分页查询:
    * 1.specification,pageable:条件加分页
    * 2.pageable：分页
    * PageRequest是 Pageable的实现类
    * */
    @Test
    public void selectByPage() {
        Pageable pageable = new PageRequest(0,10);
        Page<Customer> page = customerDao.findAll(null, pageable);
        List<Customer> content = page.getContent();
        for (Customer customer : content) {
            System.out.println(customer);
        }
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());

    }
}
