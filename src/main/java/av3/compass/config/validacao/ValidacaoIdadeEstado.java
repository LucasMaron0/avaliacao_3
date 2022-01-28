package av3.compass.config.validacao;

import java.time.LocalDate;
import java.time.Period;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import av3.compass.annotations.ValidarIdade;
import av3.compass.controller.form.EstadoForm;




public class ValidacaoIdadeEstado implements ConstraintValidator<ValidarIdade, EstadoForm> {

	@Override
	public boolean isValid(EstadoForm value, ConstraintValidatorContext context) {				
		int anos = Period.between(value.getFundacao(), LocalDate.now()).getYears();
		return value.getIdade()==anos;
		
	}

}
