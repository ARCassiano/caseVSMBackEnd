package com.andersoncassiano.vsm.CaseVSMContatos.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntidadeNotFoundException extends RuntimeException {

	public EntidadeNotFoundException(Long id, String entidade) {
		super("Não foi possível encontrar o "+ entidade.replace("com.andersoncassiano.vsm.CaseVSMContatos.domain.", "") +" " + id);
	}
}