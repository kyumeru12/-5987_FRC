import cvs
import cv2
import sys
from misc.params import RESIZE_FACTOR, BRIGHTNESS


color_code = sys.argv[1]
tf = cvs.UsbCam()
tf.cam.set(cv2.CAP_PROP_BRIGHTNESS, BRIGHTNESS)
path = '/home/pi/steamworks2017/Steamworks_2017/Raspberry/tools/misc/hsv_values{}.pickle'.format(color_code.upper())
finder = cvs.HSVRangeFinder(right_click_save=True, save_pickle_path=path)
while True:
	frame = tf.read().resize(RESIZE_FACTOR)
	finder.update(frame)
