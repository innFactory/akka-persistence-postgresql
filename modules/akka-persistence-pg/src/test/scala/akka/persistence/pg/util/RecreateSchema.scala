package akka.persistence.pg.util

import akka.persistence.pg.PgConfig

trait RecreateSchema {
  self: PgConfig =>

  def schemaName: String

  import driver.api._

  lazy val recreateSchema: DBIO[Unit] = DBIO.seq(
    SimpleDBIO(_.connection.createStatement.execute(s"""drop schema if exists "$schemaName" cascade;""")),
    SimpleDBIO(_.connection.createStatement.execute(s"""create schema "$schemaName";"""))
  )

}