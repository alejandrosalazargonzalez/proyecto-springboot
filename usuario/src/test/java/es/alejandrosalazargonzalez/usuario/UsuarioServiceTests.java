package es.alejandrosalazargonzalez.usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.util.Assert;


//@SpringBootTest
@MockitoBean
class UsuarioServiceTests {
	@Mock
	UsuarioRepository usuarioRepositoryMock;
	Usuario usuario;
	UsuarioService usuarioService;

	@BeforeEach
	void beforeEach(){
		usuarioRepositoryMock = mock(UsuarioRepository.class);
		usuario = new Usuario();
		usuarioService = new UsuarioService();
		usuarioService.setUsuarioRepository(usuarioRepositoryMock);
		when(usuarioRepositoryMock.save(any(Usuario.class))).thenReturn(usuario);
		when(usuarioRepositoryMock.findById(anyInt())).thenReturn(Optional.of(usuario));
	}

	@Test
	void createUsuarioTest(){
		try {
			usuario = new Usuario("miguel");
			Usuario usuarioSave = usuarioService.createUsuario(usuario);
			assertNotNull(usuarioSave);
		} catch (Exception e) {
			fail("se a producido un error en la ejecucion del test");
		}
	}

	@Test
	void createUsuarioNoNombreTest() {
		try {
			usuarioService.createUsuario(usuario);
		} catch (Exception e) {
			assertTrue(e.getMessage().contains("nombre"));
		}
	}

	@Test
	void updateUsuarioTest(){
		try {
			usuario = new Usuario("miguel");
			Usuario miguel = usuarioService.createUsuario(usuario);
			Usuario usuarioSave = usuarioService.updateUsuario(1, new Usuario("manola"));
			assertEquals(usuarioSave.getName(), "manola");
		} catch (Exception e) {
			fail("se ha producido un error en el test");
		}
	}

	@Test
	void updateUsuarioNoNombreTest(){
		try {
			usuario = new Usuario("");
			Usuario miguel = usuarioService.createUsuario(usuario);
			Usuario usuarioSave = usuarioService.updateUsuario(1, new Usuario("manola"));
		} catch (Exception e) {
			assertTrue(e.getMessage().contains("nombre"));

		}
	}

}
