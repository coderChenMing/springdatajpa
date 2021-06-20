package com.eversec.dao;

import com.eversec.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.security.PublicKey;
import java.util.List;

/*
 * dao的实现：定义dao接口，继承JpaRepository,JpaSpecificationExecutor
 * JpaRepository<操作的实体类型，实体类中主键属性类型>:封装了crud操作
 * JpaSpecificationExecutor<操作的实体类型>：封装了复杂操作
 * */
public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    /*
     * jpa支持三种查询
     * 1.生成的simpleJpaRepository代理对象默认方法的查询
     * 2.自定义Jpql语句查询：jpql就是查询实体属性
     * 3.定义sql查询
     * 4.根据属性名称进行查询:不用@Query注解
     *   条件：findBy开头+属性首字母大写+(查询方式like|isNull)
     * */
    /* 2.自定义Jpql语句查询
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

    /*定义sql查询
     * nativeQuery必须设置：ture:jpql查询 false:sql语句查询
     * 把每个属性视为一个Object对象，则一行数据就是一个object[]
     * */
    @Query(value = "select * from cst_customer", nativeQuery = true)
    public List<Object[]> selectAll();

    @Query(value = "select * from cst_customer where cust_name like ?1", nativeQuery = true)
    public List<Object[]> selectByName(String name);

    /*4.根据属性名称进行查询*/
    public Customer findByCustName(String custName);

    public List<Customer> findByCustNameLike(String custName);

    public List<Customer> findByCustNameLikeAndCustIndustry(String custName,String custIndustry);
}
