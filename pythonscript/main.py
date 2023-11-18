from camera_thread import CameraThread
from mediapipe_thread import MediapipeThread
from socket_thread import SocketThread


def main():
    
    
    shared_data = {"Move": None}

    # Create and start threads
    camera_thread = CameraThread()
    mediapipe_thread = MediapipeThread(camera_thread, shared_data)
    socket_thread = SocketThread(mediapipe_thread, shared_data)

    camera_thread.start()
    mediapipe_thread.start()
    socket_thread.start()

    try:
        while True:
            # Print shared data (for debugging purposes)
            pass
    except KeyboardInterrupt:
        # Handle Ctrl+C gracefully
        print("Terminating the application...")
        camera_thread.stop()
        mediapipe_thread.running = False
        socket_thread.running = False

if __name__ == "__main__":
    main()
