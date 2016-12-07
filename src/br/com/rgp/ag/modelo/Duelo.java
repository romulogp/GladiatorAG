package br.com.rgp.ag.modelo;

import br.com.rgp.ag.geradores.GeradorAleatoriedade;
import br.com.rgp.ag.geradores.GeradorGladiador;

public class Duelo {

  private final Individuo indiv1;
  private final Individuo indiv2;
  
  private Individuo vencedor;
  
  /**
   * <p>Construtor da classe que define quais são os oponentes e quem iniciará 
   * o duelo, sendo g1 o iniciador.</p>
   * @param oponente1
   * @param oponente2 
   */
  public Duelo(Individuo oponente1, Individuo oponente2) {
    int iniciante = GeradorAleatoriedade.gerarNumeroAleatorioEntre(0, 1);
    indiv1 = (iniciante == 0) ? oponente1 : oponente2;
    indiv2 = (iniciante == 0) ? oponente2 : oponente1;
  }
  
  public void iniciar() {
    System.out.println("\n---------------");
    System.out.println("INICIALIZA DUELO...");
    System.out.println(indiv1);
    System.out.println("   VS   ");
    System.out.println(indiv2);
    System.out.println("---------------");
    
    Individuo gladiadorDaVez = indiv1;
    Individuo adversarioDaVez = indiv2;
    
    while (vencedor == null) {
      System.out.println("\n--> Vez de " + gladiadorDaVez.getNome());
      gladiadorDaVez.atacar(adversarioDaVez);
      
      gladiadorDaVez = (gladiadorDaVez == indiv1) ? indiv2 : indiv1;
      adversarioDaVez = (adversarioDaVez == indiv1) ? indiv2 : indiv1;
      
      vencedor = !indiv1.estaVivo() ? indiv2 : !indiv2.estaVivo() ? indiv1 : null;
    }
    vencedor.setVitorias(vencedor.getVitorias() + 1);
    System.out.println("\n*** " + indiv1.getNome() + "  vs  " + indiv2.getNome() + "  =  " + vencedor.getNome() + " *** \n");
    indiv1.renascer();
    indiv2.renascer();
  }
  
  public Individuo getVencedor() {
    return vencedor;
  }
  
}
