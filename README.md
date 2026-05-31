# Kumamoto Traffic ETL Analyzer

交通データ分析による熊本県の渋滞可視化を目的としたETL分析PoC（Proof of Concept）です。

本システムでは、

* CSV交通データ取得
* ETL処理
* PostgreSQL格納
* Spring Boot API
* 渋滞可視化

を実施します。

社内論文
「交通データ分析による熊本県の渋滞可視化と改善提案」
の成果物として作成しています。

---

# 背景

熊本県では慢性的な交通渋滞が課題となっています。

特に、

* 国道57号
* 国道3号
* 流通団地入口
* 熊本市中心部

などで朝夕を中心に渋滞が発生しています。

本PoCでは、交通データ分析によって、

* 時間帯別渋滞
* 路線別混雑傾向
* ボトルネック箇所

を可視化し、交通改善へ活用できないかを検証します。

---

# システム構成

```text
CSV交通データ
        ↓
ETL処理
        ↓
PostgreSQL
        ↓
Spring Boot API
        ↓
可視化 / 分析
```

---

# 使用技術

| 分類         | 技術          |
| ---------- | ----------- |
| Backend    | Spring Boot |
| Language   | Java 21     |
| Database   | PostgreSQL  |
| ETL        | Java        |
| Build Tool | Maven       |
| Container  | Docker      |
| API        | REST API    |

---

# ディレクトリ構成

```text
kumamoto-traffic-etl/
├── README.md
├── docker-compose.yml
├── .env
├── sample-data/
│   └── traffic_data.csv
├── sql/
│   └── schema.sql
├── backend/
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/
└── docs/
    ├── architecture.md
    ├── api-spec.md
    └── images/
```

---

# Quick Start

## 1. リポジトリ取得

```bash
git clone https://github.com/yourname/kumamoto-traffic-etl.git
```

---

## 2. ディレクトリ移動

```bash
cd kumamoto-traffic-etl
```

---

## 3. Docker起動

```bash
docker compose up --build
```

---

# 動作確認

## API確認

ブラウザまたはcurlで確認できます。

```bash
curl http://localhost:8080/api/traffic
```

---

# Docker構成

本システムでは以下をDocker化しています。

| コンテナ     | 内容              |
| -------- | --------------- |
| postgres | PostgreSQL      |
| backend  | Spring Boot API |

そのため、

* Javaインストール不要
* PostgreSQLインストール不要
* Mavenインストール不要

で動作可能です。

Dockerのみインストールされていれば実行できます。

---

# サンプルCSV

## sample-data/traffic_data.csv

```csv
road_name,observation_time,average_speed,traffic_volume
国道57号,2026-05-01 07:00:00,18.5,1200
国道57号,2026-05-01 08:00:00,12.3,1800
国道3号,2026-05-01 08:00:00,14.5,1600
流通団地入口,2026-05-01 18:00:00,9.8,2400
高下西町,2026-05-01 18:00:00,13.4,2200
```

---

# ETL処理

ETL処理では以下を実施しています。

## Extract

* CSV交通データ取得

## Transform

* 日時変換
* 欠損値除去
* 平均速度分析
* 渋滞レベル分類

## Load

* PostgreSQL格納

---

# DB構成

## traffic_data

| column           | type      |
| ---------------- | --------- |
| id               | bigint    |
| road_name        | varchar   |
| observation_time | timestamp |
| average_speed    | numeric   |
| traffic_volume   | integer   |
| congestion_level | varchar   |

---

# API仕様

## 全件取得

```http
GET /api/traffic
```

---

## レスポンス例

```json
[
  {
    "id": 1,
    "roadName": "国道57号",
    "averageSpeed": 12.3,
    "congestionLevel": "HIGH"
  }
]
```

---

# 今後の拡張

以下を今後検討しています。

* Chart.jsによるグラフ表示
* 地図表示
* ヒートマップ
* AI渋滞予測
* ETC2.0連携
* OpenData自動取得
* リアルタイム交通分析
* 自転車ナビゲーション

---

# 本PoCの目的

本PoCは完成品ではなく、

* ETL
* Spring Boot
* PostgreSQL
* Docker
* データ分析

を活用し、

「交通課題へIT技術をどう活用できるか」

を検証することを目的としています。

---

# 社内論文

本成果物は以下社内論文と連動しています。

「交通データ分析による熊本県の渋滞可視化と改善提案
― ETL基盤を活用した交通分析PoCの試作 ―」

---

# ライセンス

This project is for internal study and PoC purposes.

