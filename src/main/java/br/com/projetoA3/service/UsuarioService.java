package br.com.projetoA3.service;

import br.com.projetoA3.dto.UsuarioRequest;
import br.com.projetoA3.dto.UsuarioResetSenhaRequest;
import br.com.projetoA3.dto.CreateUsuarioResponse;
import br.com.projetoA3.dto.TextoResponse;
import br.com.projetoA3.dto.UsuarioResponse;

public interface UsuarioService {
    CreateUsuarioResponse create(UsuarioRequest usuarioRequest);

    UsuarioResponse getById(Long id);

    UsuarioResponse update(UsuarioRequest usuarioRequest, Long id);

    TextoResponse findByEmail(String email);

    TextoResponse updateSenha(UsuarioResetSenhaRequest UsuarioResetSenha);

}
