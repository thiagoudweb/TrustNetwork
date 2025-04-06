package service.entity;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UsuarioEntity {

    private String nome;
    private Map<UsuarioEntity, Double> conexoes;

    public UsuarioEntity(String nome) {
        this.nome = nome;
        this.conexoes = new HashMap<>();
    }

    public String getNome() {
        return nome;
    }

    public Map<UsuarioEntity, Double> getConexoes() {
        return conexoes;
    }

    public void adicionarConexao(UsuarioEntity outro, double peso) {
        conexoes.put(outro, peso);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioEntity)) return false;
        UsuarioEntity that = (UsuarioEntity) o;
        return Objects.equals(nome, that.nome); //
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome); //
    }

    @Override
    public String toString() {
        return nome;
    }
}
