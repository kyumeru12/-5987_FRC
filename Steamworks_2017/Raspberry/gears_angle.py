import cvs
import cv2
import pickle
from math import sin, cos, acos, sqrt, degrees, radians
from params import RESIZE_FACTOR
from calcs import px2dist
from a_cool_networktable import SmartDashboard
from stabilizer import Stabilizer


class GearsAngle:

    def __init__(self, data_holder):
        self.data_holder = data_holder
        self.hsv_vals = self.get_hsv_range()
        self.SDboard = SmartDashboard()
        # self.distance_stabilizer = Stabilizer(7)
        cam = cvs.UsbCam()
        while True:
            frame = cam.read().resize(RESIZE_FACTOR)
            self.img_centerX = frame.width / 2
            filtered = frame.convert('hsv').in_range(*self.hsv_vals)
            cnts = self.find_target(filtered.contours())
            if cnts is not None:
                cnt_right = cnts.rightmost()
                cnt_left = cnts.leftmost()
                frame.draw_contour(cnt_right, color=(0,0,0))
                frame.draw_contour(cnt_left)
                left_dist = px2dist(cnt_left.height, 0.13)
                right_dist = px2dist(cnt_right.height, 0.13)
                angle = self.get_gears_angle(right_dist, left_dist)
                frame.println("left :{:.2f}, px:{} ".format(left_dist, cnt_left.height))
                frame.println("right :{:.2f}, px:{} ".format(right_dist, cnt_right.height))
                frame.println("Angle needed is {} ".format(angle))
                self.draw_angle(angle, (left_dist + right_dist) / 2.0,  frame)
                #SmartDashboard["Gears Angle"] = get_gears_angle(right_dist, left_dist)
            frame.show("Frame")
            filtered.show("Filtered")
            cvs.pressed_key()

    def find_target(self, contours):
        targets = cvs.Contours()
        largest5 = contours.largest(5)
        if largest5 is not None:
            for contour in largest5:
                #contour.data_type = "rotated"
                try:
                    if contour.aspect_ratio < 0.6 and contour.solidity > 0.8 and contour.area > 50: #0.7 < contour.extent < 1.3 and contour.area > 50
                        targets.append(contour)
                except ZeroDivisionError:
                    continue
        return targets.largest(2)
    
    @staticmethod
    def get_hsv_range():
        with open('hsv_values.pickle', 'rb') as data:
            hsv_vals = pickle.load(data)["D"]
            (h1, s1, v1), (h2, s2, v2) = hsv_vals
            v1 = 55
            v2 = 225
            hsv_vals = [(h1, s1, v1), (h2, s2, v2)]
            return hsv_vals
        
    def get_gears_angle(self, b,c):
        a = 0.21
        try:
            m = (sqrt((2*(b**2)) + (2*(c**2)) - (a**2))) / 2
            wanted_angle = acos(((b ** 2) - (m ** 2) - ((a ** 2) / 4)) / ((-m) * a))
        except ValueError:
            return 400
        return degrees(wanted_angle)

    def extract_data(self, cnts):
        if cnts is not None:
            cnt_right = cnts.rightmost()
            cnt_left = cnts.leftmost()
            left_dist = px2dist(cnt_left.height, 0.13)
            right_dist = px2dist(cnt_right.height, 0.13)
            avg_dist = (left_dist + right_dist) / 2.0
            angle = self.get_gears_angle(right_dist, left_dist)
            x_avg = (cnt_right.x + cnt_left) / 2.0
            x_difference = (x_avg - self.img_centerX) / self.img_centerX
            draw_data = {"right dist": right_dist, "left dist": left_dist,"x diff": x_difference, "angle": angle}
            network_data = {"Lift X Difference": x_difference, "Lift Distance": avg_dist, "I've Got Lift In My Sight": True}
        else:
            draw_data = {}
            network_data = {"I've Got Lift In My Sight": False}
        return draw_data, network_data

    def publish_data(self, frame, draw_data, network_data):
        for key, value in network_data.iteritems():
            self.SDboard[key] = value
        for key, value in draw_data.iteritems():
            frame.println("{} : {}".format(key, value))
        self.draw_angle(draw_data["angle"], network_data["Lift Distance"], frame)

    def draw_angle(self, angle, dist, frame):
        angle = radians(angle)
        line_length = 150 * (dist / 1.3)
        base_length = 80
        basex, basey = (frame.width - base_length, 50)
        straight_line1, straight_line2 = ((basex - base_length/2, basey), (basex + base_length/2, basey))
        cv2.line(frame, tuple(map(int, straight_line1)), tuple(map(int, straight_line2)), (255, 0, 0), 4)  # draw the straight line
        end_point = (basex + cos(angle)*line_length, basey + sin(angle)*line_length)
        cv2.line(frame, tuple(map(int, (basex, basey))), tuple(map(int, end_point)), (255, 0, 0), 4)  # draw the line of the angle
