# POS Restaurante Spring Boot

Sistema de punto de venta (POS) para restaurante desarrollado con Spring Boot, diseñado para gestionar operaciones de caja, inventario y ventas.

## Descripción

Aplicación empresarial de punto de venta construida con el stack moderno de Java. Permite a los cajeros procesar ventas, generar recibos en PDF, gestionar categorías de productos y mantener un historial de transacciones. La arquitectura es segura, escalable y lista para producción con autenticación JWT y base de datos relacional robusta.

## Stack Tecnológico

### ☕ Lenguaje y Plataforma
- **Java 21 LTS**: Versión de soporte a largo plazo con mejoras en Records y manejo de hilos

### 🌱 Framework Principal
- **Spring Boot 3.3.x**: Configuración automática, inyección de dependencias
  - **Spring Web**: Controladores REST y manejo HTTP
  - **Spring Data JPA**: Acceso a datos con interfaces, SQL automático
  - **Spring Security**: Autenticación, autorización y gestión de permisos
  - **Spring Boot DevTools**: Recarga automática en desarrollo

### 🗄️ Base de Datos
- **PostgreSQL 16**: Motor relacional open source, transacciones ACID, concurrencia
- **Hibernate**: ORM integrado en Spring Data JPA, mapeo objeto-relacional
- **Flyway**: Migraciones de base de datos versionadas (V1__schema.sql, V2__indexes.sql)

### 🔐 Seguridad
- **JWT (JSON Web Tokens)**: Autenticación stateless con tokens firmados
- **JJWT (io.jsonwebtoken) 0.12.x**: Generación y validación de JWT
- **BCrypt**: Hash irreversible de contraseñas con factor de costo

### 📝 Herramientas de Desarrollo
- **Lombok**: Eliminación de código repetitivo (@Getter, @Setter, @Builder, @Slf4j)
- **Springdoc OpenAPI / Swagger UI**: Documentación interactiva de API (http://localhost:8080/swagger-ui.html)

### 🧪 Testing
- **JUnit 5**: Framework de pruebas estándar
- **Mockito**: Mocking de dependencias para tests aislados
- **JaCoCo**: Cobertura de código (mínimo 70% requerido en capa Service)

### 📄 Generación de PDFs
- **iText 7**: Generación de recibos de pago con tabla de ítems, subtotal, IVA y total

### 🐳 Infraestructura y Despliegue
- **Docker**: Empaquetamiento en contenedores
- **Dockerfile multistage**: Optimización de tamaño de imagen (Maven → JRE)
- **Docker Compose**: Orquestación de aplicación + PostgreSQL

### 📦 Gestor de Dependencias y Build
- **Maven**: Gestión de dependencias, compilación y empaquetamiento

---

## Convención de Commits

Usamos una convención clara y consistente para mantener el historial de cambios legible y automatable.

### Formato

```
tipo(scope): descripción
```

### Tipos de Commits

| Tipo | Descripción | Ejemplo |
|------|-------------|---------|
| `feat` | Nueva funcionalidad | `feat(menu): agregar CRUD de categorías` |
| `fix` | Corrección de errores | `fix(auth): resolver error en validación de JWT` |
| `chore` | Configuración, dependencias, herramientas | `chore: actualizar versión de Spring Boot` |
| `docs` | Documentación | `docs: agregar guía de instalación` |
| `test` | Pruebas unitarias, integración | `test(producto): agregar tests de validación` |
| `refactor` | Refactorización sin cambio funcional | `refactor(service): simplificar lógica de cálculo` |

### Ejemplos Reales

```bash
git commit -m "feat(venta): crear endpoint POST /ventas para registrar transacciones"
git commit -m "fix(seguridad): corregir validación de roles en JWT"
git commit -m "chore: agregar dependencia de Flyway para migraciones"
git commit -m "docs(api): documentar campos de modelo Producto en README"
git commit -m "test(categoria): agregar tests de listado con filtro activo"
git commit -m "refactor(util): extraer lógica de cálculo de impuestos a método reutilizable"
```

### Buenas Prácticas

- ✅ Commits pequeños y enfocados en un único cambio
- ✅ Descripción en tiempo presente imperativo ("agregar" no "agregado")
- ✅ Scope en minúsculas y sin espacios
- ✅ Primera línea concisa (máx. 50 caracteres)
- ✅ Si necesitas más detalle, agrega una línea en blanco y descripción extendida

---

## Arquitectura de Flujo de Datos

```
Cliente (Postman / Frontend)
    │  HTTP + JWT
    ▼
Spring Web (@RestController)
    │
    ▼
Spring Security (JwtAuthFilter)
    │
    ▼
Service Layer (lógica de negocio)
    │
    ▼
Spring Data JPA + Hibernate
    │  SQL
    ▼
PostgreSQL ◄── Flyway (migraciones)
```

---

## Requisitos Previos

- **Java 21 LTS** o superior
- **Maven 3.8.x** o superior
- **PostgreSQL 16** (local o con Docker)
- **Git** para control de versiones
- **IntelliJ IDEA** (recomendado) o editor de tu preferencia

---

## Instalación y Configuración

### 1. Clonar el Repositorio

```bash
git clone https://github.com/JonathanCadena28906/pos-restaurante-springboot.git
cd pos-restaurante-springboot
```

### 2. Configurar Base de Datos

Con Docker Compose:

```bash
docker-compose up -d
```

O manualmente:
- Crear base de datos: `pos_db`
- Usuario: `pos_user`
- Contraseña: `pos_pass`
- Puerto: `5432` (o el configurado en `application.yml`)

### 3. Ejecutar la Aplicación

```bash
mvn spring-boot:run
```

O compilar y empaquetar:

```bash
mvn clean package
java -jar target/pos_LCB-0.0.1-SNAPSHOT.jar
```

### 4. Acceder a la Documentación de API

```
http://localhost:8080/swagger-ui.html
```

---

## Estructura del Proyecto

```
src/
├── main/
│   ├── java/com/pos/restaurante/pos_LCB/
│   │   ├── PosLcbApplication.java          # Punto de entrada
│   │   ├── config/                         # Configuración de Spring
│   │   ├── controllers/                    # Endpoints REST
│   │   ├── services/                       # Lógica de negocio
│   │   ├── repositories/                   # Acceso a datos (JPA)
│   │   ├── entity/                         # Modelos (@Entity)
│   │   ├── dto/                            # Data Transfer Objects
│   │   ├── security/                       # JWT, filters, roles
│   │   ├── exception/                      # Manejo de excepciones
│   │   └── util/                           # Utilidades
│   └── resources/
│       ├── application.yml                 # Configuración principal
│       ├── application-dev.yml             # Perfil desarrollo
│       ├── db/migration/                   # Scripts Flyway (V1__, V2__, etc.)
│       └── templates/                      # Vistas Thymeleaf (si aplica)
└── test/
    └── java/com/pos/restaurante/pos_LCB/  # Tests con JUnit 5 + Mockito
```

---

## Licencia

Este proyecto está bajo la licencia **MIT**. Ver archivo `LICENSE` para más detalles.

---

## Autor

**Jonathan Cadena**  
📧 [jonathancadenaloaiza@gmail.com](mailto:jonathancadenaloaiza@gmail.com)  
🔗 [GitHub](https://github.com/JonathanCadena28906)

---

**Última actualización**: 2026-03-24
