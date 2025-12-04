# Arquetipo Android 

## Qué incluye
- Kotlin + Jetpack Compose (Compose BOM 2025.12.00, Compose 1.10)
- Carpetas con arquitectura clean: **core, domain, application, infrastructure, presentation**
- Room (SQLite) para almacenamiento local de usuarios (registro)
- Verificación de login remoto basada en Retrofit (URL Base de la API configurable en la pantalla de Login)
- Patrón MVVM, ViewModels conectados a pantallas Compose
- Navegación entre **Login -> Register -> Home**
- Pruebas unitarias para casos de uso (mocked)

## Entorno recomendado (estable a diciembre 2025)
- Android Studio **Otter | 2025.2.1 (estable)**
- Android SDK Platform para Android 16 (API level 36)
- JDK 17 o JDK 11 soportado por el bundle de Android Studio

## Cómo ejecutar
1. Descarga y descomprime el proyecto.  
2. Abre la carpeta **'arquetipo_android_project'** en Android Studio (Open -> File).  
3. Permite que Android Studio sincronice Gradle e instale cualquier SDK/plataforma faltante (instalar Android 16 SDK/API 36).  
4. Crea o configura un emulador/dispositivo y ejecuta la app.  
5. En la pantalla de Login puedes ingresar una URL Base de API (ejemplo: `https://example.com/api/login`).  
   La llamada Retrofit usa la URL configurada; para pruebas locales puedes apuntarla a un servidor mock que regrese JSON como:  
   `{ "success": true }`.

## Notas y limitaciones
- La llamada Retrofit espera un POST que regrese `{"success": true}` para navegar a Home.  
- Para producción, se debe asegurar el almacenamiento seguro de contraseñas y transporte encriptado (TLS). Las contraseñas **nunca** deben almacenarse en texto plano (este ejemplo las almacena así ya que aun no tenemos la libreria de seguridad encargada de esto).  
- Para ejecutar pruebas unitarias: usa el test runner de Android Studio o ejecuta `./gradlew test`.

---


