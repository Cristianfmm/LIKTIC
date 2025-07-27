package com.example.productos_service.service;


import com.example.productos_service.model.Producto;
import com.example.productos_service.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repo;

    public Producto crear(Producto p) {
        return repo.save(p);
    }

    public Producto obtener(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Producto> listar() {
        return repo.findAll();
    }
}
