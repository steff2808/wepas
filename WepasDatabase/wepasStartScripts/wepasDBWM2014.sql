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
  `fk_variabel` int(11) NOT NULL,
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

INSERT INTO `en_einstellung` (`id_einstellung`, `fk_variabel`, `at_einstellung_schluessel`, `at_einstellung_wert_bool`, `at_einstellung_wert_int`, `at_einstellung_wert_dec`, `at_einstellung_wert_cha10`, `at_einstellung_wert_cha30`) VALUES
(1, 0, 'gesamt01', 1, 0, 45.00, '', ''),
(2, 0, 'gesamt02', 1, 0, 40.00, '', ''),
(3, 0, 'gesamt03', 1, 0, 35.00, '', ''),
(4, 0, 'gesamt04', 1, 0, 30.00, '', ''),
(5, 0, 'gesamt05', 1, 0, 25.00, '', ''),
(6, 0, 'gesamt06', 1, 0, 20.00, '', ''),
(7, 0, 'gesamt07', 1, 0, 15.00, '', ''),
(8, 0, 'gesamt08', 1, 0, 10.00, '', ''),
(9, 0, 'gesamt09', 1, 0,  5.00, '', ''),
(10, 0, 'gesamt10', 1, 0, 0.00, '', ''),
(11, 0, 'einzel01', 1, 0, 0.00, '', ''),
(12, 0, 'einzel02', 1, 0, 0.00, '', ''),
(13, 0, 'einzel03', 1, 0, 0.00, '', ''),
(14, 0, 'einzel04', 1, 0, 0.00, '', ''),
(17, 0, 'land', 1, 0, 0.00, 'sh', 'Schleswig-Holstein'),
(18, 0, 'land', 1, 0, 0.00, 'hh', 'Hamburg'),
(19, 0, 'land', 1, 0, 0.00, 'ns', 'Niedersachsen'),
(20, 0, 'saison', 1, 0, 0.00, '', 'WM-Tipprunde<br>Brasilien 2014'),
(21, 0, 'tunier', 1, 0, 0.00, 'T', 'Boolischer Wert true (1) = Tunier'),
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
(41, 51, 'wettbewe', 1, 0, 0.00, 'DP', 'DFB-Pokal'),
(42, 52, 'wettbewe', 1, 0, 0.00, 'CL', 'Championsleague'),
(43, 53, 'wettbewe', 1, 0, 0.00, 'EL', 'Euroleague'),
(44, 53, 'wettbewe', 1, 0, 0.00, 'WM', 'Weltmeisterschaft'),
(45, 55, 'wettbewe', 1, 0, 0.00, 'EM', 'Europameisterschaft'),
(50, 0, 'blfeed', 1, 0, 0.00, '', 'http://rss.kicker.de/live/bundesliga'),
(51, 0, 'dpfeed', 1, 0, 0.00, '', 'http://rss.kicker.de/live/bundesliga'),
(52, 0, 'clfees', 1, 0, 0.00, '', 'http://rss.kicker.de/live/bundesliga'),
(53, 0, 'elfeed', 1, 0, 0.00, '', 'http://rss.kicker.de/live/bundesliga'),
(54, 0, 'wmfeed', 1, 0, 0.00, '', 'http://rss.kicker.de/live/bundesliga'),
(55, 0, 'emfeed', 1, 0, 0.00, '', 'http://rss.kicker.de/live/bundesliga');
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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Daten für Tabelle `en_forum`
--

INSERT INTO `en_forum` (`id_forum`, `id_tipper`, `at_forum_type`, `at_forum_text1`, `at_forum_text2`) VALUES
(1, 1, 'F', 'Mo, 12. Mai 18:45 <b>Stefan Meister: </b>', 'WM in Brasilien...es geht wieder los!');

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
  `fk_spiel_einstellung` int(11) DEFAULT NULL,
  `at_spiel_tore_heim` int(11) DEFAULT NULL,
  `at_spiel_tore_gast` int(11) DEFAULT NULL,
  `at_spiel_is_played` tinyint(1) DEFAULT NULL,
  `at_spiel_anpfiff` datetime DEFAULT NULL,
  PRIMARY KEY (`id_spiel`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Daten für Tabelle `en_spiel`
--

INSERT INTO `en_spiel` (`id_spiel`, `fk_spiel_verein_heim`, `fk_spiel_verein_gast`, `fk_spiel_spieltag`, `fk_spiel_einstellung`, `at_spiel_tore_heim`, `at_spiel_tore_gast`, `at_spiel_is_played`, `at_spiel_anpfiff`) VALUES
( 1,  6, 12, 1, 22, 0, 0, 0, '2014-06-12 22:00:00'),
( 2, 25,  9, 1, 22, 0, 0, 0, '2014-06-13 18:00:00'),
( 3, 15, 26, 1, 23, 0, 0, 0, '2014-06-13 21:00:00'),
( 4,  7,  3, 1, 23, 0, 0, 0, '2014-06-13 23:00:00'),
( 5, 10, 19, 1, 24, 0, 0, 0, '2014-06-14 18:00:00'),
( 6,  8, 23, 1, 24, 0, 0, 0, '2014-06-15 03:00:00'),
( 7, 31, 11, 1, 25, 0, 0, 0, '2014-06-14 21:00:00'),
( 8, 14, 22, 1, 25, 0, 0, 0, '2014-06-14 23:00:00'),
( 9, 30, 13, 1, 26, 0, 0, 0, '2014-06-15 18:00:00'),
(10, 16, 20, 1, 26, 0, 0, 0, '2014-06-15 21:00:00'),
(11,  2,  5, 1, 27, 0, 0, 0, '2014-06-16 00:00:00'),
(12, 21, 27, 1, 27, 0, 0, 0, '2014-06-16 21:00:00'),
(13, 17, 28, 1, 28, 0, 0, 0, '2014-06-16 18:00:00'),
(14, 18, 32, 1, 28, 0, 0, 0, '2014-06-16 23:00:00'),
(15,  4,  1, 1, 29, 0, 0, 0, '2014-06-17 22:00:00'),
(16, 29, 24, 1, 29, 0, 0, 0, '2014-06-17 23:00:00'),

(17,  6, 25, 2, 22, 0, 0, 0, '2014-06-17 21:00:00'),
(18,  9, 12, 2, 22, 0, 0, 0, '2014-06-18 23:00:00'),
(19,  3, 26, 2, 23, 0, 0, 0, '2014-06-18 18:00:00'),
(20, 15,  7, 2, 23, 0, 0, 0, '2014-06-18 21:00:00'),
(21, 10,  8, 2, 24, 0, 0, 0, '2014-06-19 18:00:00'),
(22, 23, 19, 2, 24, 0, 0, 0, '2014-06-20 00:00:00'),
(23, 31, 14, 2, 25, 0, 0, 0, '2014-06-19 21:00:00'),
(24, 22, 11, 2, 25, 0, 0, 0, '2014-06-20 18:00:00'),
(25, 30, 16, 2, 26, 0, 0, 0, '2014-06-20 21:00:00'),
(26, 20, 13, 2, 26, 0, 0, 0, '2014-06-21 00:00:00'),
(27,  2, 21, 2, 27, 0, 0, 0, '2014-06-21 18:00:00'),
(28, 27,  5, 2, 27, 0, 0, 0, '2014-06-21 23:00:00'),
(29, 17, 18, 2, 28, 0, 0, 0, '2014-06-21 21:00:00'),
(30, 32, 28, 2, 28, 0, 0, 0, '2014-06-22 23:00:00'),
(31,  4, 29, 2, 29, 0, 0, 0, '2014-06-22 18:00:00'),
(32, 24,  1, 2, 29, 0, 0, 0, '2014-06-22 21:00:00'),

(33,  9,  6, 3, 22, 0, 0, 0, '2014-06-23 22:00:00'),
(34, 12, 25, 3, 22, 0, 0, 0, '2014-06-23 22:00:00'),
(35, 26,  7, 3, 23, 0, 0, 0, '2014-06-23 18:00:00'),
(36,  3, 15, 3, 23, 0, 0, 0, '2014-06-23 18:00:00'),
(37, 23, 10, 3, 24, 0, 0, 0, '2014-06-24 21:00:00'),
(38, 19,  8, 3, 24, 0, 0, 0, '2014-06-24 22:00:00'),
(39, 22, 31, 3, 25, 0, 0, 0, '2014-06-24 18:00:00'),
(40, 11, 14, 3, 25, 0, 0, 0, '2014-06-24 18:00:00'),
(41, 20, 30, 3, 26, 0, 0, 0, '2014-06-25 21:00:00'),
(42, 13, 16, 3, 26, 0, 0, 0, '2014-06-25 22:00:00'),
(43, 27,  2, 3, 27, 0, 0, 0, '2014-06-25 18:00:00'),
(44,  5, 21, 3, 27, 0, 0, 0, '2014-06-25 18:00:00'),
(45, 28, 18, 3, 28, 0, 0, 0, '2014-06-26 18:00:00'),
(46, 32, 17, 3, 28, 0, 0, 0, '2014-06-26 18:00:00'),
(47, 24,  4, 3, 29, 0, 0, 0, '2014-06-26 22:00:00'),
(48,  1, 29, 3, 29, 0, 0, 0, '2014-06-26 22:00:00');



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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Daten für Tabelle `en_spieltag`
--

INSERT INTO `en_spieltag` (`id_spieltag`, `fk_spieltag_einstellung`, `at_spieltag_nummer`, `at_spieltag_text`, `at_spieltag_start`, `at_spieltag_ende`, `at_spieltag_paarungen`, `at_spieltag_supertipps`, `at_spieltag_is_started`, `at_spieltag_is_finished`, `at_spieltag_is_duty`, `at_spieltag_kosten`, `at_spieltag_einsatz`) VALUES
(1, 44, 1, 'Erste Gruppenspiele<br>', '2014-06-12', '2014-06-17', 16, 4, 0, 0, 1, 0.00, 0.00),
(2, 44, 2, 'Zweite Gruppenspiele<br>', '2014-06-18', '2014-06-22', 16, 4, 0, 0, 1, 0.00, 0.00),
(3, 44, 3, 'Dritte Gruppenspiele<br>', '2014-06-23', '2014-08-26', 16, 4, 0, 0, 1, 0.00, 0.00),
(4, 44, 4, 'Achtelfinale<br>', '2014-06-28', '2014-07-01', 8, 2, 0, 0, 1, 0.00, 0.00),
(5, 44, 5, 'Viertelfinale<br>', '2014-07-04', '2014-07-05', 4, 1, 0, 0, 1, 0.00, 0.00),
(6, 44, 6, 'Halbfinale<br>', '2014-07-08', '2014-07-09', 2, 1, 0, 0, 1, 0.00, 0.00),
(7, 44, 7, 'Finalspiele<br>', '2014-07-12', '2014-07-13', 2, 1, 0, 0, 1, 0.00, 0.00);

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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Daten für Tabelle `en_tipp`
--

-- --------------------------------------------------------

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
(20, 'Schmidt-G.', 'Heiner', 'schmhe', 'vitareform', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'sh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
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
(34, 'Woiwode', 'Thomas', 'woiwth', 'BL2013', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(35, 'Bläsing', 'Jana', 'blaeja', 'jana', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'sh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(36, 'Stolley', 'Brigitte', 'stollbr', 'brigitte', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(37, 'Bielefeld', 'Knud', 'bielkn', 'knud', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(38, 'Stark', 'Maya', 'starma', 'maya', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000');


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


(1, 'Algerien', 'alg', '(.*Algerien.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(2, 'Argentinien', 'arg', '(.*Argentinien.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(3, 'Australien', 'aus', '(.*Australien.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(4, 'Belgien', 'bel', '(.*Belgien.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(5, 'Bosnien-H.', 'bih', '(.*Bosnien.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(6, 'Brasilien', 'brs', '(.*Brasilien.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(7, 'Chile', 'chi', '(.*Chile.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(8, 'Elfenbeinküste', 'civ', '(.*Elfenbein.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(9, 'Kamerun', 'cmr', '(.*Kamerun.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(10, 'Kolumbien', 'col', '(.*Kolumbien.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(11, 'Costa Rica', 'crc', '(.*Costa Rica.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(12, 'Kroatien', 'cro', '(.*Kroatien.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(13, 'Ecuador', 'ecu', '(.*Ecuador.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(14, 'England', 'eng', '(.*England.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(15, 'Spanien', 'esp', '(.*Spanien.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(16, 'Frankreich', 'frr', '(.*Frankreich.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(17, 'Deutschland', 'ger', '(.*Deutschland.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(18, 'Ghana', 'gha', '(.*Ghana.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(19, 'Griechenland', 'gre', '(.*Griechenland.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(20, 'Honduras', 'hon', '(.*Honduras.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(21, 'Iran', 'irn', '(.*Iran.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(22, 'Italien', 'ita', '(.*Italien.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(23, 'Japan', 'jpn', '(.*Japan.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(24, 'Südkorea', 'kor', '(.*Südkorea.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(25, 'Mexiko', 'mex', '(.*Mexiko.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(26, 'Niederlande', 'ned', '(.*Niederlande.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(27, 'Nigeria', 'nga', '(.*Nigeria.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(28, 'Portugal', 'por', '(.*Portugal.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(29, 'Russland', 'rus', '(.*Russland.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(30, 'Schweiz', 'sui', '(.*Schweiz.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(31, 'Uruguay', 'uru', '(.*Uruguay.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),

(32, 'Vereinigte Staaten', 'usa', '(.*usa.*)', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx');




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
  `at_wette_punktef` int(11) NOT NULL,
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
