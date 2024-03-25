package com.example.xls2sql.xls;

import com.example.xls2sql.sql.domain.CelulaLinhaSql;
import com.example.xls2sql.sql.domain.TipoDados;
import com.example.xls2sql.sql.domain.Coluna;
import org.apache.poi.ss.usermodel.Cell;

import java.util.ArrayList;

/**Esta classe faz a conversão da celula no Xls para as classes ElementoSql ou Coluna conforme o método
 * chamado pela classe {@link com.example.xls2sql.xls.LeitorXls leitorXls }que faz a separação das células entre elemento e coluna.
 * @author Halan Silva
 * @see CelulaLinhaSql
 * @see LeitorXls
   */
public class ConversorXls {

    final private int colunaXls;
    final private int linhaXls;
/**Cria a classe ConversorXls a qual recebe os parâmetros
 * @param colunaXls o qual deve ser a posição no eixo X da celula no arquivo Xls/Xlsx
 * @param  linhaXls o qual deve ser a posição no eixo Y da celula no arquivo Xls/Xlsx
 *                  <p>
 * Os parâmetros acima são muito importantes para as classes ElementoSql e Coluna poderem checar
 * os dados incluídos nas células e retornarem uma das suas exceções com a posição exata da celula
 * que se tem a exceção*/
    public ConversorXls(int colunaXls, int linhaXls) {
        this.colunaXls = colunaXls;
        this.linhaXls = linhaXls;
    }
/**Este método pega uma celula do arquivo Xls e a converte em uma classe
 * {@link CelulaLinhaSql ElementoSql}.
 * @param cell Deve ser uma das células do arquivo xls/xlsx.
 * @param tipoDados Deve ser o  {@link com.example.xls2sql.sql.domain.TipoDados TipoDados}
 *                  vinculado a primeira linha daquela Coluna.
 *<p>
 *@return {@link CelulaLinhaSql ElementoSql}*/
    public CelulaLinhaSql adicionarElemento(Cell cell, TipoDados tipoDados) {

        ArrayList<String> celulaAAdicionar = new ArrayList<>();
        int i = 1;
        int numeroLimiteAtual = 16777215*i;
        int numeroLimiteAntigo = 0;
        CelulaLinhaSql celulaLinhaSql = new CelulaLinhaSql(this.linhaXls,this.colunaXls);



        switch (cell.getCellType()) {



            case NUMERIC ->{

                while(Integer.toString((int) cell.getNumericCellValue()).length() >= numeroLimiteAtual){
                    String celula = Integer.toString((int) cell.getNumericCellValue()).substring(numeroLimiteAntigo
                            ,numeroLimiteAtual);


                    celulaAAdicionar.add(celula);
                    i++;
                    numeroLimiteAntigo = numeroLimiteAtual;
                    numeroLimiteAtual = 16777215*i;
                }
                int numerMaximo = Integer.toString((int) cell.getNumericCellValue()).length() - numeroLimiteAntigo;
                if (numerMaximo > 0){
                    String celula = Integer.toString((int) cell.getNumericCellValue()).substring(numeroLimiteAntigo);

                    celulaAAdicionar.add(celula);
                }



            }

            case STRING->{
                while( cell.getStringCellValue().length() >= numeroLimiteAtual){
                    String celula = cell.getStringCellValue().substring(numeroLimiteAntigo,numeroLimiteAtual);
                    celulaAAdicionar.add(celula);
                    i++;
                    numeroLimiteAntigo = numeroLimiteAtual;
                    numeroLimiteAtual = 16777215*i;
                }


                int numerMaximo = cell.getStringCellValue().length() - numeroLimiteAntigo;
                if (numerMaximo > 0){
                    String celula = cell.getStringCellValue().substring(numeroLimiteAntigo);

                    celulaAAdicionar.add(celula);
                }
            }

        }
        celulaLinhaSql.adicionarCelula(celulaAAdicionar,tipoDados);


        return celulaLinhaSql;


    }

    /**Este método pega uma das celulas do arquivo xls/xlsx e com esses dados cria uma classe
     * Coluna.
     * @param cell deve ser uma das células do arquivo xls
     * @return {@link Coluna Coluna}
     * @see <a href= https://poi.apache.org/apidocs/dev/org/apache/poi/ss/usermodel/Cell.html>Cell</a>
     * */

    public Coluna adicionarColuna(Cell cell) {

        Coluna coluna = new Coluna();
        switch (cell.getCellType()) {
            case NUMERIC:

                coluna.adicionar(Integer.toString((int) cell.getNumericCellValue()), colunaXls);

                break;
            case STRING:

                coluna.adicionar(cell.getStringCellValue(), colunaXls);

                break;
        }
        return coluna;
    }
}
