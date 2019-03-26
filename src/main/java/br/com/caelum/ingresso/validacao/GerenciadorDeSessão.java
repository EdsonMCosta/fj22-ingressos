package br.com.caelum.ingresso.validacao;

import java.util.List;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessão {
	
	private List<Sessao> sessoesDaSala;
	
	public GerenciadorDeSessão(List<Sessao> sessoesDaSala) {
		this.sessoesDaSala = sessoesDaSala;
	}

	private boolean horarioIsConflitante(Sessao sessaoNova, Sessao sessaoExistente) {
		boolean sessaoNovaTerminaAntes = 
				sessaoNova.getHorarioTermino().isBefore(sessaoExistente.getHorario());
		boolean sessaoNovaComecaDepois = 
				sessaoNova.getHorario().isAfter(sessaoExistente.getHorarioTermino());
		
		if(sessaoNovaTerminaAntes || sessaoNovaComecaDepois) {
			return false; //não tem conflito
		}
		return true; //tem conflito
	}


public boolean cabe(Sessao sessaoNova) {
	
	return sessoesDaSala.stream().noneMatch(
			sessaoExistente -> horarioIsConflitante(
					sessaoExistente, sessaoNova));
	
	}
}