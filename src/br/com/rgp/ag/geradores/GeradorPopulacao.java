package br.com.rgp.ag.geradores;

import br.com.rgp.ag.modelo.Individuo;
import java.util.ArrayList;
import java.util.List;

public class GeradorPopulacao {
  
  public static List<Individuo> gerarPopulacao(int populacaoTotal) {
    List<Individuo> populacao = new ArrayList<>();
    for (int i = 0; i < populacaoTotal; i++) {
      Individuo gladiador = GeradorGladiador.obterGladiadorAleatoriamente();
      gladiador.setNome(String.valueOf(i));
      populacao.add(gladiador);
    }
    return populacao;
  }
  
  
}
