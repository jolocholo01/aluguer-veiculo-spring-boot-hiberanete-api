package com.aluguer.carros.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.aluguer.carros.model.Pessoa;
import com.aluguer.carros.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PessoaController.class)
public class PessoaControllerTest {

	private final String BASE_URL = "/pessoas";
	
	@MockBean
	private PessoaService mockService;

	@Autowired
	private MockMvc mvc;

	@Test
	public void deveRetornarSuccesso_quandoBuscaPessoas() throws Exception {
		mvc.perform(get(BASE_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print());
		when(this.mockService.findAll()).thenCallRealMethod();
	}
	@Test
	public void deveRetornarSuccesso_QuandoBuscarPorIdPessoa() throws Exception {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(1l);
		pessoa.setNomeCompleto("Agostinho B. Jolocholo");
		pessoa.setEmail("agostinhojolocholo64@gmail.com");
		pessoa.setCpf("666.333.444-00");
		
		
		when(this.mockService.findById(1l)).thenReturn(pessoa);
		mvc.perform(get(BASE_URL + "/{id}", 1).accept(MediaType.APPLICATION_JSON)).
		
		andDo(print());
	}

	@Test
	public void deveRetornarPessoa_QuandoAdiciona() throws Exception 
	{
		Pessoa pessoa = new Pessoa();
		pessoa.setId(1l);
		pessoa.setNomeCompleto("Agostinho B. Jolocholo");
		pessoa.setEmail("agostinhojolocholo64@gmail.com");
		pessoa.setCpf("666.333.444-00");
		
		mvc.perform(post(BASE_URL)
	      .content(asJsonString(pessoa))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON));
		when(this.mockService.save(pessoa)).thenReturn(pessoa);
	}
	@Test
	public void deveRetornarPessoa_QuandoAtualizar() throws Exception 
	{
		Pessoa pessoa = new Pessoa();
		pessoa.setId(1l);
		pessoa.setNomeCompleto("Agostinho B. Jolocholo");
		pessoa.setEmail("agostinhojolocholo64@gmail.com");
		pessoa.setCpf("666.333.444-00");
		
//		mvc.perform(put(BASE_URL+"/{id}", 1l)
//	      .content(asJsonString(pessoa))
//	      .contentType(MediaType.APPLICATION_JSON)
//	      .accept(MediaType.APPLICATION_JSON));
		when(this.mockService.save(pessoa)).thenReturn(pessoa);
	}
	

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
