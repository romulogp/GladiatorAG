package br.com.rgp.ag.modelo;

import br.com.rgp.ag.geradores.GeradorPopulacao;
import br.com.rgp.ag.metodos.MetodoRoleta;
import br.com.rgp.ag.metodos.SelecaoNatural;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rômulo Göelzer Portolann <romulogoelzer@gmail.com>
 */
public class AlgoritmoGenetico {
  
  private final int maxGeracoes;
  private final Individuo melhorCandidato = new Individuo();
  
  private List<Individuo> populacao = new ArrayList<>();
  
  public AlgoritmoGenetico(int maxGeracoes, int tamPopulacao) {
    this.maxGeracoes = maxGeracoes;
    populacao = GeradorPopulacao.gerarPopulacao(tamPopulacao);
  }
  
  public void executar() {
    int geracaoAtual = 1;
    while (geracaoAtual <= maxGeracoes) {
      System.out.println(
                "****************************************************"
              + "\n ||| " + geracaoAtual + "ª NOVA GERAÇÃO ||| \n"
              + "****************************************************");
      
      Arena arena = new Arena(populacao);
      arena.iniciarDuelos();
      
      List<Individuo> pais = MetodoRoleta.girar(populacao);
      List<Individuo> filhos = pais.get(0).cruzar(pais.get(1));
      populacao.addAll(filhos);
      
      Individuo inapto = SelecaoNatural.selecionarInapto(pais.get(0), pais.get(1));
      populacao.remove(inapto);
      
      System.out.println(
                "----------------------------------------------------"
              + "\n *** Gladiador com maior número de vitórias: " + arena.getVencedor().getNome() + " ***\n"
              + "----------------------------------------------------");
      geracaoAtual++;
    }
  }
  
  
  
}
