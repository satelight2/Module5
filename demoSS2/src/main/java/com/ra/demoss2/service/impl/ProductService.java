package com.ra.demoss2.service.impl;

import com.ra.demoss2.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    List<ProductEntity> findByKeyword(String keyword, Integer batch);
}
