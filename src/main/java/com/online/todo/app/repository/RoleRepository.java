package com.online.todo.app.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.todo.app.model.Role;

/**
 * Role Repository
 * 
 * @author Manoj Pandey
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	@Cacheable("roles")
	Role findOneByName(String name);
}
