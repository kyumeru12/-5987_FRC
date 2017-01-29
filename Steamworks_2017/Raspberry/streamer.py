#!/usr/bin/env python
#
# Project: Video Streaming with Flask
# Author: Log0 <im [dot] ckieric [at] gmail [dot] com>
# Date: 2014/12/21
# Website: http://www.chioka.in/
# Description:
# Modified to support streaming out with webcams, and not just raw JPEGs.
# Most of the code credits to Miguel Grinberg, except that I made a small tweak. Thanks!
# Credits: http://blog.miguelgrinberg.com/post/video-streaming-with-flask
#
# Usage:
# 1. Install Python dependencies: cv2, flask. (wish that pip install works like a charm)
# 2. Run "python main.py".
# 3. Navigate the browser to the local webpage.
from flask import Flask, render_template, Response
import cv2
import commands

app = Flask(__name__)


def main(data_holder):
    @app.route('/')
    def index():
        return render_template('index.html')

    def arr2str(image):
        gotit, jpeg = cv2.imencode('.jpg', image)
        if not gotit:
            raise ValueError("FAILED to convert image to .jpg")
        return jpeg.tostring()
        
    def gen():
        while True:
            frame = arr2str(data_holder.shooting_frame)
            yield (b'--frame\r\n'b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n\r\n')

    @app.route('/video_feed')
    def video_feed():
        return Response(gen(),
                        mimetype='multipart/x-mixed-replace; boundary=frame')

    app.run(host=commands.getstatusoutput("hostname -I")[1], debug=False)
