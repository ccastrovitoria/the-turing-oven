package com.turingoven.Dominio.Servicos;

import com.turingoven.Dominio.Entidades.Pedido;

public interface ICozinhaService {
    void chegadaDePedido(Pedido p);
    void pedidoPronto();
}