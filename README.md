# BankApp
Para ver data mockeada en la pantalla principal ingresar con

Dato | Valor
--- | --- 
Email   |  `test@gmail.com`
Password | `12345678`

La aplicación se encuentra modularizada siguiendo vertical slicing e IDD. Separando la capa de presentación en el módulo de feature y la lógica de negocio en model.
![Captura de pantalla 2023-10-31 a la(s) 12 02 01](https://github.com/buongarzoni/bankapp/assets/57886390/04a5fadb-8438-4296-ba97-7ae9133c9611)

Módulo | Responsabilidad
--- | --- 
App   |  Inyectar depencencias, manejar navegaciones
Core | Contiene clases que se van a utilizar a través de todos los módulos y algunas interfaces, clases que sean comunes a todos contextos
Components | Funciones componibles que se van a utilizar en gran parte de la aplicación
Onboarding:feature | Capa de presentación del Onboarding
Onboarding:Model | Lógica de negocio del Onboarding
Home:Feature | Capa de presentación de la Home
Home:Model | Lógica de negocio de la Home
Infrastructure:Database:Firebase | Implementación de lógica de repositorios con la base de datos remota de Firebase
Infrastructure:Auth:Firebase | Implementación de autenticación con Firebase
