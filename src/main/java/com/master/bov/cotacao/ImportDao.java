/**
 * 
 */
package com.master.bov.cotacao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Vilmar.Ricken
 * 
 */
public class ImportDao {

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(final String[] args) throws SQLException {
		final int year = 2012;
		ImportDao dao = new ImportDao();
		File file = new File("COTAHIST_A2011.TXT");
		dao.importYear(year, file);
		file = new File("COTAHIST_A2012.TXT");
		dao.importYear(year, file);
	}

	/**
	 * @param year
	 * @param file
	 * @throws SQLException
	 */
	public void importYear(final int year, final File file) throws SQLException {
		final Connection conn = ConnectionFactory.getConnection();
		// conn.setAutoCommit(false);
		final PreparedStatement stmtLastDate = conn.prepareStatement(this.getSqlLastDateCotacao());
		stmtLastDate.setInt(1, year);
		ResultSet rsLastDate = stmtLastDate.executeQuery();
		Date lastDate = null;
		if (rsLastDate.next()) {
			lastDate = rsLastDate.getDate(1);
		}

		final PreparedStatement insertCotacao = conn.prepareStatement(this.getSqlInsertCotacao());
		final ReaderBov reader = new ReaderBov(file, lastDate);
		final GregorianCalendar gc = new GregorianCalendar();
		int count = 0;
		while (reader.next()) {
			final Date date = reader.getDataPregao();
			gc.setTimeInMillis(date.getTime());
			// 1 - DATA
			insertCotacao.setDate(1, new java.sql.Date(date.getTime()));
			final String codigoNegociacao = reader.getCodigoNegociacao();
			// 2 - ACAO
			insertCotacao.setString(2, codigoNegociacao);
			// 3 - DIA
			insertCotacao.setInt(3, gc.get(Calendar.DATE));
			// 4 - MES
			insertCotacao.setInt(4, gc.get(Calendar.MONTH + 1));
			// 5 - ANO
			insertCotacao.setInt(5, gc.get(Calendar.YEAR));
			// 6 - NOME
			insertCotacao.setString(6, reader.getNomeEmpresa());
			// 7 - IDENTIFICACAO
			insertCotacao.setInt(7, reader.getIdentificacao());
			// 8 - DBI
			insertCotacao.setInt(8, reader.getCodigoDBI());
			// 9 - ESPECIFICACAO
			insertCotacao.setString(9, reader.getEspecificacao());
			// 10 - TIPOMERCADO
			insertCotacao.setInt(10, reader.getTipoMercado());
			// 11 - MOEDA
			insertCotacao.setString(11, reader.getMoeda());
			// 12 - ABERTURA
			insertCotacao.setDouble(12, reader.getPrecoAbertura());
			// 13 - MAXIMO
			insertCotacao.setDouble(13, reader.getPrecoMaximo());
			// 14 - MINIMO
			insertCotacao.setDouble(14, reader.getPrecoMinimo());
			// 15 - MEDIO
			insertCotacao.setDouble(15, reader.getPrecoMedio());
			// 16 - ULTIMO
			insertCotacao.setDouble(16, reader.getPrecoUltimo());
			// 17 - MELHOROFERTACOMPRA
			insertCotacao.setDouble(17, reader.getPrecoMelhorOfertaCompra());
			// 18 - MELHOROFERTAVENDA
			insertCotacao.setDouble(18, reader.getPrecoMelhorOfertaVenda());
			// 19 - TOTALNEGOCIOS
			insertCotacao.setInt(19, reader.getTotalNegocios());
			// 20 - TOTALNEGOCIADO
			insertCotacao.setLong(20, reader.getQuantidadeTotal());
			// 21 - VOLUMETOTAL
			insertCotacao.setDouble(21, reader.getVolumeTotal());
			// 22 - PRECOEXERCECIO
			insertCotacao.setDouble(22, reader.getPrecoExercicio());
			count += insertCotacao.executeUpdate();
		}
		if ((count % 1000) == 0 && count > 10) {
			System.out.println("Inserido " + count + " cotações.");
		}
		// conn.commit();
	}

	public void clearYear(int year) throws SQLException {
		final Connection conn = ConnectionFactory.getConnection();
		// conn.setAutoCommit(false);
		final PreparedStatement stmtDelete = conn.prepareStatement(this.getSqlDeleteYear());
		stmtDelete.setInt(1, year);
		System.out.println("Deletado " + stmtDelete.executeUpdate() + " cotações do ano " + year);
	}

	protected String getSqlReadAcao() {
		return "select acao, nome from bovacao";
	}

	protected String getSqlInsertAcao() {
		return "insert into bovacao (acao, nome) values (?, ?)";
	}

	protected String getSqlInsertCotacao() {
		return "insert into bovcotacao (DATA, ACAO, DIA, MES, ANO, NOME, IDENTIFICACAO, DBI, ESPECIFICACAO, TIPOMERCADO, MOEDA, ABERTURA, MAXIMO, MINIMO, MEDIO, ULTIMO, MELHOROFERTACOMPRA, MELHOROFERTAVENDA, TOTALNEGOCIOS, TOTALNEGOCIADO, VOLUMETOTAL, PRECOEXERCECIO) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	}

	protected String getSqlDeleteYear() {
		return "delete from bovcotacao where ano = ?";
	}

	protected String getSqlLastDateCotacao() {
		return "select max(data) from bovcotacao where ano = ?";
	}

}
