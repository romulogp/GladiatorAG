package br.com.rgp.ag.metodos;

import br.com.rgp.ag.modelo.Individuo;

public class SelecaoNatural {

  /**
   * 
   * @param g1 
   * @param g2
   * @return o pior entre os 2 gladiadores
   */
  public static Individuo selecionarInapto(Individuo g1, Individuo g2) {
    return g1.getPontos() < g2.getPontos() ? g1 : g2;
  }
  
}
