package sysdat

import io.ktor.server.netty.*
import kotlinx.coroutines.Job

var serverEnginge: NettyApplicationEngine? = null
var serverJob: Job? = null
