package br.com.projetoA3.service.impl;

import br.com.projetoA3.dto.CreateUsuarioRequest;
import br.com.projetoA3.dto.UsuarioResetSenhaRequest;
import br.com.projetoA3.dto.CreateUsuarioResponse;
import br.com.projetoA3.dto.TextoResponse;
import br.com.projetoA3.dto.UpdateUsuarioRequest;
import br.com.projetoA3.dto.UsuarioResponse;
import br.com.projetoA3.model.ResetSenhaToken;
import br.com.projetoA3.model.TokenUtils;
import br.com.projetoA3.model.Usuario;
import br.com.projetoA3.repository.ResetSenhaTokenRepository;
import br.com.projetoA3.repository.UsuarioRepository;
import br.com.projetoA3.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UsuarioServiceImpl implements UsuarioService {

  private static final int TEMPO_VALIDADE_MINUTOS = 60;

  @Autowired
  UsuarioRepository usuarioRepository;

  @Autowired
  ResetSenhaTokenRepository resetSenhaTokenRepository;

  @Autowired
  EmailServiceImpl emailServiceImpl;

  @Override
  public CreateUsuarioResponse create(CreateUsuarioRequest usuarioRequest) {
    Usuario usuario = new Usuario(usuarioRequest);

    if (!isEmailValid(usuario.getEmail())) {
      throw new RuntimeException("E-mail inválido");
    }

    if (!isCPFValid(usuario.getCpf())) {
      throw new RuntimeException("CPF inválido");
    }

    usuarioRepository.save(usuario);
    return new CreateUsuarioResponse(usuario);
  }

  @Override
  public UsuarioResponse update(UpdateUsuarioRequest usuarioRequest, Long id) {

    var usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    if (!isEmailValid(usuario.getEmail())) {
      throw new RuntimeException("E-mail inválido");
    }

    usuario.setNome(usuarioRequest.getNome());
    usuario.setEmail(usuarioRequest.getEmail());
    usuario.setDataAtualizacao(LocalDateTime.now());
    usuarioRepository.save(usuario);

    return new UsuarioResponse(usuario);
  }

  @Override
  public TextoResponse findByEmail(String email) {
    if (!isEmailValid(email)) {
      throw new RuntimeException("E-mail inválido");
    }

    var usuario = usuarioRepository.getByEmail(email);
    if (usuario == null) {
      throw new RuntimeException("Usuário não encontrado");
    }

    String token = TokenUtils.gerarToken();
    LocalDateTime expiracao = LocalDateTime.now().plusMinutes(TEMPO_VALIDADE_MINUTOS);

    ResetSenhaToken resetPasswordToken = new ResetSenhaToken();
    resetPasswordToken.setToken(token);
    resetPasswordToken.setDataExpiracao(expiracao);
    resetPasswordToken.setStatus(true);
    resetPasswordToken.setUsuario(usuario);

    resetSenhaTokenRepository.save(resetPasswordToken);

    String assunto = "Redefinição de Senha";

    String corpo = "Olá " + usuario.getNome() + ",";
    corpo = corpo + "\n\nCopie o token abaixo e use para redefinir sua senha:\n\n" + token;
    corpo = corpo + "\n\nAtenciosamente,";
    corpo = corpo + "\n\nValo Bank.";

    emailServiceImpl.enviarEmailSimples(email, assunto, corpo);

    return new TextoResponse("E-mail com token enviado com sucesso.");
  }

  @Override
  public TextoResponse updateSenha(UsuarioResetSenhaRequest usuarioResetSenha) {
    var resetSenhaToken = resetSenhaTokenRepository.findByToken(usuarioResetSenha.getToken());
    if (resetSenhaToken == null) {
      throw new RuntimeException("Token não encontrado");
    }

    if (!resetSenhaToken.isStatus() || resetSenhaToken.getDataExpiracao().isBefore(LocalDateTime.now())) {
      throw new RuntimeException("Token expirado ou inválido");
    }

    var usuario = resetSenhaToken.getUsuario();
    usuario.setSenha(usuarioResetSenha.getSenha());
    usuario.setDataAtualizacao(LocalDateTime.now());
    usuarioRepository.save(usuario);

    resetSenhaToken.setStatus(false);
    resetSenhaTokenRepository.save(resetSenhaToken);

    String assunto = "Senha Alterada";

    String corpo = "Olá " + usuario.getNome() + ",";
    corpo = corpo + "\n\nSua senha foi alterada com sucesso.";
    corpo = corpo + "\n\nCaso tenha sido um engano, entre em contato conosco.";
    corpo = corpo + "\n\nAtenciosamente,";
    corpo = corpo + "\n\nValo Bank.";

    emailServiceImpl.enviarEmailSimples(usuario.getEmail(), assunto, corpo);

    return new TextoResponse("Senha alterada com sucesso.");
  }

  @Override
  public UsuarioResponse getById(Long id) {
    var usuario = usuarioRepository.findById(id).orElseThrow();
    return new UsuarioResponse(usuario);
  }

  private boolean isEmailValid(String email) {
    String regex = "^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[A-z]{2,4}$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(email);

    return matcher.matches();
  }

  private static boolean isCPFValid(String cpf) {
    if (cpf.length() != 11) {
      return false;
    }

    for (int i = 0; i < 11; i++) {
      if (!Character.isDigit(cpf.charAt(i))) {
        return false;
      }
    }

    return true;
  }

}
