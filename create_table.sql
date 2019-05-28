CREATE TABLE IF NOT EXISTS contacts
(
   id    serial        NOT NULL,
   name  varchar(20)   NOT NULL
);

-- Column id is associated with sequence public.contacts_id_seq

ALTER TABLE contacts
   ADD CONSTRAINT contacts_pkey
   PRIMARY KEY (id);