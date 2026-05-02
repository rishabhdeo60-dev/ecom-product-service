ALTER TABLE products
    ADD CONSTRAINT uq_products_creator_name
        UNIQUE (created_by, name);