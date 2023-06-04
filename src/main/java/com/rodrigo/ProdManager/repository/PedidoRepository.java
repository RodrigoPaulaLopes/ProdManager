package com.rodrigo.ProdManager.repository;

import com.rodrigo.ProdManager.domain.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    Page<Pedido> findByClienteId(Pageable paginacao, Long id);
}
