package ru.job4j.grabber.stores;

import org.apache.log4j.Logger;
import ru.job4j.Main;
import ru.job4j.grabber.model.Post;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcStore implements Store {
    private final Connection connection;
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public JdbcStore(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Post post) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO post(name, text, link, created) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getDescription());
            statement.setString(3, post.getLink());
            statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            statement.execute();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                post.setId(generatedKeys.getLong(1));
            }
        } catch (Exception e) {
            LOGGER.error("When saving to DB", e);
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            statement.execute("SELECT * FROM post;");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                result.add(new Post(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("link"),
                        resultSet.getString("text"),
                        resultSet.getTimestamp("created").getTime())
                );
            }
        } catch (SQLException e) {
            LOGGER.error("When getting all", e);
        }
        return result;
    }

    @Override
    public Optional<Post> findById(Long id) {
        Post post = null;
        try {
            Statement statement = connection.createStatement();
            statement.execute(String.format("SELECT * FROM post WHERE id = %d;", id));
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                post = new Post(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("link"),
                        resultSet.getString("text"),
                        resultSet.getTimestamp("created").getTime()
                );
            }
        } catch (SQLException e) {
            LOGGER.error("When finding by ID", e);
        }
        return Optional.ofNullable(post);
    }
}