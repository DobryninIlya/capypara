package com.kai.capybara.domain.util

import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.ExecutionException

fun isConnectingException(e: Exception) =
    e is SocketTimeoutException || e is UnknownHostException || e is ExecutionException

