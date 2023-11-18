import asyncio
import websockets
import json
import threading

class SocketThread(threading.Thread):
    def __init__(self, mediapipe_thread, shared_data):
        super(SocketThread, self).__init__()
        self.mediapipe_thread = mediapipe_thread
        self.shared_data = shared_data
        self.running = True

    async def send_message(self, uri, message):
        async with websockets.connect(uri) as websocket:
            await websocket.send(message)
            print(f"Sent: {message}")

    def run(self):
        try:
            uri = "ws://localhost:8000"  # Adjust the URI to match your WebSocket server
            while self.running:
                
                hand_coordinates = self.shared_data.get("Move")

                # Check if hand_coordinates is not None (to handle initial case)
                if hand_coordinates is not None:
                    # Encode the coordinates as JSON
                    coordinates_json = json.dumps({"Move" : hand_coordinates})
                    print(coordinates_json)

                    # Use asyncio event loop to run the send_message function
                    asyncio.get_event_loop().run_until_complete(self.send_message(uri, coordinates_json))
        except Exception as e:
            print(f"An error occurred: {e}")
