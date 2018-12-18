--
-- Tabellenstruktur für Tabelle `en_verein`
--

DROP TABLE IF EXISTS `en_verein`;
CREATE TABLE IF NOT EXISTS `en_verein` (
  `id_verein` int(11) NOT NULL AUTO_INCREMENT,
  `at_verein_name` char(30) NOT NULL,
  `at_verein_kurz` char(3) NOT NULL,
  `at_verein_regex` char(30) NOT NULL,
  `at_verein_sp` int(11) DEFAULT NULL,
  `at_verein_si` int(11) DEFAULT NULL,
  `at_verein_un` int(11) DEFAULT NULL,
  `at_verein_ni` int(11) DEFAULT NULL,
  `at_verein_tg` int(11) DEFAULT NULL,
  `at_verein_tk` int(11) DEFAULT NULL,
  `at_verein_punke` int(11) DEFAULT NULL,
  `at_verein_hsp` int(11) DEFAULT NULL,
  `at_verein_hsi` int(11) DEFAULT NULL,
  `at_verein_hun` int(11) DEFAULT NULL,
  `at_verein_hni` int(11) DEFAULT NULL,
  `at_verein_htg` int(11) DEFAULT NULL,
  `at_verein_htk` int(11) DEFAULT NULL,
  `at_verein_hpunke` int(11) DEFAULT NULL,
  `at_verein_asp` int(11) DEFAULT NULL,
  `at_verein_asi` int(11) DEFAULT NULL,
  `at_verein_aun` int(11) DEFAULT NULL,
  `at_verein_ani` int(11) DEFAULT NULL,
  `at_verein_atg` int(11) DEFAULT NULL,
  `at_verein_atk` int(11) DEFAULT NULL,
  `at_verein_apunke` int(11) DEFAULT NULL,
  `at_verein_hrsp` int(11) DEFAULT NULL,
  `at_verein_hrsi` int(11) DEFAULT NULL,
  `at_verein_hrun` int(11) DEFAULT NULL,
  `at_verein_hrni` int(11) DEFAULT NULL,
  `at_verein_hrtg` int(11) DEFAULT NULL,
  `at_verein_hrtk` int(11) DEFAULT NULL,
  `at_verein_hrpunke` int(11) DEFAULT NULL,
  `at_verein_rrsp` int(11) DEFAULT NULL,
  `at_verein_rrsi` int(11) DEFAULT NULL,
  `at_verein_rrun` int(11) DEFAULT NULL,
  `at_verein_rrni` int(11) DEFAULT NULL,
  `at_verein_rrtg` int(11) DEFAULT NULL,
  `at_verein_rrtk` int(11) DEFAULT NULL,
  `at_verein_rrpunke` int(11) DEFAULT NULL,
  `at_verein_form` char(70) NOT NULL,
  PRIMARY KEY (`id_verein`)
)
ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
