/*DROP TABLE IF EXISTS roles;
DROP TYPE IF EXISTS role_type CASCADE ;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS symbols;
DROP TABLE IF EXISTS words;
DROP TABLE IF EXISTS objfiles;
DROP TABLE IF EXISTS slangs;
DROP TABLE IF EXISTS dialects;
DROP TABLE IF EXISTS languages;
*/
CREATE EXTENSION IF NOT EXISTS CITEXT;

CREATE TABLE IF NOT EXISTS users (
  ID SERIAL PRIMARY KEY,
  username CITEXT UNIQUE NOT NULL,
  email CITEXT UNIQUE NOT NULL,
  password TEXT NOT NULL,
  created TIMESTAMPTZ DEFAULT NOW()
);
/*
CREATE TYPE  role_type AS ENUM ('admin','moderator');
*/

CREATE TABLE IF NOT EXISTS roles (
  role role_type NOT NULL,
  username citext NOT NULL REFERENCES users(username) ON DELETE CASCADE
);

CREATE OR REPLACE FUNCTION create_admin(_name CITEXT, _email CITEXT, _password TEXT)
  RETURNS RECORD AS '
DECLARE
  rec RECORD;
BEGIN
  UPDATE users
  SET username = _name, email = _email, password = _password
  WHERE users.username = (SELECT roles.username
                          FROM roles
                          WHERE roles.role = ''admin'')
  RETURNING *
    INTO rec;
  IF rec IS NULL
  THEN
    RAISE NOTICE ''AFTER EXCEPTION'';
    BEGIN
      INSERT INTO users (username, email, password) VALUES (_name, _email, _password)
      RETURNING *
        INTO rec;
      INSERT INTO roles (role, username) VALUES (''admin'', _name);
      RETURN rec;
    END;
  END IF;
  RETURN rec;
END;
' LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION  change_or_insert_role(name citext,type role_type)
  RETURNS RECORD AS'
DECLARE
  rec RECORD;
BEGIN
  UPDATE roles SET role = type WHERE username = name
  RETURNING *
    INTO rec;
  IF rec IS NULL
  THEN
    RAISE NOTICE ''AFTER EXCEPTION'';
    BEGIN
      INSERT INTO roles (role, username) VALUES (type,name)  RETURNING * INTO rec;
      RETURN rec;
    END;
  END IF;
  RETURN rec;
END;
' LANGUAGE plpgsql;

CREATE INDEX IF NOT EXISTS users_name_email_idx ON users(username, email);

CREATE TABLE IF NOT EXISTS languages (
  language citext PRIMARY KEY UNIQUE NOT NULL,
  discription TEXT
);

CREATE TABLE IF NOT EXISTS dialects(
  dialect citext PRIMARY KEY UNIQUE NOT NULL,
  language citext NOT NULL REFERENCES languages(language) ON DELETE CASCADE,
  discription TEXT
);

CREATE TABLE IF NOT EXISTS slangs (
  slang citext PRIMARY KEY UNIQUE NOT NULL,
  discription TEXT
);

CREATE TABLE IF NOT EXISTS objfiles (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS words (
  id SERIAL PRIMARY KEY,
  word citext NOT NULL,
  dialect citext NOT NULL REFERENCES  dialects(dialect) ON DELETE CASCADE,
  slang citext,
  file_id INTEGER NOT NULL REFERENCES  objfiles(id) ON DELETE CASCADE,
  discription TEXT
);

CREATE INDEX IF NOT EXISTS words_word_dialect_slang_idx ON words(word, dialect, slang);

CREATE TABLE IF NOT EXISTS symbols (
  symbol citext NOT NULL PRIMARY KEY,
  dialect citext NOT NULL REFERENCES  dialects(dialect) ON DELETE CASCADE,
  file_id SERIAL NOT NULL REFERENCES  objfiles(id) ON DELETE CASCADE,
  UNIQUE (symbol,dialect)
);

CREATE INDEX IF NOT EXISTS symbols_symbol_dialect_idx ON symbols(symbol, dialect);
