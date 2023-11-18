import asyncio
import websockets

async def send_message(uri, message):
    async with websockets.connect(uri) as websocket:
        await websocket.send(message)
        print(f"Sent message: {message}")

if __name__ == "__main__":
    server_uri = "ws://localhost:8000"  # Adjust the URI to match your WebSocket server

    # Message to be sent to the WebSocket server
    message_to_send = "Hello, WebSocket Server!"

    # Use asyncio event loop to run the send_message function
    asyncio.get_event_loop().run_until_complete(send_message(server_uri, message_to_send))
