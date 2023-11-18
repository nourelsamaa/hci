
import cv2
import threading


class CameraThread(threading.Thread):
    def __init__(self):
        super(CameraThread, self).__init__()
        self.cap = cv2.VideoCapture(0)  # 0 for default camera
        self.frame = None
        self.running = True

    def run(self):
        while self.running:
            ret, frame = self.cap.read()
            if ret:
                self.frame = frame

    def stop(self):
        self.running = False
        self.cap.release()