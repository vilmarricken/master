package com.master.bov.cotacao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.management.RuntimeErrorException;

public class ReaderBov {

	private LineNumberReader reader;

	private String line;

	private Date lastDate;

	private static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

	public ReaderBov(final File file, Date lastDate) {
		this.lastDate = lastDate;
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
					final int codigoDBI = this.getCodigoDBI();
					if (codigoDBI == 2 || true) {
						if (this.lastDate == null) {
							return true;
						}
						Date date = getDataPregao();
						if (date.after(lastDate))
							return true;
					}
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
		return this.readString(12, 24);
	}

	public String getNomeEmpresa() {
		return this.readString(27, 39);
	}

	public String getEspecificacao() {
		return this.readString(39, 49);
	}

	public int getTotalNegocios() {
		return Integer.parseInt(this.readString(147, 152));
	}

	public long getQuantidadeTotal() {
		return Long.parseLong(this.readString(152, 170));
	}

	public int getCorrecaoPrecoExercicio() {
		return Integer.parseInt(this.readString(201, 202));
	}

	public int getPrazoTermo() {
		return Integer.parseInt(this.readString(49, 52));
	}

	public String getMoeda() {
		return this.readString(52, 56);
	}

	public double getVolumeTotal() {
		return this.readDouble(170, 188);
	}

	public double getPrecoExercicio() {
		return this.readDouble(188, 201);
	}

	public double getPrecoMaximo() {
		return this.readDouble(69, 82);
	}

	public double getPrecoMinimo() {
		return this.readDouble(82, 95);
	}

	public double getPrecoMedio() {
		return this.readDouble(95, 108);
	}

	public double getPrecoUltimo() {
		return this.readDouble(108, 121);
	}

	public double getPrecoMelhorOfertaVenda() {
		return this.readDouble(134, 147);
	}

	public double getPrecoMelhorOfertaCompra() {
		return this.readDouble(121, 134);
	}

	public double getPrecoAbertura() {
		return this.readDouble(56, 69);
	}

	private double readDouble(final int start, final int end) {
		String value = this.readString(start, end);
		final int length = value.length() - 2;
		value = value.substring(0, length) + "." + value.substring(length);
		return Double.parseDouble(value);
	}

	public int getTipoMercado() {
		return Integer.parseInt(this.readString(24, 27));
	}

	public int getCodigoDBI() {
		return Integer.parseInt(this.readString(10, 12));
	}

	public Date getDataPregao() {
		return this.readData(2, 10);
	}

	private Date readData(final int start, final int end) {
		final String data = this.readString(start, end);
		try {
			return ReaderBov.format.parse(data);
		} catch (final ParseException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private String getTipoRegistro() {
		return this.readString(0, 2);
	}

	private String readString(final int start, final int end) {
		if (this.line != null) {
			return this.line.substring(start, end).trim();
		}
		throw new RuntimeException("Line is null");
	}

	public int getIdentificacao() {
		return Integer.parseInt(this.readString(201, 202));
	}

}
