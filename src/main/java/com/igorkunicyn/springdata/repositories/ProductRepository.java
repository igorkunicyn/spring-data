package com.igorkunicyn.springdata.repositories;

import com.igorkunicyn.springdata.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();

    @Override
    <S extends Product> S save(S s);
    Product findProductById(long id);
    List<Product> findAllByTitleOrderByPriceAsc(String title);
    List<Product> findAllByTitleOrderByPriceDesc(String title);

}
