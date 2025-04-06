package service.entity;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Grafo rede = new Grafo();
        Random random = new Random();
        List<UsuarioEntity> todosUsuarios = new ArrayList<>();

        // criando 100 usuarios de maneira aleatoria
        for (int i = 1; i <= 100; i++) {
            String nome = "User_" + String.format("%03d", i);
            UsuarioEntity usuario = new UsuarioEntity(nome);
            rede.setUsuarios(usuario);
            todosUsuarios.add(usuario);
        }

        System.out.println(" 100 usuários criados com sucesso.\n");

        // gerando conexões aleatorias
        for (UsuarioEntity origem : todosUsuarios) {
            int numConexoes = random.nextInt(3) + 3; // de 3 a 5 conexões

            Set<UsuarioEntity> conectados = new HashSet<>();

            while (conectados.size() < numConexoes) {
                UsuarioEntity destino = todosUsuarios.get(random.nextInt(todosUsuarios.size()));

                if (!destino.equals(origem) && !conectados.contains(destino)) {
                    double peso = 0.1 + (1.0 - 0.1) * random.nextDouble(); // variação de pesos
                    peso = Math.round(peso * 10.0) / 10.0; //
                    rede.conectarUsuarios(origem, destino, peso);
                    conectados.add(destino);
                }
            }
        }

        // mostrar rede
        System.out.println("📡 Rede de confiança gerada:\n");
        rede.mostrarRede();
        UsuarioEntity userA = todosUsuarios.get(0);
        UsuarioEntity userB = todosUsuarios.get(5);

// antes
        double antes = rede.calcularNivelDeConfianca(userA, userB);
        System.out.println("Confianca antes: " + antes);

// add close friends
        rede.adicionarAmigoProximo(userA, userB);
        System.out.println("Adicionado como amigo próximo!");

// teste | nivel de confiança depois
        double depois = rede.calcularNivelDeConfianca(userA, userB);
        System.out.println("Confianca depois: " + depois);
    }
}
