package br.com.itau;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.itau.controller.UsuarioController;
import br.com.itau.model.Usuario;
import br.com.itau.service.UsuarioService;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class ItauApplicationTests {
	
	@MockBean
	private UsuarioService usuarioService;

	@Autowired
	UsuarioController usuarioController;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void usuarioSenhaValida() throws Exception {
		Usuario user = new Usuario(1L, "AbTp9!fok");
		when(usuarioService.salvar(any())).thenReturn(user);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/usuarios")
				.content("{\"id\": \"1\",\"senha\": \"AbTp9!fok\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	public void usuarioSenhaInValida() throws Exception {
		Usuario user = new Usuario(1L, "AbTp9!fo");
		when(usuarioService.salvar(any())).thenReturn(user);

		mockMvc.perform(MockMvcRequestBuilders.post("/usuarios").content("{\"id\": \"1\",\"senha\": \"AbTp9!fok\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

}
