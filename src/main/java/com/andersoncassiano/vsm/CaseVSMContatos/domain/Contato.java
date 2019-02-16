package com.andersoncassiano.vsm.CaseVSMContatos.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
public class Contato {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotBlank(message = "O nome do contato é obrigatório")
	@Column(nullable = false)
	private String nome;
	
	@NotBlank(message = "O CPF do contato é obrigatório")
	@Column(nullable = false)
	private String cpf;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;
	
	private String email;
	
	private String telefone;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties({"contato"})
	@OneToMany(mappedBy = "contato", cascade = CascadeType.ALL, orphanRemoval=true)
	@Valid
	private List<Endereco> enderecos;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		cpf = cpf.replace(".", "").replace("-", "");
		
		this.cpf = cpf;
	}
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		enderecos.parallelStream().forEach(endereco -> endereco.setContato(this));

		if(this.enderecos == null)
			this.enderecos = enderecos;
		else {
			this.enderecos.clear();
			this.enderecos.addAll(enderecos);
		}
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
}
