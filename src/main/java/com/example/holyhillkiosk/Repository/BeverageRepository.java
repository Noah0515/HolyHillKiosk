package com.example.holyhillkiosk.Repository;

import com.example.holyhillkiosk.Entity.Beverage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeverageRepository extends CrudRepository<Beverage, Integer> {
    @Query(value = """
            select beveragename, sum(beveragenum)
            from beverage, orderedbeverage
            where beverage.beveragecode = orderedbeverage.beveragecode
            group by beverage.beveragecode
            """, nativeQuery = true)
    List<Object[]> findBeverageNameAndSum();

}
