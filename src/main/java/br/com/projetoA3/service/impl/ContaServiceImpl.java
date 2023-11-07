package br.com.projetoA3.service.impl;

import br.com.projetoA3.dto.ContaRequest;
import br.com.projetoA3.dto.ContaResponse;
import br.com.projetoA3.dto.CreateContaResponse;
import br.com.projetoA3.model.Conta;
import br.com.projetoA3.repository.ContaRepository;
import br.com.projetoA3.repository.UsuarioRepository;
import br.com.projetoA3.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class ContaServiceImpl implements ContaService {

  @Autowired
  ContaRepository contaRepository;
  @Autowired
  UsuarioRepository usuarioRepository;

  @Override
  public CreateContaResponse create(ContaRequest contaRequest, Long id) {
    var usuario = usuarioRepository.getById(id);
    Conta conta = new Conta(contaRequest);
    conta.setNumero(gerarNumeroUnico());
    conta.setUsuario(usuario);
    contaRepository.save(conta);
    return new CreateContaResponse(conta);
  }

  @Override
  public ContaResponse getById(Long id) {
    Conta conta = contaRepository.findById(id).orElseThrow();
    return new ContaResponse(conta);
  }

  private Integer gerarNumeroUnico() {
    Random random = new Random();
    Integer numeroAleatorio;

    do {
      numeroAleatorio = random.nextInt(900000) + 100000;
    } while (!numeroEhUnico(numeroAleatorio));

    return numeroAleatorio;
  }

  private boolean numeroEhUnico(Integer numero) {
    return contaRepository.findByNumero(numero) == null;
  }
}
