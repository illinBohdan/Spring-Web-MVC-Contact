CREATE TABLE IF NOT EXISTS contacts (
    id         INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    phone      TEXT NOT NULL
);

SELECT * FROM contacts;


