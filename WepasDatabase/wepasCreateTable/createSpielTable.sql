--
-- Tabellenstruktur für Tabelle `en_spiel`
--

DROP TABLE IF EXISTS `en_spiel`;
CREATE TABLE IF NOT EXISTS `en_spiel` (
  `id_spiel` int(11) NOT NULL AUTO_INCREMENT,
  `fk_spiel_verein_heim` int(11) NOT NULL,
  `fk_spiel_verein_gast` int(11) NOT NULL,
  `fk_spiel_spieltag` int(11) NOT NULL,
  `fk_spiel_einstellung` int(11) DEFAULT NULL,
  `at_spiel_tore_heim` int(11) DEFAULT NULL,
  `at_spiel_tore_gast` int(11) DEFAULT NULL,
  `at_spiel_is_played` tinyint(1) DEFAULT NULL,
  `at_spiel_anpfiff` datetime DEFAULT NULL,
  PRIMARY KEY (`id_spiel`)
) 
ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
