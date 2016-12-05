package br.com.rgp.ag.modelo;

import br.com.rgp.ag.gerador.GeradorPopulacao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rômulo Göelzer Portolann <romulogoelzer@gmail.com>
 */
public class AlgoritmoGenetico {
  
  private Gladiador melhorCandidato = new Gladiador();
  
  private int maxGeracoes;
  private List<Gladiador> populacao = new ArrayList<>();
  
  public AlgoritmoGenetico(int maxGeracoes, int tamPopulacao) {
    this.maxGeracoes = maxGeracoes;
    populacao = GeradorPopulacao.gerarPopulacao(tamPopulacao);
  }
  
  public void executar() {
    int geracaoAtual = 1;
    while (geracaoAtual <= maxGeracoes) {
      System.out.println("****************************************************"
              + "\n ||| " + geracaoAtual + "ª NOVA GERAÇÃO ||| \n"
              + "****************************************************");
      Arena arena = new Arena(populacao);
      arena.iniciarDuelos();
      System.out.println("----------------------------------------------------"
              + "\n *** Gladiador com maior número de vitórias: " + arena.getVencedor().getNome() + " ***\n"
              + "----------------------------------------------------");
      geracaoAtual++;
    }
  }
  
}
