CREATE TABLE topic (
    id          BIGINT      NOT NULL AUTO_INCREMENT,
    id_user     BIGINT      NOT NULL,
    id_course   BIGINT      NOT NULL,
    title       VARCHAR(50) NOT NULL,
    message     TEXT        NOT NULL,
    date        DATETIME    NOT NULL,
    status      BOOLEAN     NOT NULL,
    author      VARCHAR(25) NOT NULL,
    course      VARCHAR(80) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_user)   REFERENCES user (id),
    FOREIGN KEY (id_course) REFERENCES course (id)
);