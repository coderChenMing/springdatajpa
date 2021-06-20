package com.eversec.dao;

import com.eversec.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/*
 * dao的实现：定义dao接口，继承JpaRepository,JpaSpecificationExecutor
 * JpaRepository<操作的实体类型，实体类中主键属性类型>:封装了crud操作
 * JpaSpecificationExecutor<操作的实体类型>：封装了复杂操作
 * */
public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    /*复杂查询
     * 1.定义方法
     * 2.加query注解标注sql
     * */
    @Query(value = "from Customer  where custName = ?")
    public Customer findByName(String custNamer);

    @Query(value = "from Customer  where custName = ?1 and custId= ?2")
    public Customer findByNameAndId(String name, long id);

    @Query(value = "from Customer  where custName = ?2 and custId= ?1")
    public Customer findByNameAndId(long id, String name);

    @Query(value = "update Customer  set custName = ?2   where  custId= ?1")
    @Modifying
    public int updateNameById(long id, String name);
}
