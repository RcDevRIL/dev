
SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

CREATE SCHEMA j2e;

ALTER SCHEMA j2e OWNER TO postgres;

SET default_tablespace = '';
SET default_with_oids = false;

CREATE TABLE j2e.website (
    i_website_id serial,
    v_url character varying(45),
	i_category_id integer NOT NULL
);
ALTER TABLE j2e.website OWNER TO postgres;

CREATE TABLE j2e.category (
    i_category_id serial,
    v_category character varying(45)
);
ALTER TABLE j2e.category OWNER TO postgres;

CREATE INDEX fk_category_idx ON j2e.category USING btree (i_category_id);

-- Contrainte j2e.category

ALTER TABLE ONLY j2e.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (i_category_id);

-- Contrainte j2e.website

ALTER TABLE ONLY j2e.website
    ADD CONSTRAINT website_pkey PRIMARY KEY (i_website_id);
ALTER TABLE ONLY j2e.website
    ADD CONSTRAINT fk_category FOREIGN KEY (i_category_id) REFERENCES j2e.category(i_category_id);
