/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Expressao;

/**
 *
 * @author Arthur
 */
public class ExpressaoLogica extends Expressao{

    public ExpressaoLogica(String expressao) {
        super(new ResolveExpressaoLogica(), expressao);
    }
    
}
