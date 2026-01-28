package cardapiodigital.cardapio.repository;

import cardapiodigital.cardapio.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByDisponivelTrue();

    List<Produto> findByCategoriaId(Long categoriaId);

    @Override
    void deleteById(Long aLong);
}
