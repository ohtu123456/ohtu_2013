create table article (
  id                        integer primary key AUTOINCREMENT,
  short_id                  varchar(255),
  author                    varchar(255),
  title                     varchar(255),
  journal                   varchar(255),
  volume                    varchar(255),
  number                    varchar(255),
  publish_year              varchar(255),
  pages                     varchar(255),
  publisher                 varchar(255),
  address                   varchar(255))
;

create table book (
  id                        integer primary key AUTOINCREMENT,
  short_id                  varchar(255),
  author                    varchar(255),
  title                     varchar(255),
  publish_year              varchar(255),
  publisher                 varchar(255))
;

create table inproceeding (
  id                        integer primary key AUTOINCREMENT,
  short_id                  varchar(255),
  author                    varchar(255),
  title                     varchar(255),
  booktitle                 varchar(255),
  publish_year              varchar(255),
  pages                     varchar(255),
  publisher                 varchar(255),
  address                   varchar(255))
;



