
package es.alejandrosalazargonzalez.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
/**
 *   @author: alejandrosalazargonzalez
 *   @version: 1.0.0
 */
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    /**
     * inicia la bbdd
     * @param userRepository
     */
    public void setUsuarioRepository(UsuarioRepository userRepository) {
        this.usuarioRepository = userRepository;
    }

    /**
     * retorna todos los usuarios de la bbdd
     * @return List<Usuario>
     */
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    /**
     * busca un usuario por su id
     * @param userId id del usuario a buscar
     * @return Usuario
     */
    public Usuario getUsuarioById(int userId) {
        return usuarioRepository.findById(userId).orElse(null);
    }

    /**
     * agrega un usuario a bbdd
     * @param user a agregar
     * @return Usuario
     */
    public Usuario createUsuario(Usuario user) {
        if (user.getName()==null||user.getName().isEmpty()) {
        throw new RuntimeException("el usuario no tiene nombre");
        }
        return usuarioRepository.save(user);
    }

    /**
     * actualiza la indormacion de un usuario
     * @param userId id del usuario
     * @param userDetails usuario actualizado
     * @return Usuario
     */
    public Usuario updateUsuario(int userId, Usuario userDetails) {
        if (userDetails.getName()== null) {
        throw new RuntimeException("la nueva informacion no tiene nombre");
        }
        if (userId < 0) {
        throw new RuntimeException("id no valido");
        }
        
        Usuario user = usuarioRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setName(userDetails.getName());
            return usuarioRepository.save(user);
        }
        throw new RuntimeException("no existe el usuario a actualizar");
    }

    /**
     * elimnina a un usuario de la bbdd
     * @param userId id del usuario a eliminar
     */
    public void deleteUsuario(int userId) {
        if (usuarioRepository.existsById(userId)) {
            usuarioRepository.deleteById(userId);
        }else{
            throw new RuntimeException("no exite el usuario a eliminar");
        }
    }
}
