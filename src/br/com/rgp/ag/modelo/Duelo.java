package br.com.rgp.ag.modelo;

import br.com.rgp.ag.geradores.GeradorAleatoriedade;
import br.com.rgp.ag.geradores.GeradorGladiador;

public class Duelo {

  private final Individuo g1;
  private final Individuo g2;
  
  private Individuo vencedor;
  
  /**
   * <p>Construtor da classe que define quais são os oponentes e quem iniciará 
   * o duelo, sendo g1 o iniciador.</p>
   * @param oponente1
   * @param oponente2 
   */
  public Duelo(Individuo oponente1, Individuo oponente2) {
    int iniciante = GeradorAleatoriedade.gerarNumeroAleatorioEntre(0, 1);
    g1 = (iniciante == 0) ? oponente1 : oponente2;
    g2 = (iniciante == 0) ? oponente2 : oponente1;
  }
  
  public void iniciar() {
    System.out.println(g1);
    System.out.println(g2);
    
    Individuo gladiadorDaVez = g1;
    Individuo adversarioDaVez = g2;
    
    while (vencedor == null) {
      System.out.println("--> Vez de " + gladiadorDaVez.getNome());
      gladiadorDaVez.atacar(adversarioDaVez);
      
      gladiadorDaVez = (gladiadorDaVez == g1) ? g2 : g1;
      adversarioDaVez = (adversarioDaVez == g1) ? g2 : g1;
      
      vencedor = !g1.estaVivo() ? g2 : !g2.estaVivo() ? g1 : null;
    }
    vencedor.setVitorias(vencedor.getVitorias() + 1);
    System.out.println("\n*** " + g1.getNome() + "  vs  " + g2.getNome() + "  =  " + vencedor.getNome() + " *** \n");
    g1.renascer();
    g2.renascer();
  }
  
  public Individuo getVencedor() {
    return vencedor;
  }
  
  public static void main(String[] args) {
    Individuo op1 = GeradorGladiador.obterGladiadorAleatoriamente();
    op1.setNome("Oponente1");
    System.out.println(op1);
    
    Individuo op2 = GeradorGladiador.obterGladiadorAleatoriamente();
    op2.setNome("Oponente2");
    System.out.println(op2);
    
    System.out.println("");
    Duelo d = new Duelo(op1, op2);
    d.iniciar();
  }
  
}
