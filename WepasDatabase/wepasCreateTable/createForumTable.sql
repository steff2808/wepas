--
-- Tabellenstruktur für Tabelle `en_forum`
--

DROP TABLE IF EXISTS `en_forum`;
CREATE TABLE IF NOT EXISTS `en_forum` (
  `id_forum` int(11) NOT NULL AUTO_INCREMENT,
  `id_tipper` int(11) DEFAULT NULL,
  `at_forum_type` char(1) NOT NULL,
  `at_forum_text1` varchar(80) DEFAULT NULL,
  `at_forum_text2` varchar(160) DEFAULT NULL,
  PRIMARY KEY (`id_forum`)
)
ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
