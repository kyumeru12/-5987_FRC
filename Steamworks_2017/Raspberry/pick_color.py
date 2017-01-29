import cvs


tf = cvs.UsbCam()
finder = cvs.HSVRangeFinder(right_click_save=True)
while True:
	frame = tf.read().resize(0.8)
	finder.update(frame)
