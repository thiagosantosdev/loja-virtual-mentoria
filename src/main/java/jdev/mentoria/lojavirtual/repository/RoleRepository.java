package jdev.mentoria.lojavirtual.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jdev.mentoria.lojavirtual.model.Roles;

@Repository
@Transactional
public interface RoleRepository extends CrudRepository<Roles, Long>{

	void flush();

}

