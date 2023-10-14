package jdev.mentoria.lojavirtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jdev.mentoria.lojavirtual.model.Roles;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Roles, Long>{

}
