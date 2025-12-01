# Estrutura
```
src/main/java/br.com.modulo3
├── configuration/
│     └── ListenerConfig.java     - Configura o listener Kafka (tópicos, factory, groupId etc.)
│
├── consumer/
│     └── Consumer.java           - Classe que recebe as mensagens vindas do Kafka.
│
├── entity/
│     └── Mensagem.java           - Objeto que representa a mensagem recebida.
│
└── ConsumerApplication.java      - Classe principal que inicializa a aplicação Spring Boot.
```