package library

import java.net.InetSocketAddress
import java.net.Socket



fun getLocalIP():String {
    val socket = Socket()
    socket.connect(InetSocketAddress("google.com", 80))
    return socket.localAddress.toString()
}

fun main() {

    println(getLocalIP())
}