# 🍕 Cardápio API - Gestão de Pedidos em Tempo Real

### 📋 Descrição do Projeto

**Cardápio API** é uma plataforma que automatiza o fluxo de pedidos de um restaurante. O sistema permite o gerenciamento do cardápio, registro de pedidos vinculados a mesas/clientes e o controle em tempo real do status de preparação na cozinha.

### Principais Funcionalidades

✅ **Gestão de Produtos** - Cadastro, edição e listagem de itens do cardápio.
👥 **Identificação de Clientes** - Registro de pedidos por mesa e nome do cliente.
💰 **Cálculo de Totais Automatizado** - O sistema busca o preço unitário no banco e calcula subtotais e totais.
📊 **Fluxo de Cozinha** - Atualização de status (Pendente, Preparando, Pronto, Entregue, Finalizado).
📝 **Observações nos Itens** - Suporte para notas personalizadas em cada item do pedido.

### 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **MySQL** (ou H2 para ambiente de teste)
- **Lombok**
- **Jakarta Validation** (Bean Validation)
- **Maven**

### 📦 Pré-requisitos

Antes de começar, certifique-se de ter instalado:
- **Java 17** ou superior
- **Maven 3.6+**
- **Git**

---

### 🚀 Como Executar o Projeto

1️⃣ **Clone o Repositório**
bash
git clone <url-do-seu-repositorio>
cd cardapio-api
2️⃣ Build do ProjetoBashmvn clean install
3️⃣ Executar a AplicaçãoBashmvn spring-boot:run
4️⃣ Verificar se está rodandoA aplicação estará disponível em:
URL Base: http://localhost:8080

###n🔑 Endpoints Principais

📦 **Produtos**
Método,Endpoint,Descrição
GET,/produtos,Lista todos os produtos cadastrados
POST,/produtos,Cadastrar novo produto no cardápio

📝 **Pedidos**
Método,Endpoint,Descrição
POST,/pedidos,Criar um novo pedido (Mesa + Cliente + Itens)
GET,/pedidos/listarTodos,Listar todos os pedidos com detalhes e totais
PATCH,/pedidos/{id}/status,Atualizar status do pedido (via RequestParam novoStatus)

### 📌 Ciclo de Vida do Pedido
O sistema utiliza os seguintes estados para garantir a organização do atendimento:
**PENDENTE:** Pedido recém-criado, aguardando início da produção.
**PREPARANDO:** O pedido já está sendo manipulado pela cozinha.
**PRONTO:** Prato finalizado, aguardando o garçom para entrega.
**ENTREGUE:** O cliente já recebeu o produto na mesa.
**FINALIZADO:** Conta paga e mesa liberada no sistema.
**CANCELADO:** Pedido anulado (interrompe o fluxo).

### 💡 Exemplo de Requisição (POST /pedidos)JSON
{
    "mesa": 5,
    "nomeCliente": "Felipe Lee",
    "itens": [
        {
            "id_produto": 1,
            "quantidade": 2,
            "observacoes": "Sem cebola"
        },
        {
            "id_produto": 3,
            "quantidade": 1,
            "observacoes": "Gelo e limão"
        }
    ]
}

### 📖 Estrutura do Projeto

src/main/java/com/seuprojeto/cardapio/

 **controller/**    # Endpoints da API
 **dto/**           # Request e Response Objects
 **entity/**        # Modelos de dados (Banco)
 **repository/**    # Interfaces de comunicação com o banco
 **service/**       # Regras de negócio e cálculos
 **enums/**         # Definições de Status do Pedido

### 📝 Licença
Este projeto foi desenvolvido para fins de portfólio acadêmico e profissional.

Desenvolvido por Anderson Cardim - 2025
