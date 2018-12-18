--<ScriptOptions statementTerminator=";"/>

ALTER TABLE en_wette DROP PRIMARY KEY;

ALTER TABLE en_tipper DROP PRIMARY KEY;

ALTER TABLE en_einstellung DROP PRIMARY KEY;

ALTER TABLE en_spieltag DROP PRIMARY KEY;

ALTER TABLE en_tipp DROP PRIMARY KEY;

ALTER TABLE en_spiel DROP PRIMARY KEY;

ALTER TABLE en_forum DROP PRIMARY KEY;

ALTER TABLE en_verein DROP PRIMARY KEY;

DROP TABLE en_tipp;

DROP TABLE en_forum;

DROP TABLE en_tipper;

DROP TABLE en_wette;

DROP TABLE en_einstellung;

DROP TABLE en_verein;

DROP TABLE en_spieltag;

DROP TABLE en_spiel;

