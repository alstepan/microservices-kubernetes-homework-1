package me.alstepan

import cats.effect.Async
import cats.effect.std.Random
import cats.implicits._
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.typelevel.log4cats.slf4j.Slf4jLogger

class HealthService[F[_]: Async] {

  object dsl extends Http4sDsl[F]
  import dsl._

  def health: HttpRoutes[F] = HttpRoutes.of[F] {
    case GET -> root / "health" =>
      for {
        logger <- Slf4jLogger.create[F]
        _ <- logger.debug(s"Got Health request")
        resp <- Ok("""{"status": "OK"}""")
        _ <- logger.debug(s"Health response is ready: $resp")
      } yield resp

  }

  def liveness: HttpRoutes[F] = HttpRoutes.of[F] {
    case GET -> root / "liveness" =>
      for {
        logger <- Slf4jLogger.create[F]
        _ <- logger.debug(s"Got liveness request")
        resp <- Ok("""{"status": "OK"}""")
        _ <- logger.debug(s"Liveness response is ready: $resp")
      } yield resp
  }

  def allServices = health <+> liveness
}

object HealthService {
  def apply[F[_]: Async](config: Config): F[HealthService[F]] = Async[F].delay(new HealthService[F])
}
