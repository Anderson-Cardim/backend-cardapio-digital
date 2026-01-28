package cardapiodigital.cardapio.dto;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoriaRequestDTO {
    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotNull(message = "O campo ativo é obrigatório")
    private Boolean ativo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
