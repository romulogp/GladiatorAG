package br.com.rgp.ag.geradores;

import br.com.rgp.ag.modelo.Atributo;
import br.com.rgp.ag.modelo.Individuo;
import java.util.List;

public class GeradorGladiador {
  
  /**
   * <p>Gera um gladiador com os pontos de atributos distribuidos aleatoriamente.</p>
   * <p>O somatório dos pontos gerados será de 100 no total, sendo estes distribuidos entre
   * todos os atributos do gladiador.</p>
   * <p>Um atributo poderá ser de 0 a 100</p>
   * @return gladiador gerado aleatoriamente.
   */
  public static Individuo obterGladiadorAleatoriamente() {
    Individuo g = new Individuo();
    List<Atributo> atributos = g.getAtributos();
    
    int pontosDistribuiveis = 100;
    while (atributos.size() > 0) {
      // Define qual será o atributo e o seu valor a ser preenchido 
      int randPos = GeradorAleatoriedade.gerarNumeroAleatorioEntre(0, atributos.size() - 1);
      int randVal = GeradorAleatoriedade.gerarNumeroAleatorioEntre(0, pontosDistribuiveis);
      
      // Incrementa o valor do atributo e remove da lista para não preenchê-lo novamente
      atributos.get(randPos).setValor(atributos.get(randPos).getValor() + randVal);
      atributos.remove(randPos);
      
      pontosDistribuiveis -= randVal;
    }
    g.fixarAtributosDefault();
    return g;
  }
    
}
