package br.kliemann.flashcar.exception;

public class DescricaoInvalidaException extends Exception {
    
    public DescricaoInvalidaException() {
        super("Descrição vazia ou invalida. Verifique!");
    }
    
}
