-- MySQL dump 10.13  Distrib 5.5.31, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: qpeka
-- ------------------------------------------------------
-- Server version	5.5.31-0ubuntu0.12.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `badges`
--

DROP TABLE IF EXISTS `badges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `badges` (
  `badgeid` smallint(6) NOT NULL AUTO_INCREMENT,
  `typeid` smallint(6) NOT NULL DEFAULT '0',
  `badge` varchar(50) NOT NULL DEFAULT 'new',
  `level` tinyint(4) NOT NULL DEFAULT '1',
  `points` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`badgeid`),
  KEY `fk_badges_1` (`typeid`),
  KEY `badge` (`badge`),
  CONSTRAINT `fk_badges_1` FOREIGN KEY (`typeid`) REFERENCES `usertype` (`typeid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `badges`
--

LOCK TABLES `badges` WRITE;
/*!40000 ALTER TABLE `badges` DISABLE KEYS */;
/*!40000 ALTER TABLE `badges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `categoryid` smallint(6) NOT NULL AUTO_INCREMENT,
  `type` enum('book','short story','poem','article') DEFAULT 'book',
  `category` varchar(50) NOT NULL DEFAULT '',
  `genre` varchar(50) NOT NULL DEFAULT '',
  `points` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`categoryid`),
  UNIQUE KEY `category` (`category`,`genre`),
  KEY `category_2` (`category`),
  KEY `genre` (`genre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `countryid` smallint(5) NOT NULL AUTO_INCREMENT,
  `iso2` char(2) DEFAULT NULL,
  `shortname` varchar(80) NOT NULL DEFAULT '',
  `longname` varchar(80) NOT NULL DEFAULT '',
  `iso3` char(3) DEFAULT NULL,
  `numcode` varchar(6) DEFAULT NULL,
  `unmember` varchar(12) DEFAULT NULL,
  `callingcode` varchar(8) DEFAULT NULL,
  `cctld` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`countryid`)
) ENGINE=InnoDB AUTO_INCREMENT=251 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'AF','Afghanistan','Islamic Republic of Afghanistan','AFG','004','yes','93','.af'),(2,'AX','Aland Islands','&Aring;land Islands','ALA','248','no','358','.ax'),(3,'AL','Albania','Republic of Albania','ALB','008','yes','355','.al'),(4,'DZ','Algeria','People\'s Democratic Republic of Algeria','DZA','012','yes','213','.dz'),(5,'AS','American Samoa','American Samoa','ASM','016','no','1+684','.as'),(6,'AD','Andorra','Principality of Andorra','AND','020','yes','376','.ad'),(7,'AO','Angola','Republic of Angola','AGO','024','yes','244','.ao'),(8,'AI','Anguilla','Anguilla','AIA','660','no','1+264','.ai'),(9,'AQ','Antarctica','Antarctica','ATA','010','no','672','.aq'),(10,'AG','Antigua and Barbuda','Antigua and Barbuda','ATG','028','yes','1+268','.ag'),(11,'AR','Argentina','Argentine Republic','ARG','032','yes','54','.ar'),(12,'AM','Armenia','Republic of Armenia','ARM','051','yes','374','.am'),(13,'AW','Aruba','Aruba','ABW','533','no','297','.aw'),(14,'AU','Australia','Commonwealth of Australia','AUS','036','yes','61','.au'),(15,'AT','Austria','Republic of Austria','AUT','040','yes','43','.at'),(16,'AZ','Azerbaijan','Republic of Azerbaijan','AZE','031','yes','994','.az'),(17,'BS','Bahamas','Commonwealth of The Bahamas','BHS','044','yes','1+242','.bs'),(18,'BH','Bahrain','Kingdom of Bahrain','BHR','048','yes','973','.bh'),(19,'BD','Bangladesh','People\'s Republic of Bangladesh','BGD','050','yes','880','.bd'),(20,'BB','Barbados','Barbados','BRB','052','yes','1+246','.bb'),(21,'BY','Belarus','Republic of Belarus','BLR','112','yes','375','.by'),(22,'BE','Belgium','Kingdom of Belgium','BEL','056','yes','32','.be'),(23,'BZ','Belize','Belize','BLZ','084','yes','501','.bz'),(24,'BJ','Benin','Republic of Benin','BEN','204','yes','229','.bj'),(25,'BM','Bermuda','Bermuda Islands','BMU','060','no','1+441','.bm'),(26,'BT','Bhutan','Kingdom of Bhutan','BTN','064','yes','975','.bt'),(27,'BO','Bolivia','Plurinational State of Bolivia','BOL','068','yes','591','.bo'),(28,'BQ','Bonaire, Sint Eustatius and Saba','Bonaire, Sint Eustatius and Saba','BES','535','no','599','.bq'),(29,'BA','Bosnia and Herzegovina','Bosnia and Herzegovina','BIH','070','yes','387','.ba'),(30,'BW','Botswana','Republic of Botswana','BWA','072','yes','267','.bw'),(31,'BV','Bouvet Island','Bouvet Island','BVT','074','no','NONE','.bv'),(32,'BR','Brazil','Federative Republic of Brazil','BRA','076','yes','55','.br'),(33,'IO','British Indian Ocean Territory','British Indian Ocean Territory','IOT','086','no','246','.io'),(34,'BN','Brunei','Brunei Darussalam','BRN','096','yes','673','.bn'),(35,'BG','Bulgaria','Republic of Bulgaria','BGR','100','yes','359','.bg'),(36,'BF','Burkina Faso','Burkina Faso','BFA','854','yes','226','.bf'),(37,'BI','Burundi','Republic of Burundi','BDI','108','yes','257','.bi'),(38,'KH','Cambodia','Kingdom of Cambodia','KHM','116','yes','855','.kh'),(39,'CM','Cameroon','Republic of Cameroon','CMR','120','yes','237','.cm'),(40,'CA','Canada','Canada','CAN','124','yes','1','.ca'),(41,'CV','Cape Verde','Republic of Cape Verde','CPV','132','yes','238','.cv'),(42,'KY','Cayman Islands','The Cayman Islands','CYM','136','no','1+345','.ky'),(43,'CF','Central African Republic','Central African Republic','CAF','140','yes','236','.cf'),(44,'TD','Chad','Republic of Chad','TCD','148','yes','235','.td'),(45,'CL','Chile','Republic of Chile','CHL','152','yes','56','.cl'),(46,'CN','China','People\'s Republic of China','CHN','156','yes','86','.cn'),(47,'CX','Christmas Island','Christmas Island','CXR','162','no','61','.cx'),(48,'CC','Cocos (Keeling) Islands','Cocos (Keeling) Islands','CCK','166','no','61','.cc'),(49,'CO','Colombia','Republic of Colombia','COL','170','yes','57','.co'),(50,'KM','Comoros','Union of the Comoros','COM','174','yes','269','.km'),(51,'CG','Congo','Republic of the Congo','COG','178','yes','242','.cg'),(52,'CK','Cook Islands','Cook Islands','COK','184','some','682','.ck'),(53,'CR','Costa Rica','Republic of Costa Rica','CRI','188','yes','506','.cr'),(54,'CI','Cote d\'ivoire (Ivory Coast)','Republic of C&ocirc;te D\'Ivoire (Ivory Coast)','CIV','384','yes','225','.ci'),(55,'HR','Croatia','Republic of Croatia','HRV','191','yes','385','.hr'),(56,'CU','Cuba','Republic of Cuba','CUB','192','yes','53','.cu'),(57,'CW','Curacao','Cura&ccedil;ao','CUW','531','no','599','.cw'),(58,'CY','Cyprus','Republic of Cyprus','CYP','196','yes','357','.cy'),(59,'CZ','Czech Republic','Czech Republic','CZE','203','yes','420','.cz'),(60,'CD','Democratic Republic of the Congo','Democratic Republic of the Congo','COD','180','yes','243','.cd'),(61,'DK','Denmark','Kingdom of Denmark','DNK','208','yes','45','.dk'),(62,'DJ','Djibouti','Republic of Djibouti','DJI','262','yes','253','.dj'),(63,'DM','Dominica','Commonwealth of Dominica','DMA','212','yes','1+767','.dm'),(64,'DO','Dominican Republic','Dominican Republic','DOM','214','yes','1+809, 8','.do'),(65,'EC','Ecuador','Republic of Ecuador','ECU','218','yes','593','.ec'),(66,'EG','Egypt','Arab Republic of Egypt','EGY','818','yes','20','.eg'),(67,'SV','El Salvador','Republic of El Salvador','SLV','222','yes','503','.sv'),(68,'GQ','Equatorial Guinea','Republic of Equatorial Guinea','GNQ','226','yes','240','.gq'),(69,'ER','Eritrea','State of Eritrea','ERI','232','yes','291','.er'),(70,'EE','Estonia','Republic of Estonia','EST','233','yes','372','.ee'),(71,'ET','Ethiopia','Federal Democratic Republic of Ethiopia','ETH','231','yes','251','.et'),(72,'FK','Falkland Islands (Malvinas)','The Falkland Islands (Malvinas)','FLK','238','no','500','.fk'),(73,'FO','Faroe Islands','The Faroe Islands','FRO','234','no','298','.fo'),(74,'FJ','Fiji','Republic of Fiji','FJI','242','yes','679','.fj'),(75,'FI','Finland','Republic of Finland','FIN','246','yes','358','.fi'),(76,'FR','France','French Republic','FRA','250','yes','33','.fr'),(77,'GF','French Guiana','French Guiana','GUF','254','no','594','.gf'),(78,'PF','French Polynesia','French Polynesia','PYF','258','no','689','.pf'),(79,'TF','French Southern Territories','French Southern Territories','ATF','260','no',NULL,'.tf'),(80,'GA','Gabon','Gabonese Republic','GAB','266','yes','241','.ga'),(81,'GM','Gambia','Republic of The Gambia','GMB','270','yes','220','.gm'),(82,'GE','Georgia','Georgia','GEO','268','yes','995','.ge'),(83,'DE','Germany','Federal Republic of Germany','DEU','276','yes','49','.de'),(84,'GH','Ghana','Republic of Ghana','GHA','288','yes','233','.gh'),(85,'GI','Gibraltar','Gibraltar','GIB','292','no','350','.gi'),(86,'GR','Greece','Hellenic Republic','GRC','300','yes','30','.gr'),(87,'GL','Greenland','Greenland','GRL','304','no','299','.gl'),(88,'GD','Grenada','Grenada','GRD','308','yes','1+473','.gd'),(89,'GP','Guadaloupe','Guadeloupe','GLP','312','no','590','.gp'),(90,'GU','Guam','Guam','GUM','316','no','1+671','.gu'),(91,'GT','Guatemala','Republic of Guatemala','GTM','320','yes','502','.gt'),(92,'GG','Guernsey','Guernsey','GGY','831','no','44','.gg'),(93,'GN','Guinea','Republic of Guinea','GIN','324','yes','224','.gn'),(94,'GW','Guinea-Bissau','Republic of Guinea-Bissau','GNB','624','yes','245','.gw'),(95,'GY','Guyana','Co-operative Republic of Guyana','GUY','328','yes','592','.gy'),(96,'HT','Haiti','Republic of Haiti','HTI','332','yes','509','.ht'),(97,'HM','Heard Island and McDonald Islands','Heard Island and McDonald Islands','HMD','334','no','NONE','.hm'),(98,'HN','Honduras','Republic of Honduras','HND','340','yes','504','.hn'),(99,'HK','Hong Kong','Hong Kong','HKG','344','no','852','.hk'),(100,'HU','Hungary','Hungary','HUN','348','yes','36','.hu'),(101,'IS','Iceland','Republic of Iceland','ISL','352','yes','354','.is'),(102,'IN','India','Republic of India','IND','356','yes','91','.in'),(103,'ID','Indonesia','Republic of Indonesia','IDN','360','yes','62','.id'),(104,'IR','Iran','Islamic Republic of Iran','IRN','364','yes','98','.ir'),(105,'IQ','Iraq','Republic of Iraq','IRQ','368','yes','964','.iq'),(106,'IE','Ireland','Ireland','IRL','372','yes','353','.ie'),(107,'IM','Isle of Man','Isle of Man','IMN','833','no','44','.im'),(108,'IL','Israel','State of Israel','ISR','376','yes','972','.il'),(109,'IT','Italy','Italian Republic','ITA','380','yes','39','.jm'),(110,'JM','Jamaica','Jamaica','JAM','388','yes','1+876','.jm'),(111,'JP','Japan','Japan','JPN','392','yes','81','.jp'),(112,'JE','Jersey','The Bailiwick of Jersey','JEY','832','no','44','.je'),(113,'JO','Jordan','Hashemite Kingdom of Jordan','JOR','400','yes','962','.jo'),(114,'KZ','Kazakhstan','Republic of Kazakhstan','KAZ','398','yes','7','.kz'),(115,'KE','Kenya','Republic of Kenya','KEN','404','yes','254','.ke'),(116,'KI','Kiribati','Republic of Kiribati','KIR','296','yes','686','.ki'),(117,'XK','Kosovo','Republic of Kosovo','---','---','some','381',''),(118,'KW','Kuwait','State of Kuwait','KWT','414','yes','965','.kw'),(119,'KG','Kyrgyzstan','Kyrgyz Republic','KGZ','417','yes','996','.kg'),(120,'LA','Laos','Lao People\'s Democratic Republic','LAO','418','yes','856','.la'),(121,'LV','Latvia','Republic of Latvia','LVA','428','yes','371','.lv'),(122,'LB','Lebanon','Republic of Lebanon','LBN','422','yes','961','.lb'),(123,'LS','Lesotho','Kingdom of Lesotho','LSO','426','yes','266','.ls'),(124,'LR','Liberia','Republic of Liberia','LBR','430','yes','231','.lr'),(125,'LY','Libya','Libya','LBY','434','yes','218','.ly'),(126,'LI','Liechtenstein','Principality of Liechtenstein','LIE','438','yes','423','.li'),(127,'LT','Lithuania','Republic of Lithuania','LTU','440','yes','370','.lt'),(128,'LU','Luxembourg','Grand Duchy of Luxembourg','LUX','442','yes','352','.lu'),(129,'MO','Macao','The Macao Special Administrative Region','MAC','446','no','853','.mo'),(130,'MK','Macedonia','The Former Yugoslav Republic of Macedonia','MKD','807','yes','389','.mk'),(131,'MG','Madagascar','Republic of Madagascar','MDG','450','yes','261','.mg'),(132,'MW','Malawi','Republic of Malawi','MWI','454','yes','265','.mw'),(133,'MY','Malaysia','Malaysia','MYS','458','yes','60','.my'),(134,'MV','Maldives','Republic of Maldives','MDV','462','yes','960','.mv'),(135,'ML','Mali','Republic of Mali','MLI','466','yes','223','.ml'),(136,'MT','Malta','Republic of Malta','MLT','470','yes','356','.mt'),(137,'MH','Marshall Islands','Republic of the Marshall Islands','MHL','584','yes','692','.mh'),(138,'MQ','Martinique','Martinique','MTQ','474','no','596','.mq'),(139,'MR','Mauritania','Islamic Republic of Mauritania','MRT','478','yes','222','.mr'),(140,'MU','Mauritius','Republic of Mauritius','MUS','480','yes','230','.mu'),(141,'YT','Mayotte','Mayotte','MYT','175','no','262','.yt'),(142,'MX','Mexico','United Mexican States','MEX','484','yes','52','.mx'),(143,'FM','Micronesia','Federated States of Micronesia','FSM','583','yes','691','.fm'),(144,'MD','Moldava','Republic of Moldova','MDA','498','yes','373','.md'),(145,'MC','Monaco','Principality of Monaco','MCO','492','yes','377','.mc'),(146,'MN','Mongolia','Mongolia','MNG','496','yes','976','.mn'),(147,'ME','Montenegro','Montenegro','MNE','499','yes','382','.me'),(148,'MS','Montserrat','Montserrat','MSR','500','no','1+664','.ms'),(149,'MA','Morocco','Kingdom of Morocco','MAR','504','yes','212','.ma'),(150,'MZ','Mozambique','Republic of Mozambique','MOZ','508','yes','258','.mz'),(151,'MM','Myanmar (Burma)','Republic of the Union of Myanmar','MMR','104','yes','95','.mm'),(152,'NA','Namibia','Republic of Namibia','NAM','516','yes','264','.na'),(153,'NR','Nauru','Republic of Nauru','NRU','520','yes','674','.nr'),(154,'NP','Nepal','Federal Democratic Republic of Nepal','NPL','524','yes','977','.np'),(155,'NL','Netherlands','Kingdom of the Netherlands','NLD','528','yes','31','.nl'),(156,'NC','New Caledonia','New Caledonia','NCL','540','no','687','.nc'),(157,'NZ','New Zealand','New Zealand','NZL','554','yes','64','.nz'),(158,'NI','Nicaragua','Republic of Nicaragua','NIC','558','yes','505','.ni'),(159,'NE','Niger','Republic of Niger','NER','562','yes','227','.ne'),(160,'NG','Nigeria','Federal Republic of Nigeria','NGA','566','yes','234','.ng'),(161,'NU','Niue','Niue','NIU','570','some','683','.nu'),(162,'NF','Norfolk Island','Norfolk Island','NFK','574','no','672','.nf'),(163,'KP','North Korea','Democratic People\'s Republic of Korea','PRK','408','yes','850','.kp'),(164,'MP','Northern Mariana Islands','Northern Mariana Islands','MNP','580','no','1+670','.mp'),(165,'NO','Norway','Kingdom of Norway','NOR','578','yes','47','.no'),(166,'OM','Oman','Sultanate of Oman','OMN','512','yes','968','.om'),(167,'PK','Pakistan','Islamic Republic of Pakistan','PAK','586','yes','92','.pk'),(168,'PW','Palau','Republic of Palau','PLW','585','yes','680','.pw'),(169,'PS','Palestine','State of Palestine (or Occupied Palestinian Territory)','PSE','275','some','970','.ps'),(170,'PA','Panama','Republic of Panama','PAN','591','yes','507','.pa'),(171,'PG','Papua New Guinea','Independent State of Papua New Guinea','PNG','598','yes','675','.pg'),(172,'PY','Paraguay','Republic of Paraguay','PRY','600','yes','595','.py'),(173,'PE','Peru','Republic of Peru','PER','604','yes','51','.pe'),(174,'PH','Phillipines','Republic of the Philippines','PHL','608','yes','63','.ph'),(175,'PN','Pitcairn','Pitcairn','PCN','612','no','NONE','.pn'),(176,'PL','Poland','Republic of Poland','POL','616','yes','48','.pl'),(177,'PT','Portugal','Portuguese Republic','PRT','620','yes','351','.pt'),(178,'PR','Puerto Rico','Commonwealth of Puerto Rico','PRI','630','no','1+939','.pr'),(179,'QA','Qatar','State of Qatar','QAT','634','yes','974','.qa'),(180,'RE','Reunion','R&eacute;union','REU','638','no','262','.re'),(181,'RO','Romania','Romania','ROU','642','yes','40','.ro'),(182,'RU','Russia','Russian Federation','RUS','643','yes','7','.ru'),(183,'RW','Rwanda','Republic of Rwanda','RWA','646','yes','250','.rw'),(184,'BL','Saint Barthelemy','Saint Barth&eacute;lemy','BLM','652','no','590','.bl'),(185,'SH','Saint Helena','Saint Helena, Ascension and Tristan da Cunha','SHN','654','no','290','.sh'),(186,'KN','Saint Kitts and Nevis','Federation of Saint Christopher and Nevis','KNA','659','yes','1+869','.kn'),(187,'LC','Saint Lucia','Saint Lucia','LCA','662','yes','1+758','.lc'),(188,'MF','Saint Martin','Saint Martin','MAF','663','no','590','.mf'),(189,'PM','Saint Pierre and Miquelon','Saint Pierre and Miquelon','SPM','666','no','508','.pm'),(190,'VC','Saint Vincent and the Grenadines','Saint Vincent and the Grenadines','VCT','670','yes','1+784','.vc'),(191,'WS','Samoa','Independent State of Samoa','WSM','882','yes','685','.ws'),(192,'SM','San Marino','Republic of San Marino','SMR','674','yes','378','.sm'),(193,'ST','Sao Tome and Principe','Democratic Republic of S&atilde;o Tom&eacute; and Pr&iacute;ncipe','STP','678','yes','239','.st'),(194,'SA','Saudi Arabia','Kingdom of Saudi Arabia','SAU','682','yes','966','.sa'),(195,'SN','Senegal','Republic of Senegal','SEN','686','yes','221','.sn'),(196,'RS','Serbia','Republic of Serbia','SRB','688','yes','381','.rs'),(197,'SC','Seychelles','Republic of Seychelles','SYC','690','yes','248','.sc'),(198,'SL','Sierra Leone','Republic of Sierra Leone','SLE','694','yes','232','.sl'),(199,'SG','Singapore','Republic of Singapore','SGP','702','yes','65','.sg'),(200,'SX','Sint Maarten','Sint Maarten','SXM','534','no','1+721','.sx'),(201,'SK','Slovakia','Slovak Republic','SVK','703','yes','421','.sk'),(202,'SI','Slovenia','Republic of Slovenia','SVN','705','yes','386','.si'),(203,'SB','Solomon Islands','Solomon Islands','SLB','090','yes','677','.sb'),(204,'SO','Somalia','Somali Republic','SOM','706','yes','252','.so'),(205,'ZA','South Africa','Republic of South Africa','ZAF','710','yes','27','.za'),(206,'GS','South Georgia and the South Sandwich Islands','South Georgia and the South Sandwich Islands','SGS','239','no','500','.gs'),(207,'KR','South Korea','Republic of Korea','KOR','410','yes','82','.kr'),(208,'SS','South Sudan','Republic of South Sudan','SSD','728','yes','211','.ss'),(209,'ES','Spain','Kingdom of Spain','ESP','724','yes','34','.es'),(210,'LK','Sri Lanka','Democratic Socialist Republic of Sri Lanka','LKA','144','yes','94','.lk'),(211,'SD','Sudan','Republic of the Sudan','SDN','729','yes','249','.sd'),(212,'SR','Suriname','Republic of Suriname','SUR','740','yes','597','.sr'),(213,'SJ','Svalbard and Jan Mayen','Svalbard and Jan Mayen','SJM','744','no','47','.sj'),(214,'SZ','Swaziland','Kingdom of Swaziland','SWZ','748','yes','268','.sz'),(215,'SE','Sweden','Kingdom of Sweden','SWE','752','yes','46','.se'),(216,'CH','Switzerland','Swiss Confederation','CHE','756','yes','41','.ch'),(217,'SY','Syria','Syrian Arab Republic','SYR','760','yes','963','.sy'),(218,'TW','Taiwan','Republic of China (Taiwan)','TWN','158','former','886','.tw'),(219,'TJ','Tajikistan','Republic of Tajikistan','TJK','762','yes','992','.tj'),(220,'TZ','Tanzania','United Republic of Tanzania','TZA','834','yes','255','.tz'),(221,'TH','Thailand','Kingdom of Thailand','THA','764','yes','66','.th'),(222,'TL','Timor-Leste (East Timor)','Democratic Republic of Timor-Leste','TLS','626','yes','670','.tl'),(223,'TG','Togo','Togolese Republic','TGO','768','yes','228','.tg'),(224,'TK','Tokelau','Tokelau','TKL','772','no','690','.tk'),(225,'TO','Tonga','Kingdom of Tonga','TON','776','yes','676','.to'),(226,'TT','Trinidad and Tobago','Republic of Trinidad and Tobago','TTO','780','yes','1+868','.tt'),(227,'TN','Tunisia','Republic of Tunisia','TUN','788','yes','216','.tn'),(228,'TR','Turkey','Republic of Turkey','TUR','792','yes','90','.tr'),(229,'TM','Turkmenistan','Turkmenistan','TKM','795','yes','993','.tm'),(230,'TC','Turks and Caicos Islands','Turks and Caicos Islands','TCA','796','no','1+649','.tc'),(231,'TV','Tuvalu','Tuvalu','TUV','798','yes','688','.tv'),(232,'UG','Uganda','Republic of Uganda','UGA','800','yes','256','.ug'),(233,'UA','Ukraine','Ukraine','UKR','804','yes','380','.ua'),(234,'AE','United Arab Emirates','United Arab Emirates','ARE','784','yes','971','.ae'),(235,'GB','United Kingdom','United Kingdom of Great Britain and Nothern Ireland','GBR','826','yes','44','.uk'),(236,'US','United States','United States of America','USA','840','yes','1','.us'),(237,'UM','United States Minor Outlying Islands','United States Minor Outlying Islands','UMI','581','no','NONE','NONE'),(238,'UY','Uruguay','Eastern Republic of Uruguay','URY','858','yes','598','.uy'),(239,'UZ','Uzbekistan','Republic of Uzbekistan','UZB','860','yes','998','.uz'),(240,'VU','Vanuatu','Republic of Vanuatu','VUT','548','yes','678','.vu'),(241,'VA','Vatican City','State of the Vatican City','VAT','336','no','39','.va'),(242,'VE','Venezuela','Bolivarian Republic of Venezuela','VEN','862','yes','58','.ve'),(243,'VN','Vietnam','Socialist Republic of Vietnam','VNM','704','yes','84','.vn'),(244,'VG','Virgin Islands, British','British Virgin Islands','VGB','092','no','1+284','.vg'),(245,'VI','Virgin Islands, US','Virgin Islands of the United States','VIR','850','no','1+340','.vi'),(246,'WF','Wallis and Futuna','Wallis and Futuna','WLF','876','no','681','.wf'),(247,'EH','Western Sahara','Western Sahara','ESH','732','no','212','.eh'),(248,'YE','Yemen','Republic of Yemen','YEM','887','yes','967','.ye'),(249,'ZM','Zambia','Republic of Zambia','ZMB','894','yes','260','.zm'),(250,'ZW','Zimbabwe','Republic of Zimbabwe','ZWE','716','yes','263','.zw');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `files`
--

DROP TABLE IF EXISTS `files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `files` (
  `fileid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL DEFAULT '0',
  `filetype` varchar(25) NOT NULL DEFAULT 'profilepic',
  `filename` varchar(255) NOT NULL DEFAULT '',
  `filepath` varchar(255) NOT NULL DEFAULT '',
  `filemime` varchar(255) NOT NULL DEFAULT '',
  `filesize` int(11) unsigned NOT NULL DEFAULT '0',
  `status` tinyint(11) NOT NULL DEFAULT '0',
  `timestamp` int(11) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`fileid`),
  KEY `filetype` (`filetype`),
  KEY `status` (`status`),
  KEY `timestamp` (`timestamp`),
  KEY `files_ibfk_1` (`userid`),
  CONSTRAINT `files_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `files`
--

LOCK TABLES `files` WRITE;
/*!40000 ALTER TABLE `files` DISABLE KEYS */;
/*!40000 ALTER TABLE `files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `languages`
--

DROP TABLE IF EXISTS `languages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `languages` (
  `languageid` smallint(6) NOT NULL,
  `language` varchar(12) NOT NULL DEFAULT '',
  `name` varchar(64) NOT NULL DEFAULT '',
  `native` smallint(5) NOT NULL DEFAULT '0',
  `direction` tinyint(4) NOT NULL DEFAULT '0',
  `enabled` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`languageid`),
  KEY `language` (`language`),
  KEY `list` (`name`),
  KEY `native` (`native`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `languages`
--

LOCK TABLES `languages` WRITE;
/*!40000 ALTER TABLE `languages` DISABLE KEYS */;
/*!40000 ALTER TABLE `languages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sessions`
--

DROP TABLE IF EXISTS `sessions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sessions` (
  `sessionid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `hostname` varchar(128) NOT NULL DEFAULT '',
  `timestamp` int(11) NOT NULL DEFAULT '0',
  `session` text,
  PRIMARY KEY (`sessionid`),
  KEY `timestamp` (`timestamp`),
  KEY `sessions_ibfk_1` (`userid`),
  CONSTRAINT `sessions_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sessions`
--

LOCK TABLES `sessions` WRITE;
/*!40000 ALTER TABLE `sessions` DISABLE KEYS */;
/*!40000 ALTER TABLE `sessions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(200) DEFAULT NULL,
  `password` varchar(64) NOT NULL DEFAULT '',
  `email` varchar(100) NOT NULL DEFAULT '',
  `created` int(11) NOT NULL DEFAULT '0',
  `lastaccess` int(11) NOT NULL DEFAULT '0',
  `lastlogin` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `type` tinyint(4) NOT NULL DEFAULT '0',
  `timezone` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraddress`
--

DROP TABLE IF EXISTS `useraddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraddress` (
  `addressid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL DEFAULT '0',
  `addressLine1` varchar(256) DEFAULT NULL,
  `addressLine2` varchar(256) DEFAULT NULL,
  `addressLine3` varchar(256) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `country` smallint(5) DEFAULT NULL,
  `pincode` smallint(6) NOT NULL DEFAULT '0',
  `timestamp` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`addressid`),
  KEY `useraddress_ibfk_1` (`userid`),
  KEY `useraddress_ibfk_2` (`country`),
  CONSTRAINT `useraddress_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
  CONSTRAINT `useraddress_ibfk_2` FOREIGN KEY (`country`) REFERENCES `country` (`countryid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraddress`
--

LOCK TABLES `useraddress` WRITE;
/*!40000 ALTER TABLE `useraddress` DISABLE KEYS */;
/*!40000 ALTER TABLE `useraddress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userbadges`
--

DROP TABLE IF EXISTS `userbadges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userbadges` (
  `userid` int(11) NOT NULL DEFAULT '0',
  `badgeid` smallint(6) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userid`,`badgeid`),
  KEY `badgeid` (`badgeid`),
  CONSTRAINT `userbadges_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
  CONSTRAINT `userbadges_ibfk_2` FOREIGN KEY (`badgeid`) REFERENCES `badges` (`badgeid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userbadges`
--

LOCK TABLES `userbadges` WRITE;
/*!40000 ALTER TABLE `userbadges` DISABLE KEYS */;
/*!40000 ALTER TABLE `userbadges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userbookratings`
--

DROP TABLE IF EXISTS `userbookratings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userbookratings` (
  `ratingid` int(11) NOT NULL DEFAULT '0',
  `userid` int(11) NOT NULL DEFAULT '0',
  `bookid` varchar(50) NOT NULL DEFAULT '',
  `rating` smallint(6) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ratingid`),
  KEY `userid` (`userid`),
  CONSTRAINT `userbookratings_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userbookratings`
--

LOCK TABLES `userbookratings` WRITE;
/*!40000 ALTER TABLE `userbookratings` DISABLE KEYS */;
/*!40000 ALTER TABLE `userbookratings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userinterests`
--

DROP TABLE IF EXISTS `userinterests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userinterests` (
  `userid` int(11) NOT NULL DEFAULT '0',
  `categoryid` smallint(6) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userid`,`categoryid`),
  KEY `categoryid` (`categoryid`),
  CONSTRAINT `userinterests_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
  CONSTRAINT `userinterests_ibfk_2` FOREIGN KEY (`categoryid`) REFERENCES `category` (`categoryid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinterests`
--

LOCK TABLES `userinterests` WRITE;
/*!40000 ALTER TABLE `userinterests` DISABLE KEYS */;
/*!40000 ALTER TABLE `userinterests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userlanguage`
--

DROP TABLE IF EXISTS `userlanguage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userlanguage` (
  `userid` int(11) NOT NULL DEFAULT '0',
  `languageid` smallint(6) NOT NULL DEFAULT '0',
  `type` enum('read','write') NOT NULL DEFAULT 'read',
  PRIMARY KEY (`userid`,`languageid`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userlanguage`
--

LOCK TABLES `userlanguage` WRITE;
/*!40000 ALTER TABLE `userlanguage` DISABLE KEYS */;
/*!40000 ALTER TABLE `userlanguage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userpoints`
--

DROP TABLE IF EXISTS `userpoints`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userpoints` (
  `userid` int(11) NOT NULL,
  `type` smallint(6) NOT NULL,
  `points` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userid`,`type`),
  KEY `fk_userpoints_1` (`type`),
  CONSTRAINT `fk_userpoints_1` FOREIGN KEY (`type`) REFERENCES `usertype` (`typeid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `userpoints_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userpoints`
--

LOCK TABLES `userpoints` WRITE;
/*!40000 ALTER TABLE `userpoints` DISABLE KEYS */;
/*!40000 ALTER TABLE `userpoints` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userprofile`
--

DROP TABLE IF EXISTS `userprofile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userprofile` (
  `userid` int(11) NOT NULL,
  `penname` varchar(30) DEFAULT NULL,
  `firstname` varchar(30) DEFAULT NULL,
  `middlename` varchar(30) DEFAULT NULL,
  `lastname` varchar(30) DEFAULT NULL,
  `gender` enum('Unspecified','Male','Female') DEFAULT 'Unspecified',
  `dob` int(11) NOT NULL DEFAULT '0',
  `nationality` smallint(6) DEFAULT NULL,
  `website` varchar(256) DEFAULT NULL,
  `biography` text,
  `profilepic` int(11) unsigned DEFAULT NULL,
  `level` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userid`),
  KEY `userprofile_ibfk_2` (`nationality`),
  KEY `fk_userprofile_1` (`profilepic`),
  CONSTRAINT `userprofile_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
  CONSTRAINT `userprofile_ibfk_2` FOREIGN KEY (`nationality`) REFERENCES `country` (`countryid`),
  CONSTRAINT `userprofile_ibfk_3` FOREIGN KEY (`profilepic`) REFERENCES `files` (`fileid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userprofile`
--

LOCK TABLES `userprofile` WRITE;
/*!40000 ALTER TABLE `userprofile` DISABLE KEYS */;
/*!40000 ALTER TABLE `userprofile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usertype`
--

DROP TABLE IF EXISTS `usertype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usertype` (
  `typeid` smallint(6) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL DEFAULT '' COMMENT 'User type: Reader, Writer, Publisher, Service Provider',
  `typename` varchar(50) NOT NULL DEFAULT '',
  `typeidentifier` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`typeid`),
  KEY `type` (`type`),
  KEY `typename` (`typename`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertype`
--

LOCK TABLES `usertype` WRITE;
/*!40000 ALTER TABLE `usertype` DISABLE KEYS */;
INSERT INTO `usertype` VALUES (1,'Reader','Reader','reader'),(2,'Writer','Writer','writer'),(3,'Publisher','Publisher','publisher'),(4,'Service Provider','Reviewer','reviewer'),(5,'Service Provider','Editor','editor'),(6,'Service Provider','Printers','printers'),(7,'Service Provider','Marketing','marketing'),(8,'Service Provider','Cover Designer','cover-designer'),(9,'Service Provider','Proof Reader','proof-reader'),(10,'Service Provider','Copyright Registration','copyright-registration'),(11,'Service Provider','Public Relation','public-relation');
/*!40000 ALTER TABLE `usertype` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-07-25 13:52:47
