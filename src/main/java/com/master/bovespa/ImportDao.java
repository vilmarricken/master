/**
 * 
 */
package com.master.bovespa;

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

import com.master.datasource.ConnectionFactory;

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
        final int year = 2009;
        final File file = new File("C:\\Particular\\CD\\Pen\\Projetos\\COTAHIST_A2009\\COTAHIST_A2009.TXT");
        new ImportDao().importYear(year, file);
    }

    /**
     * @param year
     * @param file
     * @throws SQLException
     */
    public void importYear(final int year, final File file) throws SQLException {
        final Connection conn = ConnectionFactory.getConnection();
        //conn.setAutoCommit(false);
        final PreparedStatement stmtDelete = conn.prepareStatement(this.getSqlDeleteYear());
        stmtDelete.setInt(1, year);
        System.out.println("Deletando " + stmtDelete.executeUpdate() + " cotações do ano " + year);
        final PreparedStatement insertCotacao = conn.prepareStatement(this.getSqlInsertCotacao());
        final PreparedStatement insertAcao = conn.prepareStatement(this.getSqlInsertAcao());
        final ReaderBov reader = new ReaderBov(file);
        final GregorianCalendar gc = new GregorianCalendar();
        int count = 0;
        final Set<String> acoes = this.readAcoes(conn);
        while (reader.next()) {
            int index = 1;
            //data
            final Date date = reader.getDataPregao();
            gc.setTimeInMillis(date.getTime());
            insertCotacao.setDate(index++, new java.sql.Date(date.getTime()));
            //acao
            final String codigoNegociacao = reader.getCodigoNegociacao();
            insertCotacao.setString(index++, codigoNegociacao);
            //dia
            insertCotacao.setInt(index++, gc.get(Calendar.DATE));
            //mes
            insertCotacao.setInt(index++, gc.get(Calendar.MONTH + 1));
            //ano
            insertCotacao.setInt(index++, gc.get(Calendar.YEAR));
            //nome
            final String nomeEmpresa = reader.getNomeEmpresa();
            insertCotacao.setString(index++, nomeEmpresa);
            //especificacao
            insertCotacao.setString(index++, reader.getEspecificacao());
            //moeda
            insertCotacao.setString(index++, reader.getMoeda());
            //abertura
            insertCotacao.setDouble(index++, reader.getPrecoAbertura());
            //maximo
            insertCotacao.setDouble(index++, reader.getPrecoMaximo());
            //minimo
            insertCotacao.setDouble(index++, reader.getPrecoMinimo());
            //medio
            insertCotacao.setDouble(index++, reader.getPrecoMedio());
            //ultimo
            insertCotacao.setDouble(index++, reader.getPrecoUltimo());
            //melhorofertacompra
            insertCotacao.setDouble(index++, reader.getPrecoMelhorOfertaCompra());
            //melhorofertavenda
            insertCotacao.setDouble(index++, reader.getPrecoMelhorOfertaVenda());
            //totalnegocios
            insertCotacao.setInt(index++, reader.getTotalNegocios());
            //totalnegociado
            insertCotacao.setLong(index++, reader.getQuantidadeTotal());
            //volumetotal
            insertCotacao.setDouble(index++, reader.getVolumeTotal());
            //precoexercicio
            insertCotacao.setDouble(index++, reader.getPrecoExercicio());
            if (!acoes.contains(codigoNegociacao)) {
                acoes.add(codigoNegociacao);
                insertAcao.setString(1, codigoNegociacao);
                insertAcao.setString(2, nomeEmpresa);
                System.out.println("Inserindo acao: " + codigoNegociacao + " (" + insertAcao.execute() + ")");
            }
            count += insertCotacao.executeUpdate();
            System.out.println("Inserido cotação: " + count);
        }
        conn.commit();
    }

    /**
     * @return
     * @throws SQLException
     */
    private Set<String> readAcoes(final Connection conn) throws SQLException {
        final Set<String> acoes = new TreeSet<String>();
        final PreparedStatement stmt = conn.prepareStatement(this.getSqlReadAcao());
        final ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            acoes.add(rs.getString(1));
        }
        stmt.close();
        return acoes;
    }

    protected String getSqlReadAcao() {
        return "select acao, nome from bovacao";
    }

    protected String getSqlInsertAcao() {
        return "insert into bovacao (acao, nome) values (?, ?)";
    }

    protected String getSqlInsertCotacao() {
        return "insert into bovcotacao (data, acao, dia, mes, ano, nome, especificacao, moeda, abertura, maximo, minimo, medio, ultimo, melhorofertacompra, melhorofertavenda, totalnegocios, totalnegociado, volumetotal, precoexercicio) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    protected String getSqlDeleteYear() {
        return "delete from bovcotacao where ano = ?";
    }

}
