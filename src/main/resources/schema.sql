DROP TABLE IF EXISTS passengers;

CREATE TABLE passengers (
    passenger_id BIGINT PRIMARY KEY,
    survived INT,
    pclass INT,
    name VARCHAR(255),
    sex VARCHAR(20),
    age DOUBLE,
    sibsp INT,
    parch INT,
    ticket VARCHAR(50),
    fare DOUBLE,
    cabin VARCHAR(50),
    embarked VARCHAR(5)
);
