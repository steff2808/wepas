--
-- Tabellenstruktur für Tabelle `en_tipper`
--

DROP TABLE IF EXISTS `en_tipper`;
CREATE TABLE IF NOT EXISTS `en_tipper` (
  `id_tipper` int(11) NOT NULL AUTO_INCREMENT,
  `at_tipper_name` char(30) NOT NULL,
  `at_tipper_vorname` char(20) NOT NULL,
  `at_tipper_login` char(20) NOT NULL,
  `at_tipper_password` char(20) NOT NULL,
  `at_tipper_punkte` int(11) NOT NULL,
  `at_tipper_konto` decimal(5,2) NOT NULL,
  `at_tipper_siege` int(11) NOT NULL,
  `at_tipper_zweite` int(11) NOT NULL,
  `at_tipper_team` char(2) NOT NULL,
  `at_tipper_mail` char(30) NOT NULL,
  `at_tipper_tel` char(20) NOT NULL,
  `at_tipper_mobil` char(20) NOT NULL,
  `at_tipper_iban` char(22) NOT NULL,
  `at_tipper_form` char(70) NOT NULL,
  `at_tipper_tippennext` int(11) NOT NULL,
  
  PRIMARY KEY (`id_tipper`)
)
ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;