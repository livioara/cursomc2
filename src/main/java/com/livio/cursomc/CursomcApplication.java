package com.livio.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.livio.cursomc.domain.Categoria;
import com.livio.cursomc.domain.Cidade;
import com.livio.cursomc.domain.Cliente;
import com.livio.cursomc.domain.Endereco;
import com.livio.cursomc.domain.Estado;
import com.livio.cursomc.domain.ItemPedido;
import com.livio.cursomc.domain.Pagamento;
import com.livio.cursomc.domain.PagamentoComBoleto;
import com.livio.cursomc.domain.PagamentoComCartao;
import com.livio.cursomc.domain.Pedido;
import com.livio.cursomc.domain.Produto;
import com.livio.cursomc.domain.enums.EstadoPagamento;
import com.livio.cursomc.domain.enums.TipoCliente;
import com.livio.cursomc.repositories.CategoriaRepository;
import com.livio.cursomc.repositories.CidadeRepository;
import com.livio.cursomc.repositories.ClienteRepository;
import com.livio.cursomc.repositories.EnderecoRepository;
import com.livio.cursomc.repositories.EstadoRepository;
import com.livio.cursomc.repositories.ItemPedidoRepository;
import com.livio.cursomc.repositories.PagamentoRepository;
import com.livio.cursomc.repositories.PedidoRepository;
import com.livio.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository; 
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository esatdoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Inform??tica");
		Categoria cat2 = new Categoria(null, "Escrit??rio");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1)); 
		
			
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		
		Estado est1 = new Estado (null, "Minas Gerais");
		Estado est2 = new Estado (null, "S??o Paulo");
		
		Cidade cid1 = new Cidade (null, "Uberl??ndia",est1);
		Cidade cid2 = new Cidade (null, "S??o Paulo",est2);
		Cidade cid3 = new Cidade (null, "Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));
		
		
		esatdoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
		
		Cliente cli1 = new Cliente(null,"Maria Silva", "maria@gmail.com","55555555588",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("111111111","22222222222"));
		
		Endereco e1 = new Endereco(null,"Rua Flores","300", "Apt 202", "Jardim","55555555555",cli1,cid1);
		Endereco e2 = new Endereco(null,"Av MAtos","105", "Casa", "Centro","66666666666",cli1,cid2);
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:22"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/01/2020 02:50"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2021 05:22"),null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.0);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2,80.0);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.0);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
		
		
		
		
		
		
				
	}

}
