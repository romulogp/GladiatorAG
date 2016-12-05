package br.com.rgp.ag;

import br.com.rgp.ag.gerador.GeradorGladiador;
import br.com.rgp.ag.gerador.GeradorPopulacao;
import br.com.rgp.ag.modelo.Gladiador;
import java.util.List;

/**
 *
 * @author Rômulo Göelzer Portolann <romulogoelzer@gmail.com>
 */
public class RunApplication {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // cria um gladiador aleatoriamente
//    Gladiador g = GeradorGladiador.obterGladiadorAleatoriamente();
//    System.out.println("Pontos: " + g.getPontos());
    
    // gera a população
    List<Gladiador> populacao = GeradorPopulacao.gerar(100);
    for (Gladiador p : populacao) {
      for (int i = 0; i < p.getPontos(); i++) {
        char block = '\u2588';
        System.out.print(block);
      }
      System.out.println("");
    }
  }
}
