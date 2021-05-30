CREATE TABLE player
    (
     id         INT8 NOT NULL,
     name       VARCHAR(255),
     PRIMARY KEY (id)
    );

CREATE TABLE record
    (
       id         INT8 NOT NULL,
       player_id    INT8 NOT NULL,
       outcome    VARCHAR(255),
       PRIMARY KEY (id)
    );

INSERT INTO player(id, name)
VALUES
( 1, 'John'),
( 2, 'Andrew'),
( 3, 'Jason'),
( 4, 'Bill');