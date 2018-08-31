-- -----------------------------------------------------
-- Table jogador
-- -----------------------------------------------------
ALTER TABLE jogador ADD CONSTRAINT fk_jogador_time FOREIGN KEY ( fk_time ) REFERENCES time ( id ) ON DELETE NO ACTION ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table time
-- -----------------------------------------------------
ALTER TABLE time ADD CONSTRAINT fk_time_estado FOREIGN KEY ( fk_estado ) REFERENCES estado ( id ) ON DELETE NO ACTION ON UPDATE CASCADE,
ADD CONSTRAINT fk_time_capitao FOREIGN KEY ( capitao ) REFERENCES jogador ( id ) ON DELETE NO ACTION ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table partida
-- -----------------------------------------------------
ALTER TABLE partida ADD CONSTRAINT fk_partida_campeonato FOREIGN KEY ( fk_campeonato ) REFERENCES campeonato ( id ) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT fk_partida_time_visitante FOREIGN KEY ( fk_time_visitante ) REFERENCES time ( id ) ON DELETE NO ACTION ON UPDATE CASCADE,
ADD CONSTRAINT fk_partida_time_mandante FOREIGN KEY ( fk_time_mandante ) REFERENCES time ( id ) ON DELETE NO ACTION ON UPDATE CASCADE;

-- -----------------------------------------------------
-- Table campeonatos_times
-- -----------------------------------------------------
ALTER TABLE campeonatos_times ADD CONSTRAINT fk_campeonatos_times_1 FOREIGN KEY ( campeonato_id ) REFERENCES campeonato ( id ) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT fk_campeonatos_times_2 FOREIGN KEY ( time_id ) REFERENCES time ( id ) ON DELETE NO ACTION ON UPDATE NO ACTION;
