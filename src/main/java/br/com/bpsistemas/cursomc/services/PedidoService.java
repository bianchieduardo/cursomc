package br.com.bpsistemas.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bpsistemas.cursomc.domain.Pedido;
import br.com.bpsistemas.cursomc.repositories.PedidoRepository;
import br.com.bpsistemas.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;

	public Pedido find(Integer id) {
		 Pedido obj = repo.findOne(id);		 
		 if(obj == null) {
			 throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					 +", Tipo: " + Pedido.class.getName());
		 }
		 return obj;
	}
	
}