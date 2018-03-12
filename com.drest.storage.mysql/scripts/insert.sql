use moi;

INSERT INTO roles(name,is_system_role) values ('admin',1);
INSERT INTO roles(name,is_system_role) values ('store_mgr',0);
INSERT INTO roles(name,is_system_role) values ('empployee',0);
INSERT INTO roles(name,is_system_role) values ('client',0);

-- default location -1
insert into locations (id, street_address,postal_code, city, state_province, country_id, geo_coordinates) values (-1,"DEFAULT","000000", "DEFAULT","DEFAULT","DF","0.0,0.0");