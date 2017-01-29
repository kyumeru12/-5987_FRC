import cvs
import cv2
import pickle
from math import sin, cos, acos, sqrt, degrees, radians
from params import RESIZE_FACTOR
from calcs import px2dist
from a_cool_networktable import SmartDashboard


def find_target(contours):
    targets = cvs.Contours()
    largest5 = contours.largest(5)
    if largest5 is not None:
        for contour in largest5:
            #contour.data_type = "rotated"
            try:
                if 0.7 < contour.extent < 1.3 and contour.area > 80:
                    targets.append(contour)
            except ZeroDivisionError:
                continue
    return targets.largest(2)


def get_gears_angle(b,c):
    a = 0.21
    try:
        m = (sqrt((2*(b**2)) + (2*(c**2)) - (a**2))) / 2
        wanted_angle = acos(((b ** 2) - (m ** 2) - ((a ** 2) / 4)) / ((-m) * a))
    except ValueError:
        return 400
    return degrees(wanted_angle)


def draw_angle(angle, dist, frame):
    angle = radians(angle)
    line_length = 150 * (dist / 1.3)
    base_length = 80
    basex, basey = (frame.width - base_length, 50)
    straight_line1, straight_line2 = ((basex - base_length/2, basey), (basex + base_length/2, basey))
    cv2.line(frame, tuple(map(int, straight_line1)), tuple(map(int, straight_line2)), (255, 0, 0), 4)  # draw the straight line
    end_point = (basex + cos(angle)*line_length, basey + sin(angle)*line_length)
    cv2.line(frame, tuple(map(int, (basex, basey))), tuple(map(int, end_point)), (255, 0, 0), 4)  # draw the line of the angle


with open('hsv_values.pickle', 'rb') as data:
    hsv_vals = pickle.load(data)["D"]
    (h1, s1, v1), (h2, s2, v2) = hsv_vals
    v1 = 50
    hsv_vals = [(h1, s1, v1), (h2, s2, v2)]

cam = cvs.UsbCam()
while True:
    frame = cam.read().resize(RESIZE_FACTOR)
    filtered = frame.convert('hsv').in_range(*hsv_vals)
    cnts = find_target(filtered.contours())
    if cnts is not None:
        cnt_right = cnts.rightmost()
        cnt_left = cnts.leftmost()
        frame.draw_contour(cnt_right, color=(0,0,0))
        frame.draw_contour(cnt_left)
        left_dist = px2dist(cnt_left.height, 0.13)
        right_dist = px2dist(cnt_right.height, 0.13)
        angle = get_gears_angle(right_dist, left_dist)
        frame.println("left :{:.2f}, px:{} ".format(left_dist, cnt_left.height))
        frame.println("right :{:.2f}, px:{} ".format(right_dist, cnt_right.height))
        frame.println("Angle needed is {} ".format(angle))
        draw_angle(angle, (left_dist + right_dist) / 2.0,  frame)
        #SmartDashboard["Gears Angle"] = get_gears_angle(right_dist, left_dist)
    frame.show("Frame")
    filtered.show("Filtered")
    cvs.pressed_key()

