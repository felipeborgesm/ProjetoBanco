package br.com.projetoA3.service;

import br.com.projetoA3.dto.UsuarioRequest;
import br.com.projetoA3.dto.CreateUsuarioResponse;
import br.com.projetoA3.dto.UsuarioResponse;

public interface UsuarioService {
    CreateUsuarioResponse create(UsuarioRequest usuarioRequest);

    UsuarioResponse getById(Long id);

    UsuarioResponse update(String senha, UsuarioRequest usuarioRequest, Long id);

    void delete(String senha, Long id);
}
