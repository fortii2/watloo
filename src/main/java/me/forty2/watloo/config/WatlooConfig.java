package me.forty2.watloo.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import me.forty2.watloo.WatlooBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;

@Slf4j
@Configuration
public class WatlooConfig {

    @Value("${telegram.bot.token}")
    private String botToken;

    @Autowired
    private WatlooBot watlooBot;

    @PostConstruct
    public void registerBot() {
        try {
            TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication();
            botsApplication.registerBot(botToken, watlooBot);
            log.info("bot registered successfully.");
        } catch (Exception e) {
            log.error("failed to register bot", e);
        }
    }
}
