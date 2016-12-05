package br.com.rgp.ag.gerador;

import java.util.Random;

public class GeradorAleatoriedade {

  /**
   * <p>Gera um número aleário entre o intervalo especificado, incluindo o mínimo<p>
   * @param minimo 
   * @param maximo 
   * @return número aleatório entre minimo >= x <= maximo
   */
  public static int gerarNumeroAleatorioEntre(int minimo, int maximo) {
    Random r = new Random();
    return r.nextInt((maximo - minimo) + 1) + minimo;
  }
  
  public static void testarAleatoriedade(int minimo, int maximo, int numTestes) {
    int[] count = new int[maximo + 1];
    for (int i = 0; i  < numTestes; i++) {
      count[GeradorAleatoriedade.gerarNumeroAleatorioEntre(minimo, maximo)]++;
    }
    for (int i = minimo; i <= maximo; i++) {
      for (int j = minimo; j < count[i]; j++) {
        System.out.print("|");
      }
      System.out.println("");
    }
  }
  
}
