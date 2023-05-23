package com.rodrigo.ProdManager.resources;

import com.rodrigo.ProdManager.dtos.ListarPedidoDTO;
import com.rodrigo.ProdManager.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidosResource {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<Page<ListarPedidoDTO>> findAll(Pageable paginacao){

        return ResponseEntity.ok().body(pedidoService.findAll(paginacao));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ListarPedidoDTO> findAll(@PathVariable Integer id){
        return ResponseEntity.ok().body(pedidoService.findById(id));
    }

}
