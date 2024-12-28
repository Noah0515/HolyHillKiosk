CREATE TABLE `Beverage`
(
    `beverageCode`    INT NOT NULL,
    `beverageName`    VARCHAR(30) NOT NULL,
    PRIMARY KEY ( `beverageCode` )
);


CREATE TABLE `BeverageOrder`
(
    `beverageOrderId`    CHAR(10) NOT NULL,
    `orderTime`    DATETIME NOT NULL,
    `orderId`    CHAR(3) NOT NULL,
    `beverageOrderComplete`    BOOLEAN NOT NULL,
    PRIMARY KEY ( `beverageOrderId` )
);


CREATE TABLE `Food`
(
    `foodCode`    INT NOT NULL,
    `foodName`    VARCHAR(30) NOT NULL,
    `foodOption`    BOOLEAN NOT NULL,
    PRIMARY KEY ( `foodCode` )
);


CREATE TABLE `FoodOrder`
(
    `foodOrderId`    CHAR(10) NOT NULL,
    `orderTime`    DATETIME NOT NULL,
    `orderId`    CHAR(3) NOT NULL,
    `foodOrderComplete`    BOOLEAN NOT NULL,
    PRIMARY KEY ( `foodOrderId` )
);


CREATE TABLE `OrderedBeverage`
(
    `beverageOrderId`    CHAR(10) NOT NULL,
    `beverageCode`    INT NOT NULL,
    `beverageNum`    INT NOT NULL,
    PRIMARY KEY ( `beverageOrderId`,`beverageCode` )
);


CREATE TABLE `OrderedFood`
(
    `foodOrderId`    CHAR(10) NOT NULL,
    `foodCode`    INT NOT NULL,
    `foodNum`    INT NOT NULL,
    `optionChoice`    BOOLEAN NOT NULL,
    PRIMARY KEY ( `foodOrderId`,`foodCode` )
);


CREATE TABLE `Orders`
(
    `orderTime`    DATETIME NOT NULL,
    `orderId`    CHAR(3) NOT NULL,
    `orderComplete`    BOOLEAN NOT NULL,
    PRIMARY KEY ( `orderTime`,`orderId` )
);





