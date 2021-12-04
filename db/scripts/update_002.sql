CREATE TABLE IF NOT EXISTS city (
   id SERIAL PRIMARY KEY,
   name TEXT
);

ALTER TABLE candidate
    ADD COLUMN IF NOT EXISTS city_id INT;

ALTER TABLE candidate
    ADD COLUMN IF NOT EXISTS created timestamp;

ALTER TABLE post
    ADD COLUMN IF NOT EXISTS created timestamp;