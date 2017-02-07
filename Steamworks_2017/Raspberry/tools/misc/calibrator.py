import cvs
import cv2


cam = cvs.UsbCam()
while True:
    frame = cam.read()
    h = frame.height
    w = frame.width
    frame.draw_point((int(w/2.0), int(h/2.0))) # center point
    # kav anachi
    top_center = (int(w/2.0), 0)
    bottom_center = (int(w/2.0), int(h))
    cv2.line(frame, top_center, bottom_center, (0,0,0), 1)
    # kav ofki
    left_center = (0, int(h/2))
    right_center = (int(w), int(h/2))
    cv2.line(frame, left_center, right_center, (0,0,0), 1)

    cvs.pressed_key()
    frame.show("Center Calibrator")
    
