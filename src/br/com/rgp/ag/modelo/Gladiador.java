package br.com.rgp.ag.modelo;

import java.util.ArrayList;
import java.util.List;

public class Gladiador {

  private static final int MIN_ATRIB_VALUE = 10;

  private String nome;
  private Atributo vida;
  private Atributo ataque;
  private Atributo defesa;
  private Atributo destreza;
  private int vitorias;

  private Atributo VIDA_DEFAULT;

  public Gladiador() {
    vida = new Atributo(MIN_ATRIB_VALUE);
    ataque = new Atributo(MIN_ATRIB_VALUE);
    defesa = new Atributo(MIN_ATRIB_VALUE);
    destreza = new Atributo(MIN_ATRIB_VALUE);
  }

  public Gladiador(Atributo vida, Atributo ataque, Atributo defesa, Atributo destreza, int vitorias) {
    this.vida = vida;
    this.ataque = ataque;
    this.defesa = defesa;
    this.destreza = destreza;
    this.vitorias = vitorias;
  }

  public int fitness() {
    return getPontos();
  }
  
  public void atacar(Gladiador oponente) {
    System.out.println(this.nome + " atacou " + oponente.getNome());
    oponente.defender(this);
  }

  public void atingir(Gladiador oponente, int intensidade) {
    int vidaAtual = oponente.getVida().value();
    oponente.getVida().setValor(vidaAtual - intensidade > 0
            ? vidaAtual - intensidade : 0);

    System.out.println(this.nome + " atingiu " + oponente.getNome()
            + " com intensidade de " + intensidade + " pontos de vida, deixando-o com "
            + oponente.getVida() + " pontos de vida");
  }

  /**
   * <p>Soma-se os pontos de ataque do atacante com os pontos de defesa gladiador a
   * se defender, então divide-se a defesa do defensor pelo resultado para obter
   * a porcentagem de redução no dano a ser causado pelo atacante.</p>
   * @param atacante gladiador que realizará o ataque
   */
  public void defender(Gladiador atacante) {
    System.out.println(this.nome + " se defendeu contra " + atacante.getNome());
    double reducao = (this.getDefesa().value() / (double) (this.getDefesa().value() + atacante.getAtaque().value()));
    double danoFinal = atacante.getAtaque().value() * reducao;
    atacante.atingir(this, (int) Math.round(danoFinal));
  }

  public boolean estaVivo() {
    return vida.value() > 0;
  }

  public void renascer() {
    this.vida = new Atributo(VIDA_DEFAULT.value());
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
    total = getAtributos().stream().map((attr) -> attr.value()).reduce(total, Integer::sum);
    return total;
  }

  public void fixarAtributosDefault() {
    this.VIDA_DEFAULT = new Atributo(vida.value());
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
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("\nGladiador ").append(nome)
            .append("\nVida: ").append(vida)
            .append("\nAtaque: ").append(ataque)
            .append("\nDefesa: ").append(defesa)
            .append("\nDestreza: ").append(destreza);
    return builder.toString();
  }
  
}
