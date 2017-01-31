from threading import Thread
from shooting_vision import ShootingVision
from streamer import main as streamer
import sys


class DataHolder: pass  # a class for sharing data between threads 

streaming_start = False
if len(sys.argv) > 1:
    if sys.argv[1] in ("-s", "--streaming"):
        streaming_start = True
data_holder = DataHolder()
data_holder.data = None


vision = Thread(target=ShootingVision, args=(data_holder, ))
streaming = Thread(target=streamer, args=(data_holder, ))

vision.start()
if streaming_start:
    streaming.start()
