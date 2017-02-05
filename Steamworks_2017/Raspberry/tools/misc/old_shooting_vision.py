from __future__ import division, print_function
import cvs
import pickle
from calcs import px2angle, px2dist
from params import TARGET_ASPECT_RATIO
import cv2
from math import pi, fabs
from a_cool_networktable import SmartDashboard


enable_networktable = False
class FrameHolder: pass

aframe_holder = FrameHolder()
aframe_holder.frame = None


def get_hsv_range():
    with open('hsv_values.pickle', 'rb') as data:
        hsv_vals = pickle.load(data)["D"]
        (h1, s1, v1), (h2, s2, v2) = hsv_vals
        v1 = 75
        v2 = 225
        hsv_vals = [(h1, s1, v1), (h2, s2, v2)]
        return hsv_vals


def filter_image(frame):
    """
    Get the area of the target using the hsv values
    :param img:
    :return: black & white image of the filtered target.
    """
    hsv_vals = get_hsv_range()
    filtered = frame.convert('hsv').in_range(*hsv_vals).fill_gaps()
    return filtered


def find_target(contours):  # What time is it?
    """
    :param contours:
    :return:
    """
    if len(contours) > 0:
        largest5 = contours.largest(5)
        target_aspect_ratio = TARGET_ASPECT_RATIO
        tol = 1

        rekts = cvs.Contours()
        for r in largest5:
            if target_aspect_ratio - tol < r.aspect_ratio < target_aspect_ratio + tol:
                rekts.append(r)
        if rekts:  # is it high noon?
            target = rekts.bottommost()
            return target


def main(frame_holder=aframe_holder):
    if enable_networktable:
        SDboard = SmartDashboard()
    cam = cvs.UsbCam()
    resize_factor = 0.5
    while True:
        frame = cam.read().resize(resize_factor)
        frame = frame(cv2.flip, frame, -1)
        filtered = filter_image(frame)
        contours = filtered.contours()
        target = find_target(contours)
        if target is not None:
            angle = px2angle(target.width)
            img_centerX = frame.width / 2
            x_difference = (target.x - img_centerX) / (frame.width / 2)
            if enable_networktable:
                SDboard["Boiler X Difference"] = x_difference
                SDboard["Boiler Angle"] = angle
            frame.println("X Difference: {}".format(x_difference))
            frame.println("Shooter Angle: {}".format(angle))
            frame.println("Distance (m): {}".format(px2dist(target.width)))
            frame.draw_contour(target)
        else:
            frame.println("No Target Found")
            if enable_networktable:
                SDboard["Boiler In Field View"] = False
        frame.show("frame")
        filtered.show("filtered")
        cvs.pressed_key()
        frame_holder.frame = frame

 if __name__ == "__main__":
     main()