package cardapiodigital.cardapio.service;

import cardapiodigital.cardapio.dto.ProdutoRequestDTO;
import cardapiodigital.cardapio.dto.ProdutoResponseDTO;
import cardapiodigital.cardapio.model.Categoria;
import cardapiodigital.cardapio.model.Produto;
import cardapiodigital.cardapio.repository.CategoriaRepository;
import cardapiodigital.cardapio.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto salvarProduto(ProdutoRequestDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        produto.setUrlImagem(produtoDTO.getUrlImagem());
        produto.setDisponivel(produtoDTO.getDisponivel());

        Categoria categoria = categoriaRepository.findById(produtoDTO.getId_categoria())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        produto.setCategoria(categoria);
        return produtoRepository.save(produto);
    }

    public List<ProdutoResponseDTO> listarTodosOsProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        System.out.println("Produtos encontrados no banco: " + produtos.size());

        return produtos.stream()
                .map(p -> {
                    ProdutoResponseDTO dto = new ProdutoResponseDTO(p);
                    System.out.println("DTO Gerado para: " + dto.getNome());
                    return dto;
                })
                .toList();
    }

    public List<ProdutoResponseDTO> listarProdutosDisponiveis() {
        List<Produto> produtos = produtoRepository.findByDisponivelTrue();

        return produtos.stream()
                .map(ProdutoResponseDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public List<ProdutoResponseDTO> listarPorCategoria(Long id) {
        List<Produto> produto = produtoRepository.findByCategoriaId(id);
        return produto.stream().map(ProdutoResponseDTO::new).collect(Collectors.toList());
    }

    public Produto atualizarProduto(Long id, ProdutoRequestDTO produtoDTO) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));

        produtoExistente.setNome(produtoDTO.getNome());
        produtoExistente.setDescricao(produtoDTO.getDescricao());
        produtoExistente.setPreco(produtoDTO.getPreco());
        produtoExistente.setUrlImagem(produtoDTO.getUrlImagem());
        produtoExistente.setDisponivel(produtoDTO.getDisponivel());

        Categoria categoria = categoriaRepository.findById(produtoDTO.getId_categoria())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        produtoExistente.setCategoria(categoria);

        return produtoRepository.save(produtoExistente);
    }

    public void deletarProduto(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado com ID: " + id);
        }
        produtoRepository.deleteById(id);
    }

}
