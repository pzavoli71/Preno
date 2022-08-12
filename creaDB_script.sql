USE MASTER
GO
CREATE DATABASE preno
GO
ALTER DATABASE preno MODIFY FILE (NAME = preno, FILEGROWTH = 10%)
GO

USE preno
GO

DROP TABLE zUtenteLocale
GO
CREATE TABLE zUtenteLocale (
	cdUtente int NOT NULL,
	idUtente char(40) NOT NULL,
	dsUtente char(255) NOT NULL,
	[disabled] smallint NOT NULL,
	hpwd char(32) NOT NULL,
	scadpasswd datetime NULL,
	duratapasswd int NOT NULL,
	UtenteInternet smallint NOT NULL,
	Realm smallint NOT NULL,
	Email varchar(100) NOT NULL,
	ultagg datetime NOT NULL,
	utente char(16) NOT NULL,
	CONSTRAINT PK_zUtenteLocale PRIMARY KEY CLUSTERED 
	(
		CdUtente ASC
	)
)
GO

ALTER TABLE zUtenteLocale ADD  CONSTRAINT DF_zUtenteLocale_dsUtente  DEFAULT ('') FOR dsUtente
GO

ALTER TABLE zUtenteLocale ADD  CONSTRAINT DF_zUtenteLocale_disabled  DEFAULT ((0)) FOR disabled
GO

ALTER TABLE zUtenteLocale ADD  CONSTRAINT DF_zUtenteLocale_duratapasswd  DEFAULT ((0)) FOR duratapasswd
GO

ALTER TABLE zUtenteLocale ADD  CONSTRAINT DF_zUtenteLocale_UtenteInternet  DEFAULT ((0)) FOR UtenteInternet
GO

ALTER TABLE zUtenteLocale ADD  CONSTRAINT DF_zUtenteLocale_Realm  DEFAULT ((1)) FOR Realm
GO

ALTER TABLE zUtenteLocale ADD  CONSTRAINT DF_zUtenteLocale_Email  DEFAULT ('') FOR Email
GO

ALTER TABLE zUtenteLocale ADD  CONSTRAINT DF_zUtenteLocale_ultagg  DEFAULT (getdate()) FOR ultagg
GO

ALTER TABLE zUtenteLocale ADD  CONSTRAINT DF_zUtenteLocale_utente  DEFAULT (user_name()) FOR utente
GO


CREATE INDEX IX_IdUtente ON zUtenteLocale(IdUtente)
GO

create table zUtenteApp
(
	CdUtente int not null,
	Realm smallint not null,
	IdProfiloUtente int null,
	ultagg datetime not null,
	utente varchar(20) not null,
	CONSTRAINT PK_zUtenteApp PRIMARY KEY CLUSTERED 
	(
		CdUtente ASC,Realm ASC
	)
)
GO
ALTER TABLE zUtenteApp ADD CONSTRAINT DF_zUtenteSpec_ultagg DEFAULT (getdate()) FOR ultagg
GO
ALTER TABLE zUtenteApp ADD CONSTRAINT DF_zUtenteSpec_utente DEFAULT (user_name()) FOR utente
GO
create index IX_IdProfiloUtente on zUtenteApp(IdProfiloUtente)
GO

CREATE TABLE zGruppo
(
	CdGruppo int NOT NULL,
	IdGruppo varchar(20) NOT NULL,
	DsGruppo varchar(255) NOT NULL,
	Flags int NOT NULL,
	ultagg datetime NOT NULL,
	utente varchar(20) NOT NULL,
	CONSTRAINT PK_zGruppo PRIMARY KEY CLUSTERED 
	(
		CdGruppo ASC
	)
)
GO
ALTER TABLE zGruppo ADD CONSTRAINT DF_zGruppo_ultagg DEFAULT (getdate()) FOR ultagg
GO
ALTER TABLE zGruppo ADD CONSTRAINT DF_zGruppo_utente DEFAULT (user_name()) FOR utente
GO


CREATE TABLE zTrans
(
	CdPDC varchar(50) NOT NULL,
	DsPDC varchar(128) NOT NULL,
	IdNodo int NOT NULL,
	IdPadre int NOT NULL,
	Flag int NOT NULL,
	ultagg datetime NOT NULL,
	utente varchar(20) NOT NULL,
	CONSTRAINT PK_zPDC PRIMARY KEY CLUSTERED(CdPDC)
)
GO

ALTER TABLE zTrans ADD CONSTRAINT DF_zTrans_IdNodo DEFAULT (0) FOR IdNodo
GO
ALTER TABLE zTrans ADD CONSTRAINT DF_zTrans_IdPadre DEFAULT (-1) FOR IdPadre
GO
ALTER TABLE zTrans ADD CONSTRAINT DF_zTrans_Flag DEFAULT (0) FOR Flag
GO
ALTER TABLE zTrans ADD CONSTRAINT DF_zTrans_ultagg DEFAULT (getdate()) FOR ultagg
GO
ALTER TABLE zTrans ADD CONSTRAINT DF_zTrans_utente DEFAULT (user_name()) FOR utente
GO

CREATE TABLE zPermessi
(
	CdGruppo int NOT NULL,
	CdPDC varchar(50) NOT NULL,
	Descrizione varchar(50) NULL,
	Permesso varchar(64) NOT NULL,
	ultagg datetime NOT NULL,
	utente varchar(20) NOT NULL,
	CONSTRAINT PK_zPerm PRIMARY KEY CLUSTERED(CdGruppo, CdPDC)
)
GO

ALTER TABLE zPermessi ADD CONSTRAINT DF_zPermessi_ultagg DEFAULT (getdate()) FOR ultagg
GO
ALTER TABLE zPermessi ADD CONSTRAINT DF_zPermessi_utente DEFAULT (user_name()) FOR utente
GO
ALTER TABLE zPermessi ADD CONSTRAINT FK_zPerm_zGruppo FOREIGN KEY(CdGruppo) REFERENCES zGruppo(CdGruppo)
GO
ALTER TABLE zPermessi ADD CONSTRAINT FK_zPerm_zPDC FOREIGN KEY(CdPDC) REFERENCES zTrans(CdPDC)
GO

CREATE TABLE zRuoliGruppo
(
	IdRuolo int NOT NULL,
	CdGruppo int NOT NULL,
	ultagg datetime NOT NULL,
	utente varchar(20) NOT NULL,
	CONSTRAINT PK_zRuoliGruppo PRIMARY KEY CLUSTERED(IdRuolo,CdGruppo)
)
GO

ALTER TABLE zRuoliGruppo ADD CONSTRAINT DF_zRuoliGruppo_ultagg DEFAULT (getdate()) FOR ultagg
GO
ALTER TABLE zRuoliGruppo ADD CONSTRAINT DF_zRuoliGruppo_utente DEFAULT (user_name()) FOR utente
GO
ALTER TABLE zRuoliGruppo ADD CONSTRAINT FK_Gruppo_RuoliGruppo FOREIGN KEY(CdGruppo) REFERENCES zGruppo(CdGruppo)
GO

CREATE TABLE zUtGr
(
	CdUtente int NOT NULL,
	CdGruppo int NOT NULL,
	Realm smallint NOT NULL,
	ultagg datetime NOT NULL,
	utente varchar(20) NOT NULL,
	CONSTRAINT PK_zUtGr PRIMARY KEY CLUSTERED(CdUtente,CdGruppo,Realm)
)
GO

ALTER TABLE zUtGr ADD CONSTRAINT DF_zUtGr_ultagg DEFAULT (getdate()) FOR ultagg
GO
ALTER TABLE zUtGr ADD CONSTRAINT DF_zUtGr_utente DEFAULT (user_name()) FOR utente
GO

CREATE view zRuolo 
AS
	select 
		IdRuolo,
		NomeRuolo,
		SiglaRuolo,
		IdPadre,
		IdEnte,
		bAmministratoreImpresa,
		bPerImprese,
		bRichiedibileDaTutti,
		bInternet,
		bNonDipendeDaUfficio,
		IdProc,
		bVediDeleghe,
		bCollaboratore,
		bRuoloInvisibile,
		bRespChat,
		ultagg,
		utente
	from portalepa.dbo.Ruolo 
	where idproc = 67
GO

create view dbo.zUtente 
AS 
	select 
		cdUtente,
		idUtente,
		dsUtente,
		[disabled],
		dbuser,
		xpwd,
		hpwd,
		scadpasswd,
		duratapasswd,
		IdSoggetto,
		Email,
		UtenteInternet,
		Realm,
		bEntraSenzaSmartCard,
		NumTelefono,
		CodiceFiscale,
		ultagg,
		utente
	from portalepa.dbo.zUtente
	
	union all

	select 
		cdUtente,
		idUtente,
		dsUtente,
		[disabled],
		null,
		null,
		hpwd,
		scadpasswd,
		duratapasswd,
		null,
		Email,
		UtenteInternet,
		Realm,
		null,
		null,
		null,
		ultagg,
		utente
	from zUtenteLocale
GO

CREATE LOGIN sqlpreno WITH PASSWORD = '$sql!preno'
GO

CREATE user sqlpreno FOR LOGIN sqlpreno WITH DEFAULT_SCHEMA = [dbo]
GO
EXEC sp_addrolemember N'db_owner', N'sqlpreno'
GO

USE aus
GO
CREATE user sqlpreno FOR LOGIN sqlpreno WITH DEFAULT_SCHEMA = [dbo]
GO
EXEC sp_addrolemember N'db_datareader', N'sqlpreno'
GO

USE paghe
GO
CREATE user sqlpreno FOR LOGIN sqlpreno WITH DEFAULT_SCHEMA = [dbo]
GO
EXEC sp_addrolemember N'db_datareader', N'sqlpreno'
GO

USE opec
GO
CREATE user sqlpreno FOR LOGIN sqlpreno WITH DEFAULT_SCHEMA = [dbo]
GO
EXEC sp_addrolemember N'db_datareader', N'sqlpreno'
GO

USE portalepa
GO
CREATE user sqlpreno FOR LOGIN sqlpreno WITH DEFAULT_SCHEMA = [dbo]
GO
EXEC sp_addrolemember N'db_datareader', N'sqlpreno'
GO

USE pass
GO
CREATE user sqlpreno FOR LOGIN sqlpreno WITH DEFAULT_SCHEMA = [dbo]
GO
EXEC sp_addrolemember N'db_datareader', N'sqlpreno'
GO

USE preno
GO

declare @cdGruppo int
set @cdGruppo=1

insert into zGruppo(CdGruppo,IdGruppo,DsGruppo,Flags) values (@cdGruppo,'Ammin','Amministratori della procedura',0);

if >0
begin
	insert into zRuoliGruppo(IdRuolo,CdGruppo) values(,@cdGruppo);
end

insert into zUtenteLocale(CdUtente,idUtente,hpwd,Realm,UtenteInternet) values (1,'ciscoop','E87031463711C3F1AD4F4823EBA06FE7',1,0);
insert into zUtGr(CdUtente,Realm,CdGruppo) values (1,1,@cdGruppo);

GO

