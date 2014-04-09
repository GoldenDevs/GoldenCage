-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Mer 09 Avril 2014 à 13:55
-- Version du serveur: 5.0.27-community-nt
-- Version de PHP: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `goldencage`
--
CREATE DATABASE IF NOT EXISTS `goldencage` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `goldencage`;

-- --------------------------------------------------------

--
-- Structure de la table `administrateur`
--

CREATE TABLE IF NOT EXISTS `administrateur` (
  `login` varchar(20) NOT NULL,
  `last_login` datetime default NULL,
  PRIMARY KEY  (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `administrateur`
--

INSERT INTO `administrateur` (`login`, `last_login`) VALUES
('aeg', '2014-03-07 00:00:00'),
('elyes', '2014-03-26 00:00:00'),
('super', '2014-03-07 00:00:00'),
('superadmin', '2014-03-07 00:00:00'),
('test', '2014-04-09 00:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `login` varchar(20) NOT NULL,
  `etat` tinyint(1) NOT NULL default '1',
  `date_naiss` datetime default NULL,
  PRIMARY KEY  (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `client`
--

INSERT INTO `client` (`login`, `etat`, `date_naiss`) VALUES
('5liyon', 1, NULL),
('aaerazr', 1, '2014-03-04 00:00:00'),
('aegae', 0, '2014-03-01 00:00:00'),
('aegaegaeg', 1, '2014-03-01 00:00:00'),
('Client1', 0, '2014-03-05 00:00:00'),
('Client5', 0, '1994-03-05 00:00:00'),
('ohlaega', 1, '1904-03-15 00:00:00'),
('Said', 0, '1990-03-06 00:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

CREATE TABLE IF NOT EXISTS `commentaire` (
  `id_comment` int(10) unsigned NOT NULL auto_increment,
  `id_client` varchar(45) NOT NULL,
  `id_offre` int(10) unsigned NOT NULL,
  `text` varchar(500) NOT NULL,
  `date_comm` datetime NOT NULL,
  PRIMARY KEY  (`id_comment`),
  KEY `FK_com_id_client` (`id_client`),
  KEY `FK_com_id_offre` (`id_offre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `evaluation`
--

CREATE TABLE IF NOT EXISTS `evaluation` (
  `id_eval` int(10) unsigned NOT NULL auto_increment,
  `id_client` varchar(45) NOT NULL,
  `id_offre` int(10) unsigned NOT NULL,
  `note` varchar(45) NOT NULL,
  `date_eval` datetime NOT NULL,
  PRIMARY KEY  (`id_eval`),
  KEY `FK_ev_id_client` (`id_client`),
  KEY `FK_ev_id_offre` (`id_offre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `offre`
--

CREATE TABLE IF NOT EXISTS `offre` (
  `ID_offre` int(10) unsigned NOT NULL auto_increment,
  `Libelle_offre` varchar(45) NOT NULL,
  `Dispo` tinyint(1) default '1',
  `date_Post` datetime default NULL,
  `ID_prest` varchar(45) NOT NULL,
  `note` float(2,1) default '0.0',
  `prix` float(10,3) default '0.000',
  `urlimg` varchar(255) default NULL,
  `num_tel` varchar(15) default NULL,
  PRIMARY KEY  (`ID_offre`),
  KEY `FK_id_prest` (`ID_prest`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Contenu de la table `offre`
--

INSERT INTO `offre` (`ID_offre`, `Libelle_offre`, `Dispo`, `date_Post`, `ID_prest`, `note`, `prix`, `urlimg`, `num_tel`) VALUES
(1, 'BMW1', 1, '2014-04-09 00:00:00', 'Microsoft', 0.0, 195.000, 'http://localhost/goldencage/images/1.jpg', '55505000'),
(2, 'BMW2', 0, '2014-03-07 00:00:00', 'Microsoft2', 0.0, 100.500, 'http://localhost/goldencage/images/2.jpg', '55500010'),
(3, 'BMW3', 1, '2014-03-07 00:00:00', 'Microsoft2', 0.0, 100.500, 'http://localhost/goldencage/images/3.jpg', '22745642'),
(4, 'BMW4', 1, '2014-03-07 00:00:00', 'Microsoft2', 0.0, 100.500, 'http://localhost/goldencage/images/4.jpg', NULL),
(5, 'Sourie444', 1, '2014-03-07 00:00:00', 'Microsoft2', 0.0, 100.500, NULL, NULL),
(6, 'azerty', 1, '2014-03-08 00:00:00', 'Microsoft', 0.0, 1000.000, NULL, NULL),
(7, 'Ola', 1, '2014-03-08 00:00:00', 'Microsoft', 0.0, 1000.000, NULL, NULL),
(9, 'Olaf', 1, '2014-03-08 00:00:00', 'Microsoft', 0.0, 1500.000, NULL, NULL),
(11, 'Olaet', 1, '2014-03-08 00:00:00', 'Microsoft', 0.0, 1500.000, NULL, NULL),
(12, 'Dog', 1, '2014-03-09 00:00:00', 'Microsoft2', 0.0, 1500.000, NULL, NULL),
(13, 'Eza', 1, '2014-03-09 00:00:00', 'Microsoft2', 0.0, 1500.000, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `prestataire`
--

CREATE TABLE IF NOT EXISTS `prestataire` (
  `login` varchar(20) NOT NULL,
  `debutAb` datetime NOT NULL,
  `finAb` datetime NOT NULL,
  `etat` tinyint(1) default '1',
  PRIMARY KEY  (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `prestataire`
--

INSERT INTO `prestataire` (`login`, `debutAb`, `finAb`, `etat`) VALUES
('aegaegaeg', '2014-03-08 00:00:00', '2014-03-25 00:00:00', 1),
('Microsoft', '2014-03-02 00:00:00', '2014-03-02 00:00:00', 1),
('Microsoft2', '2014-03-06 00:00:00', '2014-03-06 00:00:00', 1);

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE IF NOT EXISTS `reclamation` (
  `id_reclamation` int(10) unsigned NOT NULL auto_increment,
  `rec_sujet` varchar(45) NOT NULL,
  `rec_text` varchar(500) NOT NULL,
  `id_client` varchar(45) NOT NULL,
  `id_offre` int(10) unsigned NOT NULL,
  `date_post` datetime NOT NULL,
  PRIMARY KEY  (`id_reclamation`),
  KEY `FK_rec_id_client` (`id_client`),
  KEY `FK_rec_id_offre` (`id_offre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `reclamation`
--

INSERT INTO `reclamation` (`id_reclamation`, `rec_sujet`, `rec_text`, `id_client`, `id_offre`, `date_post`) VALUES
(1, 'jnnkgnegkzng', 'gnrgnerngjrgnrjgn', '5liyon', 1, '1990-01-01 00:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE IF NOT EXISTS `reservation` (
  `ID_reservation` int(10) unsigned NOT NULL auto_increment,
  `id_offre` int(10) unsigned NOT NULL,
  `id_client` varchar(20) NOT NULL,
  `date_Debut` datetime NOT NULL,
  `date_Fin` datetime NOT NULL,
  PRIMARY KEY  (`ID_reservation`),
  KEY `FK_res_id_client` (`id_client`),
  KEY `FK_res_id_offre` (`id_offre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `login` varchar(20) NOT NULL,
  `password` varchar(45) NOT NULL,
  `nom` varchar(45) default NULL,
  `prenom` varchar(45) default NULL,
  `email` varchar(45) default NULL,
  `adresse` varchar(45) default NULL,
  `num_tel` varchar(11) default NULL,
  `date_inscrit` datetime default NULL,
  PRIMARY KEY  (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`login`, `password`, `nom`, `prenom`, `email`, `adresse`, `num_tel`, `date_inscrit`) VALUES
('5liyon', '123', 'Said', 'Saiid', 'Said@saiid.tn', 'Rue hahaha', '0', NULL),
('aaerazr', 'aer', 'aerae', 'aera', 'aeraer', 'aerae', '0', NULL),
('aeg', 'aegae', 'aeg', 'aegaeg', 'aegaeg', 'aeg', '0', NULL),
('aegae', 'gaegaega', 'egaegaeg', 'aegaeg', 'aegaeg', 'aeg', '0', NULL),
('aegaegaeg', 'aegaegae', 'gaeg', 'aegaeg', 'aegaeg', 'aegaeg', '0', NULL),
('Client1', '123', 'Said', 'Saiid', 'Said@saiid.tn', 'Rue hahaha', '0', NULL),
('Client5', '123', 'Said', 'Saiid', 'Said@saiid.tn', 'Rue hahaha', '0', NULL),
('elyes', '123', 'Elyes', 'Ben Salah', 'Elyes.bensalah@esprit.tn', NULL, '0', NULL),
('elyes2', '123', 'aeg', 'aeg', 'Moula', 'kasa', '0', NULL),
('Microsoft', '123', 'Microsoft', 'Blabla', 'Bla@esprit.Tn', 'Rue fagaebaebaea', '0', NULL),
('Microsoft2', '123', 'Microsoft', 'Blabla', 'Bla@esprit.Tn', 'Rue fagaebaebaea', '0', NULL),
('ohlaega', 'aegaeg', 'aega', 'aegaeg', 'daegae', 'aeg', '0', NULL),
('Said', 'hahaha', 'Elyes', 'Salah', 'aegaeg', 'aegaeg', '0', '1990-03-06 00:00:00'),
('super', 'aegaeae', 'gaeg', 'aeg', 'egaeg', 'aeg', '0', NULL),
('superadmin', '123', 'aegae', 'aegae', 'gaegae', 'g', '0', NULL),
('test', 'a', 'test', 'tester', 'test@PIDev.tn', 'tester in esprit', '0', '1900-08-07 00:00:00');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `administrateur`
--
ALTER TABLE `administrateur`
  ADD CONSTRAINT `FK_administrateur` FOREIGN KEY (`login`) REFERENCES `user` (`login`);

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FK_client_1` FOREIGN KEY (`login`) REFERENCES `user` (`login`);

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `FK_com_id_client` FOREIGN KEY (`id_client`) REFERENCES `client` (`login`),
  ADD CONSTRAINT `FK_com_id_offre` FOREIGN KEY (`id_offre`) REFERENCES `offre` (`ID_offre`);

--
-- Contraintes pour la table `evaluation`
--
ALTER TABLE `evaluation`
  ADD CONSTRAINT `FK_ev_id_client` FOREIGN KEY (`id_client`) REFERENCES `client` (`login`),
  ADD CONSTRAINT `FK_ev_id_offre` FOREIGN KEY (`id_offre`) REFERENCES `offre` (`ID_offre`);

--
-- Contraintes pour la table `offre`
--
ALTER TABLE `offre`
  ADD CONSTRAINT `FK_id_prest` FOREIGN KEY (`ID_prest`) REFERENCES `prestataire` (`login`);

--
-- Contraintes pour la table `prestataire`
--
ALTER TABLE `prestataire`
  ADD CONSTRAINT `FK_prestataire` FOREIGN KEY (`login`) REFERENCES `user` (`login`);

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `FK_rec_id_client` FOREIGN KEY (`id_client`) REFERENCES `client` (`login`),
  ADD CONSTRAINT `FK_rec_id_offre` FOREIGN KEY (`id_offre`) REFERENCES `offre` (`ID_offre`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `FK_res_id_client` FOREIGN KEY (`id_client`) REFERENCES `client` (`login`),
  ADD CONSTRAINT `FK_res_id_offre` FOREIGN KEY (`id_offre`) REFERENCES `offre` (`ID_offre`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
