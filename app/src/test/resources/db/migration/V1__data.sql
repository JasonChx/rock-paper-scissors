CREATE TABLE player
    (
     id         INT8 NOT NULL,
     name       VARCHAR(255),
     PRIMARY KEY (id)
    );

CREATE TABLE record
    (
       id         INT8 NOT NULL,
       player_id  INT8 NOT NULL,
       outcome    VARCHAR(255),
       date       timestamp NOT NULL,
       PRIMARY KEY (id)
    );

INSERT INTO player(id, name)
VALUES
( 1, 'John'),
( 2, 'Andrew'),
( 3, 'Jason'),
( 4, 'Bill');

INSERT INTO record(id, player_id, outcome, date)
VALUES
( 1, 1, 0, '2021-05-30 11:24:00'),
( 2, 1, 1, '2021-05-30 12:24:00'),
( 3, 1, 1, '2021-05-30 13:24:00'),
( 4, 1, 2, '2021-05-30 14:24:00'),
( 5, 1, 2, '2021-05-30 15:24:00'),
( 6, 1, 2, '2021-05-30 16:24:00');