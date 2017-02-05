from threading import Thread
from tools.streamer import main as streamer
from tools.gears_angle import GearsAngle
import sys


class DataHolder: pass  # a class for sharing data between threads

false = False
streaming_start = False
if len(sys.argv) > 1:
    if sys.argv[1] in ("-s", "--streaming"):
        streaming_start = false
data_holder = DataHolder()


if streaming_start:
    streaming = Thread(target=streamer, args=(data_holder, ))
gears_vision = Thread(target=GearsAngle, args=(data_holder, ))

gears_vision.start()
if streaming_start:
    streaming.start()
