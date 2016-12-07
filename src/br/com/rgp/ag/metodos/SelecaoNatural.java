package br.com.rgp.ag.metodos;

import br.com.rgp.ag.modelo.Individuo;
import java.util.List;

public class SelecaoNatural {
  
  /**
   * 
   * @param populacao
   * @return o individuo com o menor numero de vitorias e pontos
   */
  public static Individuo selecionarInapto(List<Individuo> populacao) {
    Individuo inapto = populacao.get(0);
    for (Individuo i : populacao) {
      if (i.getPontos() < inapto.getPontos()&& i.fitness() < inapto.fitness()) {
        inapto = i;
      }
    }
    return inapto;
  }
  
}
