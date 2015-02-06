/**
 * 
 */
package com.master.bovespa;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Vilmar.Ricken
 * 
 */
public class Cotacoes {

    private final Set<Cotacao> cotacoes = new TreeSet<Cotacao>();

    private int index;

    private int[] abertura;

    private int[] fechamento;

    private int[] media;

    private Cotacao primeiro;

    private Cotacao ultimo;

    /**
     * 
     */
    public Cotacoes(final int length) {
        this.abertura = new int[length];
        this.fechamento = new int[length];
        this.media = new int[length];
    }

    public Cotacoes() {
    }

    public void add(final Cotacao cotacao) {
        this.cotacoes.add(cotacao);
        if (this.primeiro == null || cotacao.compareTo(this.primeiro) < 0) {
            this.primeiro = cotacao;
        }
        if (this.ultimo == null || cotacao.compareTo(this.ultimo) > 0) {
            this.ultimo = cotacao;
        }
        if (this.index >= this.abertura.length) {
            this.abertura = this.aumentaCapacidade(this.abertura);
            this.fechamento = this.aumentaCapacidade(this.fechamento);
            this.media = this.aumentaCapacidade(this.media);
        }
        this.abertura[this.index] = cotacao.getValue() * 1;
        this.fechamento[this.index] = cotacao.getValue() * 3;
        this.media[this.index] = cotacao.getValue() * 2;
        this.index++;
    }

    /**
     * @param media2
     */
    private int[] aumentaCapacidade(final int[] array) {
        final int novaCapacidade = array.length * 3 / 2 + 1;
        return Arrays.copyOf(array, novaCapacidade);
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(512);
        sb.append("  Primeiro: ").append(this.primeiro).append("\n");
        sb.append("    Último: ").append(this.ultimo).append("\n");
        sb.append("  Abertura: ").append(Arrays.toString(this.getAbertura())).append("\n");
        sb.append("     Média: ").append(Arrays.toString(this.getMedia())).append("\n");
        sb.append("Fechamento: ").append(Arrays.toString(this.getFechamento())).append("\n");
        sb.append(this.cotacoes);
        return sb.toString();
    }

    public static void main(final String[] args) {
        final Cotacoes c = new Cotacoes(10);
        for (int i = 0; i < 20; i++) {
            final Cotacao ct = new Cotacao(i);
            c.add(ct);
            System.out.println(ct);
        }
        System.out.println(c);

    }

    /**
     * @return the abertura
     */
    public int[] getAbertura() {
        return Arrays.copyOf(this.abertura, this.index);
    }

    /**
     * @return the fechamento
     */
    public int[] getFechamento() {
        return Arrays.copyOf(this.fechamento, this.index);
    }

    /**
     * @return the media
     */
    public int[] getMedia() {
        return Arrays.copyOf(this.media, this.index);
    }

}
