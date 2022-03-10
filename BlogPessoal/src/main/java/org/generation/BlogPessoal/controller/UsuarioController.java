package org.generation.BlogPessoal.controller;

import java.util.List;
import java.util.Optional;

import org.generation.BlogPessoal.model.UserLogin;
import org.generation.BlogPessoal.model.Usuario;
import org.generation.BlogPessoal.repository.UsuarioRepository;
import org.generation.BlogPessoal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "", allowedHeaders = "")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/logar")
	public ResponseEntity<UserLogin> Autentication(@RequestBody Optional<UserLogin> user) {
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario) {
		return usuarioService.CadastrarUsuario(usuario).map(resp -> ResponseEntity.status(201).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> getAll() {
		return ResponseEntity.ok(usuarioRepository.findAll());
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> put(@RequestBody Usuario usuario) {
		return ResponseEntity.ok(usuarioRepository.save(usuario));
	}

}