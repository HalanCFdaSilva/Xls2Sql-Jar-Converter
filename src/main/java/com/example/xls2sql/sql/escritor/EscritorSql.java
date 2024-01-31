package com.example.xls2sql.sql.escritor;

import com.example.xls2sql.domain.InfoUsuario;
import com.example.xls2sql.domain.sql.Coluna;
import com.example.xls2sql.domain.sql.DadosSql;
import com.example.xls2sql.domain.sql.ElementoSql;
import com.example.xls2sql.domain.sql.ElementosSql;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
                writer.append(coluna.getTipo().getNumeroElementosString());
            }

            primeiraInteracaoLaco = false;
        }
        writer.append(");");
        this.pularLinha();


    }

    private void IncluirElemento() throws IOException {
        EscritorTextoLinha escritorLinha = new EscritorTextoLinha();
        for(ElementosSql elementosSql : dadosSql.getElementos()){
            writer.append(escritorLinha.textoColunaIncluirLinhas(dadosSql.getColunas(), usuario.getNomeTabela()));
            writer.append(escritorLinha.textoElementosLinha(elementosSql));
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
