package com.ejemplo.inventario_service.controller;

import com.ejemplo.inventario_service.model.Inventario;
import com.ejemplo.inventario_service.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    // ✅ Crear nuevo inventario
    @PostMapping
    public ResponseEntity<Inventario> crearInventario(@RequestBody Inventario inventario) {
        Inventario nuevo = inventarioService.crear(inventario);
        return ResponseEntity.ok(nuevo);
    }

    // ✅ Comprar producto (descontar inventario y consultar a productos-service)
    @PostMapping("/comprar")
    public ResponseEntity<?> comprarProducto(@RequestParam Long productoId, @RequestParam int cantidad) {
        Inventario inventario = inventarioService.obtenerPorProductoId(productoId);

        if (inventario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado en inventario");
        }

        if (inventario.getCantidad() < cantidad) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Inventario insuficiente");
        }

        // Descontar inventario
        inventario.setCantidad(inventario.getCantidad() - cantidad);
        inventarioService.crear(inventario);

        // Obtener datos del producto desde productos-service
        Map<String, Object> producto = inventarioService.obtenerProductoDesdeServicio(productoId);

        // Construir respuesta final
        Map<String, Object> respuesta = Map.of(
            "mensaje", "Compra realizada exitosamente",
            "producto", producto,
            "cantidadComprada", cantidad,
            "stockRestante", inventario.getCantidad()
        );

        return ResponseEntity.ok(respuesta);
    }

    // ✅ Consultar inventario por productoId
    @GetMapping("/producto/{productoId}")
    public Inventario obtenerPorProductoId(@PathVariable Long productoId) {
        return inventarioService.obtenerPorProductoId(productoId);
    }

    // ✅ Actualizar stock
    @PutMapping("/producto/{productoId}")
    public Inventario actualizarCantidad(@PathVariable Long productoId, @RequestParam int cantidad) {
        return inventarioService.actualizarCantidad(productoId, cantidad);
    }

    // ✅ Listar todo el inventario
    @GetMapping
    public List<Inventario> listarTodo() {
        return inventarioService.listarTodo();
    }
}
