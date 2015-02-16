/**
 * 
 */
package com.master.bov.cotacao;

/**
 * @author Vilmar.Ricken
 * 
 */
public class Cotacao implements Comparable<Cotacao> {

    private static final int T = (int) (System.nanoTime() % 99) + 100;
    private final int t;

    /**
     * @param i
     */
    public Cotacao(final int i) {
        this.t = Cotacao.T + i;
    }

    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(final Cotacao o) {
        if (o == null) {
            return -1;
        }
        return this.t - o.t;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Cotação: " + this.t;
    }

    /**
     * @return
     */
    public int getValue() {
        return this.t;
    }

}
