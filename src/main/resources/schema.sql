DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS symbols;
DROP TABLE IF EXISTS words;
DROP TABLE IF EXISTS objfiles;
DROP TABLE IF EXISTS slangs;
DROP TABLE IF EXISTS dialects;
DROP TABLE IF EXISTS languages;


CREATE EXTENSION IF NOT EXISTS CITEXT;

CREATE TABLE IF NOT EXISTS users (
  id SERIAL PRIMARY KEY,
  username CITEXT UNIQUE NOT NULL,
  email CITEXT UNIQUE NOT NULL,
  password TEXT NOT NULL,
  created TIMESTAMPTZ DEFAULT NOW()
);

CREATE TYPE role AS ENUM ('admin','moderator');

CREATE TABLE  IF NOT EXISTS roles (
  type role NOT NULL,
  username citext NOT NULL REFERENCES users(username) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS users_name_email_idx ON users(username, email);

CREATE TABLE IF NOT EXISTS languages (
  language citext PRIMARY KEY UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS dialects(
  dialect citext PRIMARY KEY UNIQUE NOT NULL,
  language citext NOT NULL REFERENCES languages(language) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS slangs (
  slang citext PRIMARY KEY UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS objfiles (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS words (
  id SERIAL PRIMARY KEY,
  word citext NOT NULL,
  dialect citext NOT NULL REFERENCES  dialects(dialect) ON DELETE CASCADE,
  slang citext NOT NULL REFERENCES  slangs(slang),
  file_id SERIAL NOT NULL REFERENCES  objfiles(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS words_word_dialect_slang_idx ON words(word, dialect, slang);

CREATE TABLE IF NOT EXISTS symbols (
  symbol citext NOT NULL PRIMARY KEY,
  dialect citext NOT NULL REFERENCES  dialects(dialect) ON DELETE CASCADE,
  file_id SERIAL NOT NULL REFERENCES  objfiles(id) ON DELETE CASCADE
);
