package br.com.rgp.ag.modelo;

import java.util.ArrayList;
import java.util.List;

public class Arena {

  private final List<Duelo> duelos = new ArrayList<>();
  
  public Arena(List<Gladiador> oponentes) {
    gerarQuadroDeDuelos(oponentes);
  }
  
  public void iniciarDuelos() {
    duelos.stream().forEach((d) -> {
      d.iniciarDuelo();
    });
  }
  
  public Gladiador getVencedor() {
    if (duelos.size() == 0) {
      return null;
    }
    Gladiador vencedor = new Gladiador();
    for (Duelo d : duelos) {
      if (d.getVencedor() != null) {
        if (d.getVencedor().getVitorias() > vencedor.getVitorias()
                || d.getVencedor().fitness() > vencedor.fitness()) {
          vencedor = d.getVencedor();
        }
      }
    }
    return vencedor;
  }
  
  private void gerarQuadroDeDuelos(List<Gladiador> oponentes) {
    for (int i = 0; i < oponentes.size() - 1; i++) {
      for (int j = i + 1; j < oponentes.size(); j++) {
        duelos.add(new Duelo(oponentes.get(i), oponentes.get(j)));
      }
    }
    System.out.println("Gerado Quadro de Duelos, totalizando " + duelos.size() + " duelos.");
  }
  
}
