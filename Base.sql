create database buho;
use buho;
drop database buho;
create table if not exists login_administrador (
usuAdmin varchar (10) primary key not null,
passAdmin varchar (10) not null);

create table if not exists login_cajero (
usuCaj varchar (10) primary key not null,
passCaj varchar (10) not null);

create table if not exists datos_minimarket(
rucMini varchar (13) primary key not null,
nomMini varchar (30) not null,
dirMin varchar (30) not null);

create table if not exists datos_administrador (
cedAdmin char(10) primary key not null,
nomAdmin varchar (50) not null,
CelAdmin varchar (10) not null);

create table if not exists datos_cajero (
cedCaj char (10) primary key not null,
nomCaj varchar (50) not null,
dirCaj varchar (30) not null,
celCaj varchar (10) not null);

insert into productos values("1234","Yogurt","100",21);
insert into productos values("1111","Manzana","100",1);


create table if not exists productos (
codProd varchar (4) primary key not null,
nomProd varchar (30) not null,
stockProd varchar (3) not null,
preProd decimal (4,2) not null);

create table if not exists clientes (
cedCli char (10) primary key not null,
nomCli varchar (50) not null,
dirCli varchar (30) not null,
emailCli varchar (40) not null);

create table if not exists cabecera_factura (
numFac varchar (4) primary key not null,
rucMiniFK varchar (13) not null,
nomMiniFK varchar(30) not null,
dirMiniFK varchar(30) not null,
cedCajFK char (10) not null,
nomCajFK varchar (50) not null,
fechaEmi date not null);

ALTER TABLE `buho`.`cabecera_factura` 
ADD INDEX `fk_rucMini_idx` (`rucMiniFK` ASC) VISIBLE;

ALTER TABLE `buho`.`cabecera_factura` 
ADD CONSTRAINT `fk_rucMini`
  FOREIGN KEY (`rucMiniFK`)
  REFERENCES `buho`.`datos_minimarket` (`rucMini`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
;

ALTER TABLE `buho`.`cabecera_factura` 
ADD INDEX `fk_nomMini_idx` (`nomMiniFK` ASC) VISIBLE;

ALTER TABLE `buho`.`cabecera_factura` 
ADD CONSTRAINT `fk_nomMini`
  FOREIGN KEY (`nomMiniFK`)
  REFERENCES `buho`.`datos_minimarket` (`rucMini`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
;

ALTER TABLE `buho`.`cabecera_factura` 
ADD INDEX `fk_dirMini_idx` (`dirMiniFK` ASC) VISIBLE;

ALTER TABLE `pbuho`.`cabecera_factura` 
ADD CONSTRAINT `fk_dirMini`
  FOREIGN KEY (`dirMiniFK`)
  REFERENCES `buho`.`datos_minimarket` (`rucMini`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
;

ALTER TABLE `buho`.`cabecera_factura` 
ADD INDEX `fk_cedCaj_idx` (`cedCajFK` ASC) VISIBLE;

ALTER TABLE `buho`.`cabecera_factura` 
ADD CONSTRAINT `fk_cedCaj`
  FOREIGN KEY (`cedCajFK`)
  REFERENCES `buho`.`datos_cajero` (`cedCaj`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
;

create table if not exists detalle_factura (
codDet varchar (4) primary key not null,
numFacFK varchar (4) not null, 
codProdFK varchar (4) not null,
nomProd varchar (30) not null,
cantProd varchar (30) not null,
preProd decimal (4,2) not null);

insert into detalle_factura values("1000","100","1234","Yogurt","4",21);
SET SQL_SAFE_UPDATES = 0;
delete from detalle_factura;

ALTER TABLE `buho`.`detalle_factura` 
ADD INDEX `fk_numFac_idx` (`numFacFK` ASC) VISIBLE;

ALTER TABLE `buho`.`detalle_factura` 
ADD CONSTRAINT `fk_numFac`
  FOREIGN KEY (`numFacFK`)
  REFERENCES `buho`.`cabecera_factura` (`numFac`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
;

ALTER TABLE `buho`.`detalle_factura` 
ADD INDEX `fk_codProd_idx` (`codProdFK` ASC) VISIBLE;

ALTER TABLE `buho`.`detalle_factura` 
ADD CONSTRAINT `fk_codProd`
  FOREIGN KEY (`codProdFK`)
  REFERENCES `buho`.`productos` (`codProd`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
;

create table if not exists total_factura (
codTot varchar (4) primary key not null,
codDetFK varchar (4) not null,
subtTot decimal (5,2) not null,
ivaTot decimal (4,2) not null,
totalTot decimal (5,2) not null);

insert into total_factura values("2000","200",1,1,1);
delete from total_factura;
ALTER TABLE `buho`.`total_factura` 
ADD INDEX `fk_codDet_idx` (`codDetFK` ASC) VISIBLE;

ALTER TABLE `buho`.`total_factura` 
ADD CONSTRAINT `fk_codDet`
  FOREIGN KEY (`codDetFK`)
  REFERENCES `buho`.`detalle_factura` (`codDet`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
;

insert into login_administrador values 
(1234567890, 12345);

insert into datos_administrador values 
(1234567890, 'Gustavo Mendoza', 0945213657);

insert into login_cajero (usuCaj, passCaj) values
(1726603739, 123456),
(1750875682, 112233);

insert into datos_cajero values 
(1726603739, 'David Gamboa', 'El Recreo', '0962314256'),
(1750875682, 'Esteban Quito', 'Solanda', '0963214557');

alter table cabecera_factura drop column nomCajFK;

SET FOREIGN_KEY_CHECKS=0;

insert into cabecera_factura (numFac, rucMiniFK, nomMiniFK, dirMiniFK, cedCajFK, fechaEmi) values
(1, 1230214514, 'EstebanQuito Minimarket', 'Solanda', 1750875682, '2023-03-05');

insert into detalle_factura values 
("1", "1", "1", 'Doritos', "3", 0.50);

