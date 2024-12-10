-- Criação do tipo ENUM
CREATE TYPE user_type_enum AS ENUM ('COMMON', 'MERCHANT');

-- Criação da tabela com o tipo ENUM
CREATE TABLE tb_users (
                          id BIGINT PRIMARY KEY,
                          first_name VARCHAR(50) NOT NULL,
                          last_name VARCHAR(50) NOT NULL,
                          cpf VARCHAR(20) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          balance DECIMAL(10, 2) NOT NULL,
                          phone VARCHAR(50) DEFAULT NULL,
                          user_type user_type_enum NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
