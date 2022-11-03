package br.com.vr.controllers;

import br.com.vr.dto.CartcaoDTO;
import br.com.vr.dto.Transacoes;
import br.com.vr.services.CartoesServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"Gerenciamento de Cartões"})
@RestController
public class CartoesControllers {

    @Autowired
    private CartoesServices cartoesServices;

    @ApiOperation(" Criar cartão")
    @ApiResponses({
            @ApiResponse(code = 201, message = "CREATED", response = CartcaoDTO.class)
    })
    @PostMapping("/cartoes")
    public ResponseEntity<CartcaoDTO> criarCartoa(@Valid @RequestBody CartcaoDTO body){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.cartoesServices.criarCartao(body));
    }


    @ApiOperation(" Obter Saldo cartaõ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
    @GetMapping("/cartoes/{numeroCartao}")
    public ResponseEntity<Double> consultarCartao(@Valid @PathVariable("numeroCartao") String numeroCartao){
        ;
        return ResponseEntity.status(HttpStatus.OK).body(this.cartoesServices.consultarSaldo(numeroCartao));
    }

    @ApiOperation(" Realizar Transação cartão")
    @ApiResponses({
            @ApiResponse(code = 201, message = "CREATED", response = Transacoes.class)
    })
    @PostMapping("/transacoes")
    public ResponseEntity<String> realizarTransacao(@Valid @RequestBody Transacoes body){
        this.cartoesServices.realizarTransacao(body);
        return ResponseEntity.status(HttpStatus.CREATED).body("OK");
    }

}
