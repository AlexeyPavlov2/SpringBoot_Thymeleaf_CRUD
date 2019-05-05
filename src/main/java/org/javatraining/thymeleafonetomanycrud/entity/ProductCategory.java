package org.javatraining.thymeleafonetomanycrud.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="product_category",uniqueConstraints={@UniqueConstraint(columnNames="name")})
@NoArgsConstructor
@Getter
@Setter
public class ProductCategory {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name", length = 50)
    private String name;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="category")
    private Set<Product> products = new HashSet<Product>(0);


}