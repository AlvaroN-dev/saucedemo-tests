# Pruebas automatizadas de Sauce Demo

![Pruebas UI](https://github.com/AlvaroN-dev/saucedemo-tests/actions/workflows/tests.yml/badge.svg)

Pruebas de UI sobre [saucedemo.com](https://www.saucedemo.com) con **Java 21, Selenium 4, JUnit 5 y Page Object Model**. Corren automáticamente en cada push con GitHub Actions.

## Qué se prueba
- Un login válido muestra la página de productos
- Un usuario bloqueado ve el mensaje de error
- Una clave incorrecta muestra el mensaje de error
- Agregar un producto actualiza el contador del carrito

## Estructura
```
src/test/java
├── pages/   Page Objects: localizadores y acciones de cada página
└── tests/   Casos de prueba con JUnit 5
```

## Cómo correrlo
```
mvn test
```