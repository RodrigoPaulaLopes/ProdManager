package com.rodrigo.ProdManager.repository;

import com.rodrigo.ProdManager.domain.Pagamento;
import com.rodrigo.ProdManager.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}
