package br.com.itau.dto;

import br.com.itau.model.Usuario;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UsuarioRespostaDTO {

    private Long id;
    private String senha;

    public static UsuarioRespostaDTO transformaEmDTO(Usuario usuario) {
        return new UsuarioRespostaDTO(usuario.getId(),usuario.getSenha());
    }
}
