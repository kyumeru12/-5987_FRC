from threading import Thread
from tools.streamer import main as streamer
from tools.gears_angle import GearsAngle
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


if streaming_start:
    streaming = Thread(target=streamer, args=(data_holder, display))
gears_vision = Thread(target=GearsAngle, args=(data_holder, display))

gears_vision.start()
if streaming_start:
    streaming.start()
