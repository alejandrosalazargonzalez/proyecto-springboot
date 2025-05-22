package es.alejandrosalazargonzalez.usuario;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
/**
 *   @author: alejandrosalazargonzalez
 *   @version: 1.0.0
 */
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name", nullable = false)
	private String name;
	
    /**
     * constructor vacio
     */
	public Usuario() {
	}
	
    /**
     * constructor con el nombre
     * @param name nombre del usuario
     */
	public Usuario(String name) {
		this.name = name;
	}
	
    //getters y setters
	public int getId() {
		return id;
	}
	
    public void setId(int id) {
		this.id = id;
	}
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", name=" + name + "]";
	}
}
