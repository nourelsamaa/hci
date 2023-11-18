import threading
import mediapipe as mp
import cv2

class MediapipeThread(threading.Thread):
    def __init__(self, camera_thread, lock, shared_data):
        super(MediapipeThread, self).__init__()
        self.camera_thread = camera_thread
        self.running = True
        self.lock = lock
        self.shared_data = shared_data
        self.mp_hands = mp.solutions.hands
        self.hands = self.mp_hands.Hands()

    def extract_finger_coordinates(self, hand_landmarks):
        index_finger_tip = hand_landmarks.landmark[8]
        return index_finger_tip.x, index_finger_tip.y, index_finger_tip.z

    def run(self):
        while self.running:
            if self.camera_thread.frame is not None:
                image = cv2.cvtColor(self.camera_thread.frame, cv2.COLOR_BGR2RGB)
                results = self.hands.process(image)
                if results.multi_hand_landmarks:
                    hand_landmarks = results.multi_hand_landmarks[0]  # Assuming only one hand is detected
                    x, y, z = self.extract_finger_coordinates(hand_landmarks)

                    # Use lock to ensure safe access and update of shared data
                    with self.lock:
                        self.shared_data["finger_coordinates"] = (x, y, z)
