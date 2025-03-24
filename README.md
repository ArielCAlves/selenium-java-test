# Selenium Java Test

Este repositório contém uma coleção de testes automatizados usando Selenium WebDriver com Java. Os testes foram desenvolvidos para validar funcionalidades em uma aplicação web de exemplo, como o PrestaShop.

## Funcionalidades

### Testes Automatizados

1. **Formulário de Contato**  
   Teste para preencher e enviar um formulário de contato. O script verifica se o formulário foi preenchido corretamente e se a mensagem de sucesso é exibida após o envio.

2. **Carrinho de Compras e Checkout**  
   Teste para adicionar produtos ao carrinho, verificar a quantidade de produtos e realizar o processo de checkout, incluindo a criação de uma conta durante o processo.

3. **Validação da Interface e Captura de Screenshots**  
   Teste que captura screenshots de páginas específicas da aplicação, como a página de contato, para facilitar a validação visual e auxiliar no processo de depuração de testes.

4. **Gerenciamento de Cookies e Sessões**  
   Teste que faz uso de cookies para salvar e reutilizar sessões de usuário entre execuções, evitando logins repetitivos e otimizando o tempo de execução dos testes.

5. **Rolagem de Página e Visibilidade de Elementos**  
   Teste que simula a rolagem da página para garantir que elementos, como botões e links, sejam visíveis antes de interagir com eles.

## Estrutura do Repositório

- `src/` – Scripts de teste automatizados em Java com Selenium.
- `screenshots/` – Pasta contendo as capturas de tela feitas durante os testes.
- `LICENSE` – Licença MIT.
- `README.md` – Este arquivo de documentação.

## Pré-requisitos

- JDK 17 ou superior.
- Maven ou Gradle (dependendo do seu ambiente).
- WebDriverManager (para gerenciar o driver do navegador).
- Selenium WebDriver.

## Como Rodar os Testes

1. **Clone o Repositório**:

    ```bash
    git clone https://github.com/ArielCAlves/selenium-java-test.git
    cd selenium-java-test
    ```

2. **Instale as Dependências**:
   Se estiver usando Maven, execute:

    ```bash
    mvn install
    ```

3. **Execute os Testes**:
   Para rodar os testes, use o comando Maven:

    ```bash
    mvn test
    ```

4. **Visualize os Relatórios**:
   Após a execução dos testes, os relatórios e screenshots serão gerados na pasta `screenshots/` para validação.

## Contribuições

Se você quiser contribuir para o projeto, siga os seguintes passos:

1. Fork este repositório.
2. Crie uma branch (`git checkout -b feature/novo-teste`).
3. Comite suas alterações (`git commit -am 'Adiciona novo teste'`).
4. Envie a sua branch para o repositório (`git push origin feature/novo-teste`).
5. Abra um Pull Request.

## Licença

Distribuído sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais informações.
