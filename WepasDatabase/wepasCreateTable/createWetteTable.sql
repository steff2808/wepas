--
-- Tabellenstruktur für Tabelle `en_wette`
--

DROP TABLE IF EXISTS `en_wette`;
CREATE TABLE IF NOT EXISTS `en_wette` (
  `id_wette` int(11) NOT NULL AUTO_INCREMENT,
  `id_tipper` int(11) NOT NULL,
  `id_spieltag` int(11) NOT NULL,
  `at_wette_punkte` int(11) NOT NULL,
  `at_wette_gewinn` decimal(5,2) NOT NULL,
  `at_wette_is_gewonnen` tinyint(1) NOT NULL,
  `at_wette_is_sieger` tinyint(1) NOT NULL,
  `at_wette_punktef` int(11) NOT NULL,
  `at_wette_punktem` int(11) NOT NULL,
  `at_wette_punktek` int(11) NOT NULL,
  PRIMARY KEY (`id_wette`)
)
ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;