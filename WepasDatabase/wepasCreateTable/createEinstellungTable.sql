-- --------------------------------------------------------
--
-- Tabellenstruktur für Tabelle `en_einstellung`
--

DROP TABLE IF EXISTS `en_einstellung`;
CREATE TABLE IF NOT EXISTS `en_einstellung` (
  `id_einstellung` int(11) NOT NULL AUTO_INCREMENT,
  `fk_variabel` int(11) NOT NULL,
  `at_einstellung_schluessel` char(8) NOT NULL,
  `at_einstellung_wert_bool` tinyint(1) NOT NULL,
  `at_einstellung_wert_int` int(11) NOT NULL,
  `at_einstellung_wert_dec` decimal(5,2) NOT NULL,
  `at_einstellung_wert_cha10` char(10) NOT NULL,
  `at_einstellung_wert_cha30` char(50) NOT NULL,
  PRIMARY KEY (`id_einstellung`)
) 
ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
