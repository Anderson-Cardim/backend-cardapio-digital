package cardapiodigital.cardapio.dto;


import cardapiodigital.cardapio.model.ItemPedido;
import cardapiodigital.cardapio.model.Pedido;
import cardapiodigital.cardapio.model.Produto;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
public class ItemPedidoResponseDTO {

    private Long id;
    private String nomeProduto;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private String observacoes;

    public ItemPedidoResponseDTO(ItemPedido itemPedido) {
        this.id = itemPedido.getId();
        this.quantidade = itemPedido.getQuantidade();
        this.precoUnitario = itemPedido.getPrecoUnitario();
        this.observacoes = itemPedido.getObservacoes();

        if (itemPedido.getProduto() != null) {
            this.nomeProduto = itemPedido.getProduto().getNome();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}