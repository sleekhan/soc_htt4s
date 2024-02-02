package soc.common.utils

import com.google.inject.{Guice, Injector}

object Injector {
  private val injector = Guice.createInjector()

  def get: Injector = injector
}
