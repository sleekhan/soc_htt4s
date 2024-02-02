package soc.common.config

import com.typesafe.config.*

val config = ConfigFactory.load()

object Properties {
  val serverPort: Int = config.getInt("server.port")
  val databaseUrl: String = config.getString("database.url").strip()
  val databaseUser: String = config.getString("database.user").strip()
  val databasePassword: String = config.getString("database.password").strip()
  val databaseDriver: String = config.getString("database.driver").strip()
  val databaseNumThreads: Int = config.getInt("database.numThreads")
  val databaseKeepAliveConnection: Boolean = config.getBoolean("database.keepAliveConnection")
}

//server.port=8080
//database.url = "jdbc:postgresql://localhost:5432/soc"
//database.user = "postgres"
//database.password = "postgres"
//database.driver = "org.postgresql.Driver"
//database.numThreads = 10
//database.keepAliveConnection = true
//database.connectionPool = enabled