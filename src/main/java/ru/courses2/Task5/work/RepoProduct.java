package ru.courses2.Task5.work;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoProduct extends CrudRepository<Product,Integer> {
    Product findFirstByNumber(String number);

    Product findFirstById(Integer instanceId);

    @Query("Select p from Product p left join ProductClass c on p.classCode=c where p.id = ?1 and ?2 like concat(c.value,'%')")
    Product findFirstByIdAndClassCode(int id1, String classCode);
}

