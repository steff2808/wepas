-- phpMyAdmin SQL Dump
-- version 3.5.8.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 12. Mai 2014 um 10:28
-- Server Version: 5.5.34-0ubuntu0.13.04.1
-- PHP-Version: 5.4.9-4ubuntu2.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `wepas`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `en_einstellung`
--

DROP TABLE IF EXISTS `en_einstellung`;
CREATE TABLE IF NOT EXISTS `en_einstellung` (
  `id_einstellung` int(11) NOT NULL AUTO_INCREMENT,
  `id_tipper` int(11) NOT NULL,
  `at_einstellung_schluessel` char(8) NOT NULL,
  `at_einstellung_wert_bool` tinyint(1) NOT NULL,
  `at_einstellung_wert_int` int(11) NOT NULL,
  `at_einstellung_wert_dec` decimal(5,2) NOT NULL,
  `at_einstellung_wert_cha10` char(10) NOT NULL,
  `at_einstellung_wert_cha30` char(30) NOT NULL,
  PRIMARY KEY (`id_einstellung`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=22 ;

--
-- Daten für Tabelle `en_einstellung`
--

INSERT INTO `en_einstellung` (`id_einstellung`, `id_tipper`, `at_einstellung_schluessel`, `at_einstellung_wert_bool`, `at_einstellung_wert_int`, `at_einstellung_wert_dec`, `at_einstellung_wert_cha10`, `at_einstellung_wert_cha30`) VALUES
(1, 0, 'gesamt01', 1, 0, 40.80, '', ''),
(2, 0, 'gesamt02', 1, 0, 30.60, '', ''),
(3, 0, 'gesamt03', 1, 0, 20.40, '', ''),
(4, 0, 'gesamt04', 1, 0, 10.20, '', ''),
(5, 0, 'gesamt05', 1, 0, 0.00, '', ''),
(6, 0, 'gesamt06', 1, 0, 0.00, '', ''),
(7, 0, 'einzel01', 1, 0, 70.00, '', ''),
(8, 0, 'einzel02', 1, 0, 30.00, '', ''),
(9, 0, 'einzel03', 1, 0, 0.00, '', ''),
(10, 0, 'einzel04', 1, 0, 0.00, '', ''),
(11, 0, 'land', 1, 0, 0.00, 'sh', 'Schleswig-Holstein'),
(12, 0, 'land', 1, 0, 0.00, 'hh', 'Hamburg'),
(13, 0, 'land', 1, 0, 0.00, 'ns', 'Niedersachsen'),
(20, 0, 'saison', 1, 0, 0.00, '', 'BL-Tipprunde 2014/15'),
(21, 0, 'tunier', 0, 0, 0.00, 'L', ''),
(22, 0, 'wmgruppe', 1, 0, 0.00, '', 'Gruppe A'),
(23, 0, 'wmgruppe', 1, 0, 0.00, '', 'Gruppe B'),
(24, 0, 'wmgruppe', 1, 0, 0.00, '', 'Gruppe C'),
(25, 0, 'wmgruppe', 1, 0, 0.00, '', 'Gruppe D'),
(26, 0, 'wmgruppe', 1, 0, 0.00, '', 'Gruppe E'),
(27, 0, 'wmgruppe', 1, 0, 0.00, '', 'Gruppe F'),
(28, 0, 'wmgruppe', 1, 0, 0.00, '', 'Gruppe G'),
(29, 0, 'wmgruppe', 1, 0, 0.00, '', 'Gruppe H'),
(30, 0, 'wettbewerb', 1, 0, 0.00, '', 'Bundesliga'),
(31, 0, 'wettbewerb', 1, 0, 0.00, '', 'DFB-Pokal'),
(32, 0, 'wettbewerb', 1, 0, 0.00, '', 'Championsleague'),
(33, 0, 'wettbewerb', 1, 0, 0.00, '', 'Euroleague');



-- --------------------------------------------------------

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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=67 ;

--
-- Daten für Tabelle `en_forum`
--

INSERT INTO `en_forum` (`id_forum`, `id_tipper`, `at_forum_type`, `at_forum_text1`, `at_forum_text2`) VALUES
(1, 1, 'F', 'Mo, 12. Mai 18:45 <b>Stefan Meister: </b>', 'Bundesliga 2014/15...es geht wieder los!');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `en_spiel`
--

DROP TABLE IF EXISTS `en_spiel`;
CREATE TABLE IF NOT EXISTS `en_spiel` (
  `id_spiel` int(11) NOT NULL AUTO_INCREMENT,
  `fk_spiel_verein_heim` int(11) NOT NULL,
  `fk_spiel_verein_gast` int(11) NOT NULL,
  `fk_spiel_spieltag` int(11) NOT NULL,
  `at_spiel_tore_heim` int(11) DEFAULT NULL,
  `at_spiel_tore_gast` int(11) DEFAULT NULL,
  `at_spiel_is_played` tinyint(1) DEFAULT NULL,
  `at_spiel_anpfiff` datetime DEFAULT NULL,
  PRIMARY KEY (`id_spiel`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=307 ;

--
-- Daten für Tabelle `en_spiel`
--


-- --------------------------------------------------------

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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=35 ;

--
-- Daten für Tabelle `en_spieltag`
--

INSERT INTO `en_spieltag` (`id_spieltag`, `fk_spieltag_einstellung`, `at_spieltag_nummer`, `at_spieltag_text`, `at_spieltag_start`, `at_spieltag_ende`, `at_spieltag_paarungen`, `at_spieltag_supertipps`, `at_spieltag_is_started`, `at_spieltag_is_finished`, `at_spieltag_is_duty`, `at_spieltag_kosten`, `at_spieltag_einsatz`)
(1, 1, '1. Spieltag', '2013-08-09', '2013-08-11', 9, 2, 1, 1, 1, 1.00, 34.00),
(2, 2, '2. Spieltag', '2013-08-17', '2013-08-18', 9, 2, 1, 1, 1, 1.00, 34.00),
(3, 3, '3. Spieltag', '2013-08-23', '2013-08-25', 9, 2, 1, 1, 1, 1.00, 34.00),
(4, 4, '4. Spieltag', '2013-08-27', '2013-09-01', 9, 2, 1, 1, 1, 1.00, 34.00),
(5, 5, '5. Spieltag', '2013-09-13', '2013-09-15', 9, 2, 1, 1, 1, 1.00, 34.00),
(6, 6, '6. Spieltag', '2013-09-20', '2013-09-22', 9, 2, 1, 1, 1, 1.00, 34.00),
(7, 7, '7. Spieltag', '2013-09-27', '2013-09-29', 9, 2, 1, 1, 1, 1.00, 34.00),
(8, 8, '8. Spieltag', '2013-10-04', '2013-10-06', 9, 2, 1, 1, 1, 1.00, 34.00),
(9, 9, '9. Spieltag', '2013-10-18', '2013-10-20', 9, 2, 1, 1, 1, 1.00, 34.00),
(10, 10, '10. Spieltag', '2013-10-25', '2013-10-27', 9, 2, 1, 1, 1, 1.00, 34.00),
(11, 11, '11. Spieltag', '2013-11-01', '2013-11-03', 9, 2, 1, 1, 1, 1.00, 34.00),
(12, 12, '12. Spieltag', '2013-11-08', '2013-11-10', 9, 2, 1, 1, 1, 1.00, 34.00),
(13, 13, '13. Spieltag', '2013-11-22', '2013-11-24', 9, 2, 1, 1, 1, 1.00, 34.00),
(14, 14, '14. Spieltag', '2013-11-29', '2013-12-01', 9, 2, 1, 1, 1, 1.00, 34.00),
(15, 15, '15. Spieltag', '2013-12-06', '2013-12-08', 9, 2, 1, 1, 1, 1.00, 34.00),
(16, 16, '16. Spielatag', '2013-12-13', '2013-12-15', 9, 2, 1, 1, 1, 1.00, 34.00),
(17, 17, '17. Spieltag', '2013-12-20', '2013-12-22', 9, 2, 1, 1, 1, 1.00, 34.00),
(18, 18, '18. Spieltag', '2014-01-24', '2014-01-26', 9, 2, 1, 1, 1, 1.00, 34.00),
(19, 19, '19. Spieltag', '2014-01-31', '2014-02-02', 9, 2, 1, 1, 1, 1.00, 34.00),
(20, 20, '20. Spieltag', '2014-02-07', '2014-02-09', 9, 2, 1, 1, 1, 1.00, 34.00),
(21, 21, '21. Spieltag', '2014-02-14', '2014-02-16', 9, 2, 1, 1, 1, 1.00, 34.00),
(22, 22, '22. Spieltag', '2014-02-21', '2014-02-23', 9, 2, 1, 1, 1, 1.00, 34.00),
(23, 23, '23. Spieltag', '2014-02-28', '2014-03-02', 9, 2, 1, 1, 1, 1.00, 34.00),
(24, 24, '24. Spieltag', '2014-03-07', '2014-03-09', 9, 2, 1, 1, 1, 1.00, 34.00),
(25, 25, '25. Spieltag', '2014-03-14', '2014-03-16', 9, 2, 1, 1, 1, 1.00, 34.00),
(26, 26, '26. Spieltag', '2014-03-21', '2014-03-23', 9, 2, 1, 1, 1, 1.00, 34.00),
(27, 27, '27. Spieltag', '2014-03-25', '2014-03-26', 9, 2, 1, 1, 1, 1.00, 34.00),
(28, 28, '28. Spieltag', '2014-03-28', '2014-03-30', 9, 2, 1, 1, 1, 1.00, 34.00),
(29, 29, '29. Spieltag', '2014-04-04', '2014-04-06', 9, 2, 1, 1, 1, 1.00, 34.00),
(30, 30, '30. Spieltag', '2014-04-11', '2014-04-13', 9, 2, 1, 1, 1, 1.00, 34.00),
(31, 31, '31. Spieltag', '2014-04-17', '2014-04-20', 9, 2, 1, 1, 1, 1.00, 34.00),
(32, 32, '32. Spieltag', '2014-04-25', '2014-04-27', 9, 2, 1, 1, 1, 1.00, 34.00),
(33, 33, '33. Spieltag', '2014-05-03', '2014-05-03', 9, 2, 1, 1, 1, 1.00, 34.00),
(34, 34, 'Letzter Spieltag', '2014-05-10', '2014-05-10', 9, 2, 1, 1, 1, 1.00, 34.00);

-- --------------------------------------------------------

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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10342 ;

--
-- Daten für Tabelle `en_tipp`
--


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
  `at_tipper_pflichtsiege` int(11) NOT NULL,
  `at_tipper_pflichtzweite` int(11) NOT NULL,
  `at_tipper_pflichtdritte` int(11) NOT NULL,
  `at_tipper_freiesiege` int(11) NOT NULL,
  `at_tipper_freiezweite` int(11) NOT NULL,
  `at_tipper_freiedritte` int(11) NOT NULL,
  `at_tipper_punktef` int(11) NOT NULL,
  `at_tipper_kontof` decimal(5,2) NOT NULL,
  `at_tipper_punktem` int(11) NOT NULL,
  `at_tipper_punktek` int(11) NOT NULL,
  `at_tipper_team` char(2) NOT NULL,
  `at_tipper_fraktion` char(2) NOT NULL,
  `at_tipper_mail` char(30) NOT NULL,
  `at_tipper_tel` char(20) NOT NULL,
  `at_tipper_mobil` char(20) NOT NULL,
  `at_tipper_iban` char(22) NOT NULL,
  `at_tipper_form` char(70) NOT NULL,
  PRIMARY KEY (`id_tipper`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=35 ;

--
-- Daten für Tabelle `en_tipper`
--

INSERT INTO `en_tipper` (`id_tipper`, `at_tipper_name`, `at_tipper_vorname`, `at_tipper_login`, `at_tipper_password`, `at_tipper_punkte`,  `at_tipper_konto`, `at_tipper_pflichtsiege`, `at_tipper_pflichtzweite`, `at_tipper_pflichtdritte`, `at_tipper_freiesiege`,  `at_tipper_freiezweite`, `at_tipper_freiedritte`,  `at_tipper_punktef`,  `at_tipper_kontof`, `at_tipper_punktem`, `at_tipper_punktek`, `at_tipper_team`, `at_tipper_fraktion`, `at_tipper_mail`, `at_tipper_tel`, `at_tipper_mobil`, `at_tipper_iban`, `at_tipper_form`) 
VALUES
(1, 'Meister', 'Stefan', 'meisst', 'Abisk00', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'ns', '', 'stefan-meister@gmx.de', '+4941676807', '+491899611270', 'DE54200505501484488224',
'0000000000000000000000000000000000000000000000000000000000000000000000000'),
(2, 'Bläsing', 'Arno', 'blaear', 'arno', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'sh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(3, 'Diekmann', 'Jörg', 'diecjo', 'wepas', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'sh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(4, 'Freiburg', 'Charly', 'freiwo', 'bulova2000', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'sh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(5, 'Görlitz', 'Wolfgang', 'goerwo', 'alana', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'sh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(6, 'Heesch', 'Frank', 'heesfr', 'pauli', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'ns', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(7, 'Heupel', 'Gunnar', 'heupgu', 'bundesliga', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(8, 'Jacobs', 'Stefan', 'jacost', 'lara1505', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(9, 'Kasten', 'Marita', 'kastma', 'knuffi', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(10, 'Kremer', 'Axel', 'kremax', 'yoco2011', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'ns', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(11, 'Kunze', 'Andreas', 'kunzan', 'felix', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(12, 'Kunze', 'Felix', 'kunzfe', 'felix', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(13, 'Lodders', 'Dieter', 'lodddi', 'DiDi', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'sh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(14, 'Marschke', 'Thomas', 'marsth', '15151', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(15, 'Meyer', 'Markus', 'meyema', 'mischer', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'ns', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(16, 'Mutz', 'Günter', 'mutzgu', 'besttip', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'ns', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(17, 'Neumann', 'Marco', 'neumma', 'pauli', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'ns', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(18, 'Saft', 'Detlef', 'saftde', 'schnuffi', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'ns', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(19, 'Schilling', 'Michael', 'schimi', 'alexbia', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'ns', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(20, 'Schmidt-Gal.', 'Heiner', 'schmhe', 'vitareform', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'sh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(21, 'Schönfeld', 'Ralf', 'schora', 'rsa2010rs', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'sh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(22, 'Schröder', 'Yannik', 'schrya', 'luebeck', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'sh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(23, 'Schwarz', 'Michael', 'schwmi', 'michiblack', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(24, 'Schwarz', 'Thies Gerrit', 'schwth', 'scholl', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(25, 'Stark', 'Manfred', 'starma', 'sap-2011', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(26, 'Stobbe', 'Joachim', 'stobjo', 'hsv', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(27, 'Stobbe', 'Jürgen', 'stobju', 'test', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'sh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(28, 'Stobbe', 'Michel', 'stobmi', 'amica', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(29, 'Teerling', 'Monika', 'teermo', 'alea', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'sh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(30, 'von der Weppen', 'Florian', 'wepefl', 'start123', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0,  'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(31, 'Westphalen', 'Jens', 'westje', 'jenne2305', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'sh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(32, 'Wittke', 'Dennis', 'wittde', 'ncc1701e', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(33, 'Witt', 'Andre', 'wittan', 'EffCom', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'ns', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(34, 'Woiwode', 'Thomas', 'woiwth', 'BL2013', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000');


-- --------------------------------------------------------

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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=33 ;

--
-- Daten für Tabelle `en_verein`
--

INSERT INTO `en_verein` (`id_verein`, `at_verein_name`, `at_verein_kurz`, `at_verein_regex`, `at_verein_sp`, `at_verein_si`, `at_verein_un`, `at_verein_ni`, `at_verein_tg`, `at_verein_tk`, `at_verein_punke`, `at_verein_hsp`, `at_verein_hsi`, `at_verein_hun`, `at_verein_hni`, `at_verein_htg`, `at_verein_htk`, `at_verein_hpunke`, `at_verein_asp`, `at_verein_asi`, `at_verein_aun`, `at_verein_ani`, `at_verein_atg`, `at_verein_atk`, `at_verein_apunke`, `at_verein_hrsp`, `at_verein_hrsi`, `at_verein_hrun`, `at_verein_hrni`, `at_verein_hrtg`, `at_verein_hrtk`, `at_verein_hrpunke`, `at_verein_rrsp`, `at_verein_rrsi`, `at_verein_rrun`, `at_verein_rrni`, `at_verein_rrtg`, `at_verein_rrtk`, `at_verein_rrpunke`, `at_verein_form`) VALUES
(1, 'Hamburger SV', 'hsv', '(.*HSV.*|.*Hamburg.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(2, 'Werder Bremen', 'bre', '(.*Werder.*|.*Bremen.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(3, 'Hannover 96', 'h96', '(.*Hannover.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(4, 'Vfl Wolfsburg', 'wob', '(.*Wolfsburg.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(5, 'Hertha BSC Berlin', 'bsc', '(.*Hertha.*|.*Berlin.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(6, 'Paderborn', 'pad', '(.*Pader.*|.*aderborn.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(7, 'Borussia Dortmund', 'bvb', '(.*BVB.*|.*Dortmund.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(8, 'Schalke 04', 's04', '(.*Schalke.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(9, 'Borussia Mönchengladbach', 'bmg', '(.*(G|g)ladbach.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(10, 'Bayer Leverkusen', 'lev', '(.*Lever.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(11, '1. FC Köln', 'koe', '(.*Köln.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(12, '1. FSV Mainz 05', 'mnz', '(.*Mainz.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(13, 'Eintracht Frankfurt', 'fra', '(.*Frankfurt.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(14, '1899 Hoffenheim', 'hof', '(.*Hoffenheim.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(15, 'VfB Stuttgart', 'vfb', '(.*Stuttgart.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(16, 'SC Freiburg', 'fcf', '(.*Freiburg.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(17, 'FC Augsburg', 'fca', '(.*Augsburg.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(18, 'Bayern München', 'fcb', '(.*Bayern.*|.*München.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx');

-- --------------------------------------------------------
-- --------------------------------------------------------

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
  `at_wette_punktef` tinyint(1) NOT NULL,
  `at_wette_punktem` int(11) NOT NULL,
  `at_wette_punktek` int(11) NOT NULL,
  PRIMARY KEY (`id_wette`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Daten für Tabelle `en_wette`
--

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
