CREATE TABLE Product(
    id            BIGINT(20)  PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name          VARCHAR(50) NOT NULL UNIQUE,
    description   TEXT,
    price         FLOAT(7,4) NOT NULL,
    register_date DATETIME NOT NULL,
    is_active     BOOLEAN NOT NULL DEFAULT true,
    width         FLOAT(7,4),
    height        FLOAT(7,4),
    diameter      FLOAT(7,4),
    weight        FLOAT(7,4),
    sequence      INT(7) NOT NULL,
    photo_url     VARCHAR(255)
);