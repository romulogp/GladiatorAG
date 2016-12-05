package br.com.rgp.ag.modelo;

import br.com.rgp.ag.gerador.GeradorAleatoriedade;
import br.com.rgp.ag.gerador.GeradorGladiador;

public class Duelo {

  private final Gladiador g1;
  private final Gladiador g2;
  
  private Gladiador vencedor;
  
  /**
   * <p>Construtor da classe que define quais são os oponentes e quem iniciará 
   * o duelo, sendo g1 o iniciador.</p>
   * @param oponente1
   * @param oponente2 
   */
  public Duelo(Gladiador oponente1, Gladiador oponente2) {
    int iniciante = GeradorAleatoriedade.gerarNumeroAleatorioEntre(0, 1);
    g1 = (iniciante == 0) ? oponente1 : oponente2;
    g2 = (iniciante == 0) ? oponente2 : oponente1;
  }
  
  public void iniciarDuelo() {
    System.out.println(g1);
    System.out.println(g2);
    
    Gladiador gladiadorDaVez = g1;
    Gladiador adversarioDaVez = g2;
    
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
  
  public Gladiador getVencedor() {
    return vencedor;
  }
  
  public static void main(String[] args) {
    Gladiador op1 = GeradorGladiador.obterGladiadorAleatoriamente();
    op1.setNome("Oponente1");
    System.out.println(op1);
    
    Gladiador op2 = GeradorGladiador.obterGladiadorAleatoriamente();
    op2.setNome("Oponente2");
    System.out.println(op2);
    
    System.out.println("");
    Duelo d = new Duelo(op1, op2);
    d.iniciarDuelo();
  }
  
}
