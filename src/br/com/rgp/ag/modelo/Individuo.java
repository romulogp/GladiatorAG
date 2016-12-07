package br.com.rgp.ag.modelo;

import br.com.rgp.ag.geradores.GeradorAleatoriedade;
import java.util.ArrayList;
import java.util.List;

public class Individuo {
  
  private static final int MIN_ATRIB_VALUE = 10;
  
  private String nome;
  private Atributo vida;
  private Atributo ataque;
  private Atributo defesa;
  private Atributo destreza;
  private int vitorias;
  private int geracao;

  private Atributo VIDA_DEFAULT;

  public Individuo() {
    vida = new Atributo(MIN_ATRIB_VALUE);
    ataque = new Atributo(MIN_ATRIB_VALUE);
    defesa = new Atributo(MIN_ATRIB_VALUE);
    destreza = new Atributo(MIN_ATRIB_VALUE);
    geracao = 1;
    fixarAtributosDefault();
  }

  public Individuo(Atributo vida, Atributo ataque, Atributo defesa, Atributo destreza, int vitorias, int geracao) {
    this.vida = vida;
    this.ataque = ataque;
    this.defesa = defesa;
    this.destreza = destreza;
    this.vitorias = vitorias;
    this.geracao = geracao;
    fixarAtributosDefault();
  }

  public List<Individuo> cruzar(Individuo pai2) {
    List<Individuo> filhos = new ArrayList<>();
    Individuo filho1 = new Individuo(
            new Atributo(this.vida.getValor()),
            new Atributo(this.ataque.getValor()),
            new Atributo(pai2.defesa.getValor()),
            new Atributo(pai2.destreza.getValor()),
            0,
            new Integer(AlgoritmoGenetico.geracaoAtual));
    Individuo filho2 = new Individuo(
            new Atributo(pai2.vida.getValor()),
            new Atributo(pai2.ataque.getValor()),
            new Atributo(this.defesa.getValor()),
            new Atributo(this.destreza.getValor()),
            0,
            new Integer(AlgoritmoGenetico.geracaoAtual));
    filho1.setNome(String.valueOf(++AlgoritmoGenetico.individuosGerados));
    filho2.setNome(String.valueOf(++AlgoritmoGenetico.individuosGerados));
    
    filhos.add(filho1);
    filhos.add(filho2);
    
    for (Individuo f : filhos) {
      System.out.println(this.getNome() + " cruzou com " + pai2.getNome() + " e geraram " + f.getNome());
      if (sofrerMutacao()) {
        f.mutar();
      }
    }
    return filhos;
  }

  public boolean sofrerMutacao() {
    return GeradorAleatoriedade.gerarNumeroAleatorioEntre(0, 100) <= 10;
  }
  
  public void mutar() {
    // qual atributo sofrerá a mutação?
    Atributo attr = getAtributos().get(GeradorAleatoriedade.gerarNumeroAleatorioEntre(0, this.getAtributos().size() - 1));
    
    System.out.print("\n" + this.getNome() + " sofreu uma mutação");
    
    // para melhor ou pior?
    boolean mutarParaMelhor = GeradorAleatoriedade.gerarNumeroAleatorioEntre(0, 100) > 50;
    if (mutarParaMelhor) {
      System.out.print(" para MELHOR e teve seu atributo alterado de " + attr.getValor());
      attr.setValor(attr.getValor() * 2);
      System.out.print(" para " + attr.getValor());
    } else {
      System.out.print(" para PIOR e teve seu atributo alterado de " + attr.getValor());
      attr.setValor((int) (attr.getValor() / 2.0));
      System.out.print(" para " + attr.getValor() + "\n");
    }
  }
  
  public double fitness() {
    return getPontos();
  }

  public void atacar(Individuo oponente) {
    System.out.println(this.nome + " atacou " + oponente.getNome());
    oponente.defender(this);
  }

  public void atingir(Individuo oponente, int intensidade) {
    int vidaAtual = oponente.getVida().getValor();
    oponente.getVida().setValor(vidaAtual - intensidade > 0
            ? vidaAtual - intensidade : 0);

    System.out.println(this.nome + " atingiu " + oponente.getNome()
            + " com intensidade de " + intensidade + " pontos de vida, deixando-o com "
            + oponente.getVida() + " pontos de vida");
  }
  
  /**
   * <p>
   * Soma-se os pontos de ataque do atacante com os pontos de defesa gladiador a
   * se defender, então divide-se a defesa do defensor pelo resultado para obter
   * a porcentagem de redução no dano a ser causado pelo atacante.</p>
   *
   * @param atacante gladiador que realizará o ataque
   */
  public void defender(Individuo atacante) {
    if (esquivar()) {
      System.out.println(this.nome + " esquivou-se de " + atacante.getNome());
      return;
    }
    double reducao = (this.getDefesa().getValor() / (double) (this.getDefesa().getValor() + atacante.getAtaque().getValor()));
    double danoFinal = Math.round(atacante.getAtaque().getValor() * (1 - reducao));
    System.out.println(this.nome + " defendeu " + (atacante.getAtaque().getValor() - danoFinal) + " pontos de " + atacante.getNome());
    atacante.atingir(this, (int) Math.round(danoFinal));
  }

  public boolean esquivar() {
    int chanceEsquiva = (int) (getDestreza().getValor() * 0.25);
    return GeradorAleatoriedade.gerarNumeroAleatorioEntre(0, 100) <= chanceEsquiva;
  }
  
  public boolean estaVivo() {
    return vida.getValor() > 0;
  }

  public void renascer() {
    this.vida = new Atributo(VIDA_DEFAULT.getValor());
  }

  /**
   * @return lista de atributos do gladiador
   */
  public List<Atributo> getAtributos() {
    List<Atributo> atributos = new ArrayList<>();
    atributos.add(this.vida);
    atributos.add(this.ataque);
    atributos.add(this.defesa);
    atributos.add(this.destreza);
    return atributos;
  }

  public int getPontos() {
    int total = 0;
    total = getAtributos().stream().map((attr) -> attr.getValor()).reduce(total, Integer::sum);
    return total;
  }

  public final void fixarAtributosDefault() {
    this.VIDA_DEFAULT = new Atributo(vida.getValor());
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Atributo getVida() {
    return vida;
  }

  public void setVida(Atributo vida) {
    this.vida = vida;
  }

  public Atributo getAtaque() {
    return ataque;
  }

  public void setAtaque(Atributo ataque) {
    this.ataque = ataque;
  }

  public Atributo getDefesa() {
    return defesa;
  }

  public void setDefesa(Atributo defesa) {
    this.defesa = defesa;
  }

  public Atributo getDestreza() {
    return destreza;
  }

  public void setDestreza(Atributo destreza) {
    this.destreza = destreza;
  }

  public int getVitorias() {
    return vitorias;
  }

  public void setVitorias(int vitorias) {
    this.vitorias = vitorias;
  }

  public int getGeracaoCriacao() {
    return geracao;
  }
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Nome: ").append(nome)
            .append("\nVida: ").append(vida)
            .append("\nAtaque: ").append(ataque)
            .append("\nDefesa: ").append(defesa)
            .append("\nDestreza: ").append(destreza)
            .append("\nVitórias: ").append(vitorias)
            .append("\nGeração: ").append(geracao);
    return builder.toString();
  }

}
