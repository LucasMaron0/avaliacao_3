package av3.compass.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import av3.compass.config.validacao.ValidacaoIdadeEstado;





@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidacaoIdadeEstado.class)
public @interface ValidarIdade {
	
	String message () default "Idade do estádo nao compátivel com data de fundação.";
	
	Class<?>[] groups ()default{};
	public abstract Class<? extends Payload>[] payload () default {};

}
