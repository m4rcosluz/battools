
CREATE TABLE cliente_contato (
  cd_contato  VARCHAR2(50) NOT NULL,
  nm_contato  VARCHAR2(50) NOT NULL,
  cd_cliente  VARCHAR2(10) NULL,
  cd_usuario  VARCHAR2(30) NULL,
  dt_gravacao DATE         NULL,
  cd_ramal    VARCHAR2(9)  NULL
)
  STORAGE (
    NEXT       1024 K
  )
/

PROMPT ALTER TABLE cliente_contato ADD CONSTRAINT cd_contato_pk PRIMARY KEY
ALTER TABLE cliente_contato
  ADD CONSTRAINT cd_contato_pk PRIMARY KEY (
    cd_contato
  )
  USING INDEX
    STORAGE (
      NEXT       1024 K
    )
/




COMMIT WORK;