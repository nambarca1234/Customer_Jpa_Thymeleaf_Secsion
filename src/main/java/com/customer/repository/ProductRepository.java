package com.customer.repository;

import com.customer.model.Product;
import com.customer.model.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByCid(int pid);

    @Modifying
    @Transactional
    @Query(value = "insert into product(pid,cid,pname) values(:pid,:cid,:pname)", nativeQuery = true)
    void insertProduct(@Param("pid") int pid,@Param("cid")int cid,@Param("pname") String pname);

    @Query(value = "SELECT p.*,c.cname from product as p left join category as c on c.cid=p.cid;", nativeQuery = true)
    List<Map<String, Object>> findInfor();

    //    @Query(value = "SELECT p.*,c.cname from product as p left join category as c on c.cid=p.cid where p.pname like %?1%;", nativeQuery = true)
//    List<Map<String, Object>> findAllByPname(String pname);
    @Query(value = "SELECT p.*,c.cname from product as p left join category as c on c.cid=p.cid where p.pname like %:pname%", nativeQuery = true)
    List<Map<String, Object>> findAllByPname(@Param("pname") String pname);


    @Query(value = "SELECT p.*,c.cname from product as p, category as c where c.cid=p.cid and p.pid=?1 and c.cid=?2", nativeQuery = true)
    List<Map<String, Object>> findProductDtoBy(int pid, int cid);

    @Query(value = "SELECT p.*,c.cname from product as p, category as c where c.cid=p.pid and p.pid=:pid and c.cid=:cid", nativeQuery = true)
    List<Map<String, Object>> findProductDtoBy2(@Param("pid") int pid, @Param("cid") int cid);

}
