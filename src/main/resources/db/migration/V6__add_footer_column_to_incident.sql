ALTER TABLE Incident ADD COLUMN `footer` LONGTEXT NULL AFTER `description`;
ALTER TABLE Incident_AUD ADD COLUMN `footer` LONGTEXT NULL AFTER `description`;
