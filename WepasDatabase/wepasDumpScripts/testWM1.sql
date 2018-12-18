-- phpMyAdmin SQL Dump
-- version 4.0.6deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 19. Mai 2014 um 06:39
-- Server Version: 5.5.35-0ubuntu0.13.10.2
-- PHP-Version: 5.5.3-1ubuntu2.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=34 ;

--
-- Daten für Tabelle `en_einstellung`
--

INSERT INTO `en_einstellung` (`id_einstellung`, `id_tipper`, `at_einstellung_schluessel`, `at_einstellung_wert_bool`, `at_einstellung_wert_int`, `at_einstellung_wert_dec`, `at_einstellung_wert_cha10`, `at_einstellung_wert_cha30`) VALUES
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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=49 ;

--
-- Daten für Tabelle `en_spiel`
--

INSERT INTO `en_spiel` (`id_spiel`, `fk_spiel_verein_heim`, `fk_spiel_verein_gast`, `fk_spiel_spieltag`, `fk_spiel_einstellung`, `at_spiel_tore_heim`, `at_spiel_tore_gast`, `at_spiel_is_played`, `at_spiel_anpfiff`) VALUES
(1, 6, 12, 1, 22, 0, 0, 0, '2014-06-12 22:00:00'),
(2, 25, 9, 1, 22, 0, 0, 0, '2014-06-13 18:00:00'),
(3, 15, 26, 1, 23, 0, 0, 0, '2014-06-13 21:00:00'),
(4, 7, 3, 1, 23, 0, 0, 0, '2014-06-13 23:00:00'),
(5, 10, 19, 1, 24, 0, 0, 0, '2014-06-14 18:00:00'),
(6, 8, 23, 1, 24, 0, 0, 0, '2014-06-15 03:00:00'),
(7, 31, 11, 1, 25, 0, 0, 0, '2014-06-14 21:00:00'),
(8, 14, 22, 1, 25, 0, 0, 0, '2014-06-14 23:00:00'),
(9, 30, 13, 1, 26, 0, 0, 0, '2014-06-15 18:00:00'),
(10, 16, 20, 1, 26, 0, 0, 0, '2014-06-15 21:00:00'),
(11, 2, 5, 1, 27, 0, 0, 0, '2014-06-16 00:00:00'),
(12, 21, 27, 1, 27, 0, 0, 0, '2014-06-16 21:00:00'),
(13, 17, 28, 1, 28, 0, 0, 0, '2014-06-16 18:00:00'),
(14, 18, 32, 1, 28, 0, 0, 0, '2014-06-16 23:00:00'),
(15, 4, 1, 1, 29, 0, 0, 0, '2014-06-17 22:00:00'),
(16, 29, 24, 1, 29, 0, 0, 0, '2014-06-17 23:00:00'),
(17, 6, 25, 2, 22, 0, 0, 0, '2014-06-17 21:00:00'),
(18, 9, 12, 2, 22, 0, 0, 0, '2014-06-18 23:00:00'),
(19, 3, 26, 2, 23, 0, 0, 0, '2014-06-18 18:00:00'),
(20, 15, 7, 2, 23, 0, 0, 0, '2014-06-18 21:00:00'),
(21, 10, 8, 2, 24, 0, 0, 0, '2014-06-19 18:00:00'),
(22, 23, 19, 2, 24, 0, 0, 0, '2014-06-20 00:00:00'),
(23, 31, 14, 2, 25, 0, 0, 0, '2014-06-19 21:00:00'),
(24, 22, 11, 2, 25, 0, 0, 0, '2014-06-20 18:00:00'),
(25, 30, 16, 2, 26, 0, 0, 0, '2014-06-20 21:00:00'),
(26, 20, 13, 2, 26, 0, 0, 0, '2014-06-21 00:00:00'),
(27, 2, 21, 2, 27, 0, 0, 0, '2014-06-21 18:00:00'),
(28, 27, 5, 2, 27, 0, 0, 0, '2014-06-21 23:00:00'),
(29, 17, 18, 2, 28, 0, 0, 0, '2014-06-21 21:00:00'),
(30, 32, 28, 2, 28, 0, 0, 0, '2014-06-22 23:00:00'),
(31, 4, 29, 2, 29, 0, 0, 0, '2014-06-22 18:00:00'),
(32, 24, 1, 2, 29, 0, 0, 0, '2014-06-22 21:00:00'),
(33, 9, 6, 3, 22, 0, 0, 0, '2014-06-23 22:00:00'),
(34, 12, 25, 3, 22, 0, 0, 0, '2014-06-23 22:00:00'),
(35, 26, 7, 3, 23, 0, 0, 0, '2014-06-23 18:00:00'),
(36, 3, 15, 3, 23, 0, 0, 0, '2014-06-23 18:00:00'),
(37, 23, 10, 3, 24, 0, 0, 0, '2014-06-24 21:00:00'),
(38, 19, 8, 3, 24, 0, 0, 0, '2014-06-24 22:00:00'),
(39, 22, 31, 3, 25, 0, 0, 0, '2014-06-24 18:00:00'),
(40, 11, 14, 3, 25, 0, 0, 0, '2014-06-24 18:00:00'),
(41, 20, 30, 3, 26, 0, 0, 0, '2014-06-25 21:00:00'),
(42, 13, 16, 3, 26, 0, 0, 0, '2014-06-25 22:00:00'),
(43, 27, 2, 3, 27, 0, 0, 0, '2014-06-25 18:00:00'),
(44, 5, 21, 3, 27, 0, 0, 0, '2014-06-25 18:00:00'),
(45, 28, 18, 3, 28, 0, 0, 0, '2014-06-26 18:00:00'),
(46, 32, 17, 3, 28, 0, 0, 0, '2014-06-26 18:00:00'),
(47, 24, 4, 3, 29, 0, 0, 0, '2014-06-26 22:00:00'),
(48, 1, 29, 3, 29, 0, 0, 0, '2014-06-26 22:00:00');

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
(1, NULL, 1, 'Erste Gruppenspiele<br>', '2014-06-12', '2014-06-17', 16, 4, 0, 0, 1, 0.00, 0.00),
(2, NULL, 2, 'Zweite Gruppenspiele<br>', '2014-06-18', '2014-06-22', 16, 4, 0, 0, 1, 0.00, 0.00),
(3, NULL, 3, 'Dritte Gruppenspiele<br>', '2014-06-23', '2014-08-26', 16, 4, 0, 0, 1, 0.00, 0.00),
(4, NULL, 4, 'Achtelfinale<br>', '2014-06-28', '2014-07-01', 8, 2, 0, 0, 1, 0.00, 0.00),
(5, NULL, 5, 'Viertelfinale<br>', '2014-07-04', '2014-07-05', 4, 1, 0, 0, 1, 0.00, 0.00),
(6, NULL, 6, 'Halbfinale<br>', '2014-07-08', '2014-07-09', 2, 1, 0, 0, 1, 0.00, 0.00),
(7, NULL, 7, 'Finalspiele<br>', '2014-07-12', '2014-07-13', 2, 1, 0, 0, 1, 0.00, 0.00);

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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=241 ;

--
-- Daten für Tabelle `en_tipp`
--

INSERT INTO `en_tipp` (`id_tipp`, `id_spiel`, `id_tipper`, `at_tipp_tor_heim`, `at_tipp_tor_gast`, `at_tipp_is_supertipp`, `at_tipp_is_tendenz`, `at_tipp_is_differenz`, `at_tipp_is_exakt`, `at_tipp_is_valid`, `at_tipp_punkte`, `at_tipp_punktem`, `at_tipp_punktek`) VALUES
(1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(2, 2, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(3, 3, 1, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(4, 4, 1, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0),
(5, 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(6, 6, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(7, 7, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(8, 8, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(9, 9, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(10, 10, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(11, 11, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(12, 12, 1, 4, 1, 1, 0, 0, 0, 0, 0, 0, 0),
(13, 13, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(14, 14, 1, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(15, 15, 1, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0),
(16, 16, 1, 3, 1, 1, 0, 0, 0, 0, 0, 0, 0),
(17, 1, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(18, 2, 2, 8, 2, 1, 0, 0, 0, 0, 0, 0, 0),
(19, 3, 2, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(20, 4, 2, 3, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(21, 5, 2, 6, 1, 1, 0, 0, 0, 0, 0, 0, 0),
(22, 6, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(23, 7, 2, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(24, 8, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(25, 9, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(26, 10, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0),
(27, 11, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(28, 12, 2, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(29, 13, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(30, 14, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(31, 15, 2, 3, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(32, 16, 2, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0),
(33, 1, 13, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0),
(34, 2, 13, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0),
(35, 3, 13, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0),
(36, 4, 13, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0),
(37, 5, 13, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0),
(38, 6, 13, 1, 5, 0, 0, 0, 0, 0, 0, 0, 0),
(39, 7, 13, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(40, 8, 13, 1, 5, 0, 0, 0, 0, 0, 0, 0, 0),
(41, 9, 13, 4, 3, 0, 0, 0, 0, 0, 0, 0, 0),
(42, 10, 13, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(43, 11, 13, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(44, 12, 13, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(45, 13, 13, 3, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(46, 14, 13, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(47, 15, 13, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(48, 16, 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(49, 1, 7, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(50, 2, 7, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(51, 3, 7, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(52, 4, 7, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(53, 5, 7, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0),
(54, 6, 7, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(55, 7, 7, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(56, 8, 7, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0),
(57, 9, 7, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0),
(58, 10, 7, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0),
(59, 11, 7, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(60, 12, 7, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(61, 13, 7, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(62, 14, 7, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(63, 15, 7, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(64, 16, 7, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(65, 1, 6, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(66, 2, 6, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(67, 3, 6, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(68, 4, 6, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(69, 5, 6, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(70, 6, 6, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(71, 7, 6, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(72, 8, 6, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(73, 9, 6, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0),
(74, 10, 6, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0),
(75, 11, 6, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0),
(76, 12, 6, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0),
(77, 13, 6, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(78, 14, 6, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(79, 15, 6, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(80, 16, 6, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(81, 1, 32, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(82, 2, 32, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(83, 3, 32, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0),
(84, 4, 32, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(85, 5, 32, 4, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(86, 6, 32, 8, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(87, 7, 32, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(88, 8, 32, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0),
(89, 9, 32, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0),
(90, 10, 32, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0),
(91, 11, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(92, 12, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(93, 13, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(94, 14, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(95, 15, 32, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(96, 16, 32, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(97, 1, 12, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(98, 2, 12, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(99, 3, 12, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(100, 4, 12, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(101, 5, 12, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(102, 6, 12, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(103, 7, 12, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(104, 8, 12, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(105, 9, 12, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(106, 10, 12, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(107, 11, 12, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(108, 12, 12, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(109, 13, 12, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0),
(110, 14, 12, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0),
(111, 15, 12, 3, 1, 1, 0, 0, 0, 0, 0, 0, 0),
(112, 16, 12, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0),
(113, 1, 15, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(114, 2, 15, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(115, 3, 15, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0),
(116, 4, 15, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0),
(117, 5, 15, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0),
(118, 6, 15, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0),
(119, 7, 15, 4, 3, 1, 0, 0, 0, 0, 0, 0, 0),
(120, 8, 15, 5, 4, 1, 0, 0, 0, 0, 0, 0, 0),
(121, 9, 15, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(122, 10, 15, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0),
(123, 11, 15, 4, 5, 0, 0, 0, 0, 0, 0, 0, 0),
(124, 12, 15, 5, 6, 0, 0, 0, 0, 0, 0, 0, 0),
(125, 13, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(126, 14, 15, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(127, 15, 15, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(128, 16, 15, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(129, 1, 9, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(130, 2, 9, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(131, 3, 9, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(132, 4, 9, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(133, 5, 9, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(134, 6, 9, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(135, 7, 9, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(136, 8, 9, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(137, 9, 9, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0),
(138, 10, 9, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0),
(139, 11, 9, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0),
(140, 12, 9, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0),
(141, 13, 9, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(142, 14, 9, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(143, 15, 9, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(144, 16, 9, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(145, 1, 5, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(146, 2, 5, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(147, 3, 5, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(148, 4, 5, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(149, 5, 5, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0),
(150, 6, 5, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0),
(151, 7, 5, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0),
(152, 8, 5, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0),
(153, 9, 5, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(154, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(155, 11, 5, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(156, 12, 5, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(157, 13, 5, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(158, 14, 5, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(159, 15, 5, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(160, 16, 5, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(161, 1, 31, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(162, 2, 31, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0),
(163, 3, 31, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0),
(164, 4, 31, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0),
(165, 5, 31, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0),
(166, 6, 31, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(167, 7, 31, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0),
(168, 8, 31, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(169, 9, 31, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(170, 10, 31, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(171, 11, 31, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(172, 12, 31, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(173, 13, 31, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(174, 14, 31, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(175, 15, 31, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(176, 16, 31, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(177, 1, 22, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(178, 2, 22, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(179, 3, 22, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(180, 4, 22, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(181, 5, 22, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(182, 6, 22, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(183, 7, 22, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(184, 8, 22, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0),
(185, 9, 22, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0),
(186, 10, 22, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0),
(187, 11, 22, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0),
(188, 12, 22, 3, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(189, 13, 22, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(190, 14, 22, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(191, 15, 22, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(192, 16, 22, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(193, 1, 25, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0),
(194, 2, 25, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0),
(195, 3, 25, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0),
(196, 4, 25, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0),
(197, 5, 25, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(198, 6, 25, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(199, 7, 25, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(200, 8, 25, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(201, 9, 25, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(202, 10, 25, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(203, 11, 25, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(204, 12, 25, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(205, 13, 25, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(206, 14, 25, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(207, 15, 25, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(208, 16, 25, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(209, 1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(210, 2, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(211, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(212, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(213, 5, 4, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0),
(214, 6, 4, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0),
(215, 7, 4, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0),
(216, 8, 4, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0),
(217, 9, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(218, 10, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(219, 11, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(220, 12, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(221, 13, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(222, 14, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(223, 15, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(224, 16, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(225, 1, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(226, 2, 3, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(227, 3, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0),
(228, 4, 3, 3, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(229, 5, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(230, 6, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0),
(231, 7, 3, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0),
(232, 8, 3, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0),
(233, 9, 3, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(234, 10, 3, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0),
(235, 11, 3, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(236, 12, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(237, 13, 3, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0),
(238, 14, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(239, 15, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(240, 16, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0);

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

INSERT INTO `en_tipper` (`id_tipper`, `at_tipper_name`, `at_tipper_vorname`, `at_tipper_login`, `at_tipper_password`, `at_tipper_punkte`, `at_tipper_konto`, `at_tipper_pflichtsiege`, `at_tipper_pflichtzweite`, `at_tipper_pflichtdritte`, `at_tipper_freiesiege`, `at_tipper_freiezweite`, `at_tipper_freiedritte`, `at_tipper_punktef`, `at_tipper_kontof`, `at_tipper_punktem`, `at_tipper_punktek`, `at_tipper_team`, `at_tipper_fraktion`, `at_tipper_mail`, `at_tipper_tel`, `at_tipper_mobil`, `at_tipper_iban`, `at_tipper_form`) VALUES
(1, 'Meister', 'Stefan', 'meisst', 'Abisk00', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'ns', '', 'stefan-meister@gmx.de', '+4941676807', '+491899611270', 'DE54200505501484488224', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(2, 'Bläsing', 'Arno', 'blaear', 'arno', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'sh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(3, 'Diekmann', 'Jörg', 'diecjo', 'wepas', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'sh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(4, 'Freiburg', 'Charly', 'freiwo', 'bulova2000', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'sh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(5, 'Görlitz', 'Wolfgang', 'goerwo', 'alana', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'sh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(6, 'Heesch', 'Frank', 'heesfr', 'pauli', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'ns', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(7, 'Heupel', 'Gunnar', 'heupgu', 'bundesliga', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(8, 'Jacobs', 'Stefan', 'jacost', 'lara1505', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(9, 'Kasten', 'Marita', 'kastma', 'knuffi', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(10, 'Kremer', 'Axel', 'kremax', 'yoco2011', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'ns', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(11, 'Kunze', 'Andreas', 'kunzan', 'felix', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(12, 'Kunze', 'Felix', 'kunzfe', 'felix', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(13, 'Lodders', 'Dieter', 'lodddi', 'DiDi', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'sh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(14, 'Marschke', 'Thomas', 'marsth', '15151', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(15, 'Meyer', 'Markus', 'meyema', 'mischer', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'ns', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(16, 'Mutz', 'Günter', 'mutzgu', 'besttip', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'ns', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(17, 'Neumann', 'Marco', 'neumma', 'pauli', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'ns', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(18, 'Saft', 'Detlef', 'saftde', 'schnuffi', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'ns', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(19, 'Schilling', 'Michael', 'schimi', 'alexbia', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'ns', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(20, 'Schmidt-G.', 'Heiner', 'schmhe', 'vitareform', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'sh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(21, 'Schönfeld', 'Ralf', 'schora', 'rsa2010rs', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'sh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(22, 'Schröder', 'Yannik', 'schrya', 'luebeck', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'sh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(23, 'Schwarz', 'Michael', 'schwmi', 'michiblack', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(24, 'Schwarz', 'Thies Gerrit', 'schwth', 'scholl', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(25, 'Stark', 'Manfred', 'starma', 'sap-2011', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(26, 'Stobbe', 'Joachim', 'stobjo', 'hsv', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(27, 'Stobbe', 'Jürgen', 'stobju', 'test', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'sh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(28, 'Stobbe', 'Michel', 'stobmi', 'amica', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(29, 'Teerling', 'Monika', 'teermo', 'alea', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'sh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(30, 'von der Weppen', 'Florian', 'wepefl', 'start123', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(31, 'Westphalen', 'Jens', 'westje', 'jenne2305', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'sh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(32, 'Wittke', 'Dennis', 'wittde', 'ncc1701e', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(33, 'Witt', 'Andre', 'wittan', 'EffCom', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'ns', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000'),
(34, 'Woiwode', 'Thomas', 'woiwth', 'BL2013', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(35, 'Bläsing', 'Jana', 'blaeja', 'jana', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'sh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(36, 'Stolley', 'Brigitte', 'stollbr', 'brigitte', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000'),
(37, 'Bielefeld', 'Knud', 'bielkn', 'knud', 0, 0.00, 0, 0, 0, 0, 0, 0, 0, 0.00, 0, 0, 'hh', '', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000000');
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
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
