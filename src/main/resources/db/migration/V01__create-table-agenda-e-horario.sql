CREATE TABLE agenda (
    idAgenda UUID,
    dia DATE NOT NULL,
    idMedico UUID NOT NULL,
    ativo BOOLEAN,
    PRIMARY KEY(idAgenda)
);

CREATE TABLE horario (
    idHorario INT NOT NULL AUTO_INCREMENT,
    idAgenda UUID NOT NULL,
    inicio TIME NOT NULL,
    fim TIME NOT NULL,
    disponivel BOOLEAN,
    FOREIGN KEY (idAgenda) REFERENCES agenda(idAgenda),
    PRIMARY KEY(idHorario)
);

