from __future__ import division, print_function
import cvs
import pickle
from misc.calcs import px2angle, px2dist, dist2horizontal
from misc.params import TARGET_ASPECT_RATIO, RESIZE_FACTOR
import cv2
from misc.a_cool_networktable import SmartDashboard
from misc.stabilizer import Stabilizer


class ShootingVision:

    def __init__(self, data_holder):
        self.SDboard = SmartDashboard()
        self.data_holder = data_holder
        self.hsv_vals = self.get_hsv_range()
        self.distance_stabilizer = Stabilizer(7)
        cam = cvs.UsbCam()
        while True:
            frame = cam.read().resize(RESIZE_FACTOR)  # read a frame and resize it
            self.img_centerX = frame.width / 2
            # frame = frame(cv2.flip, frame, -1) # flip image upside down
            filtered = self.filter_image(frame) # filtering the image acording to the colors
            contours = filtered.contours()
            target = self.find_target(contours)
            frame.draw_contour(target)
            if target is not None: # checks if any of the contours
                network_data, draw_data = self.extract_data(target)
                self.publish_data(network_data, draw_data, frame)
            else:
                frame.println("No Target Found")
                self.SDboard["I've Got You In My Sight (bolier)"] = False  # soldier76 doesn't have ult yet (target is not in sight)
            self.data_holder.frame = frame
            frame.show("frame")
            filtered.show("filtered")
            cvs.pressed_key() # must have delay

    @staticmethod
    def get_hsv_range():
        with open('hsv_values.pickle', 'rb') as data:
            hsv_vals = pickle.load(data)["D"]
            (h1, s1, v1), (h2, s2, v2) = hsv_vals
            v1 = 55
            v2 = 255
            hsv_vals = [(h1, s1, v1), (h2, s2, v2)]
            return hsv_vals

    def filter_image(self, frame):
        """
        Get the area of the target using the hsv values
        :param img:
        :return: black & white image of the filtered target.
        """
        filtered = frame.convert('hsv').in_range(*self.hsv_vals)
        return filtered

    def find_target(self, contours):  
        """
        checks if soldier76 has his ult yet (checks if any of the targets is the correct taeget) 
        :param contours:
        :return contour:
        """
        if len(contours) > 0:
            largest5 = contours.largest(5)
            target_aspect_ratio = TARGET_ASPECT_RATIO
            tol = 1

            rekts = cvs.Contours()
            for r in largest5:
                keeps_aspect_ratio = 1.5 < r.aspect_ratio < 4.5
                keeps_min_area = r.area > 50
                if keeps_aspect_ratio and keeps_min_area:
                    rekts.append(r)
            if rekts:  # is it high noon?
                target = rekts.uppermost()
                return target

    def extract_data(self, target):
        """
        
        """
        angle = px2angle(target.width)
        x_difference = (target.x - self.img_centerX) / self.img_centerX
        diag_dist = px2dist(target.width)
        horiz_dist = dist2horizontal(diag_dist)
        self.distance_stabilizer.insert_measure(horiz_dist)
        draw_data = {"Shooter Angle": angle, "X Difference": x_difference, "Horizontal Distance": self.distance_stabilizer.biggest()}
        #draw_data = {"D": diag_dist, "M": self.distance_stabilizer.median(), "L": self.distance_stabilizer.biggest(), "S": self.distance_stabilizer.smallest(), "AVG": self.distance_stabilizer.avg()}
        network_data = {"Shooter Angle": angle, "X Difference": x_difference}
        return network_data, draw_data

    def publish_data(self, network_data, draw_data, frame):
        for key, value in network_data.iteritems():
            self.SDboard[key] = value
        self.SDboard["I've Got You In My Sight (bolier)"] = True
        for key, value in draw_data.iteritems():
            frame.println("{} : {}".format(key, value))

