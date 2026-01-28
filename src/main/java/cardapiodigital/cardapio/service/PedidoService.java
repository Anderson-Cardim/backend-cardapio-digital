package cardapiodigital.cardapio.service;

import cardapiodigital.cardapio.dto.ItemPedidoRequestDTO;
import cardapiodigital.cardapio.dto.PedidoRequestDTO;
import cardapiodigital.cardapio.dto.PedidoResponseDTO;
import cardapiodigital.cardapio.model.ItemPedido;
import cardapiodigital.cardapio.model.Pedido;
import cardapiodigital.cardapio.model.Produto;
import cardapiodigital.cardapio.model.enums.StatusPedido;
import cardapiodigital.cardapio.repository.PedidoRepository;
import cardapiodigital.cardapio.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Pedido salvar(PedidoRequestDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setMesa(dto.getMesa());
        pedido.setStatus(StatusPedido.PENDENTE);
        pedido.setNomeCliente(dto.getNomeCliente());

        BigDecimal totalPedido = BigDecimal.ZERO;

        for (ItemPedidoRequestDTO itemDto : dto.getItens()) {
            Produto produto = produtoRepository.findById(itemDto.getId_produto())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            ItemPedido item = new ItemPedido();
            item.setProduto(produto);
            item.setQuantidade(itemDto.getQuantidade());
            item.setPrecoUnitario(produto.getPreco());
            item.setObservacoes(itemDto.getObservacoes());

            BigDecimal subtotal = produto.getPreco().multiply(BigDecimal.valueOf(itemDto.getQuantidade()));
            totalPedido = totalPedido.add(subtotal);

            pedido.adicionarItem(item);
        }

        pedido.setValorTotal(totalPedido);
        return pedidoRepository.save(pedido);
    }

    public List<PedidoResponseDTO> listarTodosPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        System.out.println("Pedidos encontrados no banco: " + pedidos.size());

        return pedidos.stream()
                .map(PedidoResponseDTO::new)
                .toList();
    }

    public List<PedidoResponseDTO> listarParaCozinha() {
        return pedidoRepository.findByStatusIn(List.of(StatusPedido.PENDENTE, StatusPedido.PREPARANDO))
                .stream()
                .map(PedidoResponseDTO::new).toList();
    }

    public List<PedidoResponseDTO> listarPorStatus(StatusPedido statusPedido) {
        return pedidoRepository.findByStatus(statusPedido)
                .stream()
                .map(PedidoResponseDTO::new).toList();
    }

    public List<PedidoResponseDTO> listarPorMesa(Integer mesa) {
        return pedidoRepository.findByMesaAndStatusNot(mesa, StatusPedido.PRONTO)
                .stream()
                .map(PedidoResponseDTO::new).toList();
    }

    @Transactional
    public PedidoResponseDTO atualizarStatus(Long id, StatusPedido novoStatus) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if (pedido.getStatus() == StatusPedido.FINALIZADO || pedido.getStatus() == StatusPedido.CANCELADO) {
            throw new RuntimeException("Não é possível alterar o status de um pedido que já foi finalizado ou cancelado.");
        }
        pedido.setStatus(novoStatus);
        return new PedidoResponseDTO(pedidoRepository.save(pedido));
    }
}