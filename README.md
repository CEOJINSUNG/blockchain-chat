# blockchain-chat
 blockchain-chat based on spring boot


## 프로젝트 순서

### 1. 프로젝트 세팅

    - start.spring.io 에서 프로젝트 생성
    - Docker로 local 환경에서 kafka/redis/mysql 세팅

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
          - '9093:9093'
        environment:
          - KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper:2181
          - ALLOW_PLAINTEXT_LISTENER=yes
          - KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP=CLIENT:PLAINTEXT,EXTERNAL:PLAINTEXT
          - KAFKA_CFG_LISTENERS=CLIENT://:9092,EXTERNAL://:9093
          - KAFKA_CFG_ADVERTISED_LISTENERS=CLIENT://kafka:9092,EXTERNAL://localhost:9093
          - KAFKA_CFG_INTER_BROKER_LISTENER_NAME=CLIENT
        depends_on:
          - zookeeper
    
    - docker-compose -f docker-compose.yml up -d
    - docker ps
          

### 2. 객체 도메인 설정

    - 유저/주문/거래내역 객체 생성

### 3. kafka 기반 주문/거래 시스템 구현

### 4. Websocket + 업비트 API 연동

    - 업비트 API를 활용하여 코인의 정보를 끌어옴

### 5. Rest API 생성

### 6. 카카오 Chat 제작

### 7. 모니터링 및 테스트
