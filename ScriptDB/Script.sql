DROP DATABASE IF EXISTS organizeOasis;
CREATE DATABASE organizeOasis;

USE organizeOasis;

CREATE TABLE IF NOT EXISTS users (
 id_user INT NOT NULL AUTO_INCREMENT,
 name VARCHAR(16),
 surname VARCHAR(24),
 email VARCHAR(32),
 user_password VARCHAR(32),
 rol ENUM('Private','Influencer','Administrator'),
 visits INT,
 PRIMARY KEY (id_user));

-- ALTER TABLE users
-- MODIFY COLUMN rol ENUMCOMMENT 'Tipo de rol que tiene el usuario, privado: no puede compartir paginas, influencer: permite compartir paginas';
 
 CREATE TABLE IF NOT EXISTS pages (
 id_page INT NOT NULL AUTO_INCREMENT,
 title VARCHAR(16),
 num_blocks INT,
 id_user INT NOT NULL,
 visits INT,
 PRIMARY KEY (id_page),
 FOREIGN KEY (id_user) REFERENCES users(id_user));
-- ALTER TABLE pages
-- MODIFY COLUMN id_user COMMENT 'Usuario al que esta asociado la pagina',
-- MODIFY COLUMN num_blocks COMMENT 'Numero de bloques en los que se divide la pagina';

 
    CREATE TABLE IF NOT EXISTS blocks (
 id_block INT NOT NULL AUTO_INCREMENT,
 id_type INT,
 id_page INT NOT NULL,
 PRIMARY KEY (id_block),
 FOREIGN KEY (id_page) REFERENCES pages(id_page));
-- ALTER TABLE blocks
-- MODIFY COLUMN id_page COMMENT 'Pagina a la que esta asociado un bloque';

 
    CREATE TABLE IF NOT EXISTS texts (
 id_texts INT NOT NULL AUTO_INCREMENT,
 id_block INT,
 text_str text,
 PRIMARY KEY (id_texts),
 FOREIGN KEY (id_block) REFERENCES blocks(id_block));
-- ALTER TABLE texts
-- MODIFY COLUMN id_block COMMENT 'Bloque al que esta asociado un tipo de bloque texto';
 
   CREATE TABLE IF NOT EXISTS lists (
 id_list INT NOT NULL AUTO_INCREMENT,
 id_block INT,
 text_str text,
 PRIMARY KEY (id_list),
 FOREIGN KEY (id_block) REFERENCES blocks(id_block));
-- ALTER TABLE lists
-- MODIFY COLUMN id_block COMMENT 'Bloque al que esta asociado un tipo de bloque lista';


   CREATE TABLE IF NOT EXISTS tables (
 id_table INT NOT NULL AUTO_INCREMENT,
 id_block INT,
 text_str text,
 PRIMARY KEY (id_table),
 FOREIGN KEY (id_block) REFERENCES blocks(id_block));
-- ALTER TABLE tables
-- MODIFY COLUMN id_block COMMENT 'Bloque al que esta asociado un tipo de bloque tabla';

 
 ALTER TABLE blocks
 ADD CONSTRAINT fk_Texts FOREIGN KEY (id_type) REFERENCES texts(id_texts);

 ALTER TABLE blocks
 ADD CONSTRAINT fk_Lists FOREIGN KEY (id_type) REFERENCES lists(id_list);

 ALTER TABLE blocks
 ADD CONSTRAINT fk_Tables FOREIGN KEY (id_type) REFERENCES tables(id_table);
 
 -- USUARIOS --
 INSERT INTO `organizeOasis`.`users` (`id_user`, `name`, `surname`, `email`) VALUES ('1', 'Alicia', 'Mayor', 'alicia@gmail.com');
 UPDATE `organizeOasis`.`users` SET `user_password`='1234', `rol`='administrator', `visits`='3' WHERE  `id_user`=1;
/*COMMENT ON COLUMN users.rol IS 'Tipo de rol que tiene el usuario. privado: no puede compartir paginas, influencer: permite compartir paginas';
COMMENT ON COLUMN pages.id_user IS 'Usuario al que esta asociado la pagina';
COMMENT ON COLUMN pages.num_blocks IS 'Numero de bloques en los que se divide la pagina';
COMMENT ON COLUMN blocks.id_page IS 'Pagina a la que esta asociado un bloque';
COMMENT ON COLUMN texts.id_block IS 'Bloque al que esta asociado un tipo de bloque texto';
COMMENT ON COLUMN lists.id_block IS 'Bloque al que esta asociado un tipo de bloque lista';
COMMENT ON COLUMN tables.id_block IS 'Bloque al que esta asociado un tipo de bloque tabla';*/

 