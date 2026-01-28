package cardapiodigital.cardapio.dto;

import cardapiodigital.cardapio.model.Pedido;
import cardapiodigital.cardapio.model.enums.StatusPedido;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PedidoResponseDTO {

    private Long id;
    private LocalDateTime dataPedido;
    private BigDecimal valorTotal;
    private Integer mesa;
    private StatusPedido status = StatusPedido.PENDENTE;
    private List<ItemPedidoResponseDTO> itens = new ArrayList<>();
    private String nomeCliente;

    public PedidoResponseDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.dataPedido = pedido.getDataPedido();
        this.valorTotal = pedido.getValorTotal();
        this.mesa = pedido.getMesa();
        this.nomeCliente = pedido.getNomeCliente();
        this.status = pedido.getStatus();
        this.itens = pedido.getItens().stream()
                .map(ItemPedidoResponseDTO::new)
                .toList();
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getMesa() {
        return mesa;
    }

    public void setMesa(Integer mesa) {
        this.mesa = mesa;
    }

    public List<ItemPedidoResponseDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoResponseDTO> itens) {
        this.itens = itens;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }
}