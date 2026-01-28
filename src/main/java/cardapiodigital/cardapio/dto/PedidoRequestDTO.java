package cardapiodigital.cardapio.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class PedidoRequestDTO {
    @NotNull(message = "O número da mesa é obrigatório")
    private Integer mesa;

    private List<ItemPedidoRequestDTO> itens;

    private String nomeCliente;

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Integer getMesa() {
        return mesa;
    }

    public void setMesa(Integer mesa) {
        this.mesa = mesa;
    }

    public List<ItemPedidoRequestDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoRequestDTO> itens) {
        this.itens = itens;
    }
}
