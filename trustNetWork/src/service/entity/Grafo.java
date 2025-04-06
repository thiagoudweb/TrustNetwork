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

    public void mostrarRede() {
        for (UsuarioEntity u : usuarios) {
            System.out.print(u.getNome() + " → ");
            for (Map.Entry<UsuarioEntity, Double> entry : u.getConexoes().entrySet()) {
                System.out.print(entry.getKey().getNome() + " (" + entry.getValue() + ") ");
            }
            System.out.println(); // para quebrar linha entre usuários
        }
    }

    // codigo que encontre para calcular o nivel de confiança entre dois usuarios ( inverti o codigo pq djsktra trabalha com menor caminho ( peso ) e aq queremos o maior
    public double calcularNivelDeConfianca(UsuarioEntity origem, UsuarioEntity destino) {
        Map<UsuarioEntity, Double> confianca = new HashMap<>();
        PriorityQueue<UsuarioEntity> fila = new PriorityQueue<>(Comparator.comparingDouble(confianca::get).reversed());
        Set<UsuarioEntity> visitados = new HashSet<>();

        for (UsuarioEntity u : usuarios) {
            confianca.put(u, Double.NEGATIVE_INFINITY);
        }

        confianca.put(origem, 1.0); // Começamos com confiança máxima
        fila.add(origem);

        while (!fila.isEmpty()) {
            UsuarioEntity atual = fila.poll();
            visitados.add(atual);

            for (Map.Entry<UsuarioEntity, Double> entry : atual.getConexoes().entrySet()) {
                UsuarioEntity vizinho = entry.getKey();
                double peso = entry.getValue();

                if (!visitados.contains(vizinho)) {
                    double novaConfianca = Math.min(confianca.get(atual), peso);

                    if (novaConfianca > confianca.get(vizinho)) {
                        confianca.put(vizinho, novaConfianca);
                        fila.remove(vizinho);
                        fila.add(vizinho);
                    }
                }
            }
        }

        return confianca.get(destino); // retorna o nível de confiança de origem até destino
    }

    // aumentar a confiança ao adicionar como amigo
    public void adicionarAmigo(UsuarioEntity origem, UsuarioEntity destino) {
        double pesoAtual = origem.getConexoes().getOrDefault(destino, 0.0);
        double novoPeso = Math.min(pesoAtual + 0.2, 1.0);
        origem.adicionarConexao(destino, novoPeso);
    }

    // aumentar mais ainda ao adicionar como amigo próximo
    public void adicionarAmigoProximo(UsuarioEntity origem, UsuarioEntity destino) {
        double pesoAtual = origem.getConexoes().getOrDefault(destino, 0.0);
        double novoPeso = Math.min(pesoAtual + 0.5, 1.0);
        origem.adicionarConexao(destino, novoPeso);
    }

    // aemover amizade (ou reduzir peso)
    public void removerAmigo(UsuarioEntity origem, UsuarioEntity destino) {
        origem.getConexoes().remove(destino);
    }


}



