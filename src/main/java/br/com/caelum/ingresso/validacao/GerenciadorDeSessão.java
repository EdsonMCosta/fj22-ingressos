package br.com.caelum.ingresso.validacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessão {
	
	private List<Sessao> sessoesDaSala;
	
	public GerenciadorDeSessão(List<Sessao> sessoesDaSala) {
		this.sessoesDaSala = sessoesDaSala;
	}

	private boolean horarioIsConflitante(Sessao sessaoExistente, Sessao sessaoNova) {
		
		LocalDate hoje = LocalDate.now();
		
		LocalDateTime horarioSessaoExistente = sessaoExistente.getHorario().atDate(hoje);
		LocalDateTime terminoSessaoExistente = sessaoExistente.getHorarioTermino().atDate(hoje);
		LocalDateTime horarioSessaoNova = sessaoNova.getHorario().atDate(hoje);
		LocalDateTime terminoSessaoNova = sessaoNova.getHorarioTermino().atDate(hoje);
				
		boolean terminaAntes = terminoSessaoNova.isBefore(horarioSessaoExistente);
		boolean comecaDepois = terminoSessaoExistente.isBefore(horarioSessaoNova);
		
		if(terminaAntes || comecaDepois) {
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