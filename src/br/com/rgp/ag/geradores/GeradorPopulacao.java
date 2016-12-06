package br.com.rgp.ag.geradores;

import br.com.rgp.ag.modelo.Gladiador;
import java.util.ArrayList;
import java.util.List;

public class GeradorPopulacao {
  
  public static List<Gladiador> gerarPopulacao(int populacaoTotal) {
    List<Gladiador> populacao = new ArrayList<>();
    for (int i = 0; i < populacaoTotal; i++) {
      Gladiador gladiador = GeradorGladiador.obterGladiadorAleatoriamente();
      gladiador.setNome(String.valueOf(i));
      populacao.add(gladiador);
    }
    System.out.println("Nova População gerada com " + populacao.size() + " integrantes.");
    return populacao;
  }
  
  
}
