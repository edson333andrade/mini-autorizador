package br.com.vr.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class CartcaoDTO {
    @NotNull(message = "Numero do cartão é obrigatorio")
    @NotEmpty(message = "Numero do senha é obrigatorio")
    private String numeroCartao;

    @NotEmpty(message = "Numero do senha é obrigatorio")
    @NotNull(message = "Numero do senha é obrigatorio")
    private String senha;

}
