package br.com.tais.cadastro.exception;

public class CustomExceptions extends RuntimeException{

    public CustomExceptions(){
        super("Id não encontrado.");
    }

    public CustomExceptions(String mensagem){
        super(mensagem);
    }
}
