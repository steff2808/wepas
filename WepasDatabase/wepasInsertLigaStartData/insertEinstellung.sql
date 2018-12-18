--
-- Table structure for table `en_einstellung`
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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


--
-- Daten für Tabelle `en_einstellung`
--

INSERT INTO `en_einstellung` (`id_einstellung`, `fk_variabel`, `at_einstellung_schluessel`, `at_einstellung_wert_bool`, `at_einstellung_wert_int`, `at_einstellung_wert_dec`, `at_einstellung_wert_cha10`, `at_einstellung_wert_cha30`) VALUES
(1, 0, 'gesamt01', 1, 0, 40.00, '', ''),
(2, 0, 'gesamt02', 1, 0, 30.00, '', ''),
(3, 0, 'gesamt03', 1, 0, 20.00, '', ''),
(4, 0, 'gesamt04', 1, 0, 10.00, '', ''),
(5, 0, 'gesamt05', 1, 0, 0.00, '', ''),
(6, 0, 'gesamt06', 1, 0, 0.00, '', ''),
(7, 0, 'gesamt07', 1, 0, 0.00, '', ''),
(8, 0, 'gesamt08', 1, 0, 0.00, '', ''),
(9, 0, 'gesamt09', 1, 0, 0.00, '', ''),
(10, 0, 'gesamt10', 1, 0, 0.00, '', ''),
(11, 0, 'einzel01', 1, 0, 70.00, '', ''),
(12, 0, 'einzel02', 1, 0, 30.00, '', ''),
(13, 0, 'einzel03', 1, 0, 0.00, '', ''),
(14, 0, 'einzel04', 1, 0, 0.00, '', ''),
(16, 0, 'land', 1, 0, 0.00, 'so', 'Sonstiges'),
(17, 0, 'land', 1, 0, 0.00, 'sh', 'Schleswig-Holstein'),
(18, 0, 'land', 1, 0, 0.00, 'hh', 'Hamburg'),
(19, 0, 'land', 1, 0, 0.00, 'ns', 'Niedersachsen'),
(20, 0, 'saison', 1, 0, 0.00, '', 'Tippsaison Hinrunde 2018'),
(21, 0, 'tunier', 0, 0, 0.00, 'L', 'Boolischer Wert true (1) = Tunier'),
(22, 0, 'gruppe', 1, 0, 0.00, '', 'Gruppe A'),
(23, 0, 'gruppe', 1, 0, 0.00, '', 'Gruppe B'),
(24, 0, 'gruppe', 1, 0, 0.00, '', 'Gruppe C'),
(25, 0, 'gruppe', 1, 0, 0.00, '', 'Gruppe D'),
(26, 0, 'gruppe', 1, 0, 0.00, '', 'Gruppe E'),
(27, 0, 'gruppe', 1, 0, 0.00, '', 'Gruppe F'),
(28, 0, 'gruppe', 1, 0, 0.00, '', 'Gruppe G'),
(29, 0, 'gruppe', 1, 0, 0.00, '', 'Gruppe H'),
(33, 0, 'finale', 1, 0, 0.00, '', 'Achtelfinale'),
(34, 0, 'finale', 1, 0, 0.00, '', 'Viertelfinale'),
(35, 0, 'finale', 1, 0, 0.00, '', 'Halbfinale'),
(36, 0, 'finale', 1, 0, 0.00, '', 'Finale'),
(40, 50, 'wettbewe', 1, 0, 0.00, 'BL', 'Bundesliga'),
(44, 53, 'wettbewe', 1, 0, 0.00, 'WM', 'Weltmeisterschaft'),
(45, 55, 'wettbewe', 1, 0, 0.00, 'EM', 'Europameisterschaft'),
(50, 0, 'blfeed', 1, 0, 0.00, '', 'http://rss.kicker.de/live/bundesliga'),
(54, 0, 'wmfeed', 1, 0, 0.00, '', 'http://rss.kicker.de/live/wm'),
(55, 0, 'emfeed', 1, 0, 0.00, '', 'http://rss.kicker.de/live/em'),
(60, 0, 'gesamt11', 1, 0, 0.00, '', ''),
(61, 0, 'gesamt12', 1, 0, 0.00, '', ''),
(62, 0, 'gesamt13', 1, 0, 0.00, '', ''),
(63, 0, 'gesamt14', 1, 0, 0.00, '', '');

