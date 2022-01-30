package av3.compass.controller;

import java.net.URI;
import java.util.Optional;

import javax.persistence.EnumType;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import av3.compass.modelo.Estado;
import av3.compass.controller.dto.EstadoDto;
import av3.compass.controller.form.EstadoForm;
import av3.compass.modelo.Regiao;
import av3.compass.repository.EstadoRepository;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;


	@GetMapping
	@Cacheable(value = "listagemEstados")
	public Page<EstadoDto> list(@RequestParam(required = false)String regiao,
			@PageableDefault(sort="id", direction= Direction.ASC,page=0, size=10)Pageable paginacao) {

		if (regiao == null) {
			Page<Estado> estado = estadoRepository.findAll(paginacao);
			return EstadoDto.converter(estado);
		} else {

			String stringUp = regiao.toUpperCase();
			Regiao regiaoEnum = Regiao.valueOf(stringUp);

			Page<Estado> estado = estadoRepository.findByRegiao(regiaoEnum, paginacao);
			return EstadoDto.converter(estado);			
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EstadoDto> getById(@PathVariable Long id) {
		Optional<Estado> estado = estadoRepository.findById(id);
		if (estado.isPresent()) {
			return ResponseEntity.ok(new EstadoDto(estado.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/regiao-{regiao}")
	public Page<EstadoDto> listPorRegiao(@PathVariable String regiao, 
			@PageableDefault(sort="id", direction= Direction.ASC,page=0, size=10)Pageable paginacao)
	{
		String stringUp = regiao.toUpperCase();
		Regiao regiaoEnum = Regiao.valueOf(stringUp);

		Page<Estado> estado = estadoRepository.findByRegiao(regiaoEnum, paginacao);
		return EstadoDto.converter(estado);				
	}

	@GetMapping("/area")
	public Page<EstadoDto> listPorArea() {		
		Pageable paginacao = PageRequest.of(0, 10, Sort.by(Direction.DESC, "area"));
		Page<Estado> estado = estadoRepository.findAll(paginacao);
		return EstadoDto.converter(estado);		
	}

	@GetMapping("/populacao")
	public Page<EstadoDto> listPorPopulacao() {		
		Pageable paginacao = PageRequest.of(0, 10, Sort.by(Direction.DESC, "populacao"));
		Page<Estado> estado = estadoRepository.findAll(paginacao);
		return EstadoDto.converter(estado);		
	}



	@PostMapping
	@Transactional
	@CacheEvict(value = "listagemEstados", allEntries = true)
	public ResponseEntity<EstadoDto> cadastrar(@RequestBody @Valid EstadoForm form, UriComponentsBuilder uriBuilder) {
		Estado estado = form.converter();
		estadoRepository.save(estado);

		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(estado.getId()).toUri();
		return ResponseEntity.created(uri).body(new EstadoDto(estado));
	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listagemEstados", allEntries = true)
	public ResponseEntity<EstadoDto> atualizar(@PathVariable Long id, @RequestBody @Valid EstadoForm form) {
		Optional<Estado> optional = estadoRepository.findById(id);
		if (optional.isPresent()) {
			Estado estado = form.atualizar(id, estadoRepository);
			return ResponseEntity.ok(new EstadoDto(estado));
		}

		return ResponseEntity.notFound().build();
	}



	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listagemEstados", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Estado> optional = estadoRepository.findById(id);
		if (optional.isPresent()) {
			estadoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}
