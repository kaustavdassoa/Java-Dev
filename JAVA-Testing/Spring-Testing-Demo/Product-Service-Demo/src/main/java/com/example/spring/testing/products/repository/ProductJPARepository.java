package com.example.spring.testing.products.repository;

import com.example.spring.testing.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJPARepository extends JpaRepository<Product,Integer> {
}
