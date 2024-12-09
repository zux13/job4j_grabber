package ru.job4j;

import org.apache.log4j.Logger;
import ru.job4j.grabber.model.Post;
import ru.job4j.grabber.service.Config;
import ru.job4j.grabber.service.SchedulerManager;
import ru.job4j.grabber.service.SuperJobGrab;
import ru.job4j.grabber.stores.JdbcStore;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        var config = new Config();
        config.load("application.properties");
        try {
            var connection = getConnection(config);
            var store = new JdbcStore(connection);
            var post = new Post();
            post.setTitle("Super Java Job");
            store.save(post);
            var scheduler = new SchedulerManager();
            scheduler.init();
            scheduler.load(
                    Integer.parseInt(config.get("rabbit.interval")),
                    SuperJobGrab.class,
                    store);
        } catch (Exception e) {
            LOGGER.error("When create a connection", e);
        }
    }

    private static Connection getConnection(Config config) throws Exception {
        Class.forName(config.get("db.driver-class-name"));
        String url = config.get("db.url");
        String login = config.get("db.username");
        String password = config.get("db.password");
        return DriverManager.getConnection(url, login, password);
    }
}