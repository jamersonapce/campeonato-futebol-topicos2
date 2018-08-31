-- MySQL Workbench Forward Engineering

-- -----------------------------------------------------
-- Table campeonato
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS campeonato (
  id INT(11) NOT NULL,
  nome VARCHAR(50) NOT NULL,
  ano INT(11) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table estado
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS estado (
  id INT(11) NOT NULL AUTO_INCREMENT,
  nome VARCHAR(50) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table jogador
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS jogador (
  id INT(11) NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  nascimento DATE NOT NULL,
  genero VARCHAR(50) NULL,
  altura DECIMAL NULL,
  fk_time INT(11) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table time
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS time (
  id INT(11) NOT NULL AUTO_INCREMENT,
  nome VARCHAR(50) NOT NULL,
  fk_estado INT(11) NULL,
  capitao INT(11) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX nome_UNIQUE (nome ASC),
  INDEX fk_time_estado_idx (fk_estado ASC),
  UNIQUE INDEX capitao_UNIQUE (capitao ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table partida
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS partida (
  id INT(11) NOT NULL AUTO_INCREMENT,
  data_partida DATE NOT NULL,
  pontuacao_mandante INT(11) NULL DEFAULT 0,
  pontuacao_visitante INT(11) NULL DEFAULT 0,
  fk_campeonato INT(11) NOT NULL,
  fk_time_visitante INT(11) NOT NULL,
  fk_time_mandante INT(11) NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_partida_campeonato_idx (fk_campeonato ASC),
  INDEX fk_partida_time_visitante_idx (fk_time_visitante ASC),
  INDEX fk_partida_time_mandante_idx (fk_time_mandante ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table campeonatos_times
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS campeonatos_times (
  campeonato_id INT(11) NOT NULL,
  time_id INT(11) NOT NULL,
  PRIMARY KEY (campeonato_id, time_id),
  INDEX fk_campeonatos_times_2_idx (time_id ASC))
ENGINE = InnoDB;