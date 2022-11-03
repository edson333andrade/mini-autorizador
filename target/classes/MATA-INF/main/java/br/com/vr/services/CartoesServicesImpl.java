package br.com.vr.services;

import br.com.vr.dto.CartcaoDTO;
import br.com.vr.dto.Transacoes;
import br.com.vr.exception.ViolationException;
import br.com.vr.models.Cartao;
import br.com.vr.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class CartoesServicesImpl implements CartoesServices {
    @Autowired
    private CartaoRepository cartaoRepository;

    /**
     * Metodo de criação de Cartaão
     * @param cartcaoDTO
     */
    @Override
    public CartcaoDTO criarCartao(CartcaoDTO cartcaoDTO) {
        var cartao = new Cartao();
        cartao.setNumeroCartao(cartcaoDTO.getNumeroCartao());
        cartao.setSenha(cartcaoDTO.getSenha());
        this.cartaoRepository.findByNumeroCartao(cartcaoDTO.getNumeroCartao())
                .ifPresent((p) -> exception(cartcaoDTO, HttpStatus.UNPROCESSABLE_ENTITY));
        this.cartaoRepository.saveAndFlush(cartao);
        return cartcaoDTO;
    }

    /**
     * Metodo de consultar saldo
     * @param numeroCartao
     * @return Double
     */
    @Override
    public Double consultarSaldo(String numeroCartao) {
        var cartao = this.cartaoRepository.findByNumeroCartao(numeroCartao)
                .orElseThrow(() ->{
                    exception(null, HttpStatus.NOT_FOUND);
                    return null;
                });
        return cartao.getSaldo();
    }

    /**
     * Realizar transação
     * @param transacoes
     */
    @Override
    public void realizarTransacao(Transacoes transacoes) {
        var cartao = this.cartaoRepository.findByNumeroCartao(transacoes.getNumeroCartao())
                .orElseThrow(() ->{
                    exception("CARTAO_INEXISTENTE ", HttpStatus.UNPROCESSABLE_ENTITY);
                    return null;
                });

        if(!cartao.getSenha().equals(transacoes.getSenha())){
            exception("SENHA_INVALIDA", HttpStatus.UNPROCESSABLE_ENTITY);
        }else if(cartao.getSaldo().compareTo(transacoes.getValor()) <= 0){
            exception("SALDO_INSUFICIENTE", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        var saldoAtual = cartao.getSaldo();
        cartao.setSaldo(saldoAtual - transacoes.getValor());
        this.cartaoRepository.saveAndFlush(cartao);
    }

    /**
     * Metodo de Execução dos exception
     * @param cartcaoDTO
     * @param httpStatus
     */
    private void exception(Object cartcaoDTO, HttpStatus httpStatus) {
        throw new ViolationException(cartcaoDTO, httpStatus);
    }
}
