DO $$ BEGIN
CREATE TYPE user_type AS ENUM ('COMMON', 'MERCHANT');
END $$;

CREATE TABLE tb_users (
                          id BIGINT PRIMARY KEY,
                          first_name VARCHAR(50) NOT NULL,
                          last_name VARCHAR(50) NOT NULL,
                          cpf VARCHAR(20) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          balance DECIMAL(10, 2) NOT NULL,
                          phone VARCHAR(50) DEFAULT NULL,
                          user_type user_type NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);