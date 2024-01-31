package jdev.mentoria.lojavirtual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jdev.mentoria.lojavirtual.model.Roles;

@Repository
@Transactional
public interface RoleRepository extends CrudRepository<Roles, Long>{

	@Query("select a from Roles a where upper(trim(a.descricao)) like %?1%")
	List<Roles> buscarAcessoDesc(String desc);

}

