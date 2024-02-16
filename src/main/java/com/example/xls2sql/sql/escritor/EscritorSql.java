package com.example.xls2sql.sql.escritor;

import com.example.xls2sql.domain.InfoUsuario;
import com.example.xls2sql.sql.domain.Coluna;
import com.example.xls2sql.sql.domain.DadosSql;
import com.example.xls2sql.sql.domain.ElementosSql;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EscritorSql  {

    private DadosSql dadosSql;
    private InfoUsuario usuario;
    private FileWriter writer;



    public EscritorSql(DadosSql Dados, InfoUsuario usuario) throws IOException {
        this.dadosSql = Dados;
       this.usuario = usuario;
        this.writer = new FileWriter(new File(usuario.getEnderecoSql() + usuario.getNomeTabela() + ".sql"));

    }

    public void usarBancoDedados() throws IOException {

        writer.write("use " + usuario.getNomeBancoDados() + ";\n");

    }

    private void criarTabela() throws IOException {
        writer.write("CREATE TABLE " + usuario.getNomeTabela() + "(" );
        boolean primeiraInteracaoLaco = true;
        for (Coluna coluna : dadosSql.getColunas()){
            if(!primeiraInteracaoLaco){
                writer.append(",");
            }
            writer.append(coluna.getNome() + " " +
                    coluna.getTipo().getTipo() );
            if (coluna.getTipo().contemNumeroElementos()){
                if (coluna.getTipo().getNumeroElementosString().contains(".")){
                    String numeroElementos = coluna.getTipo().getNumeroElementosString().replace(".", ",");
                    writer.append(numeroElementos);
                }else{
                    writer.append(coluna.getTipo().getNumeroElementosString());
                }
            }

            primeiraInteracaoLaco = false;
        }
        writer.append(");");
        this.pularLinha();


    }

    private void IncluirElemento() throws IOException {
        EscritorTextoLinha escritorLinha = new EscritorTextoLinha();
        for(ElementosSql elementosSql : dadosSql.getElementos()){
            escritorLinha.textoColunaIncluirLinhas(dadosSql.getColunas(), usuario.getNomeTabela());
            escritorLinha.textoElementosLinha(elementosSql);
            ArrayList<String> textoInsercaoSql = escritorLinha.getTextoAEscrever();
            for (String textoAInserir : textoInsercaoSql){
                writer.append(textoAInserir);
            }


            this.pularLinha();
            this.pularLinha();
       }
    }
    public void escreverBancoDados() throws IOException {


        this.usarBancoDedados();
        this.pularLinha();
        this.criarTabela();
        this.pularLinha();
        this.IncluirElemento();
        writer.close();

    }

    private void pularLinha() throws IOException {
        writer.write("\n");
    }
}
