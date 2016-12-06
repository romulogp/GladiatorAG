package br.com.rgp.ag.metodos;

import br.com.rgp.ag.geradores.GeradorAleatoriedade;
import br.com.rgp.ag.modelo.Gladiador;
import java.util.ArrayList;
import java.util.List;

public class MetodoRoleta {

  public static List<Gladiador> rodar(List<Gladiador> populacao, int maxCandidatos) {
    if (populacao.size() <= maxCandidatos) {
      return populacao;
    }
    
    int valorMaximo = 0;
    // adiciona aquelas que tem pelo menos uma vitoria
    List<Gladiador> possiveisCandidatos = new ArrayList<>();
    for (Gladiador g : populacao) {
      if (g.getVitorias() > 0) {
        possiveisCandidatos.add(g);
        valorMaximo += g.getVitorias();
      }
    }
    
    // randomiza um dos individuos de acordo com sua probabilidade de ser escolhido
    // ex.: Glad01 venceu 5 dos 10 duelos, ou seja possui 50% de chance de ser selecionado
    // Glad01: 1-5  (1,2,3,4,5)
    // Glad02: 6-7  (6,7)
    // Glad03: 8-10 (8,9,10)
    List<Gladiador> candidatos = new ArrayList<>();
    while (possiveisCandidatos.size() < maxCandidatos) {  
      int randVal = GeradorAleatoriedade.gerarNumeroAleatorioEntre(1, valorMaximo);
      for (Gladiador g : possiveisCandidatos) {
        if (randVal <= g.getVitorias()) {
          valorMaximo -= g.getVitorias();
          candidatos.add(g);
          possiveisCandidatos.remove(g);
        }
        randVal -= g.getVitorias();
      }
    }
    return candidatos;
  }
  
}
