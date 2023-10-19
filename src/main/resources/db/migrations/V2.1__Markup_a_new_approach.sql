ALTER TABLE markup_dictionary_entity
    ADD class_name VARCHAR(255) NULL;

ALTER TABLE markup_dictionary_entity
    ADD tag_name VARCHAR(255) NULL;

ALTER TABLE markup_dictionary_entity
    ADD CONSTRAINT uc_markupdictionaryentity_tagname UNIQUE (tag_name);

UPDATE markup_dictionary_entity
    SET tag_name = SUBSTRING(opening, 2, LOCATE(' ', opening) - 2)
    WHERE opening IS NOT NULL;

UPDATE markup_dictionary_entity
    SET class_name = SUBSTRING_INDEX(SUBSTRING_INDEX(opening, '"', -2), '"', 1)
    WHERE opening IS NOT NULL;
