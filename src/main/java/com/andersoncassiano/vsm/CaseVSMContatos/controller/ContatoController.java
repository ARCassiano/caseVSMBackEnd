package com.andersoncassiano.vsm.CaseVSMContatos.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.andersoncassiano.vsm.CaseVSMContatos.domain.Contato;
import com.andersoncassiano.vsm.CaseVSMContatos.repository.ContatoRepository;
import com.andersoncassiano.vsm.CaseVSMContatos.exceptions.*;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ContatoController {

	private final ContatoRepository repository;
	
	ContatoController(ContatoRepository repository){
		this.repository = repository;
	}
	
	@GetMapping("/contatos")
	Iterable<Contato> all(@RequestParam(name = "cpf", defaultValue = "", required = false) String cpf) {
		if(cpf.isEmpty())
			return repository.findAll();
		else
			return repository.findByCPF(cpf);
	} 	
	
	@PostMapping("/contatos")
	Contato newContato(@RequestBody @Valid Contato contato) {
		return repository.save(contato);
	}

	@GetMapping("/contatos/{id}")
	Contato one(@PathVariable Long id) {

		return repository.findById(id).orElseThrow(() -> new EntidadeNotFoundException(id, Contato.class.getName())); 
	}

	@PutMapping("/contatos/{id}")
	Contato replaceContato(@RequestBody @Valid Contato novoContato, @PathVariable Long id) {

		return repository.findById(id)
			.map(contato -> {
				contato.setNome(novoContato.getNome());
				contato.setEmail(novoContato.getEmail());
				contato.setTelefone(novoContato.getTelefone());
				contato.setEnderecos(novoContato.getEnderecos());
				contato.setCpf(novoContato.getCpf());
				contato.setDataNascimento(novoContato.getDataNascimento());
				return repository.save(contato);
			})
			.orElseGet(() -> {
				novoContato.setId(id);
				return repository.save(novoContato);
			});
	}

	@DeleteMapping("/contatos/{id}")
	void deleteContato(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
