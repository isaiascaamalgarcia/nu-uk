-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 06-04-2015 a las 17:25:43
-- Versión del servidor: 5.6.12-log
-- Versión de PHP: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `nuuk`
--
CREATE DATABASE IF NOT EXISTS `nuuk` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `nuuk`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

CREATE TABLE IF NOT EXISTS `administrador` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `usuario` varchar(30) NOT NULL,
  `contrasena` varchar(100) NOT NULL,
  `perfil` varchar(25) NOT NULL,
  `escuela` int(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `escuela` (`escuela`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `administrador`
--

INSERT INTO `administrador` (`id`, `nombre`, `usuario`, `contrasena`, `perfil`, `escuela`) VALUES
(1, 'Isaías Caamal García', 'isaiasCaamal', '8cb2237d0679ca88db6464eac60da96345513964', 'administrador', 1),
(2, 'Ramón Alejandro Can Tepal', 'ramonCan', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'SuperU', 0),
(3, 'Shary Yareli Chuc Ku', 'sharyChuc', '8cb2237d0679ca88db6464eac60da96345513964', 'administrador', 9),
(4, 'test', 'newuser', '2fb5e13419fc89246865e7a324f476ec624e8740', 'administrador', 15);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrera`
--

CREATE TABLE IF NOT EXISTS `carrera` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `carrera` varchar(100) NOT NULL,
  `tipoCarrera` varchar(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `tipo_idx` (`tipoCarrera`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=23 ;

--
-- Volcado de datos para la tabla `carrera`
--

INSERT INTO `carrera` (`id`, `carrera`, `tipoCarrera`) VALUES
(22, 'Software', 'I');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `encuesta`
--

CREATE TABLE IF NOT EXISTS `encuesta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pregunta` longtext NOT NULL,
  `tipoCarrera` varchar(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `tipoCarrera_idx` (`tipoCarrera`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=99 ;

--
-- Volcado de datos para la tabla `encuesta`
--

INSERT INTO `encuesta` (`id`, `pregunta`, `tipoCarrera`) VALUES
(1, 'Aceptarías trabajar escribiendo artículos en la sección económica de un diario', 'C'),
(2, '¿Te ofrecerías para organizar la despedida de soltero de uno de tus amigos?', 'C'),
(3, '¿Te gustaría dirigir un proyecto de urbanización en tu provincia?', 'A'),
(4, '¿A una frustración siempre opones un pensamiento positivo?', 'S'),
(5, '¿Te dedicarías a socorrer a personas accidentadas o atacadas por asaltantes?', 'D'),
(6, '¿Cuando eras chico  te interesaba saber cómo estaban construidos tus juguetes?', 'I'),
(7, '¿Te interesan más los misterios de la naturaleza que los secretos de la tecnología?', 'E'),
(8, '¿Escuchas atentamente los problemas que te plantean tus amigos?', 'S'),
(9, '¿Te ofrecerías para explicar a tus compañeros un determinado tema que ellos no entendieron?', 'H'),
(10, '¿Eres exigente y crítico con tu equipo de trabajo?', 'I'),
(11, '¿Te atrae armar rompecabezas o puzzles?', 'A'),
(12, '¿Puedes establecer la diferencia conceptual entre macroeconomía y microeconomía?', 'C'),
(13, '¿Usar uniforme te hace sentir distinto  importante?', 'D'),
(14, '¿Participarías como profesional en un espectáculo de acrobacia aérea?', 'D'),
(15, '¿Organizas tu dinero de manera que te alcance hasta el próximo cobro?', 'C'),
(16, '¿Convences fácilmente a otras personas sobre la validez de tus argumentos?', 'S'),
(17, '¿Estás informado sobre los nuevos descubrimientos que se están realizando sobre la Teoría del Big-Bang?', 'E'),
(18, '¿Ante una situación de emergencia actúas rápidamente?', 'D'),
(19, '¿Cuando tienes que resolver un problema matemático  perseveras hasta encontrar la solución?', 'I'),
(20, '¿Si te convocara tu club preferido para planificar  organizar y dirigir un campo de deportes  aceptarías?', 'C'),
(21, '¿Eres el que pone un toque de alegría en las fiestas?', 'A'),
(22, '¿Crees que los detalles son tan importantes como el todo?', 'A'),
(23, '¿Te sentirías a gusto trabajando en un ámbito hospitalario?', 'S'),
(24, '¿Te gustaría participar para mantener el orden ante grandes desórdenes y cataclismos?', 'D'),
(25, '¿Pasarías varias horas leyendo algún libro de tu interés?', 'H'),
(26, '¿Planificas detalladamente tus trabajos antes de empezar?', 'I'),
(27, '¿Entablas una relación casi personal con tu computadora?', 'I'),
(28, '¿Disfrutas modelando con arcilla?', 'A'),
(29, '¿Ayudas habitualmente a los no videntes a cruzar la calle?', 'S'),
(30, '¿Consideras importante que desde la escuela primaria se fomente la actitud crítica y la participación activa?', 'H'),
(31, '¿Aceptarías que las mujeres formaran parte de las fuerzas armadas bajo las mismas normas que los hombres?', 'D'),
(32, '¿Te gustaría crear nuevas técnicas para descubrir las patologías de algunas enfermedades a través del microscopio?', 'E'),
(33, '¿Participarías en una campaña de prevención contra la enfermedad de Chagas?', 'S'),
(34, '¿Te interesan los temas relacionados al pasado y a la evolución del hombre?', 'H'),
(35, '¿Te incluirías en un proyecto de investigación de los movimientos sísmicos y sus consecuencias?', 'E'),
(36, '¿Fuera de los horarios escolares  dedicas algún día de la semana a la realización de actividades corporales?', 'A'),
(37, '¿Te interesan las actividades de mucha acción y de reacción rápida en situaciones imprevistas y de peligro?', 'D'),
(38, '¿Te ofrecerías para colaborar como voluntario en los gabinetes espaciales de la NASA?', 'I'),
(39, '¿Te gusta más el trabajo manual que el trabajo intelectual?', 'A'),
(40, '¿Estarías dispuesto a renunciar a un momento placentero para ofrecer tu servicio como profesional?', 'S'),
(41, '¿Participarías de una investigación sobre la violencia en el fútbol?', 'H'),
(42, '¿Te gustaría trabajar en un laboratorio mientras estudias?', 'E'),
(43, '¿Arriesgarías tu vida para salvar la vida de otro que no conoces?', 'D'),
(44, '¿Te agradaría hacer un curso de primeros auxilios?', 'S'),
(45, '¿Tolerarías empezar tantas veces como fuere necesario hasta obtener el logro deseado?', 'A'),
(46, '¿Distribuyes tus horarios del día adecuadamente para poder hacer todo lo planeado?', 'C'),
(47, '¿Harías un curso para aprender a fabricar los instrumentos y/o piezas de las máquinas o aparatos con que trabajas?', 'I'),
(48, '¿Elegirías una profesión en la tuvieras que estar algunos meses alejado de tu familia  por ejemplo el marino?', 'D'),
(49, '¿Te radicarías en una zona agrícola-ganadera para desarrollar tus actividades como profesional?', 'E'),
(50, '¿Cuando estás en un grupo trabajando  te entusiasma producir ideas originales y que sean tenidas en cuenta?', 'A'),
(51, '¿Te resulta fácil coordinar un grupo de trabajo?', 'C'),
(52, '¿Te resultó interesante el estudio de las ciencias biológicas?', 'S'),
(53, '¿Si una gran empresa solicita un profesional como gerente de comercialización  te sentirías a gusto desempeñando ese rol?', 'C'),
(54, '¿Te incluirías en un proyecto nacional de desarrollo de la principal fuente de recursos de tu provincia?', 'I'),
(55, '¿Tienes interés por saber cuáles son las causas que determinan ciertos fenómenos  aunque saberlo no altere tu vida?', 'E'),
(56, '¿Descubriste algún filósofo o escritor que haya expresado tus mismas ideas con antelación?', 'H'),
(57, '¿Desearías que te regalen algún instrumento musical para tu cumpleaños?', 'A'),
(58, '¿Aceptarías colaborar con el cumplimiento de las normas en lugares públicos?', 'D'),
(59, '¿Crees que tus ideas son importantes  y haces todo lo posible para ponerlas en práctica?', 'I'),
(60, '¿Cuándo se descompone un artefacto en tu casa  te dispones prontamente a repararlo?', 'I'),
(61, '¿Formarías parte de un equipo de trabajo orientado a la preservación de la flora y la fauna en extinción?', 'E'),
(62, '¿Acostumbras a leer revistas relacionadas con los últimos avances científicos y tecnológicos en el área de la salud?', 'S'),
(63, '¿Preservar las raíces culturales de nuestro país  te parece importante y necesario?', 'H'),
(64, '¿Te gustaría realizar una investigación que contribuyera a hacer más justa la distribución de la riqueza?', 'C'),
(65, '¿Te gustaría realizar tareas auxiliares en una nave  como por ejemplo izado y arriado de velas  pintura y conservación del casco  arreglo de averías  conservación de motores  etc?', 'D'),
(66, '¿Crees que un país debe poseer la más alta tecnología armamentista  a cualquier precio?', 'D'),
(67, '¿La libertad y la justicia son valores fundamentales en tu vida?', 'H'),
(68, '¿Aceptarías hacer una práctica rentada en una industria de productos alimenticios en el sector de control de calidad?', 'E'),
(69, '¿Consideras que la salud pública debe ser prioritaria  gratuita y eficiente para todos?', 'S'),
(70, '¿Te interesaría investigar sobre alguna nueva vacuna?', 'S'),
(71, '¿En un equipo de trabajo  prefieres el rol de coordinador?', 'C'),
(72, '¿En una discusión entre amigos  te ofreces como mediador?', 'H'),
(73, '¿Estás de acuerdo con la formación de un cuerpo de soldados profesionales?', 'D'),
(74, '¿Lucharías por una causa justa hasta las últimas consecuencias?', 'H'),
(75, '¿Te gustaría investigar científicamente sobre cultivos agrícolas?', 'I'),
(76, '¿Harías un nuevo diseño de una prenda pasada de moda  ante una reunión imprevista?', 'A'),
(77, '¿Visitarías un observatorio astronómico para conocer en acción el funcionamiento de los aparatos?', 'E'),
(78, '¿Dirigirías el área de importación y exportación de una empresa?', 'C'),
(79, '¿Te inhibís al entrar a un lugar nuevo con gente desconocida?', 'E'),
(80, '¿Te gratificaría el trabajar con niños?', 'H'),
(81, '¿Harías el diseño de un afiche para una campaña contra el sida?', 'A'),
(82, '¿Dirigirías un grupo de teatro independiente?', 'A'),
(83, '¿Enviarías tu currículum a una empresa automotriz que solicita gerente para su área de producción?', 'I'),
(84, '¿Participarías en un grupo de defensa internacional dentro de alguna fuerza armada?', 'D'),
(85, '¿Te costearías tus estudios trabajando en una auditoría?', 'C'),
(86, '¿Eres de los que defiendes causas perdidas?', 'H'),
(87, '¿Ante una emergencia epidémica participarías en una campaña brindando tu ayuda?', 'S'),
(88, '¿Sabrías responder que significa ADN y ARN?', 'E'),
(89, '¿Elegirías una carrera cuyo instrumento de trabajo fuere la utilización de un idioma extranjero?', 'H'),
(90, '¿Trabajar con objetos te resulta más gratificante que trabajar con personas?', 'I'),
(91, '¿Te resultaría gratificante ser asesor contable en una empresa reconocida?', 'C'),
(92, '¿Ante un llamado solidario  te ofrecerías para cuidar a un enfermo?', 'S'),
(93, '¿Te atrae investigar sobre los misterios del universo  por ejemplo los agujeros negros?', 'E'),
(94, '¿El trabajo individual te resulta más rápido y efectivo que el trabajo grupal?', 'E'),
(95, '¿Dedicarías parte de tu tiempo a ayudar a personas de zonas carenciadas?', 'H'),
(96, '¿Cuándo eliges tu ropa o decoras un ambiente  tienes en cuenta la combinación de los colores  las telas o el estilo de los muebles?', 'A'),
(97, '¿Te gustaría trabajar como profesional dirigiendo la construcción de una empresa hidroeléctrica?', 'I'),
(98, '¿Sabes qué es el PBI?', 'C');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `escuela`
--

CREATE TABLE IF NOT EXISTS `escuela` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` tinyint(4) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `direccion` varchar(200) NOT NULL,
  `latitud` varchar(100) NOT NULL,
  `longitud` varchar(100) NOT NULL,
  `telefono` varchar(8) DEFAULT NULL,
  `pagina` varchar(50) DEFAULT NULL,
  `correo` varchar(50) DEFAULT NULL,
  `facebook` varchar(50) DEFAULT NULL,
  `twitter` varchar(50) DEFAULT NULL,
  `sector` varchar(10) NOT NULL,
  `modificacion` int(11) NOT NULL,
  `idLocalidad` int(11) NOT NULL,
  `estado` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `modificacion_idx` (`modificacion`),
  KEY `idlocalidad_idx` (`idLocalidad`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=243 ;

--
-- Volcado de datos para la tabla `escuela`
--

INSERT INTO `escuela` (`id`, `tipo`, `nombre`, `direccion`, `latitud`, `longitud`, `telefono`, `pagina`, `correo`, `facebook`, `twitter`, `sector`, `modificacion`, `idLocalidad`, `estado`) VALUES
(1, 1, 'CENTRO DE ACTUALIZACIÓN DEL MAGISTERIO', 'TRES GARANTIAS CALLE 24 DE NOVIEMBRE Y 12 DE DICIEMBRE', '18.525854', '-88.297017', '8334832', 'NULL', 'cam_1960@hotmail.com', 'NULL', 'NULL', 'PUBLICO', 1, 22, 1),
(2, 1, 'CENTRO REGIONAL DE EDUCACIÓN NORMAL', 'KILOMETRO 1.5 A VALLADOLID', '19.591484', '-88.049277', '8340863', 'NULL', 'crenfcpqr@yahoo.com.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 6, 1),
(3, 1, 'CENTRO REGIONAL DE EDUCACIÓN NORMAL JAVIER ROJO GOMEZ', 'AVENIDA COSTERA S/N', '18.668641', '-88.394626', '8342185', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 74, 1),
(4, 1, 'JAVIER ROJO GOMEZ', 'AVENIDA COSTERA S/N', '18.678611', '-88.395278', '8342185', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 74, 1),
(5, 1, 'CENTRO DE EDUCACIÓN CONTINUA UNIDAD CANCÚN', 'BOULEVARD KUKULCAN KILOMETRO 12.5 CENTRO EMPRESARIAL TORRE A ENTRE CALLE TORRE QUINTANA PRIMER PISO', '21.164167', '-86.8475', '7296000', 'NULL', 'gamaro@ipn.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 39, 1),
(6, 1, 'INSTITUTO TECNOLÓGICO DE CHETUMAL', 'AVENIDA INSURGENTES NUM. 830  A.P. 267 CALLE TORRE QUINTANA PRIMER PISO Y ENTRE CALLE ANDRES QUINTANA ROO Y EMILIANO ZAPATA', '18.505278', '-88.305278', '8321019', 'NULL', 'arsantin@yahoo.com', 'NULL', 'NULL', 'PÚBLICO', 3, 22, 1),
(7, 1, 'INSTITUTO TECNOLÓGICO DE CHETUMAL', 'AVENIDA INSURGENTES NUM. 830  A.P. 267 ENTRE CALLE ANDRES QUINTANA ROO Y EMILIANO ZAPATA', '18.505278', '-88.305278', '8321019', 'NULL', 'arsantin@yahoo.com', 'NULL', 'NULL', 'PÚBLICO', 3, 22, 1),
(8, 1, 'INSTITUTO TECNOLÓGICO DE CHETUMAL', 'AVENIDA INSURGENTES NUM. 830  A.P. 267 ENTRE CALLE ANDRES QUINTANA ROO Y EMILIANO ZAPATA', '18.505278', '-88.305278', '8321019', 'NULL', 'arsantin@yahoo.com', 'NULL', 'NULL', 'PÚBLICO', 3, 22, 1),
(9, 1, 'INSTITUTO TECNOLÓGICO DE CANCÚN', 'AVENIDA KABAH KILOMETRO 3 S/N', '21.139261', ' -86.833679', '8807432', 'NULL', 'buzonitc@itcancun.edu.mx', 'NULL', 'NULL', 'PUBLICO', 3, 39, 1),
(10, 1, 'INSTITUTO TECNOLÓGICO DE CANCÚN', 'AVENIDA KABAH KILOMETRO 3 S/N', '21.164167', '-86.8475', '8807432', 'NULL', 'buzonitc@itcancun.edu.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 39, 1),
(11, 1, 'INSTITUTO TECNOLÓGICO DE LA ZONA MAYA', 'CARRETERA CHETUMAL-ESCARCEGA  KILOMETRO 21.5  A.P. 207', '18.514722', '-88.481389', '1046341', 'NULL', 'itzonamaya@correo.dgest.gob.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 78, 1),
(12, 1, 'INSTITUTO TECNOLÓGICO DE LA ZONA MAYA', 'CARRETERA CHETUMAL-ESCARCEGA  KILOMETRO 21.5  A.P. 207', '18.514722', '-88.481389', '1046341', 'NULL', 'itzonamaya@correo.dgest.gob.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 78, 1),
(13, 1, 'UNIDAD REGIONAL DE EL COLEGIO DE LA FRONTERA SUR', 'AVENIDA CENTENARIO KILOMETRO 5.5', '18.505278', '-88.305278', '8350455', 'NULL', 'magda@ecosur-qroo.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 22, 1),
(14, 1, 'UNIDAD REGIONAL DE EL COLEGIO DE LA FRONTERA SUR', 'AVENIDA CENTENARIO KILOMETRO 5.5', '18.505278', '-88.305278', '8350455', 'NULL', 'magda@ecosur-qroo.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 22, 1),
(15, 1, 'UNIVERSIDAD PEDAGOGICA NACIONAL 231 CHETUMAL', 'JUSTO SIERRA S/N CALLE JAIME VILLAURRUTIA', '18.514287', '-88.322606', '8370857', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 22, 1),
(16, 1, 'UNIVERSIDAD PEDAGOGICA NACIONAL 231 CHETUMAL', 'JUSTO SIERRA S/N CALLE JAIME VILLAURRUTIA', '18.514287', '-88.322606', '8370857', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 22, 1),
(17, 1, 'UNIVERSIDAD PEDAGOGICA NACIONAL 231 FELIPE CARRILLO PUERTO', 'AVENIDA CONSTITUYENTES ENTRE CALLE 79 Y 76', '19.581973', '-88.041221', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 6, 1),
(18, 1, 'UNIVERSIDAD PEDAGOGICA NACIONAL 231 FELIPE CARRILLO PUERTO', 'AVENIDA CONSTITUYENTES ENTRE CALLE 79 Y 76', '19.581973', '-88.041221', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 6, 1),
(19, 1, 'UNIVERSIDAD PEDAGOGICA NACIONAL 231 CANCÚN', 'SUPER MANZANA 22 RETORNO 7 (ESC. HERMANOS NICHITA)', '21.163113', '-86.874123', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 39, 1),
(20, 1, 'UNIVERSIDAD PEDAGOGICA NACIONAL 231 CANCÚN', 'SUPER MANZANA 22 RETORNO 7 (ESC. HERMANOS NICHITA)', '21.163113', '-86.874123', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 39, 1),
(21, 1, 'INSTITUTO TECNOLÓGICO SUPERIOR DE FELIPE CARRILLO PUERTO', 'CARRETERA VIGIA CHICO KILOMETRO 1.5', '19.578611', '-88.045278', '8340051', 'NULL', 'itse_dg@hotmail.com', 'NULL', 'NULL', 'PÚBLICO', 3, 6, 1),
(22, 1, 'UNIVERSIDAD INTERCULTURAL MAYA DE QUINTANA ROO', 'CARRETERA MUNA-FELIPE CARRILLO PUERTO KILOMETRO 137', '19.803889', '-88.753889', '9780160', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 49, 1),
(23, 1, 'UNIVERSIDAD INTERCULTURAL MAYA DE QUINTANA ROO', 'CARRETERA MUNA-FELIPE CARRILLO PUERTO KILOMETRO 137', '19.803889', '-88.753889', '9780160', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 49, 1),
(24, 1, 'UNIVERSIDAD POLITECNICA DE QUINTANA ROO', 'AVENIDA TULUM MANZANA 1 LOTE 40 PLANTA ALTA ENTRE CALLE UXMAL Y PALENQUE', '21.164167', '-86.8475', '8840221', 'NULL', 'rectoria@upqroo.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 39, 1),
(25, 1, 'UNIVERSIDAD POLITÉCNICA DE BACALAR', 'NULL', '18.678611', '-88.395278', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 74, 1),
(26, 1, 'UNIVERSIDAD DE QUINTANA ROO', 'BOULEVARD BAHIA S/N CALLE COMONFORT', '18.505278', '-88.305278', '8328388', 'NULL', 'fmay@correo.uqroo.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 22, 1),
(27, 1, 'UNIVERSIDAD DE QUINTANA ROO', 'BOULEVARD BAHIA S/N CALLE COMONFORT', '18.505278', '-88.305278', '8328388', 'NULL', 'fmay@correo.uqroo.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 22, 1),
(28, 1, 'UNIVERSIDAD DE QUINTANA ROO', 'BOULEVARD BAHIA S/N CALLE COMONFORT', '18.505278', '-88.305278', '8328388', 'NULL', 'fmay@correo.uqroo.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 22, 1),
(29, 1, 'UNIVERSIDAD DE QUINTANA ROO', 'BOULEVARD BAHIA S/N CALLE COMONFORT', '18.505278', '-88.305278', '8328388', 'NULL', 'fmay@correo.uqroo.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 22, 1),
(30, 1, 'ALFONSO TOHEN ZAMUDIO', 'AVENIDA INSURGENTES NUM. 480 ENTRE CALLE PALERMO Y CORCEGA', '18.505278', '-88.305278', '8320408', 'NULL', 'creechetumal@hotmail.com.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 22, 1),
(31, 1, 'UNIVERSIDAD DE QUINTANA ROO COZUMEL', 'ANDRES QUINTANA ROO S/N LOTE 2 ENTRE CALLE 95 AVENIDA', '20.525', '-86.941667', '8729000', 'NULL', 'ofrausto@correo.uqroo.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 1, 1),
(32, 1, 'UNIVERSIDAD DE QUINTANA ROO COZUMEL', 'ANDRES QUINTANA ROO S/N LOTE 2 ENTRE CALLE 95 AVENIDA', '20.525', '-86.941667', '8729000', 'NULL', 'ofrausto@correo.uqroo.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 1, 1),
(33, 1, 'UNIVERSIDAD DE QUINTANA ROO COZUMEL', 'ANDRES QUINTANA ROO S/N LOTE 2 ENTRE CALLE 95 AVENIDA', '20.525', '-86.941667', '8729000', 'NULL', 'ofrausto@correo.uqroo.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 1, 1),
(34, 1, 'UNIVERSIDAD DEL CARIBE', 'MANZANA 1 LOTE 1', '21.164167', '-86.8475', '8814400', 'NULL', 'recoria@unicaribe.edu.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 39, 1),
(35, 1, 'UNIVERSIDAD DEL CARIBE', 'MANZANA 1 LOTE 1', '21.164167', '-86.8475', '8814400', 'NULL', 'recoria@unicaribe.edu.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 39, 1),
(36, 1, 'UNIVERSIDAD DEL CARIBE', 'MANZANA 1 LOTE 1', '21.164167', '-86.8475', '8814400', 'NULL', 'recoria@unicaribe.edu.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 39, 1),
(37, 1, 'UNIVERSIDAD DE QUINTANA ROO PLAYA DEL CARMEN', 'LOTE 007-01-021 ENTRE CALLE AVENIDA DE LAS UNIVERSIDADES Y AVENIDA DEL TECNOLOGICO', '20.631111', '-87.081111', '2673218', 'NULL', 'cmvazquez@uqroo.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 63, 1),
(38, 1, 'UNIVERSIDAD TECNOLÓGICA DE CANCÚN', 'CARRETERA CANCUN-AEROPUERTO KILOMETRO 115 MANZANA 5 LOTE 1', '21.164167', '-86.8475', '8811900', 'NULL', 'informes@utcancun.edu.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 39, 1),
(39, 1, 'UNIVERSIDAD TECNOLÓGICA DE CANCÚN', 'CARRETERA CANCUN-AEROPUERTO KILOMETRO 115 MANZANA 5 LOTE 1', '21.164167', '-86.8475', '8811900', 'NULL', 'informes@utcancun.edu.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 39, 1),
(40, 1, 'UNIVERSIDAD TECNOLÓGICA DE LA RIVIERA MAYA', 'AVENIDA PASEO MAYAB NUM. 4000', '20.631111', '-87.081111', '8774600', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 63, 1),
(41, 1, 'UNIVERSIDAD TECNOLÓGICA DE LA RIVIERA MAYA', 'AVENIDA PASEO MAYAB NUM. 4000', '20.631111', '-87.081111', '8774600', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 63, 1),
(42, 1, 'UNIVERSIDAD TECNOLÓGICA DE CHETUMAL', 'CENTRO DE ESTUDIOS TECNOLOGICOS DEL MAR NUM.10 PROLONGACION BOULEVARD BAHIA Y CONSERVADOR DIAZ MIRON', '18.505278', '-88.305278', 'NULL', 'NULL', 'utchetumal@hotmail.com', 'NULL', 'NULL', 'PÚBLICO', 3, 22, 1),
(43, 1, 'UNIVERSIDAD TECNOLÓGICA DE CHETUMAL', 'CENTRO DE ESTUDIOS TECNOLOGICOS DEL MAR NUM.10 PROLONGACION BOULEVARD BAHIA Y CONSERVADOR DIAZ MIRON', '18.505278', '-88.305278', 'NULL', 'NULL', 'utchetumal@hotmail.com', 'NULL', 'NULL', 'PÚBLICO', 3, 22, 1),
(44, 1, 'ANDRES QUINTANA ROO', 'MANZANA 1 LOTE 2 ENTRE AVENIDA PUERTO JUAREZ Y CHACMOL', '21.164167', '-86.8475', '8988077', 'NULL', 'ensaqroo@hotmail.com', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(45, 1, 'UNIVERSIDAD LA SALLE CANCÚN', 'CARRETERA CHETUMAL-CANCUN KILOMETRO 344 MANZANA 1 LOTE 1', '21.164167', '-86.8475', '8862201', 'NULL', 'servesc@ulsacancun.edu.mx', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(46, 1, 'UNIVERSIDAD LA SALLE CANCÚN', 'CARRETERA CHETUMAL-CANCUN KILOMETRO 344 MANZANA 1 LOTE 1', '21.164167', '-86.8475', '8862201', 'NULL', 'servesc@ulsacancun.edu.mx', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(47, 1, 'UNIVERSIDAD LA SALLE CANCÚN', 'CARRETERA CHETUMAL-CANCUN KILOMETRO 344 MANZANA 1 LOTE 1', '21.164167', '-86.8475', '8862201', 'NULL', 'servesc@ulsacancun.edu.mx', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(48, 1, 'UNIVERSIDAD LA SALLE CANCÚN', 'CARRETERA CHETUMAL-CANCUN KILOMETRO 344 MANZANA 1 LOTE 1', '21.164167', '-86.8475', '8862201', 'NULL', 'servesc@ulsacancun.edu.mx', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(49, 1, 'INSTITUTO DE ESTUDIOS UNIVERSITARIOS  A. C. PLANTEL CANCÚN', 'AVENIDA MAYAPAN MANZANA 4 LOTE 7 ENTRE CALLE 16 DE ABRIL Y AVENIDA SAYIL', '21.164167', '-86.8475', '8875725', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(50, 1, 'UNIVERSIDAD PARTENON COZUMEL  A.C.', 'AVENIDA 11 SUR S/N ENTRE CALLE 90 Y 95 SUR', '20.525', '-86.941667', '8693874', 'NULL', 'ipartyenon@hotmail.com', 'NULL', 'NULL', 'PRIVADO', 3, 1, 1),
(51, 1, 'UNIVERSIDAD ANAHUAC DE CANCÚN', 'BOULEVARD CANCUN-AEROPUERTO KILOMETRO 7.5', '21.164167', '-86.8475', '8820770', 'NULL', 'mperez@anahuaccancun.edu.mx', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(52, 1, 'UNIVERSIDAD ANAHUAC DE CANCÚN', 'BOULEVARD CANCUN-AEROPUERTO KILOMETRO 7.5', '21.164167', '-86.8475', '8820770', 'NULL', 'mperez@anahuaccancun.edu.mx', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(53, 1, 'UNIVERSIDAD MAYA DE LAS AMÉRICAS', 'AVENIDA FRANCISCO I. MADERO 23 MANZANA 6 LOTE 15 ENTRE CALLE 7 Y 3', '21.164167', '-86.8475', '8879499', 'NULL', 'mayadelasamericas@hotmail.com', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(54, 1, 'ESCUELA SUPERIOR DE LEYES', 'PROLONGACION BONAMPAK NUMERO 8 MANZANA 2 LOTE 5', '21.164167', '-86.8475', '8899400', 'NULL', 'academ@escuelasuperiordeleyes.com', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(55, 1, 'ESCUELA SUPERIOR DE LEYES', 'PROLONGACION BONAMPAK NUMERO 8 MANZANA 2 LOTE 5', '21.164167', '-86.8475', '8899400', 'NULL', 'academ@escuelasuperiordeleyes.com', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(56, 1, 'UNIVERSIDAD LATINOAMÉRICANA DEL CARIBE', 'AVENIDA CHICHEN ITZA NUMERO 126 MANZANA 27', '21.164167', '-86.8475', '8873518', 'NULL', 'santaclaus26@hotmail.com', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(57, 1, 'UNIVERSIDAD LATINOAMÉRICANA DEL CARIBE', 'AVENIDA CHICHEN ITZA NUMERO 126 MANZANA 27', '21.164167', '-86.8475', '8873518', 'NULL', 'santaclaus26@hotmail.com', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(58, 1, 'UNIVERSIDAD LATINOAMÉRICANA DEL CARIBE', 'AVENIDA CHICHEN ITZA NUMERO 126 MANZANA 27', '21.164167', '-86.8475', '8873518', 'NULL', 'santaclaus26@hotmail.com', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(59, 1, 'UNIVERSIDAD LATINOAMÉRICANA DEL CARIBE', 'AVENIDA CHICHEN ITZA NUMERO 126 MANZANA 27', '21.164167', '-86.8475', '8873518', 'NULL', 'santaclaus26@hotmail.com', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(60, 1, 'CENTRO DE ESTUDIOS UNIVER QUINTANA ROO', 'AVENIDA TORRES SUR MANZANA 14 LOTE 1 NUM. 16 ENTRE CALLE NORTE 519 FRACCIONAMIENTO PEHALTUN Y SUR 522', '21.164167', '-86.8475', '9149093', 'NULL', 'univercancun@prodigy.net.mx', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(61, 1, 'CENTRO DE ESTUDIOS UNIVER QUINTANA ROO', 'AVENIDA TORRES SUR MANZANA 14 LOTE 1 NUM. 16 ENTRE CALLE NORTE 519 FRACCIONAMIENTO PEHALTUN Y SUR 522', '21.164167', '-86.8475', '9149093', 'NULL', 'univercancun@prodigy.net.mx', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(62, 1, 'CAMPO ESCUELA SAN JOSE', 'CARRETERA MERIDA-PUERTO JUAREZ KILOMETRO 310.5', '21.164167', '-86.8475', '8986709', 'NULL', 'levisoco@cancun.com.mx', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(63, 1, 'UNIVERSIDAD INTERAMÉRICANA PARA EL DESARROLLO UNIDAD CHETUMAL', 'AVENIDA BENITO JUAREZ NUM. 168 ENTRE AVENIDA EFRAIN AGUILAR Y MAHATMA GANDHI', '18.505278', '-88.305278', '8328559', 'NULL', 'imgarcia@unid.edu.mx', 'NULL', 'NULL', 'PRIVADO', 3, 22, 1),
(64, 1, 'UNIVERSIDAD INTERAMÉRICANA PARA EL DESARROLLO UNIDAD CHETUMAL', 'AVENIDA BENITO JUAREZ NUM. 168 ENTRE AVENIDA EFRAIN AGUILAR Y MAHATMA GANDHI', '18.505278', '-88.305278', '8328559', 'NULL', 'imgarcia@unid.edu.mx', 'NULL', 'NULL', 'PRIVADO', 3, 22, 1),
(65, 1, 'UNIVERSIDAD INTERAMÉRICANA PARA EL DESARROLLO UNIDAD CHETUMAL', 'AVENIDA BENITO JUAREZ NUM. 168 ENTRE AVENIDA EFRAIN AGUILAR Y MAHATMA GANDHI', '18.505278', '-88.305278', '8328559', 'NULL', 'imgarcia@unid.edu.mx', 'NULL', 'NULL', 'PRIVADO', 3, 22, 1),
(66, 1, 'UNIVERSIDAD INTERAMÉRICANA PARA EL DESARROLLO UNIDAD CANCÚN', 'AVENIDA NICHUPTE MANZANA 72 LOTE 5-A Y PROLONGACION KABAH', '21.164167', '-86.8475', '8483120', 'NULL', 'jruiz@unid.edu.mx', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(67, 1, 'UNIVERSIDAD INTERAMÉRICANA PARA EL DESARROLLO UNIDAD CANCÚN', 'AVENIDA NICHUPTE MANZANA 72 LOTE 5-A Y PROLONGACION KABAH', '21.164167', '-86.8475', '8483120', 'NULL', 'jruiz@unid.edu.mx', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(68, 1, 'UNIVERSIDAD INTERAMÉRICANA PARA EL DESARROLO UNIDAD PLAYA DEL CARMEN', 'CARRETERA FEDERAL CON CALLE 28 S/N  LOTE 1  MANZANA 4', '20.631111', '-87.081111', '8038458', 'NULL', 'jruiz@unid.edu.mex', 'NULL', 'NULL', 'PRIVADO', 3, 63, 1),
(69, 1, 'UNIVERSIDAD INTERAMÉRICANA PARA EL DESARROLO UNIDAD PLAYA DEL CARMEN', 'CARRETERA FEDERAL CON CALLE 28 S/N  LOTE 1  MANZANA 4', '20.631111', '-87.081111', '8038458', 'NULL', 'jruiz@unid.edu.mex', 'NULL', 'NULL', 'PRIVADO', 3, 63, 1),
(70, 1, 'UNIVERSIDAD INTERAMÉRICANA PARA EL DESARROLO UNIDAD PLAYA DEL CARMEN', 'CARRETERA FEDERAL CON CALLE 28 S/N  LOTE 1  MANZANA 4', '20.631111', '-87.081111', '8038458', 'NULL', 'jruiz@unid.edu.mex', 'NULL', 'NULL', 'PRIVADO', 3, 63, 1),
(71, 1, 'UNIVERSIDAD INTERAMÉRICANA PARA EL DESARROLO UNIDAD PLAYA DEL CARMEN', 'CARRETERA FEDERAL CON CALLE 28 S/N  LOTE 1  MANZANA 4', '20.631111', '-87.081111', '8038458', 'NULL', 'jruiz@unid.edu.mex', 'NULL', 'NULL', 'PRIVADO', 3, 63, 1),
(72, 1, 'CENTRO DE ESTUDIOS SUPERIORES ULQ ALIAT TULUM', 'CENTAURO NORTE MANZANA 5 LOTE 7 ENTRE AVENIDA TULUM ORIENTE Y POLAR ORIENTE', '20.215833', '-87.465833', '8713545', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 65, 1),
(73, 1, 'INSTITUTO UNIVERSITARIO KUKULCAN', 'CALLE CAYO CENTRO MANZANA 3 LOTE 2 Y 3 ENTRE CALLE CABO CATOCHE Y PUNTA NOH-KU', '21.164167', '-86.8475', '8841435', 'NULL', 'colegiokukulcan@hotmail.com', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(74, 1, 'INSTITUTO UNIVERSITARIO CARL ROGERS', 'AVENIDA INSURGENTES NUMERO 967 ENTRE CALLE LAGUNA OM Y LAGUNA NOH-BEC', '18.505278', '-88.305278', '8370388', 'NULL', 'qroocmic@prodigy.net.mx', 'NULL', 'NULL', 'PRIVADO', 3, 22, 1),
(75, 1, 'INSTITUTO EDUCATIVO FELIPE CARRILLO PUERTO', 'DIAGONAL 63 ENTRE CALLE 93 Y 91', '19.578611', '-88.045278', '1016325', 'NULL', 'cimma@yahoo.com', 'NULL', 'NULL', 'PRIVADO', 3, 6, 1),
(76, 1, 'UNIVERSIDAD RIVIERA', 'AVENIDA 58 NORTE LOTE 4-1', '20.631111', '-87.081111', '1093634', 'NULL', 'sergio.dorantes@universidadriviera.org.mx', 'NULL', 'NULL', 'PRIVADO', 3, 63, 1),
(77, 1, 'UNIVERSIDAD RIVIERA', 'AVENIDA 58 NORTE LOTE 4-1', '20.631111', '-87.081111', '1093634', 'NULL', 'sergio.dorantes@universidadriviera.org.mx', 'NULL', 'NULL', 'PRIVADO', 3, 63, 1),
(78, 1, 'UNIVERSIDAD RIVIERA', 'AVENIDA 58 NORTE LOTE 4-1', '20.631111', '-87.081111', '1093634', 'NULL', 'sergio.dorantes@universidadriviera.org.mx', 'NULL', 'NULL', 'PRIVADO', 3, 63, 1),
(79, 1, 'UNIVERSIDAD MODELO', 'AVENIDA JUAREZ NUMERO 88 ENTRE IGNACIO ZARAGOZA Y PLUTARCO ELIAS CALLES ', '18.505278', '-88.305278', '1270051', 'NULL', 'mduch@modelo.edu.mx', 'NULL', 'NULL', 'PRIVADO', 3, 22, 1),
(80, 1, 'UNIVERSIDAD MODELO', 'AVENIDA JUAREZ NUMERO 88 ENTRE IGNACIO ZARAGOZA Y PLUTARCO ELIAS CALLES ', '18.505278', '-88.305278', '1270051', 'NULL', 'mduch@modelo.edu.mx', 'NULL', 'NULL', 'PRIVADO', 3, 22, 1),
(81, 1, 'UNIVERSIDAD TEC MILENIO CANCÚN', 'AVENIDA BONAMPAK NUMERO 371 MANZANA 2 LOTE 4', '21.164167', '-86.8475', '8814700', 'NULL', 'locarril@tecmilenio.edu.mx', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(82, 1, 'UNIVERSIDAD TEC MILENIO CANCÚN', 'AVENIDA BONAMPAK NUMERO 371 MANZANA 2 LOTE 4', '21.164167', '-86.8475', '8814700', 'NULL', 'locarril@tecmilenio.edu.mx', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(83, 1, 'UNIVERSIDAD DEL SUR PLANTEL CANCÚN', 'AVENIDA UXMAL MANZANA 15 LOTE 10 ENTRE CALLE PULTICUB Y PUNTA PULTICUB  ', '21.164167', '-86.8475', '8923299', 'NULL', 'universidaddelsurcancun@hotmail.com', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(84, 1, 'UNIVERSIDAD DEL SUR PLANTEL CANCÚN', 'AVENIDA UXMAL MANZANA 15 LOTE 10 ENTRE CALLE PULTICUB Y PUNTA PULTICUB  ', '21.164167', '-86.8475', '8923299', 'NULL', 'universidaddelsurcancun@hotmail.com', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(85, 1, 'UNIVERSIDAD DEL SUR PLANTEL CANCÚN', 'AVENIDA UXMAL MANZANA 15 LOTE 10 ENTRE CALLE PULTICUB Y PUNTA PULTICUB  ', '21.164167', '-86.8475', '8923299', 'NULL', 'universidaddelsurcancun@hotmail.com', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(86, 1, 'UNIVERSIDAD DEL SUR PLANTEL CANCÚN', 'AVENIDA UXMAL MANZANA 15 LOTE 10 ENTRE CALLE PULTICUB Y PUNTA PULTICUB  ', '21.164167', '-86.8475', '8923299', 'NULL', 'universidaddelsurcancun@hotmail.com', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(87, 1, 'INSTITUTO CULINARIO DE CANCÚN', 'CALLE FRESNO MANZANA 100 LOTE 2 ENTRE AVENIDA LIMON VERDE Y HUAYACAN', '21.164167', '-86.8475', '8821500', 'NULL', 'aamador@iccun.com.mx', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(88, 1, 'INSTITUTO CULINARIO DE CANCÚN', 'CALLE FRESNO MANZANA 100 LOTE 2 ENTRE AVENIDA LIMON VERDE Y HUAYACAN', '21.164167', '-86.8475', '8821500', 'NULL', 'aamador@iccun.com.mx', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(89, 1, 'UNIVERSIDAD DE ORIENTE - CANCUN', 'MANZANA 296 LOTE 1-03 CARRETERA CANCUN - CHETUMAL KILOMETRO 10.6', '21.164167', '-86.8475', '8821843', 'NULL', 'eac1206@hotmail.com', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(90, 1, 'ANGLOAMÉRICANO CENTRO UNIVERSITARIO', 'CALLE 75 LOTE 13 ENTRE AVENIDA LIMON VERDE Y AVENIDA CHICHEN ITZA', '21.164167', '-86.8475', '8482902', 'NULL', 'anglocentrouniversitario4@gmail.com', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(91, 1, 'UNIVERSIDAD INTERAMERICANA PARA EL DESARROLLO  CAMPUS COZUMEL', 'CALLE 14 NORTE NUMERO 999 ENTRE CALLE 45 Y SO', '20.525', '-86.941667', '8698640', 'NULL', 'cozume1@unid.mx', 'NULL', 'NULL', 'PRIVADO', 3, 1, 1),
(92, 1, 'UNIVERSIDAD INTERAMERICANA PARA EL DESARROLLO  CAMPUS COZUMEL', 'CALLE 14 NORTE NUMERO 999 ENTRE CALLE 45 Y SO', '20.525', '-86.941667', '8698640', 'NULL', 'cozume1@unid.mx', 'NULL', 'NULL', 'PRIVADO', 3, 1, 1),
(93, 1, 'INSTITUTO SUPERIOR DE ESTUDIOS CREATIVOS', 'CARRETERA A PUNTA SAM  MANZANA 4  LOTE 11 KILOMETRO 3.8', '21.235833', '-86.8025', '8984058', 'NULL', 'patmacfarland@prodigy.net.mx', 'NULL', 'NULL', 'PRIVADO', 3, 21, 1),
(94, 1, 'INSTITUTO PRIVADO DEL SUR DE MÉXICO', 'NULL', '21.164167', '-86.8475', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 1, 1),
(95, 1, 'UNIVERSIDAD PARA LA COOPERACION INTERNACIONAL MEXICO', 'NULL', '21.164167', '-86.8475', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(96, 1, 'UNIVERSIDAD PARA LA COOPERACION INTERNACIONAL MEXICO', 'NULL', '21.164167', '-86.8475', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(97, 1, 'CENTRO DE PSICOTERAPIA COGNITIVA', 'NULL', '21.164167', '-86.8475', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(98, 1, 'CENTRO UNIVERSITARIO INGLES', 'NULL', '20.631111', '-87.081111', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 63, 1),
(99, 1, 'INSTITUTO DE ESTUDIOS SUPERIORES DE LA COMUNICACIÓN', 'NULL', '21.164167', '-86.8475', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(100, 1, 'INSTITUTO MEXICANO DE ESTUDIOS PEDAGOGICOS', 'NULL', '18.505278', '-88.305278', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 22, 1),
(101, 1, 'UNIVERSIDAD PARA LA COOPERACIÓN INTERNACIONAL', 'NULL', '18.678611', '-88.395278', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 74, 1),
(102, 1, 'UNIVERSIDAD PARA LA COOPERACIÓN INTERNACIONAL', 'NULL', '18.678611', '-88.395278', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 74, 1),
(103, 1, 'INSTITUTO HUMANISTA DE PSICOTERAPIA GESTALT-IHPG', 'NULL', '21.164167', '-86.8475', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(104, 1, 'INSTITUTO TECNOLÓGICO DE LA CONSTRUCCION', 'AVENIDA INSURGANTE NUMERO 967 ENTRE CALLE LAGUNA OM Y LAGUNA NOH-BEN ', '18.505278', '-88.305278', '8370388', 'NULL', 'capacitacionqroo@prodigy.net.mx', 'NULL', 'NULL', 'PRIVADO', 3, 22, 1),
(105, 0, 'JUSTO SIERRA MENDEZ', 'AVENIDA CINCO ENTRE CALLE 40 Y 38', '18.687126', '-88.386259', '8342280', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 74, 1),
(106, 0, 'RAFAEL RAMÍREZ CASTAÑEDA', 'AVENIDA CONSTITUYENTES ENTRE CALLE 91 Y 87', '19.587333', '-88.038661', '8340124', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 6, 1),
(107, 0, 'CENTRO DE ESTUDIOS TECNOLÓGICOS DEL MAR NUM. 10', 'PROLONGACION BOULEVARD BAHIA ENTRE CALLE JULIETA', '18.516333', '-88.274764', '8310422', 'NULL', 'gamboa66@yahoo.com', 'NULL', 'NULL', 'PÚBLICO', 3, 22, 1),
(108, 0, 'CENTRO DE ESTUDIOS TECNOLÓGICOS DEL MAR NO. 33 EN COZUMEL  QUINTANA ROO', 'AVENIDA LEONIDES GARCIA ENTRE CALLE 37 Y 35 SUR', '20.516667', '-86.941667', 'NULL', 'NULL', 'plangecytm@sep.gob.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 1, 1),
(109, 0, 'CENTRO DE BACHILLERATO TECNOLÓGICO INDUSTRIAL Y DE SERVICIOS NUM. 214', 'AVENIDA HERIBERTO FRIAS ENTRE CALLE CIRICOTE Y CEDRO', '18.526499', '-88.274531', '8322177', 'NULL', 'director@cbtis214.sems.gobmx', 'NULL', 'NULL', 'PÚBLICO', 3, 22, 1),
(110, 0, 'CENTRO DE BACHILLERATO TECNOLÓGICO INDUSTRIAL Y DE SERVICIOS NUM. 28', 'AVENIDA ANTONIO GONZALEZ FERNANDEZ ENTRE AVENIDA 40 AVENIDA NORTE Y AVENIDA LICENCIADO PEDRO JOAQUIN COLDWEL', '20.506285', '-86.942159', '8720039', 'NULL', 'director@cbtis028.sems.gobmx', 'NULL', 'NULL', 'PÚBLICO', 3, 1, 1),
(111, 0, 'CENTRO DE BACHILLERATO TECNOLÓGICO INDUSTRIAL Y DE SERVICIOS NUM. 72', 'AVENIDA SANTIAGO PACHECO CRUZ ENTRE CALLE 60 Y 54', '19.575928', '-88.052032', '8340270', 'NULL', 'director@cbtis072.sems.gobmx', 'NULL', 'NULL', 'PÚBLICO', 3, 6, 1),
(112, 0, 'CENTRO DE BACHILLERATO TECNOLÓGICO INDUSTRIAL Y DE SERVICIOS NUM. 253', 'CALLE SAC-XAN ENTRE AVENIDA TRES GARANTIAS Y RAUDALES', '18.525945', '-88.305982', '8328736', 'NULL', 'director@cbtis253.sems.gobmx', 'NULL', 'NULL', 'PÚBLICO', 3, 22, 1),
(113, 0, 'CENTRO DE BACHILLERATO TECNOLÓGICO INDUSTRIAL Y DE SERVICIOS NO.272', 'ANDADOR GUADALUPE VICTORIA CALLE JOSE MARIA MORELOS', '21.160556', '-86.8475', '38323800', 'NULL', 'cbtis_arconorte@hotmail.com', 'NULL', 'NULL', 'PÚBLICO', 3, 39, 1),
(114, 0, 'CENTRO DE BACHILLERATO TECNOLÓGICO INDUSTRIAL Y DE SERVICIOS NUM. 111', 'AVENIDA CHICHEN ITZA LOTE 2 ENTRE AVENIDA BONAMPAK Y CALLE HE-ZABA', '21.169916', '-86.8229', '8846198', 'NULL', 'director@cbtis111.sems.gobmx', 'NULL', 'NULL', 'PÚBLICO', 3, 39, 1),
(115, 0, 'PLANTEL CONALEP 009. CANCUN', 'AVENIDA JOSE GARCIA DE LA TORRE ENTRE AVENIDA BONAMPAK Y CHICHEN ITZA', '21.16638', '-86.822315', '8844298', 'NULL', 'conalep@q.roo1.telmex.net.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 39, 1),
(116, 0, 'PLANTEL CONALEP 065. COZUMEL', 'AVENIDA PEDRO JOAQUIN COLDWELL ENTRE CALLE 21 Y 23 SUR', '20.496054', '-86.954712', '8724882', 'NULL', 'ph065-9r@yahoo.com.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 1, 1),
(117, 0, 'PLANTEL CONALEP 102. FELIPE CARRILLO PUERTO', 'CALLE 63 ENTRE CALLE 68 Y AVENIDA BENITO JUAREZ', '19.577276', '-88.04458', '8340217', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 6, 1),
(118, 0, 'PLANTEL CONALEP 007. LIC. JESÚS MARTÍNEZ ROSS- CHETUMAL', 'AVENIDA INSURGENTES ENTRE CALLE FLOR DE MAYO Y MANUEL ACUÑA', '18.519069', '-88.325453', '8372663', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 22, 1),
(119, 0, 'PLANTEL CONALEP 286. CANCUN II', 'AVENIDA 20 DE NOVIEMBRE ENTRE AVENIDA LA COSTA Y CALLE 161 NORTE', '21.181026', '-86.854644', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 39, 1),
(120, 0, 'PLANTEL CONALEP 298. CANCUN III', 'AVENIDA 121 NORTE (CHAC MOOL) CALLE PAGALO', '21.160556', '-86.8475', '1293273', 'NULL', 'conalepqroo@prodigy.net.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 39, 1),
(121, 0, 'PLANTEL CONALEP 297. PLAYA DEL CARMEN', 'AVENIDA TECNOLOGICO', '20.6275', '-87.081111', 'NULL', 'NULL', 'conalepq.roo@prodigy.net.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 63, 1),
(122, 0, 'CENTRO DE BACHILLERATO TECNOLÓGICO AGROPECUARIO NUM. 11', 'AVENIDA INSURGENTES', '18.517964', '-88.332544', '8363676', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 22, 1),
(123, 0, 'CENTRO DE BACHILLERATO TECNOLÓGICO AGROPECUARIO NUM. 80', 'CARRETERA CHETUMAL - FELIPE CARRILLO PUERTO KILOMETRO 169', '19.584762', '-88.591152', '8340812', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 8, 1),
(124, 0, 'CENTRO DE BACHILLERATO TECNOLÓGICO AGROPECUARIO NUM. 186', 'CARRETERA MUNICIPAL LIBRE KM 3 TRAMO KANTUNILKIN CHIQUILA', '21.102778', '-87.487778', '8750071', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 53, 1),
(125, 0, 'CHETUMAL UNO', 'CALLE CORNELIO LIZARRAGA ENTRE CALLE NORMAN MCLIBERTY Y INSURGENTES', '18.517964', '-88.332544', '8371523', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 22, 1),
(126, 0, 'PLANTEL CANCUN UNO', 'CALLE 21 ENTRE CALLE 65 Y 57 NORTE', '21.164839', '-86.843573', '8883652', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 39, 1),
(127, 0, 'BACALAR', 'AVENIDA QUINCE ENTRE CALLE 16 Y 18', '18.679349', '-88.397314', '8342440', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 74, 1),
(128, 0, 'JOSÉ MARÍA MORELOS', 'CALLE 31 Y 29', '19.756903', '-88.701794', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 45, 1),
(129, 0, 'RIO HONDO', 'CARRETERA ESTATAL LIBRE TRAMO UCUM LA UNION KILOMETRO 33+500', '18.244722', '-88.661389', '8310329', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 32, 1),
(130, 0, 'ISLA MUJERES', 'CARRETERA MUNICIPAL LIBRE TRAMO PERIMETRAL KILOMETRO 1', '21.241218', '-86.736054', '9825829', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 18, 1),
(131, 0, 'COZUMEL', 'CALLE 27 SUR ENTRE CALLE 15 Y 31 SUR', '20.491589', '-86.955002', '8724778', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 1, 1),
(132, 0, 'CHETUMAL DOS', 'AVENIDA CONSTITUYENTES DEL 74 ENTRE CALLE NORMAN MCLIBERTY Y AVENIDA ERICK PAOLO MARTINEZ', '18.524306', '-88.295527', '8329119', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 22, 1),
(133, 0, 'PLANTEL TIHOSUCO', 'NINGUNO', '20.195494', '-88.366186', '25829', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 7, 1),
(134, 0, 'CARLOS A.MADRAZO', 'NINGUNO', '18.505833', '-88.5225', '8325829', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 33, 1),
(135, 0, 'PLAYA DEL CARMEN', 'CALLE 12 NORTE ENTRE AVENIDA 30 Y 40 NORTE', '20.630751', '-87.076599', '8730251', 'NULL', 'monick12@yahooo.com', 'NULL', 'NULL', 'PÚBLICO', 3, 63, 1),
(136, 0, 'NICOLAS BRAVO', 'NINGUNO', '18.45012', '-88.929862', '8325829', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 24, 1),
(137, 0, 'CANCUN DOS', 'AVENIDA FRANCISCO I. MADERO (RUTA 4) ENTRE CALLE 137 Y 139 NORTE', '21.17101', '-86.836599', '454617', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 39, 1),
(138, 0, 'SERVICIOS ACADEMICOS INTEGRALES', 'CALLE 57 ENTRE CALLE 65 Y 57 NORTE', '21.160556', '-86.8475', '8885242', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 39, 1),
(139, 0, 'SERVICIOS ACADEMICOS INTEGRALES', 'AVENIDA INSURGENTES ENTRE AVENIDA CONSTITUYENTES DEL 74 Y CALLE COROZAL', '18.503611', '-88.305278', '8327268', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 22, 1),
(140, 0, 'IGNACIO ZARAGOZA', 'NINGUNO', '20.875', '-87.525', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 54, 1),
(141, 0, 'SABAN', 'NINGUNO', '20.038611', '-88.538611', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 47, 1),
(142, 0, 'SERVICIOS ACADEMICOS INTEGRALES', 'CALLE 16 NORTE ENTRE CALLE 35 Y 40 NORTE', '20.630751', '-87.076599', '8330251', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 63, 1),
(143, 0, 'COLEGIO DE BACHILLERES PLANTEL CANCUN TRES BONFIL', 'CALLE SALINAS DE GORTARI ENTRE CALLE JAVIER ROJO GOMEZ Y LEONA VICARIO', '21.164506', '-86.844153', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 40, 1),
(144, 0, 'SEÑOR', 'CONTINUACION CARRETERA FEDERAL LIBRE TRAMO FELIPE CARRILLO PUERTO', '19.843056', '-88.134444', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 9, 1),
(145, 0, 'PLANTEL CANCUN CUATRO', 'AVENIDA CHETUMAL SUPERMANZANA 260 MANZANA 9 ENTRE CALLE KANLOL Y CALLE KANLOL', '21.160556', '-86.8475', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 39, 1),
(146, 0, 'PLANTEL CIUDAD MUJERES', 'NINGUNO', '21.263184', '-86.913184', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 86, 1),
(147, 0, 'PLANTEL PRESIDENTE JUÁREZ', 'NINGUNO', '19.328333', '-88.561667', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 17, 1),
(148, 0, 'PLANTEL MAYA BALAM', 'NINGUNO', '18.949722', '-88.399722', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 76, 1),
(149, 0, 'PLANTEL CANDELARIA', 'NINGUNO', '19.741944', '-88.958611', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 82, 1),
(150, 0, 'PLANTEL PUERTO MORELOS', 'CALLE CAOBAS Y CALLE 50', '20.853611', '-86.898611', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 41, 1),
(151, 0, 'EVA SAMANO DE LÓPEZ MATEOS', 'CALLE CELUL ENTRE CALLE 30 DE NOVIEMBRE Y CHICOZAPOTE', '18.503611', '-88.305278', '1183450', 'NULL', 'contacto@evasamano.edu.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 22, 1),
(152, 0, 'EMSAD X-PICHIL', 'NINGUNO', '19.694722', '-88.378056', '8340493', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 13, 1),
(153, 0, 'NOH-BEC', 'NINGUNO', '19.135833', '-88.169167', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 11, 1),
(154, 0, 'ZAMORA', 'NINGUNO', '19.075833', '-88.7925', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 93, 1),
(155, 0, 'SAN PEDRO PERALTA', 'AVENIDA INDEPENDENCIA ENTRE CALLE LAZARO CARDENAS Y BENITO JUAREZ', '18.706667', '-88.856667', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 90, 1),
(156, 0, 'VALLEHERMOSO', 'NINGUNO', '19.191111', '-88.524444', '8323540', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 92, 1),
(157, 0, 'X-HAZIL SUR', 'CALLE FRANCISCO VILLA ENTRE CALLE JOSE CRUZ Y MARTIN AKE', '19.390833', '-88.074167', '8340153', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 13, 1),
(158, 0, 'CAOBAS', 'NINGUNO', '18.4375', '-89.104167', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 82, 1),
(159, 0, 'DIVORCIADOS', 'AVENIDA JAVIER ROJO GOMEZ Y CALLE VICENTE FOX QUEZADA', '19.073056', '-88.456389', '8323330', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 83, 1),
(160, 0, 'CANDELARIA', 'NINGUNO', '19.741944', '-88.958611', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 81, 1),
(161, 0, 'COBA', 'NINGUNO', '20.486111', '-87.736111', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 68, 1),
(162, 0, 'CHANCHEN I', 'NINGUNO', '20.401111', '-87.967778', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 69, 1),
(163, 0, 'PRESIDENTE JUÁREZ', 'PRESIDENTE JUAREZ', '19.328333', '-88.561667', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 17, 1),
(164, 0, 'BLANCA FLOR', 'NINGUNO', '18.9325', '-88.499167', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 80, 1),
(165, 0, 'LIMONES', 'NINGUNO', '19.024167', '-88.108333', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 75, 1),
(166, 0, 'ALTOS DE SEVILLA', 'ENTRE CALLE LUCIO CEN Y CALLE DAVID GUSTAVO GUTIERREZ RUIZ ', '18.860556', '-88.677222', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 79, 1),
(167, 0, 'CHIQUILA', 'NINGUNO', '21.421389', '-87.338056', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 56, 1),
(168, 0, 'RIO VERDE', 'CALLE MIGUEL HIDALGO', '19.038333', '-89.038333', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 88, 1),
(169, 0, 'EMSAD MAHAHUAL', 'ANDADOR MAHAHUAL ENTRE CALLE LIZA Y MOJARRA', '18.709444', '-87.709444', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 37, 1),
(170, 0, 'EMSAD MIGUEL ALEMAN', 'CALLE MIGUEL ALEMAN Y CALLE TENIENTE J AZUETA', '18.91', '-89.093333', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 85, 1),
(171, 0, 'EMSAD CALDERITAS', 'CALLE GUERRERO ENTRE CALLE TAMPICO Y SONORA', '18.554444', '-88.258333', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 23, 1),
(172, 0, 'COLEGIO DE ESTUDIOS CIENTIFICOS Y TECNOLÓGICOS UNIDAD CANCUN I', 'CALLE 71 ENTRE CALLE 72 PONIENTE Y AVENIDA LEONA VICARIO', '21.176328', '-86.852812', '8926646', 'NULL', 'cecyteqrcun@prodigy.net.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 39, 1),
(173, 0, 'COLEGIO DE ESTUDIOS CIENTIFICOS Y TECNOLÓGICOS UNIDAD CHETUMAL', 'CALLE TECNOLOGICO DE TUXTLA GUTIERREZ ENTRE CALLE TECNOLOGICO DE ORIZABA Y CALLE TECNOLOGICO DE CAMPECHE', '18.520819', '-88.310773', '8320384', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 22, 1),
(174, 0, 'COLEGIO DE ESTUDIOS CIENTIFICOS Y TECNOLÓGICOS UNIDAD TULUM', 'CALLE 4 ORIENTE ENTRE CALLE JUPITER NORTE Y ALFA NORTE', '20.213251', '-87.465345', '8712198', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 65, 1),
(175, 0, 'COLEGIO DE ESTUDIOS CIENTIFICOS Y TECNOLÓGICOS UNIDAD CANCUN II', 'AVENIDA CANCUN MANZANA 1 LOTE 1 ENTRE CALLE MARIO VILLANUEVA MADRID Y AVENIDA 135', '21.13091', '-86.877076', '8503797', 'NULL', 'cecyteplantelcancun2@yahoo.com.mx', 'NULL', 'NULL', 'PÚBLICO', 3, 39, 1),
(176, 0, 'COLEGIO DE ESTUDIOS CIENTIFICOS Y TECNOLÓGICOS UNIDAD PLAYA DEL CARMEN', 'AVENIDA CONSTITUYENTES ENTRE AVENIDA 105 Y 110', '20.642333', '-87.088531', '8731027', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 63, 1),
(177, 0, 'COLEGIO DE ESTUDIOS CIENTIFICOS Y TECNOLÓGICOS UNIDAD LEONA VICARIO', 'CARRETERA FEDERAL LIBRE 180 TRAMO MERIDA CANCUN KILOMETRO 278+0', '20.98116', '-87.222273', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 42, 1),
(178, 0, 'COLEGIO DE ESTUDIOS CIENTIFICOS Y TECNOLÓGICOS UNIDAD CANCUN III', 'AVENIDA 20 DE NOVIEMBRE MANZANA 1 LOTE 187 Y AVENIDA CENTENARIO', '21.16692', '-86.887871', '1517444', 'NULL', 'moregomez2001@hotmail.com', 'NULL', 'NULL', 'PÚBLICO', 3, 39, 1),
(179, 0, 'CENTRO DE ESTUDIOS CIENTIFICOS Y TECNOLÓGICOS UNIDAD CANCUN IV', 'CALLE 110 ENTRE CALLE 113 Y 143 NORTE', '21.191227', '-86.827826', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PÚBLICO', 3, 39, 1),
(180, 0, 'TELEBACHILLERATO COMUNITARIO', 'NINGUNO', '18.651667', '-88.218333', 'NULL', 'NULL', 'montserrattapia@hotmail.com', 'NULL', 'NULL', 'PÚBLICO', 3, 84, 1),
(181, 0, 'TELEBACHILLERATO COMUNITARIO', 'CALLE CHAPULTEPEC ENTRE CALLE MERIDA Y CEDROS', '18.805278', '-88.571944', 'NULL', 'NULL', 'ya_paola@hotmail.com', 'NULL', 'NULL', 'PÚBLICO', 3, 87, 1),
(182, 0, 'TELEBACHILLERATO COMUNITARIO', 'NINGUNO', '19.805556', '-88.772222', '1248804', 'NULL', 'gladys.puc@gmail.com', 'NULL', 'NULL', 'PÚBLICO', 3, 91, 1),
(183, 0, 'TELEBACHILLERATO COMUNITARIO', 'CALLE ACNUR ENTRE CALLE ONCE DE OCTUBRE Y CALLE JOAQUIN HENDRICKS DIAZ', '18.803889', '-88.370556', '1365856', 'NULL', 'teba_sill@gmail.com', 'NULL', 'NULL', 'PÚBLICO', 3, 89, 1),
(184, 0, 'ESCUELA DE MUSICA CROC EN MOVIMIENTO', 'CALLE PUNTA NICCHEHABI ENTRE CALLE PUNTA NICCHEHABI Y AVENIDA YAXCHILAN', '21.150036', '-86.831647', '8843361', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(185, 0, 'COLEGIO KUKULCAN CANCUN  S.C.', 'PUNTA NOH-KU MANZANA 3 LOTE 2 Y 3 ENTRE CALLE CABO CATOCHE', '21.157627', '-86.846095', '8841435', 'NULL', 'colegiokukulcan@hotmail.com', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(186, 0, 'COLEGIO ECAB', 'AVENIDA KABAH REGION 93 ENTRE AVENIDA JOSE LOPEZ PORTILLO Y AVENIDA FRANCISCO I MADERO', '21.161805', '-86.853933', '887980', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(187, 0, 'IGNACIO LÓPEZ RAYON', 'AVENIDA 16 DE SEPTIEMBRE ENTRE AVENIDA IGNACIO ZARAGOZA Y AVENIDA ALVARO OBREGON', '18.497115', '-88.294573', '8331410', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 22, 1),
(188, 0, 'INSTITUTO PARTENON DE COZUMEL  A.C.', 'AVENIDA ANDRES QUINTANA ROO ENTRE AVENIDA 95 SUR Y AVENIDA 90 SUR BIS', '20.491146', '-86.939836', '8693874', 'NULL', 'ipartyenon@hotmail.com', 'NULL', 'NULL', 'PRIVADO', 3, 1, 1),
(189, 0, 'CENTRO EDUCATIVO ITZAMNA', 'CALLE PUNTA CHUNYUNYUN  RETORNO 1-A  LOTE CENTRAL ENTRE CALLE PUNTA YOQUEN Y PUNTA CHACCHIC', '21.164765', '-86.832937', '8840019', 'NULL', 'itzamna@cancun.rce.com.mx', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(190, 0, 'CAMPO ESCUELA SAN JOSÉ', 'CARRETERA FEDERAL LIBRE 307 TRAMO MERIDA PUERTO JUAREZ KILOMETRO', '21.136539', '-86.904077', '8986681', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(191, 0, 'COLEGIO IGNACIO COMONFORT', 'AVENIDA BONAMPAK MANZANA 14 LOTES 17-19 ENTRE CALLE 28 Y 26 ORIENTE', '21.181174', '-86.819422', '8804971', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(192, 0, 'INSTITUTO EDUCATIVO YITSATIL', 'CALLE 24 NORTE MANZANA 46 ENTRE AVENIDA 20 NORTE Y CALLE 25 NORTE', '20.633335', '-87.071399', '8733874', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 63, 1),
(193, 0, 'INSTITUTO AMERICANO LEONARDO DA VINCI', 'SUPERMANZANA 10 Y AVENIDA 16 DE ABRIL', '21.131436', '-86.82606', '88487100', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(194, 0, 'COLEGIO ST. JOHNS', 'AVENIDA 16 DE ABRIL LOTE 4 ENTRE AVENIDA BONAMPAK Y LUIS DONALDO COLOSIO', '21.129921', '-86.826792', '8482088', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(195, 0, 'COLEGIO INTERNACIONAL AMERICANO DE CANCUN  A.C.', 'AVENIDA DE LOS COLEGIOS Y CALLE BARDA', '21.088417', '-86.845275', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 40, 1),
(196, 0, 'ICARO', 'AVENIDA NICHUPTE MANZANA 13 LOTE 1 ENTRE AVENIDA MEXICO Y 121 NORTE', '21.138466', '-86.863584', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(197, 0, 'PREPARATORIA LA SALLE PLAYA DEL CARMEN', 'AVENIDA TECNOLOGICO Y AVENIDA UNIVERSIDADES', '20.630662', '-87.076055', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 63, 1),
(198, 0, 'BOSTON', 'AVENIDA CHICHEN ITZA MANZANA 91 LOTE 1-02', '21.14262', '-86.872985', '8435724', 'NULL', 'cebostona@prodigy.net.mx', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(199, 0, 'COLEGIO INGLES', 'CARRETERA FEDERAL LIBRE CANCUN PLAYA DEL', '20.634036', '-87.073109', '8793642', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 63, 1),
(200, 0, 'COLEGIO UNIVERSITARIO ANGLO MEXICANO  S.C. (CUAM)', 'AVENIDA BONAMPAK SUPERMANZANA 10 B ENTRE AVENIDA PUNTA CELARIN Y CONTOY ORIENTE', '21.133663', '-86.825156', '8899292', 'NULL', 'cuamcun@hotmail.com', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(201, 0, 'COLEGIO PUERTO AVENTURAS', 'CARRETERA CHETUMAL-PUERTO JUAREZ KILOMETRO 269+500', '20.6275', '-87.081111', '8735141', 'NULL', 'cpa@pa.com.mx', 'NULL', 'NULL', 'PRIVADO', 3, 64, 1),
(202, 0, 'CENTRO EDUCATIVO MERIDA', 'AVENIDA DE LOS COLEGIOS ENTRE CALLE LUIS DONALDO COLOSIO Y BOULEVARD LUIS DONALDO COLOSIO ', '21.15544', '-86.8378', '8822331', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(203, 0, 'COLEGIO LIZARDI', 'AVENIDA MAYAPAN MANZANA 4 LOTE 7-A ENTRE AVENIDA MAYAPAN Y KABAH', '21.151926', '-86.835706', 'NULL', 'NULL', 'colegiolizardi94@hotmail.com', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(204, 0, 'BACHILLERATO UNID', 'AVENIDA NICHUPTE MANZANA 72 LOTES 5 Y 2 ENTRE AVENIDA HUAYACAN Y KABAH', '21.138184', '-86.833453', '8483120', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(205, 0, 'COLEGIO DEL CARIBE', 'AVENIDA FRANCISCO I. MADERO MANZANA 14 LOTE 2 ENTRE CALLE 33 Y 55 NORTE', '21.163217', '-86.855179', '8403030', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(206, 0, 'BENITO JUÁREZ GARCIA', 'AVENIDA 40 MANZANA 23 LOTE 2 CALLE 1 SUR Y CALLE 45 NORTE', '20.625831', '-87.081782', '8731000', 'NULL', 'alega-31@hotmail.com', 'NULL', 'NULL', 'PRIVADO', 3, 63, 1),
(207, 0, 'PREPARATORIA CANCUN', 'AVENIDA JOSE LOPEZ PORTILLO SUPERMANZANA 59 ENTRE CALLE BACAL Y CALLE YAXCOPOIL', '21.153966', '-86.860491', '8865766', 'NULL', 'miislupita-1@hotmail.com', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(208, 0, 'INSTITUTO CESARE', 'BOULEVARD LUIS DONALDO COLOSIO MANZANA 03 LOTE 01', '21.160556', '-86.8475', '2677303', 'NULL', 'gleaso@institutocesare.com.mx', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(209, 0, 'COLEGIO MAYALAND', 'NINGUNO', '20.625046', '-87.079101', '2063860', 'NULL', 'informes@colegiomayaland.com.mx', 'NULL', 'NULL', 'PRIVADO', 3, 63, 1),
(210, 0, 'ESCUELA DEL SINDICALISMO NUEVO', 'CALLE 12 PONIENTE MANZANA 46 LOTE 8 PLANTA BAJA ENTRE AVENIDA UXMAL Y CALLE 12', '21.166928', '-86.836101', '8840328', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(211, 0, 'ESCUELA PREPARATORIA ANDRES QUINTANA ROO', 'AVENIDA CHAC MOOL MANZANA 1 LOTE 2 SUPERMANZANA 218 ENTRE AVENIDA PUERTO JUAREZ Y LEONA VICARIO', '21.160765', '-86.872281', '1327693', 'NULL', 'ensaqroo@hotmail.com', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(212, 0, 'INSTITUTO TEPEYAC CAMPUS XCARET', 'CARRETERA FEDERAL 307 KILOMETRO 282 SUPERMANZANA 00 MANZANA 20', '20.6275', '-87.081111', '8733785', 'NULL', 'cap.itxcaret@itc.edu.mx', 'NULL', 'NULL', 'PRIVADO', 3, 63, 1),
(213, 0, 'PREPARATORIA RIVIERA', 'AVENIDA 58 NORTE LOTE 4-1', '20.6275', '-87.081111', '1093634', 'NULL', 'sergio.dorantes@universidadriviera.org.mx', 'NULL', 'NULL', 'PRIVADO', 3, 63, 1),
(214, 0, 'BACHILLERATO NACIONES UNIDAS', 'AVENIDA 25 NORTE MANZANA 50 LOTE 10 ENTRE CALLE 32 Y 30 NORTE', '20.6275', '-87.081111', '8793052', 'NULL', 'elpapalote@prodigy.net.mx', 'NULL', 'NULL', 'PRIVADO', 3, 63, 1),
(215, 0, 'COLEGIO ALAMOS', 'PASEO DEL CASTAÑO SUPERMANZANA 313', '21.160556', '-86.8475', '2679274', 'NULL', 'lalocruzsalazar@yahoo.com', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(216, 0, 'COLEGIO REAL DEL CARIBE', 'CALLE MARTE MANZANA 01 LOTE 01 ENTRE AVENIDA FONATUR Y CALLE MAPLE', '21.160556', '-86.8475', '2710572', 'NULL', 'colegiorealdelcaribe@hotmail.com', 'NULL', 'NULL', 'PRIVADO', 3, 40, 1),
(217, 0, 'COLEGIO MANO AMIGA CANCUN', 'CARRETERA FEDERAL CUOTA 307 CANCUN VALLADOLID KILOMETRO 308+0', '21.160556', '-86.8475', '8987333', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 40, 1),
(218, 0, 'CENTRO EDUCATIVO MAHATMA GANDHI', 'CALLE HERMANOS CAMPOS AGUEROS ENTRE CALLE MAPLE Y AVENIDA FONATUR', '21.144028', '-86.850264', '2678888', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(219, 0, 'BACHILLERATO SUMMERHILL', 'CALLE 75 LOTE 6 AL 13 MANZANA 14 ENTRE AVENIDA LOPEZ PORTILLO Y CALLE 10', '21.160556', '-86.8475', '8870404', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(220, 0, 'COLEGIO MIRAMAR', 'CALLE FRESNO MANZANA 100 LOTE 2 ENTRE AVENIDA LIMON VERDE Y HUAYACAN', '21.160556', '-86.8475', '9821500', 'NULL', 'aamador@iccum.com.mx', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(221, 0, 'KOOX KANIK XOOK TULUM', 'CALLE ESCORPION SUR MANZANA 17 LOTE 01 ENTRE CALLE ANDROMEDA ORIENTE Y CALLE SOL ORIENTE', '20.21196', '-87.465709', '8712031', 'NULL', 'kooxkanicxooctulum@gmail.com', 'NULL', 'NULL', 'PRIVADO', 3, 65, 1),
(222, 0, 'COLEGIO LATINO', 'CALLE VICENTE GUERRERO LOTE 08 ENTRE CALLE ANDROMEDA ORIENTE Y AVENIDA HEROES DE CHAPULTEPEC', '18.503611', '-88.305278', 'NULL', 'NULL', 'latino-sesamo@hotmail.com', 'NULL', 'NULL', 'PRIVADO', 3, 22, 1),
(223, 0, 'CENTRO EDUCATIVO INTERCULTURAL DEL CARIBE', 'CALLE 28 NORTE LOTE 5 MANZANA 130 ENTRE CARRETERA FEDERAL Y CALLE 50 PONIENTE', '20.6275', '-87.081111', '8733650', 'NULL', 'claujulian@gmail.com', 'NULL', 'NULL', 'PRIVADO', 3, 63, 1),
(224, 0, 'INSTITUTO XEL-BAALAM', 'AVENIDA FRANCISCO I MADERO SUPERMANZANA 65 MANZANA 6 LOTE 15 ENTRE CALLE 7 Y 3 ', '21.160556', '-86.8475', '8839647', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(225, 0, 'INSTITUTO VITTORIO MONTEVERDI', 'AVENIDA 28 DE JULIO Y AVENIDA CHEMUYIL', '20.6275', '-87.081111', '8036257', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 63, 1),
(226, 0, 'PREPARATORIA MONTEVERDE', 'AVENIDA BONAMPAK MANZANA 2 LOTE 5 ENTRE AVENIDA BANCO CHINCHORRO Y AVENIDA CABO CATOCHE', '21.160556', '-86.8475', '8819800', 'NULL', 'monteverde@prodigy.net.mx', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(227, 0, 'COLEGIO COZUMEL', 'CALLE 14 NORTE ENTRE AVENIDA 45 NORTE Y AVENIDA 50 NORTE', '20.510339', '-86.937035', '8721075', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 1, 1),
(228, 0, 'INSTITUTO CUMBRES DE QUINTANA ROO', 'CALLE JUAN RUIZ DE ALARCON ENTRE CALLE MANUEL ACUÑA Y TREBOLES', '18.509196', '-88.318831', '8370552', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 22, 1),
(229, 0, 'UNIVERSIDAD LA SALLE CANCUN', 'CARRETERA CHETUMAL-CANCUN MANZANA 1 LOTE 1 KILOMETRO 344', '21.160556', '-86.8475', '8862201', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(230, 0, 'INSTITUTO DEL CARIBE', 'BOULEVARD CUMBRES CANCUN AEROPUERTO KILOMETRO 75+0 ENTRE AVENIDA HUAYACAN Y CALLE MONTE VISION', '21.160556', '-86.8475', '2035052', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(231, 0, 'UNIVERSIDAD TEC MILENIO CANCUN', 'AVENIDA BONAMPAK ENTRE AVENIDA BANCO CHINCHORRO Y AVENIDA CABO CATOCHE', '21.160556', '-86.8475', '8814700', 'NULL', 'locarril@tecmilenio.edu.mx', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(232, 0, 'PREPARATORIA AZTECA DEL CARIBE', 'AVENIDA CHICHEN ITZA MANZANA 12 LOTE 20-01', '21.154933', '-86.852704', '8436209', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(233, 0, 'BACHILLERATO UVG CAMPUS CANCUN', 'AVENIDA DE LAS TORRES ENTRE AVENIDA DEL BOSQUE Y AVENIDA CANCUN', '21.160556', '-86.8475', '9149094', 'NULL', 'rasantaella@universidadaliat.com', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(234, 0, 'COLEGIO BRITANICO DEL CARIBE  S.C.', 'CALLE PARGO ENTRE CALLE HUACHINANGO Y AVENIDA BONAMPAK', '21.158561', '-86.822051', '8841295', 'NULL', 'british@cancun.com.mx', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(235, 0, 'INSTITUTO LATINOAMERICANO DE TURISMO  S.C.', 'AVENIDA TORRES SUR', '21.132391', '-86.846025', '8809975', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(236, 0, 'INSTITUTO LATINOAMERICANO DE TURISMO', 'AVENIDA CANCUN Y AVENIDA DEL BOSQUE', '21.131805', '-86.844779', '8809975', 'NULL', 'ilat@buzon.as', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(237, 0, 'JOSÉ MARTI', 'AVENIDA ANDRES QUINTANA ROO ENTRE CALLE NACHI COCOM Y AVENIDA TEPICH', '21.171493', '-86.826774', '8841278', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(238, 0, 'ESCUELA DE PUERICULTURA DEL SURESTE CAMPUS CANCUN', 'AVENIDA JOSE LOPEZ PORTILLO ENTRE CALLE 57 Y 63 NORTE', '21.164602', '-86.834933', '8371359', 'NULL', 'puericultura-cancun@hotmail.com', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(239, 0, 'JAIME TORRES BODET', 'AVENIDA CHICHEN ITZA ENTRE CALLE NACHI COCOM Y CALLE TIMUL', '21.154904', '-86.853502', '82068065', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1),
(240, 0, 'ESCUELA DE PUERICULTURA DEL SURESTE MANUEL ACEVEDO RUIZ DEL HOYO', 'AVENIDA OTHON P BLANCO ENTRE AVENIDA 5 DE MAYO Y AVENIDA 16 DE SEPTIEMBRE', '18.496348', '-88.294998', '2856093', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 22, 1),
(241, 0, 'INSTITUTO PARTENON PREPARATORIA', 'AVENIDA ANDRES QUINTANA ROO ENTRE AVENIDA VENUSTIANO CARRANZA Y AVENIDA SAN SALVADOR', '18.508681', '-88.304956', '1292897', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 22, 1),
(242, 0, 'CENTRO UNIVERSITARIO PENINSULAR', 'AVENIDA UXMAL SUPERMANZANA 24 MANZANA 15 LOTE 10 ENTRE CALLE PUNTA ALLEN Y CALLE PUNTA PULTICUB', '21.160556', '-86.8475', '8923299', 'NULL', 'NULL', 'NULL', 'NULL', 'PRIVADO', 3, 39, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `localidad`
--

CREATE TABLE IF NOT EXISTS `localidad` (
  `id` int(11) NOT NULL,
  `localidad` varchar(150) NOT NULL,
  `idMunicipio` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idmunicipio_idx` (`idMunicipio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `localidad`
--

INSERT INTO `localidad` (`id`, `localidad`, `idMunicipio`) VALUES
(1, 'San Miguel de Cozumel', 1),
(2, 'Las Fincas', 1),
(3, 'Kilómetro Cuatro y Medio', 1),
(4, 'La Estrella', 1),
(5, 'El Cedral', 1),
(6, 'Felipe Carrillo Puerto', 2),
(7, 'Tihosuco', 2),
(8, 'Chunhuhub', 2),
(9, 'Señor', 2),
(10, 'Tepich', 2),
(11, 'Noh-Bec', 2),
(12, 'X-Hazil Sur', 2),
(13, 'X-Pichil', 2),
(14, 'Polyuc', 2),
(15, 'Dzulá', 2),
(16, 'Santa Rosa Segundo', 2),
(17, 'Presidente Juárez', 2),
(18, 'Isla Mujeres', 3),
(19, 'Zona Urbana Ejido Isla Mujeres', 3),
(20, 'Francisco May', 3),
(21, 'Punta Sam', 3),
(22, 'Chetumal', 4),
(23, 'Calderitas', 4),
(24, 'Nicolás Bravo', 4),
(25, 'Javier Rojo Gómez', 4),
(26, 'Álvaro Obregón', 4),
(27, 'Sergio Butrón Casas', 4),
(28, 'Cacao', 4),
(29, 'Xul-Ha', 4),
(30, 'Maya Balam', 4),
(31, 'Subteniente López', 4),
(32, 'Pucté', 4),
(33, 'Carlos A. Madrazo', 4),
(34, 'Huay-Pix', 4),
(35, 'Ucum', 4),
(36, 'La Unión', 4),
(37, 'Mahahual', 4),
(38, 'Xcalak', 4),
(39, 'Cancún', 5),
(40, 'Alfredo V. Bonfil', 5),
(41, 'Puerto Morelos', 5),
(42, 'Leona Vicario', 5),
(43, 'El Porvenir', 5),
(44, 'Central Vallarta', 5),
(45, 'José María Morelos', 6),
(46, 'Dziuché', 6),
(47, 'Sabán', 6),
(48, 'Huay Max', 6),
(49, 'La Presumida', 6),
(50, 'X-Cabil', 6),
(51, 'Kancabchén', 6),
(52, 'Sacalaca', 6),
(53, 'Kantunilkín', 7),
(54, 'Ignacio Zaragoza', 7),
(55, 'Holbox', 7),
(56, 'Chiquilá', 7),
(57, 'Nuevo Valladolid', 7),
(58, 'Nuevo Xcán', 7),
(59, 'El Tintal', 7),
(60, 'Solferino', 7),
(61, 'San Angel', 7),
(62, 'Pacchen', 7),
(63, 'Playa del Carmen', 8),
(64, 'Puerto Aventuras', 8),
(65, 'Tulum', 9),
(66, 'Ciudad Chemuyil', 9),
(67, 'Akumal', 9),
(68, 'Cobá', 9),
(69, 'Chanchen Primero', 9),
(70, 'Francisco Uh May', 9),
(71, 'San Juan', 9),
(72, 'Macario Gómez', 9),
(73, 'Punta Allen', 9),
(74, 'Bacalar', 10),
(75, 'Limones', 10),
(76, 'Maya Balam', 10),
(77, 'Pedro Antonio Santos', 10),
(78, 'Juan Sarabia', 4),
(79, 'Altos de Sevilla', 10),
(80, 'Blanca Flor', 10),
(81, 'Candelaria', 6),
(82, 'Caobas', 4),
(83, 'Los divorciados', 10),
(84, 'Luis Echeverría Álvares', 4),
(85, 'Miguel Alemán', 10),
(86, 'Rancho Viejo', 3),
(87, 'Reforma', 10),
(88, 'Río Verde', 10),
(89, 'San Isidro La Laguna', 10),
(90, 'San Pedro Peralta', 4),
(91, 'Santa Gertrudis', 6),
(92, 'Vallehermoso', 10),
(93, 'Zamora', 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `municipio`
--

CREATE TABLE IF NOT EXISTS `municipio` (
  `id` int(11) NOT NULL,
  `municipio` varchar(100) NOT NULL,
  `cabecera` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `municipio`
--

INSERT INTO `municipio` (`id`, `municipio`, `cabecera`) VALUES
(1, 'Cozumel', 'Cozumel'),
(2, 'Felipe Carrillo Puerto', 'Felipe Carillo Puerto'),
(3, 'Isla Mujeres', 'Isla Mujeres'),
(4, 'Othón P. Blanco', 'Chetumal'),
(5, 'Benito Juárez', 'Cancún'),
(6, 'José María Morelos', 'José María Morelos'),
(7, 'Lázaro Cárdenas', 'Kantunilkín'),
(8, 'Solidaridad', 'Playa del Carmen'),
(9, 'Tulum', 'Tulum'),
(10, 'Bacalar', 'Bacalar');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `relacion_escuela`
--

CREATE TABLE IF NOT EXISTS `relacion_escuela` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idEscuela` int(11) NOT NULL,
  `idCarrera` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idEscuela_idx` (`idEscuela`) USING BTREE,
  KEY `idCarrera2_idx` (`idCarrera`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `relacion_escuela`
--

INSERT INTO `relacion_escuela` (`id`, `idEscuela`, `idCarrera`) VALUES
(1, 9, 22);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `resultado_sugerencia`
--

CREATE TABLE IF NOT EXISTS `resultado_sugerencia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `curp` varchar(18) NOT NULL,
  `idCarrera` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `curpusuario_idx` (`curp`),
  KEY `idCarrera_idx` (`idCarrera`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo`
--

CREATE TABLE IF NOT EXISTS `tipo` (
  `tipo` varchar(1) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipo`
--

INSERT INTO `tipo` (`tipo`, `nombre`) VALUES
('A', 'Artísticas'),
('C', 'Administrativas y Contables'),
('D', 'Defensa y Seguridad'),
('E', 'Ciencias Exactas y Agrarias'),
('H', 'Humanísticas y Sociales'),
('I', 'Ingeniería y Computación'),
('S', 'Medicina y Cs. de la Salud');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `curp` varchar(18) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  PRIMARY KEY (`curp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `carrera`
--
ALTER TABLE `carrera`
  ADD CONSTRAINT `tipo` FOREIGN KEY (`tipoCarrera`) REFERENCES `tipo` (`tipo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `encuesta`
--
ALTER TABLE `encuesta`
  ADD CONSTRAINT `tipoCarrera` FOREIGN KEY (`tipoCarrera`) REFERENCES `tipo` (`tipo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `escuela`
--
ALTER TABLE `escuela`
  ADD CONSTRAINT `idlocalidad` FOREIGN KEY (`idLocalidad`) REFERENCES `localidad` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `modificacion` FOREIGN KEY (`modificacion`) REFERENCES `administrador` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `localidad`
--
ALTER TABLE `localidad`
  ADD CONSTRAINT `idmunicipio` FOREIGN KEY (`idMunicipio`) REFERENCES `municipio` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `relacion_escuela`
--
ALTER TABLE `relacion_escuela`
  ADD CONSTRAINT `idCarrera2` FOREIGN KEY (`idCarrera`) REFERENCES `carrera` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `idEscuela` FOREIGN KEY (`idEscuela`) REFERENCES `escuela` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `resultado_sugerencia`
--
ALTER TABLE `resultado_sugerencia`
  ADD CONSTRAINT `curpusuario` FOREIGN KEY (`curp`) REFERENCES `usuario` (`curp`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `idCarrera` FOREIGN KEY (`idCarrera`) REFERENCES `carrera` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
