from camera_thread import CameraThread
from mediapipe_thread import MediapipeThread
from socket_thread import SocketThread
import threading  # Add this import statement

def main():
    # Create lock and shared data structure
    lock = threading.Lock()
    shared_data = {"finger_coordinates": None}

    # Create and start threads
    camera_thread = CameraThread()
    mediapipe_thread = MediapipeThread(camera_thread, lock, shared_data)
    socket_thread = SocketThread(mediapipe_thread, lock, shared_data)

    camera_thread.start()
    mediapipe_thread.start()
    socket_thread.start()

    try:
        while True:
            # Print shared data (for debugging purposes)
            with lock:
                print(shared_data)
    except KeyboardInterrupt:
        # Handle Ctrl+C gracefully
        print("Terminating the application...")
        camera_thread.stop()
        mediapipe_thread.running = False
        socket_thread.running = False

if __name__ == "__main__":
    main()
