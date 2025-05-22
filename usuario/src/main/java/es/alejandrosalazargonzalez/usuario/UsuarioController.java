package es.alejandrosalazargonzalez.usuario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
/**
 *   @author: alejandrosalazargonzalez
 *   @version: 1.0.0
 */
public class UsuarioController {

    private UsuarioService usuarioService;

    @Autowired
    /**
     * inicia la base de datos
     * @param userService a iniciar
     */
    public void setUsuarioRepository(UsuarioService userService) {
        this.usuarioService = userService;
    }

    @GetMapping("/users/")
    /**
     * recoge todos los usuarios de la bbdd
     * @return List<Usuario>
     */
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }


    @GetMapping("/user/{id}")
    /**
     * busca un usuario por la id
     * @param userId id del usuario buscado
     * @return ResponseEntity<Usuario>
     */
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable(value = "id") int userId) {
        Usuario user = usuarioService.getUsuarioById(userId);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario con id:" + userId + " no existe");
        }
        return ResponseEntity.ok().body(user);
    }


    @PostMapping("/add/user/")
    /**
     * agrega un usuario a la bbdd
     * @param user a agregar
     * @return Usuario
     */
    public Usuario createUsuario(@Valid @RequestBody Usuario user) {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario no puede ser nulo");
        }
        if (user.getName()==null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre del usuario no puede ser nulo");
        }
        if (user.getId()!=0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No debe de poner un id");
        }
        return usuarioService.createUsuario(user);
    }


    @PutMapping("/update/user/{id}")
    /**
     * actualiza la informacion de un usuario
     * @param userId del usuario al que se le quiere actualizar la informacion
     * @param userDetails usuario con la nueva informacion
     * @return ResponseEntity<Usuario>
     */
    public ResponseEntity<Usuario> updateUsuario(@PathVariable(value = "id") int userId, @Valid @RequestBody Usuario userDetails) {
        final Usuario updatedUsuario = usuarioService.updateUsuario(userId, userDetails);
        return ResponseEntity.ok(updatedUsuario);
    }


    @DeleteMapping("/delete/user/{id}")
    /**
     * elimina un usuario de la bbdd
     * @param userId id del usuario a eliminar
     * @return Map<String, Boolean>
     */
    public Map<String, Boolean> deleteUsuario(@PathVariable(value = "id") int userId) {
        usuarioService.deleteUsuario(userId); 
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
