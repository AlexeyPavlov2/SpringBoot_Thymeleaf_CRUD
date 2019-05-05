package org.javatraining.thymeleafonetomanycrud.repository;

import org.javatraining.thymeleafonetomanycrud.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

}
