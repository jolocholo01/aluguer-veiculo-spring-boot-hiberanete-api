package com.aluguer.carros.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.aluguer.carros.model.Veiculo;
import com.aluguer.carros.service.VeiculoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(VeiculoController.class)
public class VeiculoControllerTest {

	private final String BASE_URL = "/viaturas";
	
	@MockBean
	private VeiculoService mockService;

	@Autowired
	private MockMvc mvc;

	@Test
	public void deveRetornarSuccesso_quandoBuscaVeiculos() throws Exception {
		when(this.mockService.findAll()).thenCallRealMethod();
		mvc.perform(get(BASE_URL)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print());
	}
	@Test
	public void deveRetornarSuccesso_QuandoBuscarPorIdVeiculo() throws Exception {
		
		Veiculo veiculo = new Veiculo();
		veiculo.setId(1l);
		veiculo.setMarca("MBW 10");
		veiculo.setModelo("Zenza");
		veiculo.setCor("Azul");
		veiculo.setPlaca("Carroçaria");
		veiculo.setValorDiario(BigDecimal.valueOf(1000.00));
		veiculo.setAlugado(false);
		when(this.mockService.findById(1l)).thenReturn(veiculo);
		mvc.perform(get(BASE_URL + "/{id}", 1).accept(MediaType.APPLICATION_JSON)).
		andDo(print());
	}

	@Test
	public void deveRetornarVeiculo_QuandoAdiciona() throws Exception 
	{
		Veiculo veiculo = new Veiculo();
		veiculo.setId(1l);
		veiculo.setMarca("MBW 10");
		veiculo.setModelo("Zenza");
		veiculo.setCor("Azul");
		veiculo.setPlaca("Carroçaria");
		veiculo.setValorDiario(BigDecimal.valueOf(1000.00));
		veiculo.setAlugado(false);
		
		mvc.perform(post(BASE_URL)
	      .content(asJsonString(veiculo))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON));
		when(this.mockService.save(veiculo)).thenReturn(veiculo);
	}
	@Test
	public void deveRetornarVeiculo_QuandoAtualizar() throws Exception 
	{
		Veiculo veiculo = new Veiculo();
		veiculo.setId(1l);
		veiculo.setMarca("MBW 10");
		veiculo.setModelo("Zenza");
		veiculo.setCor("Azul");
		veiculo.setPlaca("Carroçaria");
		veiculo.setValorDiario(BigDecimal.valueOf(1000.00));
		veiculo.setAlugado(false);
		
		mvc.perform(put(BASE_URL+"/{id}", 1l)
	      .content(asJsonString(veiculo))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON));
		when(this.mockService.save(veiculo)).thenReturn(veiculo);
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
