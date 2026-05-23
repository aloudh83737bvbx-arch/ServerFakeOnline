package saturn.fakeonline;

import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public final class Main extends JavaPlugin implements Listener {

    private int currentFakeOnline = 0;
    private int baseCount = 7;
    private int minFake = 3;
    private int maxFake = 12;
    private int slots = 20;
    private int updateInterval = 5;

    private final Random random = new Random();
    private boolean paperSupport = false;

    @Override
    public void onEnable() {
        loadConfig();
        // Проверяем наличие Paper-события
        try {
            Class.forName("com.destroystokyo.paper.event.server.PaperServerListPingEvent");
            paperSupport = true;
            getLogger().info("Paper обнаружен – полная подмена онлайна активирована.");
        } catch (ClassNotFoundException e) {
            getLogger().warning("Paper не найден! Онлайн игроков изменить не получится, только слоты.");
        }

        startFluctuationTask();
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("FakeOnline запущен! Текущий фейк: " + currentFakeOnline);
    }

    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        // Максимальное число слотов меняем всегда
        if (slots > 0) {
            event.setMaxPlayers(slots);
        }

        // Подмена онлайна возможна только через Paper API
        if (paperSupport && event instanceof PaperServerListPingEvent) {
            PaperServerListPingEvent paperEvent = (PaperServerListPingEvent) event;
            int realOnline = paperEvent.getNumPlayers();
            paperEvent.setNumPlayers(realOnline + currentFakeOnline);
        }
    }

    private void loadConfig() {
        saveDefaultConfig(); // копирует config.yml из ресурсов, если его нет
        reloadConfig();

        baseCount = getConfig().getInt("fakeOnline.count", 7);
        minFake = getConfig().getInt("fakeOnline.min-fake", 3);
        maxFake = getConfig().getInt("fakeOnline.max-fake", 12);
        slots = getConfig().getInt("fakeOnline.slots", 20);
        updateInterval = getConfig().getInt("fakeOnline.update-interval", 5);

        // Устанавливаем начальное значение близко к базовому
        currentFakeOnline = Math.max(minFake, Math.min(maxFake, baseCount));
    }

    private void startFluctuationTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                int delta = random.nextInt(3) - 1;  // -1, 0, +1
                int newFake = currentFakeOnline + delta;
                newFake = Math.max(minFake, Math.min(maxFake, newFake));

                if (newFake != currentFakeOnline) {
                    currentFakeOnline = newFake;
                    getLogger().info("Фейковый онлайн изменился: " + currentFakeOnline);
                }
            }
        }.runTaskTimer(this, 0, updateInterval * 20L); // интервал в тиках
    }
}