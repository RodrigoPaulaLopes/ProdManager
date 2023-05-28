package com.rodrigo.ProdManager.resources;

import com.rodrigo.ProdManager.domain.Pedido;
import com.rodrigo.ProdManager.dtos.InserirPedidoDTO;
import com.rodrigo.ProdManager.dtos.ListarPedidoDTO;
import com.rodrigo.ProdManager.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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

    @PostMapping
    public ResponseEntity<ListarPedidoDTO> create(@RequestBody Pedido dados, UriComponentsBuilder builder){
        ListarPedidoDTO pedido = pedidoService.create(dados);
        var uri = builder.path("/pedidos/{id}").buildAndExpand(pedido.id()).toUri();

        return ResponseEntity.created(uri).body(pedido);
//        return ResponseEntity.ok().body(dados);
    }


}
