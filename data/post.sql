CREATE TABLE post (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    text TEXT,
    link VARCHAR(512),
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT unique_link UNIQUE (link)
);
