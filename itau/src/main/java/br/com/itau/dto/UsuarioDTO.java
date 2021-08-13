package br.com.itau.dto;

import br.com.itau.model.Usuario;
import lombok.Getter;

@Getter
public class UsuarioDTO {

    private String senha;

    public Usuario transformaParaObjeto(){
        return new Usuario(senha);
    }
}
