# AviationSpringAPI
Uma API em Spring para capturar dados de aeroportos vindos de https://www.aviationapi.com/

## Requisitos
Antes de rodar o projeto, certifique-se de que as seguintes dependências estão instaladas em seu sistema:

### 1. Java
É necessário o Java 17. Para instalar, execute o seguinte comando:
```bash
sudo apt-get install openjdk-17-jdk openjdk-17-jdk-headless
```
Para verificar se o java foi instalado corretamente, execute:
```bash
java -version
```

### 2. Maven
O Maven é utilizado para gerenciar o build, rodar comandos de inicialização e executar testes do projeto. Para instalar, execute:
```bash
sudo apt-get install maven
```

Para verificar se o Maven foi instalado corretamente, execute:
```bash
mvn -v
```

## Rodando o projeto
Após a instalação das dependências, siga as etapas abaixo para rodar o projeto:

### 1. Clone o repositório
Se ainda não fez o clone do repositório, faça isso com o comandos:
```bash
git clone https://github.com/RafaSilvaDev/AviationSpringAPI
cd AviationSpringAPI
```

### 2. Build do projeto
Dentro da pasta do projeto, execute o seguinte comando para compilar e empacotar o projeto:
```bash
mvn clean install
```

### 3. Executando testes
Caso deseje executar os testes, execute o seguinte comando Maven:
```bash
mvn test
```

### 4. Rodando o projeto
Após o build anterior ser concluído com sucesso, você pode rodar o projeto com o seguinte comando:
```bash
mvn spring-boot:run
```

## Justificativas de implementação

### Estruturação de arquivos
__Os arquivos de código do projeto estão estruturados da seguinte forma:__
* __client:__ pacote que contém o documento java com uma função para capturar aeroportos da AviationAPI. Caso mais informações sejam capturadas da AviationAPI futuramente, novas funcionalidades de interação com a API podem ser estruturadas nesse srquivo.
* __config:__ neste pacote estão localizadas algumas configurações de funcionalidade durante o processo de interação com a AviationAPI. Nele foram habilitados `timeout` caso a AviationAPI não seja possível a comunicação com o serviço ou atrase para responder a solicitações, bem como `retries` para casos em que a AviationAPI falhe em comunicações, e então o serviço tente mais algumas vezes a comunicação com a API antes de retornar erros de comunicação.
* __controller:__ pacote que contém o documento java com o endpoint de busca de aeroportos por ICAO mapeado.
* __model:__ pacote que contém a modelagem de `Airports` com seus devidos atributos. Os mesmos estão mapeados com a anotação `@JsonProperty` possibilitando a identificação dos atributos correspondentes no JSON retornado pela AviationAPI.
* __service:__ pacote apenas com a lógica de validação caso o retorno da AviationAPI seja nulo, ou seja, o aeroporto buscado não foi encontrado. Nota-se também a transformação do parâmetro ICAO em _upperCase()_, isso devido ao comportamento da AviationAPI observado, que também transforma os parâmetros de busca em _upperCase_. Sendo assim, o parâmetro é transformado no serviço para auxiliar a manipulação de informações.

__Já os arquivos de teste estão estruturados da seguinte forma:__
* __integration:__ contém o teste de integração do serviço, validando retornos positivos e nulos da AviationAPI durante a operação de busca.
* __unit:__ contém os pacotes e arquivos de teste de unidade da `controller` e `service` do projeto.
* __unit.utils:__ aqui, em especial, há uma função de criação e retorno de um objeto de Airport para ser usado nos testes, economizando linhas de código e memória (pois usa o padrão singleton para a criação do objeto).