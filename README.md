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

### 3. Docker
O Docker é utilizado para a conteinerização do projeto e suas dependências. Para mais detalhes sobre a instalação correta do Docker, [clique aqui](https://docs.docker.com/engine/install/). 

## Rodando o projeto
Após a instalação das dependências, siga as etapas abaixo para rodar o projeto:

### 1. Clone o repositório
Se ainda não fez o clone do repositório, faça isso com o comandos:
```bash
git clone https://github.com/RafaSilvaDev/AviationSpringAPI
cd AviationSpringAPI
```

### 2. Build do projeto 
Dentro da pasta do projeto, execute os seguintes comandos para acessar a pasta `docker` e executar o projeto em containeres docker:
```bash
cd /docker
docker compose up --build -d
```

### 3. Executando endpoints
Caso queira testar um endpoint da aplicação, basta usar este como exemplo:
```bash
curl http://localhost:8090/aviation-airports/kavl
```

### Testes
Caso deseje executar os testes da API, execute o seguinte comando Maven dentro da pasta do projeto:
```bash
mvn test
```

## Justificativas de implementação

### Estruturação de arquivos
__Os arquivos de código do projeto estão estruturados da seguinte forma:__
* __client:__ pacote que contém o documento java com uma função para capturar aeroportos da AviationAPI. Caso mais informações sejam capturadas da AviationAPI futuramente, novas funcionalidades de interação com a API podem ser estruturadas nesse srquivo.
* __config:__ neste pacote estão localizadas algumas configurações de funcionalidade durante o processo de interação com a AviationAPI. Nele foram habilitados `timeout` caso a AviationAPI não seja possível a comunicação com o serviço ou atrase para responder a solicitações, bem como `retries` para casos em que a AviationAPI falhe em comunicações, e então o serviço tente mais algumas vezes a comunicação com a API antes de retornar erros de comunicação, além de habilitar `cache` na aplicação com uma implementação customizada.
* __controller:__ pacote que contém o documento java com o endpoint de busca de aeroportos por ICAO mapeado.
* __model:__ pacote que contém a modelagem de `Airport`, `AviationAirport` e `CacheData` com seus devidos atributos. Os mesmos estão mapeados com a anotação `@JsonProperty` possibilitando a identificação dos atributos correspondentes no JSON retornado pela AviationAPI, com exceção da classe `CacheData`, pois esta foi construída para a manipulação de cache da base de dados.
* __repository:__ pacote com os repositórios da aplicação que se comunicam com a base de dados. Nela existem alguns métodos de operação na base de dados com queries customizadas usando a anotação '@Query()'."
* __service:__ pacote apenas com a lógica de validação caso o retorno da AviationAPI seja nulo, ou seja, o aeroporto buscado não foi encontrado. Nota-se também a transformação do parâmetro ICAO em _upperCase()_, isso devido ao comportamento da AviationAPI observado, que também transforma os parâmetros de busca em _upperCase_. Sendo assim, o parâmetro é transformado no serviço para auxiliar a manipulação de informações. Dentro da pasta `/cache` está a implementação customizada para a funcionalidade de cache da aplicação.

__Já os arquivos de teste estão estruturados da seguinte forma:__
* __integration:__ contém o teste de integração do serviço, validando retornos positivos e nulos da AviationAPI durante a operação de busca.
* __unit:__ contém os pacotes e arquivos de teste de unidade da `controller` e `service` do projeto.
* __unit.utils:__ aqui, em especial, há funções de criação e retorno de objetos mockados para os casos de teste, economizando linhas para os casos de testes criados e facilitando o controle de objetos esperados.

### Endpoints disponíveis
__GET:__
1. http://localhost:8090/aviation-airports/{icao} | Usado para capturar aeroportos diretamente de AviationAPI através de seu ICAO.
2. http://localhost:8090/airports/{icao} | Usado para capturar aeroportos armazenados na base de dados através de seu ICAO.

__POST:__
1. http://localhost:8090/airports | Usado para armazenar um aeroporto na base de dados. O corpo deve ser semelhante a este: 
```json
{
	"site_number": "01818.*A",
	"type": "AIRPORT",
	"facility_name": "LOS ANGELES INTL",
	"faa_ident": "LAX",
	"icao_ident": "KLAX",
	"district_office": "LAX",
	"state": "CA",
	"state_full": "CALIFORNIA",
	"county": "LOS ANGELES",
	"city": "LOS ANGELES",
	"ownership": "PU",
	"use": "PU",
	"manager": "VIJI PRASAD",
	"manager_phone": "424-646-8251",
	"latitude": "33-56-32.9870N",
	"latitude_sec": "122192.9870N",
	"longitude": "118-24-28.9750W",
	"longitude_sec": "426268.9750W",
	"elevation": "127",
	"magnetic_variation": "12E",
	"tpa": "",
	"vfr_sectional": "LOS ANGELES",
	"boundary_artcc": "ZLA",
	"boundary_artcc_name": "LOS ANGELES",
	"responsible_artcc": "ZLA",
	"responsible_artcc_name": "LOS ANGELES",
	"fss_phone_number": "",
	"fss_phone_numer_tollfree": "1-800-WX-BRIEF",
	"notam_facility_ident": "LAX",
	"status": "O",
	"certification_typedate": "I E S 05/1973",
	"customs_airport_of_entry": "N",
	"military_joint_use": "",
	"military_landing": "",
	"lighting_schedule": "",
	"beacon_schedule": "SS-SR",
	"control_tower": "Y",
	"unicom": "122.950",
	"ctaf": "",
	"effective_date": "11/04/2021"
}
```
1. http://localhost:8090/airports/cache | Usado para limpar o cache armazenado de aeroportos vindos da base de dados.
2. http://localhost:8090/aviation-airports/cache | Usado para limpar o cache armazenado de aeroportos vindos de AviationAPI.