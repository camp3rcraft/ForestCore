# ForestCore

Плагин который добавляет несколько фич сразу для моих плагинов

## Основные возможности

### 1. Система обновлений
- Автоматическая проверка обновлений через Modrinth
- Уведомления о доступных обновлениях в консоли
- Поддержка всех плагинов, подключенных к ForestCore

### 2. Система цветов
- Поддержка HEX цветов (&#000000)
- Поддержка ванильных цветов (&f)
- Удобный метод `ColorUtils.colorize()` для использования в плагинах

### 3. Меню управления плагинами
- Просмотр всех подключенных плагинов
- Отображение информации о каждом плагине:
  - Название
  - Версия
  - ID плагина
  - Статус работы
- Возможность редактирования конфигурации плагина прямо из меню

### 4. Система команд
- `/fcore` - основная информация о ForestCore
- `/fcore help` - список всех команд
- `/fcore help <plugin-id>` - команды конкретного плагина
- `/fcore reload <plugin-id>` - перезагрузка конфигурации плагина
- `/fcore menu` - открытие меню управления плагинами
- `/fcore <plugin-id>` - информация о конкретном плагине

## Установка

### Для серверов
1. Скачайте последнюю версию `ForestCore-1.0-SNAPSHOT.jar` из папки `target` после сборки проекта
2. Поместите файл в папку `plugins` вашего сервера
3. Перезапустите сервер
4. ForestCore готов к использованию!

### Для разработчиков

## Подключение плагина к ForestCore

### 1. Добавление зависимости
В `pom.xml` вашего плагина добавьте:

```xml
<repositories>
    <repository>
        <id>github</id>
        <name>GitHub Packages</name>
        <url>https://maven.pkg.github.com/camper_crafting/ForestCore</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>ru.forestcore</groupId>
        <artifactId>ForestCore</artifactId>
        <version>1.0-SNAPSHOT</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

### 2. Добавление зависимости в plugin.yml
```yaml
depend: [ForestCore]
```

### 3. Регистрация плагина
В методе `onEnable()` вашего плагина:

```java
ForestCore forestCore = (ForestCore) Bukkit.getPluginManager().getPlugin("ForestCore");
if (forestCore != null) {
    ForestPlugin forestPlugin = new ForestPlugin(
        "Название плагина",           // Название плагина
        getDescription().getVersion(), // Версия плагина
        "plugin-id",                  // Уникальный ID плагина
        "camper_crafting",            // Имя автора
        "https://modrinth.com/plugin/your-plugin", // Ссылка на Modrinth
        "https://github.com/your-username/your-plugin", // Ссылка на GitHub
        Arrays.asList(                // Список команд плагина
            "/command1 - Описание команды 1",
            "/command2 - Описание команды 2"
        )
    );
    forestCore.getPluginManager().registerPlugin("plugin-id", forestPlugin);
}
```

## Использование цветов

### HEX цвета
```java
String message = ColorUtils.colorize("&#FF0000Красный текст");
```

### Ванильные цвета
```java
String message = ColorUtils.colorize("&cКрасный текст");
```

### Комбинация цветов
```java
String message = ColorUtils.colorize("&#FF0000Красный текст &aи зеленый текст");
```

## Пример плагина

В репозитории есть пример плагина `ExamplePlugin`, который демонстрирует:
- Подключение к ForestCore
- Использование цветов
- Регистрацию команд
- Интеграцию с меню ForestCore

## Требования

- Minecraft 1.16.5
- Paper/Spigot сервер
- Java 8 или выше

## Поддержка

Если у вас возникли проблемы или есть вопросы - создайте Issue выше