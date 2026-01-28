package cardapiodigital.cardapio.repository;

import cardapiodigital.cardapio.model.Pedido;
import cardapiodigital.cardapio.model.enums.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByStatus(StatusPedido status);

    List<Pedido> findByMesaAndStatusNot(Integer mesa, StatusPedido status);

    List<Pedido> findByStatusIn(List<StatusPedido> statuses);
}
