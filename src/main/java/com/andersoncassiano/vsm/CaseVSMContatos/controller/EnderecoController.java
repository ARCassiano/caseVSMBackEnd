package com.andersoncassiano.vsm.CaseVSMContatos.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andersoncassiano.vsm.CaseVSMContatos.domain.Endereco;
import com.andersoncassiano.vsm.CaseVSMContatos.exceptions.EntidadeNotFoundException;
import com.andersoncassiano.vsm.CaseVSMContatos.repository.EnderecoRepository;

@RestController
@RequestMapping(path = "/api")
public class EnderecoController {
	private final EnderecoRepository repository;
	
	EnderecoController(EnderecoRepository repository){
		this.repository = repository;
	}
	
	@GetMapping("/enderecos")
	Iterable<Endereco> all() {
		return repository.findAll();
	}

	@PostMapping("/enderecos")
	Endereco newEndereco(@RequestBody Endereco endereco) {
		return repository.save(endereco);
	}

	@GetMapping("/enderecos/{id}")
	Endereco one(@PathVariable Long id) {

		return repository.findById(id).orElseThrow(() -> new EntidadeNotFoundException(id, Endereco.class.getName())); 
	}

	@PutMapping("/enderecos/{id}")
	Endereco replaceEndereco(@RequestBody Endereco novoEndereco, @PathVariable Long id) {

		return repository.findById(id)
			.map(endereco -> {
				endereco.setBairro(novoEndereco.getBairro());
				endereco.setCep(novoEndereco.getCep());
				endereco.setComplemento(novoEndereco.getComplemento());
				endereco.setContato(novoEndereco.getContato());
				endereco.setEndereco(novoEndereco.getEndereco());
				endereco.setLogradouro(novoEndereco.getLogradouro());
				endereco.setNumero(novoEndereco.getNumero());
				return repository.save(endereco);
			})
			.orElseGet(() -> {
				novoEndereco.setId(id);
				return repository.save(novoEndereco);
			});
	}

	@DeleteMapping("/enderecos/{id}")
	void deleteEndereco(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
