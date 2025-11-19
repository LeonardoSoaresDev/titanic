INSERT INTO passengers (passenger_id, survived, pclass, name, sex, age, sibsp, parch, ticket, fare, cabin, embarked)
SELECT * FROM CSVREAD('classpath:titanic.csv');
