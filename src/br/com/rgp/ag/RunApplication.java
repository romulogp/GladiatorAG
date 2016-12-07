package br.com.rgp.ag;

import br.com.rgp.ag.geradores.GeradorPopulacao;
import br.com.rgp.ag.modelo.AlgoritmoGenetico;
import br.com.rgp.ag.modelo.Individuo;
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
    AlgoritmoGenetico ag = new AlgoritmoGenetico(20, 1000, 10);
    ag.executar();
  }
}
