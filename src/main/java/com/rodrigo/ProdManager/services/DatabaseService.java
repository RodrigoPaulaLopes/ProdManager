package com.rodrigo.ProdManager.services;

import com.rodrigo.ProdManager.domain.*;
import com.rodrigo.ProdManager.enums.EstadoPagamento;
import com.rodrigo.ProdManager.enums.PerfilCliente;
import com.rodrigo.ProdManager.enums.TipoCliente;
import com.rodrigo.ProdManager.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DatabaseService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;
    public void instantiateTestDatabase() throws ParseException {
        var c1 = new Categoria(null, "Eletrônicos");
        var c2 = new Categoria(null, "Games");
        var c3 = new Categoria(null, "Moveis");

        categoriaRepository.saveAll(Arrays.asList(c1, c2, c3));

        var p1 = new Produto("Resident Evil 4 Remake", 230.00);
        var p2 = new Produto("Playstation 5", 230.00);
        var p3 = new Produto("Mesa Para Notebook", 230.00);


        p1.getCategorias().addAll(Arrays.asList(c1, c2));
        p2.getCategorias().addAll(Arrays.asList(c1));
        p3.getCategorias().addAll(Arrays.asList(c3));

        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

        var estado = new Estado(null, "Rio De Janeiro");

        var cidade1 = new Cidade(null, "Nova iguaçu", estado);
        var cidade2 = new Cidade(null, "Nilopolis", estado);
        var cidade3 = new Cidade(null, "Mesquita", estado);

        estado.getCidades().addAll(Arrays.asList(cidade1, cidade2, cidade3));
        estadoRepository.save(estado);
        cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));


        var cli1 = new Cliente(null, "Rodrigo de paula", "rodrigolopesti1@gmail.com", "159.853.107-79", TipoCliente.PESSOAFISICA);
        cli1.setSenha(bCryptPasswordEncoder.encode("1234"));
        cli1.addPerfilCliente(PerfilCliente.ADMIN);
        var cli2 = new Cliente(null, "Camille Marques", "millemzda@gmail.com", "033.525.788-80", TipoCliente.PESSOAFISICA);
        cli2.setSenha(bCryptPasswordEncoder.encode("1234"));
        cli2.addPerfilCliente(PerfilCliente.CLIENTE);
        cli1.getTelefones().addAll(Arrays.asList("(21) 99604-1143", "(21) 99489-4638"));
        cli2.getTelefones().addAll(Arrays.asList("(21) 99604-1143", "(21) 99489-4638"));

        var endereco1 = new Endereco(null, "Rua inga", "4", "casa 1", "carmari", "26023-140", cidade1, cli1);
        var endereco2 = new Endereco(null, "Rua inga", "4", "casa 1", "carmari", "26023-140", cidade1, cli2);
        var endereco3 = new Endereco(null, "Rua dona joaquina sampaio", "257", "casa 1", "vila neli", "26023-140", cidade1, cli2);

        cli1.getEnderecos().add(endereco1);
        cli2.getEnderecos().addAll(Arrays.asList(endereco3, endereco2));

        clienteRepository.saveAll(Arrays.asList(cli1, cli2));
        enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2, endereco3));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        var ped1 = new Pedido(null, sdf.parse("30/09/2023 10:32"), endereco1, cli1);
        var ped2 = new Pedido(null, sdf.parse("30/09/2023 10:32"), endereco2, cli2);

        var pagamento1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 2);
        var pagamento2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("22/05/2023 08:50"), null);

        ped1.setPagamento(pagamento1);
        ped2.setPagamento(pagamento2);

        cli1.getPedidos().add(ped1);
        cli2.getPedidos().add(ped2);

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pagamento1, pagamento2));


        var ip1 = new ItemPedido(p1, ped1, 0.00, 1);
        var ip2 = new ItemPedido(p3, ped1, 0.00, 2);
        var ip3 = new ItemPedido(p2, ped2, 100.00, 1);

        ip1.setPreco(p1.getPreco() * ip1.getQuantidade());
        ip2.setPreco(p3.getPreco() * ip2.getQuantidade());
        ip3.setPreco(p2.getPreco() * ip3.getQuantidade());

        ped1.getItems().addAll(Arrays.asList(ip1, ip2));
        ped2.getItems().add(ip3);

        p1.getItems().add(ip1);
        p2.getItems().add(ip3);
        p3.getItems().add(ip2);

        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
    }
}
