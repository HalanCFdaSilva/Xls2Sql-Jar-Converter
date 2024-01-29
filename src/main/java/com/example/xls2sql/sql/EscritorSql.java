package com.example.xls2sql.sql;

import com.example.xls2sql.domain.InfoUsuario;
import com.example.xls2sql.domain.sql.Coluna;
import com.example.xls2sql.domain.sql.DadosSql;
import com.example.xls2sql.domain.sql.ElementoSql;
import com.example.xls2sql.domain.sql.ElementosSql;
import com.example.xls2sql.sql.tipoDadosSQL.TipoDadosSql;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EscritorSql  {

    private DadosSql colunas;
    private InfoUsuario usuario;
    private FileWriter writer;



    public EscritorSql(DadosSql Dados, InfoUsuario usuario) throws IOException {
        this.colunas = Dados;
       this.usuario = usuario;
        this.writer = new FileWriter(new File(usuario.getEnderecoSql() + usuario.getNomeTabela() + ".sql"));

    }

    public void usarBancoDedados() throws IOException {

        writer.write("use " + usuario.getNomeBancoDados() + ";\n");

    }

    private void criarTabela() throws IOException {
        writer.write("CREATE TABLE " + usuario.getNomeTabela() + "(" );
        boolean primeiraInteracaoLaco = true;
        for (Coluna coluna : colunas.getColunas()){
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
       for(ElementosSql elementosSql : colunas.getElementos()){
           writer.write("INSERT INTO " + usuario.getNomeTabela() + "(" );
           boolean primeiraInteracaoLaco = true;
           for (Coluna coluna : colunas.getColunas()) {
               if (!primeiraInteracaoLaco) {
                   writer.append(",");
               }
               writer.append(coluna.getNome());
               primeiraInteracaoLaco = false;
           }
           writer.append(")\nVALUES(");
           primeiraInteracaoLaco = true;
           for (ElementoSql dadoSql : elementosSql.getElementosTabela()) {
               if (!primeiraInteracaoLaco) {
                   writer.append(",");
               }
//               if(dadoSql.getTipoDados().getTipo().equals(TipoDadosSql.VARCHAR)||dadoSql.getTipoDados().getTipo().equals(TipoDadosSql.DATETIME)){
//                   writer.append("'" + dadoSql.getCelula() + "'");
//               }else{
//                   writer.append(dadoSql.getCelula());
//               }
               primeiraInteracaoLaco = false;
           }
           writer.append(");");
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
