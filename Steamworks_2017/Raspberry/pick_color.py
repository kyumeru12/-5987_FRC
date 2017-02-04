import cvs
from params import RESIZE_FACTOR


tf = cvs.UsbCam()
finder = cvs.HSVRangeFinder(right_click_save=True)
while True:
	frame = tf.read().resize(RESIZE_FACTOR)
	finder.update(frame)
