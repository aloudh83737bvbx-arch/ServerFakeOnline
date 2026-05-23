# FakeOnline – Плагин динамического фейкового онлайна для Paper
📌 Что делает плагин?
Плагин FakeOnline создаёт иллюзию живого сервера: в списке серверов Minecraft отображается не статичное число игроков, а постоянно меняющееся (то больше, то меньше) – как будто на сервер постоянно заходят и выходят реальные люди.
Также можно изменить максимальное количество слотов, показываемое в списке.

🧩 Требования
Серверное ядро: Paper (или его форки: Purpur, Pufferfish, Tuinity и т.п.) версии 1.13+.

Для версий 1.9.4 – 1.12.2 плагин может работать, но не гарантируется (см. ниже).

Java 8 или выше.

🗂️ Поддержка версий сервера
Версия Minecraft	Поддержка FakeOnline	Пояснение
1.13 – 1.21.4+	✅ Полная поддержка	Используется Paper API с событием PaperServerListPingEvent, всё работает идеально.
1.9.4 – 1.12.2	⚠️ Ограниченная	Paper существовал для этих версий, но событие могло называться иначе или поведение отличаться. Полная совместимость не проверялась. Слоты изменить можно всегда, онлайн – под вопросом.
1.8.8 и ниже	❌ Не работает	Нет необходимого API для подмены числа игроков. Слоты меняются, онлайн – нет. Плагин не загрузится или не будет функционировать.
Spigot (чистый) – любая версия	❌ Не работает для онлайна	Без Paper невозможно перехватить точное событие пинга с числом игроков. Максимальные слоты изменить можно, но фейковый онлайн добавлен не будет. Плагин выдаст предупреждение в консоли.
📝 Почему нужен именно Paper?
Событие PaperServerListPingEvent содержит методы getNumPlayers() и setNumPlayers(int), которых нет в стандартном Spigot API. Именно они позволяют модифицировать отображаемый онлайн. На чистом Spigot такой возможности нет без внешних библиотек (ProtocolLib).

📁 Конфигурация (plugins/FakeOnline/config.yml)
yaml
fakeOnline:
  count: 7               # Базовое (среднее) число фейковых игроков
  slots: 20              # Показываемое количество слотов (0 = не менять)
  min-fake: 3            # Минимальное значение фейка
  max-fake: 12           # Максимальное значение фейка
  update-interval: 5     # Интервал изменения онлайна (в секундах)
После изменения конфига нужно перезапустить сервер (или плагин командой /plugman reload (Плагин PLUGMANX)).

🚀 Установка
Скачай файл FakeOnline.jar.

Помести его в папку plugins твоего сервера (обязательно Paper или форк).

Запусти или перезапусти сервер.

При необходимости настрой config.yml в папке plugins/FakeOnline.

💡 Пример из игры
В списке серверов ты увидишь:

text
ТвойСервер ┃ 2/20
ТвойСервер ┃ 16/20
ТвойСервер ┃ 7/20
ТвойСервер ┃ 15/20
– число игроков плавно колеблется, создавая видимость активности.

## 📄 Лицензия
Данный проект распространяется под лицензией GNU GPLv3 с дополнительным запретом на имперсонацию автора. Подробнее см. [LICENSE](LICENSE).



# FakeOnline – Dynamic Fake Player Count Plugin for Paper

## 📌 What does the plugin do?
The **FakeOnline** plugin creates the illusion of an active server: the Minecraft server list shows a constantly changing player count (fluctuating up and down) – as if real players are joining and leaving all the time.  
You can also change the maximum slots displayed in the server list.

---

## 🧩 Requirements
- **Server software:** **Paper** (or its forks: Purpur, Pufferfish, Tuinity, etc.) version **1.13+**.
- For versions **1.9.4 – 1.12.2** the plugin may work, but full compatibility is **not guaranteed** (see below).
- **Java 8** or higher.

---

## 🗂️ Supported server versions

| Minecraft version | FakeOnline support | Explanation |
|------------------|-------------------|-------------|
| **1.13 – 1.21.4+** | ✅ Full support | Uses the Paper API with `PaperServerListPingEvent`, everything works perfectly. |
| **1.9.4 – 1.12.2** | ⚠️ Limited | Paper existed for these versions, but the event may have a different name or behavior. Full compatibility hasn't been tested. *Slots can always be changed; online count – maybe.* |
| **1.8.8 and below** | ❌ Not working | The necessary API to spoof the player count is missing. Slots are modified, but online is not. Plugin will not load or will not function. |
| **Pure Spigot (any version)** | ❌ Online spoofing not possible | Without Paper it is impossible to intercept the exact ping event with player count. Max slots can be changed, but the fake online count will not be added. The plugin will show a warning in the console. |

---

### 📝 Why is Paper required?
The `PaperServerListPingEvent` provides `getNumPlayers()` and `setNumPlayers(int)` methods that are missing from the standard Spigot API. These methods allow modifying the displayed online count. On pure Spigot this is impossible without external libraries (like ProtocolLib).

---

## 📁 Configuration (`plugins/FakeOnline/config.yml`)
fakeOnline:
  count: 7               # Base (average) number of fake players
  slots: 20              # Displayed slot count (0 = don't change)
  min-fake: 3            # Minimum fake count
  max-fake: 12           # Maximum fake count
  update-interval: 5     # Interval between online count changes (in seconds)
After editing the config, restart the server or reload the plugin with /plugman reload (requires PlugManX).

🚀 Installation
Download the FakeOnline.jar file.

Place it in the plugins folder of your server (must be Paper or a fork).

Start or restart the server.

If needed, adjust config.yml in the plugins/FakeOnline folder.

💡 In-game example
In the server list you will see something like:

text
YourServer ┃ 12/20
YourServer ┃ 16/20
YourServer ┃ 7/20
YourServer ┃ 15/20
The player count smoothly fluctuates, creating the appearance of real activity.
