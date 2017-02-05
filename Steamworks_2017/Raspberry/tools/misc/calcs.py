from __future__ import division, print_function
from math import atan, sqrt, pi
from params import *


def px2dist(px, target_m=TARGET_WIDTH):
    """
    Convert the number of pixels of a target (using the actual measure of the target)
     to the distance - from the camera to the target
    :param px: pixels (length) of the target
    :param target_m: the actual measure of the target in METERS
    :return: the found distance in METERS
    """
    ys = [0.41, 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5]  # meters
    xs = [640, 255, 180, 128, 102, 86, 77, 68, 60, 51]  # pixels that were measured with a target of 0.385 METERS length
    xs = map(lambda x: x * (target_m / 0.385) * RESIZE_FACTOR , xs)
    for i, n in enumerate(xs):
        if n < px:
            break
    a = (ys[i] - ys[i-1]) / (xs[i] - xs[i-1])
    b = ys[i] - a*xs[i]
    y = a * px+b

    return y


def dist2horizontal(dist):
    """
    Convert the diagonal distance - from the camera to the boiler into
    an horizontal distance - from the shooter to the boiler
    :param dist: diagonal distance in METERS
    :return: horizontal distance in METERS
     based on the difference between the heights (params.py)
    """
    height_dif = TARGET_HEIGHT_FROM_FLOOR - CAMERA_HEIGHT_FROM_FLOOR
    try:
        return sqrt(dist**2 - height_dif**2) - CAMERA2SHOOTER_XDIFFERENCE  # DAMN PYTHAGORAS
    except:
        print("Distance cannot be real with the current parameters (in params.py) - pythagoras")
        return -1

def dist2angle(dist):
    """
    Convert the horizontal distance from the boliler to the
    angle needed to shoot based on params.py
    :param dist: horizontal distance from the boliler in METERS
    :return: angles needed to shoot in DEGREES
    """
    # constants
    v = SHOOTER_SPEED
    g = -9.81  # Gravaty acceleration
    h = TARGET_HEIGHT_FROM_FLOOR - CAMERA_HEIGHT_FROM_FLOOR

    # get angle from distance und velocity [F(dist,v) = a]
    k = (g * dist ** 2) / (2 * v ** 2)

    if (dist ** 2) - 4 * k * (k - h) >= 0:
        a = atan((-dist - sqrt(dist ** 2 - 4 * k * (k - h))) / (2 * k))
    else:
        a = 0

    a *= (180 / pi)  # from radians to angles
    return a


def px2angle(px):
    """
    Convert the number of pixels in the boiler target to the angle needed to shoot
    :param px:
    :return: The angle needed to shoot
    """
    dist = px2dist(px)
    horizontal_dist = dist2horizontal(dist)
    angle = dist2angle(horizontal_dist)
    return angle
