package br.com.rgp.ag.metodos;

import br.com.rgp.ag.geradores.GeradorAleatoriedade;
import br.com.rgp.ag.modelo.Individuo;
import java.util.ArrayList;
import java.util.List;

public class MetodoRoleta {

  public static List<Individuo> girar(List<Individuo> populacao) {
    int maxCandidatos = 2;
    if (populacao.size() <= maxCandidatos) {
      return populacao;
    }
    
    int valorMaximo = 0;
    // adiciona aquelas que tem pelo menos uma vitoria
    List<Individuo> possiveisCandidatos = new ArrayList<>();
    for (Individuo g : populacao) {
      if (g.getVitorias() > 0) {
        possiveisCandidatos.add(g);
        valorMaximo += g.getVitorias();
      }
    }
    
    // randomiza um dos individuos de acordo com sua probabilidade de ser escolhido
    // ex.: Glad01 venceu 5 dos 10 duelos, ou seja, tem 50% de chance de ser selecionado
    // Glad01: 1-5  (1,2,3,4,5)
    // Glad02: 6-7  (6,7)
    // Glad03: 8-10 (8,9,10)
    List<Individuo> candidatos = new ArrayList<>();
    while (candidatos.size() < maxCandidatos) {  
      int randVal = GeradorAleatoriedade.gerarNumeroAleatorioEntre(1, valorMaximo);
      for (Individuo g : possiveisCandidatos) {
        if (randVal <= g.getVitorias()) {
          valorMaximo -= g.getVitorias();
          candidatos.add(g);
          possiveisCandidatos.remove(g);
          break;
        }
        randVal -= g.getVitorias();
      }
    }
    return candidatos;
  }
  
}
