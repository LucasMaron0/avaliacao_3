package av3.compass.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;
import av3.compass.modelo.Estado;
import av3.compass.modelo.Regiao;





public interface EstadoRepository  extends JpaRepository<Estado, Long> {
	
	Page<Estado> findByRegiao(@RequestParam("regiao") Regiao regiao, Pageable paginacao);
	
	
	
		
}
