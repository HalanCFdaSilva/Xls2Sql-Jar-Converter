package com.example.xls2sql.sql.domain;

import com.example.xls2sql.sql.DadosSql;
import com.example.xls2sql.sql.factorys.TipoDadosFactory;
import com.example.xls2sql.sql.exceptions.TextoColunaVaziaException;
import com.example.xls2sql.sql.exceptions.TipoColunaSqlNaoEncontradoException;

/**<p>Classe herdeira da Classe AcondicionadoresTipoDados ela armazena todos os dados de uma das colunas do Sql.</p>
 * <p>Ela é criada pela classe {@link com.example.xls2sql.xls.LeitorXls LeitorXls} e depois é guardada em DadosSql.</p>
 * <p>No fim ela é pega de DadosSql pela classe {@link com.example.xls2sql.sql.escritor.EscritorSql EscritorSql}
 * aonde é utilizada para criar o arquivo sql.</p>
 * @author Halan Silva
 * @see DadosSql */
public class Coluna extends AcondicionadoresTipoDados {


    /**Essa variável armazena o nome da coluna do sql.*/
    private String nome;


    /**Esse método retorna a variável nome.
     * @return String*/
    public String getNome() {
        return nome;
    }


    /**<p>Este método é utilizado para a partir do texto de uma das células do arquivo xls/xlsx gerar um
     * TipoDados e modificar a variável nome.</p>
     * <p>Caso a String esteja vazia o método irá soltar a exception
     * {@link TextoColunaVaziaException TextoColunaVaziaException}</p>
     * <p>Caso o método não encontrar as informações do TipoDados na String ele irá soltar a exception
     * {@link TipoColunaSqlNaoEncontradoException TipoDadoSqlNaoEncontradoException}</p>
     * @param textoCelulaExcel String retirada do xls que será convertida no TipoDados e na variável nome.
     * @param colunaExcel posição no eixo x da célula no arquivo xls.
     *
     *  @see TipoDados*/
    public void adicionar(String textoCelulaExcel, int colunaExcel){

        textoCelulaExcel = textoCelulaExcel.strip();
        if (textoCelulaExcel.contains("[")){
            int numeroInicioColchetes = textoCelulaExcel.indexOf("[");
            int numeroFimColchetes = textoCelulaExcel.indexOf("]");
            String textoSemTipoDado = textoCelulaExcel.substring(0,numeroInicioColchetes)+textoCelulaExcel.substring(numeroFimColchetes+1);
            TipoDados tipo = TipoDadosFactory.generate(textoCelulaExcel.substring(numeroInicioColchetes + 1,numeroFimColchetes),colunaExcel);
            this.setTipoDados(tipo);

            if (textoSemTipoDado.contains("(")){
                int numeroInicioParenteses = textoSemTipoDado.indexOf("(");
                this.nome = textoSemTipoDado.substring(0,numeroInicioParenteses);
                this.nome = this.nome.replace(" ","_");
            }else{
                this.nome = textoSemTipoDado;
            }
        }else {
            if (textoCelulaExcel.isEmpty()){
                throw new TextoColunaVaziaException(colunaExcel);
            }else{
                throw new TipoColunaSqlNaoEncontradoException(colunaExcel);
            }


        }
    }


}
