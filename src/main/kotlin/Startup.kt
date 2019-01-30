class Startup {

    fun startup() {
        val discovery = Discovery()
        val future = discovery.startServer()
        discovery.sendDiscoveryRequest()
        val discoveryData = future.join()

        val client = Client(discoveryData.sourceAddress, "000000000000", discoveryData.getGatewayId())
        var readBytes = client.readBytes()
        // should read 5410EC03615000000000000600090153BA3FD391BAB9
        client.sendMessage(Package(Command.GET_NAME))
        readBytes = client.readBytes()
        client.sendMessage(Package(command = Command.LOGIN, payload = Payload.login("admin", "aundt")))
        readBytes = client.readBytes()
    }
}
