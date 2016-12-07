package br.com.rgp.ag.modelo;

import java.util.ArrayList;
import java.util.List;

public class Arena {

  private final List<Duelo> duelos = new ArrayList<>();
  
  public Arena(List<Individuo> oponentes) {
    gerarQuadroDeDuelos(oponentes);
    inicializarVitorias(oponentes);
  }
  
  public void iniciarDuelos() {
    duelos.stream().forEach((d) -> {
      d.iniciar();
    });
  }
  
  public Individuo getVencedor() {
    if (duelos.isEmpty()) {
      return null;
    }
    Individuo vencedor = new Individuo();
    for (Duelo d : duelos) {
      if (d.getVencedor() != null) {
        // O vencedor é definido por aquele que tiver maior número de vitórias
        // Obs.: O termo de desempate é o somatório dos atributos
        if (d.getVencedor().getVitorias() > vencedor.getVitorias()
                || d.getVencedor().getPontos() > vencedor.getPontos()) {
          vencedor = d.getVencedor();
        }
      }
    }
    return vencedor;
  }
  
  private void gerarQuadroDeDuelos(List<Individuo> oponentes) {
    for (int i = 0; i < oponentes.size() - 1; i++) {
      for (int j = i + 1; j < oponentes.size(); j++) {
        duelos.add(new Duelo(oponentes.get(i), oponentes.get(j)));
      }
    }
    System.out.println("\n\t*Quadro de Duelos Gerado, totalizando " + duelos.size() + " duelos.");
  }
  
  private void inicializarVitorias(List<Individuo> populacao) {
    populacao.stream().forEach((i) -> {
      i.setVitorias(0);
    });
    System.out.println("\t*Números de vitórias inicializados.");
  }
  
}
