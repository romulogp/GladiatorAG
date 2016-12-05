package br.com.rgp.ag;

import br.com.rgp.ag.gerador.GeradorPopulacao;
import br.com.rgp.ag.modelo.AlgoritmoGenetico;
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
//    List<Gladiador> populacao = GeradorPopulacao.gerarPopulacao(100);
//    for (Gladiador p : populacao) {
//      for (int i = 0; i < p.getPontos(); i++) {
//        char block = '\u2588';
//        System.out.print(block);
//      }
//      System.out.println("");
//    }
    AlgoritmoGenetico ag = new AlgoritmoGenetico(10, 3);
    ag.executar();
  }
}
