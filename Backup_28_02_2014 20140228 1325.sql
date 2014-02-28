-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.27-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema goldencage
--

CREATE DATABASE IF NOT EXISTS goldencage;
USE goldencage;

--
-- Definition of table `administrateur`
--

DROP TABLE IF EXISTS `administrateur`;
CREATE TABLE `administrateur` (
  `login` varchar(20) NOT NULL,
  `last_login` datetime default NULL,
  PRIMARY KEY  (`login`),
  CONSTRAINT `FK_administrateur` FOREIGN KEY (`login`) REFERENCES `user` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `administrateur`
--

/*!40000 ALTER TABLE `administrateur` DISABLE KEYS */;
INSERT INTO `administrateur` (`login`,`last_login`) VALUES 
 ('elyes','2014-02-28 00:00:00'),
 ('test','2014-02-28 00:00:00'),
 ('test2','2014-02-28 00:00:00');
/*!40000 ALTER TABLE `administrateur` ENABLE KEYS */;


--
-- Definition of table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `login` varchar(20) NOT NULL,
  PRIMARY KEY  (`login`),
  CONSTRAINT `FK_client_1` FOREIGN KEY (`login`) REFERENCES `user` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

/*!40000 ALTER TABLE `client` DISABLE KEYS */;
/*!40000 ALTER TABLE `client` ENABLE KEYS */;


--
-- Definition of table `commentaire`
--

DROP TABLE IF EXISTS `commentaire`;
CREATE TABLE `commentaire` (
  `id_comment` int(10) unsigned NOT NULL auto_increment,
  `id_client` varchar(45) NOT NULL,
  `id_offre` int(10) unsigned NOT NULL,
  `text` varchar(500) NOT NULL,
  `date_comm` datetime NOT NULL,
  PRIMARY KEY  (`id_comment`),
  KEY `FK_com_id_client` (`id_client`),
  KEY `FK_com_id_offre` (`id_offre`),
  CONSTRAINT `FK_com_id_client` FOREIGN KEY (`id_client`) REFERENCES `client` (`login`),
  CONSTRAINT `FK_com_id_offre` FOREIGN KEY (`id_offre`) REFERENCES `offre` (`ID_offre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `commentaire`
--

/*!40000 ALTER TABLE `commentaire` DISABLE KEYS */;
/*!40000 ALTER TABLE `commentaire` ENABLE KEYS */;


--
-- Definition of table `evaluation`
--

DROP TABLE IF EXISTS `evaluation`;
CREATE TABLE `evaluation` (
  `id_eval` int(10) unsigned NOT NULL auto_increment,
  `id_client` varchar(45) NOT NULL,
  `id_offre` int(10) unsigned NOT NULL,
  `note` varchar(45) NOT NULL,
  `date_eval` datetime NOT NULL,
  PRIMARY KEY  (`id_eval`),
  KEY `FK_ev_id_client` (`id_client`),
  KEY `FK_ev_id_offre` (`id_offre`),
  CONSTRAINT `FK_ev_id_client` FOREIGN KEY (`id_client`) REFERENCES `client` (`login`),
  CONSTRAINT `FK_ev_id_offre` FOREIGN KEY (`id_offre`) REFERENCES `offre` (`ID_offre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `evaluation`
--

/*!40000 ALTER TABLE `evaluation` DISABLE KEYS */;
/*!40000 ALTER TABLE `evaluation` ENABLE KEYS */;


--
-- Definition of table `offre`
--

DROP TABLE IF EXISTS `offre`;
CREATE TABLE `offre` (
  `ID_offre` int(10) unsigned NOT NULL auto_increment,
  `Libelle_offre` varchar(45) NOT NULL,
  `Dispo` tinyint(1) NOT NULL,
  `date_Post` datetime NOT NULL,
  `ID_prest` varchar(45) NOT NULL,
  `note` int(10) unsigned NOT NULL default '0',
  `prix` float(10,3) NOT NULL,
  PRIMARY KEY  (`ID_offre`),
  KEY `FK_id_prest` (`ID_prest`),
  CONSTRAINT `FK_id_prest` FOREIGN KEY (`ID_prest`) REFERENCES `prestataire` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `offre`
--

/*!40000 ALTER TABLE `offre` DISABLE KEYS */;
/*!40000 ALTER TABLE `offre` ENABLE KEYS */;


--
-- Definition of table `prestataire`
--

DROP TABLE IF EXISTS `prestataire`;
CREATE TABLE `prestataire` (
  `login` varchar(20) NOT NULL,
  `debutAb` datetime NOT NULL,
  `finAb` datetime NOT NULL,
  PRIMARY KEY  (`login`),
  CONSTRAINT `FK_prestataire` FOREIGN KEY (`login`) REFERENCES `user` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prestataire`
--

/*!40000 ALTER TABLE `prestataire` DISABLE KEYS */;
/*!40000 ALTER TABLE `prestataire` ENABLE KEYS */;


--
-- Definition of table `reclamation`
--

DROP TABLE IF EXISTS `reclamation`;
CREATE TABLE `reclamation` (
  `id_reclamation` int(10) unsigned NOT NULL auto_increment,
  `rec_sujet` varchar(45) NOT NULL,
  `rec_text` varchar(500) NOT NULL,
  `id_client` varchar(45) NOT NULL,
  `id_offre` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id_reclamation`),
  KEY `FK_rec_id_client` (`id_client`),
  KEY `FK_rec_id_offre` (`id_offre`),
  CONSTRAINT `FK_rec_id_client` FOREIGN KEY (`id_client`) REFERENCES `client` (`login`),
  CONSTRAINT `FK_rec_id_offre` FOREIGN KEY (`id_offre`) REFERENCES `offre` (`ID_offre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reclamation`
--

/*!40000 ALTER TABLE `reclamation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reclamation` ENABLE KEYS */;


--
-- Definition of table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE `reservation` (
  `ID_reservation` int(10) unsigned NOT NULL auto_increment,
  `id_offre` int(10) unsigned NOT NULL,
  `id_client` varchar(20) NOT NULL,
  `date_Debut` datetime NOT NULL,
  `date_Fin` datetime NOT NULL,
  PRIMARY KEY  (`ID_reservation`),
  KEY `FK_res_id_client` (`id_client`),
  KEY `FK_res_id_offre` (`id_offre`),
  CONSTRAINT `FK_res_id_client` FOREIGN KEY (`id_client`) REFERENCES `client` (`login`),
  CONSTRAINT `FK_res_id_offre` FOREIGN KEY (`id_offre`) REFERENCES `offre` (`ID_offre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reservation`
--

/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;


--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `login` varchar(20) NOT NULL,
  `password` varchar(45) NOT NULL,
  `nom` varchar(45) default NULL,
  `prenom` varchar(45) default NULL,
  `email` varchar(45) default NULL,
  `adresse` varchar(45) default NULL,
  PRIMARY KEY  (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`login`,`password`,`nom`,`prenom`,`email`,`adresse`) VALUES 
 ('elyes','123','Elyes','Ben Salah','Elyes.bensalah@esprit.tn',NULL),
 ('test','a','test','tester','test@PIDev.tn','tester in esprit'),
 ('test2','test2','test2','test2','test2@hola.fr','test2');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
