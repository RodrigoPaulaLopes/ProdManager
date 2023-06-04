package com.rodrigo.ProdManager.repository;


import com.rodrigo.ProdManager.domain.Cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    UserDetails findByEmail(String email);

    Page<Cliente> findById(Pageable paginacao, Long id);
    @Query("SELECT c FROM Cliente c WHERE c.email = :email")
    Cliente buscarPeloEmail(String email);
}
