DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS MATCHES;
DROP TABLE IF EXISTS CHAT_MESSAGES;

CREATE TABLE USERS(
                         id VARCHAR(36) PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         email VARCHAR(255) NOT NULL,
                         bio VARCHAR(255),
                         photo VARCHAR(255),
                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
                         deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE MATCHES(
                        id VARCHAR(36) PRIMARY KEY,
                        liker_id VARCHAR(36) NOT NULL,
                        liked_id VARCHAR(36) NOT NULL,
                        status VARCHAR(20) NOT NULL,
                        FOREIGN KEY(liker_id) REFERENCES USERS(id),
                        FOREIGN KEY(liked_id) REFERENCES USERS(id)
);

CREATE TABLE CHAT_MESSAGES(
                              id VARCHAR(36) PRIMARY KEY,
                              match_id VARCHAR(36) NOT NULL,
                              sender_id VARCHAR(36) NOT NULL,
                              content VARCHAR(255) NOT NULL,
                              created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
                              FOREIGN KEY(match_id) REFERENCES MATCHES(id),
                              FOREIGN KEY(sender_id) REFERENCES USERS(id)
);