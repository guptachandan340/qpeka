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
  `category` varchar(50) NOT NULL,
  PRIMARY KEY (`categoryid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'book','Childrens Book'),(2,'book','Education'),(3,'book','Fiction'),(4,'book','Non-Fiction');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `countryid` smallint(6) NOT NULL AUTO_INCREMENT,
  `iso2` char(2) DEFAULT NULL,
  `shortname` varchar(80) NOT NULL DEFAULT '',
  `longname` varchar(80) NOT NULL DEFAULT '',
  `iso3` char(3) DEFAULT NULL,
  `numcode` varchar(6) DEFAULT NULL,
  `unmember` varchar(12) DEFAULT NULL,
  `callingcode` varchar(8) DEFAULT NULL,
  `cctld` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`countryid`)
) ENGINE=InnoDB AUTO_INCREMENT=252 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'NN','None','None','Non','0','no','0','.nn'),(2,'AF','Afghanistan','Islamic Republic of Afghanistan','AFG','004','yes','93','.af'),(3,'AX','Aland Islands','&Aring;land Islands','ALA','248','no','358','.ax'),(4,'AL','Albania','Republic of Albania','ALB','008','yes','355','.al'),(5,'DZ','Algeria','People\'s Democratic Republic of Algeria','DZA','012','yes','213','.dz'),(6,'AS','American Samoa','American Samoa','ASM','016','no','1+684','.as'),(7,'AD','Andorra','Principality of Andorra','AND','020','yes','376','.ad'),(8,'AO','Angola','Republic of Angola','AGO','024','yes','244','.ao'),(9,'AI','Anguilla','Anguilla','AIA','660','no','1+264','.ai'),(10,'AQ','Antarctica','Antarctica','ATA','010','no','672','.aq'),(11,'AG','Antigua and Barbuda','Antigua and Barbuda','ATG','028','yes','1+268','.ag'),(12,'AR','Argentina','Argentine Republic','ARG','032','yes','54','.ar'),(13,'AM','Armenia','Republic of Armenia','ARM','051','yes','374','.am'),(14,'AW','Aruba','Aruba','ABW','533','no','297','.aw'),(15,'AU','Australia','Commonwealth of Australia','AUS','036','yes','61','.au'),(16,'AT','Austria','Republic of Austria','AUT','040','yes','43','.at'),(17,'AZ','Azerbaijan','Republic of Azerbaijan','AZE','031','yes','994','.az'),(18,'BS','Bahamas','Commonwealth of The Bahamas','BHS','044','yes','1+242','.bs'),(19,'BH','Bahrain','Kingdom of Bahrain','BHR','048','yes','973','.bh'),(20,'BD','Bangladesh','People\'s Republic of Bangladesh','BGD','050','yes','880','.bd'),(21,'BB','Barbados','Barbados','BRB','052','yes','1+246','.bb'),(22,'BY','Belarus','Republic of Belarus','BLR','112','yes','375','.by'),(23,'BE','Belgium','Kingdom of Belgium','BEL','056','yes','32','.be'),(24,'BZ','Belize','Belize','BLZ','084','yes','501','.bz'),(25,'BJ','Benin','Republic of Benin','BEN','204','yes','229','.bj'),(26,'BM','Bermuda','Bermuda Islands','BMU','060','no','1+441','.bm'),(27,'BT','Bhutan','Kingdom of Bhutan','BTN','064','yes','975','.bt'),(28,'BO','Bolivia','Plurinational State of Bolivia','BOL','068','yes','591','.bo'),(29,'BQ','Bonaire, Sint Eustatius and Saba','Bonaire, Sint Eustatius and Saba','BES','535','no','599','.bq'),(30,'BA','Bosnia and Herzegovina','Bosnia and Herzegovina','BIH','070','yes','387','.ba'),(31,'BW','Botswana','Republic of Botswana','BWA','072','yes','267','.bw'),(32,'BV','Bouvet Island','Bouvet Island','BVT','074','no','NONE','.bv'),(33,'BR','Brazil','Federative Republic of Brazil','BRA','076','yes','55','.br'),(34,'IO','British Indian Ocean Territory','British Indian Ocean Territory','IOT','086','no','246','.io'),(35,'BN','Brunei','Brunei Darussalam','BRN','096','yes','673','.bn'),(36,'BG','Bulgaria','Republic of Bulgaria','BGR','100','yes','359','.bg'),(37,'BF','Burkina Faso','Burkina Faso','BFA','854','yes','226','.bf'),(38,'BI','Burundi','Republic of Burundi','BDI','108','yes','257','.bi'),(39,'KH','Cambodia','Kingdom of Cambodia','KHM','116','yes','855','.kh'),(40,'CM','Cameroon','Republic of Cameroon','CMR','120','yes','237','.cm'),(41,'CA','Canada','Canada','CAN','124','yes','1','.ca'),(42,'CV','Cape Verde','Republic of Cape Verde','CPV','132','yes','238','.cv'),(43,'KY','Cayman Islands','The Cayman Islands','CYM','136','no','1+345','.ky'),(44,'CF','Central African Republic','Central African Republic','CAF','140','yes','236','.cf'),(45,'TD','Chad','Republic of Chad','TCD','148','yes','235','.td'),(46,'CL','Chile','Republic of Chile','CHL','152','yes','56','.cl'),(47,'CN','China','People\'s Republic of China','CHN','156','yes','86','.cn'),(48,'CX','Christmas Island','Christmas Island','CXR','162','no','61','.cx'),(49,'CC','Cocos (Keeling) Islands','Cocos (Keeling) Islands','CCK','166','no','61','.cc'),(50,'CO','Colombia','Republic of Colombia','COL','170','yes','57','.co'),(51,'KM','Comoros','Union of the Comoros','COM','174','yes','269','.km'),(52,'CG','Congo','Republic of the Congo','COG','178','yes','242','.cg'),(53,'CK','Cook Islands','Cook Islands','COK','184','some','682','.ck'),(54,'CR','Costa Rica','Republic of Costa Rica','CRI','188','yes','506','.cr'),(55,'CI','Cote d\'ivoire (Ivory Coast)','Republic of C&ocirc;te D\'Ivoire (Ivory Coast)','CIV','384','yes','225','.ci'),(56,'HR','Croatia','Republic of Croatia','HRV','191','yes','385','.hr'),(57,'CU','Cuba','Republic of Cuba','CUB','192','yes','53','.cu'),(58,'CW','Curacao','Cura&ccedil;ao','CUW','531','no','599','.cw'),(59,'CY','Cyprus','Republic of Cyprus','CYP','196','yes','357','.cy'),(60,'CZ','Czech Republic','Czech Republic','CZE','203','yes','420','.cz'),(61,'CD','Democratic Republic of the Congo','Democratic Republic of the Congo','COD','180','yes','243','.cd'),(62,'DK','Denmark','Kingdom of Denmark','DNK','208','yes','45','.dk'),(63,'DJ','Djibouti','Republic of Djibouti','DJI','262','yes','253','.dj'),(64,'DM','Dominica','Commonwealth of Dominica','DMA','212','yes','1+767','.dm'),(65,'DO','Dominican Republic','Dominican Republic','DOM','214','yes','1+809, 8','.do'),(66,'EC','Ecuador','Republic of Ecuador','ECU','218','yes','593','.ec'),(67,'EG','Egypt','Arab Republic of Egypt','EGY','818','yes','20','.eg'),(68,'SV','El Salvador','Republic of El Salvador','SLV','222','yes','503','.sv'),(69,'GQ','Equatorial Guinea','Republic of Equatorial Guinea','GNQ','226','yes','240','.gq'),(70,'ER','Eritrea','State of Eritrea','ERI','232','yes','291','.er'),(71,'EE','Estonia','Republic of Estonia','EST','233','yes','372','.ee'),(72,'ET','Ethiopia','Federal Democratic Republic of Ethiopia','ETH','231','yes','251','.et'),(73,'FK','Falkland Islands (Malvinas)','The Falkland Islands (Malvinas)','FLK','238','no','500','.fk'),(74,'FO','Faroe Islands','The Faroe Islands','FRO','234','no','298','.fo'),(75,'FJ','Fiji','Republic of Fiji','FJI','242','yes','679','.fj'),(76,'FI','Finland','Republic of Finland','FIN','246','yes','358','.fi'),(77,'FR','France','French Republic','FRA','250','yes','33','.fr'),(78,'GF','French Guiana','French Guiana','GUF','254','no','594','.gf'),(79,'PF','French Polynesia','French Polynesia','PYF','258','no','689','.pf'),(80,'TF','French Southern Territories','French Southern Territories','ATF','260','no',NULL,'.tf'),(81,'GA','Gabon','Gabonese Republic','GAB','266','yes','241','.ga'),(82,'GM','Gambia','Republic of The Gambia','GMB','270','yes','220','.gm'),(83,'GE','Georgia','Georgia','GEO','268','yes','995','.ge'),(84,'DE','Germany','Federal Republic of Germany','DEU','276','yes','49','.de'),(85,'GH','Ghana','Republic of Ghana','GHA','288','yes','233','.gh'),(86,'GI','Gibraltar','Gibraltar','GIB','292','no','350','.gi'),(87,'GR','Greece','Hellenic Republic','GRC','300','yes','30','.gr'),(88,'GL','Greenland','Greenland','GRL','304','no','299','.gl'),(89,'GD','Grenada','Grenada','GRD','308','yes','1+473','.gd'),(90,'GP','Guadaloupe','Guadeloupe','GLP','312','no','590','.gp'),(91,'GU','Guam','Guam','GUM','316','no','1+671','.gu'),(92,'GT','Guatemala','Republic of Guatemala','GTM','320','yes','502','.gt'),(93,'GG','Guernsey','Guernsey','GGY','831','no','44','.gg'),(94,'GN','Guinea','Republic of Guinea','GIN','324','yes','224','.gn'),(95,'GW','Guinea-Bissau','Republic of Guinea-Bissau','GNB','624','yes','245','.gw'),(96,'GY','Guyana','Co-operative Republic of Guyana','GUY','328','yes','592','.gy'),(97,'HT','Haiti','Republic of Haiti','HTI','332','yes','509','.ht'),(98,'HM','Heard Island and McDonald Islands','Heard Island and McDonald Islands','HMD','334','no','NONE','.hm'),(99,'HN','Honduras','Republic of Honduras','HND','340','yes','504','.hn'),(100,'HK','Hong Kong','Hong Kong','HKG','344','no','852','.hk'),(101,'HU','Hungary','Hungary','HUN','348','yes','36','.hu'),(102,'IS','Iceland','Republic of Iceland','ISL','352','yes','354','.is'),(103,'IN','India','Republic of India','IND','356','yes','91','.in'),(104,'ID','Indonesia','Republic of Indonesia','IDN','360','yes','62','.id'),(105,'IR','Iran','Islamic Republic of Iran','IRN','364','yes','98','.ir'),(106,'IQ','Iraq','Republic of Iraq','IRQ','368','yes','964','.iq'),(107,'IE','Ireland','Ireland','IRL','372','yes','353','.ie'),(108,'IM','Isle of Man','Isle of Man','IMN','833','no','44','.im'),(109,'IL','Israel','State of Israel','ISR','376','yes','972','.il'),(110,'IT','Italy','Italian Republic','ITA','380','yes','39','.jm'),(111,'JM','Jamaica','Jamaica','JAM','388','yes','1+876','.jm'),(112,'JP','Japan','Japan','JPN','392','yes','81','.jp'),(113,'JE','Jersey','The Bailiwick of Jersey','JEY','832','no','44','.je'),(114,'JO','Jordan','Hashemite Kingdom of Jordan','JOR','400','yes','962','.jo'),(115,'KZ','Kazakhstan','Republic of Kazakhstan','KAZ','398','yes','7','.kz'),(116,'KE','Kenya','Republic of Kenya','KEN','404','yes','254','.ke'),(117,'KI','Kiribati','Republic of Kiribati','KIR','296','yes','686','.ki'),(118,'XK','Kosovo','Republic of Kosovo','---','---','some','381',''),(119,'KW','Kuwait','State of Kuwait','KWT','414','yes','965','.kw'),(120,'KG','Kyrgyzstan','Kyrgyz Republic','KGZ','417','yes','996','.kg'),(121,'LA','Laos','Lao People\'s Democratic Republic','LAO','418','yes','856','.la'),(122,'LV','Latvia','Republic of Latvia','LVA','428','yes','371','.lv'),(123,'LB','Lebanon','Republic of Lebanon','LBN','422','yes','961','.lb'),(124,'LS','Lesotho','Kingdom of Lesotho','LSO','426','yes','266','.ls'),(125,'LR','Liberia','Republic of Liberia','LBR','430','yes','231','.lr'),(126,'LY','Libya','Libya','LBY','434','yes','218','.ly'),(127,'LI','Liechtenstein','Principality of Liechtenstein','LIE','438','yes','423','.li'),(128,'LT','Lithuania','Republic of Lithuania','LTU','440','yes','370','.lt'),(129,'LU','Luxembourg','Grand Duchy of Luxembourg','LUX','442','yes','352','.lu'),(130,'MO','Macao','The Macao Special Administrative Region','MAC','446','no','853','.mo'),(131,'MK','Macedonia','The Former Yugoslav Republic of Macedonia','MKD','807','yes','389','.mk'),(132,'MG','Madagascar','Republic of Madagascar','MDG','450','yes','261','.mg'),(133,'MW','Malawi','Republic\n of Malawi','MWI','454','yes','265','.mw'),(134,'MY','Malaysia','Malaysia','MYS','458','yes','60','.my'),(135,'MV','Maldives','Republic of Maldives','MDV','462','yes','960','.mv'),(136,'ML','Mali','Republic of Mali','MLI','466','yes','223','.ml'),(137,'MT','Malta','Republic of Malta','MLT','470','yes','356','.mt'),(138,'MH','Marshall Islands','Republic of the Marshall Islands','MHL','584','yes','692','.mh'),(139,'MQ','Martinique','Martinique','MTQ','474','no','596','.mq'),(140,'MR','Mauritania','Islamic Republic of Mauritania','MRT','478','yes','222','.mr'),(141,'MU','Mauritius','Republic of Mauritius','MUS','480','yes','230','.mu'),(142,'YT','Mayotte','Mayotte','MYT','175','no','262','.yt'),(143,'MX','Mexico','United Mexican States','MEX','484','yes','52','.mx'),(144,'FM','Micronesia','Federated States of Micronesia','FSM','583','yes','691','.fm'),(145,'MD','Moldava','Republic of Moldova','MDA','498','yes','373','.md'),(146,'MC','Monaco','Principality of Monaco','MCO','492','yes','377','.mc'),(147,'MN','Mongolia','Mongolia','MNG','496','yes','976','.mn'),(148,'ME','Montenegro','Montenegro','MNE','499','yes','382','.me'),(149,'MS','Montserrat','Montserrat','MSR','500','no','1+664','.ms'),(150,'MA','Morocco','Kingdom of Morocco','MAR','504','yes','212','.ma'),(151,'MZ','Mozambique','Republic of Mozambique','MOZ','508','yes','258','.mz'),(152,'MM','Myanmar (Burma)','Republic of the Union of Myanmar','MMR','104','yes','95','.mm'),(153,'NA','Namibia','Republic of Namibia','NAM','516','yes','264','.na'),(154,'NR','Nauru','Republic of Nauru','NRU','520','yes','674','.nr'),(155,'NP','Nepal','Federal Democratic Republic of Nepal','NPL','524','yes','977','.np'),(156,'NL','Netherlands','Kingdom of the Netherlands','NLD','528','yes','31','.nl'),(157,'NC','New Caledonia','New Caledonia','NCL','540','no','687','.nc'),(158,'NZ','New Zealand','New Zealand','NZL','554','yes','64','.nz'),(159,'NI','Nicaragua','Republic of Nicaragua','NIC','558','yes','505','.ni'),(160,'NE','Niger','Republic of Niger','NER','562','yes','227','.ne'),(161,'NG','Nigeria','Federal Republic of Nigeria','NGA','566','yes','234','.ng'),(162,'NU','Niue','Niue','NIU','570','some','683','.nu'),(163,'NF','Norfolk Island','Norfolk Island','NFK','574','no','672','.nf'),(164,'KP','North Korea','Democratic People\'s Republic of Korea','PRK','408','yes','850','.kp'),(165,'MP','Northern Mariana Islands','Northern Mariana Islands','MNP','580','no','1+670','.mp'),(166,'NO','Norway','Kingdom of Norway','NOR','578','yes','47','.no'),(167,'OM','Oman','Sultanate of Oman','OMN','512','yes','968','.om'),(168,'PK','Pakistan','Islamic Republic of Pakistan','PAK','586','yes','92','.pk'),(169,'PW','Palau','Republic of Palau','PLW','585','yes','680','.pw'),(170,'PS','Palestine','State of Palestine (or Occupied Palestinian Territory)','PSE','275','some','970','.ps'),(171,'PA','Panama','Republic of Panama','PAN','591','yes','507','.pa'),(172,'PG','Papua New Guinea','Independent State of Papua New Guinea','PNG','598','yes','675','.pg'),(173,'PY','Paraguay','Republic of Paraguay','PRY','600','yes','595','.py'),(174,'PE','Peru','Republic of Peru','PER','604','yes','51','.pe'),(175,'PH','Philippines','Republic of the Philippines','PHL','608','yes','63','.ph'),(176,'PN','Pitcairn','Pitcairn','PCN','612','no','NONE','.pn'),(177,'PL','Poland','Republic of Poland','POL','616','yes','48','.pl'),(178,'PT','Portugal','Portuguese Republic','PRT','620','yes','351','.pt'),(179,'PR','Puerto Rico','Commonwealth of Puerto Rico','PRI','630','no','1+939','.pr'),(180,'QA','Qatar','State of Qatar','QAT','634','yes','974','.qa'),(181,'RE','Reunion','R&eacute;union','REU','638','no','262','.re'),(182,'RO','Romania','Romania','ROU','642','yes','40','.ro'),(183,'RU','Russia','Russian Federation','RUS','643','yes','7','.ru'),(184,'RW','Rwanda','Republic of Rwanda','RWA','646','yes','250','.rw'),(185,'BL','Saint Barthelemy','Saint Barth&eacute;lemy','BLM','652','no','590','.bl'),(186,'SH','Saint Helena','Saint Helena, Ascension and Tristan da Cunha','SHN','654','no','290','.sh'),(187,'KN','Saint Kitts and Nevis','Federation of Saint Christopher and Nevis','KNA','659','yes','1+869','.kn'),(188,'LC','Saint Lucia','Saint Lucia','LCA','662','yes','1+758','.lc'),(189,'MF','Saint Martin','Saint Martin','MAF','663','no','590','.mf'),(190,'PM','Saint Pierre and Miquelon','Saint Pierre and Miquelon','SPM','666','no','508','.pm'),(191,'VC','Saint Vincent and the Grenadines','Saint Vincent and the Grenadines','VCT','670','yes','1+784','.vc'),(192,'WS','Samoa','Independent State of Samoa','WSM','882','yes','685','.ws'),(193,'SM','San Marino','Republic of San Marino','SMR','674','yes','378','.sm'),(194,'ST','Sao Tome and Principe','Democratic Republic of S&atilde;o Tom&eacute; and Pr&iacute;ncipe','STP','678','yes','239','.st'),(195,'SA','Saudi Arabia','Kingdom of Saudi Arabia','SAU','682','yes','966','.sa'),(196,'SN','Senegal','Republic of Senegal','SEN','686','yes','221','.sn'),(197,'RS','Serbia','Republic of Serbia','SRB','688','yes','381','.rs'),(198,'SC','Seychelles','Republic of Seychelles','SYC','690','yes','248','.sc'),(199,'SL','Sierra Leone','Republic of Sierra Leone','SLE','694','yes','232','.sl'),(200,'SG','Singapore','Republic of Singapore','SGP','702','yes','65','.sg'),(201,'SX','Sint Maarten','Sint Maarten','SXM','534','no','1+721','.sx'),(202,'SK','Slovakia','Slovak Republic','SVK','703','yes','421','.sk'),(203,'SI','Slovenia','Republic of Slovenia','SVN','705','yes','386','.si'),(204,'SB','Solomon Islands','Solomon Islands','SLB','090','yes','677','.sb'),(205,'SO','Somalia','Somali Republic','SOM','706','yes','252','.so'),(206,'ZA','South Africa','Republic of South Africa','ZAF','710','yes','27','.za'),(207,'GS','South Georgia and the South Sandwich Islands','South Georgia and the South Sandwich Islands','SGS','239','no','500','.gs'),(208,'KR','South Korea','Republic of Korea','KOR','410','yes','82','.kr'),(209,'SS','South Sudan','Republic of South Sudan','SSD','728','yes','211','.ss'),(210,'ES','Spain','Kingdom of Spain','ESP','724','yes','34','.es'),(211,'LK','Sri Lanka','Democratic Socialist Republic of Sri Lanka','LKA','144','yes','94','.lk'),(212,'SD','Sudan','Republic of the Sudan','SDN','729','yes','249','.sd'),(213,'SR','Suriname','Republic of Suriname','SUR','740','yes','597','.sr'),(214,'SJ','Svalbard and Jan Mayen','Svalbard and Jan Mayen','SJM','744','no','47','.sj'),(215,'SZ','Swaziland','Kingdom of Swaziland','SWZ','748','yes','268','.sz'),(216,'SE','Sweden','Kingdom of Sweden','SWE','752','yes','46','.se'),(217,'CH','Switzerland','Swiss Confederation','CHE','756','yes','41','.ch'),(218,'SY','Syria','Syrian Arab Republic','SYR','760','yes','963','.sy'),(219,'TW','Taiwan','Republic of China (Taiwan)','TWN','158','former','886','.tw'),(220,'TJ','Tajikistan','Republic of Tajikistan','TJK','762','yes','992','.tj'),(221,'TZ','Tanzania','United Republic of Tanzania','TZA','834','yes','255','.tz'),(222,'TH','Thailand','Kingdom of Thailand','THA','764','yes','66','.th'),(223,'TL','Timor-Leste (East Timor)','Democratic Republic of Timor-Leste','TLS','626','yes','670','.tl'),(224,'TG','Togo','Togolese Republic','TGO','768','yes','228','.tg'),(225,'TK','Tokelau','Tokelau','TKL','772','no','690','.tk'),(226,'TO','Tonga','Kingdom of Tonga','TON','776','yes','676','.to'),(227,'TT','Trinidad and Tobago','Republic of Trinidad and Tobago','TTO','780','yes','1+868','.tt'),(228,'TN','Tunisia','Republic of Tunisia','TUN','788','yes','216','.tn'),(229,'TR','Turkey','Republic of Turkey','TUR','792','yes','90','.tr'),(230,'TM','Turkmenistan','Turkmenistan','TKM','795','yes','993','.tm'),(231,'TC','Turks and Caicos Islands','Turks and Caicos Islands','TCA','796','no','1+649','.tc'),(232,'TV','Tuvalu','Tuvalu','TUV','798','yes','688','.tv'),(233,'UG','Uganda','Republic of Uganda','UGA','800','yes','256','.ug'),(234,'UA','Ukraine','Ukraine','UKR','804','yes','380','.ua'),(235,'AE','United Arab Emirates','United Arab Emirates','ARE','784','yes','971','.ae'),(236,'GB','United Kingdom','United Kingdom of Great Britain and Nothern Ireland','GBR','826','yes','44','.uk'),(237,'US','United States','United States of America','USA','840','yes','1','.us'),(238,'UM','United States Minor Outlying Islands','United States Minor Outlying Islands','UMI','581','no','NONE','NONE'),(239,'UY','Uruguay','Eastern Republic of Uruguay','URY','858','yes','598','.uy'),(240,'UZ','Uzbekistan','Republic of Uzbekistan','UZB','860','yes','998','.uz'),(241,'VU','Vanuatu','Republic of Vanuatu','VUT','548','yes','678','.vu'),(242,'VA','Vatican City','State of the Vatican City','VAT','336','no','39','.va'),(243,'VE','Venezuela','Bolivarian Republic of Venezuela','VEN','862','yes','58','.ve'),(244,'VN','Vietnam','Socialist Republic of Vietnam','VNM','704','yes','84','.vn'),(245,'VG','Virgin Islands, British','British Virgin Islands','VGB','092','no','1+284','.vg'),(246,'VI','Virgin Islands, US','Virgin Islands of the United States','VIR','850','no','1+340','.vi'),(247,'WF','Wallis and Futuna','Wallis and Futuna','WLF','876','no','681','.wf'),(248,'EH','Western Sahara','Western Sahara','ESH','732','no','212','.eh'),(249,'YE','Yemen','Republic of Yemen','YEM','887','yes','967','.ye'),(250,'ZM','Zambia','Republic of Zambia','ZMB','894','yes','260','.zm'),(251,'ZW','Zimbabwe','Republic of Zimbabwe','ZWE','716','yes','263','.zw');
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
  `extension` varchar(10) NOT NULL DEFAULT 'unknown',
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
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genre` (
  `genreid` smallint(6) NOT NULL AUTO_INCREMENT,
  `categoryid` smallint(6) NOT NULL,
  `genre` varchar(50) NOT NULL DEFAULT '',
  `points` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`genreid`),
  KEY `categoryid_fk` (`categoryid`),
  CONSTRAINT `categoryid_fk` FOREIGN KEY (`categoryid`) REFERENCES `category` (`categoryid`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (1,3,'Adult',0),(2,3,'Classic',0),(3,3,'Fantasy',0),(4,3,'Crime',0),(5,3,'Mistry',0),(6,3,'Historical',0),(7,3,'Romance',0),(8,3,'Drama',0),(9,3,'SciFi',0),(10,3,'Horror',0),(11,3,'Picture Books',0),(12,3,'Rhytmic Books',0),(13,3,'Folklore',0),(14,3,'Fables',0),(15,3,'Fairytales',0),(16,4,'Adult',0),(17,4,'Classic',0),(18,4,'Crime',0),(19,4,'Mistry',0),(20,4,'Historical',0),(21,4,'Romance',0),(22,4,'Drama',0),(23,4,'Biography',0),(24,4,'Autobiography',0),(25,4,'Business',0),(26,4,'Environment',0),(27,4,'Health',0),(28,4,'Personal Development',0),(29,4,'Politics',0),(30,4,'Philosophy',0),(31,4,'Society',0),(32,4,'Science',0),(33,4,'Technology',0),(34,4,'Cultural',0),(35,4,'Arts',0),(36,4,'Picture Books',0),(37,1,'Classic',0),(38,1,'Fantasy',0),(39,1,'Mistry',0),(40,1,'Historical',0),(41,1,'Romance',0),(42,1,'Drama',0),(43,1,'SciFi',0),(44,1,'Biography',0),(45,1,'Autobiography',0),(46,1,'Environment',0),(47,1,'Health',0),(48,1,'Personal Development',0),(49,1,'Philosophy',0),(50,1,'Society',0),(51,1,'Science',0),(52,1,'Technology',0),(53,1,'Cultural',0),(54,1,'Arts',0),(55,1,'Picture Books',0),(56,1,'Rhytmic Books',0),(57,1,'Baby Learning',0),(58,1,'Folklore',0),(59,1,'Fables',0),(60,1,'Fairytales',0),(61,1,'Children Learning',0),(62,2,'Historical',0),(63,2,'Biography',0),(64,2,'Autobiography',0),(65,2,'Business',0),(66,2,'Environment',0),(67,2,'Health',0),(68,2,'Personal Development',0),(69,2,'Politics',0),(70,2,'Philosophy',0),(71,2,'Society',0),(72,2,'Science',0),(73,2,'Technology',0),(74,2,'Cultural',0),(75,2,'Arts',0),(76,2,'Picture Books',0),(77,2,'Rhytmic Books',0),(78,2,'Baby Learning',0),(79,2,'Children Learning',0);
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `languages`
--

DROP TABLE IF EXISTS `languages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `languages` (
  `languageid` smallint(6) NOT NULL AUTO_INCREMENT,
  `language` varchar(12) NOT NULL DEFAULT '',
  `script` varchar(64) NOT NULL DEFAULT '',
  `native` smallint(5) NOT NULL DEFAULT '0',
  `direction` tinyint(4) NOT NULL DEFAULT '0',
  `enabled` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`languageid`),
  KEY `language` (`language`),
  KEY `list` (`script`),
  KEY `native` (`native`),
  CONSTRAINT `languages_ibfk_1` FOREIGN KEY (`native`) REFERENCES `country` (`countryid`)
) ENGINE=InnoDB AUTO_INCREMENT=326 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `languages`
--

LOCK TABLES `languages` WRITE;
/*!40000 ALTER TABLE `languages` DISABLE KEYS */;
INSERT INTO `languages` VALUES (1,'language','script',1,1,1),(2,'Abkhaz','Georgian',1,0,0),(3,'Afaan Oromo','Ethiopic',1,0,1),(4,'Afaan Oromo','Latin',1,0,1),(5,'Afrikaans','Latin',206,0,0),(6,'Akan','Latin',1,0,0),(7,'Albanian','Latin',4,0,0),(8,'Amharic lang','Ethiopic',72,0,0),(9,'Arabic','Arabic',5,1,0),(10,'Arabic','Arabic',19,1,0),(11,'Arabic','Arabic',67,1,0),(12,'Arabic','Arabic',106,1,0),(13,'Arabic','Arabic',114,1,0),(14,'Arabic','Arabic',119,1,0),(15,'Arabic','Arabic',123,1,0),(16,'Arabic','Arabic',126,1,0),(17,'Arabic','Arabic',150,1,0),(18,'Arabic','Arabic',167,1,0),(19,'Arabic','Arabic',180,1,0),(20,'Arabic','Arabic',195,1,0),(21,'Arabic','Arabic',218,1,0),(22,'Arabic','Arabic',228,1,0),(23,'Arabic','Arabic',235,1,0),(24,'Arabic','Arabic',249,1,0),(25,'Armenian','Armenian',13,0,0),(26,'Assamese','Bengali',1,0,0),(27,'Avestan','Avestan',1,1,0),(28,'Azeri','Cyrillic',1,0,1),(29,'Azeri','Latin',1,0,1),(30,'Bakhtiari','Arabic',1,1,0),(31,'Balinese','Latin',1,0,0),(32,'Balochi','Arabic',1,1,0),(33,'Bashkir','Cyrillic',1,0,0),(34,'Basque','Latin',1,0,0),(35,'Belarusian','Latin',1,0,0),(36,'Belorussian','Cyrillic',1,0,0),(37,'Bengali','Bengali',20,0,0),(38,'Bengali','Bengali',103,0,0),(39,'Bhojpuri','Devanagari',1,0,0),(40,'Bihari','Devanagari',1,0,0),(41,'Bosnian','Cyrillic',30,0,1),(42,'Bosnian','Latin',30,0,1),(43,'Breton','Latin',1,0,0),(44,'Bugis','Latin',1,0,0),(45,'Bulgarian','Cyrillic',1,0,0),(46,'Burmese','Burmese',1,0,0),(47,'Catalan','Latin',1,0,0),(48,'Celtiberian','Latin',1,0,0),(49,'Cham','Arabic',1,1,0),(50,'Cherokee','Cherokee',237,0,0),(51,'Chinese','Bopomofo',219,0,0),(52,'Chinese','Han',47,0,0),(53,'Chinese','Han',100,0,0),(54,'Chinese','Han',130,0,0),(55,'Chinese','Han',200,0,0),(56,'Chinese','Han',219,0,0),(57,'Corsican','Latin',1,0,0),(58,'Croatian','Latin',30,0,0),(59,'Croatian','Latin',56,0,0),(60,'Czech','Latin',1,0,0),(61,'Dairi','Latin',1,0,0),(62,'Danish','Latin',1,0,0),(63,'Dari','Arabic',1,1,0),(64,'Divehi','Thaana',1,1,0),(65,'Dutch','Latin',23,0,0),(66,'Dutch','Latin',156,0,0),(67,'Eastern Cham','Latin',1,0,0),(68,'Edo','Latin',1,0,0),(69,'English','Latin',15,0,0),(70,'English','Latin',24,0,0),(71,'English','Latin',41,0,0),(72,'English','Latin',1,0,0),(73,'English','Latin',100,0,0),(74,'English','Latin',103,0,0),(75,'English','Latin',104,0,0),(76,'English','Latin',107,0,0),(77,'English','Latin',111,0,0),(78,'English','Latin',134,0,0),(79,'English','Latin',158,0,0),(80,'English','Latin',175,0,0),(81,'English','Latin',200,0,0),(82,'English','Latin',206,0,0),(83,'English','Latin',1,0,0),(84,'English','Latin',236,0,0),(85,'English','Latin',237,0,0),(86,'English','Latin',251,0,0),(87,'Eskimo','Latin',1,0,0),(88,'Estonian','Latin',1,0,0),(89,'Faroese','Latin',1,0,0),(90,'Farsi','Arabic',1,1,1),(91,'Farsi','Cyrillic',1,0,1),(92,'Farsi','Hebrew',1,1,1),(93,'Filipino','Latin',1,0,0),(94,'Finnish','Latin',1,0,0),(95,'French','Latin',23,0,0),(96,'French','Latin',40,0,0),(97,'French','Latin',41,0,0),(98,'French','Latin',55,0,0),(99,'French','Latin',61,0,0),(100,'French','Latin',77,0,0),(101,'French','Latin',97,0,0),(102,'French','Latin',129,0,0),(103,'French','Latin',136,0,0),(104,'French','Latin',146,0,0),(105,'French','Latin',150,0,0),(106,'French','Latin',1,0,0),(107,'French','Latin',181,0,0),(108,'French','Latin',196,0,0),(109,'French','Latin',217,0,0),(110,'French','Latin',1,0,0),(111,'Frisian','Latin',156,0,0),(112,'Fulfulde','Latin',161,0,0),(113,'FYRO Macedon','Cyrillic',1,0,0),(114,'Galician','Latin',1,0,0),(115,'Georgian','Georgian',1,0,0),(116,'German','Latin',16,0,0),(117,'German','Latin',84,0,0),(118,'German','Latin',127,0,0),(119,'German','Latin',129,0,0),(120,'German','Latin',217,0,0),(121,'Gilaki','Arabic',1,1,0),(122,'Gondi','Devanagari',1,0,0),(123,'Greek','Greek',1,0,0),(124,'Greenlandic','Latin',1,0,0),(125,'Guarani','Latin',173,0,0),(126,'Gujarati','Gujarati',1,0,0),(127,'Hausa','Arabic',161,1,1),(128,'Hausa','Latin',161,0,1),(129,'Hawaiian','Latin',237,0,0),(130,'Hebrew','Hebrew',1,1,0),(131,'Hindi','Devanagari',1,0,0),(132,'Hmong','Han',1,0,1),(133,'Hmong','Latin',1,0,1),(134,'Hmong','Thai',1,0,1),(135,'Ho','Devanagari',1,0,1),(136,'Ho','Oriya',1,0,1),(137,'Hungarian','Latin',1,0,0),(138,'Iberian','Greek',1,0,1),(139,'Iberian','Latin',1,0,1),(140,'Ibibio','Nsibidi',161,0,0),(141,'Icelandic','Latin',1,0,0),(142,'Igbo','Latin',161,0,0),(143,'Ilokano','Latin',1,0,0),(144,'Indonesian','Latin',1,0,0),(145,'Inuktitut','Latin',1,0,0),(146,'Irish','Latin',1,0,0),(147,'Italian','Latin',110,0,0),(148,'Italian','Latin',217,0,0),(149,'Japanese','Han',1,0,1),(150,'Japanese','Hiragana',1,0,1),(151,'Japanese','Katakana',1,0,1),(152,'Jarai','Latin',1,0,0),(153,'Javanese','Latin',1,0,0),(154,'K\'iche','Latin',1,0,0),(155,'Kacchi','Gujarati',1,0,0),(156,'Kachin','Latin',1,0,0),(157,'Kannada','Kannada',1,0,0),(158,'Kanuri','Arabic',161,1,0),(159,'Kashmiri','Arabic',1,1,1),(160,'Kashmiri','Devanagari',1,0,1),(161,'Kazakh','Arabic',1,1,1),(162,'Kazakh','Cyrillic',1,0,1),(163,'Kazakh','Latin',1,0,1),(164,'Kharoshthi','Kharoshthi',1,0,0),(165,'Khasi','Latin',1,0,0),(166,'Khmer','Khmer',1,0,0),(167,'Kinyarwanda','Latin',1,0,0),(168,'Konkani','Devanagari',1,0,0),(169,'Korean','Han',1,0,1),(170,'Korean','Hangul',1,0,1),(171,'Kurdish','Arabic',1,1,1),(172,'Kurdish','Cyrillic',1,0,1),(173,'Kurdish','Latin',1,0,1),(174,'Kyrgyz','Cyrillic',1,0,0),(175,'Lao','Lao',1,0,0),(176,'Latvian','Latin',1,0,0),(177,'Limbu','Devanagari',1,0,0),(178,'Lithuanian','Latin',1,0,0),(179,'Luxembourgis','Latin',1,0,0),(180,'Macedonian','Cyrillic',1,0,0),(181,'Makasarese','Latin',1,0,0),(182,'Malay','Arabic',1,1,1),(183,'Malay','Latin',1,0,1),(184,'Malay','Thai',1,0,1),(185,'Malayalam','Arabic',1,1,1),(186,'Malayalam','Malayalam',1,0,1),(187,'Maltese','Latin',1,0,0),(188,'Mandailing','Latin',1,0,1),(189,'Mandailing','Latin',1,0,1),(190,'Mandar','Latin',1,0,0),(191,'Manipuri','Bengali',1,0,0),(192,'Maori','Latin',158,0,0),(193,'Marathi','Devanagari',1,0,0),(194,'Mingrelian','Georgian',1,0,0),(195,'Mizo','Bengali',1,0,0),(196,'Mon','Khmer',1,0,0),(197,'Mongolian','Cyrillic',1,0,1),(198,'Mongolian','Mongolian',1,0,1),(199,'Mundari','Devanagari',1,0,0),(200,'Naga','Bengali',1,0,0),(201,'Nepali','Devanagari',1,0,0),(202,'Newari','Devanagari',1,0,0),(203,'Norwegian','Latin',1,0,0),(204,'Norwegian','Latin',1,0,0),(205,'Occitan','Latin',1,0,0),(206,'Oriya langua','Oriya',1,0,0),(207,'Pali','Oriya',1,0,1),(208,'Pali','Sinhala',1,0,1),(209,'Pali','Thai',1,0,1),(210,'Pangasinan','Latin',1,0,0),(211,'Papiamentu','Latin',1,0,0),(212,'Pashto','Arabic',1,1,0),(213,'Persian','Arabic',1,1,0),(214,'Polish','Latin',1,0,0),(215,'Portuguese','Latin',33,0,0),(216,'Portuguese','Latin',178,0,0),(217,'Punjabi','Arabic',1,1,1),(218,'Punjabi','Gurmukhi',1,0,1),(219,'Qashqai','Arabic',1,1,0),(220,'Quecha','Latin',28,0,0),(221,'Quecha','Latin',66,0,0),(222,'Quecha','Latin',174,0,0),(223,'Rajasthani','Devanagari',1,0,0),(224,'Rejang','Rejang',1,0,0),(225,'Rhaeto','Latin',1,0,0),(226,'Romanian','Cyrillic',1,0,1),(227,'Romanian','Latin',1,0,1),(228,'Russian','Cyrillic',1,0,0),(229,'Sami','Lappish',1,1,0),(230,'Sanskrit','Devanagari',1,0,1),(231,'Sanskrit','Sinhala',1,0,1),(232,'Santali','Bengali',1,0,1),(233,'Santali','Devanagari',1,0,1),(234,'Santali','Latin',1,0,1),(235,'Santali','Oriya',1,0,1),(236,'Scottish Gae','Latin',1,0,0),(237,'Sepedi','Latin',1,0,0),(238,'Serbian','Cyrillic',1,0,1),(239,'Serbian','Glagolitic',1,0,1),(240,'Serbian','Latin',1,0,1),(241,'Sharada','Sharada',1,0,0),(242,'Simalungun','Latin',1,0,0),(243,'Sindhi','Arabic',1,1,1),(244,'Sindhi','Devanagari',1,0,1),(245,'Sinhalese','Sinhala',211,0,0),(246,'Slovak','Latin',1,0,0),(247,'Slovenian','Latin',1,0,0),(248,'Somali','Arabic',1,1,1),(249,'Somali','Latin',1,0,1),(250,'Somali','Osmanya',1,0,1),(251,'Sora','Latin',1,0,1),(252,'Sora','Oriya',1,0,1),(253,'Sora','Telugu',1,0,1),(254,'Spanish','Latin',12,0,0),(255,'Spanish','Latin',28,0,0),(256,'Spanish','Latin',46,0,0),(257,'Spanish','Latin',50,0,0),(258,'Spanish','Latin',54,0,0),(259,'Spanish','Latin',65,0,0),(260,'Spanish','Latin',66,0,0),(261,'Spanish','Latin',68,0,0),(262,'Spanish','Latin',92,0,0),(263,'Spanish','Latin',99,0,0),(264,'Spanish','Latin',1,0,0),(265,'Spanish','Latin',143,0,0),(266,'Spanish','Latin',159,0,0),(267,'Spanish','Latin',171,0,0),(268,'Spanish','Latin',173,0,0),(269,'Spanish','Latin',174,0,0),(270,'Spanish','Latin',179,0,0),(271,'Spanish','Latin',1,0,0),(272,'Spanish','Latin',1,0,0),(273,'Spanish','Latin',237,0,0),(274,'Spanish','Latin',239,0,0),(275,'Spanish','Latin',243,0,0),(276,'Sundanese','Arabic',1,1,0),(277,'Sutu','Latin',1,0,0),(278,'Swahili','Arabic',1,1,1),(279,'Swahili','Latin',1,0,1),(280,'Swedish','Latin',76,0,0),(281,'Sylheti','Bengali',1,0,0),(282,'Syriac','Syriac',1,1,0),(283,'Tagalog','Latin',1,0,0),(284,'Tajik','Cyrillic',1,0,0),(285,'Takestani','Arabic',1,1,0),(286,'Tamahaq','Latin',1,0,0),(287,'Tamazight','Arabic',1,1,1),(288,'Tamazight','Latin',1,0,1),(289,'Tamil','Tamil',1,0,0),(290,'Tatar','Latin',1,0,0),(291,'Tausug','Arabic',1,1,0),(292,'Telugu','Telugu',1,0,0),(293,'Thai','Thai',1,0,0),(294,'Tibetan','Tibetan',27,0,0),(295,'Tibetan','Tibetan',47,0,0),(296,'Tigre','Ethiopic',1,0,0),(297,'Tigrigna','Tigrigna',70,1,0),(298,'Tigrinya','Ethiopic',70,0,0),(299,'Tigrinya','Ethiopic',72,0,0),(300,'Toba','Latin',1,0,0),(301,'Tsonga','Latin',1,0,0),(302,'Tswana','Tswana',1,1,0),(303,'Turkish','Latin',1,0,0),(304,'Turkmen','Latin',1,0,0),(305,'Uighur','Arabic',1,1,1),(306,'Uighur','Latin',1,0,1),(307,'Ukrainian','Cyrillic',1,0,0),(308,'Urdu','Arabic',1,1,1),(309,'Urdu','Devanagari',1,0,1),(310,'Uzbek','Cyrillic',1,0,1),(311,'Uzbek','Latin',1,0,1),(312,'Venda','Latin',1,0,0),(313,'Vietnamese','Latin',1,0,0),(314,'Visayan','Latin',1,0,0),(315,'Welsh','Latin',1,0,0),(316,'Western Cham','Arabic',1,1,1),(317,'Western Cham','Latin',1,0,1),(318,'Wolof','Latin',1,0,0),(319,'Xhosa','Latin',1,0,0),(320,'Yakut','Cyrillic',1,0,0),(321,'Yi','Yi',1,0,0),(322,'Yiddish','Hebrew',1,1,0),(323,'Yoruba','Latin',1,0,0),(324,'Zhuang','Latin',1,0,0),(325,'Zulu','Latin',1,0,0);
/*!40000 ALTER TABLE `languages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `serviceresponse`
--

DROP TABLE IF EXISTS `serviceresponse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `serviceresponse` (
  `errorid` smallint(11) NOT NULL AUTO_INCREMENT,
  `status` int(11) NOT NULL DEFAULT '200',
  `name` varchar(64) NOT NULL DEFAULT 'ok',
  `message` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`errorid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serviceresponse`
--

LOCK TABLES `serviceresponse` WRITE;
/*!40000 ALTER TABLE `serviceresponse` DISABLE KEYS */;
INSERT INTO `serviceresponse` VALUES (1,200,'Success','Success'),(2,64,'suspended','Account is suspended'),(3,34,'already registered','email already registered'),(4,215,'bad request','bad request'),(5,500,'internal server error','internal server error'),(6,84,'pen name already registered','pen name already registered'),(7,404,'wrong credentials','PenName/Email or password is incoorect'),(8,201,'invitation Sent','invitation sent'),(9,202,'invitation rejected','invitation rejected'),(10,401,'session not active','session not active'),(11,214,'file not available','file not available'),(12,415,'No active session','No active session'),(13,204,'user does not exists','userdoes not exists'),(14,74,'successfully logged out','successfully logged  out');
/*!40000 ALTER TABLE `serviceresponse` ENABLE KEYS */;
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
  `created` int(11) NOT NULL DEFAULT '0',
  `session` varchar(250) DEFAULT NULL,
  `status` tinyint(11) NOT NULL DEFAULT '0',
  `sessionobj` text,
  PRIMARY KEY (`sessionid`),
  UNIQUE KEY `userid` (`userid`,`hostname`,`sessionid`,`status`),
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
  `penname` varchar(200) DEFAULT NULL,
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
  UNIQUE KEY `username` (`penname`)
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
  `pincode` int(11) NOT NULL DEFAULT '0',
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
-- Table structure for table `userfieldvisibility`
--

DROP TABLE IF EXISTS `userfieldvisibility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userfieldvisibility` (
  `visibilityid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `fieldname` varchar(20) NOT NULL,
  `status` tinyint(8) DEFAULT '0',
  PRIMARY KEY (`visibilityid`),
  KEY `userfieldvisibility_ibfk_1` (`userid`),
  CONSTRAINT `userfieldvisibility_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `userprofile` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userfieldvisibility`
--

LOCK TABLES `userfieldvisibility` WRITE;
/*!40000 ALTER TABLE `userfieldvisibility` DISABLE KEYS */;
/*!40000 ALTER TABLE `userfieldvisibility` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userinterests`
--

DROP TABLE IF EXISTS `userinterests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userinterests` (
  `userid` int(11) NOT NULL,
  `genreid` smallint(6) NOT NULL,
  PRIMARY KEY (`userid`,`genreid`),
  KEY `genreid` (`genreid`),
  CONSTRAINT `userinterests_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
  CONSTRAINT `userinterests_ibfk_2` FOREIGN KEY (`genreid`) REFERENCES `genre` (`genreid`)
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
-- Table structure for table `userinvites`
--

DROP TABLE IF EXISTS `userinvites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userinvites` (
  `inviteid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `type` varchar(25) NOT NULL DEFAULT 'NULL',
  `inviteIdentifier` varchar(100) NOT NULL,
  `hashvalue` varchar(128) NOT NULL,
  `status` enum('pending','Sent','accepted','rejected') DEFAULT 'pending',
  PRIMARY KEY (`inviteid`),
  KEY `userid` (`userid`),
  CONSTRAINT `userinvites_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `userprofile` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinvites`
--

LOCK TABLES `userinvites` WRITE;
/*!40000 ALTER TABLE `userinvites` DISABLE KEYS */;
/*!40000 ALTER TABLE `userinvites` ENABLE KEYS */;
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
  `tnc` tinyint(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userid`),
  KEY `nationality` (`nationality`),
  KEY `profilepic` (`profilepic`),
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
-- Table structure for table `usersocialconnection`
--

DROP TABLE IF EXISTS `usersocialconnection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usersocialconnection` (
  `usersocialconnid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `platform` varchar(20) NOT NULL DEFAULT 'NULL',
  `socialid` varchar(100) NOT NULL DEFAULT 'NULL',
  PRIMARY KEY (`usersocialconnid`),
  UNIQUE KEY `userid` (`userid`,`platform`),
  CONSTRAINT `usersocialconnection_frkey` FOREIGN KEY (`userid`) REFERENCES `userprofile` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usersocialconnection`
--

LOCK TABLES `usersocialconnection` WRITE;
/*!40000 ALTER TABLE `usersocialconnection` DISABLE KEYS */;
/*!40000 ALTER TABLE `usersocialconnection` ENABLE KEYS */;
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

-- Dump completed on 2013-10-08 20:09:01
