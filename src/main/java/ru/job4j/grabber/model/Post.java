package ru.job4j.grabber.model;

import java.util.Objects;

public class Post {
    private Long id;
    private String title;
    private String link;
    private String description;
    private Long time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Post(Long id, String title, String link, String description, Long time) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.description = description;
        this.time = time;
    }

    public Post() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Post post)) {
            return false;
        }
        return Objects.equals(link, post.link);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(link);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Post{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", link='").append(link).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", time=").append(time);
        sb.append('}');
        return sb.toString();
    }
}
