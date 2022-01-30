package av3.compass.controller.form;


import java.time.LocalDate;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;



import av3.compass.annotations.ValidarIdade;
import av3.compass.modelo.Estado;
import av3.compass.modelo.Regiao;
import av3.compass.repository.EstadoRepository;




@ValidarIdade
public class EstadoForm {

	@NotNull@NotEmpty @Pattern(regexp="^[A-Za-z ]*$",message = "Digite um nome válido (apenas letras)")
	private String nome;

	@NotNull
	private Regiao regiao;

	@NotNull  @Min(0)@Max(value = Long.MAX_VALUE)
	private long populacao;

	@NotNull@NotEmpty @Pattern(regexp="^[A-Za-z ]*$",message = "Digite uma capital válida (apenas letras)")
	private String capital;

	@NotNull  @Min(0)@Max(value = Long.MAX_VALUE)
	private long area;

	@NotNull 
	private LocalDate fundacao;

	@NotNull @Min(0)@Max(value = Long.MAX_VALUE)
	private int idade;



	public Estado converter () {
		return new Estado(nome,regiao,populacao,capital,area,fundacao,idade);
	}

	public Estado atualizar(Long id, EstadoRepository estadoRepository) {
		Estado estado = estadoRepository.getOne(id);
		estado.setNome(this.nome);
		estado.setRegiao(this.regiao);
		estado.setPopulacao(this.populacao);
		estado.setCapital(capital);
		estado.setArea(this.area);
		estado.setFundacao(this.fundacao);
		estado.setIdade(this.idade);

		return estado;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}
	public void setPopulacao(long populacao) {
		this.populacao = populacao;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public void setArea(long area) {
		this.area = area;
	}

	public void setFundacao(LocalDate fundacao) {
		this.fundacao = fundacao;
	}
	public void setIdade(int idade) {
		this.idade = idade;
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
