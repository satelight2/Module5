package com.ra.demoss2.service.impl.impl;

import com.ra.demoss2.entity.ProductEntity;
import com.ra.demoss2.repository.ProductRepository;
import com.ra.demoss2.service.impl.ProductService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service

public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductEntity> findByKeyword(String keyword, Integer batch) {
        return productRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Predicate searchKey = null;
            Predicate andPredicate = null;
            if (keyword != null) {
                searchKey = criteriaBuilder.or(
                        criteriaBuilder.like(root.get("productId"), "%"+keyword+"%"),
                        criteriaBuilder.like(root.get("productName"), "%"+keyword+"%"),
                        criteriaBuilder.like(root.get("manufacturer"), "%"+keyword+"%")
                );
            }
            if (batch != null) {
                andPredicate = criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("batch"), batch)
                );
            }
            if (searchKey != null)
                predicates.add(searchKey);
            if (andPredicate != null)
                predicates.add(andPredicate);
            return criteriaBuilder.and(predicates.toArray(new Predicate [0]));
        });
    }
}
