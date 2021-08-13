package br.com.itau.controller;

import br.com.itau.dto.UsuarioDTO;
import br.com.itau.dto.UsuarioRespostaDTO;
import br.com.itau.model.Usuario;
import br.com.itau.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/usuarios")
@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Rejex
     * 
     * Nove ou mais caracteres - .{9,} 
     * Ao menos 1 dígito - (?=.*[0-9]) 
     * Ao menos 1 letra minúscula - (?=.*[a-z]) 
     * Ao menos 1 letra maiúscula - (?=.*[A-Z]) 
     * Ao menos 1 caractere especial !@#$%^&*()-+ - (?=.*[!@#$%^&*()-+])
     * Espaços em branco - (?=\\S+$)
     * 
     * @return Senha válida ou inválida
     */
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody UsuarioDTO dto) {
        Usuario usuario;
        try {
            usuario = usuarioService.salvar(dto.transformaParaObjeto()); 
            if (usuario.getSenha().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()-+])(?=\\S+$).{9,}$")) {
                return new ResponseEntity<>(UsuarioRespostaDTO.transformaEmDTO(usuario), HttpStatus.CREATED);
            } else {
                return ResponseEntity.badRequest()
                    .body("Sua senha deve conter: Nove ou mais caracteres"
                        + "Ao menos 1 dígito" + "\r\n"
                        + "Ao menos 1 letra minúscula" + "\r\n"
                        + "Ao menos 1 letra maiúscula" + "\r\n"
                        + "Ao menos 1 caractere especial (!@#$%^&*()-+)" + "\r\n"
                        + "Espaços em branco não devem ser considerados como caracteres válidos" + "\r\n"
                        + "Não possuir caracteres repetidos dentro do conjunto");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
