package com.andersoncassiano.vsm.CaseVSMContatos.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotBlank(message = "O logradouro é obrigatório")
	@Column(nullable = false)
	private String logradouro;
	
	@NotBlank(message = "O endereço é obrigatório")
	@Column(nullable = false)
	private String endereco;
	
	@NotBlank(message = "O número é obrigatório")
	@Column(nullable = false)
	private String numero;
	
	@NotBlank(message = "O bairro é obrigatório")
	@Column(nullable = false)
	private String bairro;
	
	@NotBlank(message = "O CEP é obrigatório")
	@Column(nullable = false)
	private String cep;
	
	private String complemento;
	
	@JsonIgnoreProperties({"enderecos"})
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@ManyToOne
	private Contato contato;
	
	public Endereco() {}
	
	public Endereco(String logradouro, String endereco, String numero, String bairro, String cep, String complemento,
			Contato contato) {
		super();
		this.setLogradouro(logradouro);
		this.setEndereco(endereco);
		this.setNumero(numero);
		this.setBairro(bairro);
		this.setCep(cep);
		this.setComplemento(complemento);
		this.setContato(contato);
	}
	
	public Endereco(String logradouro, String endereco, String numero, String bairro, String cep, String complemento) {
		super();
		this.setLogradouro(logradouro);
		this.setEndereco(endereco);
		this.setNumero(numero);
		this.setBairro(bairro);
		this.setCep(cep);
		this.setComplemento(complemento);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {		
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {		
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		cep = cep.replace(".", "").replace("-", "");
		
		this.cep = cep;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {		
		this.contato = contato;
	}
	
}
