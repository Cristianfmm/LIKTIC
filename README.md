# Prueba Técnica Backend — Microservicios de Productos e Inventario

## Descripción del Proyecto

Este proyecto implementa dos microservicios independientes usando **Spring Boot**, que se comunican entre sí para gestionar productos e inventario, permitiendo realizar compras, controlar stock y mantener la consistencia de datos.

### Arquitectura de Microservicios

- `productos-service` (puerto **8082**): gestiona la creación y consulta de productos.
- `inventario-service` (puerto **8080**): gestiona el inventario y permite realizar compras consultando productos vía HTTP.

### diagrama
https://lucid.app/lucidchart/c3e600a8-8f2b-4771-953b-4d80e7166502/edit?viewport_loc=-2520%2C-1384%2C2209%2C1023%2C0_0&invitationId=inv_4514f4e4-9eb8-4b9b-9d24-fcf54b5ac543


### flujo de compra

https://lucid.app/lucidchart/c3e600a8-8f2b-4771-953b-4d80e7166502/edit?viewport_loc=-675%2C24%2C3031%2C1403%2CADTBx_af1Q_r&invitationId=inv_4514f4e4-9eb8-4b9b-9d24-fcf54b5ac543

Comunicación entre microservicios usando `RestTemplate` con respuesta tipo JSON.

---

## Tecnologías Usadas

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- H2 Database (en memoria)
- Maven
- Postman (para pruebas)
- Git

---

##  Endpoints disponibles

### Microservicio: `productos-service` (localhost:8082)

| Método | Endpoint                          | Descripción                  |
|--------|-----------------------------------|------------------------------|
| POST   | `/api/productos`                 | Crear un producto            |
| GET    | `/api/productos`                 | Listar todos los productos   |
| GET    | `/api/productos/{id}`            | Consultar producto por ID    |

---

### Microservicio: `inventario-service` (localhost:8080)

| Método | Endpoint                                                     | Descripción                          |
|--------|--------------------------------------------------------------|--------------------------------------|
| POST   | `/api/inventario`                                            | Crear inventario inicial             |
| POST   | `/api/inventario/comprar?productoId=1&cantidad=2`            | Realizar una compra                  |
| GET    | `/api/inventario`                                            | Listar inventarios                   |
| GET    | `/api/inventario/producto/{productoId}`                      | Consultar stock por producto         |
| PUT    | `/api/inventario/producto/{productoId}?cantidad=50`          | Actualizar manualmente el stock      |

---

## Cómo ejecutar los microservicios

### 1. Clona el proyecto
```bash
git clone https://github.com/usuario/mi-proyecto-backend.git

## Rama Develop
Este cambio lo hice desde la rama develop para demostrar el proceso de Git Flow