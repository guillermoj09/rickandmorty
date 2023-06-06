CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE IF NOT EXISTS origin (
    id_origin UUID,
    name TEXT NOT NULL,
    type TEXT NOT NULL,
    dimension TEXT NOT NULL,
    PRIMARY KEY (id_origin)
);

CREATE TABLE IF NOT EXISTS location (
    id_location UUID,
    name TEXT NOT NULL,
    type TEXT NOT NULL,
    dimension TEXT NOT NULL,
    PRIMARY KEY (id_location)
);


CREATE TABLE IF NOT EXISTS characters (
    uuid UUID,
    name TEXT NOT NULL,
    status TEXT NOT NULL,
    species TEXT NOT NULL,
    gender TEXT NOT NULL,
    type TEXT NOT NULL,
    PRIMARY KEY (uuid),
    id_origin UUID,
    id_location UUID,
    FOREIGN KEY(id_origin) REFERENCES origin(id_origin),
    FOREIGN KEY(id_location) REFERENCES location(id_location)
);

