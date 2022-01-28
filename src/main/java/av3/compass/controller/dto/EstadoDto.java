package av3.compass.controller.dto;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import av3.compass.modelo.Estado;
import av3.compass.modelo.Regiao;

public class EstadoDto {


	private long id;
	private String nome;
	private Regiao regiao;
	private long populacao;
	private String capital;
	private long area;
	private LocalDate fundacao;
	private int idade;



	public EstadoDto (Estado estado) {
		this.id = estado.getId();
		this.nome = estado.getNome();
		this.regiao = estado.getRegiao();
		this.populacao = estado.getPopulacao();
		this.capital = estado.getCapital();
		this.area = estado.getArea();
		this.fundacao= estado.getFundacao();
		this.idade=estado.getIdade();
	}
	

	public static Page<EstadoDto> converter(Page <Estado> estados){
		return estados.map(EstadoDto::new);

	}


	public long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public Regiao getRegiao() {
		return regiao;
	}
	public long getPopulacao() {
		return populacao;
	}

	public String getCapital() {
		return capital;
	}

	public long getArea() {
		return area;
	}
	public LocalDate getFundacao() {
		return fundacao;
	}
	public int getIdade() {
		return idade;
	}
	
	
}
