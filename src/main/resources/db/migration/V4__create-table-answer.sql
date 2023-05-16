CREATE TABLE answer (
    id          BIGINT      NOT NULL AUTO_INCREMENT,
    id_user     BIGINT      NOT NULL,
    id_topic    BIGINT      NOT NULL,
    message     TEXT        NOT NULL,
    date        DATETIME    NOT NULL,
    author      VARCHAR(25) NOT NULL,
    votes       INT         NOT NULL,
    solve       BOOLEAN DEFAULT FALSE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_user)   REFERENCES user (id),
    FOREIGN KEY (id_topic) REFERENCES topic (id)
);