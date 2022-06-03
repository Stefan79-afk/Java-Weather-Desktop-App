DROP WEATHER;

CREATE TABLE WEATHER(
    CITYID                 NUMBER(4) NOT NULL,

    CITYNAME               VARCHAR2(30),

    COUNTRY                VARCHAR2(30),

    TEMPERATURE_CELSIUS    NUMBER(3,2),

    TEMPERATURE_FAHRENHEIT NUMBER(3,2),

    UV_INDEX               NUMBER(3),

    WEATHER                VARCHAR2(20),

    HUMIDITY               NUMBER(3),

    PRECIPITATION          NUMBER(3),

    WIND                   NUMBER(4),

    CONSTRAINT WEATHER_PRIMARY_KEY PRIMARY KEY (CITYID)

);


INSERT INTO WEATHER VALUES (1, 'Timisoara', 'Romania', -1, 30.2, 1, 'Light Snow', 100%, 1.2, 9);

INSERT INTO WEATHER VALUES (2, 'London', 'United Kingdom', 10, 50, 1, 'Light Rain', 93%, 2.2, 9);

INSERT INTO WEATHER VALUES (1, 'Paris', 'France', 1, 33.8, 2, 'Mostly Sunny', 93%, 0, 6);

INSERT INTO WEATHER VALUES (1, 'Berlin', 'Germany', -1, 30.2, 1, 'Mostly Sunny', 65%, 0, 10);

INSERT INTO WEATHER VALUES (1, 'Amsterdam', 'Netherlands', 2, 35.6, 1, 'Somewhat Sunny', 86%, 0, 16);

INSERT INTO WEATHER VALUES (1, 'Brussels', 'Belgium', 3, 37.4, 1, 'Mostly Cloudy', 75%, 0, 11);

INSERT INTO WEATHER VALUES (1, 'Madrid', 'Spain', 8, 46.4, 2, 'Sunny', 77%, 0, 6);

INSERT INTO WEATHER VALUES (1, 'Lisbon', 'Portugal', 11, 51.8, 2, 'Fog', 100%, 0, 13);




