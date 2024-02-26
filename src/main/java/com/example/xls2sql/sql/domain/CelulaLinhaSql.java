package com.example.xls2sql.sql.domain;


import com.example.xls2sql.sql.celulaLinhaSql.CelulaLinhaSqlService;
import com.example.xls2sql.sql.exceptions.CelulaComElementosNaoConversiveisException;
import com.example.xls2sql.sql.exceptions.TipoDadoSqlNaoEncontradoException;

import java.util.ArrayList;


/**<p>Esta classe é herdeira da classe AcondicionadoresTipoDados e ela armazena os dados de uma única célula de uma linha do sql</p>
 * @see AcondicionadoresTipoDados*/
public class CelulaLinhaSql extends AcondicionadoresTipoDados {


    /**Esta variável é utilizada para armazenar as informações contidas na celula do arquivo xls/xlsx correspondente.
     * A variável é um ArrayList porque os textos podem acabar sendo muito grande.*/
    private ArrayList<String> celula;

    /**Esta variável corresponde a posição no eixo Y da celula no arquivo xls/xlsx.*/
    private final int linha;

    /**Esta variável corresponde a posição no eixo X da celula no arquivo xls/xlsx.*/
    private final int coluna;

    /**Cria uma CelulaLinhaSql.
     * @param linha posição no eixo y da célula no arquivo xls/xlsx.
     * @param coluna posição no eixo x da célula no arquivo xls/xlsx.*/
    public CelulaLinhaSql(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    /**<p>Este método modifica a variável tipoDados que vem herdada e também modifica a variável celula.</p>
     * <p>Este método é ideal de ser utilizado no lugar do próprio setCelula, porque reduz o risco de erros por
     * não ter o tipoDados ou ter o tipoDados não correspondente.</p>
     * @param listaCelulaAdicionar Dados contidos na celula correspondente do arquivo xls/xlsx.
     * @param tipoDados Deve ser o tipoDados da classe Coluna correspondente a célula.
     * */
    public void adicionarCelula(ArrayList<String> listaCelulaAdicionar, TipoDados tipoDados) {
        this.setTipoDados(tipoDados);
        this.setCelula(listaCelulaAdicionar);
    }

    /**Retorna a variável celula.*/
    public ArrayList<String> getCelula() {
        return celula;
    }



    /**Retorna a variável linha.*/
    public int getLinha() {
        return linha;
    }

    /**Retorna a variável coluna.*/
    public int getColuna() {
        return coluna;
    }

    /**<p>Modifica a variável celula utilizando a classe CelulaLinhaSqlService para checar se a modificação da classe é
     * válida.</p>
     * <p>Caso a instância da classe não tiver um tipoDados vai soltar a exception
     * {@link TipoDadoSqlNaoEncontradoException TipoDadoSqlNaoEncontradoException}. </p>
     * <p>Caso o Arraylist a por estiver fora do padrão do tipoDados o método vai soltar a exception
     * {@link CelulaComElementosNaoConversiveisException CelulaComElementosNaoConversiveisException}. </p>
     *  @param listaCelulaAdicionar  Deve ser uma arraylist com o texto novo da variável celula.
     *  @see CelulaLinhaSqlService*/
    public void setCelula(ArrayList<String> listaCelulaAdicionar) {



        if(this.getTipoDados() != null) {

            String textoAposChecagem = CelulaLinhaSqlService.formatarCelulaDateTime(
                    listaCelulaAdicionar.get(0),this.getTipoDados().tipoDadosSql());
            listaCelulaAdicionar.set(0, textoAposChecagem);

            if (CelulaLinhaSqlService.checarCelulaAIncluir(listaCelulaAdicionar,this.getTipoDados())) {

                this.celula = listaCelulaAdicionar;

            } else {
                throw new CelulaComElementosNaoConversiveisException(this.getLinha(), this.getColuna());

            }
        }else {
            throw new TipoDadoSqlNaoEncontradoException(this.getLinha(),this.getColuna());
        }




    }

    /**Método que verifica se a celula possui dados a serem inseridos ou não.
     * @return Retorna true se a celula não tiver dados a serem inseridos e retorna false se tiver dados a serem inseridos.*/
    public boolean isNull() {
        if (this.getCelula().get(0).equals("null")||this.getCelula().get(0).equals(" ")){
            return true;
        }else{
            return false;
        }
    }
}
