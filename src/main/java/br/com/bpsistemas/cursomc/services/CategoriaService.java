package br.com.bpsistemas.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.bpsistemas.cursomc.domain.Categoria;
import br.com.bpsistemas.cursomc.dto.CategoriaDTO;
import br.com.bpsistemas.cursomc.repositories.CategoriaRepository;
import br.com.bpsistemas.cursomc.services.exceptions.DataIntegrityException;
import br.com.bpsistemas.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		 Categoria obj = repo.findOne(id);		 
		 if(obj == null) {
			 throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					 +", Tipo: " + Categoria.class.getName());
		 }
		 return obj;
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {	
		try {
			repo.delete(find(id));
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}		
	}
	
	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);		
	}
	
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
	
}
