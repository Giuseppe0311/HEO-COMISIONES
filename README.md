
# Proyecto de Gestión de Comisiones y Contratos Automatizados 📊📝

## Descripción
Este proyecto desarrollado en Java con Spring Boot 3 permite calcular las comisiones basadas en las ventas realizadas por empleados de una empresa. Incluye un robusto sistema de generación de contratos mediante scripts en Python, los cuales son ejecutados para generar documentos Word personalizados con parámetros específicos. Además, el sistema automatiza el cierre mensual de reportes mediante tareas programadas (`@Scheduled`), asegurando que los empleados envíen sus informes el día 28 de cada mes sin intervención manual.

## Características Principales
- **Cálculo de Comisiones:** Basado en las ventas registradas por los empleados.
- **Generador de Contratos:** Utiliza scripts de Python para generar documentos Word con datos específicos.
- **Automatización del Cierre Mensual:** Utiliza `@Scheduled` para enviar reportes automáticamente el día 28 de cada mes.
- **Registro de Clientes:** Permite el manejo y registro de clientes.
- **Tecnologías Utilizadas:** Spring Boot 3, Java 17, JPA (Hibernate), PostgreSQL, JWT para autenticación, Cloudinary para almacenamiento en la nube de contratos.

## Retos Superados
Durante el desarrollo de este proyecto, enfrenté varios desafíos significativos que contribuyeron a mi aprendizaje y crecimiento profesional:
- **Integración Compleja de Tecnologías:** Lograr una integración fluida entre Spring Boot 3, JPA, PostgreSQL y Cloudinary fue fundamental.
- **Implementación del Generador de Contratos:** Desarrollar un sistema robusto para ejecutar scripts de Python desde Java para generar documentos Word personalizados.
- **Automatización del Cierre Mensual:** Utilización efectiva de `@Scheduled` para la automatización del envío de reportes mensuales.
