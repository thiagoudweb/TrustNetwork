package service.entity;
import java.util.*;


public class Grafo {

    // array com a lista de usuarios e suas conexões
    private List<UsuarioEntity> usuarios;

    // inicia o grafo
    public Grafo() {
        this.usuarios = new ArrayList<>();
    }

    public List<UsuarioEntity> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(UsuarioEntity user) {
        usuarios.add(user);
    }

    public void conectarUsuarios(UsuarioEntity origem, UsuarioEntity destino, double peso) {
        origem.adicionarConexao(destino, peso);
    }


}



