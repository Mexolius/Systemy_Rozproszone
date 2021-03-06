import socket;
serverIP = "127.0.0.1"
serverPort = 9876
msg = "żółta gęś"
client = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
client.sendto(bytes(msg, 'utf-8'), (serverIP, serverPort))
client.sendto(bytes("int", 'utf-8'), (serverIP, serverPort))

client.sendto((300).to_bytes(4, byteorder='little'), (serverIP, serverPort))

print(int.from_bytes(client.recv(4), byteorder='big'))