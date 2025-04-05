package service.entity;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UsuarioEntity {

        private String nome;
        // Lista com usuarios ( chaves ) e o peso entre eles.
        private Map<UsuarioEntity, Double> conexoes;

        public UsuarioEntity() {
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
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEntity that = (UsuarioEntity) o;
        return Objects.equals(nome, that.nome) && Objects.equals(conexoes, that.conexoes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, conexoes);
    }

    @Override
        public String toString() {
            return nome;
        }
    }



