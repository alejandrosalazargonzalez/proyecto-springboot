package es.alejandrosalazargonzalez.usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.util.Assert;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
//@SpringBootTest
@MockitoBean
class UsuarioControllerTests {
	@Mock
	UsuarioService usuarioServiceMock;
	Usuario usuario;
	UsuarioController usuarioController;

	@BeforeEach
	void beforeEach(){
		usuarioServiceMock = mock(UsuarioService.class);
		usuario = new Usuario();
		usuarioController = new UsuarioController();
		usuarioController.setUsuarioRepository(usuarioServiceMock);
		when(usuarioServiceMock.createUsuario(any(Usuario.class))).thenReturn(usuario);
	}

	@Test
	void createUsuarioTest(){
		try {
			usuario = new Usuario("miguel");
			Usuario usuarioSave = usuarioController.createUsuario(usuario);
			assertNotNull(usuarioSave);
		} catch (Exception e) {
			fail("se a producido un error en la ejecucion del test");
		}
	}

	@Test
	void createUsuarioNoNombreTest() {
		try {
			usuarioController.createUsuario(usuario);
		} catch (Exception e) {
			assertTrue(e.getMessage().contains("nombre"));
		}
	}

}
