package com.example.xls2sql.sql.escritor;

import com.example.xls2sql.coletor.domain.InfoUsuario;
import com.example.xls2sql.sql.domain.Coluna;
import com.example.xls2sql.sql.DadosSql;
import com.example.xls2sql.sql.domain.LinhaSql;
import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSqlNumeric;
import org.jetbrains.annotations.NotNull;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**Classe que faz a escrita do arquivo sql.
 * @author Halan Silva*/
public class EscritorSql  {

    /** Esta variável guarda os dados contidos no arquivo xls/xlsx já preparados para ser incluso no sql*/
    private final DadosSql dadosSql;

    /**Esta variável guarda o nome do banco de dados aonde será salvo os registros.*/
    private final String nomeBancoDados;

    /**Esta variável guarda o nome da tabela dentro do banco de dados onde será salvo os registros.*/
    private final String nomeTabela;
    /**Esta variável é que faz a escrita do arquivo Xls.*/
    private final FileWriter writer;



    /**Método gerador o qual já instância todas as variáveis da classe.
     * @param Dados Este parâmetro é copiado para a variável dadosSql e deve conter todos os dados do arquivo xls/xlsx.
     * @param usuario <p>Parâmetro que guarda todos os dados da interação do usuário com o programa, dele será retirado
     *               as variáveis nomeBancoDados e nomeTabela e serão copiados para as variáveis de mesmo nome do EscritorSql.</p>*/
    public EscritorSql(DadosSql Dados, @NotNull InfoUsuario usuario) throws IOException {
        this.dadosSql = Dados;
       this.nomeBancoDados = usuario.getNomeBancoDados();
       this.nomeTabela = usuario.getNomeTabela();
        this.writer = new FileWriter(usuario.getEnderecoSql() + usuario.getNomeTabela() + ".sql");

    }

    /**Método que faz a integração entre os métodos internos e faz toda a escrita sql e fecha o escritor.*/
    public void escreverBancoDados() {


        try {
            this.usarBancoDedados();
            this.pularLinha();
            this.criarTabela();
            this.pularLinha();
            this.IncluirElemento();
            writer.close();

        } catch (IOException e) {
            System.out.println("Um erro aconteceu durante a escrita do arquivoSql.\n Por favor, tente novamente.");
        }

    }

    /**Método interna que faz a escrita do código sql para selecionar o banco de dados a ser usado.*/
    private void usarBancoDedados() throws IOException {

        writer.write("use " + this.nomeBancoDados + ";");
        this.pularLinha();

    }

    /**Método interno que faz a escrita do código sql para criar a tabela.*/
    private void criarTabela() throws IOException {
        writer.write("CREATE TABLE " + this.nomeTabela + "(" );
        boolean primeiraInteracaoLaco = true;
        for (Coluna coluna : dadosSql.getColunas()){
            if(!primeiraInteracaoLaco){
                writer.append(",");
            }
            writer.append(coluna.getNome() + " " + coluna.getTipoDados().tipoColunaSql());
            if (coluna.getTipoDados().contemNumeroElementos()){
                if (coluna.getTipoDados().tipoColunaSql() == TipoColunaSqlNumeric.DECIMAL){

                    String numeroElementos = Double.toString(coluna.getTipoDados().numeroElementos()).replace(".", ",");
                    writer.append("(" + numeroElementos + ")");
                }else{

                    String textoAEscrever = "(" + (int)coluna.getTipoDados().numeroElementos() + ")";
                    writer.append(textoAEscrever);
                }
            }

            primeiraInteracaoLaco = false;
        }
        writer.append(");");
        this.pularLinha();


    }

    /**Método interno que com a ajuda da classe {@link EscritorTextoLinha EscritorTextoLinha}  faz a escrita das linhas do sql.*/
    private void IncluirElemento() throws IOException {
        for(LinhaSql linhaSql : dadosSql.getAgregadorElementosSql()){

            EscritorTextoLinha escritorLinha = new EscritorTextoLinha(linhaSql);
            escritorLinha.textoColunaIncluirLinhas(this.dadosSql.getColunas(), this.nomeTabela);
            escritorLinha.textoElementosLinha();
            ArrayList<String> textoInsercaoSql = escritorLinha.getTextoAEscrever();
            for (String textoAInserir : textoInsercaoSql){
                writer.append(textoAInserir);
            }


            this.pularLinha();
            this.pularLinha();
       }
    }

    /**Método interno que pula uma linha no texto escrito do sql.*/
    private void pularLinha() throws IOException {
        writer.write("\n");
    }
}
