# Xls2Sql-Jar-Converter

Conversor de tabela no formato (.xls/.xlsx) para um arquivo em (.sql) todo feito em java.
A qual pode ser aplicado no mysql assim pondo os elementos em uma tabela do sql.

## Como Usar

### Tabela Xls

1. Quando for montar a tabela xls tudo oque precisa se atentar é em colocar o nome das colunas do sql nas células 
da primeira linha do arquivo xls. Com os seus respectivos tipos de dados entre colchetes ao lado, e caso haja 
alguma limitação de tamanho na tabela sql você deve por entre aspas ao lado do tipo de dado e ainda dentro dos 
colchetes.

    Ex:

    | EXEMPLO1[varchar(10)] | EXEMPLO2[int] |
    |-----------------------|---------------|

2. Nas linhas seguintes você vai colocar as inserções no sql sendo uma por linha obedecendo a ordem das colunas
   postas previamente e sem ultrapassar o limite de células de coluna escritas.

    Ex2:

   | EXEMPLO1[varchar(10)] | EXEMPLO2[int] |
   |-----------------------|---------------|
   | elemento1             | elemento1     |
   | elemento2             | elemento2     |
   




