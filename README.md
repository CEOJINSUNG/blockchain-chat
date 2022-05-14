# blockchain-chat

## 프로젝트 순서

### 1. 프로젝트 세팅

    - start.spring.io 에서 프로젝트 생성
    - Docker로 local 환경에서 kafka/redis/mysql 세팅
    - application.yml 연동

#### 1) Redis 설정

    - docker search redis
    - docker pull redis
    - docker images
    - docker run -d —name {컨테이너 이름} -p 6379:6379 redis

#### 2) MySQL 설정

    - docker search mysql
    - docker pull mysql
    - docker images
    - docker run -d —name {컨테이너 이름} -e MYSQL_ROOT_PASSWORD={설정하고자 하는 비밀번호, 나는 1234} -p 3306:3306 mysql

#### 3) Kafka 설정

    - docker -v
    - docker-compose -v
    - docker pull bitnami/zookeeper
    - docker pull bitnami/kafka
    - docker images
    - docker-compose.yml 파일 작성
    
    version: '3.8'

    networks:
      kafka-net:
        driver: bridge

    services:
      zookeeper:
        container_name: blockchain-zookeeper
        image: bitnami/zookeeper
        networks:
          - kafka-net
        ports:
          - '2181:2181'
        environment:
          - ALLOW_ANONYMOUS_LOGIN=yes

      kafka:
        container_name: blockchain-kafka
        image: bitnami/kafka
        networks:
          - kafka-net
        ports:
          - '9092:9092'
        environment:
          - KAFKA_BROKER_ID=1
          - KAFKA_CFG_LISTENERS=PLAINTEXT://:9092
          - KAFKA_CFG_ADVERTISED_LISTENERS=PLAINTEXT://127.0.0.1:9092
          - KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper:2181
          - ALLOW_PLAINTEXT_LISTENER=yes
        depends_on:
          - zookeeper
    
    - docker-compose -f docker-compose.yml up -d
    - docker ps
          

### 2. 객체 설정

    - 이번 프로젝트에는 거래소 시스템을 적용하는 것으로 UML 기반 Actor는 User 한명이고 매수자와 매도자의 Transaction을 처리하는 것이다. 
    - 따라서, 사용자/가상자산/주문/거래내역 4개의 객체를 생성한다. 
    - 또한, Blockchain 가격을 가져오기 위해서는 Binance Data가 필수이므로 이에 대한 Binance 객체도 만들어야 한다.

### 3. Websocket + Binance 구현

    1. wss를 통하여 Spring이 Streaming 을 받아 출력하는 방식
        1) https://docs.binance.org/api-reference/dex-api/ws-connection.html
        2) Binance wss를 Websocket을 통하여 가져온 코인의 가격 정보를 가져온다.
        3) 최신 코인 가격을 Redis의 기록한다. 
    
    2. binance-java-api를 이용하는 방식
        1) https://github.com/binance-exchange/binance-java-api
        2) implementation group: 'io.github.serg-maximchuk', name: 'binance-api-client', version: '1.0.4'
        3) Github에 나와 있는 것과 달리 실제 Spring Dependency에는 2) 요소를 집어넣어야 작동이 된다. 

### 4. kafka 기반 주문/거래 시스템 구현

### 5. Rest API 생성

### 6. 카카오 Chat 제작

### 7. 모니터링 및 테스트
