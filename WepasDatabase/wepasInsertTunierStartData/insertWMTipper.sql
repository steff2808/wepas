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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=31 ;

--
-- Dumping data for table `en_tipper`
--

INSERT INTO `en_tipper` (`id_tipper`, `at_tipper_name`, `at_tipper_vorname`, `at_tipper_login`, `at_tipper_password`, `at_tipper_punkte`, `at_tipper_konto`, `at_tipper_siege`, `at_tipper_zweite`, `at_tipper_team`, `at_tipper_mail`, `at_tipper_tel`, `at_tipper_mobil`, `at_tipper_iban`, `at_tipper_form`, `at_tipper_tippennext`) VALUES
(1, 'Meister', 'Stefan', 'meisst', 'Abisk00', 0, 0.00, 0, 0, 'ns', 'stefan-meister@gmx.de', '+4941676807', '+491899611270', 'DE54200505501484488224', '0000000000000000000000000000000000000000000000000000000000000000000000', 1),
(2, 'Almeida', 'Andrea', 'almean', 'ando', 0, 0.00, 0, 0, 'sh', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000', 1),
(3, 'Andersen', 'Georg', 'andege', 'georg', 0, 0.00, 0, 0, 'sh', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000', 1),
(4, 'Bartels', 'Ulrich', 'bartul', 'uli', 0, 0.00, 0, 0, 'hh', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000', 1),
(5, 'Bläsing', 'Arno', 'blaear', 'arno', 0, 0.00, 1, 0, 'sh', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000', 1),
(6, 'Diekmann', 'Jörg', 'diecjo', 'wepas', 0, 0.00, 0, 0, 'sh', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000', 1),
(7, 'Heesch', 'Frank', 'heesfr', 'pauli', 0, 0.00, 0, 0, 'ns', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000', 1),
(8, 'Jacobs', 'Stefan', 'jacost', 'lara1505', 0, 0.00, 0, 0, 'hh', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000', 1),
(9, 'Kasten', 'Marita', 'kastma', 'knuffi', 0, 0.00, 0, 0, 'hh', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000', 1),
(10, 'Biel', 'Stefan', 'bielst', 'stbi', 0, 0.00, 3, 0, 'sh', '', '', '', '', '', 1),
(11, 'Köhler', 'Chris', 'koehch', 'chris', 0, 0.00, 0, 0, 'hh', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000', 1),
(12, 'Kunze', 'Andreas', 'kunzan', 'felix', 0, 0.00, 2, 0, 'hh', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000', 1),
(13, 'Lodders', 'Dieter', 'lodddi', 'DiDi', 0, 0.00, 2, 0, 'sh', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000', 1),
(14, 'Marschke', 'Thomas', 'marsth', '15151', 0, 0.00, 0, 0, 'hh', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000', 1),
(15, 'Neumann', 'Marco', 'neumma', 'pauli', 0, 0.00, 0, 0, 'ns', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000', 1),
(16, 'Rieck', 'Gunnar', 'riecgu', 'bundesliga', 0, 0.00, 0, 0, 'hh', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000', 1),
(17, 'Schilling', 'Michael', 'schimi', 'alexbia', 0, 0.00, 1, 0, 'ns', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000', 1),
(18, 'Schmidt-Gal.', 'Heiner', 'schmhe', 'vitareform', 0, 0.00, 0, 0, 'sh', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000', 1),
(19, 'Schönfeld', 'Ralf', 'schora', 'rsa2010rs', 0, 0.00, 0, 0, 'sh', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000', 1),
(20, 'Schröder', 'Yannik', 'schrya', 'luebeck', 0, 0.00, 2, 0, 'sh', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000', 1),
(21, 'Schwarz', 'Michael', 'schwmi', 'michiblack', 0, 0.00, 0, 0, 'hh', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000', 1),
(22, 'Schwarz', 'Thies Gerrit', 'schwth', 'scholl', 0, 0.00, 0, 0, 'hh', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000', 1),
(23, 'Stark', 'Manfred', 'starma', 'Ball2017', 0, 0.00, 1, 0, 'hh', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000', 1),
(24, 'Stobbe', 'Joachim', 'stobjo', 'hsv', 0, 0.00, 0, 0, 'hh', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000', 1),
(25, 'Stobbe', 'Michel', 'stobmi', 'amica', 0, 0.00, 1, 0, 'hh', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000', 1),
(26, 'Stolley', 'Brigitte', 'stolbr', 'Bundesliga', 0, 0.00, 0, 0, 'sh', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000', 1),
(28, 'von der Weppen', 'Florian', 'wepefl', 'start123', 0, 0.00, 0, 0, 'hh', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000', 1),
(29, 'Wittke', 'Dennis', 'wittde', 'ncc1701e', 0, 0.00, 3, 0, 'hh', '', '', '', '', '0000000000000000000000000000000000000000000000000000000000000000000000', 1),
(30, 'Gaisbauer', 'Toni', 'gaisto', 'trudi', 0, 0.00, 0, 0, '', '', '', '', '', '', 1);
