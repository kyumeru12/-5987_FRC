from threading import Thread
from tools.shooting_vision import ShootingVision
from tools.streamer import main as streamer
import sys


class DataHolder: pass  # a class for sharing data between threads

false = False
true = True
streaming_start = false
display = true

args = "".join(sys.argv)
if "-s" in args or "--streaming" in args:
    streaming_start = true
if "-nd" in args or "--NoDisplay" in args:
    display = false
data_holder = DataHolder()


shooting_vision = Thread(target=ShootingVision, args=(data_holder, display ))
if streaming_start:
    streaming = Thread(target=streamer, args=(data_holder, ))

shooting_vision.start()
if streaming_start:
    streaming.start()
