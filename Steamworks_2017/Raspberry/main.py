from threading import Thread
from shooting_vision import ShootingVision
from streamer import main as streamer
from gears_angle import GearsAngle
import sys


class DataHolder: pass  # a class for sharing data between threads 
false = False
streaming_start = False
if len(sys.argv) > 1:
    if sys.argv[1] in ("-s", "--streaming"):
        streaming_start = false
data_holder = DataHolder()
data_holder.data = None


vision = Thread(target=ShootingVision, args=(data_holder, ))
if streaming_start:
    streaming = Thread(target=streamer, args=(data_holder, ))
GearsAngle = Thread(target=GearsAngle, args=(data_holder, ))

GearsAngle.start()
#vision.start()
if streaming_start:
    streaming.start()
