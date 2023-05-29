package com.rodrigo.ProdManager.services;

import com.rodrigo.ProdManager.domain.ItemPedido;
import com.rodrigo.ProdManager.domain.PagamentoBoleto;
import com.rodrigo.ProdManager.domain.Pedido;
import com.rodrigo.ProdManager.domain.Produto;
import com.rodrigo.ProdManager.dtos.InserirPedidoDTO;
import com.rodrigo.ProdManager.dtos.ListarPedidoDTO;
import com.rodrigo.ProdManager.enums.EstadoPagamento;
import com.rodrigo.ProdManager.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmailService emailService;

    public Page<ListarPedidoDTO> findAll(Pageable paginacao) {
        return pedidoRepository.findAll(paginacao).map(ListarPedidoDTO::new);
    }

    public ListarPedidoDTO findById(Integer id) {
        return new ListarPedidoDTO(pedidoRepository.getReferenceById(id));
    }


    public ListarPedidoDTO create(Pedido obj) {
        var endereco = enderecoRepository.getReferenceById(obj.getEnderecoEntrega().getId());

        var cliente = clienteRepository.getReferenceById(obj.getCliente().getId());

        obj.setEnderecoEntrega(endereco);
        obj.setCliente(cliente);
        obj.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        obj.setInstante(new Date());

        if (obj.getPagamento() instanceof PagamentoBoleto) {
            var pgto = (PagamentoBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pgto, obj.getInstante());
        }

        var pedido = pedidoRepository.save(obj);
        pagamentoRepository.save(obj.getPagamento());


        for (ItemPedido itemPedido : obj.getItems()) {
            itemPedido.setDesconto(0.0);
            itemPedido.setPreco(produtoRepository.getReferenceById(itemPedido.getProduto().getId()).getPreco() * itemPedido.getQuantidade());
            itemPedido.getProduto().setNome(produtoRepository.getReferenceById(itemPedido.getProduto().getId()).getNome());
            itemPedido.getProduto().setPreco(produtoRepository.getReferenceById(itemPedido.getProduto().getId()).getPreco());
            itemPedido.setPedido(obj);
        }


        itemPedidoRepository.saveAll(obj.getItems());
        emailService.sendOrderConfirmationEmail(obj);
        return new ListarPedidoDTO(pedido);
    }
}
