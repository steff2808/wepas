--
-- Tabellenstruktur für Tabelle `en_tipp`
--

DROP TABLE IF EXISTS `en_tipp`;
CREATE TABLE IF NOT EXISTS `en_tipp` (
  `id_tipp` int(11) NOT NULL AUTO_INCREMENT,
  `id_spiel` int(11) NOT NULL,
  `id_tipper` int(11) NOT NULL,
  `at_tipp_tor_heim` int(1) NOT NULL,
  `at_tipp_tor_gast` int(1) NOT NULL,
  `at_tipp_is_supertipp` tinyint(1) NOT NULL,
  `at_tipp_is_tendenz` tinyint(1) NOT NULL,
  `at_tipp_is_differenz` tinyint(1) NOT NULL,
  `at_tipp_is_exakt` tinyint(1) NOT NULL,
  `at_tipp_is_valid` tinyint(1) NOT NULL,
  `at_tipp_punkte` int(1) NOT NULL,
  `at_tipp_punktem` int(1) NOT NULL,
  `at_tipp_punktek` int(1) NOT NULL,
  PRIMARY KEY (`id_tipp`)
)
ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;
