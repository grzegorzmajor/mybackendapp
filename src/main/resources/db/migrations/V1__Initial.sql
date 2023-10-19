CREATE SEQUENCE markup_dictionary_entity_seq INCREMENT BY 50 START WITH 1;

CREATE SEQUENCE paragraph_entity_seq INCREMENT BY 50 START WITH 1;

CREATE SEQUENCE post_entity_seq INCREMENT BY 50 START WITH 1;

CREATE TABLE markup_dictionary_entity
(
    id      INT          NOT NULL,
    opening VARCHAR(255) NULL,
    closing VARCHAR(255) NULL,
    CONSTRAINT pk_markupdictionaryentity PRIMARY KEY (id)
);

CREATE TABLE paragraph_entity
(
    id            INT NOT NULL,
    dictionary_id INT NULL,
    paragraph_id  INT NULL,
    CONSTRAINT pk_paragraphentity PRIMARY KEY (id)
);

CREATE TABLE paragraph_entity_paragraph_content
(
    paragraph_entity_id INT          NOT NULL,
    paragraph_content   VARCHAR(255) NULL
);

CREATE TABLE post_entity
(
    id               INT      NOT NULL,
    adding_date      datetime NULL,
    publication_date datetime NULL,
    CONSTRAINT pk_postentity PRIMARY KEY (id)
);

ALTER TABLE markup_dictionary_entity
    ADD CONSTRAINT uc_markupdictionaryentity_opening UNIQUE (opening);

ALTER TABLE paragraph_entity
    ADD CONSTRAINT FK_PARAGRAPHENTITY_ON_DICTIONARY FOREIGN KEY (dictionary_id) REFERENCES markup_dictionary_entity (id);

ALTER TABLE paragraph_entity
    ADD CONSTRAINT FK_PARAGRAPHENTITY_ON_PARAGRAPH FOREIGN KEY (paragraph_id) REFERENCES post_entity (id);

ALTER TABLE paragraph_entity_paragraph_content
    ADD CONSTRAINT fk_paragraphentity_paragraphcontent_on_paragraph_entity FOREIGN KEY (paragraph_entity_id) REFERENCES paragraph_entity (id);