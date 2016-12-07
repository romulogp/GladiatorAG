package br.com.rgp.ag;

import br.com.rgp.ag.modelo.AlgoritmoGenetico;

/**
 *
 * @author Rômulo Göelzer Portolann <romulogoelzer@gmail.com>
 */
public class RunApplication {

    private static final int MINIMO_VITORIAS = 20;
    private static final int MAX_GERACOES = 100;
    private static final int POPULACAO_INICIAL = 10;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AlgoritmoGenetico ag = new AlgoritmoGenetico(MINIMO_VITORIAS, MAX_GERACOES, POPULACAO_INICIAL);
        ag.executar();
    }
}
