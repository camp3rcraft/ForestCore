# ForestCore

ForestCore - это ядро для плагинов Minecraft, которое предоставляет общие функции и утилиты для всех плагинов Forest. Плагин разработан для Minecraft 1.16.5 на серверах Paper.

**Автор:** camper_crafting

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

### 2. Настройка аутентификации GitHub
Для доступа к GitHub Packages вам нужно добавить аутентификацию. Создайте файл `~/.m2/settings.xml` со следующим содержимым:

```xml
<settings>
    <servers>
        <server>
            <id>github</id>
            <username>YOUR_GITHUB_USERNAME</username>
            <password>YOUR_GITHUB_TOKEN</password>
        </server>
    </servers>
</settings>
```

Замените `YOUR_GITHUB_USERNAME` на ваше имя пользователя GitHub и `YOUR_GITHUB_TOKEN` на ваш персональный токен доступа GitHub.

### 3. Добавление зависимости в plugin.yml
```yaml
depend: [ForestCore]
```

### 4. Регистрация плагина
В методе `onEnable()`