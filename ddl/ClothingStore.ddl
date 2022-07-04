-- Generated by Oracle SQL Developer Data Modeler 21.4.2.059.0838
--   at:        2022-06-24 16:39:18 PKT
--   site:      Oracle Database 11g
--   type:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE admin (
    user_id    INTEGER NOT NULL,
    admin_id   INTEGER NOT NULL,
    contact_no VARCHAR2(15)
);

ALTER TABLE admin ADD CONSTRAINT admin_pk PRIMARY KEY ( user_id );

ALTER TABLE admin ADD CONSTRAINT admin_pkv1 UNIQUE ( admin_id );

CREATE TABLE brand (
    brand_id   INTEGER NOT NULL,
    brand_name VARCHAR2(256) NOT NULL
);

ALTER TABLE brand ADD CONSTRAINT brand_pk PRIMARY KEY ( brand_id );

CREATE TABLE cart (
    cart_number INTEGER NOT NULL,
    quantity    INTEGER
);

ALTER TABLE cart ADD CONSTRAINT cart_pk PRIMARY KEY ( cart_number );

CREATE TABLE credit_card (
    credit_card_no INTEGER NOT NULL,
    paymentmode_id INTEGER NOT NULL,
    pin            INTEGER NOT NULL
);

ALTER TABLE credit_card ADD CONSTRAINT credit_card_pk PRIMARY KEY ( paymentmode_id );

ALTER TABLE credit_card ADD CONSTRAINT credit_card_pkv1 UNIQUE ( credit_card_no );

CREATE TABLE customer (
    user_id          INTEGER NOT NULL,
    customer_id      INTEGER NOT NULL,
    address          VARCHAR2(256),
    frequency        INTEGER,
    cart_cart_number INTEGER NOT NULL
);

CREATE UNIQUE INDEX customer__idx ON
    customer (
        cart_cart_number
    ASC );

ALTER TABLE customer ADD CONSTRAINT customer_pk PRIMARY KEY ( user_id );

ALTER TABLE customer ADD CONSTRAINT customer_pkv1 UNIQUE ( customer_id );

CREATE TABLE easypaisa (
    mobile_no      INTEGER NOT NULL,
    paymentmode_id INTEGER NOT NULL,
    iban           INTEGER NOT NULL
);

ALTER TABLE easypaisa ADD CONSTRAINT easypaisa_pk PRIMARY KEY ( paymentmode_id );

ALTER TABLE easypaisa ADD CONSTRAINT easypaisa_pkv1 UNIQUE ( mobile_no );

CREATE TABLE employee (
    user_id      INTEGER NOT NULL,
    employee_id  INTEGER NOT NULL,
    joining_date DATE,
    leave_date   DATE,
    salary       INTEGER
);

ALTER TABLE employee ADD CONSTRAINT employee_pk PRIMARY KEY ( user_id );

ALTER TABLE employee ADD CONSTRAINT employee_pkv1 UNIQUE ( employee_id );

CREATE TABLE has (
    cart_cart_number     INTEGER NOT NULL,
    items_item_id        INTEGER NOT NULL,
    items_brand_brand_id INTEGER NOT NULL
);

ALTER TABLE has
    ADD CONSTRAINT has_pk PRIMARY KEY ( cart_cart_number,
                                        items_item_id,
                                        items_brand_brand_id );

CREATE TABLE items (
    item_id        INTEGER NOT NULL,
    "Size"         INTEGER,
    price          INTEGER NOT NULL,
    type           VARCHAR2(256) NOT NULL,
    brand_brand_id INTEGER NOT NULL,
    stock_stock_id INTEGER NOT NULL
);

ALTER TABLE GENDER ADD CONSTRAINT items_pk PRIMARY KEY ( item_id,
                                                        brand_brand_id );

CREATE TABLE orders (
    order_no                    INTEGER NOT NULL,
    order_details               VARCHAR2(256),
    "Date"                      DATE,
    time                        DATE,
    customer_customer_id        INTEGER NOT NULL,
    payment_mode_paymentmode_id INTEGER NOT NULL
);

ALTER TABLE orders ADD CONSTRAINT orders_pk PRIMARY KEY ( order_no );

CREATE TABLE payment_mode (
    customer_customer_id INTEGER NOT NULL,
    paymentmode_id       INTEGER NOT NULL,
    "Mode"               CHAR(12) NOT NULL
);

ALTER TABLE payment_mode
    ADD CONSTRAINT ch_inh_payment_mode CHECK ( "Mode" IN ( 'Credit_Card', 'EasyPaisa', 'Payment_mode' ) );

ALTER TABLE payment_mode ADD CONSTRAINT payment_mode_pk PRIMARY KEY ( paymentmode_id );

CREATE TABLE sales (
    items_item_id        INTEGER NOT NULL,
    items_brand_brand_id INTEGER NOT NULL,
    orders_order_no      INTEGER NOT NULL,
    quantity             INTEGER NOT NULL,
    discount             FLOAT NOT NULL
);

ALTER TABLE sales
    ADD CONSTRAINT sales_pk PRIMARY KEY ( items_item_id,
                                          items_brand_brand_id,
                                          orders_order_no );

CREATE TABLE stock (
    stock_id      INTEGER NOT NULL,
    quantity      INTEGER NOT NULL,
    purchase_date DATE
);

ALTER TABLE stock ADD CONSTRAINT stock_pk PRIMARY KEY ( stock_id );

CREATE TABLE "User" (
    user_id  INTEGER NOT NULL,
    type     VARCHAR2(8) NOT NULL,
    cnic     INTEGER NOT NULL,
    password VARCHAR2(16) NOT NULL,
    email    VARCHAR2(100)
);

ALTER TABLE "User"
    ADD CONSTRAINT ch_inh_user CHECK ( type IN ( 'Admin', 'Customer', 'Employee', 'User' ) );

ALTER TABLE "User" ADD CONSTRAINT user_pk PRIMARY KEY ( user_id );

ALTER TABLE admin
    ADD CONSTRAINT admin_user_fk FOREIGN KEY ( user_id )
        REFERENCES "User" ( user_id );

ALTER TABLE credit_card
    ADD CONSTRAINT credit_card_payment_mode_fk FOREIGN KEY ( paymentmode_id )
        REFERENCES payment_mode ( paymentmode_id );

ALTER TABLE customer
    ADD CONSTRAINT customer_cart_fk FOREIGN KEY ( cart_cart_number )
        REFERENCES cart ( cart_number );

ALTER TABLE customer
    ADD CONSTRAINT customer_user_fk FOREIGN KEY ( user_id )
        REFERENCES "User" ( user_id );

ALTER TABLE easypaisa
    ADD CONSTRAINT easypaisa_payment_mode_fk FOREIGN KEY ( paymentmode_id )
        REFERENCES payment_mode ( paymentmode_id );

ALTER TABLE employee
    ADD CONSTRAINT employee_user_fk FOREIGN KEY ( user_id )
        REFERENCES "User" ( user_id );

ALTER TABLE has
    ADD CONSTRAINT has_cart_fk FOREIGN KEY ( cart_cart_number )
        REFERENCES cart ( cart_number );

ALTER TABLE has
    ADD CONSTRAINT has_items_fk FOREIGN KEY ( items_item_id,
                                              items_brand_brand_id )
        REFERENCES GENDER ( item_id,
                           brand_brand_id );

ALTER TABLE GENDER
    ADD CONSTRAINT items_brand_fk FOREIGN KEY ( brand_brand_id )
        REFERENCES brand ( brand_id );

ALTER TABLE GENDER
    ADD CONSTRAINT items_stock_fk FOREIGN KEY ( stock_stock_id )
        REFERENCES stock ( stock_id );

ALTER TABLE orders
    ADD CONSTRAINT orders_customer_fk FOREIGN KEY ( customer_customer_id )
        REFERENCES customer ( customer_id );

ALTER TABLE orders
    ADD CONSTRAINT orders_payment_mode_fk FOREIGN KEY ( payment_mode_paymentmode_id )
        REFERENCES payment_mode ( paymentmode_id );

ALTER TABLE payment_mode
    ADD CONSTRAINT payment_mode_customer_fk FOREIGN KEY ( customer_customer_id )
        REFERENCES customer ( customer_id );

ALTER TABLE sales
    ADD CONSTRAINT sales_items_fk FOREIGN KEY ( items_item_id,
                                                items_brand_brand_id )
        REFERENCES GENDER ( item_id,
                           brand_brand_id );

ALTER TABLE sales
    ADD CONSTRAINT sales_orders_fk FOREIGN KEY ( orders_order_no )
        REFERENCES orders ( order_no );

CREATE OR REPLACE TRIGGER arc_fkarc_1_credit_card BEFORE
    INSERT OR UPDATE OF paymentmode_id ON credit_card
    FOR EACH ROW
DECLARE
    d CHAR(12);
BEGIN
    SELECT
        a."Mode"
    INTO d
    FROM
        payment_mode a
    WHERE
        a.paymentmode_id = :new.paymentmode_id;

    IF ( d IS NULL OR d <> 'Credit_Card' ) THEN
        raise_application_error(-20223, 'FK Credit_Card_Payment_mode_FK in Table Credit_Card violates Arc constraint on Table Payment_mode - discriminator column Mode doesn''t have value ''Credit_Card''');
    END IF;

EXCEPTION
    WHEN no_data_found THEN
        NULL;
    WHEN OTHERS THEN
        RAISE;
END;
/

CREATE OR REPLACE TRIGGER arc_fkarc_1_easypaisa BEFORE
    INSERT OR UPDATE OF paymentmode_id ON easypaisa
    FOR EACH ROW
DECLARE
    d CHAR(12);
BEGIN
    SELECT
        a."Mode"
    INTO d
    FROM
        payment_mode a
    WHERE
        a.paymentmode_id = :new.paymentmode_id;

    IF ( d IS NULL OR d <> 'EasyPaisa' ) THEN
        raise_application_error(-20223, 'FK EasyPaisa_Payment_mode_FK in Table EasyPaisa violates Arc constraint on Table Payment_mode - discriminator column Mode doesn''t have value ''EasyPaisa''');
    END IF;

EXCEPTION
    WHEN no_data_found THEN
        NULL;
    WHEN OTHERS THEN
        RAISE;
END;
/

CREATE OR REPLACE TRIGGER arc_fkarc_2_employee BEFORE
    INSERT OR UPDATE OF user_id ON employee
    FOR EACH ROW
DECLARE
    d VARCHAR2(8);
BEGIN
    SELECT
        a.type
    INTO d
    FROM
        "User" a
    WHERE
        a.user_id = :new.user_id;

    IF ( d IS NULL OR d <> 'Employee' ) THEN
        raise_application_error(-20223, 'FK Employee_User_FK in Table Employee violates Arc constraint on Table "User" - discriminator column Type doesn''t have value ''Employee''');
    END IF;

EXCEPTION
    WHEN no_data_found THEN
        NULL;
    WHEN OTHERS THEN
        RAISE;
END;
/

CREATE OR REPLACE TRIGGER arc_fkarc_2_customer BEFORE
    INSERT OR UPDATE OF user_id ON customer
    FOR EACH ROW
DECLARE
    d VARCHAR2(8);
BEGIN
    SELECT
        a.type
    INTO d
    FROM
        "User" a
    WHERE
        a.user_id = :new.user_id;

    IF ( d IS NULL OR d <> 'Customer' ) THEN
        raise_application_error(-20223, 'FK Customer_User_FK in Table Customer violates Arc constraint on Table "User" - discriminator column Type doesn''t have value ''Customer''');
    END IF;

EXCEPTION
    WHEN no_data_found THEN
        NULL;
    WHEN OTHERS THEN
        RAISE;
END;
/

CREATE OR REPLACE TRIGGER arc_fkarc_2_admin BEFORE
    INSERT OR UPDATE OF user_id ON admin
    FOR EACH ROW
DECLARE
    d VARCHAR2(8);
BEGIN
    SELECT
        a.type
    INTO d
    FROM
        "User" a
    WHERE
        a.user_id = :new.user_id;

    IF ( d IS NULL OR d <> 'Admin' ) THEN
        raise_application_error(-20223, 'FK Admin_User_FK in Table Admin violates Arc constraint on Table "User" - discriminator column Type doesn''t have value ''Admin''');
    END IF;

EXCEPTION
    WHEN no_data_found THEN
        NULL;
    WHEN OTHERS THEN
        RAISE;
END;
/



-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                            14
-- CREATE INDEX                             1
-- ALTER TABLE                             36
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           5
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
