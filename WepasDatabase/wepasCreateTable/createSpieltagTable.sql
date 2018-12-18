--
-- Tabellenstruktur für Tabelle `en_spieltag`
--

DROP TABLE IF EXISTS `en_spieltag`;
CREATE TABLE IF NOT EXISTS `en_spieltag` (
  `id_spieltag` int(11) NOT NULL AUTO_INCREMENT,
  `fk_spieltag_einstellung` int(11) DEFAULT NULL,
  `at_spieltag_nummer` int(11) NOT NULL,
  `at_spieltag_text` char(30) NOT NULL,
  `at_spieltag_start` date DEFAULT NULL,
  `at_spieltag_ende` date DEFAULT NULL,
  `at_spieltag_paarungen` int(2) NOT NULL,
  `at_spieltag_supertipps` int(2) NOT NULL,
  `at_spieltag_is_started` tinyint(1) NOT NULL,
  `at_spieltag_is_finished` tinyint(1) NOT NULL,
  `at_spieltag_is_duty` tinyint(1) NOT NULL,
  `at_spieltag_kosten` decimal(5,2) NOT NULL,
  `at_spieltag_einsatz` decimal(5,2) NOT NULL,
  PRIMARY KEY (`id_spieltag`)
)
ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;
