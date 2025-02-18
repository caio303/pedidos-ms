# Pedidos Service (MS)

### Mensageria
Este microsserviço produz mensagens para o tópico <code>novo-pedido-topic</code>, enquanto consumirá
dos seguintes:
* <code>estoque-insuficiente-topic</code>
* <code>entrega-status-topic</code>

### Environment Vars

* PEDIDOS_PROFILE     _(dev)_
* PEDIDOS_API_PORT    _(8081)_
* PEDIDOS_DB_USER     _(postgres)_
* PEDIDOS_DB_PASSWORD _(postgres)_
* PEDIDOS_DB_HOST     _(postgresql://localhost)_
* PEDIDOS_DB_PORT     _(5432)_
* PEDIDOS_BROKER_HOST _(localhost)_
* PEDIDOS_BROKER_PORT _(9092)_
* PEDIDOS_CLIENTE_SERVICE_HOST _(http://localhost:8080)_
* PEDIDOS_CATALOGO_SERVICE_HOST _(http://localhost:8082)_
* PEDIDOS_LIQUIBASE_CHANGELOGS_PATH _(db/changelog/changelog-master.xml)_


