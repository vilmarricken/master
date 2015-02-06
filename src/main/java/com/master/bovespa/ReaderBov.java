package com.master.bovespa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReaderBov {

    private LineNumberReader reader;

    private String line;

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

    public ReaderBov(final File file) {
        try {
            this.reader = new LineNumberReader(new FileReader(file));
        } catch (final FileNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public boolean next() {
        try {
            this.line = this.reader.readLine();
            while (this.line != null) {
                final String tipoRegistro = this.getTipoRegistro();
                if (tipoRegistro.equals("01")) {
                    final String codigoDBI = this.getCodigoDBI();
                    //if (codigoDBI.equals("02")) {
                        return true;
                    //}
                }
                this.line = this.reader.readLine();
            }
            this.line = null;
            return false;
        } catch (final IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public String getCodigoNegociacao() {
        return this.getString(12, 24);
    }

    public String getNomeEmpresa() {
        return this.getString(27, 39);
    }

    public String getEspecificacao() {
        return this.getString(39, 49);
    }

    public int getTotalNegocios() {
        return Integer.parseInt(this.getString(147, 152));
    }

    public long getQuantidadeTotal() {
        return Long.parseLong(this.getString(152, 170));
    }

    public int getCorrecaoPrecoExercicio() {
        return Integer.parseInt(this.getString(201, 202));
    }

    public int getPrazoTermo() {
        return Integer.parseInt(this.getString(49, 52));
    }

    public String getMoeda() {
        return this.getString(52, 56);
    }

    public double getVolumeTotal() {
        return this.getDouble(170, 188);
    }

    public double getPrecoExercicio() {
        return this.getDouble(188, 201);
    }

    public double getPrecoMaximo() {
        return this.getDouble(69, 82);
    }

    public double getPrecoMinimo() {
        return this.getDouble(82, 95);
    }

    public double getPrecoMedio() {
        return this.getDouble(95, 108);
    }

    public double getPrecoUltimo() {
        return this.getDouble(108, 121);
    }

    public double getPrecoMelhorOfertaVenda() {
        return this.getDouble(134, 147);
    }

    public double getPrecoMelhorOfertaCompra() {
        return this.getDouble(121, 134);
    }

    public double getPrecoAbertura() {
        return this.getDouble(56, 69);
    }

    private double getDouble(final int start, final int end) {
        String value = this.getString(start, end);
        final int length = value.length() - 2;
        value = value.substring(0, length) + "." + value.substring(length);
        return Double.parseDouble(value);
    }

    public String getTipoMercado() {
        return this.getString(24, 27);
    }

    public String getCodigoDBI() {
        return this.getString(10, 12);
    }

    public Date getDataPregao() {
        return this.getData(2, 10);
    }

    private Date getData(final int start, final int end) {
        final String data = this.getString(start, end);
        try {
            return ReaderBov.format.parse(data);
        } catch (final ParseException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private String getTipoRegistro() {
        return this.getString(0, 2);
    }

    private String getString(final int start, final int end) {
        if (this.line != null) {
            return this.line.substring(start, end).trim();
        }
        throw new RuntimeException("Line is null");
    }

}
