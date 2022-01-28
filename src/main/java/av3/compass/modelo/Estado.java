package av3.compass.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Estado {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	@Enumerated(EnumType.STRING)
	private Regiao regiao;
	private long populacao;
	private String capital;
	private long area;
	private LocalDate fundacao;
	private int idade;	

	public Estado () {

	}

	public Estado(String nome, Regiao regiao, long populacao, String capital, long area, LocalDate fundacao, int idade) {
		this.nome = nome;
		this.regiao = regiao;
		this.populacao = populacao;
		this.capital = capital;
		this.area = area;
		this.fundacao = fundacao;
		this.idade= idade;		
	}

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
	public Regiao getRegiao() {
		return regiao;
	}
	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}
	public long getPopulacao() {
		return populacao;
	}
	public void setPopulacao(long populacao) {
		this.populacao = populacao;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public long getArea() {
		return area;
	}
	public void setArea(long area) {
		this.area = area;
	}

	public LocalDate getFundacao() {
		return fundacao;
	}

	public void setFundacao(LocalDate fundacao) {
		this.fundacao = fundacao;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}
	
}
