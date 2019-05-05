package org.javatraining.thymeleafonetomanycrud.repository;

import org.javatraining.thymeleafonetomanycrud.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
