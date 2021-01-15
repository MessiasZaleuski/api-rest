insert into cliente(nome, email, senha, documento, data_cadastro) values ('João Pereira', 'joao@pereira', 1010, '1234', "2020-12-30");
insert into cliente(nome, email, senha, documento, data_cadastro) values ('Matheus Rodrigues', 'matheus@rodrigues', 1011, '1565', "2020-12-30");
insert into cliente(nome, email, senha, documento, data_cadastro) values ('Benedita Alves', 'benedita@alves', 1012, '1517', "2020-12-30");
insert into cliente(nome, email, senha, documento, data_cadastro) values ('Maria Paz', 'maria@paz', 9865, '2185', "2020-12-30");
insert into cliente(nome, email, senha, documento, data_cadastro) values ('Rogerio Oliveira', 'rogerio@oliveira', 7896, '0325', "2020-12-30");
insert into cliente(nome, email, senha, documento, data_cadastro) values ('Pedro Goncalves', 'pedro@goncalves', 4596, '7851', "2020-12-30");
insert into cliente(nome, email, senha, documento, data_cadastro) values ('Janira Augusta', 'janira@augusta', 7851, '9031', "2020-12-30");
insert into cliente(nome, email, senha, documento, data_cadastro) values ('Carolina Santos', 'carolina@santos', 2154, '9164', "2020-12-30");
insert into cliente(nome, email, senha, documento, data_cadastro) values ('Peterson Almeida ', 'peterson@almeida', 1678, '2358', "2020-12-30");
insert into cliente(nome, email, senha, documento, data_cadastro) values ('Inácio Silva', 'inacio@silva', 5451, '2154', "2020-12-30");

insert into fornecedor(nome, cnpj) values ('Pinduca', '123456');
insert into fornecedor(nome, cnpj) values ('Marilan', '965214');
insert into fornecedor(nome, cnpj) values ('Parati', '256324');
insert into fornecedor(nome, cnpj) values ('Buruti', '985461');
insert into fornecedor(nome, cnpj) values ('Pé Vermelho', '032547');
insert into fornecedor(nome, cnpj) values ('Uni Mel', '987445');
insert into fornecedor(nome, cnpj) values ('Nescafe', '236574');
insert into fornecedor(nome, cnpj) values ('Copacol', '875421');
insert into fornecedor(nome, cnpj) values ('Sadia', '986545');
insert into fornecedor(nome, cnpj) values ('perdigão', '326594');

insert into venda( total_compra, data_compra, cliente_id, fornecedor_id) values (100, "2020-12-30", 1,2);
insert into venda( total_compra, data_compra, cliente_id, fornecedor_id) values (100, "2018-11-25", 3,2);
insert into venda( total_compra, data_compra, cliente_id, fornecedor_id) values (100, "2017-05-30", 2,3);
insert into venda( total_compra, data_compra, cliente_id, fornecedor_id ) values (100, "2001-03-30", 2,2);

insert into produto(nome, codigo_produto , valor, promocao, valor_promo, categoria, imagem, quantidade, fornecedor_id, vendas_id ) values ('Feijão', '5148', 10.00, false, 1.00, 'Alimentos', 'png', 1,1,1);
insert into produto(nome, codigo_produto , valor, promocao, valor_promo, categoria, imagem, quantidade, fornecedor_id, vendas_id  ) values ('Arroz', '2154', 9.00, false, 2.00, 'Alimentos', 'png', 2,2,2);
insert into produto(nome, codigo_produto , valor, promocao, valor_promo, categoria, imagem, quantidade, fornecedor_id, vendas_id  ) values ('Macarrão', '5146', 8.00, false, 3.00, 'Alimentos', 'png', 3,1,3);
insert into produto(nome, codigo_produto , valor, promocao, valor_promo, categoria, imagem, quantidade, fornecedor_id, vendas_id  ) values ('Pimenta', '2154', 6.00, false, 2.00, 'Alimentos', 'png', 1,3,4);

