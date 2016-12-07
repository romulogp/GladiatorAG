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
  
  public static int individuosGerados = 0;
  public static int geracaoAtual = 1;
  
  private final int minimoVitorias;
  private final int maxGeracoes;
  
  private Individuo melhorCandidato = new Individuo();
  private List<Individuo> populacao = new ArrayList<>();
  
  public AlgoritmoGenetico(int minimoVitorias, int maxGeracoes, int tamPopulacao) {
    this.minimoVitorias = minimoVitorias;
    this.maxGeracoes = maxGeracoes;
    populacao = GeradorPopulacao.gerarPopulacao(tamPopulacao);
    System.out.println("População inicial gerada com " + populacao.size() + " integrantes.");
    individuosGerados = populacao.size();
  }
  
  public void executar() {
    while (geracaoAtual <= maxGeracoes && !alcancouMinimoVitorias()) {
      System.out.println(
                "********************************************************************************************************"
              + "\n ||| " + geracaoAtual + "ª GERAÇÃO ||| \n"
              + "********************************************************************************************************");
      
      exibePopulacao();
      
      Arena arena = new Arena(populacao);
      arena.iniciarDuelos();
      
      encontraMelhorCandidato();
      
      List<Individuo> pais = MetodoRoleta.girar(populacao);
      List<Individuo> filhos = pais.get(0).cruzar(pais.get(1));
      populacao.addAll(filhos);
      
      Individuo inapto = SelecaoNatural.selecionarInapto(populacao);
      System.out.println("\n" + inapto + "\n foi removido pois não está apto a continuar.");
      populacao.remove(inapto);
      
      System.out.println(
                "----------------------------------------------------"
              + "\n *** Arena Finalizada. ***"
              + "\nIndivíduo com maior número de vitórias: " + arena.getVencedor().getNome() + " ***" 
              + "\n"
              + "----------------------------------------------------");
      geracaoAtual++;
    }
    System.out.println(
                "----------------------------------------------------"
              + "\n *** ALGORITMO GENETICO FINALIZADO. ***"
              + "\n\t Melhor Candidato: " + melhorCandidato.getNome()
              + "\n\t Vitórias: " + melhorCandidato.getVitorias()
              + "\n\t Geração de Criação: " + melhorCandidato.getGeracaoCriacao()
              + "\n\t Geração Atual: " + geracaoAtual
              + "\n\t Atributos: "
              + "\n\t   > Vida: " + melhorCandidato.getVida().getValor()
              + "\n\t   > Ataque: " + melhorCandidato.getAtaque().getValor()
              + "\n\t   > Defesa: " + melhorCandidato.getDefesa().getValor()
              + "\n\t   > Destreza: " + melhorCandidato.getDestreza().getValor()
              + "\n----------------------------------------------------");
  }
  
  private void exibePopulacao() {
    for (Individuo ind : populacao) {
      System.out.println(ind);
      System.out.println("---------------");
    }
  }
  
  private void encontraMelhorCandidato() {
    for (Individuo i : populacao) {
      System.out.println(i.getNome() + " vitórias: " + i.getVitorias());
      if (i.getVitorias() > melhorCandidato.getVitorias()) {
        melhorCandidato = i;
      }
    }
  }
  
  private boolean alcancouMinimoVitorias() {
    return melhorCandidato.getVitorias() >= minimoVitorias;
  }
  
}
