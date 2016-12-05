package br.com.rgp.ag.modelo;

public class Atributo {

  private int valor;

  public Atributo(int valor) {
    this.valor = valor;
  }
  
  @Override
  public String toString() {
    return String.valueOf(this.valor);
  }
  
  public int value() {
    return valor;
  }

  public void setValor(int valor) {
    this.valor = valor;
  }
  
}
