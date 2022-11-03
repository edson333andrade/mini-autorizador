package br.com.vr.controllers;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import br.com.vr.Application;
import br.com.vr.dto.CartcaoDTO;
import br.com.vr.dto.Transacoes;
import br.com.vr.models.Cartao;
import br.com.vr.repository.CartaoRepository;
import br.com.vr.services.CartoesServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={ Application.class })
public class CartoesControllersTest {
    @InjectMocks
    private CartoesControllers cartoesControllers;
    @Mock
    private CartoesServices cartoesServices;
    @Mock
    private CartaoRepository cartaoRepository;
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(webApplicationContext).build();

    }

    @Test
    public void should_CreateCard_When_ValidRequest() throws Exception {
        var cartcaoDTO = new CartcaoDTO();

        cartcaoDTO.setNumeroCartao("6549873025634501");
        cartcaoDTO.setSenha("1234");
        when(cartoesServices.criarCartao(any(CartcaoDTO.class))).thenReturn(cartcaoDTO);

        mockMvc.perform(post("/cartoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(cartcaoDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.numeroCartao").value("6549873025634501"))
                .andExpect(jsonPath("$.senha").value("1234"));
    }

    @Test
    public void should_CreateCard_When_InvalidRequest() throws Exception {
        var cartcaoDTO = new CartcaoDTO();

        cartcaoDTO.setNumeroCartao("6549873025634501");
        cartcaoDTO.setSenha("1234");
        when(cartoesServices.criarCartao(any(CartcaoDTO.class))).thenReturn(cartcaoDTO);
        when(cartaoRepository.findByNumeroCartao(cartcaoDTO.getNumeroCartao())).thenReturn(Optional.of(new Cartao()));

        mockMvc.perform(post("/cartoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(cartcaoDTO)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.numeroCartao").value("6549873025634501"))
                .andExpect(jsonPath("$.senha").value("1234"));
    }

//    @Test
//    public void testConsultarCartao() throws Exception {
//        when(cartoesServices.consultarSaldo(anyString())).thenReturn(Double.valueOf(0));
//
//        ResponseEntity<Double> result = cartoesControllers.consultarCartao("numeroCartao");
//        Assert.assertEquals(null, result);
//    }
//
//    @Test
//    public void testRealizarTransacao() throws Exception {
//        ResponseEntity<String> result = cartoesControllers.realizarTransacao(new Transacoes());
//        Assert.assertEquals(null, result);
//    }
}

