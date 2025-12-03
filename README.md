# Arquetipo Funcional Android (Kotlin, MVVM + Clean-ish structure)

Proyecto mínimo que incluye:
- Pantalla de Login (valida credentials vía API configurable)
- Link a pantalla de Registro (guarda en Room/SQLite)
- Home que muestra: "Arquetipo Funcional Android"
- Arquitectura: MVVM en front y separación básica de datos/NETWORK

## Requisitos
- Android Studio (Arctic Fox o superior). Recomendado: la última versión estable (2025). Compile/target SDK está configurado en 36 (Android 16).
- JDK 11/17
- Conexión a internet para descargar dependencias.

## Estructura principal
- `app/src/main/java/com/example/arquetipo/ui/...` -> fragments y viewmodels
- `app/src/main/java/com/example/arquetipo/data` -> Room entity, DAO y database
- `app/src/main/java/com/example/arquetipo/network` -> Retrofit API interface
- `app/src/main/res/navigation/nav_graph.xml` -> navegación entre pantallas

## Configuración del API (configurable)
En los ViewModels se usa por defecto `https://example.com/api/` como baseUrl. Cambia la URL en:
- `RegisterViewModel.kt` y `LoginViewModel.kt` (o, mejor aún, en `UserRepository` constructor).

La API esperada para login es un POST `/validate` que reciba JSON:
```
{ "email": "...", "password": "..." }
```
y responda:
```
{ "success": true }
```

## Cómo abrir y ejecutar
1. Descomprime el ZIP.
2. Abre Android Studio -> Open a project -> selecciona la carpeta `ArquetipoAndroid`.
3. Deja que Gradle sincronice (descargue dependencias).
4. Corre la app en un emulador o dispositivo. APK mínimo soportado: API 24.

## Notas
- Este proyecto es un *esqueleto* funcional. Para pruebas locales puedes ejecutar un mock server (ej. json-server, Mockoon) que exponga `/validate`.
- Para publicar en Google Play recuerda apuntar `targetSdk` acorde a las políticas (Android 15/16 según fecha).


## UI actualizada a Jetpack Compose
Se reemplazó la UI por pantallas en Jetpack Compose. Las clases y archivos originales de fragments siguen presentes para compatibilidad, pero la app usa las nuevas composables en `ui.*`:
- `ui.login.LoginScreen`
- `ui.register.RegisterScreen`
- `ui.home.HomeScreen`

Los ViewModels existentes están enlazados a las composables mediante `viewModel()` (AndroidViewModel sigue funcionando con el factory por defecto).

## Pruebas unitarias
Se agregó un ejemplo básico de prueba unitara para LoginViewModel en `app/src/test/java/.../LoginViewModelTest.kt`.
