create database HolyHillKiosk

CREATE TABLE beverage
(
    beveragecode    INT NOT NULL,
    beveragename    VARCHAR(30) NOT NULL,
    PRIMARY KEY (beveragecode)
);

CREATE TABLE beverageorder
(
    beverageorderid    CHAR(10) NOT NULL,
    ordertime    DATETIME NOT NULL,
    orderid    CHAR(3) NOT NULL,
    beverageordercomplete    BOOLEAN NOT NULL,
    PRIMARY KEY (beverageorderid)
);

CREATE TABLE food
(
    foodcode    INT NOT NULL,
    foodname    VARCHAR(30) NOT NULL,
    foodoption    BOOLEAN NOT NULL,
    PRIMARY KEY (foodcode)
);

CREATE TABLE foodorder
(
    foodorderid    CHAR(10) NOT NULL,
    ordertime    DATETIME NOT NULL,
    orderid    CHAR(3) NOT NULL,
    foodordercomplete    BOOLEAN NOT NULL,
    PRIMARY KEY (foodorderid)
);

CREATE TABLE orderedbeverage
(
    beverageorderid    CHAR(10) NOT NULL,
    beveragecode    INT NOT NULL,
    beveragenum    INT NOT NULL,
    PRIMARY KEY (beverageorderid, beveragecode)
);

CREATE TABLE orderedfood
(
    foodorderid    CHAR(10) NOT NULL,
    foodcode    INT NOT NULL,
    foodnum    INT NOT NULL,
    optionchoice    BOOLEAN NOT NULL,
    PRIMARY KEY (foodorderid, foodcode)
);

CREATE TABLE orders
(
    ordertime    DATETIME NOT NULL,
    orderid    CHAR(3) NOT NULL,
    ordercomplete    BOOLEAN NOT NULL,
    PRIMARY KEY (ordertime, orderid)
);