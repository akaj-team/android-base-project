package com.android.appname.extension

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors

fun MockWebServer.addResponseBody(fileName: String, code: Int = 200) {
    val json = Files.lines(
        Paths.get(javaClass.classLoader?.getResource("responses/$fileName")?.toURI()),
        StandardCharsets.UTF_8
    )
        .parallel()
        .collect(Collectors.joining())
    this.enqueue(MockResponse().setBody(json).setResponseCode(code))
}
