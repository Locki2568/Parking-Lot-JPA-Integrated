alter table if exists parking_lot add column if not exists parking_lot_id varchar(12) not null;
ALTER TABLE parking_lot ADD UNIQUE (parking_lot_id);
