package cardapiodigital.cardapio.dto;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ItemPedidoRequestDTO {

    @NotNull(message = "O ID do produto é obrigatório")
    private Long id_produto;

    @NotNull(message = "A quantidade deve ser informada")
    @Min(value = 1, message = "A quantidade mínima é 1")
    private Integer quantidade;

    @Column(length = 255)
    private String observacoes;

    public Long getId_produto() {
        return id_produto;
    }

    public void setId_produto(Long id_produto) {
        this.id_produto = id_produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
