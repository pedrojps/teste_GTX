📱 Teste Técnico Android – Consumo de API com Arquitetura MVVM
📌 Objetivo

Aplicativo Android desenvolvido como desafio técnico, com foco em qualidade arquitetural em Java, separação de responsabilidades (SOLID) e testabilidade.
O app consome dados de uma API REST pública, aplica uma regra de negócio simples e exibe os resultados em uma lista.

🧩 Funcionalidades

Consumo de API REST pública (JSONPlaceholder – Posts)

Exibição dos dados em uma lista usando RecyclerView

Filtro de regra de negócio para exibir apenas itens com ID par

Atualização reativa da interface

Código organizado seguindo boas práticas de arquitetura

🏗️ Arquitetura

O projeto segue o padrão MVVM (Model–View–ViewModel):

Camadas

View (Activity)
Responsável apenas pela UI e interação do usuário.

ViewModel
Contém a lógica de apresentação e expõe os dados via LiveData.

Repository
Centraliza a regra de negócio e decide a origem dos dados.

Remote Data Source
Responsável exclusivamente pela chamada de rede e conversão dos dados.

⚙️ Assincronismo

A chamada de rede é gerenciada explicitamente utilizando concorrência em Java, por meio de Executor, garantindo:

Execução fora da UI Thread

Controle claro do fluxo assíncrono

Maior previsibilidade para testes unitários

Essa abordagem evita dependência implícita de frameworks e melhora a clareza arquitetural.

🧪 Testes

Testes unitários implementados com JUnit

Validação da interpretação dos dados retornados pela API

Uso de JSON estático para simular respostas de rede

Testes focados em regras de negócio e parsing de dados

🛠️ Tecnologias Utilizadas

Java

Android SDK

Retrofit

RecyclerView

ViewModel & LiveData

JUnit 4

Mockito / MockWebServer (quando aplicável)


✅ Considerações Finais

O projeto prioriza clareza arquitetural, aderência aos princípios SOLID e facilidade de manutenção e testes, atendendo integralmente aos requisitos do desafio técnico.
