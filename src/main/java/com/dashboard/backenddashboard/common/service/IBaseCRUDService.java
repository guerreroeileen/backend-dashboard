package com.dashboard.backenddashboard.common.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface IBaseCRUDService < D,I >
{
    Page<D> findAll(Integer page, Integer size, Boolean enablePagination);

    D findById(I id);

    D save(D object);

    D update(D object, I id);

    void delete(I object);
}
