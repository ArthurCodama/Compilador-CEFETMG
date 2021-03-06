/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padrao;

import Expressao.ExpressaoAritmetica;
import Expressao.ExpressaoLogica;
import java.util.ArrayList;
import static padrao.Aplicacao.variaveis;

/**
 *
 * @author Gabriel
 */
public class Compilador {
    String programa;
    ArrayList<String> palavras_reservadas = new ArrayList();
    ArrayList<Comando> comandos = new ArrayList();

    
    public Compilador(String programa){ //O compilador é construido com um programa a ser compilado em cefetiny.
        this.programa = programa;
        palavras_reservadas.add("print");
        palavras_reservadas.add("println");
        palavras_reservadas.add("readint");
        palavras_reservadas.add("if");
        palavras_reservadas.add("while");
        palavras_reservadas.add("for");
        palavras_reservadas.add("then");
        palavras_reservadas.add("else");
        palavras_reservadas.add("endif");
        palavras_reservadas.add("do");
        palavras_reservadas.add("endwhile");
        palavras_reservadas.add("to");
        palavras_reservadas.add("downto");
        palavras_reservadas.add("endfor");
        palavras_reservadas.add("sqrt");
        palavras_reservadas.add("end");
        palavras_reservadas.add(":=");
    }
    
    public void analisaComandos() {
        
        Compilador compilador;
        Compilador compiladorBlocoElse;
        ComandoAtribuicao comandoAtribuicao;
        ComandoPrint print;
        ComandoPrintln println;
        ComandoReadInt readInt;
        ComandoWhile comandoWhile;
        ComandoIf comandoIf;
        char caractere = '0';
        char caractereAux = '0';
        String palavraAux = "";
        String atribuicao = "";
        String expressao = "";
        String subExpressaoAtribuicao = "";
        String subPrograma = "";
        String subProgramaIf = "";
        String subProgramaElse = "";
        String subPalavraAux = "";
        int saldoParenteses = 0;
        int saldoWhile;
        int saldoIf;
        int i, a, inicioComando = 0;
        boolean temElse;
        
        for (i = 0; i < programa.length(); i++) {
            caractere = programa.charAt(i);
            if (caractere != ' ' && caractere != ':' && caractere != '(') {
                palavraAux += caractere;
            } else {
                if (palavras_reservadas.contains(palavraAux)) {
                    expressao = "";

                    if (palavraAux.equals("end")) {
                        return;
                    }

                    if (palavraAux.equals("print")) {
                        saldoParenteses = 0;
                        a = i;
                        if (caractere == ' ') {
                            while (caractere == ' ') {
                                a++;
                                caractere = programa.charAt(a);
                            }
                        }
                        if (caractere == '(') {
                            a++;
                            caractere = programa.charAt(a);
                            if (caractere == ' ') {
                                while (caractere == ' ') {
                                    a++;
                                    caractere = programa.charAt(a);
                                }
                            }

                            do {
                                expressao += caractere;
                                if (caractere == '(') {
                                    saldoParenteses++;
                                } else if (caractere == ')') {
                                    saldoParenteses--;
                                }
                                a++;
                                caractere = programa.charAt(a);
                                if (caractere == ' ') {
                                    while (caractere == ' ') {
                                        a++;
                                        caractere = programa.charAt(a);
                                    }
                                }
                            } while (!(caractere == ')' && saldoParenteses == 0));
                        }
                        a++;
                        caractere = programa.charAt(a);
                        if (caractere == ' ') {
                            while (caractere == ' ') {
                                a++;
                                caractere = programa.charAt(a);
                                inicioComando = a - 1;
                            }
                        }
                        print = new ComandoPrint(new ExpressaoAritmetica(expressao));
                        comandos.add(print);
                        i = inicioComando;
                    }

                    if (palavraAux.equals("readint")) {
                        a = i;
                        if (caractere == ' ') {
                            while (caractere == ' ') {
                                a++;
                                caractere = programa.charAt(a);
                            }
                        }
                        if (caractere == '(') {
                            a++;
                            caractereAux = programa.charAt(a);
                            if (caractereAux == ' ') {
                                while (caractereAux == ' ') {
                                    a++;
                                    caractereAux = programa.charAt(a);
                                }
                            }
                            while (caractereAux != ')') {
                                expressao += caractereAux;
                                a++;
                                caractereAux = programa.charAt(a);

                                if (caractereAux == ' ') {
                                    while (caractereAux == ' ') {
                                        a++;
                                        caractereAux = programa.charAt(a);
                                    }
                                }
                            }
                        }
                        a++;
                        caractereAux = programa.charAt(a);
                        if (caractereAux == ' ') {
                            while (caractereAux == ' ') {
                                a++;
                                caractereAux = programa.charAt(a);
                                inicioComando = a - 1;
                            }
                        }
                        if (variaveis.containsKey(expressao)) {
                            readInt = new ComandoReadInt(expressao);
                            comandos.add(readInt);
                            i = inicioComando;
                        } else {
                            //Erro: variavel nao existe.
                        }
                        if (expressao.contains(" ")) {
                            //Erro: conteudo do readInt deve ser uma variavel.
                        }
                    }
                    if (palavraAux.equals("while")) {
                        saldoWhile = 0;
                        subPrograma = "";
                        subPalavraAux = "";
                        saldoParenteses = 0;
                        saldoWhile++;
                        a = i;
                        if (caractere == ' ') {
                            while (caractere == ' ') {
                                a++;
                                caractere = programa.charAt(a);
                            }
                        }
                        if (caractere == '(') {
                            a++;
                            caractere = programa.charAt(a);
                            if (caractere == ' ') {
                                while (caractere == ' ') {
                                    a++;
                                    caractere = programa.charAt(a);
                                }
                            }

                            do {
                                expressao += caractere;
                                if (caractere == '(') {
                                    saldoParenteses++;
                                } else if (caractere == ')') {
                                    saldoParenteses--;
                                }
                                a++;
                                caractere = programa.charAt(a);
                                if (caractere == ' ') {
                                    while (caractere == ' ') {
                                        a++;
                                        caractere = programa.charAt(a);
                                    }
                                }
                            } while (!(caractere == ')' && saldoParenteses == 0));
                        }else {
                            //Erro: sintaxe while
                        }
                        a++;
                        caractere = programa.charAt(a);
                        if (caractere == ' ') {
                            while (caractere == ' ') {
                                a++;
                                caractere = programa.charAt(a);
                            }
                        }
                        a++;
                        caractereAux = programa.charAt(a);
                        if("do".equals(""+caractere+caractereAux)){
                            a++;
                            caractere = programa.charAt(a);
                            if (caractere == ' ') {
                                while (caractere == ' ') {
                                    a++;
                                    caractere = programa.charAt(a);
                                }
                            }else{
                             //Erro: sintaxe while   
                            }
                            while (saldoWhile != 0){
                                if (caractere != ' ') {
                                    subPalavraAux += caractere;

                                }else{
                                    if(subPalavraAux.equals("endwhile")){
                                        saldoWhile--;
                                        subPrograma += subPalavraAux+" ";
                                    }else if(subPalavraAux.equals("while")){
                                        saldoWhile++;
                                        subPrograma += subPalavraAux+" ";
                                    }else if(!subPalavraAux.equals("endwhile") && !subPalavraAux.endsWith("while")){
                                        subPrograma += subPalavraAux+" ";
                                    }
                                    subPalavraAux = "";
                                }
                                a++;
                                caractere = programa.charAt(a);
                            }
                        inicioComando = a-1;
                            
                        }else{
                            //Erro: sintaxe while
                        }
                        compilador = new Compilador(subPrograma);
                        compilador.analisaComandos();
                        comandoWhile = new ComandoWhile(new ExpressaoLogica(expressao), compilador.getComandos());
                        comandos.add(comandoWhile);
                        i = inicioComando;
                    }
                    
                    if (palavraAux.equals("endwhile")) {
                        return;
                    }
                    if (palavraAux.equals("println")) {
                        println = new ComandoPrintln();
                        comandos.add(println);
                        a = i;
                        caractere = programa.charAt(a);
                        if (caractere == ' ') {
                            while (caractere == ' ') {
                                a++;
                                caractere = programa.charAt(a);
                                inicioComando = a - 1;
                            }
                        }
                        i = inicioComando;
                    }
                    
                    if (palavraAux.equals("if")) {
                        saldoIf = 0;
                        subPrograma = "";
                        subProgramaIf = "";
                        subProgramaElse = "";
                        subPalavraAux = "";
                        saldoParenteses = 0;
                        saldoIf++;
                        temElse = false;
                        
                        a = i;
                        String then = "";
                        if (caractere == ' ') {
                            while (caractere == ' ') {
                                a++;
                                caractere = programa.charAt(a);
                            }
                        }
                        if (caractere == '(') {
                            a++;
                            caractere = programa.charAt(a);
                            if (caractere == ' ') {
                                while (caractere == ' ') {
                                    a++;
                                    caractere = programa.charAt(a);
                                }
                            }

                            do {
                                expressao += caractere;
                                if (caractere == '(') {
                                    saldoParenteses++;
                                } else if (caractere == ')') {
                                    saldoParenteses--;
                                }
                                a++;
                                caractere = programa.charAt(a);
                                if (caractere == ' ') {
                                    while (caractere == ' ') {
                                        a++;
                                        caractere = programa.charAt(a);
                                    }
                                }
                            } while (!(caractere == ')' && saldoParenteses == 0));
                        }else {
                            //Erro: sintaxe if
                        }
                        a++;
                        caractere = programa.charAt(a);
                        if (caractere == ' ') {
                            while (caractere == ' ') {
                                a++;
                                caractere = programa.charAt(a);
                            }
                        }
                        then += caractere;
                        for(int b=0; b<3; b++){
                            a++;
                            caractere = programa.charAt(a);
                            then += caractere;
                        }
                        if("then".equals(then)){
                            a++;
                            caractere = programa.charAt(a);
                            if (caractere == ' ') {
                                while (caractere == ' ') {
                                    a++;
                                    caractere = programa.charAt(a);
                                }
                            }else{
                             //Erro: sintaxe if   
                            }
                            while (saldoIf != 0){
                                if (caractere != ' ') {
                                    subPalavraAux += caractere;
   
                                }else{
                                    if(subPalavraAux.equals("endif")){
                                        saldoIf--;
                                        subPrograma += subPalavraAux+" ";
                                    }else if(subPalavraAux.equals("if")){
                                        saldoIf++;
                                        subPrograma += subPalavraAux+" ";
                                    }else if(!subPalavraAux.equals("endif") && !subPalavraAux.equals("if") && !subPalavraAux.equals("else")){
                                        subPrograma += subPalavraAux+" ";
                                    }else if(subPalavraAux.equals("else")){
                                        subProgramaIf = subPrograma;
                                        temElse = true;
                                        subPrograma = "";
                                        break;
                                    }
                                    subPalavraAux = "";
                                }
                                a++;
                                caractere = programa.charAt(a);
                            }
                           
                            inicioComando = a-1;
                            if(temElse){
                                
                            }else{
                                subProgramaIf = subPrograma;
                            }
                            
                        }else{
                            //Erro: sintaxe if
                        }
                        compilador = new Compilador(subProgramaIf);
                        compilador.analisaComandos();
                        compiladorBlocoElse = new Compilador(subProgramaElse);
                        compiladorBlocoElse.analisaComandos();
                        comandoIf = new ComandoIf(new ExpressaoLogica(expressao), compilador.getComandos(), temElse, compiladorBlocoElse.getComandos());
                        comandos.add(comandoIf);
                        i = inicioComando;
                    }
                    
                    if (palavraAux.equals("endif")) {
                        return;
                    }

                }

                if (!palavras_reservadas.contains(palavraAux)) {
                    a = i;
                    String palavraAuxComando = "";
                    expressao = "";

                    if (caractere == ' ') {
                        while (caractere == ' ') {
                            a++;
                            caractere = programa.charAt(a);
                        }
                    }
                    if (caractere == ':') {
                        a++;
                        atribuicao = Character.toString(caractere) + programa.charAt(a);
                        if (atribuicao.equals(":=")) {
                            a++;
                            caractere = programa.charAt(a);
                            if (caractere == ' ') {
                                while (caractere == ' ') {
                                    a++;
                                    caractere = programa.charAt(a);
                                }
                            }
                            while (!palavras_reservadas.contains(palavraAuxComando)) {
                                if (caractere == ' ' || caractere == ':') {
                                    while (caractere == ' ') {
                                        a++;
                                        caractere = programa.charAt(a);

                                    }
                                    palavraAuxComando = "";
                                    inicioComando = a - 1;
                                }
                                palavraAuxComando += caractere;
                                expressao += caractere;
                                a++;
                                caractere = programa.charAt(a);
                            }
                            if (palavraAuxComando.equals(":=")) {
                                String palavraAuxComando2 = "";
                                a = a - 3;
                                caractere = programa.charAt(a);
                                if (caractere == ' ') {
                                    while (caractere == ' ') {
                                        a--;
                                        caractere = programa.charAt(a);
                                    }
                                }
                                while (caractere != ' ') {
                                    a--;
                                    caractere = programa.charAt(a);
                                    inicioComando = a;
                                }
                                a = inicioComando + 1;
                                caractere = programa.charAt(a);
                                while (caractere != ':') {
                                    palavraAuxComando2 += caractere;
                                    a++;
                                    caractere = programa.charAt(a);
                                    if (caractere == ' ') {
                                        while (caractere == ' ') {
                                            a++;
                                            caractere = programa.charAt(a);
                                        }
                                    }
                                }
                                palavraAuxComando = palavraAuxComando2 + palavraAuxComando;
                            }
                            int posicaoComandoTerminoExpressaoAtribuicao = expressao.indexOf(palavraAuxComando);
                            subExpressaoAtribuicao = expressao.substring(0, posicaoComandoTerminoExpressaoAtribuicao);

                            if (!variaveis.containsKey(palavraAux)) {
                                comandoAtribuicao = new ComandoAtribuicao(palavraAux, new ExpressaoAritmetica(subExpressaoAtribuicao) );
                            } else if (variaveis.containsKey(palavraAux)) {
                                variaveis.remove(palavraAux);
                                comandoAtribuicao = new ComandoAtribuicao(palavraAux, new ExpressaoAritmetica(subExpressaoAtribuicao));
                            }
                            i = inicioComando; //Pula o contandor para o ultimo caractere lido dentro do fluxo de atribuição.
                        } else {
                            //Erro: caractere(s) perdido(s) no programa!
                        }
                    }
                }
                palavraAux = "";
            }
        }
    }
    
    public void executaComandos(){
        for (Comando comando : comandos) {
            comando.run();
        }
    }
    
    public ArrayList getComandos(){
        return comandos;
    }
    
    public String getPrograma(){
        return programa;
    }
}
