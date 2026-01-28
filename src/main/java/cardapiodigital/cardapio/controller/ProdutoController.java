package cardapiodigital.cardapio.controller;

import cardapiodigital.cardapio.dto.ProdutoRequestDTO;
import cardapiodigital.cardapio.dto.ProdutoResponseDTO;
import cardapiodigital.cardapio.model.Produto;
import cardapiodigital.cardapio.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody @Valid ProdutoRequestDTO produtoDTO) {
       Produto novoProduto = produtoService.salvarProduto(produtoDTO);
       return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProdutoResponseDTO>> listarProdutos() {
        List<ProdutoResponseDTO> produtos;
        produtos = produtoService.listarTodosOsProdutos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/listarDisponivel")
    public ResponseEntity<List<ProdutoResponseDTO>> listarProdutosDisponiveis() {
        List<ProdutoResponseDTO> produtos;
        produtos = produtoService.listarProdutosDisponiveis();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<ProdutoResponseDTO>> listarPorCategoria(@PathVariable Long id) {
        List<ProdutoResponseDTO> produtos = produtoService.listarPorCategoria(id);
        return ResponseEntity.ok(produtos);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Produto> atualizarProduto(
            @PathVariable Long id,
            @RequestBody @Valid ProdutoRequestDTO produtoDTO
    ) {
        Produto produtoAtualizado = produtoService.atualizarProduto(id, produtoDTO);

        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

}
