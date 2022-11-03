package br.com.vr.services;

import br.com.vr.dto.CartcaoDTO;
import br.com.vr.dto.Transacoes;

public interface CartoesServices {
    public CartcaoDTO criarCartao(CartcaoDTO cartcaoDTO);
    public Double consultarSaldo(String numeroCartao);
    public void realizarTransacao(Transacoes transacoes);

}
