import cvs
import cv2
from misc.params import RESIZE_FACTOR, BRIGHTNESS


tf = cvs.UsbCam()
tf.cam.set(cv2.CAP_PROP_BRIGHTNESS, BRIGHTNESS)
finder = cvs.HSVRangeFinder(right_click_save=True)
while True:
	frame = tf.read().resize(RESIZE_FACTOR)
	finder.update(frame)
