package com.ejemplo.inventario_service.service;

import com.ejemplo.inventario_service.model.Inventario;
import com.ejemplo.inventario_service.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    private final String PRODUCTOS_SERVICE_URL = "http://localhost:8082/api/productos";

    private RestTemplate restTemplate = new RestTemplate();

    public Inventario crear(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public Inventario obtenerPorProductoId(Long productoId) {
        return inventarioRepository.findByProductoId(productoId);
    }

    public Inventario actualizarCantidad(Long productoId, int nuevaCantidad) {
        Inventario inventario = inventarioRepository.findByProductoId(productoId);
        if (inventario != null) {
            inventario.setCantidad(nuevaCantidad);
            return inventarioRepository.save(inventario);
        }
        return null;
    }

    public List<Inventario> listarTodo() {
        return inventarioRepository.findAll();
    }

    // 🚀 Método para obtener los datos del producto desde productos-service
    public Map<String, Object> obtenerProductoDesdeServicio(Long productoId) {
        String url = PRODUCTOS_SERVICE_URL + "/" + productoId;
        return restTemplate.getForObject(url, Map.class);
    }
}
