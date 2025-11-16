# IMDTravel - Baseline (Parte 1.1) - Spring Boot

Projeto com 4 microserviços Spring Boot:
- imdtravel (orquestrador) - porta 8084
- airlineshub - porta 8081
- exchange - porta 8082
- sell/fidelity - porta 8083

## Como executar (via Prompt de Comando – Windows)

Este projeto pode ser executado usando múltiplos terminais, um para cada microserviço.

Passo 1 — Abrir o CMD como Administrador
Passo 2 — Ir para o diretório raiz do projeto

Exemplo:
   ```
cd "C:\Users\User\Downloads\imdtravel_baseline_multistage\imdtravel_baseline_multistage_falhas"
   ```
# Passo 3 — Rodar cada serviço manualmente (cada um em um terminal separado)
Service: airlineshub (porta 8081)
    ```
    cd airlineshub
    mvn spring-boot:run
    ```
Service: exchange (porta 8082)
    ```
    cd exchange
    mvn spring-boot:run
    ```
Service: sell/fidelity (porta 8083)
    ```
    cd fidelity
    mvn spring-boot:run
    ```
Service: imdtravel (orquestrador – porta 8084)
    ```
    cd imdtravel
    mvn spring-boot:run
    ```
# Testando os serviços
  Testar AirlinesHub (GET – navegador ou Postman)
   ```
   http://localhost:8081/flight?flight=LATAM123&day=2025-11-01
   ```
  Testar Exchange (GET)
   ```
   http://localhost:8082/convert?value=100
   ```
  Testar Sell/Fidelity (POST – Postman)
   ```
   http://localhost:8083/sell
   ```
  Body (JSON):
   ```
   {
     "flight": "LATAM123",
     "user": "joao"
   }
   ```
# Testar IMDTravel (POST – orquestrador)
 Endpoint da compra com tolerância a falhas:
   ```
   http://localhost:8084/api/buyTicket
   ```
  Exemplo de body SEM tolerância a falhas (ft=false):
   ```
   {
     "flight": "LATAM123",
     "day": "2025-11-01",
     "user": "101",
     "ft": false
   }
   ```
Exemplo de body COM tolerância a falhas (ft=true):
   ```
   {
     "flight": "LATAM123",
     "day": "2025-11-01",
     "user": "101",
     "ft": true
   }
   ```
## Simulação de falhas (Parte 1.3)
Faça várias requisições (10–15 vezes), observe os terminais e veja mensagens como:

.[FAILURE] Request 1 - Omission fault triggered!

.[FAILURE] Request 3 - Timeout (SELL)

.Cache sendo utilizado

.Bônus indo para a fila (quando Fidelity está off)

# Para testar SELL falhando, basta parar o terminal do serviço SELL (porta 8083) e enviar a compra com:
   ```
   {
     "flight": "LATAM123",
     "day": "2025-11-01",
     "user": "101",
     "ft": true
    }
   ```
## Execução via Docker Compose (opcional)
Se quiser usar Docker:
   ```
   docker compose up --build
   ```
## Tag obrigatória de entrega
Após commitar tudo:
   ```
   git tag BASELINE
   git push origin BASELINE
   ```

## Observações finais
- Todas as portas estão configuradas em application.properties.
- O SELL usa timeout de 2 segundos.
- Exchange usa fallback da média das últimas 10 taxas.
- AirlinesHub usa retry + fallback em cache.
- Fidelity envia bônus ou armazena na fila offline.


